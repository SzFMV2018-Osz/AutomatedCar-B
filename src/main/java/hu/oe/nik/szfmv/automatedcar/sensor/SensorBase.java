package hu.oe.nik.szfmv.automatedcar.sensor;

import hu.oe.nik.szfmv.common.enums.Coordinate;
import hu.oe.nik.szfmv.environment.WorldObject;

import java.util.List;

public abstract class SensorBase {
    private List<WorldObject> worldObjects;
    private Coordinate coordinate;
    private SensorType sensorType;

    public SensorBase(List<WorldObject> worldObjects, Coordinate coordinate, SensorType sensorType) {
        this.worldObjects = worldObjects;
        this.coordinate = coordinate;
        this.sensorType = sensorType;
    }

    public Coordinate getPosition() {
        return coordinate;
    }

    public void setPosition(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    protected List<WorldObject> getWorldObjectsIn(Coordinate A, Coordinate B, Coordinate C) {
        //TODO: search world objects inside the triangle (note: object borders should be respected)
        return null;
    }
}