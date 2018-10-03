package hu.oe.nik.szfmv.powertrain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;

import hu.oe.nik.szfmv.automatedcar.bus.VirtualFunctionBus;
import hu.oe.nik.szfmv.automatedcar.systemcomponents.PowertrainSystem;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class PowertrainSystemUpdateEngineStateTest {

    private PowertrainSystem underTest;

    @Mock
    private VirtualFunctionBus virtualFunctionBus;

    @Before
    public void setUp() throws Exception {
	virtualFunctionBus = Mockito.mock(VirtualFunctionBus.class);
	underTest = new PowertrainSystem(virtualFunctionBus);
    }

    @Test
    @Parameters({ "0|0.33|0", "10|0.33|2257", "20|0.33|4514", "30|0.33|6771", "40|0.33|9028", "50|0.33|11285" })
    public void testUpdateRpmInFirstGear(final double carSpeed, final double wheelRadius, final int expectedRpm) {
	// GIVEN
	// WHEN
	underTest.updateEngine(carSpeed / wheelRadius);
	// THEN
	Assert.assertEquals(expectedRpm, underTest.getRpm());
    }

    @Test
    @Parameters({ "0|0.33|0", "10|0.33|1475", "20|0.33|2951", "30|0.33|4427", "40|0.33|5903", "50|0.33|7379" })
    public void testUpdateRpmInSecondGear(final double carSpeed, final double wheelRadius, final int expectedRpm)
	    throws Exception {
	// GIVEN
	FieldSetter.setField(underTest, underTest.getClass().getDeclaredField("currentGear"), 2);
	// WHEN
	underTest.updateEngine(carSpeed / wheelRadius);
	// THEN
	Assert.assertEquals(expectedRpm, underTest.getRpm());
    }

    @Test
    @Parameters({ "0|0.33|0", "10|0.33|1128", "20|0.33|2257", "30|0.33|3385", "40|0.33|4514", "50|0.33|5642" })
    public void testUpdateRpmInThirdGear(final double carSpeed, final double wheelRadius, final int expectedRpm)
	    throws Exception {
	// GIVEN
	FieldSetter.setField(underTest, underTest.getClass().getDeclaredField("currentGear"), 3);
	// WHEN
	underTest.updateEngine(carSpeed / wheelRadius);
	// THEN
	Assert.assertEquals(expectedRpm, underTest.getRpm());
    }

    @Test
    @Parameters({ "0|0.33|0", "10|0.33|868", "20|0.33|1736", "30|0.33|2604", "40|0.33|3472", "50|0.33|4340" })
    public void testUpdateRpmInFourthGear(final double carSpeed, final double wheelRadius, final int expectedRpm)
	    throws Exception {
	// GIVEN
	FieldSetter.setField(underTest, underTest.getClass().getDeclaredField("currentGear"), 4);
	// WHEN
	underTest.updateEngine(carSpeed / wheelRadius);
	// THEN
	Assert.assertEquals(expectedRpm, underTest.getRpm());
    }

    @Test
    @Parameters({ "0|0.33|0", "10|0.33|607", "20|0.33|1215", "30|0.33|1823", "40|0.33|2430", "50|0.33|3038" })
    public void testUpdateRpmInFifthGear(final double carSpeed, final double wheelRadius, final int expectedRpm)
	    throws Exception {
	// GIVEN
	FieldSetter.setField(underTest, underTest.getClass().getDeclaredField("currentGear"), 5);
	// WHEN
	underTest.updateEngine(carSpeed / wheelRadius);
	// THEN
	Assert.assertEquals(expectedRpm, underTest.getRpm());
    }

    @Test
    @Parameters({ "0|0.33|0", "10|0.33|434", "20|0.33|868", "30|0.33|1302", "40|0.33|1736", "50|0.33|2170" })
    public void testUpdateRpmInSixthGear(final double carSpeed, final double wheelRadius, final int expectedRpm)
	    throws Exception {
	// GIVEN
	FieldSetter.setField(underTest, underTest.getClass().getDeclaredField("currentGear"), 6);
	// WHEN
	underTest.updateEngine(carSpeed / wheelRadius);
	// THEN
	Assert.assertEquals(expectedRpm, underTest.getRpm());
    }

    @Test
    public void testUpdateRpmShouldShiftGearToSecondIfTheRpmIsOverTheLimit() {
	// GIVEN
	// WHEN
	underTest.updateEngine(20 / 0.33);
	// THEN
	Assert.assertEquals(2, underTest.getCurrentGear());
    }

    @Test
    public void testUpdateRpmShouldShiftGearToThirdIfTheRpmIsOverTheLimit() throws Exception {
	// GIVEN
	FieldSetter.setField(underTest, underTest.getClass().getDeclaredField("currentGear"), 2);
	// WHEN
	underTest.updateEngine(30 / 0.33);
	// THEN
	Assert.assertEquals(3, underTest.getCurrentGear());
    }

    @Test
    public void testUpdateRpmShouldShiftGearToFourthIfTheRpmIsOverTheLimit() throws Exception {
	// GIVEN
	FieldSetter.setField(underTest, underTest.getClass().getDeclaredField("currentGear"), 3);
	// WHEN
	underTest.updateEngine(30 / 0.33);
	// THEN
	Assert.assertEquals(4, underTest.getCurrentGear());
    }

    @Test
    public void testUpdateRpmShouldShiftGearToFifthIfTheRpmIsOverTheLimit() throws Exception {
	// GIVEN
	FieldSetter.setField(underTest, underTest.getClass().getDeclaredField("currentGear"), 4);
	// WHEN
	underTest.updateEngine(40 / 0.33);
	// THEN
	Assert.assertEquals(5, underTest.getCurrentGear());
    }

    @Test
    public void testUpdateRpmShouldShiftGearToSixthIfTheRpmIsOverTheLimit() throws Exception {
	// GIVEN
	FieldSetter.setField(underTest, underTest.getClass().getDeclaredField("currentGear"), 5);
	// WHEN
	underTest.updateEngine(50 / 0.33);
	// THEN
	Assert.assertEquals(6, underTest.getCurrentGear());
    }

    @Test
    public void testUpdateRpmShouldShiftBackToFirstWhenTheRpmIsUnderTheLimit() throws Exception {
	// GIVEN
	FieldSetter.setField(underTest, underTest.getClass().getDeclaredField("currentGear"), 2);
	// WHEN
	underTest.updateEngine(10 / 0.33);
	// THEN
	Assert.assertEquals(1, underTest.getCurrentGear());
    }

    @Test
    public void testUpdateRpmShouldShiftBackToSecondWhenTheRpmIsUnderTheLimit() throws Exception {
	// GIVEN
	FieldSetter.setField(underTest, underTest.getClass().getDeclaredField("currentGear"), 3);
	// WHEN
	underTest.updateEngine(10 / 0.33);
	// THEN
	Assert.assertEquals(2, underTest.getCurrentGear());
    }

    @Test
    public void testUpdateRpmShouldShiftBackToThirdWhenTheRpmIsUnderTheLimit() throws Exception {
	// GIVEN
	FieldSetter.setField(underTest, underTest.getClass().getDeclaredField("currentGear"), 4);
	// WHEN
	underTest.updateEngine(10 / 0.33);
	// THEN
	Assert.assertEquals(3, underTest.getCurrentGear());
    }

    @Test
    public void testUpdateRpmShouldShiftBackToFourthWhenTheRpmIsUnderTheLimit() throws Exception {
	// GIVEN
	FieldSetter.setField(underTest, underTest.getClass().getDeclaredField("currentGear"), 5);
	// WHEN
	underTest.updateEngine(10 / 0.33);
	// THEN
	Assert.assertEquals(4, underTest.getCurrentGear());
    }

    @Test
    public void testUpdateRpmShouldShiftBackToFifthWhenTheRpmIsUnderTheLimit() throws Exception {
	// GIVEN
	FieldSetter.setField(underTest, underTest.getClass().getDeclaredField("currentGear"), 6);
	// WHEN
	underTest.updateEngine(10 / 0.33);
	// THEN
	Assert.assertEquals(5, underTest.getCurrentGear());
    }
}
