package hu.oe.nik.szfmv.automatedcar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;

import hu.oe.nik.szfmv.automatedcar.bus.VirtualFunctionBus;
import hu.oe.nik.szfmv.automatedcar.bus.packets.PowerTrainPacketImpl;
import hu.oe.nik.szfmv.automatedcar.bus.packets.SteeringPacket;
import hu.oe.nik.szfmv.automatedcar.bus.packets.VelocityPacket;
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
    private VelocityPacket velocityPacket;

    @Before
    public void setUp() throws Exception {
        underTest = new AutomatedCar(0, 0, "car_2_white.png");
        busMock = Mockito.spy(VirtualFunctionBus.class);
        mockPowertrain();
        mockSteering();
        velocityPacket = new VelocityPacket();
        busMock.velocityPacket = velocityPacket;
        FieldSetter.setField(underTest, underTest.getClass().getDeclaredField("velocityPacket"), velocityPacket);
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

    @Test
    @Parameters({ "1|0", "2|0", "3|1", "50|445" })
    public void testDriveShouldAccelerateInStraightLineFrom0InitialSpeed(int iterationCount, int expectedX)
            throws Exception {
        // GIVEN
        FieldSetter.setField(velocityPacket, velocityPacket.getClass().getDeclaredField("velocity"),
                new double[] { 0, 0 });
        setUpPowerTrainPacket(TransmissionModes.Drive, 100, 0);
        setUpSteeringPacket(0);
        // WHEN
        callDriveNTimes(iterationCount);
        // THEN
        Assert.assertEquals(expectedX, underTest.getX());
        Assert.assertEquals(0, underTest.getY());
        Assert.assertEquals(0, underTest.getRotation(), 0.5);
    }

    @Test
    @Parameters({ "1|0", "2|0", "3|-1", "50|-498" })
    public void testDriveShouldAccelerateBackwardInAStraightLineFrom0InitialSpeed(int iterationCount, int expectedX)
            throws Exception {
        // GIVEN
        FieldSetter.setField(velocityPacket, velocityPacket.getClass().getDeclaredField("velocity"),
                new double[] { 0, 0 });
        setUpPowerTrainPacket(TransmissionModes.Reverse, 100, 0);
        setUpSteeringPacket(0);
        // WHEN
        callDriveNTimes(iterationCount);
        // THEN
        Assert.assertEquals(expectedX, underTest.getX());
        Assert.assertEquals(0, underTest.getY());
        Assert.assertEquals(0, underTest.getRotation(), 0.5);
    }

    @Test
    @Parameters({ "1|124", "2|247", "3|369", "50|5229" })
    public void testDriveShouldSlowDownInAStraightLineFrom60InitialSpeedIfNoGasPressed(int iterationCount,
            int expectedX) throws Exception {
        // GIVEN
        FieldSetter.setField(velocityPacket, velocityPacket.getClass().getDeclaredField("velocity"),
                new double[] { 60, 0 });
        setUpPowerTrainPacket(TransmissionModes.Drive, 0, 0);
        setUpSteeringPacket(0);
        // WHEN
        callDriveNTimes(iterationCount);
        // THEN
        Assert.assertEquals(expectedX, underTest.getX());
        Assert.assertEquals(0, underTest.getY());
        Assert.assertEquals(0, underTest.getRotation(), 0.5);
    }

    @Test // TODO: overbreaking causes the car the go backwards
    @Parameters({ "1|1", "2|4", "3|9", "50|1511" })
    public void testDriveShouldSlowDownInAStraightLineFrom60InitialSpeedIfNoGasPressedAndBreakIsPressed(
            int iterationCount, int expectedX) throws Exception {
        // GIVEN
        FieldSetter.setField(velocityPacket, velocityPacket.getClass().getDeclaredField("velocity"),
                new double[] { 60, 0 });
        setUpPowerTrainPacket(TransmissionModes.Drive, 0, 100);
        setUpSteeringPacket(0);
        // WHEN
        callDriveNTimes(iterationCount);
        // THEN
        Assert.assertEquals(expectedX, underTest.getX());
        Assert.assertEquals(0, underTest.getY());
        Assert.assertEquals(0, underTest.getRotation(), 0.5);
    }

    @Test
    @Parameters({ "1|0|0|0", "2|0|0|0", "3|1|0|0", "50|43|-12|9" })
    public void testDriveShouldAccelerateForwardInCurveFrom0InitialSpeed(int iterationCount, int expectedX,
            int expectedY, float expectedRotation) throws Exception {
        // GIVEN
        FieldSetter.setField(velocityPacket, velocityPacket.getClass().getDeclaredField("velocity"),
                new double[] { 0, 0 });
        setUpPowerTrainPacket(TransmissionModes.Drive, 100, 0);
        setUpSteeringPacket(30);
        // WHEN
        callDriveNTimes(iterationCount);
        // THEN
        Assert.assertEquals(expectedX, underTest.getX());
        Assert.assertEquals(expectedY, underTest.getY());
        Assert.assertEquals(expectedRotation, underTest.getRotation(), 0.5);
    }

    @Test
    @Parameters({ "1|0|0|0", "2|0|0|0", "3|1|0|0", "50|-6|8|-14" }) // TODO: steering works weird
    public void testDriveShouldAccelerateForwardInCurve2From0InitialSpeed(int iterationCount, int expectedX,
            int expectedY, float expectedRotation) throws Exception {
        // GIVEN
        FieldSetter.setField(velocityPacket, velocityPacket.getClass().getDeclaredField("velocity"),
                new double[] { 0, 0 });
        setUpPowerTrainPacket(TransmissionModes.Drive, 100, 0);
        setUpSteeringPacket(-30);
        // WHEN
        callDriveNTimes(iterationCount);
        // THEN
        Assert.assertEquals(expectedX, underTest.getX());
        Assert.assertEquals(expectedY, underTest.getY());
        Assert.assertEquals(expectedRotation, underTest.getRotation(), 0.5);
    }

    private void callDriveNTimes(int n) {
        for (int i = 0; i < n; i++) {
            underTest.drive();
        }
    }

    private void setUpPowerTrainPacket(TransmissionModes transmissionModes, int gasPedalPosition,
            int breakPedalPosition) {
        Mockito.when(powertrainPacket.getTransmissionMode()).thenReturn(transmissionModes);
        Mockito.when(powertrainPacket.getThrottlePosition()).thenReturn(gasPedalPosition);
        Mockito.when(powertrainPacket.getRpm()).thenCallRealMethod();
        Mockito.when(powertrainPacket.getGear()).thenCallRealMethod();
        Mockito.when(powertrainPacket.getBrakePadelPosition()).thenReturn(breakPedalPosition);
    }

    private void setUpSteeringPacket(int steeringWheelState) {
        Mockito.when(steeringPacket.getAngularSpeed()).thenCallRealMethod();
        // Mockito.when(steeringPacket.getAngularVector()).thenCallRealMethod();
        // Mockito.when(steeringPacket.getSteeringWheelState()).thenReturn(steeringWheelState);
    }
}
