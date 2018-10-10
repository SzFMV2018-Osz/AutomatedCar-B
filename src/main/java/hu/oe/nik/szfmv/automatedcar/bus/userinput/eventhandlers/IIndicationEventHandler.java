package hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers;

import hu.oe.nik.szfmv.automatedcar.bus.userinput.enums.Direction;

public interface IIndicationEventHandler {
    /**
     * Notification of an indication event
     *
     * @param direction - the indicated direction
     */
    void onIndication(Direction direction);
}
