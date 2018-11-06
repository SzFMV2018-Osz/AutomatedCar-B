package hu.oe.nik.szfmv.sensor;

import org.junit.Assert;
import org.junit.Test;

import hu.oe.nik.szfmv.automatedcar.bus.VirtualFunctionBus;
import hu.oe.nik.szfmv.automatedcar.sensor.Camera;

public class CameraTest {
    @Test
    public void CanCreateCameraObjectTest() {
        Camera c = new Camera(1, 1, new double[] { 0, 1 }, new VirtualFunctionBus());

        Assert.assertNotNull(c);
    }
}
