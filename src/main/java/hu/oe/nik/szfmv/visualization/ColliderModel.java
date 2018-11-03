package hu.oe.nik.szfmv.visualization;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

public class ColliderModel {
    private String name;
    private int x;
    private int y;
    private int hight;
    private int width;
    private RectangularShape shape;

    public ColliderModel(String name, int x, int y, int width, int hight, String shape) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.hight = hight;
        if(shape.equals("rect"))
        {
            this.shape = new Rectangle2D.Double(x,y,width,hight);
        }
        else if(shape.equals("ellipse"))
        {
            this.shape = new Ellipse2D.Double(x,y,width,hight);
        }
    }

    public RectangularShape getShape() {
        return shape;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHight() {
        return hight;
    }
}
