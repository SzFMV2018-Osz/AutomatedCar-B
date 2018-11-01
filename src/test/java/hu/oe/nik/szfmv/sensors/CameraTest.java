package hu.oe.nik.szfmv.sensors;

import hu.oe.nik.szfmv.automatedcar.sensor.Camera;
import junitparams.JUnitParamsRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class CameraTest {

    @Test
    public void CameraTriangleCalcTest(){
        Camera tmp = new Camera(1, 1, new double[]{1, 0});

    }
}
