package hu.oe.nik.szfmv.automatedcar.bus.userinput;

import hu.oe.nik.szfmv.automatedcar.bus.userinput.enums.PedalType;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers.IIndicationEventHandler;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers.IPedalEventHandler;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers.IShiftingEventHandler;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers.ISteeringEventHandler;

public interface IUserInput {
    /**
     * @throws UnsupportedOperationException if the given implementation does not support this pedal type.
     * @throws IllegalArgumentException if the given parameter is invalid.
     */
    void subscribePedalEvents(IPedalEventHandler handler, PedalType type);
    /**
     * @throws IllegalArgumentException if the given parameter is invalid.
     */
    void subscribeSteeringEvents(ISteeringEventHandler handler);
    /**
     * @throws IllegalArgumentException if the given parameter is invalid
     */
    void subscribeShiftingEvents(IShiftingEventHandler handler);
    /**
     * @throws IllegalArgumentException if the given parameter is invalid
     */
    void subscribeIndicationEvents(IIndicationEventHandler handler);
    void unsubscribePedalEvents(IPedalEventHandler handler, PedalType type);
    void unsubscribeSteeringEvents(ISteeringEventHandler handler);
    void unsubscribeShiftingEvents(IShiftingEventHandler handler);
    void unsubscribeIndicationEvents(IIndicationEventHandler handler);

}
