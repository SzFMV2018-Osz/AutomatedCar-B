package hu.oe.nik.szfmv.engine;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import hu.oe.nik.szfmv.automatedcar.engine.CarEngine;
import hu.oe.nik.szfmv.automatedcar.engine.StandardCarEngineType;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class CalculateVelocityTest {

    private static final double[] ORIENTATION_MOCK = new double[] { 1, 0 };
    private static final int THROTTLE_MOCK = 100;
    private static final int BREAK_MOCK = 0;
    private static final double SPEED_MOCK = 30.0;
    private static final int GEAR_MOCK = 1;
    private static final double TIME_MOCK = 0.42;

    private CarEngine underTest;

    @Before
    public void setUp() {
        underTest = new CarEngine(new StandardCarEngineType());
    }

    @Test
    @Parameters({ "1|34", "2|39", "3|43" })
    public void testCalculateVelocityWithDifferentDeltaTime(double deltaTime, double expected) {
        // GIVEN
        // WHEN
        double actualSpeed = underTest.calculationVelocity(deltaTime, ORIENTATION_MOCK, GEAR_MOCK, SPEED_MOCK,
                BREAK_MOCK, THROTTLE_MOCK);
        // THEN
        Assert.assertEquals(expected, actualSpeed, 0.5);
    }

    @Test
    @Parameters({ "1|0|32", "0|1|32", "0|-1|32", "-1|0|32" })
    public void testCalculateVelocityWithDifferentOrientationVectors(int ox, int oy, double expected) {
        // GIVEN
        // WHEN
        double actualSpeed = underTest.calculationVelocity(TIME_MOCK, new double[] { ox, oy }, GEAR_MOCK, SPEED_MOCK,
                BREAK_MOCK, THROTTLE_MOCK);
        // THEN
        Assert.assertEquals(expected, actualSpeed, 0.5);
    }

    @Test
    @Parameters({ "1|32", "2|31", "3|31", "4|31", "5|30", "6|30" })
    public void testCalculateVelocityWithDifferentGears(int currentGear, double expected) {
        // GIVEN
        // WHEN
        double actualSpeed = underTest.calculationVelocity(TIME_MOCK, ORIENTATION_MOCK, currentGear, SPEED_MOCK,
                BREAK_MOCK, THROTTLE_MOCK);
        // THEN
        Assert.assertEquals(expected, actualSpeed, 0.5);
    }

    @Test
    @Parameters({ "0|2", "10|12", "20|22", "30|32", "40|42", "50|52", "60|62", "70|72" })
    public void testCalculateVelocityWithDifferentActualSpeeds(double actualSpeed, double expected) {
        // GIVEN
        // WHEN
        double actualSpeedResult = underTest.calculationVelocity(TIME_MOCK, ORIENTATION_MOCK, GEAR_MOCK, actualSpeed,
                BREAK_MOCK, THROTTLE_MOCK);
        // orientationVector, gear, actualSpeed, breakPedal, throttlePosition)
        // THEN
        Assert.assertEquals(expected, actualSpeedResult, 0.5);
    }

    @Test
    @Parameters({ "0|30", "15|31", "50|34", "100|39" })
    public void testCalculateVelocityWithDifferentThrottleValues(int throttlePosition, double expected) {
        // GIVEN
        // WHEN
        double actualSpeed = underTest.calculationVelocity(2, ORIENTATION_MOCK, GEAR_MOCK, SPEED_MOCK, BREAK_MOCK,
                throttlePosition);
        // THEN
        Assert.assertEquals(expected, actualSpeed, 0.5);
    }
}
