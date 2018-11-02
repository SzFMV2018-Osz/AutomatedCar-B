package hu.oe.nik.szfmv.sensor;

import hu.oe.nik.szfmv.automatedcar.sensor.Camera;
import org.junit.Assert;
import org.junit.Test;

public class CameraTest {
    @Test
    public void CanCreateCameraObjectTest(){
        Camera c = new Camera(1, 1, new double[]{0, 1});

        Assert.assertNotNull(c);
    }
}
