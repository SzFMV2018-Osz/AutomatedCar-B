package hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces;

import hu.oe.nik.szfmv.environment.worldobjectclasses.RoadSign;

public interface ICameraPacket {

    RoadSign getClosestRoadSign();

    void setClosestRoadSign(RoadSign roadSign);

    int getLeftDisctanceFromTheLaneEdge();

    void setLeftDisctanceFromTheLaneEdge(int distance);

    int getRightDistanceFromTheLaneEdge();

    void setRightDistanceFromTheLaneEdge(int distance);

    int getLaneNumber();

    void setLaneNumber(int laneNumber);

    boolean[] getWhichLane();

    void setWhichLane(boolean[] whichLane);

}
