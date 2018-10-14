package hu.oe.nik.szfmv.automatedcar.systemcomponents;

import hu.oe.nik.szfmv.automatedcar.bus.VirtualFunctionBus;
import hu.oe.nik.szfmv.automatedcar.bus.packets.SteeringPacket;
import hu.oe.nik.szfmv.automatedcar.engine.TurningHandler;

/**
 * Steering system is responsible for the turning of the car.
 */
public class SteeringSystem extends SystemComponent {
    private double angularSpeed = 0;
    private TurningHandler turningHandler;
    private SteeringPacket steeringPacket;

    /**
     * Creates a steering system that connects the Virtual Function Bus
     *
     * @param virtualFunctionBus {@link VirtualFunctionBus} used to connect {@link SystemComponent}s
     */
    public SteeringSystem(VirtualFunctionBus virtualFunctionBus) {
        super(virtualFunctionBus);
        turningHandler = new TurningHandler();
        steeringPacket = new SteeringPacket();
        virtualFunctionBus.steeringPacket = steeringPacket;
    }

    @Override
    public void loop() {
        steeringPacket.setAngularSpeed(getAngularSpeed());
    }

    /**
     * update angular speed
     * @param steeringWheelState actual steering wheel state
     * @param speed actual speed
     */
    public void updateAngularSpeed(final int steeringWheelState, final int speed) {
        angularSpeed = turningHandler.angularVelocityCalculation(steeringWheelState, speed);
    }

    public double getAngularSpeed() {
        return this.angularSpeed;
    }
}
