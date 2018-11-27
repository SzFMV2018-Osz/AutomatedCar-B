package hu.oe.nik.szfmv.physics;

import hu.oe.nik.szfmv.automatedcar.engine.TractionForce;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class TractionForceTest {

    @Test
    @Parameters({"1|0|2576|2575.75", "0|1|2575.75|2576"})
    public void testCalculateTractionForceShouldCalculateTractionForceForDifferentOrientationVectors(
            final double orientationX, final double orentationY, final double expectedX, final double expectedY) {
        // GIVEN
        final double drivingTorque = 850;
        // WHEN
        final double[] tractionForce = TractionForce.calculateTractionForce(
                drivingTorque, 0.33);
        // THEN
        Assert.assertEquals(expectedX, tractionForce[0], 0.5);
        Assert.assertEquals(expectedY, tractionForce[1], 0.5);
    }

    @Test
    @Parameters({"500|1515.15|1515", "650|1969.69|1970", "800|2424.24|2424", "1100|3333.3333|3333"})
    public void testCalculateTractionForceShouldCalculateTractionForceForDifferentDrivingTorques(
            final double drivingTorque, final double expectedX, final double expectedY) {
        // GIVEN
        final double[] orientationVector = new double[]{0, 1};
        // WHEN
        final double[] tractionForce = TractionForce.calculateTractionForce(drivingTorque, 0.33);
        // THEN
        Assert.assertEquals(expectedX, tractionForce[0], 0.5);
        Assert.assertEquals(expectedY, tractionForce[1], 0.5);
    }
}
