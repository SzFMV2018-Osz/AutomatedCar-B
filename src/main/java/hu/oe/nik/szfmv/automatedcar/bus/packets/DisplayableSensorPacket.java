package hu.oe.nik.szfmv.automatedcar.bus.packets;

import hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces.IReadonlyDisplayableSensorPacket;
import hu.oe.nik.szfmv.automatedcar.sensor.SensorType;
import hu.oe.nik.szfmv.common.enums.Coordinate;

import java.util.List;

public class DisplayableSensorPacket implements IReadonlyDisplayableSensorPacket {

    private List<Coordinate> visualField;
    private SensorType type;

    /**
     * Creates a new packet with necessary data to visualize the sensor visual field
     *
     * @param visualField the peaks of the visual field covered by the sensor
     * @param type        the type of the sensor
     */
    public DisplayableSensorPacket(List<Coordinate> visualField, SensorType type) {
        this.visualField = visualField;
        this.type = type;
    }

    @Override
    public List<Coordinate> getVisualField() {
        return visualField;
    }

    @Override
    public SensorType getType() {
        return type;
    }
}
