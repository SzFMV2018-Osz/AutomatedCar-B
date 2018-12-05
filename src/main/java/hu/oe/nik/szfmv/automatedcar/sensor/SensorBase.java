package hu.oe.nik.szfmv.automatedcar.sensor;

import hu.oe.nik.szfmv.environment.WorldObject;

import java.awt.*;
import java.util.List;

public abstract class SensorBase {
    protected List<WorldObject> worldObjects;
    protected float rotation;
    protected int viewDistance;
    protected SensorType sensorType;
    protected Triangle triangle;

    /**
     * Constructor for the SensorBase class
     * @param worldObjects the list of the world objects needed for the sensors.
     * @param coordinate the coordinate of the sensor
     * @param sensorType the type of the sensor
     */
    public SensorBase(List<WorldObject> worldObjects, Point coordinate, SensorType sensorType, int viewDistance, float rotation) {
        this.worldObjects = worldObjects;
        this.sensorType = sensorType;
        this.viewDistance = viewDistance;
        this.rotation = rotation;
        this.triangle = new Triangle(viewDistance, rotation, coordinate.getX(), coordinate.getY());
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public List<WorldObject> getWorldObjects() {
        return worldObjects;
    }

    public int getViewDistance() {
        return viewDistance;
    }

    public Triangle getTriangle() {
        return triangle;
    }
}