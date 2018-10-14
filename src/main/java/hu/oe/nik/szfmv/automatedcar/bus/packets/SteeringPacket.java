package hu.oe.nik.szfmv.automatedcar.bus.packets;

import hu.oe.nik.szfmv.automatedcar.bus.packets.ReadonlySteeringPacket;

public class SteeringPacket implements ReadonlySteeringPacket {

    private double angularSpeed;

    public SteeringPacket() {
        this.angularSpeed = 0;
    }

    public void setAngularSpeed(double aSpeed) {
        this.angularSpeed = aSpeed;
    }

    @Override
    public double getAngularSpeed() {
        return 0;
    }
}
