package hu.oe.nik.szfmv.automatedcar.bus.packets;

import hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces.IReadonlyGearPacket;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.IUserInput;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers.IShiftingEventHandler;
import hu.oe.nik.szfmv.common.enums.Gear;

/**
 * Gear packet class to handle shifting.
 */
public class GearPacket implements IReadonlyGearPacket, IShiftingEventHandler {

    private final IUserInput input;

    private Gear currentGear;
    private Gear currentGearSnapshot;

    /**
     * Constructor of GearPacket class.
     *
     * @param input Input manager class.
     */
    public GearPacket(IUserInput input) {
        this.input = input;
        this.input.subscribeShiftingEvents(this);
        this.currentGear = Gear.N;
        this.currentGearSnapshot = Gear.N;
    }

    @Override
    public Gear getCurrentGear() {
        return this.currentGearSnapshot;
    }

    @Override
    public void onShifting(Gear gear) {
        this.currentGear = gear;
    }

    /**
     * Refresh the gear state and ensure that its value will be the same
     * until the next call of this function.
     */
    public void createSnapshot() {

        this.currentGearSnapshot = this.currentGear;
    }
}
