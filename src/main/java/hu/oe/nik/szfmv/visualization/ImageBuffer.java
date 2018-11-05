package hu.oe.nik.szfmv.visualization;

import java.awt.image.BufferedImage;

public class ImageBuffer {
    private String name;
    private BufferedImage image;

    public ImageBuffer(String name, BufferedImage image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public BufferedImage getImage() {
        return image;
    }
}
