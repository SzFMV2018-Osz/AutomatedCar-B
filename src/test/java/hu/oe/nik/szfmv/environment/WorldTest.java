package hu.oe.nik.szfmv.environment;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class WorldTest {

    private static final double THRESHOLD = 0.0001d;
    World w = new World(1, 1);

    private List<DynamicObject> dynamicObjects = new ArrayList<>();
    private List<StaticObject> staticObjects = new ArrayList<>();
    private List<WorldObject> worldObjects = new ArrayList<>();

    public void setW(World w) {

        w.addObjectToWorld(new WorldObject(1, 1, "image.jpg"));
        w.addObjectToDynamic(new DynamicObject(1, 1, "image1.jpg", "woman"));
        w.addObjectToStatic(new StaticObject(1, 1, "image2.jpg", "crosswalk"));

        dynamicObjects.add(new DynamicObject(1, 1, "image1.jpg", "woman"));
        staticObjects.add(new StaticObject(1, 1, "image2.jpg", "crosswalk"));
        worldObjects.add(new WorldObject(1, 1, "image.jpg"));

    }


    @Test
    public void getWidth() {
        assertEquals(1, w.getWidth(), THRESHOLD);
    }

    @Test
    public void getHeight() {
        assertEquals(1, w.getHeight(), THRESHOLD);
    }

    @Test
    public void setWidth() {
        w.setWidth(2);
        assertEquals(2, w.getWidth(), THRESHOLD);
    }

    @Test
    public void setHeight() {
        w.setHeight(2);
        assertEquals(2, w.getHeight(), THRESHOLD);
    }

    @Test
    public void getWorldObjects() {
        assertEquals(worldObjects, w.getWorldObjects());
    }

    @Test
    public void getStaticObjects() {
        assertEquals(staticObjects, w.getStaticObjects());
    }

    @Test
    public void getDynamicObjects() {
        assertEquals(dynamicObjects, w.getDynamicObjects());
    }

    @Test
    public void addObjectToWorld() {
        w.addObjectToWorld(new WorldObject(2, 2, "image3.jpg"));
        assertEquals(1, w.getWorldObjects().size());
    }

    @Test
    public void addObjectToStatic() {
        w.addObjectToStatic(new StaticObject(1, 1, "image4.jpg", "crosswalk"));
        assertEquals(1, w.getStaticObjects().size());
    }

    @Test
    public void addObjectToDynamic() {
        w.addObjectToDynamic(new DynamicObject(1, 1, "image5.jpg", "woman"));
        assertEquals(1, w.getDynamicObjects().size());
    }


}