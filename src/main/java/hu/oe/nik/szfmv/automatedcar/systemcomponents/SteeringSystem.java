package hu.oe.nik.szfmv.automatedcar.systemcomponents;

import hu.oe.nik.szfmv.automatedcar.bus.VirtualFunctionBus;
import hu.oe.nik.szfmv.automatedcar.bus.packets.SteeringPacket;
import hu.oe.nik.szfmv.automatedcar.engine.TurningHandler;


/**
 * Steering system is responsible for the turning of the car.
 */
public class SteeringSystem extends SystemComponent {
    private SteeringPacket steeringPacket;
    private VirtualFunctionBus virtualFunctionBus;
    private TurningHandler turningHandler;

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
        this.virtualFunctionBus = virtualFunctionBus;
        turningHandler = new TurningHandler();
    }

    @Override
    public void loop() {
        steeringPacket.setAngularSpeed((float)
                turningHandler.angularVelocityCalculation(virtualFunctionBus.steeringWheelPacket.getSteeringWheelPosition(),
                        virtualFunctionBus.velocityPacket.getSpeed()));
    }

}
