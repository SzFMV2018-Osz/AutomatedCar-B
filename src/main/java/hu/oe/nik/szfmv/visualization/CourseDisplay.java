package hu.oe.nik.szfmv.visualization;

import hu.oe.nik.szfmv.automatedcar.AutomatedCar;
import hu.oe.nik.szfmv.environment.World;
import hu.oe.nik.szfmv.environment.WorldObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

/**
 * CourseDisplay is for providing a viewport to the virtual world where the simulation happens.
 */
public class CourseDisplay extends JPanel {

    // Ezek a valtozok tartalmazzak a kiinduloponttol valo elmozdulas mennyiseget
    // ezeket is SCALELNI kell !!!
    public int xOffset = 0;
    public int yOffset = 0;

    // Ezt az FPS-t szeretnénk tartani
    public static final int TARGET_FPS = 24;
    // Egy ciklus hossza
    private static int CYCLE_PERIOD = 40;
    // Az aktuális renderelési és számítási ciklus kezdetének időpontja
    private static long cycle_start;
    // Az aktuális renderelési és számítási ciklus hossza
    private static long cycle_length;

    // asdasd asdasdasd asdas 3

    private Calendar cal;

    private static final Logger LOGGER = LogManager.getLogger();
    private final int width = 770;
    private final int height = 700;
    private final int backgroundColor = 0xEEEEEE;
    private final double SCALING_FACTOR = 0.5;

    /**
     * Initialize the course display
     */
    public CourseDisplay() {
        // Not using any layout manager, but fixed coordinates
        setLayout(null);
        setBounds(0, 0, width, height);
        setBackground(new Color(backgroundColor));
        cal = Calendar.getInstance();
    }

    /**
     * Draws the world to the course display
     *
     * @param world {@link World} object that describes the virtual world
     */
    public void drawWorld(World world) {
        //paintComponent(getGraphics(), world);

        try {
            // TODO
            cycle_start = cal.getTimeInMillis();

            xOffset = -world.getAutomatedCar().getX();
            yOffset = world.getAutomatedCar().getY();

            /*
             * létrehozunk egy másodlagos buffert, amire egyesevél felrajzoljuk
             * az elemeket, majd ha minden felkerült rá, ezt a képet rajzoljuk át
             * a fő grafikai elemünkre, így megszüntetve az elemek remegését
             */
            Graphics g = getGraphics();
            Image offscreen = createImage(width, height);
            Graphics screenBuffer = offscreen.getGraphics();

            // TODO: StaticList-et kapjon, de az  meg nincs
            renderStaticObjects(world.getWorldObjects(), screenBuffer);

            // TODO: DynamicList-et kapjon, de az meg nincs
            //renderDynamicObjects(world.getDynamicObjects(), screenBuffer);

            renderCar(world.getAutomatedCar(), screenBuffer);

            // buffer kirajzolasa az kepernyore
            g.drawImage(offscreen, 0, 0,this);

            // FIX FPS
            cycle_length = cal.getTimeInMillis() - cycle_start;
            // Szükséges késleltetési idő kiszámítása (eltelt idő * TARGET FPS)
            CYCLE_PERIOD = (int)(1000 - (cycle_length * TARGET_FPS)) / TARGET_FPS;
            System.out.println("FPS/TARGET FPS: " + (1000/CYCLE_PERIOD) + "/" + TARGET_FPS);

            Thread.sleep(CYCLE_PERIOD);
        } catch (InterruptedException e) {

            LOGGER.error(e.getMessage());
        }
    }

    // A statikus elemek kirajzolasa
    private void renderStaticObjects(java.util.List<WorldObject> staticObjects){
        // TODO
        for (WorldObject object: staticObjects) {
            paintComponent(getGraphics(), object);
        }
    }

    // A dinamikus elemek kirajzolasa
    private void renderDynamicObjects(List<WorldObject> dynamicObjects){
        // TODO
        
    }

