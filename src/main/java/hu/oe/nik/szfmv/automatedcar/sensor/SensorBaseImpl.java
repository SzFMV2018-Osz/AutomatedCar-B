package hu.oe.nik.szfmv.automatedcar.sensor;

import hu.oe.nik.szfmv.environment.WorldObject;

import java.util.List;

public class SensorBaseImpl implements ISensor {

    @Override
    public List<WorldObject> getCollidableWorldObjectsFromArea (int x1, int x2, int x3) {
        return null;
    }

    @Override
    public List<WorldObject> getNonCollidableWorldObjectsFromArea (int x1, int x2, int x3) {
        return null;
    }
}
