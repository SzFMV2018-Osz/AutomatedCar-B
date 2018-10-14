package hu.oe.nik.szfmv.automatedcar.bus.packets;

import hu.oe.nik.szfmv.automatedcar.engine.TransmissionModes;

public class PowerTrainPacketImpl implements ReadonlyPowertrainPacket {

    int rpm;
    int gear;
    int speed;
    TransmissionModes transmissionMode;

    public PowerTrainPacketImpl() {
        int rpm=0;
        int gear=1;
        int speed=0;
        transmissionMode=TransmissionModes.Park;
    }

    @Override
    public int getRpm() {
        return rpm;
    }

    @Override
    public int getGear() {
        return gear;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public TransmissionModes getTransmissionMode() {
        return transmissionMode;
    }

    public void setRpm(int rpm) {
        this.rpm = rpm;
    }

    public void setGear(int gear) {
        this.gear = gear;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setTransmissionMode(TransmissionModes transmissionMode) {
        this.transmissionMode = transmissionMode;
    }
}
