package hu.oe.nik.szfmv.automatedcar.bus.packets;

import hu.oe.nik.szfmv.automatedcar.engine.TransmissionModes;

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

    /**
     * @return with actual car speed
     */
    double getSpeed();

    /**
     * @return with current transmission mode
     */
    TransmissionModes getTransmissionMode();

    /**
     * @return with break pedal position
     */
    int getBrakePadelPosition();

    /**
     * @return with accelerator position
     */
    int getThrottlePosition();

}
