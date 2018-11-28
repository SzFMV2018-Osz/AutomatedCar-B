package hu.oe.nik.szfmv.automatedcar.sensor;

import hu.oe.nik.szfmv.environment.WorldObject;

import java.awt.*;
import java.util.List;

public abstract class SensorBase {
    private List<WorldObject> worldObjects;
    private Point coordinate;
    private SensorType sensorType;

    /**
     * Constructor for the SensorBase class
     * @param worldObjects the list of the world objects needed for the sensors.
     * @param coordinate the coordinate of the sensor
     * @param sensorType the type of the sensor
     */
    public SensorBase(List<WorldObject> worldObjects, Point coordinate, SensorType sensorType) {
        this.worldObjects = worldObjects;
        this.coordinate = coordinate;
        this.sensorType = sensorType;
    }

    public void setPosition(Point coordinate) {
        this.coordinate = coordinate;
    }

    public Point getPosition() {
        return coordinate;
    }

    public List<WorldObject> getWorldObjects() {
        return worldObjects;
    }
}