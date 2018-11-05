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
     * @param rightClosesObject set Right Lane closes object
     */
    void setRightClosesObject(Collidable rightClosesObject);

    /**
     *  @return Left Lane closes object
     */
    Collidable getLeftClosesObject();

    /**
     * @param leftClosesObject set Left Lane closes object
     */
    void setLeftClosesObject(Collidable leftClosesObject);

    /**
     * @return Actual Road Lane number
     */
    Collidable []  getActualLaneClosesObjects();

    /**
     * @param actualLaneClosesObjects set center Lane closes object
     */
    void setActualLaneClosesObjects(Collidable [] actualLaneClosesObjects);

}
