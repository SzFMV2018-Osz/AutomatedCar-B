package hu.oe.nik.szfmv.automatedcar.systemcomponents;

import hu.oe.nik.szfmv.automatedcar.bus.VirtualFunctionBus;
import hu.oe.nik.szfmv.automatedcar.bus.packets.SteeringPacket;
import hu.oe.nik.szfmv.automatedcar.engine.CarAxisParams;
import hu.oe.nik.szfmv.automatedcar.engine.CarAxisParamsImpl;


/**
 * Steering system is responsible for the turning of the car.
 */
public class SteeringSystem extends SystemComponent {
    private SteeringPacket steeringPacket;
    private VirtualFunctionBus virtualFunctionBus;
    private CarAxisParams carAxisParams;
    private float angularSpeed;

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
        carAxisParams = new CarAxisParamsImpl();
    }

    @Override
    public void loop() {
        float circleRadius = (float) (carAxisParams.getAxisLengthPixel()
                / Math.sin(Math.toRadians(virtualFunctionBus.steeringWheelPacket.getSteeringWheelPosition())));
        angularSpeed = (float) ((virtualFunctionBus.velocityPacket.getSpeed() * 50) / circleRadius);
        steeringPacket.setAngularSpeed(angularSpeed);
    }

}
