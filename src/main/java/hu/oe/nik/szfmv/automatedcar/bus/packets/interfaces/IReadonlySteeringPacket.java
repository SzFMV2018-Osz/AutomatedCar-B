package hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces;

public interface IReadonlySteeringPacket {
    /**
     * @return a value between -60 and 60
     */
    int getSteeringWheelPosition();
}
