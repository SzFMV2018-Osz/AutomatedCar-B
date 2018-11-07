package hu.oe.nik.szfmv.visualization;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

/**
 * Class for represent the inner spaces for the images to handle the collisions.
 */
public class ColliderModel {
    /**
     * Private String, contains the name of the image.
     */
    private String name;
    /**
     * Private int, the x coordinate of the image.
     */
    private int x;
    /**
     * Private int, the y coordinate of the image.
     */
    private int y;
    /**
     * Private RectangularShape, the shape of the image.
     */
    private RectangularShape shape;

    /**
     * Constructor for the class.
     * @param name      Name of the image.
     * @param x         X coordinate of the image.
     * @param y         Y coordinate of the image.
     * @param width     Width of the image.
     * @param height     Name of the image.
     * @param shape     Shape of the image.
     */
    public ColliderModel(String name, int x, int y, int width, int height, String shape) {
        this.name = name;
        this.x = x;
        this.y = y;
        if(shape.equals("rect"))
        {
            this.shape = new Rectangle2D.Double(0,0,width,height);
        }
        else if(shape.equals("ellipse"))
        {
            this.shape = new Ellipse2D.Double(0,0,width,height);
        }
    }

    /**
     * Get the shape of the image.
     * @return Shape of the image.
     */
    public RectangularShape getShape() {
        return shape;
    }

    /**
     * Get the name of the image.
     * @return Name of the image.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the x coordinate of the image.
     * @return X coordinate of the image.
     */
    public int getX() {
        return x;
    }

    /**
     * Get the y coordinate of the image.
     * @return Y coordinate of the image.
     */
    public int getY() {
        return y;
    }
}
