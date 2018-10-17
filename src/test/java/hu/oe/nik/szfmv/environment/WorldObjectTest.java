package hu.oe.nik.szfmv.environment;

import org.junit.Test;

import static org.junit.Assert.*;

public class WorldObjectTest {

    private WorldObject wo = new WorldObject(1, 1, "man.png");
    private static final double THRESHOLD = 0.0001d;


    @Test
    public void getX() {
        assertEquals(1, wo.getX());
    }

    @Test
    public void getY() {
        assertEquals(1, wo.getY());
    }

    @Test
    public void getWidth() {
        wo.setWidth(1);
        assertEquals(1, wo.getWidth());

    }

    @Test
    public void getHeight() {
        wo.setHeight(1);
        assertEquals(1, wo.getHeight());
    }

    @Test
    public void getRotation() {
        wo.setRotation(1);
        assertEquals(1,wo.getRotation(), THRESHOLD);
    }

    @Test
    public void getImageFileName() {
        assertEquals("man.png", wo.getImageFileName());
    }

    @Test
    public void setX() {
        wo.setX(2);
        assertEquals(2, wo.getX());
    }

    @Test
    public void setY() {
        wo.setY(2);
        assertEquals(2, wo.getY());
    }

    @Test
    public void setWidth() {
        wo.setWidth(2);
        assertEquals(2, wo.getWidth());
    }

    @Test
    public void setHeight() {
        wo.setHeight(2);
        assertEquals(2, wo.getHeight());
    }

    @Test
    public void setRotation() {
        wo.setRotation(2);
        assertEquals(2, wo.getRotation(), THRESHOLD);
    }

    @Test
    public void setImageFileName() {
        wo.setImageFileName("bicycle.png");
        assertEquals("bicycle.png", wo.getImageFileName());
    }
}