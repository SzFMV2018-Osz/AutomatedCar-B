package hu.oe.nik.szfmv.engine;

import hu.oe.nik.szfmv.automatedcar.bus.VirtualFunctionBus;
import hu.oe.nik.szfmv.automatedcar.systemcomponents.SteeringSystem;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class TurningHandlerTest {

    private SteeringSystem underTest;

    @Before
    public void setUp() {
        underTest = new SteeringSystem(new VirtualFunctionBus());
    }

    @Test
    @Parameters({ "30|15|2", "60|50|15", "45|70|16", "0|10|0" })
    public void angularVelocityCalculationTest(final int steeringWheelState,int speed,long angularVelocityResult)
    {
        underTest.updateAngularSpeed(steeringWheelState,speed);
        Assert.assertEquals(Math.round(underTest.getAngularSpeed()),angularVelocityResult);
    }
}
