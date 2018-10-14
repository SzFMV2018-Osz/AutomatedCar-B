package hu.oe.nik.szfmv.automatedcar;

import hu.oe.nik.szfmv.automatedcar.bus.VirtualFunctionBus;
import hu.oe.nik.szfmv.automatedcar.systemcomponents.Driver;
import hu.oe.nik.szfmv.automatedcar.systemcomponents.PowertrainSystem;
import hu.oe.nik.szfmv.automatedcar.systemcomponents.SteeringSystem;
import hu.oe.nik.szfmv.environment.WorldObject;

public class AutomatedCar extends WorldObject {

    private final PowertrainSystem powertrainSystem;
    private final SteeringSystem steeringSystem;
    private final VirtualFunctionBus virtualFunctionBus = new VirtualFunctionBus();
    private final double speedMetersPerSeconds;

    /**
     * Constructor of the AutomatedCar class
     *
     * @param x             the initial x coordinate of the car
     * @param y             the initial y coordinate of the car
     * @param imageFileName name of the image file used displaying the car on the
     *                      course display
     */
    public AutomatedCar(final int x, final int y, final String imageFileName) {
        super(x, y, imageFileName);

        powertrainSystem = new PowertrainSystem(virtualFunctionBus);
        steeringSystem = new SteeringSystem(virtualFunctionBus);
        speedMetersPerSeconds = 0;

        new Driver(virtualFunctionBus);
    }

    /**
     * Provides a sample method for modifying the position of the car.
     */
    public void drive() {
        virtualFunctionBus.loop();
        calculatePositionAndOrientation();
    }

    /**
     * Calculates the new x and y coordinates of the {@link AutomatedCar} using the
     * powertrain and the steering systems.
     */
    private void calculatePositionAndOrientation() {

        x += virtualFunctionBus.powertrainPacket.getSpeed() * virtualFunctionBus.steeringPacket.getAngularVector()[0];
        y -= virtualFunctionBus.powertrainPacket.getSpeed() * virtualFunctionBus.steeringPacket.getAngularVector()[0];

        rotation += virtualFunctionBus.steeringPacket.getAngularSpeed();
    }
}