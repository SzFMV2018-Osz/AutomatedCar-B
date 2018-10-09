package hu.oe.nik.szfmv.environment;

import org.junit.Test;

import static org.junit.Assert.*;

public class StaticObjectTest {

    private static final double THRESHOLD = 0.0001d;
    StaticObject so = new StaticObject(1, 1, "image.jpg", "tree");


    @Test
    public void getX() {
        assertEquals(1, so.getX(), THRESHOLD);
    }

    @Test
    public void getY() {
        assertEquals(1, so.getY(), THRESHOLD);
    }

    @Test
    public void getWidth() {
        so.setWidth(1);
        assertEquals(1, so.getWidth(), THRESHOLD);
    }

    @Test
    public void getHeight() {
        so.setHeight(1);
        assertEquals(1, so.getHeight(), THRESHOLD);
    }

    @Test
    public void getRotation() {
        so.setRotation(1);
        assertEquals(1, so.getRotation(), THRESHOLD);
    }

    @Test
    public void getImageFileName() {
        assertEquals("image.jpg", so.getImageFileName());
    }

    @Test
    public void setX() {
        so.setX(2);
        assertEquals(2, so.getX(), THRESHOLD);
    }

    @Test
    public void setY() {
        so.setY(2);
        assertEquals(2, so.getY(), THRESHOLD);
    }

    @Test
    public void setWidth() {
        so.setWidth(2);
        assertEquals(2, so.getWidth(), THRESHOLD);
    }

    @Test
    public void setHeight() {
        so.setHeight(2);
        assertEquals(2, so.getHeight(), THRESHOLD);
    }

    @Test
    public void setRotation() {
        so.setRotation(2);
        assertEquals(2, so.getRotation(), THRESHOLD);
    }

    @Test
    public void setImageFileName() {
        so.setImageFileName("image2.jpg");
        assertEquals("image2.jpg", so.getImageFileName());
    }

    @Test
    public void getType() {
        assertEquals(StaticObject.TypeEnum.TREE, so.GetType());

    }

    @Test
    public void setType() {
        so.SetType(StaticObject.TypeEnum.ROAD_2LANE_45RIGHT);
        assertEquals(StaticObject.TypeEnum.ROAD_2LANE_45RIGHT, so.GetType());
    }

    @Test
    public void getCollidable() {
        assertEquals(true, so.GetCollidable());
    }

    @Test
    public void setCollidable() {
        so.SetCollidable(false);
        assertEquals(false, so.GetCollidable());
    }
}