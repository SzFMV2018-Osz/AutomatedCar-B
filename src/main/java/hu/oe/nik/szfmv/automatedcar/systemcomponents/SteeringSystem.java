package hu.oe.nik.szfmv.automatedcar.systemcomponents;

import hu.oe.nik.szfmv.automatedcar.bus.VirtualFunctionBus;
import hu.oe.nik.szfmv.automatedcar.bus.packets.SteeringPacket;
import hu.oe.nik.szfmv.automatedcar.engine.TurningHandler;

/**
 * Steering system is responsible for the turning of the car.
 */
public class SteeringSystem extends SystemComponent {
    private TurningHandler turningHandler;
    private SteeringPacket steeringPacket;

    /**
     * Creates a steering system that connects the Virtual Function Bus
     *
     * @param virtualFunctionBus {@link VirtualFunctionBus} used to connect
     *                           {@link SystemComponent}s
     */
    public SteeringSystem(VirtualFunctionBus virtualFunctionBus) {
        super(virtualFunctionBus);
        steeringPacket = new SteeringPacket();
        virtualFunctionBus.steeringPacket = steeringPacket;
        turningHandler = new TurningHandler();
    }

    @Override
    public void loop() {
        steeringPacket.setAngularSpeed(turningHandler.angularVelocityCalculation(steeringPacket.getSteeringWheelState(),
                virtualFunctionBus.velocityPacket.getSpeed()));
        System.out.println("Angular speed: " + steeringPacket.getAngularSpeed());
        steeringPacket.setAngularVector(
                turningHandler.angularVector(steeringPacket.getAngularVector(), steeringPacket.getAngularSpeed()));
    }

}
