package hu.oe.nik.szfmv.automatedcar.bus.packets;

import hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces.IReadonlySensorPacket;
import hu.oe.nik.szfmv.environment.WorldObject;

import java.util.List;

public class SensorPacket implements IReadonlySensorPacket {

    private List<WorldObject> collidable;
    private List<WorldObject> nonCollidable;

    /**
     * Creates a new sensor packet which contains the detected world objects
     *
     * @param collidable    objects detected by the ultrasound or radar sensor
     * @param nonCollidable objects detected by the camera sensor
     */
    public SensorPacket(List<WorldObject> collidable, List<WorldObject> nonCollidable) {
        this.collidable = collidable;
        this.nonCollidable = nonCollidable;
    }

    @Override
    public List<WorldObject> getDetectedCollidableObjects() {
        return collidable;
    }

    @Override
    public List<WorldObject> getDetectedNonCollidableObjects() {
        return nonCollidable;
    }
}
