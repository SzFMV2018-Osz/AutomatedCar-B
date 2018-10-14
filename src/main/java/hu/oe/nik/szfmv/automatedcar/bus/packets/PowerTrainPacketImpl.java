package hu.oe.nik.szfmv.automatedcar.bus.packets;

import hu.oe.nik.szfmv.automatedcar.engine.TransmissionModes;

public class PowerTrainPacketImpl implements ReadonlyPowertrainPacket {

    int rpm;
    int gear;
    double speed;
    TransmissionModes transmissionMode;
    int brakePadelPosition;
    int throttlePosition;

    public PowerTrainPacketImpl() {
        rpm = 0;
        gear = 1;
        speed = 0;
        brakePadelPosition = 0;
        throttlePosition = 0;
        transmissionMode = TransmissionModes.Park;
    }

    @Override
    public int getRpm() {
        return rpm;
    }

    public void setRpm(int rpm) {
        this.rpm = rpm;
    }

    @Override
    public int getGear() {
        return gear;
    }

    public void setGear(int gear) {
        this.gear = gear;
    }

    @Override
    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public TransmissionModes getTransmissionMode() {
        return transmissionMode;
    }

    public void setTransmissionMode(TransmissionModes transmissionMode) {
        this.transmissionMode = transmissionMode;
    }

    @Override
    public int getBrakePadelPosition() {
        return brakePadelPosition;
    }

    public void setBrakePadelPosition(int brakePadelPosition) {
        this.brakePadelPosition = brakePadelPosition;
    }

    @Override
    public int getThrottlePosition() {
        return throttlePosition;
    }

    public void setThrottlePosition(int throttlePosition) {
        this.throttlePosition = throttlePosition;
    }
}
