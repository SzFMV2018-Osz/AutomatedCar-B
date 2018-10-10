package hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers;

import hu.oe.nik.szfmv.common.enums.Gear;

public interface IShiftingEventHandler {
    /**
     * Notifies when a shifting event occurs
     *
     * @param gear the chosen gear
     */
    void onShifting(Gear gear);
}
