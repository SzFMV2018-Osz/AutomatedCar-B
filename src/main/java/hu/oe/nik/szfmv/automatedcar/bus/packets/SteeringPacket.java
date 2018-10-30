package hu.oe.nik.szfmv.automatedcar.bus.packets;

public class SteeringPacket implements ReadonlySteeringPacket {

    private float angularSpeed;

    public SteeringPacket() {
        this.angularSpeed = 0;
    }

    @Override
    public float getAngularSpeed() {
        return angularSpeed;
    }

    public void setAngularSpeed(float aSpeed) {
        this.angularSpeed = aSpeed;
    }
}
