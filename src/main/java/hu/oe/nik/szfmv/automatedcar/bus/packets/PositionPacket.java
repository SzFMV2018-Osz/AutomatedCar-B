package hu.oe.nik.szfmv.automatedcar.bus.packets;

public class PositionPacket implements ReadOnlyPositionPacket {

    private double[] postion;

    private double[] facingDirection;

    @Override
    public double[] getPosition() {
        return postion;
    }

    @Override
    public double[] getFacingDirection() {
        return facingDirection;
    }

    public void setPostion(double[] postion) {
        this.postion = postion;
    }

    public void setFacingDirection(double[] facingDirection) {
        this.facingDirection = facingDirection;
    }

}
