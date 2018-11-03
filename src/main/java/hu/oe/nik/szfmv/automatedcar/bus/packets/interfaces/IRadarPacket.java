package hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces;

import hu.oe.nik.szfmv.environment.worldobjectclasses.Collidable;

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
     * @return Right Lane closes object
     */
    Collidable getRightClosesObject();

    /**
     *  @return Left Lane closes object
     */
    Collidable getLeftClosesObject();

    /**
     * @return Actual Road Lane number
     */
    Collidable []  getActualLaneClosesObjects();

}
