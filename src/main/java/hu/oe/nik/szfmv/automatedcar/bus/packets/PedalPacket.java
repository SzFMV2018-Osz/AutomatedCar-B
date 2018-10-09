package hu.oe.nik.szfmv.automatedcar.bus.packets;

import hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces.IReadonlyPedalPacket;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.IUserInput;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.enums.PedalType;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers.IPedalEventHandler;

/**
 * Pedal packet class to handle pedals.
 */
public class PedalPacket implements IReadonlyPedalPacket, IPedalEventHandler {

    private final IUserInput input;
    private final int GAS_PEDAL_POSITION_DELTA = 2;
    private final int BRAKE_PEDAL_POSITION_DELTA = 2;
    private final int PEDAL_MAX_POSITION = 100;
    private final int PEDAL_MIN_POSITION = 0;

    private int gasPedalPosition;
    private int gasPedalPositionSnapshot;
    private int brakePedalPosition;
    private int brakePedalPositionSnapshot;

    /**
     * Constructor of PedalPacket class.
     *
     * @param input Input manager.
     */
    public PedalPacket(IUserInput input) {
        this.input = input;
        this.input.subscribePedalEvents(this, PedalType.Gas);
        this.input.subscribePedalEvents(this, PedalType.Brake);
        gasPedalPosition = 0;
        gasPedalPositionSnapshot = 0;
        brakePedalPosition = 0;
        brakePedalPositionSnapshot = 0;
    }

    @Override
    public int getGasPedalPosition() {
        return gasPedalPositionSnapshot;
    }

    @Override
    public int getBrakePedalPosition() {
        return brakePedalPositionSnapshot;
    }

    @Override
    public void onGasPedalPush() {
        if(gasPedalPosition + GAS_PEDAL_POSITION_DELTA <= PEDAL_MAX_POSITION)
            gasPedalPosition += GAS_PEDAL_POSITION_DELTA;
    }

    @Override
    public void onGasPedalRelease() {
        if(gasPedalPosition - GAS_PEDAL_POSITION_DELTA >= PEDAL_MIN_POSITION)
            gasPedalPosition -= GAS_PEDAL_POSITION_DELTA;
    }

    @Override
    public void onBrakePedalPush() {
        if(brakePedalPosition + BRAKE_PEDAL_POSITION_DELTA <= PEDAL_MAX_POSITION)
            brakePedalPosition += BRAKE_PEDAL_POSITION_DELTA;
    }

    @Override
    public void onBrakePedalRelease() {
        if(brakePedalPosition - BRAKE_PEDAL_POSITION_DELTA >= PEDAL_MIN_POSITION)
            brakePedalPosition -= BRAKE_PEDAL_POSITION_DELTA;
    }

    /**
     * Refresh the pedal state and ensure that its value will be the same until the next call of this function
     */
    public void createSnapshot() {
        gasPedalPositionSnapshot = gasPedalPosition;
        brakePedalPositionSnapshot = brakePedalPosition;
    }
}
