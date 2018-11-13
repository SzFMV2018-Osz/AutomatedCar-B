package hu.oe.nik.szfmv.automatedcar.sensor;

import hu.oe.nik.szfmv.common.enums.Coordinate;
import hu.oe.nik.szfmv.environment.World;
import hu.oe.nik.szfmv.environment.WorldObject;

import java.awt.*;
import java.util.List;

public abstract class SensorBase {
    private List<WorldObject> worldObjects;
    private Point coordinate;
    private SensorType sensorType;

    public SensorBase(List<WorldObject> worldObjects, Point coordinate, SensorType sensorType) {
        this.worldObjects = worldObjects;
        this.coordinate = coordinate;
        this.sensorType = sensorType;
    }

    public void setPosition(Point coordinate){
        this.coordinate = coordinate;
    }

    public Point getPosition() {
        return coordinate;
    }

    public List<WorldObject> getWorldObjects(){ return worldObjects; }
}