package hu.oe.nik.szfmv.automatedcar.bus.packets;

import hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces.IReadOnlyControlsPacket;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.IUserInput;

import java.awt.event.KeyEvent;

public class ControlsPacket implements IReadOnlyControlsPacket {
    private String gasKeyText;
    private String brakeKeyText;
    private String steerLeftKeyText;
    private String steerRightKeyText;
    private String indicateLeftKeyText;
    private String indicateRightKeyText;
    private String gearRKeyText;
    private String gearPKeyText;
    private String gearNKeyText;
    private String gearDKeyText;

    /**
     * Creates the object (constructor)
     *
     * @param userInput - object containging necessary info
     */
    public ControlsPacket(IUserInput userInput) {
        gasKeyText = KeyEvent.getKeyText(userInput.getGasKeyCode());
        brakeKeyText = KeyEvent.getKeyText(userInput.getBrakeKeyCode());
        steerLeftKeyText = KeyEvent.getKeyText(userInput.getSteerLeftKeyCode());
        steerRightKeyText = KeyEvent.getKeyText(userInput.getSteerRightKeyCode());
        indicateLeftKeyText = KeyEvent.getKeyText(userInput.getIndicateLeftKeyCode());
        indicateRightKeyText = KeyEvent.getKeyText(userInput.getIndicateRightKeyCode());
        gearRKeyText = KeyEvent.getKeyText(userInput.getGearRKeyCode());
        gearPKeyText = KeyEvent.getKeyText(userInput.getGearPKeyCode());
        gearNKeyText = KeyEvent.getKeyText(userInput.getGearNKeyCode());
        gearDKeyText = KeyEvent.getKeyText(userInput.getGearDKeyCode());
    }

    @Override
    public String getGasKeyText() {
        return gasKeyText;
    }

    @Override
    public String getBrakeKeyText() {
        return brakeKeyText;
    }

    @Override
    public String getSteerLeftKeyText() {
        return steerLeftKeyText;
    }

    @Override
    public String getSteerRightKeyText() {
        return steerRightKeyText;
    }

    @Override
    public String getIndicateLeftKeyText() {
        return indicateLeftKeyText;
    }

    @Override
    public String getIndicateRightKeyText() {
        return indicateRightKeyText;
    }

    @Override
    public String getGearRKeyText() {
        return gearRKeyText;
    }

    @Override
    public String getGearPKeyText() {
        return gearPKeyText;
    }

    @Override
    public String getGearNKeyText() {
        return gearNKeyText;
    }

    @Override
    public String getGearDKeyText() {
        return gearDKeyText;
    }
}