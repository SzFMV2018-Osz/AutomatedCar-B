package hu.oe.nik.szfmv.automatedcar.bus.packets;

import hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces.ICameraPacket;
import hu.oe.nik.szfmv.environment.WorldObject;

import java.util.Optional;

public class CameraPacket implements ICameraPacket {

    Optional<WorldObject> closestRoadSign;
    double closestRoadSignDistance;
    int leftDisctanceFromTheLaneEdge;
    int rightDistanceFromTheLaneEdge;
    int laneNumber;
    boolean[] whichLane;

    /**
     * add default value to variable
     */
    public CameraPacket() {
        this.closestRoadSign = null;
        this.leftDisctanceFromTheLaneEdge = 0;
        this.rightDistanceFromTheLaneEdge = 0;
        this.laneNumber = 0;
        this.whichLane = new boolean[laneNumber];
    }

    @Override
    public Optional<WorldObject> getClosestRoadSign() {
        return closestRoadSign;
    }

    @Override
    public void setClosestRoadSign(Optional<WorldObject> closestRoadSign) {
        this.closestRoadSign = closestRoadSign;
    }

    @Override
    public int getLeftDisctanceFromTheLaneEdge() {
        return leftDisctanceFromTheLaneEdge;
    }

    @Override
    public void setLeftDisctanceFromTheLaneEdge(int leftDisctanceFromTheLaneEdge) {
        this.leftDisctanceFromTheLaneEdge = leftDisctanceFromTheLaneEdge;
    }

    @Override
    public int getRightDistanceFromTheLaneEdge() {
        return rightDistanceFromTheLaneEdge;
    }

    @Override
    public void setRightDistanceFromTheLaneEdge(int rightDistanceFromTheLaneEdge) {
        this.rightDistanceFromTheLaneEdge = rightDistanceFromTheLaneEdge;
    }

    @Override
    public int getLaneNumber() {
        return laneNumber;
    }

    @Override
    public void setLaneNumber(int laneNumber) {
        this.laneNumber = laneNumber;
    }

    @Override
    public boolean[] getWhichLane() {
        return whichLane;
    }

    @Override
    public void setWhichLane(boolean[] whichLane) {
        this.whichLane = whichLane;
    }

    @Override
    public double getClosestRoadSignDistance() {
        return closestRoadSignDistance;
    }

    public void setClosestRoadSignDistance(double closestRoadSignDistance) {
        this.closestRoadSignDistance = closestRoadSignDistance;
    }
}
