package hu.oe.nik.szfmv.engine;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.util.reflection.FieldSetter;

import hu.oe.nik.szfmv.automatedcar.engine.GearBox;
import hu.oe.nik.szfmv.automatedcar.engine.StandardCarEngineType;

public class GearBoxTest {

    private static final int GEARSHIFT_RPM = 3001;
    private static final int NON_GEARSHIFT_RPM = 3000;
    private static final int DOWNSHIFT_RPM = 1499;
    private static final int NON_DOWNSHIFT_RPM = 1500;

    private GearBox underTest;

    @Before
    public void setUp() {
	underTest = new GearBox(new StandardCarEngineType());
    }

    @Test
    public void testUpdateGearShouldShiftGearToSecondIfTheRpmIsOverTheLimit() {
	// GIVEN
	// WHEN
	underTest.updateGear(GEARSHIFT_RPM);
	// THEN
	Assert.assertEquals(2, underTest.getCurrentGear());
    }

    @Test
    public void testUpdateGearShouldNotShiftGearToSecondIfTheRpmIsUnderTheLimit() {
	// GIVEN
	// WHEN
	underTest.updateGear(NON_GEARSHIFT_RPM);
	// THEN
	Assert.assertEquals(1, underTest.getCurrentGear());
    }

    @Test
    public void testUpdateGearShouldShiftGearToThirdIfTheRpmIsOverTheLimit() throws Exception {
	// GIVEN
	FieldSetter.setField(underTest, underTest.getClass().getDeclaredField("currentGear"), 2);
	// WHEN
	underTest.updateGear(GEARSHIFT_RPM);
	// THEN
	Assert.assertEquals(3, underTest.getCurrentGear());
    }

    @Test
    public void testUpdateGearShouldNotShiftGearToThirdIfTheRpmIsUnderTheLimit() throws Exception {
	// GIVEN
	FieldSetter.setField(underTest, underTest.getClass().getDeclaredField("currentGear"), 2);
	// WHEN
	underTest.updateGear(NON_GEARSHIFT_RPM);
	// THEN
	Assert.assertEquals(2, underTest.getCurrentGear());
    }

    @Test
    public void testUpdateGearShouldShiftGearToFourthIfTheRpmIsOverTheLimit() throws Exception {
	// GIVEN
	FieldSetter.setField(underTest, underTest.getClass().getDeclaredField("currentGear"), 3);
	// WHEN
	underTest.updateGear(GEARSHIFT_RPM);
	// THEN
	Assert.assertEquals(4, underTest.getCurrentGear());
    }

    @Test
    public void testUpdateGearShouldNotShiftGearToFourthIfTheRpmIsUnderTheLimit() throws Exception {
	// GIVEN
	FieldSetter.setField(underTest, underTest.getClass().getDeclaredField("currentGear"), 3);
	// WHEN
	underTest.updateGear(NON_GEARSHIFT_RPM);
	// THEN
	Assert.assertEquals(3, underTest.getCurrentGear());
    }

    @Test
    public void testUpdateGearShouldShiftGearToFifthIfTheRpmIsOverTheLimit() throws Exception {
	// GIVEN
	FieldSetter.setField(underTest, underTest.getClass().getDeclaredField("currentGear"), 4);
	// WHEN
	underTest.updateGear(GEARSHIFT_RPM);
	// THEN
	Assert.assertEquals(5, underTest.getCurrentGear());
    }

    @Test
    public void testUpdateGearShouldNotShiftGearToFifthIfTheRpmIsUnderTheLimit() throws Exception {
	// GIVEN
	FieldSetter.setField(underTest, underTest.getClass().getDeclaredField("currentGear"), 4);
	// WHEN
	underTest.updateGear(NON_GEARSHIFT_RPM);
	// THEN
	Assert.assertEquals(4, underTest.getCurrentGear());
    }

    @Test
    public void testUpdateGearShouldShiftGearToSixthIfTheRpmIsOverTheLimit() throws Exception {
	// GIVEN
	FieldSetter.setField(underTest, underTest.getClass().getDeclaredField("currentGear"), 5);
	// WHEN
	underTest.updateGear(GEARSHIFT_RPM);
	// THEN
	Assert.assertEquals(6, underTest.getCurrentGear());
    }

    @Test
    public void testUpdateGearShouldNotShiftGearToSixthIfTheRpmIsUnderTheLimit() throws Exception {
	// GIVEN
	FieldSetter.setField(underTest, underTest.getClass().getDeclaredField("currentGear"), 5);
	// WHEN
	underTest.updateGear(NON_GEARSHIFT_RPM);
	// THEN
	Assert.assertEquals(5, underTest.getCurrentGear());
    }

