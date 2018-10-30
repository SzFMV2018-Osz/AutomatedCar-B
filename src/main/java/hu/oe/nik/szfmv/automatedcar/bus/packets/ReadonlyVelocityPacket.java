package hu.oe.nik.szfmv.automatedcar.bus.packets;

public interface ReadonlyVelocityPacket {

    double[] getVelocity();

    double getSpeed();
}