    // Az altalunk iranyitott auto kirajzolasa
    private void renderCar(AutomatedCar car){
        // TODO
        Graphics g = getGraphics();
        BufferedImage image;

        this.xOffset = -car.getX();
        this.yOffset = car.getY();

        try {
            // Ezt nem jobb lenne eltarolni mar az inicializalaskor?
            image = ImageIO.read(new File(ClassLoader.getSystemResource(car.getImageFileName()).getFile()));
            car.setHeight(image.getHeight());
            car.setWidth(image.getWidth());
            image = RotateTransform(image,car);
            int imageWidth = scaleObject(image.getWidth());
            int imageHeight = scaleObject(image.getHeight());

            buffer.drawImage(image, width / 2 - imageWidth/2, height / 2 - imageHeight/2, imageWidth,
            g.drawImage(image, width / 2 - imageWidth/2, height / 2 - imageWidth/2, imageWidth,
                    imageHeight, this);

        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        car.setRotation((float)Math.toRadians((Math.toDegrees(car.getRotation())+3)));
    }

    // TODO: ez alapjan csinaljuk meg a render fuggvenyeket
    protected void paintComponent(Graphics g, WorldObject object) {
        super.paintComponent(g);

        // draw objects
        BufferedImage image;
        try {
            // read file from resources
            image = ImageIO.read(new File(ClassLoader.getSystemResource(object.getImageFileName()).getFile()));

            //Set object height and width. Its not our task!
            //Todo: delete this two line if T1 finished with WorldObject tasks.
            //object.setHeight(image.getHeight());
            //object.setWidth(image.getWidth());

            g.drawImage(image, scaleObject(object.getX() + xOffset), scaleObject(object.getY() + yOffset), scaleObject(image.getWidth()),
                    scaleObject(image.getHeight()), this);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    //Scale object with a scaling magic number.
    private int scaleObject(int unit) { return (int)(unit*SCALING_FACTOR); }

    /**
     * Intended to use for refreshing the course display after redrawing the world
     */
    private BufferedImage RotateTransform(BufferedImage img, WorldObject object)
    {
        AffineTransform tx = new AffineTransform();
        Point refPoint = ReferencePointsXMLReadClass.CheckIsReferenceOrNot(object.getImageFileName());
        Point translate = getTranslateUnit(new Point(0,0),new Point(img.getWidth(),0), new Point(0,img.getHeight()),
                new Point(img.getWidth(),img.getHeight()),refPoint,object.getRotation());
        tx.translate(translate.x,translate.y);
        tx.rotate(object.getRotation(), refPoint.x, refPoint.y);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        img = op.filter(img, null);
        return img;
    }

    private Point getTranslateUnit(Point BF, Point JF, Point BA, Point JA, Point refPoint, float rotation)
    {
        Point BFn = new Point((int)((BF.x-refPoint.x)*Math.cos(rotation)- (BF.y-refPoint.y)*Math.sin(rotation))+refPoint.x,
                (int)((BF.y-refPoint.y)*Math.cos(rotation) + (BF.x-refPoint.x)*Math.sin(rotation))+refPoint.y);
        Point JFn = new Point((int)((JF.x-refPoint.x)*Math.cos(rotation)- (JF.y-refPoint.y)*Math.sin(rotation))+refPoint.x,
                (int)((JF.y-refPoint.y)*Math.cos(rotation) + (JF.x-refPoint.x)*Math.sin(rotation))+refPoint.y);
        Point BAn = new Point((int)((BA.x-refPoint.x)*Math.cos(rotation)- (BA.y-refPoint.y)*Math.sin(rotation))+refPoint.x,
                (int)((BA.y-refPoint.y)*Math.cos(rotation) + (BA.x-refPoint.x)*Math.sin(rotation))+refPoint.y);
        Point JAn = new Point((int)((JA.x-refPoint.x)*Math.cos(rotation)- (JA.y-refPoint.y)*Math.sin(rotation))+refPoint.x,
                (int)((JA.y-refPoint.y)*Math.cos(rotation) + (JA.x-refPoint.x)*Math.sin(rotation))+refPoint.y);
        int minX = Math.min(Math.min(Math.min(JFn.x,BAn.x),JAn.x),BFn.x);
        int minY = Math.min(Math.min(Math.min(JFn.y,BAn.y),JAn.y),BFn.y);
        //if(refPoint.x==0 && minX > 0) minX = 0;
        //if(refPoint.y==0 && minY > 0) minY = 0;
        return new Point((-minX), (-minY));
    }

    public void refreshFrame() {
        invalidate();
        validate();
        repaint();
    }
}
