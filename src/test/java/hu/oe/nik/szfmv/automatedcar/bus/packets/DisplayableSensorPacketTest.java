package hu.oe.nik.szfmv.automatedcar.bus.packets;

import hu.oe.nik.szfmv.automatedcar.sensor.SensorType;
import hu.oe.nik.szfmv.common.enums.Coordinate;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DisplayableSensorPacketTest {
    @Test
    public void InitTest() {
        DisplayableSensorPacket dsp = new DisplayableSensorPacket(new ArrayList<>(), SensorType.Radar);

        Assert.assertNotNull(dsp);
    }

    @Test
    public void ReadTest() {
        List<Coordinate> coordinates = new ArrayList<>();
        coordinates.add(new Coordinate(0, 0));
        coordinates.add(new Coordinate(1, 1));
        coordinates.add(new Coordinate(2, 2));
        DisplayableSensorPacket dsp = new DisplayableSensorPacket(coordinates, SensorType.Radar);

        List<Coordinate> visualField = dsp.getVisualField();

        Assert.assertEquals(coordinates.get(0).getX(), visualField.get(0).getX());
        Assert.assertEquals(coordinates.get(0).getY(), visualField.get(0).getY());
        Assert.assertEquals(coordinates.get(1).getX(), visualField.get(1).getX());
        Assert.assertEquals(coordinates.get(1).getY(), visualField.get(1).getY());
        Assert.assertEquals(coordinates.get(2).getX(), visualField.get(2).getX());
        Assert.assertEquals(coordinates.get(2).getY(), visualField.get(2).getY());
    }

    @Test
    public void TypeTest() {
        List<Coordinate> coordinates = new ArrayList<>();
        DisplayableSensorPacket dsp = new DisplayableSensorPacket(coordinates, SensorType.Radar);

        SensorType st = dsp.getType();

        Assert.assertEquals(SensorType.Radar, st);
    }
}
