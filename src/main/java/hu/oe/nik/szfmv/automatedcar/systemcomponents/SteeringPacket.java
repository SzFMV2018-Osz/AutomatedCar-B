package hu.oe.nik.szfmv.automatedcar.systemcomponents;

public class SteeringPacket implements ReadonlySteeringPacket {

    private double angularSpeed;

    public void setAngularSpeed(double aSpeed) {
        this.angularSpeed = aSpeed;
    }

    @Override
    public double getAngularSpeed() {
        return 0;
    }
}
