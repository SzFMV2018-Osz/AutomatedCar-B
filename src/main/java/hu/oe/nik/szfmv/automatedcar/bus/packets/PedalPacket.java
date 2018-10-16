package hu.oe.nik.szfmv.automatedcar.bus.packets;

import hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces.IGraduallyChangeable;
import hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces.IReadonlyPedalPacket;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.IUserInput;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.enums.PedalType;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers.IPedalEventHandler;

/**
 * Pedal packet class to handle pedals.
 */
public class PedalPacket implements IReadonlyPedalPacket, IPedalEventHandler {

    public static final int PEDAL_MAX_POSITION = 100;
    public static final int PEDAL_MIN_POSITION = 0;

    private IUserInput input;
    private IGraduallyChangeable pedalPosition;
    private int pedalPositionSnapshot;
    private int speedInMilliseconds;

    /**
     * Constructor of PedalPacket class.
     * @param graduallyChangeable - represents the gradually changeable value of the pedal.
     * @param input - InputManager
     * @param pedalType - Type of the pedal
     * @param speedInMilliseconds - The speed in milliseconds
     */
    public PedalPacket(IGraduallyChangeable graduallyChangeable, IUserInput input, PedalType pedalType, int speedInMilliseconds) {
        this.input = input;
        this.input.subscribePedalEvents(this, pedalType);
        this.pedalPosition = graduallyChangeable;
        this.pedalPositionSnapshot = 0;
        this.speedInMilliseconds = speedInMilliseconds;
        this.pedalPosition.startNew(PEDAL_MIN_POSITION, PEDAL_MIN_POSITION, this.speedInMilliseconds);
    }

    @Override
    public int getPedalPosition() {
        return this.pedalPositionSnapshot;
    }

    @Override
    public void onPedalPush() {
        int from = pedalPositionSnapshot;
        int to = PEDAL_MAX_POSITION;
        this.pedalPosition.startNew(from, to, requiredMilliseconds(from, to));
    }

    @Override
    public void onPedalRelease() {
        int from = pedalPositionSnapshot;
        int to = PEDAL_MIN_POSITION;
        this.pedalPosition.startNew(from, to, requiredMilliseconds(from, to));
    }

    /**
     * Refresh the pedal state and ensure that its value will be the same until the next call of this function.
     */
    public void createSnapshot() {
        this.pedalPositionSnapshot = this.pedalPosition.getCurrentValue();
    }

    private int requiredMilliseconds(int from, int to) {
        return (int) ((distanceBetween(from, to) / (float) distanceBetween(PEDAL_MIN_POSITION, PEDAL_MAX_POSITION)) * this.speedInMilliseconds);
    }

    private int distanceBetween(int a, int b) {
        return Math.max(a, b) - Math.min(a, b);
    }
}
