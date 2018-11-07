package hu.oe.nik.szfmv.automatedcar.bus.packets;

public class PositionPacket implements ReadOnlyPositionPacket {

    private double[] postion;

    private double rotation;

    @Override
    public double[] getPosition() {
        return postion;
    }

    @Override
    public double getRotation() {
        return rotation;
    }

    public void setPostion(double[] postion) {
        this.postion = postion;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

}
