package hu.oe.nik.szfmv.automatedcar.sensor;

import hu.oe.nik.szfmv.common.enums.Coordinate;
import hu.oe.nik.szfmv.environment.WorldObject;

import java.awt.*;
import java.util.List;

public class UltraSoundSensor extends SensorBase implements ISensor {
    public UltraSoundSensor(List<WorldObject> worldObjects, Point coordinate) {
        super(worldObjects, coordinate, SensorType.Ultrasound);
    }

    @Override
    public List<WorldObject> getCollidableWorldObjectsFromArea(Point x1, Point x2, Point x3) {
        return null;
    }

    @Override
    public List<WorldObject> getNonCollidableWorldObjectsFromArea(Point x1, Point x2, Point x3) {
        throw new UnsupportedOperationException("UltraSound does not need nonCollidable objects");
    }
}
