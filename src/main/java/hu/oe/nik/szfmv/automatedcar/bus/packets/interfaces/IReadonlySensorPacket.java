package hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces;

import hu.oe.nik.szfmv.environment.WorldObject;

import java.util.List;

public interface IReadonlySensorPacket {
    /**
     * Gets all of the objects which were detected by the camera sensor
     *
     * @return {@Link WorldObject} collection of collidable objects
     */
    List<WorldObject> getDetectedCollidableObjects();

    /**
     * Gets all of the objects which were detected by the ultrasound or radar sensor
     *
     * @return {@Link WorldObject} collection of non-collidable objects
     */
    List<WorldObject> getDetectedNonCollidableObjects();
}
