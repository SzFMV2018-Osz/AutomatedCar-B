package hu.oe.nik.szfmv.automatedcar.bus.packets;

import hu.oe.nik.szfmv.automatedcar.bus.userinput.IUserInput;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.enums.PedalType;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers.IIndicationEventHandler;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers.IPedalEventHandler;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers.IShiftingEventHandler;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers.ISteeringEventHandler;

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

    @Override
    public int getGasKeyCode() {
        return 0;
    }

    @Override
    public int getBrakeKeyCode() {
        return 0;
    }

    @Override
    public int getSteerLeftKeyCode() {
        return 0;
    }

    @Override
    public int getSteerRightKeyCode() {
        return 0;
    }

    @Override
    public int getIndicateLeftKeyCode() {
        return 0;
    }

    @Override
    public int getIndicateRightKeyCode() {
        return 0;
    }

    @Override
    public int getGearRKeyCode() {
        return 0;
    }

    @Override
    public int getGearPKeyCode() {
        return 0;
    }

    @Override
    public int getGearNKeyCode() {
        return 0;
    }

    @Override
    public int getGearDKeyCode() {
        return 0;
    }
}
