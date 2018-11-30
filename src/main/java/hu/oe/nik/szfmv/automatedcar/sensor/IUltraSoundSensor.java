package hu.oe.nik.szfmv.automatedcar.sensor;

import hu.oe.nik.szfmv.environment.WorldObject;

import java.util.List;

public interface IUltraSoundSensor {
    List<WorldObject> getCollidableWorldObjects();
}
