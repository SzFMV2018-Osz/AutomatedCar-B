package hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces;

import hu.oe.nik.szfmv.environment.WorldObject;

import java.util.Optional;

public interface ICameraPacket {

    /**
     * @return closest road sign
     */
    Optional<WorldObject> getClosestRoadSign();

    /**
     * @return closest road sign distance
     */
    double getClosestRoadSignDistance();

    /**
     * @param roadSign closest road sign
     */
    void setClosestRoadSign(Optional<WorldObject> roadSign);

    /**
     * @return left distance from the lane edge
     */
    int getLeftDisctanceFromTheLaneEdge();

    /**
     * @param distance current left distance
     */
    void setLeftDisctanceFromTheLaneEdge(int distance);

    /**
     * @return right distance from the lane edge
     */
    int getRightDistanceFromTheLaneEdge();

    /**
     * @param distance current right distance
     */
    void setRightDistanceFromTheLaneEdge(int distance);

    /**
     * @return number of road lane
     */
    int getLaneNumber();

    /**
     * @param laneNumber number of road lane
     */
    void setLaneNumber(int laneNumber);

    /**
     * @return the car current lane (left to right)
     */
    boolean[] getWhichLane();

    /**
     * @param whichLane the car current lane (left to right)
     */
    void setWhichLane(boolean[] whichLane);

}
