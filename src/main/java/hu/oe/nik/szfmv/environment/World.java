package hu.oe.nik.szfmv.environment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import hu.oe.nik.szfmv.automatedcar.AutomatedCar;

public class World {
    public static List<WorldObject> objects;

    private int width;
    private int height;
    private List<WorldObject> worldObjects = new ArrayList<>();

    private AutomatedCar automatedCar;

    public void setWorldObjects(List<WorldObject> worldObjects) {
        this.worldObjects = worldObjects;
    }

    public AutomatedCar getAutomatedCar() {
        return automatedCar;
    }

    public void setAutomatedCar(AutomatedCar automatedCar) {
        this.automatedCar = automatedCar;
    }

    /**
     * Creates the virtual world with the given dimension.
     *
     * @param width  the width of the virtual world
     * @param height the height of the virtual world
     */
    public World(int width, int height) {
        this.width = width;
        this.height = height;
        this.worldObjects = DataReader.getDataFromDocument(DataReader.xmlReader(
                "src" + File.separator + "main" + File.separator + "resources" + File.separator + "test_world.xml"));
        World.objects = worldObjects;
    }

    /**
     * Add an object to the virtual world.
     *
     * @param o {@link WorldObject} to be added to the virtual world
     */
    public void addObjectToWorld(WorldObject o) {
        worldObjects.add(o);
    }

    public List<WorldObject> getWorldObjects() {
        return worldObjects;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
