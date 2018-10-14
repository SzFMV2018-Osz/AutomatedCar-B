package hu.oe.nik.szfmv.automatedcar;

import hu.oe.nik.szfmv.automatedcar.bus.VirtualFunctionBus;
import hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces.IReadOnlyDashboardPacket;
import hu.oe.nik.szfmv.automatedcar.systemcomponents.DashboardManager;
import hu.oe.nik.szfmv.automatedcar.systemcomponents.InputManager;
import hu.oe.nik.szfmv.automatedcar.systemcomponents.PowertrainSystem;
import hu.oe.nik.szfmv.automatedcar.systemcomponents.SteeringSystem;
import hu.oe.nik.szfmv.automatedcar.systemcomponents.Driver;
import hu.oe.nik.szfmv.environment.WorldObject;
import hu.oe.nik.szfmv.environment.worldobjectclasses.Car;

public class AutomatedCar extends Car {

    private final VirtualFunctionBus virtualFunctionBus = new VirtualFunctionBus();
    private InputManager inputManager;
    private PowertrainSystem powertrainSystem;
    private SteeringSystem steeringSystem;
    private DashboardManager dashboardManager;

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

        inputManager = new InputManager(virtualFunctionBus);
        powertrainSystem = new PowertrainSystem(virtualFunctionBus);
        dashboardManager = new DashboardManager(virtualFunctionBus);
        steeringSystem = new SteeringSystem(virtualFunctionBus);
        new Driver(virtualFunctionBus);
    }

    /**
     * Provides a sample method for modifying the position of the car.
     */
    public void drive() {
        dashboardManager.actualisePosition(x, y);
        virtualFunctionBus.loop();
        calculatePositionAndOrientation();
    }

    /**
     * Return dashboard data to caller
     *
     * @return - returns the packet containing the necessary information
     */
    public IReadOnlyDashboardPacket getDashboardInfo() {
        return dashboardManager.getDashboardPacket();
    }

    /**
     * Calculates the new x and y coordinates of the {@link AutomatedCar} using the
     * powertrain and the steering systems.
     */
    private void calculatePositionAndOrientation() {

        x += virtualFunctionBus.powertrainPacket.getSpeed() * virtualFunctionBus.steeringPacket.getAngularVector()[0];
        y += virtualFunctionBus.powertrainPacket.getSpeed() * virtualFunctionBus.steeringPacket.getAngularVector()[1];

        rotation += virtualFunctionBus.steeringPacket.getAngularSpeed();
    }
}