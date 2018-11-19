package hu.oe.nik.szfmv.engine;

import hu.oe.nik.szfmv.automatedcar.engine.TurningHandler;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class TurningHandlerTest {

    private TurningHandler underTest;

    @Before
    public void setUp() {
        underTest = new TurningHandler();
    }

    @Test
    @Parameters({"30|15|3", "60|50|17", "45|70|19", "0|10|0"})
    public void angularVelocityCalculationTest(final int steeringWheelState, int speed, long angularVelocityResult) {
        Assert.assertEquals(Math.round(underTest.angularVelocityCalculation(steeringWheelState, speed)), angularVelocityResult);
    }

    @Test
    @Parameters({"1|2|10|0.249|-2.22", "2|3|5|3.44|-1.07", "5|0|2|-2.08|4.55"})
    public void angularVelocityCalculationTest(double angularVectorX, double angularVectorY, int angularSpeed, double angularVectorResultX, double angularVectorResultY) {
        double[] vector = new double[2];
        vector[0] = angularVectorX;
        vector[1] = angularVectorY;
        double[] result = underTest.angularVector(vector, angularSpeed);
        Assert.assertEquals(result[1], angularVectorResultY, 2);
        Assert.assertEquals(result[0], angularVectorResultX, 2);

    }
}
