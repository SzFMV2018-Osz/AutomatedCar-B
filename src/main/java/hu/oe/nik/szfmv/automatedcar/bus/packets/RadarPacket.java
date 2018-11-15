package hu.oe.nik.szfmv.automatedcar.bus.packets;

import hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces.IRadarPacket;
import hu.oe.nik.szfmv.environment.worldobjectclasses.Collidable;

public class RadarPacket implements IRadarPacket {
    private int laneNumber;
    private Collidable rightClosesObject;
    private Collidable leftClosesObject;
    private Collidable []actualLaneClosesObjects;

    /**
     * Constructor
     */
    public RadarPacket() {
        this.laneNumber = 0;
        this.rightClosesObject = null;
        this.leftClosesObject = null;
        this.actualLaneClosesObjects = new Collidable[2];

    }

    /**
     * @return number of lanes
     */
    @Override
    public int getLaneNumber() {
        return laneNumber;
    }

    /**
     * @param laneNumber Set number of road lanes
     */
    @Override
    public void setLaneNumber(int laneNumber) {
        this.laneNumber = laneNumber;
    }

    /**
     * @return Right Lane closes object
     */
    @Override
    public Collidable getRightClosesObject() {
        return rightClosesObject;
    }

    /**
     * @param rightClosesObject set Right Lane closes object
     */
    @Override
    public void setRightClosesObject(Collidable rightClosesObject) {
        this.rightClosesObject = rightClosesObject;
    }

    /**
     * @return Left Lane closes object
     */
    @Override
    public Collidable getLeftClosesObject() {
        return leftClosesObject;
    }

    /**
     * @param leftClosesObject set Left Lane closes object
     */
    @Override
    public void setLeftClosesObject(Collidable leftClosesObject) {
        this.leftClosesObject = leftClosesObject;
    }

    /**
     * @return Actual Road Lane number
     */
    @Override
    public Collidable[] getActualLaneClosesObjects() {
        return actualLaneClosesObjects;
    }

    /**
     * @param actualLaneClosesObjects set center Lane closes object
     */
    @Override
    public void setActualLaneClosesObjects(Collidable[] actualLaneClosesObjects) {
        this.actualLaneClosesObjects = actualLaneClosesObjects;
    }
}
