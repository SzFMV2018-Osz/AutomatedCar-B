package hu.oe.nik.szfmv.automatedcar.bus.packets;

public interface ReadOnlyPositionPacket {
    /**
     * @return Position
     */
    double[] getPosition();

    /**
     * @return Rotation
     */
    double getRotation();

    /**
     * @return Rotation position
     */
    double[] getRotationPosition();
}
