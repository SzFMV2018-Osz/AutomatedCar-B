package hu.oe.nik.szfmv.automatedcar.bus.packets;

public interface ReadonlySteeringPacket {

    /**
     * @return gets angular speed from the steering system
     */
    double getAngularSpeed();
}
