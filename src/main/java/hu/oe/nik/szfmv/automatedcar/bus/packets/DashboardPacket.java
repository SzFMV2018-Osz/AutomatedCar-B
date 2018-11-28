package hu.oe.nik.szfmv.automatedcar.bus.packets;

import hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces.IReadOnlyDashboardPacket;
import hu.oe.nik.szfmv.common.enums.Gear;

public class DashboardPacket implements IReadOnlyDashboardPacket {

    private static final double MS_IN_KMH = 3.6;

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

    public void setBrakePedalPosition(int brakePedalPosition) {
        this.brakePedalPosition = brakePedalPosition;
    }

    @Override
    public int getRPM() {
        return rpm;
    }

    public void setRpm(int rpm) {
        this.rpm = rpm;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    /**
     * Set the current speed of the car for displaying on the dashboard
     *
     * @param speed current speed of the car in m/s
     */
    public void setSpeed(double speed) {
        this.speed = (int) ms2kmh(speed);
    }

    private double ms2kmh(double speed) {
        return speed * MS_IN_KMH;
    }
}