package hu.oe.nik.szfmv.automatedcar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;

import hu.oe.nik.szfmv.automatedcar.bus.VirtualFunctionBus;
import hu.oe.nik.szfmv.automatedcar.bus.packets.PowerTrainPacketImpl;
import hu.oe.nik.szfmv.automatedcar.bus.packets.SteeringPacket;
import hu.oe.nik.szfmv.automatedcar.engine.TransmissionModes;
import hu.oe.nik.szfmv.automatedcar.systemcomponents.PowertrainSystem;
import hu.oe.nik.szfmv.automatedcar.systemcomponents.SteeringSystem;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class AutomatedCarTests {

    private AutomatedCar underTest;
    private VirtualFunctionBus busMock;
    private PowertrainSystem powertrainSystem;
    private SteeringSystem steeringSystem;
    private PowerTrainPacketImpl powertrainPacket;
    private SteeringPacket steeringPacket;

    @Before
    public void setUp() throws Exception {
        underTest = new AutomatedCar(0, 0, "car_2_white.png");
        busMock = Mockito.spy(VirtualFunctionBus.class);
        mockPowertrain();
        mockSteering();
        FieldSetter.setField(underTest, underTest.getClass().getDeclaredField("virtualFunctionBus"), busMock);
        FieldSetter.setField(underTest, underTest.getClass().getDeclaredField("powertrainSystem"), powertrainSystem);
        FieldSetter.setField(underTest, underTest.getClass().getDeclaredField("steeringSystem"), steeringSystem);
    }

    private void mockPowertrain() throws NoSuchFieldException {
        powertrainSystem = new PowertrainSystem(busMock);
        powertrainPacket = Mockito.spy(PowerTrainPacketImpl.class);
        FieldSetter.setField(powertrainSystem, powertrainSystem.getClass().getDeclaredField("powertrainPacket"),
                powertrainPacket);
        busMock.powertrainPacket = powertrainPacket;
    }

    private void mockSteering() throws NoSuchFieldException {
        steeringSystem = new SteeringSystem(busMock);
        steeringPacket = Mockito.spy(SteeringPacket.class);
        FieldSetter.setField(steeringSystem, steeringSystem.getClass().getDeclaredField("steeringPacket"),
                steeringPacket);
        busMock.steeringPacket = steeringPacket;
    }

    @Ignore
    @Test
    @Parameters({ "1|1", "2|4", "3|9", "50|1511" })
    public void testDriveShouldAccelerateInStraightLineFrom0InitialSpeed(int iterationCount, int expectedX) {
        // GIVEN
        setUpPowerTrainPacket(TransmissionModes.Drive, 100, 0, 0);
        setUpSteeringPacket(0);
        // WHEN
        callDriveNTimes(iterationCount);
        // THEN
        Assert.assertEquals(expectedX, underTest.getX());
        Assert.assertEquals(0, underTest.getY());
        Assert.assertEquals(0, underTest.getRotation(), 0.5);
    }

    @Ignore
    @Test
    @Parameters({ "1|-1", "2|-1", "3|-2", "50|-25" })
    public void testDriveShouldAccelerateBackwardInAStraightLineFrom0InitialSpeed(int iterationCount, int expectedX) {
        // GIVEN
        setUpPowerTrainPacket(TransmissionModes.Reverse, 100, 0, 0);
        setUpSteeringPacket(0);
        // WHEN
        callDriveNTimes(iterationCount);
        // THEN
        Assert.assertEquals(expectedX, underTest.getX());
        Assert.assertEquals(0, underTest.getY());
        Assert.assertEquals(0, underTest.getRotation(), 0.5);
    }

    @Ignore
    @Test
    @Parameters({ "1|1", "2|4", "3|9", "4|9", "5|9", "6|9", "7|9", "8|9", "50|1511" })
    public void testDriveShouldSlowDownInAStraightLineFrom60InitialSpeedIfNoGasPressed(int iterationCount,
            int expectedX) {
        // GIVEN
        setUpPowerTrainPacket(TransmissionModes.Drive, 0, 60, 0);
        setUpSteeringPacket(0);
        // WHEN
        callDriveNTimes(iterationCount);
        // THEN
        Assert.assertEquals(expectedX, underTest.getX());
        Assert.assertEquals(0, underTest.getY());
        Assert.assertEquals(0, underTest.getRotation(), 0.5);
    }

    @Test
    @Parameters({ "1|1", "2|4", "3|9", "4|9", "5|9", "6|9", "7|9", "8|9", "50|1511" })
    public void testDriveShouldSlowDownInAStraightLineFrom60InitialSpeedIfNoGasPressedAndBreakIsPressed(
            int iterationCount, int expectedX) {
        // GIVEN
        setUpPowerTrainPacket(TransmissionModes.Drive, 0, 60, 100);
        setUpSteeringPacket(0);
        // WHEN
        callDriveNTimes(iterationCount);
        // THEN
        Assert.assertEquals(expectedX, underTest.getX());
        Assert.assertEquals(0, underTest.getY());
        Assert.assertEquals(0, underTest.getRotation(), 0.5);
    }

//    @Test
//    @Parameters({ "1|1|0|0", "2|3|2|1", "3|1|7|2", "50|179|206|244" }) // TODO: steering might not work properly
//    public void testDriveShouldAccelerateForwardInCurveFrom0InitialSpeed(int iterationCount, int expectedX,
//            int expectedY, float expectedRotation) {
//        // GIVEN
//        setUpPowerTrainPacket(TransmissionModes.Drive, 100, 0, 0);
//        setUpSteeringPacket(30);
//        // WHEN
//        callDriveNTimes(iterationCount);
//        // THEN
//        Assert.assertEquals(expectedX, underTest.getX());
//        Assert.assertEquals(expectedY, underTest.getY());
//        Assert.assertEquals(expectedRotation, underTest.getRotation(), 0.5);
//    }

    private void callDriveNTimes(int n) {
        for (int i = 0; i < n; i++) {
            underTest.drive();
        }
    }

    private void setUpPowerTrainPacket(TransmissionModes transmissionModes, int gasPedalPosition, double initialSpeed,
            int breakPedalPosition) {
        Mockito.when(powertrainPacket.getTransmissionMode()).thenReturn(transmissionModes);
        Mockito.when(powertrainPacket.getThrottlePosition()).thenReturn(gasPedalPosition);
        Mockito.when(powertrainPacket.getSpeed()).thenReturn(initialSpeed).thenCallRealMethod();
        Mockito.when(powertrainPacket.getRpm()).thenCallRealMethod();
        Mockito.when(powertrainPacket.getGear()).thenCallRealMethod();
        Mockito.when(powertrainPacket.getBrakePadelPosition()).thenReturn(breakPedalPosition);
    }

    private void setUpSteeringPacket(int steeringWheelState) {
        Mockito.when(steeringPacket.getAngularSpeed()).thenCallRealMethod();
        Mockito.when(steeringPacket.getAngularVector()).thenCallRealMethod();
        Mockito.when(steeringPacket.getSteeringWheelState()).thenReturn(steeringWheelState);
    }
}
