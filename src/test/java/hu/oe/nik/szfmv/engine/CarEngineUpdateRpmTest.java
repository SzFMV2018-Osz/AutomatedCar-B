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
    @Parameters({ "0|0", "10|2257", "20|4514", "30|6771", "40|9028", "50|11285" })
    public void testUpdateRpmInFirstGear(final int carSpeed, final int expectedRpm) {
        // GIVEN
        // WHEN
        underTest.updateRpm(carSpeed, 1);
        // THEN
        Assert.assertEquals(expectedRpm, underTest.getRpm());
    }

    @Test
    @Parameters({ "0|0", "10|1475", "20|2951", "30|4427", "40|5903", "50|7379" })
    public void testUpdateRpmInSecondGear(final int carSpeed, final int expectedRpm) throws Exception {
        // GIVEN
        // WHEN
        underTest.updateRpm(carSpeed, 2);
        // THEN
        Assert.assertEquals(expectedRpm, underTest.getRpm());
    }

    @Test
    @Parameters({ "0|0", "10|1128", "20|2257", "30|3385", "40|4514", "50|5642" })
    public void testUpdateRpmInThirdGear(final int carSpeed, final int expectedRpm) throws Exception {
        // GIVEN
        // WHEN
        underTest.updateRpm(carSpeed, 3);
        // THEN
        Assert.assertEquals(expectedRpm, underTest.getRpm());
    }

    @Test
    @Parameters({ "0|0", "10|868", "20|1736", "30|2604", "40|3472", "50|4340" })
    public void testUpdateRpmInFourthGear(final int carSpeed, final int expectedRpm) throws Exception {
        // GIVEN
        // WHEN
        underTest.updateRpm(carSpeed, 4);
        // THEN
        Assert.assertEquals(expectedRpm, underTest.getRpm());
    }

    @Test
    @Parameters({ "0|0", "10|607", "20|1215", "30|1823", "40|2430", "50|3038" })
    public void testUpdateRpmInFifthGear(final int carSpeed, final int expectedRpm) throws Exception {
        // GIVEN
        // WHEN
        underTest.updateRpm(carSpeed, 5);
        // THEN
        Assert.assertEquals(expectedRpm, underTest.getRpm());
    }

    @Test
    @Parameters({ "0|0", "10|434", "20|868", "30|1302", "40|1736", "50|2170" })
    public void testUpdateRpmInSixthGear(final int carSpeed, final int expectedRpm) throws Exception {
        // GIVEN
        // WHEN
        underTest.updateRpm(carSpeed, 6);
        // THEN
        Assert.assertEquals(expectedRpm, underTest.getRpm());
    }

}
