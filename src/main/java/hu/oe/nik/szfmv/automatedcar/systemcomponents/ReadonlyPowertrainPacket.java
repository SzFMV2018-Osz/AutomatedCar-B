package hu.oe.nik.szfmv.automatedcar.systemcomponents;

/**
 * The interface that connects the Powertrain system with the Virtual bus
 */
public interface ReadonlyPowertrainPacket {
    /**
     * @return get the current RPM
     */
    int getRpm();

    /**
     * @return gets the current gear
     */
    int getGear();
}
