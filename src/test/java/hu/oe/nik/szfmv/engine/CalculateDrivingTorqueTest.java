package hu.oe.nik.szfmv.engine;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.util.reflection.FieldSetter;

import hu.oe.nik.szfmv.automatedcar.engine.CarEngine;
import hu.oe.nik.szfmv.automatedcar.engine.CarEngineType;
import hu.oe.nik.szfmv.automatedcar.engine.StandardCarEngineType;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class CalculateDrivingTorqueTest {

    private CarEngine underTest;
    private CarEngineType engineType;

    @Before
    public void setUp() {
        engineType = new StandardCarEngineType();
        underTest = new CarEngine(engineType);
    }

    @Test
    @Parameters({ "1111|2208", "2222|2427", "3333|2548", "4444|2597", "5555|2354" })
    public void testCalculateDrivingTorqueShouldCalculateDrivingTorqueInFirstGearWithFullGas(final int rpm,
            final double expected) throws Exception {
        // GIVEN
        FieldSetter.setField(underTest, underTest.getClass().getDeclaredField("rpm"), rpm);
        // WHEN
        final double drivingTorque = underTest.calculateDriveTorque(100, 1);
        // THEN
        Assert.assertEquals(expected, drivingTorque, 0.5);
    }

    @Test
    @Parameters({ "1111|1104", "2222|1213", "3333|1274", "4444|1298", "5555|1177" })
    public void testCalculateDrivingTorqueShouldCalculateDrivingTorqueInFirstGearWithHalfGas(final int rpm,
            final double expected) throws Exception {
        // GIVEN
        FieldSetter.setField(underTest, underTest.getClass().getDeclaredField("rpm"), rpm);
        // WHEN
        final double drivingTorque = underTest.calculateDriveTorque(50, 1);
        // THEN
        Assert.assertEquals(expected, drivingTorque, 0.5);
    }

    @Test
    @Parameters({ "1111|1444", "2222|1587", "3333|1666", "4444|1698", "5555|1539" })
    public void testCalculateDrivingTorqueShouldCalculateDrivingTorqueInSecondGearWithFullGas(final int rpm,
            final double expected) throws Exception {
        // GIVEN
        FieldSetter.setField(underTest, underTest.getClass().getDeclaredField("rpm"), rpm);
        // WHEN
        final double drivingTorque = underTest.calculateDriveTorque(100, 2);
        // THEN
        Assert.assertEquals(expected, drivingTorque, 0.5);
    }

    @Test
    @Parameters({ "1111|425|6", "2222|653|5", "3333|980|4", "4444|1298|3" })
    public void testCalculateDrivingTorqueShouldCalculateDrivingTorqueInHigherGearWithFullGas(final int rpm,
            final double expected, final int gear) throws Exception {
        // GIVEN
        FieldSetter.setField(underTest, underTest.getClass().getDeclaredField("rpm"), rpm);
        // WHEN
        final double drivingTorque = underTest.calculateDriveTorque(100, gear);
        // THEN
        Assert.assertEquals(expected, drivingTorque, 0.5);
    }

    @Test
    @Parameters({ "1|1228", "2|803", "3|614", "4|472", "5|331", "6|236" })
    public void testCalculateDrivingWithSameRpmInDifferentGears(final int gear, final double expected)
            throws Exception {
        // GIVEN
        FieldSetter.setField(underTest, underTest.getClass().getDeclaredField("rpm"), 2500);
        // WHEN
        final double drivingTorque = underTest.calculateDriveTorque(50, gear);
        // THEN
        Assert.assertEquals(expected, drivingTorque, 0.5);
    }
}
