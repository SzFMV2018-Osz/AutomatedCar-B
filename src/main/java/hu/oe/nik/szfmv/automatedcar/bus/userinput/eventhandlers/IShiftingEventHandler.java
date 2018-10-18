package hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers;

import hu.oe.nik.szfmv.common.enums.Gear;

public interface IShiftingEventHandler {
    void onShifting(Gear gear);
}
