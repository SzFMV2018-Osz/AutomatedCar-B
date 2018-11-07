package hu.oe.nik.szfmv.physics;

import hu.oe.nik.szfmv.automatedcar.engine.BrakingForces;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class AirResistanceTest {

    @Test
    public void NoAirResistanceWhenNotMoving()
    {
        // GIVEN
        double[] expectedAirResist = {0.0, 0.0};
        double[] airResist;
        // WHEN
        airResist = BrakingForces.calcAirResistanceVector(0.0, 0.0);
        // THEN
        Assert.assertEquals(expectedAirResist[0], airResist[0], 0.0);
        Assert.assertEquals(expectedAirResist[1], airResist[1], 0.0);
    }

    @Test
    @Parameters({"1.0|0.0", "1.0|1.0", "0.0|1.0", "-1.0|1.0",
            "-1.0, 0.0", "-1.0, -1.0", "0.0| -1.0", "1.0, -1.0"})
    public void AirResistanceVectorDirectionIsAlwaysOppositeToVelocity(
            final double vx, final double vy)
    {
        // GIVEN
        // WHEN
        double[] airResist = BrakingForces.calcAirResistanceVector(vx, vy);

        double dot = airResist[0] * vx + airResist[1] * vy;
        double airResLen = Math.sqrt(airResist[0] * airResist[0] + airResist[1] * airResist[1]);
        double veloLen = Math.sqrt(vx * vx + vy * vy);

        double cosAngle = dot / ( airResLen * veloLen );

        double angle = Math.toDegrees(Math.acos(cosAngle));

        // THEN
        Assert.assertEquals(180.0, angle, 1.0);
    }

    @Test
    public void AirResistanceGrowsAsSpeedGrows(){
        // GIVEN
        double vx = 0.0;    // x of velocity
        double arx = 0.0;   // x of air resistance

        for(int i = 0; i < 100; ++i) {
            // WHEN
            vx += 1;

            double[] res = BrakingForces.calcAirResistanceVector(vx, 0.0);

            // THEN
            Assert.assertTrue(arx > res[0]);

            arx = res[0];
        }
    }
}
