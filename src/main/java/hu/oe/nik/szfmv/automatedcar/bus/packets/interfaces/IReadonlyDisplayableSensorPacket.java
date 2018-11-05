package hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces;

import hu.oe.nik.szfmv.automatedcar.sensor.SensorType;
import hu.oe.nik.szfmv.common.enums.Coordinate;

import java.util.List;

public interface IReadonlyDisplayableSensorPacket {
    /**
     * Gets the visual field which is covered by the sensor
     *
     * @return the peaks of the visual field
     */
    List<Coordinate> getVisualField();

    /**
     * Gets the type of the sensor
     *
     * @return the tpye of the sensor
     */
    SensorType getType();
}
