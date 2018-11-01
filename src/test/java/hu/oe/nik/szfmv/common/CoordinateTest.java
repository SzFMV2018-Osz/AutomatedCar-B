package hu.oe.nik.szfmv.common;

import hu.oe.nik.szfmv.common.enums.Coordinate;
import org.junit.Assert;
import org.junit.Test;

public class CoordinateTest {
    @Test
    public void DistanceCalculationSame() {
        Coordinate c1 = new Coordinate(0, 0);

        double distance = c1.distanceTo(c1);

        Assert.assertEquals(0, distance, 0);
    }

    @Test
    public void DistanceCalculationEquals() {
        Coordinate c1 = new Coordinate(1, 2);
        Coordinate c2 = new Coordinate(1, 2);

        double distance = c1.distanceTo(c2);

        Assert.assertEquals(0, distance, 0);
    }

    @Test
    public void DistanceCalculationDy() {
        Coordinate c1 = new Coordinate(1, 1);
        Coordinate c2 = new Coordinate(1, 6);

        double distance = c1.distanceTo(c2);

        Assert.assertEquals(5, distance, 0);
    }

    @Test
    public void DistanceCalculationDx() {
        Coordinate c1 = new Coordinate(1, 1);
        Coordinate c2 = new Coordinate(6, 1);

        double distance = c1.distanceTo(c2);

        Assert.assertEquals(5, distance, 0);
    }

    @Test
    public void DistanceCalculationSquare() {
        Coordinate c1 = new Coordinate(2, 1);
        Coordinate c2 = new Coordinate(4, 3);

        double distance = c1.distanceTo(c2);

        Assert.assertEquals(2 * Math.sqrt(2), distance, 0);
    }

    @Test
    public void DistanceCalculation() {
        Coordinate c1 = new Coordinate(2, 1);
        Coordinate c2 = new Coordinate(7, 4);

        double distance = c1.distanceTo(c2);

        Assert.assertEquals(Math.sqrt(34), distance, 0);
    }
}
