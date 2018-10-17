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
     * Integer for the fps.
     */
    public static final int TARGET_FPS = 24;
    /**
     * Integer for the scaling.
     */
    public static final int SCALING_FACTOR = 1;
    /**
     * Logger for exceptions.
     */
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * Double for get one cycle period during the run.
     */
    private static double cyclePeriodCONSTANST = 40;
    /**
     * Long for the started.
     */
    private static long cycleStart;
    /**
     * Long for the lenght of a cycle.
     */
    private static long cycleLength;
    /**
     * Integer for objects start position (x).
     */
    private int xOffset = 0;
    /**
     * Integer for objects start position (y).
     */
    private int yOffset = 0;
    /**
     * Calendar for the fps.
     */
    private Calendar cal;

    /**
     * Integer for window width.
     */
    private final int width = 770;
    /**
     * Integer for window height.
     */
    private final int height = 700;
    /**
     * Integer for windows color.
     */
    private final int backgroundColor = 0xEEEEEE;
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

    public void drawWorld(
            final World world) {
        Image offscreen = createImage(world.getWidth(), world.getHeight());
        Graphics screenBuffer = offscreen.getGraphics();
        Graphics g = getGraphics();
        super.paintComponent(screenBuffer);
        ((Graphics2D) screenBuffer).setBackground(new Color(backgroundColor));
        screenBuffer.clearRect(0, 0, world.getWidth(), world.getHeight());

        try {
            cycleStart = cal.getTimeInMillis();

            xOffset = width / 2 - scaleObject(world.getAutomatedCar()
                    .getX() - world.getAutomatedCar().getWidth() / 2);
            yOffset = height / 2 - scaleObject(world.getAutomatedCar()
                    .getY() - world.getAutomatedCar().getHeight() / 2);

            /**
             * Let's create a secondary buffer on we
             * paint the objects each by each,
             * and when everything is painted,
             * we draw this screen onto the main graphic element
             * to avoid the vibration-effect of the objects.
             */
            renderStaticObjects(world.getWorldObjects(), screenBuffer);

            //renderDynamicObjects(world.getDynamicObjects(), screenBuffer);

            renderCar(world.getAutomatedCar(), screenBuffer);

            // draw the buffer on the screen
            g.drawImage(offscreen, 0, 0, this);

            // FIX FPS
            cycleLength = cal.getTimeInMillis() - cycleStart;
            // Calculate necessary delay time (elapsed time * TARGET FPS)
            cyclePeriodCONSTANST = 1000 / TARGET_FPS - cycleLength;
            if (cyclePeriodCONSTANST < 0) {
                cyclePeriodCONSTANST = 0;
            }
            System.out.printf("FPS/TARGET FPS: %.2f / %d \n",
                    (1000 / cyclePeriodCONSTANST), TARGET_FPS);

            Thread.sleep(Math.round(cyclePeriodCONSTANST));
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Draw static objects.
     *
     * @param staticObjects static object list
     * @param screenBuffer  for stop vibration
     */
    private void renderStaticObjects(
            final java.util.List<WorldObject> staticObjects,
            final Graphics screenBuffer) {
        for (WorldObject object : staticObjects) {
            paintComponent(screenBuffer, object);
        }
    }

    /**
     * Draw dynamic objects, but it's not used yet
     * since we do not have the proper input for that.
     *
     * @param dynamicObjects dynamic object list
     * @param screenBuffer   for stop vibration
     */
    private void renderDynamicObjects(final List<WorldObject> dynamicObjects,
                                      final Graphics screenBuffer) {
        for (WorldObject object : dynamicObjects) {
            paintComponent(screenBuffer, object);
        }
    }

    /**
     * Draw the main car.
     *
     * @param car          main car
     * @param screenBuffer for stop vibration
     */
    private void renderCar(final AutomatedCar car,
                           final Graphics screenBuffer) {
        BufferedImage image;
        try {
            image = ImageIO.read(new File(ClassLoader.
                    getSystemResource(car.getImageFileName()).getFile()));
            int imageWidth = scaleObject(image.getWidth());
            int imageHeight = scaleObject(image.getHeight());

            AffineTransform at = new AffineTransform();
            at.setToTranslation(width / 2 - imageWidth / 2,
                    height / 2 - imageHeight / 2);
            at.rotate(car.getRotation(), 0, 0);
            at.scale(SCALING_FACTOR, SCALING_FACTOR);
            Graphics2D g2d = (Graphics2D) screenBuffer;
            g2d.drawImage(image, at, null);

        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Draw the objects on the right position
     * with the right translate and rotate.
     *
     * @param g      the grafic for draw
     * @param object Every object witch have to appear on the screen
     */
    protected void paintComponent(final Graphics g, final WorldObject object) {
        // draw objects
        BufferedImage image = object.getImage();

        int imagePositionX = scaleObject(object.getX()) + xOffset;
        int imagePositionY = scaleObject(object.getY()) + yOffset;
        AffineTransform at = new AffineTransform();
        at.setToTranslation(imagePositionX, imagePositionY);
        Point refPoint = ReferencePointsXMLReadClass.
                checkIsReferenceOrNot(object.getImageFileName());
        at.translate(-refPoint.x * SCALING_FACTOR,
                -refPoint.y * SCALING_FACTOR);

        at.rotate(object.getRotation(), SCALING_FACTOR * refPoint.x,
                SCALING_FACTOR * refPoint.y);

        at.scale(modifyScaleFactorFor(image.getWidth()),
                modifyScaleFactorFor(image.getHeight()));
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, at, null);
    }

    /**
     * Scale object with a scaling magic number.
     *
     * @param unit scale number
     * @return the new scale number after modify
     */
    //
    private int scaleObject(final int unit) {
        return (int) (unit * modifyScaleFactorFor(unit));
    }

    /**
     * Calculate new scale number for objects.
     *
     * @param current the current scale number
     * @return the calculated new scale number
     */
    private double modifyScaleFactorFor(final int current) {
        double roundedScale = Math.round(current * SCALING_FACTOR);
        double modifiedScaleFactor = roundedScale / current;
        return modifiedScaleFactor;
    }

    /**
     * For refresh the screen.
     */
    public void refreshFrame() {
        invalidate();
        validate();
        repaint();
    }
}
