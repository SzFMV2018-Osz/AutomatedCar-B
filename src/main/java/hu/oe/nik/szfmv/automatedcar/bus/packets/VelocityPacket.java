package hu.oe.nik.szfmv.automatedcar.bus.packets;

public class VelocityPacket implements ReadonlyVelocityPacket {

    private double[] velocity = new double[] { 0, 0 };

    @Override
    public double[] getVelocity() {
        return velocity;
    }

    public void setVelocity(double[] velocity) {
        this.velocity = velocity;
    }

    @Override
    public double getSpeed() {
        return Math.sqrt((velocity[0] * velocity[0]) + (velocity[1] * velocity[1]));
    }
}
