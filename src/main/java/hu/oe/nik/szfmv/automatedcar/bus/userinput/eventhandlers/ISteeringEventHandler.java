package hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers;

import hu.oe.nik.szfmv.automatedcar.bus.userinput.enums.Direction;

public interface ISteeringEventHandler {
    /**
     * Method to call on the steering event of the automated car.
     *
     * @param direction - the direction of the steering
     */
    void onSteering(Direction direction);

    /**
     * Method to call when the steering event is over.
     */
    void onSteeringReleased();
}
