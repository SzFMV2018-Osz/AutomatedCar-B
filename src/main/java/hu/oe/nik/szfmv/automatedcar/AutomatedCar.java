package hu.oe.nik.szfmv.automatedcar;

import hu.oe.nik.szfmv.automatedcar.bus.VirtualFunctionBus;
import hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces.IReadOnlyDashboardPacket;
import hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces.IReadonlyDisplayableSensorPacket;
import hu.oe.nik.szfmv.automatedcar.systemcomponents.DashboardManager;
import hu.oe.nik.szfmv.automatedcar.systemcomponents.InputManager;
import hu.oe.nik.szfmv.automatedcar.systemcomponents.PowertrainSystem;
import hu.oe.nik.szfmv.automatedcar.systemcomponents.SteeringSystem;
import hu.oe.nik.szfmv.automatedcar.systemcomponents.Driver;
import hu.oe.nik.szfmv.environment.worldobjectclasses.Car;

import java.util.List;

public class AutomatedCar extends Car {

    private final VirtualFunctionBus virtualFunctionBus = new VirtualFunctionBus();
    private final double speedMetersPerSeconds;
    private final double wheelRadius = 0.33;
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
        steeringSystem = new SteeringSystem(virtualFunctionBus);
        dashboardManager = new DashboardManager(virtualFunctionBus);

        speedMetersPerSeconds = 0;

        new Driver(virtualFunctionBus);
    }

    /**
     * Provides a sample method for modifying the position of the car.
     */
    public void drive() {
        dashboardManager.actualisePosition(x, y);
        final double wheelRotationRate = speedMetersPerSeconds / wheelRadius;
        powertrainSystem.updateEngine(wheelRotationRate);
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
     * Return information of the sensors current status
     *
     * @return the necessary data to display visual fields of the sensors
     */
    public List<IReadonlyDisplayableSensorPacket> getDisplayableSensors() {
        throw new RuntimeException("Missing implementation");
    }

    /**
     * Calculates the new x and y coordinates of the {@link AutomatedCar} using the
     * powertrain and the steering systems.
     */
    private void calculatePositionAndOrientation() {
        final double speed = speedMetersPerSeconds;
        final double angularSpeed = steeringSystem.getAngularSpeed();

        x += speed;
        y = 0;

        rotation += angularSpeed;
    }
}