    @Test
    public void testUpdateGearShouldNotShiftGearIfTheRpmIsOverTheLimitButGearIsAlreadySix() throws Exception {
	// GIVEN
	FieldSetter.setField(underTest, underTest.getClass().getDeclaredField("currentGear"), 6);
	// WHEN
	underTest.updateGear(GEARSHIFT_RPM);
	// THEN
	Assert.assertEquals(6, underTest.getCurrentGear());
    }

    @Test
    public void testUpdateGearShouldShiftBackToFirstWhenTheRpmIsUnderTheLimit() throws Exception {
	// GIVEN
	FieldSetter.setField(underTest, underTest.getClass().getDeclaredField("currentGear"), 2);
	// WHEN
	underTest.updateGear(DOWNSHIFT_RPM);
	// THEN
	Assert.assertEquals(1, underTest.getCurrentGear());
    }

    @Test
    public void testUpdateGearShouldNotShiftBackWhenTheRpmIsUnderTheLimitButGearIsAlreadyOne() throws Exception {
	// GIVEN
	// WHEN
	underTest.updateGear(DOWNSHIFT_RPM);
	// THEN
	Assert.assertEquals(1, underTest.getCurrentGear());
    }

    @Test
    public void testUpdateGearShouldNotShiftBackToFirstWhenTheRpmIsOverTheLimit() throws Exception {
	// GIVEN
	FieldSetter.setField(underTest, underTest.getClass().getDeclaredField("currentGear"), 2);
	// WHEN
	underTest.updateGear(NON_DOWNSHIFT_RPM);
	// THEN
	Assert.assertEquals(2, underTest.getCurrentGear());
    }

    @Test
    public void testUpdateGearShouldShiftBackToSecondWhenTheRpmIsUnderTheLimit() throws Exception {
	// GIVEN
	FieldSetter.setField(underTest, underTest.getClass().getDeclaredField("currentGear"), 3);
	// WHEN
	underTest.updateGear(DOWNSHIFT_RPM);
	// THEN
	Assert.assertEquals(2, underTest.getCurrentGear());
    }

    @Test
    public void testUpdateGearShouldNotShiftBackToSecondWhenTheRpmIsOverTheLimit() throws Exception {
	// GIVEN
	FieldSetter.setField(underTest, underTest.getClass().getDeclaredField("currentGear"), 3);
	// WHEN
	underTest.updateGear(NON_DOWNSHIFT_RPM);
	// THEN
	Assert.assertEquals(3, underTest.getCurrentGear());
    }

    @Test
    public void testUpdateGearShouldShiftBackToThirdWhenTheRpmIsUnderTheLimit() throws Exception {
	// GIVEN
	FieldSetter.setField(underTest, underTest.getClass().getDeclaredField("currentGear"), 4);
	// WHEN
	underTest.updateGear(DOWNSHIFT_RPM);
	// THEN
	Assert.assertEquals(3, underTest.getCurrentGear());
    }

    @Test
    public void testUpdateGearShouldNotShiftBackToThirdWhenTheRpmIsOverTheLimit() throws Exception {
	// GIVEN
	FieldSetter.setField(underTest, underTest.getClass().getDeclaredField("currentGear"), 4);
	// WHEN
	underTest.updateGear(NON_DOWNSHIFT_RPM);
	// THEN
	Assert.assertEquals(4, underTest.getCurrentGear());
    }

    @Test
    public void testUpdateGearShouldShiftBackToFourthWhenTheRpmIsUnderTheLimit() throws Exception {
	// GIVEN
	FieldSetter.setField(underTest, underTest.getClass().getDeclaredField("currentGear"), 5);
	// WHEN
	underTest.updateGear(DOWNSHIFT_RPM);
	// THEN
	Assert.assertEquals(4, underTest.getCurrentGear());
    }

    @Test
    public void testUpdateGearShouldNotShiftBackToFourthWhenTheRpmIsOverTheLimit() throws Exception {
	// GIVEN
	FieldSetter.setField(underTest, underTest.getClass().getDeclaredField("currentGear"), 5);
	// WHEN
	underTest.updateGear(NON_DOWNSHIFT_RPM);
	// THEN
	Assert.assertEquals(5, underTest.getCurrentGear());
    }

    @Test
    public void testUpdateGearShouldShiftBackToFifthWhenTheRpmIsUnderTheLimit() throws Exception {
	// GIVEN
	FieldSetter.setField(underTest, underTest.getClass().getDeclaredField("currentGear"), 6);
	// WHEN
	underTest.updateGear(DOWNSHIFT_RPM);
	// THEN
	Assert.assertEquals(5, underTest.getCurrentGear());
    }

    @Test
    public void testUpdateGearShouldNotShiftBackToFifthWhenTheRpmIsOverTheLimit() throws Exception {
	// GIVEN
	FieldSetter.setField(underTest, underTest.getClass().getDeclaredField("currentGear"), 6);
	// WHEN
	underTest.updateGear(NON_DOWNSHIFT_RPM);
	// THEN
	Assert.assertEquals(6, underTest.getCurrentGear());
    }
}
