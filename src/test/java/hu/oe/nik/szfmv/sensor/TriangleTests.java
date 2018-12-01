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
        //   triangle = new Triangle(200 * 50, Math.toRadians(30), 0, 0);
    }

    @Test
    @Parameters({"10000|5773|true", "500000|1000000|false", "100|10|true", "0|0|true", "10000|5774|false"})
    public void isInTheTriangleTest(double p0x, double p0y, boolean in) {
        Assert.assertEquals(in, triangle.isInTheTriangle(p0x, p0y));
    }

    @Test
    @Parameters({"0|0|100|-7421.777|8845.607|3949.306|10850.635", "0|0|270|5773|-10000|-5773.5|-10000", "100|100|270|5873|-9900|-5673.5|-9900"})
    public void calculateNextPositionTest(double p0x, double p0y, double rotation, double p1x, double p1y, double p2x, double p2y) {
        //  triangle.calculateNextPosition(Math.toRadians(rotation), p0x, p0y);
        Assert.assertEquals(triangle.getA1x(), p1x, 1);
        Assert.assertEquals(triangle.getA1y(), p1y, 1);
        Assert.assertEquals(triangle.getA2x(), p2x, 1);
        Assert.assertEquals(triangle.getA2y(), p2y, 1);

    }


}
