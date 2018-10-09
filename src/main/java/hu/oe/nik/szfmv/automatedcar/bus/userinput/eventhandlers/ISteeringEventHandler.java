package hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers;

import hu.oe.nik.szfmv.automatedcar.bus.userinput.enums.Direction;

public interface ISteeringEventHandler {
    /**
     * Notifies when a steering event occurs
     *
     * @param direction - direction of the steering wheel movement
     */
    void onSteering(Direction direction);

    /**
     * Notifies when the steering event has been stopped
     */
    void onSteeringReleased();
}
