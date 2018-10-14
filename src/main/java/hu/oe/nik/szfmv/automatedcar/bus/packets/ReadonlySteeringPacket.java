package hu.oe.nik.szfmv.automatedcar.bus.packets;

public interface ReadonlySteeringPacket {

    /**
     * @return gets angular speed from the steering system
     */
    double getAngularSpeed();

    /**
     * @return gets angular vector
     */
    double[] getAngularVector();

    /**
     * @return steering wheel state
     */
    int getSteeringWheelState();
}
