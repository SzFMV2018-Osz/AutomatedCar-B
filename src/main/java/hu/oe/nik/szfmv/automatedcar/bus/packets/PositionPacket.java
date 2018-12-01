package hu.oe.nik.szfmv.automatedcar.bus.packets;

public class PositionPacket implements ReadOnlyPositionPacket {

    private double[] postion;

    private double rotation;

    private double[] rotationPosition;

    private double[] imagePosition;
    private double[] orientation;

    public double[] getOrientation() {
        return orientation;
    }

    public void setOrientation(double[] orientation) {
        this.orientation = orientation;
    }

    public double[] getImagePosition() {
        return imagePosition;
    }

    public void setImagePosition(double[] imagePosition) {
        this.imagePosition = imagePosition;
    }

    @Override
    public double[] getRotationPosition() {
        return rotationPosition;
    }

    public void setRotationPosition(double[] rotationPosition) {
        this.rotationPosition = rotationPosition;
    }


    @Override
    public double[] getPosition() {
        return postion;
    }

    @Override
    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public void setPostion(double[] postion) {
        this.postion = postion;
    }

}
