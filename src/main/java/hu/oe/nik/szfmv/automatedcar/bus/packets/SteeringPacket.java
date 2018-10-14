package hu.oe.nik.szfmv.automatedcar.bus.packets;

public class SteeringPacket implements ReadonlySteeringPacket {

    private double angularSpeed;


    private double[] angularVector;
    private int steeringWheelState;

    public SteeringPacket() {
        this.angularSpeed = 0;
        this.angularVector[0] = 0;
        this.angularVector[1] = 1;
        this.steeringWheelState = 0;
    }

    @Override
    public double getAngularSpeed() {
        return angularSpeed;
    }

    public void setAngularSpeed(double aSpeed) {
        this.angularSpeed = aSpeed;
    }

    @Override
    public double[] getAngularVector() {
        return angularVector;
    }

    public void setAngularVector(double[] angularVector) {
        this.angularVector = angularVector;
    }

    @Override
    public int getSteeringWheelState() {
        return steeringWheelState;
    }

    public void setSteeringWheelState(int steeringWheelState) {
        this.steeringWheelState = steeringWheelState;
    }
}
