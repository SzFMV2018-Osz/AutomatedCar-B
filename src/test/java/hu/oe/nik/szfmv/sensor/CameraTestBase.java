package hu.oe.nik.szfmv.sensor;

import hu.oe.nik.szfmv.automatedcar.bus.VirtualFunctionBus;
import hu.oe.nik.szfmv.automatedcar.bus.packets.PositionPacket;
import hu.oe.nik.szfmv.automatedcar.sensor.Camera;
import org.junit.Before;

public abstract class CameraTestBase {

    protected Camera underTest;

    @Before
    public void setUp() {
        VirtualFunctionBus virtualFunctionBus = new VirtualFunctionBus();
        PositionPacket positionPacket = new PositionPacket();
        virtualFunctionBus.positionPacket = positionPacket;
        positionPacket.setPostion(new double[] { 0, 0 });
        positionPacket.setRotation(0);
        underTest = new Camera(virtualFunctionBus);
    }
}
