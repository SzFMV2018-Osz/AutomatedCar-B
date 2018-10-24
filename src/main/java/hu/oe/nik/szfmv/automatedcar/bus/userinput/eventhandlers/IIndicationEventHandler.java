package hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers;

import hu.oe.nik.szfmv.automatedcar.bus.userinput.enums.Direction;

public interface IIndicationEventHandler {

    /**
     * Method to call on indication.
     *
     * @param direction - the direction of the indication
     */
    void onIndication(Direction direction);
}
