package hu.oe.nik.szfmv.automatedcar.bus.packets;

public interface ReadOnlyPositionPacket {

    double[] getPosition();

    double[] getFacingDirection();
}
