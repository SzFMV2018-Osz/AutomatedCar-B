package hu.oe.nik.szfmv.automatedcar.bus.userinput;

import hu.oe.nik.szfmv.automatedcar.bus.userinput.enums.PedalType;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers.IIndicationEventHandler;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers.IPedalEventHandler;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers.IShiftingEventHandler;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers.ISteeringEventHandler;

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

    /**
     * Returns the numerical key-code of the given key
     *
     * @return returns the code as an int
     */
    int getGasKeyCode();

    /**
     * Returns the numerical key-code of the given key
     *
     * @return returns the code as an int
     */
    int getBrakeKeyCode();

    /**
     * Returns the numerical key-code of the given key
     *
     * @return returns the code as an int
     */
    int getSteerLeftKeyCode();

    /**
     * Returns the numerical key-code of the given key
     *
     * @return returns the code as an int
     */
    int getSteerRightKeyCode();

    /**
     * Returns the numerical key-code of the given key
     *
     * @return returns the code as an int
     */
    int getIndicateLeftKeyCode();

    /**
     * Returns the numerical key-code of the given key
     *
     * @return returns the code as an int
     */
    int getIndicateRightKeyCode();

    /**
     * Returns the numerical key-code of the given key
     *
     * @return returns the code as an int
     */
    int getGearRKeyCode();

    /**
     * Returns the numerical key-code of the given key
     *
     * @return returns the code as an int
     */
    int getGearPKeyCode();

    /**
     * Returns the numerical key-code of the given key
     *
     * @return returns the code as an int
     */
    int getGearNKeyCode();

    /**
     * Returns the numerical key-code of the given key
     *
     * @return returns the code as an int
     */
    int getGearDKeyCode();
}
