package hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers;

import hu.oe.nik.szfmv.common.enums.Gear;

public interface IShiftingEventHandler {
    /**
     * Handles a shifting event
     *
     * @param gear - the value of the current gear
     */
    void onShifting(Gear gear);
}
