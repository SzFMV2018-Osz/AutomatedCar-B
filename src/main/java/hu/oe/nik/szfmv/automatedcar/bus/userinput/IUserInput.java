package hu.oe.nik.szfmv.automatedcar.bus.userinput;

import hu.oe.nik.szfmv.automatedcar.bus.userinput.enums.PedalType;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers.*;

import java.awt.event.KeyListener;

/**
 * User input interface.
 */
public interface IUserInput extends KeyListener {

    /**
     * Helper method to subscribe to pedal events.
     *
     * @param handler - handler to subscribe
     * @param type    - type of the pedal event.
     */
    void subscribePedalEvents(IPedalEventHandler handler, PedalType type);

    /**
     * Helper method to subscribe to steering events.
     *
     * @param handler - handler to subscribe
     */
    void subscribeSteeringEvents(ISteeringEventHandler handler);

    /**
     * Helper method to subscribe to shifting events.
     *
     * @param handler - - handler to subscribe
     */
    void subscribeShiftingEvents(IShiftingEventHandler handler);

    /**
     * Helper method to subscribe to indication events.
     *
     * @param handler - - handler to subscribe
     */
    void subscribeIndicationEvents(IIndicationEventHandler handler);

    /**
     * Helper method to unsubscribe to pedal events.
     *
     * @param handler - handler to unsubscribe
     * @param type    - type of the pedal event.
     */
    void unsubscribePedalEvents(IPedalEventHandler handler, PedalType type);

    /**
     * Helper method to unsubscribe to steering events.
     *
     * @param handler - handler to unsubscribe
     */
    void unsubscribeSteeringEvents(ISteeringEventHandler handler);

    /**
     * Helper method to unsubscribe to shifting events.
     *
     * @param handler - - handler to unsubscribe
     */
    void unsubscribeShiftingEvents(IShiftingEventHandler handler);

    /**
     * Helper method to unsubscribe to indication events.
     *
     * @param handler - - handler to unsubscribe
     */
    void unsubscribeIndicationEvents(IIndicationEventHandler handler);

    void setSensorDebugEvent(ISensorDebugEventHandler handler);

}
