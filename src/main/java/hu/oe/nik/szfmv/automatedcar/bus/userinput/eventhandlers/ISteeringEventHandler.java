package hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers;

import hu.oe.nik.szfmv.automatedcar.bus.userinput.enums.Direction;

public interface ISteeringEventHandler {

    void onSteering(Direction direction);
    void onSteeringReleased();
}
