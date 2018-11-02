package hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces;

public interface IRadarPacket {
    /**
     * @return number of lanes
     */
    int getLaneNumber();

    /**
     * @param laneNumber Set number of road lanes
     */
    void setLaneNumber(int laneNumber);

    /**
     * @return Right closes Lane
     */
    int getRightClosesRoadLane();

    /**
     * @param laneNumber Set Right closes lane number
     */
    void setRightClosesRoadLane(int laneNumber);

    /**
     * @return Left closes Lane
     */
    int getLeftClosesRoadLane();

    /**
     * @param laneNumber Set Right closes lane number
     */
    void setLeftClosesRoadLane(int laneNumber);

    /**
     * @return Actual Road Lane number
     */
    int getActualtRoadLane();

    /**
     * @param laneNumber Set Actual Lane number
     */
    void setActualtRoadLane(int laneNumber);

    /**
     * @return get two closer lanes
     */
    int[] getTwoCloserLane();

    /**
     * @param actualRoadLane Set Two closer lane number
     */
    void setTwoCloserLane(int [] actualRoadLane);
}
