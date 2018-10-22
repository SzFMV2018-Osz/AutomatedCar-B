package hu.oe.nik.szfmv.physics;

import hu.oe.nik.szfmv.automatedcar.engine.BrakingForces;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class BrakeForceTest {
    @Test
    @Parameters({"1.0|0.0", "1.0|1.0", "0.0|1.0", "-1.0|1.0",
            "-1.0, 0.0", "-1.0, -1.0", "0.0| -1.0", "1.0, -1.0"})
    public void BrakeForceIsOppositeToHeading(final double vx, final double vy) {
        //GIVEN
        //WHEN

        double[] brakeForce = BrakingForces.calcBrakeForceVector(vx, vy, 1);

        double dot = brakeForce[0] * vx + brakeForce[1] * vy;
        double breakLen = Math.sqrt(brakeForce[0] * brakeForce[0] + brakeForce[1] * brakeForce[1]);
        double veloLen = Math.sqrt(vx * vx + vy * vy);

        double cosAngle = dot / (breakLen * veloLen);

        double angle = Math.toDegrees(Math.acos(cosAngle));

        //THEN
        Assert.assertEquals(180.0, angle, 1.0);
    }

    @Test
    public void NoBreakWhenNotMoving() {
        //GIVEN
        //WHEN
        double[] brakeForce = BrakingForces.calcBrakeForceVector(0.0, 0.0, 10);

        double breakLen = Math.sqrt(brakeForce[0] * brakeForce[0] + brakeForce[1] * brakeForce[1]);
        //THEN

        Assert.assertEquals(0.0, breakLen, 0.0);
    }

    @Test
    @Parameters({"0.1|0.1|0|0|0", "0.2|0.2|100|-1000|-1000"})
    public void BreakLengthEqualsVelocityLengthWhenLonger(final double vx, final double vy, final int pedal, final double resultvx, final double resultvy) {
        //GIVEN
        //WHEN
        double[] brakeForce = BrakingForces.calcBrakeForceVector(vx, vy, pedal);
        //THEN

        Assert.assertEquals(brakeForce[0], resultvx, 0.01);
        Assert.assertEquals(brakeForce[1], resultvy, 0.01);
    }

    @Test
    @Parameters({"0.2|0.1|60|30", "0.256|0.143|76.8|42.9"})
    public void RollingResistanceTest(final double vx, final double vy, final double resultvx, final double resultvy) {
        //GIVEN
        double[] rResistance_tst = BrakingForces.calcRollingResistanceVector(vx, vy);

        //WHEN
        //THEN
        Assert.assertEquals(rResistance_tst[0], -resultvx, 0.2);
        Assert.assertEquals(rResistance_tst[1], -resultvy, 0.2);

    }

}
