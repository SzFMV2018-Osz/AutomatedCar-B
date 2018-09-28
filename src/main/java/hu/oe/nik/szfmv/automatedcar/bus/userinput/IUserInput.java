package hu.oe.nik.szfmv.automatedcar.bus.userinput;

import hu.oe.nik.szfmv.automatedcar.bus.userinput.enums.PedalType;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers.IIndicationEventHandler;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers.IPedalEventHandler;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers.IShiftingEventHandler;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers.ISteeringEventHandler;

public interface IUserInput {
    void subscribePedalEvents(IPedalEventHandler handler, PedalType type);
    void subscribeSteeringEvents(ISteeringEventHandler handler);
    void subscribeShiftingEvents(IShiftingEventHandler handler);
    void subscribeIndicationEvents(IIndicationEventHandler handler);
    void unsubscribePedalEvents(IPedalEventHandler handler, PedalType type);
    void unsubscribeSteeringEvents(ISteeringEventHandler handler);
    void unsubscribeShiftingEvents(IShiftingEventHandler handler);
    void unsubscribeIndicationEvents(IIndicationEventHandler handler);

}
