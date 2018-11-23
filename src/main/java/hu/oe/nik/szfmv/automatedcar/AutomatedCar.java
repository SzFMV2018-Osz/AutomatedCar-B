package hu.oe.nik.szfmv.automatedcar;

import hu.oe.nik.szfmv.automatedcar.bus.VirtualFunctionBus;
import hu.oe.nik.szfmv.automatedcar.bus.packets.PositionPacket;
import hu.oe.nik.szfmv.automatedcar.bus.packets.VelocityPacket;
import hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces.IReadOnlyDashboardPacket;
import hu.oe.nik.szfmv.automatedcar.engine.BrakingForces;
import hu.oe.nik.szfmv.automatedcar.engine.TurningHandler;
import hu.oe.nik.szfmv.automatedcar.sensor.Camera;
import hu.oe.nik.szfmv.automatedcar.sensor.radar.Radar;
import hu.oe.nik.szfmv.automatedcar.systemcomponents.*;
import hu.oe.nik.szfmv.environment.worldobjectclasses.Car;

import java.util.Arrays;
import java.util.List;

public class AutomatedCar extends Car {

    private final VirtualFunctionBus virtualFunctionBus = new VirtualFunctionBus();
    private final double wheelRadius = 0.33;
    private double timeFrame = 0.041666667;
    private VelocityPacket velocityPacket = new VelocityPacket();
    private PositionPacket positionPacket = new PositionPacket();
    private InputManager inputManager;
    private DashboardManager dashboardManager;
    private PowertrainSystem powertrainSystem;
    private SteeringSystem steeringSystem;
    private Camera camera;
    private Radar radar;
    private ACC automat;
    private TurningHandler turningHandler;
    private double[] orientation;

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
        orientation = new double[]{0, -1};
        inputManager = new InputManager(virtualFunctionBus);
        virtualFunctionBus.velocityPacket = velocityPacket;
        virtualFunctionBus.positionPacket = positionPacket;
        positionPacket.setPostion(new double[]{x, y});
        positionPacket.setRotation(rotation);
        powertrainSystem = new PowertrainSystem(virtualFunctionBus);
        dashboardManager = new DashboardManager(virtualFunctionBus);
        steeringSystem = new SteeringSystem(virtualFunctionBus);
        camera = new Camera(virtualFunctionBus);
        radar = new Radar(virtualFunctionBus);
        automat = new ACC();
        turningHandler = new TurningHandler();
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
        double[] sumForces = calculateSummedForces();
        double[] acceleration = new double[]{sumForces[0] / 1500, sumForces[1] / 1500};
        System.out.println("acceleration" + acceleration[0] + "   " + acceleration[1]);
        double[] velocity = new double[]{velocityPacket.getVelocity()[0] + (timeFrame * acceleration[0]),
                velocityPacket.getVelocity()[1] + (timeFrame * acceleration[1])};
        velocityPacket.setVelocity(velocity);
        x += timeFrame * velocity[0] * 50;
        y += timeFrame * velocity[1] * 50;
        rotation += virtualFunctionBus.steeringPacket.getAngularSpeed();
        System.out.println("rotation: " + rotation);
        positionPacket.setPostion(new double[]{x, y});
        positionPacket.setRotation(rotation);
    }

    private double[] calculateSummedForces() {
        orientation = turningHandler.angularVector(orientation, virtualFunctionBus.steeringPacket.getAngularSpeed());
        double[] tractionForce = powertrainSystem.calculateTractionForce(orientation);
        System.out.println("orientation" + orientation[0] + "    " + orientation[1]);
        System.out.println("traction: " + tractionForce[0] + " " + tractionForce[1]);
        double[] brakeForce = BrakingForces.calcBrakeForceVector(velocityPacket.getVelocity()[0],
                velocityPacket.getVelocity()[1], virtualFunctionBus.brakePedalPacket.getPedalPosition());
        double[] airResistance = BrakingForces.calcAirResistanceVector(velocityPacket.getVelocity()[0],
                velocityPacket.getVelocity()[1]);
        double[] rollingResistance = BrakingForces.calcRollingResistanceVector(velocityPacket.getVelocity()[0],
                velocityPacket.getVelocity()[1]);
        List<double[]> forces;
        forces = Arrays.asList(tractionForce, brakeForce, airResistance, rollingResistance);
        double[] sumForces = sumForceVectors(forces);
        return sumForces;
    }

    private double[] sumForceVectors(List<double[]> forces) {
        double[] summedForces = new double[]{0, 0};
        for (double[] force : forces) {
            summedForces[0] += force[0];
            summedForces[1] += force[1];
        }
        return summedForces;
    }
}