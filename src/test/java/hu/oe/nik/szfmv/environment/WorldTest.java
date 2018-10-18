package hu.oe.nik.szfmv.environment;

import org.junit.Test;

import static org.junit.Assert.*;

public class WorldTest {


    private World w = new World(1, 1);


    @Test
    public void addObjectToWorld() {
        WorldObject wo = new WorldObject(1, 1, "man.png");
        int size = w.getWorldObjects().size();
        w.addObjectToWorld(wo);
        assertEquals(size+1, w.getWorldObjects().size());
    }

    @Test
    public void getWorldObjects() {
        assertFalse(w.getWorldObjects().isEmpty());
    }

    @Test
    public void getWidth() {
        assertEquals(1, w.getWidth());
    }

    @Test
    public void setWidth() {
        w.setWidth(2);
        assertEquals(2, w.getWidth());
    }

    @Test
    public void getHeight() {
        assertEquals(1, w.getHeight());
    }

    @Test
    public void setHeight() {
        w.setHeight(2);
        assertEquals(2, w.getHeight());
    }
}