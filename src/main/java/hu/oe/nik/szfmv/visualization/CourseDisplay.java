package hu.oe.nik.szfmv.visualization;

import hu.oe.nik.szfmv.automatedcar.AutomatedCar;
import hu.oe.nik.szfmv.environment.World;
import hu.oe.nik.szfmv.environment.WorldObject;
import javafx.scene.transform.Affine;
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
    // TODO: lehet hogy ezeket float vagy double-re kellene atalakitani, hogy ne csak egesz pixellel mozduljon el a kep
    // TODO: ha tul lassan mozog az auto es tul messzirol nezzuk (kulonben szaggat)
    public int xOffset = 0;
    public int yOffset = 0;

    // Ezt az FPS-t szeretnénk tartani
    private static final int TARGET_FPS = 24;
    // Egy ciklus hossza
    private static int CYCLE_PERIOD = 40;
    // Az aktuális renderelési és számítási ciklus kezdetének időpontja
    private static long cycle_start;
    // Az aktuális renderelési és számítási ciklus hossza
    private static long cycle_length;

    private Calendar cal;
    private static final Logger LOGGER = LogManager.getLogger();
    private final int width = 770;
    private final int height = 700;
    private final int backgroundColor = 0xEEEEEE;
    private final double SCALING_FACTOR = 0.2;

    // segédszámláló auto forgas teszteleshez, TODO: torolni
    private int f = 0;

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
        f += 5;
        try {
            // TODO
            Graphics g = getGraphics();
            cycle_start = cal.getTimeInMillis();
            xOffset = -world.getAutomatedCar().getX();
            yOffset = world.getAutomatedCar().getY();

            /*
             * létrehozunk egy másodlagos buffert, amire egyesevél felrajzoljuk
             * az elemeket, majd ha minden felkerült rá, ezt a képet rajzoljuk át
             * a fő grafikai elemünkre, így megszüntetve az elemek remegését
             */
            super.paintComponent(g);

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
            //System.out.println("FPS/TARGET FPS: " + (1000/CYCLE_PERIOD) + "/" + TARGET_FPS);

            Thread.sleep(CYCLE_PERIOD);
        } catch (InterruptedException e) {

            LOGGER.error(e.getMessage());
        }
    }

    // A statikus elemek kirajzolasa
    private void renderStaticObjects(java.util.List<WorldObject> staticObjects, Graphics buffer){
        // TODO
        for (WorldObject object: staticObjects) {
            paintComponent(buffer, object);
        }
    }

    // A dinamikus elemek kirajzolasa
    private void renderDynamicObjects(List<WorldObject> dynamicObjects, Graphics buffer){
        // TODO
        
    }

    // Az altalunk iranyitott auto kirajzolasa
    private void renderCar(AutomatedCar car, Graphics buffer){
        BufferedImage image;

        try {
            // Ezt nem jobb lenne eltarolni mar az inicializalaskor?
            image = ImageIO.read(new File(ClassLoader.getSystemResource(car.getImageFileName()).getFile()));
            car.setHeight(image.getHeight());
            car.setWidth(image.getWidth());

            int imageWidth = scaleObject(image.getWidth());
            int imageHeight = scaleObject(image.getHeight());

            AffineTransform at = AffineTransform.getTranslateInstance( width / 2 - imageWidth/2, height / 2 - imageHeight/2);
            at.rotate(Math.toRadians(f), 0,0);//imageWidth /2, imageHeight / 2);
            Graphics2D g2d = (Graphics2D) buffer;
            at.scale(SCALING_FACTOR, SCALING_FACTOR);
            g2d.drawImage(image, at, null);

        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    // TODO: ez alapjan csinaljuk meg a render fuggvenyeket
    protected void paintComponent(Graphics g, WorldObject object) {
        BufferedImage image;
        try {
            // read file from resources
            image = ImageIO.read(new File(ClassLoader.getSystemResource(object.getImageFileName()).getFile()));

            double imageWidth = scaleObject(image.getWidth());
            double imageHeight = scaleObject(image.getHeight());

            int imagePositionX = scaleObject(object.getX() + xOffset) + height/2;
            int imagePositionY = scaleObject(object.getY() + yOffset) + height/2;

            AffineTransform at = AffineTransform.getTranslateInstance( imagePositionX, imagePositionY);

            // Kep elforgatasa a megfelelo pontnal
            at.rotate(Math.toRadians(-object.getRotation()),
                    scaleObject(object.getRotPointX()),
                    scaleObject(object.getRotPointY()));//imageWidth /2, imageHeight / 2);

            // Kep atmeretezese
            at.scale(SCALING_FACTOR, SCALING_FACTOR);

            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(image, at, null);

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
        tx.rotate(object.getRotation());
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        img = op.filter(img, null);
        return img;
    }

    public void refreshFrame() {
        invalidate();
        validate();
        repaint();
    }
}
