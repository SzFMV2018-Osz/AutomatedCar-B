package hu.oe.nik.szfmv.automatedcar.bus.packets;

import hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces.IReadonlyGearPacket;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.IUserInput;
import hu.oe.nik.szfmv.common.enums.Gear;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers.IShiftingEventHandler;

/**
 * Controller class for the gear state.
 */
public class GearPacket implements IReadonlyGearPacket, IShiftingEventHandler {

    private final IUserInput input;

    private Gear currentGear;

    public GearPacket(IUserInput input) {
        this.input = input;
        this.input.subscribeShiftingEvents(this);
        this.currentGear = Gear.N;
    }

    @Override
    public Gear getCurrentGear() {
        return this.currentGear;
    }

    @Override
    public void onShifting(Gear gear) {
        this.currentGear = gear;
    }
}
