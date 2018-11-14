package hu.oe.nik.szfmv.physics;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import hu.oe.nik.szfmv.automatedcar.engine.TractionForce;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class TractionForceTest {

    @Test
    @Parameters({ "1|0|2576|0", "0|1|0|2576" })
    public void testCalculateTractionForceShouldCalculateTractionForceForDifferentOrientationVectors(
            final double orientationX, final double orentationY, final double expectedX, final double expectedY) {
        // GIVEN
        final double drivingTorque = 850;
        // WHEN
        final double[] tractionForce = TractionForce.calculateTractionForce(new double[] { orientationX, orentationY },
                drivingTorque, 0.33);
        // THEN
        Assert.assertEquals(expectedX, tractionForce[0], 0.5);
        Assert.assertEquals(expectedY, tractionForce[1], 0.5);
    }

    @Test
    @Parameters({ "500|0|1515", "650|0|1970", "800|0|2424", "1100|0|3333" })
    public void testCalculateTractionForceShouldCalculateTractionForceForDifferentDrivingTorques(
            final double drivingTorque, final double expectedX, final double expectedY) {
        // GIVEN
        final double[] orientationVector = new double[] { 0, 1 };
        // WHEN
        final double[] tractionForce = TractionForce.calculateTractionForce(orientationVector, drivingTorque, 0.33);
        // THEN
        Assert.assertEquals(expectedX, tractionForce[0], 0.5);
        Assert.assertEquals(expectedY, tractionForce[1], 0.5);
    }
}
