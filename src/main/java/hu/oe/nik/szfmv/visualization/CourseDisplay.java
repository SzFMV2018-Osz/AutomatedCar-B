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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

/**
 * CourseDisplay is for providing a viewport
 * to the virtual world where the simulation happens.
 */
public class CourseDisplay extends JPanel {

    /**
     * Integer.
     */
    private int xOffset = 0;
    /**
     * Integer.
     */
    private int yOffset = 0;

    /**
     * Integer.
     */
    public static final int TARGET_FPS = 24;
    /**
     * Integer.
     */
    private static double CYCLE_PERIOD = 40;
    /**
     * Integer.
     */
    private static long cycle_start;
    /**
     * Integer.
     */
    private static long cycle_length;
    /**
     * Calendar.
     */
    private Calendar cal;
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * Integer.
     */
    private final int width = 770;
    /**
     * Integer.
     */
    private final int height = 700;
    /**
     * Integer.
     */
    private final int backgroundColor = 0xEEEEEE;
    /**
     * double.
     */
    private final double SCALING_FACTOR = 1;

    /**
     * Initialize the course display.
     */
    public CourseDisplay() {
        setLayout(null);
        setBounds(0, 0, width, height);
        setBackground(new Color(backgroundColor));
        cal = Calendar.getInstance();
    }

    /**
     * Draws the world to the course display.
     *
     * @param world {@link World} object that describes the virtual world
     */

    public void drawWorld(final World world) {
        Image offscreen = createImage(world.getWidth(), world.getHeight());
        Graphics screenBuffer = offscreen.getGraphics();
        Graphics g = getGraphics();
        super.paintComponent(screenBuffer);
        ((Graphics2D) screenBuffer).setBackground(new Color(backgroundColor));
        screenBuffer.clearRect(0, 0, world.getWidth(), world.getHeight());

        try {
            cycle_start = cal.getTimeInMillis();

            xOffset = width / 2 - scaleObject(world.getAutomatedCar()
                    .getX() - world.getAutomatedCar().getWidth() / 2);
            yOffset = height / 2 - scaleObject(world.getAutomatedCar()
                    .getY() - world.getAutomatedCar().getHeight() / 2);

            /**
             * Let's create a secondary buffer on we paint the objects each by each,
             * and when everything is painted, we draw this screen onto the main graphic element
             * to avoid the vibration-effect of the objects
             */
            // TODO: Get a staticObjectList, but we do not have it yet, so at here we load all the worldObjects
            renderStaticObjects(world.getWorldObjects(), screenBuffer);

            // TODO: Get a dynamicObjectList, but we do not have it yet
            //renderDynamicObjects(world.getDynamicObjects(), screenBuffer);

            renderCar(world.getAutomatedCar(), screenBuffer);

            // draw the buffer on the screen
            g.drawImage(offscreen, 0, 0, this);

            // FIX FPS
            cycle_length = cal.getTimeInMillis() - cycle_start;
            // Calculate necessary delay time (elapsed time * TARGET FPS)
            CYCLE_PERIOD = 1000 / TARGET_FPS - cycle_length;
            if (CYCLE_PERIOD < 0) CYCLE_PERIOD = 0;
            System.out.printf("FPS/TARGET FPS: %.2f / %d \n", (1000 / CYCLE_PERIOD), TARGET_FPS);

            Thread.sleep(Math.round(CYCLE_PERIOD));
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage());
        }
    }

    // Draw static objects
    private void renderStaticObjects(java.util.List<WorldObject> staticObjects, Graphics screenBuffer) {
        for (WorldObject object : staticObjects) {
            paintComponent(screenBuffer, object);
        }
    }

    // Draw dynamic objects, but it's not used yet since we do not have the proper input for that
    private void renderDynamicObjects(List<WorldObject> dynamicObjects, Graphics screenBuffer) {
        for (WorldObject object : dynamicObjects) {
            paintComponent(screenBuffer, object);
        }
    }

    // Draw the main car
    private void renderCar(AutomatedCar car, Graphics screenBuffer) {
        BufferedImage image;
        try {
            image = ImageIO.read(new File(ClassLoader.getSystemResource(car.getImageFileName()).getFile()));
            int imageWidth = scaleObject(image.getWidth());
            int imageHeight = scaleObject(image.getHeight());

            AffineTransform at = new AffineTransform();
            at.setToTranslation(width / 2 - imageWidth / 2, height / 2 - imageHeight / 2);
            at.rotate(car.getRotation(), 0, 0);
            at.scale(SCALING_FACTOR, SCALING_FACTOR);
            Graphics2D g2d = (Graphics2D) screenBuffer;
            g2d.drawImage(image, at, null);

        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    // TODO: Create the separated render methods based on this one
    protected void paintComponent(Graphics g, WorldObject object) {
        // draw objects
        BufferedImage image = object.getImage();

        int imagePositionX = scaleObject(object.getX()) + xOffset;
        int imagePositionY = scaleObject(object.getY()) + yOffset;
        AffineTransform at = new AffineTransform();
        at.setToTranslation(imagePositionX, imagePositionY);
        Point refPoint = ReferencePointsXMLReadClass.checkIsReferenceOrNot(object.getImageFileName());
        at.translate(-refPoint.x * SCALING_FACTOR, -refPoint.y * SCALING_FACTOR);

        at.rotate(object.getRotation(), SCALING_FACTOR * refPoint.x, SCALING_FACTOR * refPoint.y);

        at.scale(modifyScaleFactorFor(image.getWidth()), modifyScaleFactorFor(image.getHeight()));
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, at, null);
    }

    //Scale object with a scaling magic number.
    private int scaleObject(int unit) {
        return (int) (unit * modifyScaleFactorFor(unit));
    }

    private double modifyScaleFactorFor(int current) {
        double roundedScale = Math.round(current * SCALING_FACTOR);
        double modifiedScaleFactor = roundedScale / current;
        return modifiedScaleFactor;
    }

    public void refreshFrame() {
        invalidate();
        validate();
        repaint();
    }
}
