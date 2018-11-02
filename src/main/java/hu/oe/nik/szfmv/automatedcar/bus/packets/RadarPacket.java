package hu.oe.nik.szfmv.automatedcar.bus.packets;

import hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces.IRadarPacket;

public class RadarPacket implements IRadarPacket {

    private int laneNumber;
    private int rightClosesRoadLane;
    private int leftClosesRoadLane;
    private int actualLane;
    private int [] twoCloserLane;

    /**
     * Set default values to variables
     */
    public RadarPacket() {
        this.laneNumber = 0;
        this.rightClosesRoadLane = 0;
        this.leftClosesRoadLane = 0;
        this.actualLane = 0;
        this.twoCloserLane = null;
    }
    @Override
    public int getLaneNumber() {
        return laneNumber;
    }

    @Override
    public void setLaneNumber(int laneNumber) {
        this.laneNumber = laneNumber; }

    @Override
    public int getRightClosesRoadLane() {
        return rightClosesRoadLane;
    }

    @Override
    public void setRightClosesRoadLane(int laneNumber) {
        this.rightClosesRoadLane = laneNumber; }

    @Override
    public int getLeftClosesRoadLane() {
        return leftClosesRoadLane;
    }

    @Override
    public void setLeftClosesRoadLane(int laneNumber) {
        this.leftClosesRoadLane = laneNumber; }

    @Override
    public int getActualtRoadLane() {
        return actualLane;
    }

    @Override
    public void setActualtRoadLane(int laneNumber) {
        this.actualLane = laneNumber; }

    @Override
    public int[] getTwoCloserLane() {
        return twoCloserLane;
    }

    @Override
    public void setTwoCloserLane(int[] raodLanes) {
        this.twoCloserLane = raodLanes; }
}
