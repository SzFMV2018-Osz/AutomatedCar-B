package hu.oe.nik.szfmv.sensor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hu.oe.nik.szfmv.automatedcar.bus.VirtualFunctionBus;
import hu.oe.nik.szfmv.automatedcar.bus.packets.PositionPacket;
import hu.oe.nik.szfmv.automatedcar.sensor.Camera;

public class CameraTest {

    VirtualFunctionBus virtualFunctionBus;

    @Before
    public void setUp() {
        virtualFunctionBus = new VirtualFunctionBus();
        PositionPacket positionPacket = new PositionPacket();
        virtualFunctionBus.positionPacket = positionPacket;
        positionPacket.setPostion(new double[] { 0, 0 });
        positionPacket.setRotation(0);
    }

    @Test
    public void CanCreateCameraObjectTest() {
        Camera c = new Camera(virtualFunctionBus);

        Assert.assertNotNull(c);
    }
}
