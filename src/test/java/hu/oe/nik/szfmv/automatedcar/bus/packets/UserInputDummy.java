package hu.oe.nik.szfmv.automatedcar.bus.packets;

import hu.oe.nik.szfmv.automatedcar.bus.userinput.IUserInput;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.enums.PedalType;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers.*;

import java.awt.event.KeyEvent;

public class UserInputDummy implements IUserInput {
    @Override
    public void subscribePedalEvents(IPedalEventHandler handler, PedalType type) {

    }

    @Override
    public void subscribeSteeringEvents(ISteeringEventHandler handler) {

    }

    @Override
    public void subscribeShiftingEvents(IShiftingEventHandler handler) {

    }

    @Override
    public void subscribeIndicationEvents(IIndicationEventHandler handler) {

    }

    @Override
    public void setSensorDebugEvent(ISensorDebugEventHandler handler) {

    }

    @Override
    public void unsubscribePedalEvents(IPedalEventHandler handler, PedalType type) {

    }

    @Override
    public void unsubscribeSteeringEvents(ISteeringEventHandler handler) {

    }

    @Override
    public void unsubscribeShiftingEvents(IShiftingEventHandler handler) {

    }

    @Override
    public void unsubscribeIndicationEvents(IIndicationEventHandler handler) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
