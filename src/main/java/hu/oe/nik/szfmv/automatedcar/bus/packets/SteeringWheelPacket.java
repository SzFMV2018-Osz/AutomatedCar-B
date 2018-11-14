package hu.oe.nik.szfmv.automatedcar.bus.packets;

import hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces.IGraduallyChangeable;
import hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces.IReadonlySteeringPacket;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.IUserInput;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.enums.Direction;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers.ISteeringEventHandler;

public class SteeringWheelPacket implements IReadonlySteeringPacket, ISteeringEventHandler {

    private static final int MIN_VALUE = -60;
    private static final int MAX_VALUE = 60;
    private static final int CENTER = 0;
    private static final int SPEED_IN_MILLISECONDS = 1000;

    private IGraduallyChangeable steeringWheelPosition;
    private IUserInput userInput;
    private int steeringWheelPositionSnapshot;

    public SteeringWheelPacket(IGraduallyChangeable graduallyChangeable, IUserInput userInput) {
        this.steeringWheelPositionSnapshot = 0;
        this.userInput = userInput;
        this.userInput.subscribeSteeringEvents(this);
        this.steeringWheelPosition = graduallyChangeable;
        this.steeringWheelPosition.startNew(CENTER, CENTER, SPEED_IN_MILLISECONDS);
    }

    @Override
    public int getSteeringWheelPosition() {
        return steeringWheelPositionSnapshot;
    }

    public void createSnapshot() {
        this.steeringWheelPositionSnapshot = steeringWheelPosition.getCurrentValue();
    }

    @Override
    public void onSteering(Direction direction) {
        int from = steeringWheelPositionSnapshot;
        int to = getTarget(direction);

        this.steeringWheelPosition.startNew(from, to, requiredMilliseconds(from, to));
    }

    @Override
    public void onSteeringReleased() {
        int from = steeringWheelPositionSnapshot;
        int to = CENTER;

        this.steeringWheelPosition.startNew(from, to, requiredMilliseconds(from, to));
    }

    private int getTarget(Direction direction) {

        switch (direction) {
            case Left:
                return MIN_VALUE;
            case Right:
                return MAX_VALUE;
            default:
                throw new IllegalArgumentException("Not supported direction");
        }
    }

    private int requiredMilliseconds(int from, int to) {
        return (int) ((distanceBetween(from, to) / (float) distanceBetween(CENTER, MAX_VALUE)) * SPEED_IN_MILLISECONDS);
    }

    private int distanceBetween(int a, int b) {
        return Math.max(a, b) - Math.min(a, b);
    }

}
