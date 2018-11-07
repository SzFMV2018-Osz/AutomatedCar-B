package hu.oe.nik.szfmv.visualization;

import java.awt.image.BufferedImage;

/**
 * Class for buffer the images.
 * Resolve FPS drop.
 */
public class ImageBuffer {
    /**
     * Private string, contains the name of the image.
     */
    private String name;
    /**
     * Private BufferedImage, contains the real image.
     */
    private BufferedImage image;
    /**
     * Constructor for the class.
     * @param nameParam The name of the image.
     * @param imageParam The image.
     */
    public ImageBuffer(final String nameParam, final BufferedImage imageParam) {
        this.name = nameParam;
        this.image = imageParam;
    }
    /**
     * Get the name of the image.
      * @return The name of the image.
     */
    public String getName() {
        return name;
    }
    /**
     * Get the image.
     * @return The real image.
     */
    public BufferedImage getImage() {
        return image;
    }
}
