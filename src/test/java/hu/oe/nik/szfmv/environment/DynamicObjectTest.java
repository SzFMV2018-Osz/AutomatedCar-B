package hu.oe.nik.szfmv.environment;

import org.junit.Test;

import static org.junit.Assert.*;

public class DynamicObjectTest {

    private static final double THRESHOLD = 0.0001d;

    DynamicObject dc = new DynamicObject(1, 1, "image.jpg", "car");


    @Test
    public void move() {
    }

    @Test
    public void getType() {
        assertEquals(DynamicObject.TypeEnumDynamic.CAR, dc.getType());
    }

    @Test
    public void setType() {
        dc.setType(DynamicObject.TypeEnumDynamic.BICYCLE);
        assertEquals(DynamicObject.TypeEnumDynamic.BICYCLE, dc.getType());
    }

    @Test
    public void getX() {
        assertEquals(1, dc.getX(), THRESHOLD);
    }

    @Test
    public void getY() {
        assertEquals(1, dc.getY(), THRESHOLD);
    }

    @Test
    public void getWidth() {
        dc.setWidth(1);
        assertEquals(1, dc.getWidth(), THRESHOLD);
    }

    @Test
    public void getHeight() {
        dc.setHeight(1);
        assertEquals(1, dc.getHeight(), THRESHOLD);
    }

    @Test
    public void getRotation() {
        dc.setRotation(1);
        assertEquals(1, dc.getRotation(), THRESHOLD);
    }

    @Test
    public void getImageFileName() {
        assertEquals("image.jpg", dc.getImageFileName());
    }

    @Test
    public void setX() {
        dc.setX(2);
        assertEquals(2, dc.getX(), THRESHOLD);
    }

    @Test
    public void setY() {
        dc.setY(2);
        assertEquals(2, dc.getY(), THRESHOLD);
    }

    @Test
    public void setWidth() {
        dc.setWidth(2);
        assertEquals(2, dc.getWidth(), THRESHOLD);
    }

    @Test
    public void setHeight() {
        dc.setHeight(2);
        assertEquals(2, dc.getHeight(), THRESHOLD);
    }

    @Test
    public void setRotation() {
        dc.setRotation(2);
        assertEquals(2, dc.getRotation(), THRESHOLD);
    }

    @Test
    public void setImageFileName() {
        dc.setImageFileName("image2.jpg");
        assertEquals("image2.jpg", dc.getImageFileName());
    }
}