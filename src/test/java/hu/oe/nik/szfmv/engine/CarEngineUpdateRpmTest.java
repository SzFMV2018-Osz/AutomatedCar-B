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
public class CarEngineUpdateRpmTest {

    private CarEngine underTest;

    @Before
    public void setUp() {
	underTest = new CarEngine(new StandardCarEngineType());
    }

    @Test
    @Parameters({ "0|0.33|0", "10|0.33|2257", "20|0.33|4514", "30|0.33|6771", "40|0.33|9028", "50|0.33|11285" })
    public void testUpdateRpmInFirstGear(final double carSpeed, final double wheelRadius, final int expectedRpm) {
	// GIVEN
	final double wheelRotationRatio = carSpeed / wheelRadius;
	// WHEN
	underTest.updateRpm(wheelRotationRatio, 1);
	// THEN
	Assert.assertEquals(expectedRpm, underTest.getRpm());
    }

    @Test
    @Parameters({ "0|0.33|0", "10|0.33|1475", "20|0.33|2951", "30|0.33|4427", "40|0.33|5903", "50|0.33|7379" })
    public void testUpdateRpmInSecondGear(final double carSpeed, final double wheelRadius, final int expectedRpm)
	    throws Exception {
	// GIVEN
	final double wheelRotationRatio = carSpeed / wheelRadius;
	// WHEN
	underTest.updateRpm(wheelRotationRatio, 2);
	// THEN
	Assert.assertEquals(expectedRpm, underTest.getRpm());
    }

    @Test
    @Parameters({ "0|0.33|0", "10|0.33|1128", "20|0.33|2257", "30|0.33|3385", "40|0.33|4514", "50|0.33|5642" })
    public void testUpdateRpmInThirdGear(final double carSpeed, final double wheelRadius, final int expectedRpm)
	    throws Exception {
	// GIVEN
	final double wheelRotationRatio = carSpeed / wheelRadius;
	// WHEN
	underTest.updateRpm(wheelRotationRatio, 3);
	// THEN
	Assert.assertEquals(expectedRpm, underTest.getRpm());
    }

    @Test
    @Parameters({ "0|0.33|0", "10|0.33|868", "20|0.33|1736", "30|0.33|2604", "40|0.33|3472", "50|0.33|4340" })
    public void testUpdateRpmInFourthGear(final double carSpeed, final double wheelRadius, final int expectedRpm)
	    throws Exception {
	// GIVEN
	final double wheelRotationRatio = carSpeed / wheelRadius;
	// WHEN
	underTest.updateRpm(wheelRotationRatio, 4);
	// THEN
	Assert.assertEquals(expectedRpm, underTest.getRpm());
    }

    @Test
    @Parameters({ "0|0.33|0", "10|0.33|607", "20|0.33|1215", "30|0.33|1823", "40|0.33|2430", "50|0.33|3038" })
    public void testUpdateRpmInFifthGear(final double carSpeed, final double wheelRadius, final int expectedRpm)
	    throws Exception {
	// GIVEN
	final double wheelRotationRatio = carSpeed / wheelRadius;
	// WHEN
	underTest.updateRpm(wheelRotationRatio, 5);
	// THEN
	Assert.assertEquals(expectedRpm, underTest.getRpm());
    }

    @Test
    @Parameters({ "0|0.33|0", "10|0.33|434", "20|0.33|868", "30|0.33|1302", "40|0.33|1736", "50|0.33|2170" })
    public void testUpdateRpmInSixthGear(final double carSpeed, final double wheelRadius, final int expectedRpm)
	    throws Exception {
	// GIVEN
	final double wheelRotationRatio = carSpeed / wheelRadius;
	// WHEN
	underTest.updateRpm(wheelRotationRatio, 6);
	// THEN
	Assert.assertEquals(expectedRpm, underTest.getRpm());
    }

}
