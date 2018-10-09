package hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces;

public interface IReadonlySteeringPacket {
    /**
     * Gives access to the steering wheel's state
     *
     * @return a value between -60 and 60
     */
    int getSteeringWheelPosition();
}
