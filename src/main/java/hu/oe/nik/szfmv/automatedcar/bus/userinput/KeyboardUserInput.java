package hu.oe.nik.szfmv.automatedcar.bus.userinput;

import hu.oe.nik.szfmv.automatedcar.bus.userinput.enums.Direction;
import hu.oe.nik.szfmv.common.enums.Gear;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.enums.PedalType;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers.IIndicationEventHandler;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers.IPedalEventHandler;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers.IShiftingEventHandler;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers.ISteeringEventHandler;
import hu.oe.nik.szfmv.common.ConfigProvider;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class KeyboardUserInput implements IUserInput, KeyListener {

    private final int GAS;
    private final int BRAKE;
    private final int STEER_LEFT;
    private final int STEER_RIGHT;
    private final int INDICATE_LEFT;
    private final int INDICATE_RIGHT;
    private final int GEAR_R;
    private final int GEAR_P;
    private final int GEAR_N;
    private final int GEAR_D;

    private List<IPedalEventHandler> gasPedalEventHandlers;
    private List<IPedalEventHandler> brakePedalEventHandlers;
    private List<IShiftingEventHandler> shiftingEventHandlers;
    private List<ISteeringEventHandler> steeringEventHandlers;
    private List<IIndicationEventHandler> indicationEventHandlers;

    private List<Integer> holdKeys;

    public KeyboardUserInput() {
        this.GAS=ConfigProvider.provide().getLong("keyboard.gas").intValue();
        this.BRAKE=ConfigProvider.provide().getLong("keyboard.brake").intValue();
        this.STEER_LEFT=ConfigProvider.provide().getLong("keyboard.steer_left").intValue();
        this.STEER_RIGHT=ConfigProvider.provide().getLong("keyboard.steer_right").intValue();
        this.INDICATE_LEFT=ConfigProvider.provide().getLong("keyboard.indicate_left").intValue();
        this.INDICATE_RIGHT=ConfigProvider.provide().getLong("keyboard.indicate_right").intValue();
        this.GEAR_R=ConfigProvider.provide().getLong("keyboard.gear_r").intValue();
        this.GEAR_P=ConfigProvider.provide().getLong("keyboard.gear_p").intValue();
        this.GEAR_N=ConfigProvider.provide().getLong("keyboard.gear_n").intValue();
        this.GEAR_D=ConfigProvider.provide().getLong("keyboard.gear_d").intValue();

        this.gasPedalEventHandlers=new ArrayList<>();
        this.brakePedalEventHandlers=new ArrayList<>();
        this.shiftingEventHandlers=new ArrayList<>();
        this.steeringEventHandlers=new ArrayList<>();
        this.indicationEventHandlers=new ArrayList<>();

        this.holdKeys=new ArrayList<>();
    }

    public List<IPedalEventHandler> getGasPedalEventHandlers() {
        return gasPedalEventHandlers;
    }
    public List<IPedalEventHandler> getBrakePedalEventHandlers() {
        return brakePedalEventHandlers;
    }
    public List<IShiftingEventHandler> getShiftingEventHandlers() {
        return shiftingEventHandlers;
    }
    public List<ISteeringEventHandler> getSteeringEventHandlers() {
        return steeringEventHandlers;
    }
    public List<IIndicationEventHandler> getIndicationEventHandlers() {
        return indicationEventHandlers;
    }

    @Override
    public void subscribePedalEvents(IPedalEventHandler handler, PedalType type) {
        this.argumentCheck(handler);
        this.argumentCheck(type);

        if(type==PedalType.Gas)
            this.gasPedalEventHandlers.add(handler);
        else if(type==PedalType.Brake)
            this.brakePedalEventHandlers.add(handler);
        else
            throw new UnsupportedOperationException("There isn't event handling support for this pedal type: "+type.name());
    }

    @Override
    public void subscribeSteeringEvents(ISteeringEventHandler handler) {
        argumentCheck(handler);
        this.steeringEventHandlers.add(handler);
    }

    @Override
    public void subscribeShiftingEvents(IShiftingEventHandler handler) {
        argumentCheck(handler);
        this.shiftingEventHandlers.add(handler);
    }

    @Override
    public void subscribeIndicationEvents(IIndicationEventHandler handler) {
        argumentCheck(handler);
        this.indicationEventHandlers.add(handler);
    }

    @Override
    public void unsubscribePedalEvents(IPedalEventHandler handler, PedalType type) {
        argumentCheck(type);
        if(type==PedalType.Gas)
            this.gasPedalEventHandlers.remove(handler);
        else if(type==PedalType.Brake)
            this.brakePedalEventHandlers.remove(handler);
    }

    @Override
    public void unsubscribeSteeringEvents(ISteeringEventHandler handler) {
        this.steeringEventHandlers.remove(handler);
    }

    @Override
    public void unsubscribeShiftingEvents(IShiftingEventHandler handler) {
        this.shiftingEventHandlers.remove(handler);
    }

    @Override
    public void unsubscribeIndicationEvents(IIndicationEventHandler handler) {
        this.indicationEventHandlers.remove(handler);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {

        if(!ensureKey(e.getKeyCode()))
            handleFilteredKeyEvent(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {

        if(e.getKeyCode() == GAS)
            this.onPedalRelease(PedalType.Gas);
        else if(e.getKeyCode() == BRAKE)
            this.onPedalRelease(PedalType.Brake);
        else if(e.getKeyCode() == STEER_LEFT)
            this.onSteeringRelease();
        else if(e.getKeyCode() == STEER_RIGHT)
            this.onSteeringRelease();

        removeKey(e.getKeyCode());
    }


    private void onPedalPush(PedalType p){

        if(p == PedalType.Gas)
            gasPedalEventHandlers.forEach(IPedalEventHandler::onGasPedalPush);
        else if(p == PedalType.Brake)
            brakePedalEventHandlers.forEach(IPedalEventHandler::onBrakePedalPush);
        else
            throw new UnsupportedOperationException("There isn't event handling support for this pedal type: "+p.name());
    }
    private void onPedalRelease(PedalType p){

        if(p == PedalType.Gas)
            gasPedalEventHandlers.forEach(IPedalEventHandler::onGasPedalRelease);
        else if(p== PedalType.Brake)
            brakePedalEventHandlers.forEach(IPedalEventHandler::onBrakePedalRelease);
        else
            throw new UnsupportedOperationException("There isn't event handling support for this pedal type: "+p.name());
    }
    private void onSteering(Direction d){ this.steeringEventHandlers.forEach(handler -> handler.onSteering(d)); }
    private void onSteeringRelease(){ this.steeringEventHandlers.forEach(ISteeringEventHandler::onSteeringReleased); }
    private void onShifting(Gear g){ this.shiftingEventHandlers.forEach(handler -> handler.onShifting(g));
    }
    private void onIndication(Direction d) { this.indicationEventHandlers.forEach(handler -> handler.onIndication(d)); }

    private void argumentCheck(Object o){
        if(o==null)
            throw new IllegalArgumentException("The given argument cannot be null!");
    }
    private boolean ensureKey(int code){
        if(!this.holdKeys.contains(code)) {
            this.holdKeys.add(code);
            return false;
        }
        return true;
    }
    private void removeKey(int code){
        this.holdKeys.remove(this.holdKeys.indexOf(code));
    }
    private void handleFilteredKeyEvent(KeyEvent e){
        if (e.getKeyCode() == GAS)
            this.onPedalPush(PedalType.Gas);
        else if (e.getKeyCode() == BRAKE)
            this.onPedalPush(PedalType.Brake);
        else if (e.getKeyCode() == STEER_LEFT)
            this.onSteering(Direction.Left);
        else if (e.getKeyCode() == STEER_RIGHT)
            this.onSteering(Direction.Right);
        else if (e.getKeyCode() == INDICATE_LEFT)
            this.onIndication(Direction.Left);
        else if (e.getKeyCode() == INDICATE_RIGHT)
            this.onIndication(Direction.Right);
        else if (e.getKeyCode() == GEAR_P)
            this.onShifting(Gear.P);
        else if (e.getKeyCode() == GEAR_N)
            this.onShifting(Gear.N);
        else if (e.getKeyCode() == GEAR_D)
            this.onShifting(Gear.D);
        else if (e.getKeyCode() == GEAR_R)
            this.onShifting(Gear.R);
    }
}