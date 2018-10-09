package hu.oe.nik.szfmv.automatedcar.bus.userinput;

import hu.oe.nik.szfmv.automatedcar.bus.userinput.enums.PedalType;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers.IIndicationEventHandler;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers.IPedalEventHandler;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers.IShiftingEventHandler;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers.ISteeringEventHandler;

import java.awt.event.KeyListener;

public interface IUserInput extends KeyListener {
    /**
     * @param handler - function which will handle the event
     * @param type    - the type of the pedal which events we want to be notified of
     * @throws UnsupportedOperationException if the given implementation does not support this pedal type.
     * @throws IllegalArgumentException      if the given parameter is invalid.
     */
    void subscribePedalEvents(IPedalEventHandler handler, PedalType type);

    /**
     * @param handler - function which will handle the event
     * @throws IllegalArgumentException if the given parameter is invalid.
     */
    void subscribeSteeringEvents(ISteeringEventHandler handler);

    /**
     * @param handler - function which will handle the event
     * @throws IllegalArgumentException if the given parameter is invalid
     */
    void subscribeShiftingEvents(IShiftingEventHandler handler);

    /**
     * @param handler - function which will handle the event
     * @throws IllegalArgumentException if the given parameter is invalid
     */
    void subscribeIndicationEvents(IIndicationEventHandler handler);

    /**
     * @param handler -  the instance we want to remove from the subscriber's list
     * @param type    - the type of the pedal which the handler is notified of
     */
    void unsubscribePedalEvents(IPedalEventHandler handler, PedalType type);

    /**
     * @param handler -  the instance we want to remove from the subscriber's list
     */
    void unsubscribeSteeringEvents(ISteeringEventHandler handler);

    /**
     * @param handler -  the instance we want to remove from the subscriber's list
     */
    void unsubscribeShiftingEvents(IShiftingEventHandler handler);

    /**
     * @param handler -  the instance we want to remove from the subscriber's list
     */
    void unsubscribeIndicationEvents(IIndicationEventHandler handler);

}
