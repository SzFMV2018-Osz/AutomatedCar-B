package hu.oe.nik.szfmv.sensor;

import hu.oe.nik.szfmv.automatedcar.sensor.Triangle;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class TriangleTests {

    Triangle triangle;

    @Before
    public void inicialize() {
        triangle = new Triangle(200 * 50, Math.toRadians(30), 0, 0);
    }

    @Test
    @Parameters({"10000|5773|true", "500000|1000000|false", "100|10|true", "0|0|true", "10000|5774|false"})
    public void isInTheTriangleTest(double p0x, double p0y, boolean in) {
        Assert.assertEquals(in, triangle.isInTheTriangle(p0x, p0y));
    }

    

}
