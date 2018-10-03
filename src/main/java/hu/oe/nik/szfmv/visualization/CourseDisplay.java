package hu.oe.nik.szfmv.visualization;

import hu.oe.nik.szfmv.common.Utils;
import hu.oe.nik.szfmv.environment.World;
import hu.oe.nik.szfmv.environment.WorldObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.function.ToDoubleBiFunction;

/**
 * CourseDisplay is for providing a viewport to the virtual world where the simulation happens.
 */
public class CourseDisplay extends JPanel {

    private static final Logger LOGGER = LogManager.getLogger();
    private final int width = 770;
    private final int height = 700;
    private final int backgroundColor = 0xEEEEEE;
    //Magic Number -> Scalesing
    private final double SCALING_FACTOR = 0.5;

    /**
     * Initialize the course display
     */
    public CourseDisplay() {
        // Not using any layout manager, but fixed coordinates
        setLayout(null);
        setBounds(0, 0, width, height);
        setBackground(new Color(backgroundColor));
    }


    /**
     * Draws the world to the course display
     *
     * @param world {@link World} object that describes the virtual world
     */
    public void drawWorld(World world) {
        paintComponent(getGraphics(), world);
    }

    /**
     * Inherited method that can paint on the JPanel.
     *
     * @param g     {@link Graphics} object that can draw to the canvas
     * @param world {@link World} object that describes the virtual world
     */
    protected void paintComponent(Graphics g, World world) {
        super.paintComponent(g);
        for (WorldObject object : world.getWorldObjects()) {
            // draw objects
            BufferedImage image;
            try {
                // read file from resources
                image = ImageIO.read(new File(ClassLoader.getSystemResource(object.getImageFileName()).getFile()));

                //Set object height and width. Its not our task!
                //Todo: delete this two line if T1 finished with WorldObject tasks.
                object.setHeight(image.getHeight());
                object.setWidth(image.getWidth());

                g.drawImage(image, scaleObject(object.getX()), scaleObject(object.getY()),
                        scaleObject(object.getWidth()), scaleObject(object.getHeight()), this); // see javadoc for more info on the parameters
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

    //Scale object with a scaling magic number.
    private int scaleObject(int unit)
    {
        return (int)(unit*SCALING_FACTOR);
    }

    /**
     * Intended to use for refreshing the course display after redrawing the world
     */
    public void refreshFrame() {
        invalidate();
        validate();
        repaint();
    }
}
