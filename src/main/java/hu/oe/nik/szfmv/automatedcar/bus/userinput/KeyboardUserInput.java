package hu.oe.nik.szfmv.automatedcar.bus.userinput;

import hu.oe.nik.szfmv.automatedcar.bus.userinput.enums.Direction;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.enums.PedalType;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers.IIndicationEventHandler;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers.IPedalEventHandler;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers.IShiftingEventHandler;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers.ISteeringEventHandler;
import hu.oe.nik.szfmv.common.ConfigProvider;
import hu.oe.nik.szfmv.common.enums.Gear;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

/**
 * KeyboardUserInput class.
 */
public class KeyboardUserInput implements IUserInput, KeyListener {

    private static final String UNSUPPORTEDEXCEPTIONMESSAGE = "There isn't event handling support for this pedal type:";

    private final int gas;
    private final int brake;
    private final int steerLeft;
    private final int steerRight;
    private final int indicateLeft;
    private final int indicateRight;
    private final int gearR;
    private final int gearP;
    private final int gearN;
    private final int gearD;

    private List<IPedalEventHandler> gasPedalEventHandlers;
    private List<IPedalEventHandler> brakePedalEventHandlers;
    private List<IShiftingEventHandler> shiftingEventHandlers;
    private List<ISteeringEventHandler> steeringEventHandlers;
    private List<IIndicationEventHandler> indicationEventHandlers;

    private List<Integer> holdKeys;

    /**
     * Constructor of KeyboardUserInput class.
     */
    public KeyboardUserInput() {
        this.gas = ConfigProvider.provide().getLong("keyboard.gas").intValue();
        this.brake = ConfigProvider.provide().getLong("keyboard.brake").intValue();
        this.steerLeft = ConfigProvider.provide().getLong("keyboard.steer_left").intValue();
        this.steerRight = ConfigProvider.provide().getLong("keyboard.steer_right").intValue();
        this.indicateLeft = ConfigProvider.provide().getLong("keyboard.indicate_left").intValue();
        this.indicateRight = ConfigProvider.provide().getLong("keyboard.indicate_right").intValue();
        this.gearR = ConfigProvider.provide().getLong("keyboard.gear_r").intValue();
        this.gearP = ConfigProvider.provide().getLong("keyboard.gear_p").intValue();
        this.gearN = ConfigProvider.provide().getLong("keyboard.gear_n").intValue();
        this.gearD = ConfigProvider.provide().getLong("keyboard.gear_d").intValue();

        this.gasPedalEventHandlers = new ArrayList<>();
        this.brakePedalEventHandlers = new ArrayList<>();
        this.shiftingEventHandlers = new ArrayList<>();
        this.steeringEventHandlers = new ArrayList<>();
        this.indicationEventHandlers = new ArrayList<>();

        this.holdKeys = new ArrayList<>();
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

        if (type == PedalType.Gas) {
            this.gasPedalEventHandlers.add(handler);
        } else if (type == PedalType.Brake) {
            this.brakePedalEventHandlers.add(handler);
        } else {
            throw new UnsupportedOperationException(UNSUPPORTEDEXCEPTIONMESSAGE + type.name());
        }
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
        if (type == PedalType.Gas) {
            this.gasPedalEventHandlers.remove(handler);
        } else if (type == PedalType.Brake) {
            this.brakePedalEventHandlers.remove(handler);
        }
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
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (!ensureKey(e.getKeyCode())) {
            handleFilteredKeyEvent(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == gas) {
            this.onPedalRelease(PedalType.Gas);
        } else if (e.getKeyCode() == brake) {
            this.onPedalRelease(PedalType.Brake);
        } else if (e.getKeyCode() == steerLeft) {
            this.onSteeringRelease();
        } else if (e.getKeyCode() == steerRight) {
            this.onSteeringRelease();
        }

        removeKey(e.getKeyCode());
    }

    private void onPedalPush(PedalType p) {

        if (p == PedalType.Gas) {
            gasPedalEventHandlers.forEach(IPedalEventHandler::onPedalPush);
        } else if (p == PedalType.Brake) {
            brakePedalEventHandlers.forEach(IPedalEventHandler::onPedalPush);
        } else {
            throw new UnsupportedOperationException(UNSUPPORTEDEXCEPTIONMESSAGE + p.name());
        }
    }

    private void onPedalRelease(PedalType p) {

        if (p == PedalType.Gas) {
            gasPedalEventHandlers.forEach(IPedalEventHandler::onPedalRelease);
        } else if (p == PedalType.Brake) {
            brakePedalEventHandlers.forEach(IPedalEventHandler::onPedalRelease);
        } else {
            throw new UnsupportedOperationException(UNSUPPORTEDEXCEPTIONMESSAGE + p.name());
        }
    }

    private void onSteering(Direction d) {
        this.steeringEventHandlers.forEach(handler -> handler.onSteering(d));
    }

    private void onSteeringRelease() {
        this.steeringEventHandlers.forEach(ISteeringEventHandler::onSteeringReleased);
    }

    private void onShifting(Gear g) {
        this.shiftingEventHandlers.forEach(handler -> handler.onShifting(g));
    }

    private void onIndication(Direction d) {
        this.indicationEventHandlers.forEach(handler -> handler.onIndication(d));
    }

    private void argumentCheck(Object o) {
        if (o == null) {
            throw new IllegalArgumentException("The given argument cannot be null!");
        }
    }

    private boolean ensureKey(int code) {
        if (!this.holdKeys.contains(code)) {
            this.holdKeys.add(code);
            return false;
        }
        return true;
    }

    private void removeKey(int code) {
        this.holdKeys.remove(this.holdKeys.indexOf(code));
    }

    private void handleFilteredKeyEvent(KeyEvent e) {
        if (e.getKeyCode() == gas) {
            this.onPedalPush(PedalType.Gas);
        } else if (e.getKeyCode() == brake) {
            this.onPedalPush(PedalType.Brake);
        } else if (e.getKeyCode() == steerLeft) {
            this.onSteering(Direction.Left);
        } else if (e.getKeyCode() == steerRight) {
            this.onSteering(Direction.Right);
        } else if (e.getKeyCode() == indicateLeft) {
            this.onIndication(Direction.Left);
        } else if (e.getKeyCode() == indicateRight) {
            this.onIndication(Direction.Right);
        } else if (e.getKeyCode() == gearP) {
            this.onShifting(Gear.P);
        } else if (e.getKeyCode() == gearN) {
            this.onShifting(Gear.N);
        } else if (e.getKeyCode() == gearD) {
            this.onShifting(Gear.D);
        } else if (e.getKeyCode() == gearR) {
            this.onShifting(Gear.R);
        }
    }

    public int getGasKeyCode() {
        return gas;
    }

    public int getBrakeKeyCode() {
        return brake;
    }

    public int getSteerLeftKeyCode() {
        return steerLeft;
    }

    public int getSteerRightKeyCode() {
        return steerRight;
    }

    public int getIndicateLeft() {
        return indicateLeft;
    }

    public int getIndicateRightKeyCode() {
        return indicateRight;
    }

    public int getGearRKeyCode() {
        return gearR;
    }

    public int getGearPKeyCode() {
        return gearP;
    }

    public int getGearNKeyCode() {
        return gearN;
    }

    public int getGearDKeyCode() {
        return gearD;
    }
}