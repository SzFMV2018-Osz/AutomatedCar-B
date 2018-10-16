package hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers;

import hu.oe.nik.szfmv.common.enums.Gear;

public interface IShiftingEventHandler {

    /**
     * Method to call on the shifting event of the automated car.
     * @param gear - gear to shift
     */
    void onShifting(Gear gear);
}
