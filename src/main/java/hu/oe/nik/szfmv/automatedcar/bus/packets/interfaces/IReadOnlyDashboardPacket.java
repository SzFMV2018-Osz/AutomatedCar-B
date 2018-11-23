package hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces;

import hu.oe.nik.szfmv.common.enums.Gear;

public interface IReadOnlyDashboardPacket {
    /**
     * Gets the automated car x position.
     *
     * @return the x position of the automated car
     */
    int getAutomatedCarX();

    /**
     * Gets the automated car y position.
     *
     * @return the y position of the automated car
     */
    int getAutomatedCarY();

    /**
     * Gets the value of the steering wheel.
     *
     * @return the value of the steering wheel
     */
    int getSteeringWheelValue();

    /**
     * Gets the value of the current gear.
     *
     * @return the value of the current gear
     */
    Gear getCurrentGear();

    /**
     * Gets the value of the indication direction.
     *
     * @return the value of the indication direction
     */
    int getIndicatorDirection();

    /**
     * Gets the position of the gas pedal.
     *
     * @return the position of the gas pedal
     */
    int getGasPedalPosition();

    /**
     * Gets the position of the brake pedal.
     *
     * @return the position of the brake pedal
     */
    int getBrakePedalPosition();

    /**
     * Gets the engine's RPM value
     *
     * @return the engine's RPM
     */
    int getRPM();

    /**
     * Gets the car's speed
     *
     * @return the car's speed
     */
    int getSpeed();
}