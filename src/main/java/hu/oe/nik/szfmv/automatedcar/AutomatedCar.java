package hu.oe.nik.szfmv.automatedcar;

import hu.oe.nik.szfmv.automatedcar.bus.VirtualFunctionBus;
import hu.oe.nik.szfmv.automatedcar.bus.packets.DashboardPacket;
import hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces.IReadOnlyDashboardPacket;
import hu.oe.nik.szfmv.automatedcar.systemcomponents.*;
import hu.oe.nik.szfmv.environment.WorldObject;

public class AutomatedCar extends WorldObject {

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
     * @param imageFileName name of the image file used displaying the car on the course display
     */
    public AutomatedCar(int x, int y, String imageFileName) {
        super(x, y, imageFileName);

        inputManager = new InputManager(virtualFunctionBus);
        powertrainSystem = new PowertrainSystem(virtualFunctionBus);
        steeringSystem = new SteeringSystem(virtualFunctionBus);
        dashboardManager = new DashboardManager(virtualFunctionBus);

        new Driver(virtualFunctionBus);
    }

    /**
     * Provides a sample method for modifying the position of the car.
     */
    public void drive() {
        virtualFunctionBus.loop();

        calculatePositionAndOrientation();
    }

    public IReadOnlyDashboardPacket getDashboardInfo() {
        DashboardPacket packet = dashboardManager.getDashboardPacket();

        packet.setAutomatedCarX(x);
        packet.setAutomatedCarY(y);
        packet.setSteeringWheelValue(dashboardManager.getDashboardPacket().getSteeringWheelValue());

        return packet;
    }

    /**
     * Calculates the new x and y coordinates of the {@link AutomatedCar} using the powertrain and the steering systems.
     */
    private void calculatePositionAndOrientation() {
        //TODO it is just a fake implementation
        double speed = powertrainSystem.getSpeed();
        double angularSpeed = steeringSystem.getAngularSpeed();

        x += speed;
        y = 0;

        rotation += angularSpeed;
    }
}