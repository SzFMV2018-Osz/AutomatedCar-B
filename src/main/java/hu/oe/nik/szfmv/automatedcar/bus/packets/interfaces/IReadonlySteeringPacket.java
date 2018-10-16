package hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces;

public interface IReadonlySteeringPacket {

    /**
     * Gets the position of the steering wheel.
     * @return the position of the steering wheel
     */
    int getSteeringWheelPosition();
}
