package hu.oe.nik.szfmv.automatedcar.bus.packets;

import hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces.IReadOnlyDashboardPacket;
import hu.oe.nik.szfmv.common.enums.Gear;

public class DashboardPacket implements IReadOnlyDashboardPacket {

    private int automatedCarX;
    private int automatedCarY;
    private int steeringWheelValue;
    private Gear currentGear;
    private int indicatorDirection;
    private int gasPedalPosition;
    private int brakePedalPosition;
    private int rpm;
    private int speed;

    @Override
    public int getAutomatedCarX() {
        return automatedCarX;
    }

    public void setAutomatedCarX(int automatedCarX) {
        this.automatedCarX = automatedCarX;
    }

    @Override
    public int getAutomatedCarY() {
        return automatedCarY;
    }

    public void setAutomatedCarY(int automatedCarY) {
        this.automatedCarY = automatedCarY;
    }

    @Override
    public int getSteeringWheelValue() {
        return steeringWheelValue;
    }

    public void setSteeringWheelValue(int steeringWheelValue) {
        this.steeringWheelValue = steeringWheelValue;
    }

    @Override
    public Gear getCurrentGear() {
        return currentGear;
    }

    public void setCurrentGear(Gear currentGear) {
        this.currentGear = currentGear;
    }

    @Override
    public int getIndicatorDirection() {
        return indicatorDirection;
    }

    public void setIndicatorDirection(int indicatorDirection) {
        this.indicatorDirection = indicatorDirection;
    }

    @Override
    public int getGasPedalPosition() {
        return gasPedalPosition;
    }

    public void setGasPedalPosition(int gasPedalPosition) {
        this.gasPedalPosition = gasPedalPosition;
    }

    @Override
    public int getBrakePedalPosition() {
        return brakePedalPosition;
    }

    @Override
    public int getRpm() {
        return rpm;
    }

    public void setRpm(int rpm) {
        this.rpm = rpm;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setBrakePedalPosition(int brakePedalPosition) {
        this.brakePedalPosition = brakePedalPosition;
    }
}