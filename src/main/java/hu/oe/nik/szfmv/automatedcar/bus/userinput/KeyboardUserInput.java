package hu.oe.nik.szfmv.automatedcar.bus.userinput;

import hu.oe.nik.szfmv.automatedcar.bus.userinput.enums.PedalType;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers.IIndicationEventHandler;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers.IPedalEventHandler;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers.IShiftingEventHandler;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers.ISteeringEventHandler;

import java.util.ArrayList;
import java.util.List;

public class KeyboardUserInput implements IUserInput {

    private List<IPedalEventHandler> gasPedalEventHandlers;
    private List<IPedalEventHandler> brakePedalEventHandlers;
    private List<IShiftingEventHandler> shiftingEventHandlers;
    private List<ISteeringEventHandler> steeringEventHandlers;
    private List<IIndicationEventHandler> indicationEventHandlers;

    public KeyboardUserInput() {
        this.gasPedalEventHandlers=new ArrayList<>();
        this.brakePedalEventHandlers=new ArrayList<>();
        this.shiftingEventHandlers=new ArrayList<>();
        this.steeringEventHandlers=new ArrayList<>();
        this.indicationEventHandlers=new ArrayList<>();
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

    private void argumentCheck(Object o){
        if(o==null)
            throw new IllegalArgumentException("The given argument cannot be null!");
    }
}