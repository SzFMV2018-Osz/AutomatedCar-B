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
public class CarEngineCalculateEngineTorqueTest {

    private CarEngine underTest;
    private CarEngineType engineType;

    @Before
    public void setUp() {
        engineType = new StandardCarEngineType();
        this.underTest = new CarEngine(engineType);
    }

    @Test
    @Parameters({ "0", "500", "999" })
    public void testcalculateEngineTorqueShouldReturnFirstLookupValueWhenTheRpmIsUnderTheFirstLookupValue(final int rpm)
            throws Exception {
        // GIVEN
        FieldSetter.setField(underTest, underTest.getClass().getDeclaredField("rpm"), rpm);
        // WHEN
        final double maxTorque = underTest.calculateEngineTorque(100);
        // THEN
        Assert.assertEquals(engineType.getTorqueCurve()[0], maxTorque, 0.5);
    }

    @Test
    @Parameters({ "0", "1", "2", "3", "4", "5" })
    public void testcalculateEngineTorqueShouldReturnLookupValueWhenTheRpmIsExactlyTheLookupValue(final int lookupIndex)
            throws Exception {
        // GIVEN
        final int rpm = (lookupIndex + 1) * engineType.getTorqueCurveStepSize();
        FieldSetter.setField(underTest, underTest.getClass().getDeclaredField("rpm"), rpm);
        // WHEN
        final double maxTorque = underTest.calculateEngineTorque(100);
        // THEN
        Assert.assertEquals(engineType.getTorqueCurve()[lookupIndex], maxTorque, 0.5);
    }

    @Test
    @Parameters({ "1333|413", "1500|420", "2500|450", "3500|470", "3833|477", "4500|475", "5500|435", "5833|412" })
    public void testcalculateEngineTorqueShouldReturnCalculatedValueWhenTheRpmIsBetweenTwoLookupPoints(final int rpm,
            final int expectedTorque) throws Exception {
        // GIVEN
        FieldSetter.setField(underTest, underTest.getClass().getDeclaredField("rpm"), rpm);
        // WHEN
        final double maxTorque = underTest.calculateEngineTorque(100);
        // THEN
        Assert.assertEquals(expectedTorque, maxTorque, 0.5);
    }

    @Test
    @Parameters({ "100|450", "50|225", "30|135", "0|0" })
    public void testCalculateEngineTorqueShouldCalculateEngineTorquqBasedOnGasPedalPosition(final int gasPedalPostion,
            final int expectedResult) throws Exception {
        // GIVEN
        FieldSetter.setField(underTest, underTest.getClass().getDeclaredField("rpm"), 2500);
        // WHEN
        final double engineTorque = underTest.calculateEngineTorque(gasPedalPostion);
        // THEN
        Assert.assertEquals(expectedResult, engineTorque, 0.5);
    }
}
