package hu.oe.nik.szfmv.automatedcar.bus.packets;

import hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces.IGraduallyChangeable;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.enums.Direction;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SteeringWheelPacketTest {

    private final int MIN_VALUE;
    private final int MAX_VALUE;
    private final int CENTER;
    private final int SPEED_IN_MILLISECONDS;

    public SteeringWheelPacketTest() throws NoSuchFieldException, IllegalAccessException {
        Field fMs = SteeringWheelPacket.class.getDeclaredField("SPEED_IN_MILLISECONDS");
        Field fMax = SteeringWheelPacket.class.getDeclaredField("MAX_VALUE");
        Field fMin = SteeringWheelPacket.class.getDeclaredField("MIN_VALUE");
        Field fCenter = SteeringWheelPacket.class.getDeclaredField("CENTER");
        fMs.setAccessible(true);
        fMax.setAccessible(true);
        fMin.setAccessible(true);
        fCenter.setAccessible(true);

        MIN_VALUE = (int) fMin.get(null);
        MAX_VALUE = (int) fMax.get(null);
        CENTER = (int) fCenter.get(null);
        SPEED_IN_MILLISECONDS = (int) fMs.get(null);
    }

    @Test
    public void requestBeforeAnyEvent() {
        SteeringWheelPacket swp = new SteeringWheelPacket(new GraduallyChangeableDummy(), new UserInputDummy());

        Assert.assertEquals(0, swp.getSteeringWheelPosition());
    }

    @Test
    public void requestSnapshotBeforeAnyEvent() {
        SteeringWheelPacket swp = new SteeringWheelPacket(new GraduallyChangeableDummy(), new UserInputDummy());
        swp.createSnapshot();

        Assert.assertEquals(0, swp.getSteeringWheelPosition());
    }

    @Test
    public void distanceCalculation() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method m = SteeringWheelPacket.class.getDeclaredMethod("distanceBetween", int.class, int.class);
        m.setAccessible(true);

        // ACT
        int result = (int) m.invoke(new SteeringWheelPacket(new GraduallyChangeableDummy(), new UserInputDummy()),
                new Object[]{30, -20});

        // ASSERT
        Assert.assertEquals(50, result);
    }

    @Test
    public void getTargetByDirection_LEFT() throws NoSuchMethodException, InvocationTargetException,
            IllegalAccessException {

        // ARRANGE
        Method m = SteeringWheelPacket.class.getDeclaredMethod("getTarget", Direction.class);
        m.setAccessible(true);

        SteeringWheelPacket swp = new SteeringWheelPacket(new GraduallyChangeableDummy(), new UserInputDummy());
        Direction input = Direction.Left;

        // ACT
        int result = (int) m.invoke(swp, new Object[]{input});

        // ASSERT
        Assert.assertEquals(MIN_VALUE, result);
    }

    @Test
    public void getTargetByDirection_RIGHT() throws NoSuchMethodException, InvocationTargetException,
            IllegalAccessException {

        // ARRANGE
        Method m = SteeringWheelPacket.class.getDeclaredMethod("getTarget", Direction.class);
        m.setAccessible(true);

        SteeringWheelPacket swp = new SteeringWheelPacket(new GraduallyChangeableDummy(),
                new UserInputDummy());
        Direction input = Direction.Right;

        // ACT
        int result = (int) m.invoke(swp, new Object[]{input});

        // ASSERT
        Assert.assertEquals(MAX_VALUE, result);
    }

    @Test
    public void createSnapshot() {
        SteeringWheelPacket sw = new SteeringWheelPacket(new GraduallyChangeableMock(), new UserInputDummy());
        sw.createSnapshot();
        int startValue = sw.getSteeringWheelPosition();
        sw.onSteering(Direction.Left);

        Assert.assertEquals(startValue, sw.getSteeringWheelPosition());
    }

    @Test
    public void onSteering_Right() {
        GraduallyChangeableMock mock = new GraduallyChangeableMock();
        SteeringWheelPacket sw = new SteeringWheelPacket(mock, new UserInputDummy());

        Direction input = Direction.Right;

        sw.onSteering(input);
        mock.currentValue = MAX_VALUE;
        sw.createSnapshot();

        Assert.assertEquals(MAX_VALUE, sw.getSteeringWheelPosition());
        Assert.assertEquals(SPEED_IN_MILLISECONDS, mock.milliseconds);
        Assert.assertEquals(CENTER, mock.from);
        Assert.assertEquals(MAX_VALUE, mock.to);
    }

    @Test
    public void onRelease_FromRightToCenter() {
        GraduallyChangeableMock mock = new GraduallyChangeableMock();
        SteeringWheelPacket sw = new SteeringWheelPacket(mock, new UserInputDummy());
        mock.currentValue = MAX_VALUE / 2;
        sw.createSnapshot();

        sw.onSteeringReleased();

        Assert.assertEquals(MAX_VALUE / 2, mock.from);
        Assert.assertEquals(CENTER, mock.to);
        Assert.assertEquals(SPEED_IN_MILLISECONDS / 4, mock.milliseconds);
    }
}

class GraduallyChangeableDummy implements IGraduallyChangeable {
    @Override
    public void startNew(int from, int to, int milliseconds) {

    }

    @Override
    public int getCurrentValue() {
        return 0;
    }
}

class GraduallyChangeableMock implements IGraduallyChangeable {

    int currentValue;
    int from;
    int to;
    int milliseconds;
    int callNumber;

    @Override
    public void startNew(int from, int to, int milliseconds) {
        this.from = from;
        this.to = to;
        this.milliseconds = milliseconds;
        this.callNumber++;
    }

    @Override
    public int getCurrentValue() {
        return currentValue;
    }
}