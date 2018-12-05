package hu.oe.nik.szfmv.automatedcar;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import hu.oe.nik.szfmv.automatedcar.bus.VirtualFunctionBus;
import hu.oe.nik.szfmv.automatedcar.bus.packets.PositionPacket;
import hu.oe.nik.szfmv.automatedcar.bus.packets.VelocityPacket;
import hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces.IReadOnlyControlsPacket;
import hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces.IReadOnlyDashboardPacket;
import hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces.IReadonlyDisplayableSensorPacket;
import hu.oe.nik.szfmv.automatedcar.engine.BrakingForces;
import hu.oe.nik.szfmv.automatedcar.engine.TurningHandler;
import hu.oe.nik.szfmv.automatedcar.sensor.Camera;
import hu.oe.nik.szfmv.automatedcar.sensor.radar.Radar;
import hu.oe.nik.szfmv.automatedcar.systemcomponents.DashboardManager;
import hu.oe.nik.szfmv.automatedcar.systemcomponents.Driver;
import hu.oe.nik.szfmv.automatedcar.systemcomponents.InputManager;
import hu.oe.nik.szfmv.automatedcar.systemcomponents.PowertrainSystem;
import hu.oe.nik.szfmv.automatedcar.systemcomponents.SteeringSystem;
import hu.oe.nik.szfmv.environment.World;
import hu.oe.nik.szfmv.environment.WorldObject;
import hu.oe.nik.szfmv.environment.worldobjectclasses.Car;
import hu.oe.nik.szfmv.environment.worldobjectclasses.ParkingSpot;

public class AutomatedCar extends Car {

    private final VirtualFunctionBus virtualFunctionBus = new VirtualFunctionBus();
    private final double speedMetersPerSeconds;
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
    private TurningHandler turningHandler;
    private double[] orientation;
    private int axisx, axisy;
    private float axisrotation;
    private List<WorldObject> worldObjects = new ArrayList<>();
    private List<WorldObject> cameraObjects = new ArrayList<>();
    private List<WorldObject> radarObjects = new ArrayList<>();
    private List<Point> parkingspotss = new ArrayList<>();
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
        axisx = x;
        axisy = y;
        axisrotation = 0;
        positionPacket.setPostion(new double[] { x, y });
        positionPacket.setRotation(rotation);
        powertrainSystem = new PowertrainSystem(virtualFunctionBus);
        dashboardManager = new DashboardManager(virtualFunctionBus);
        steeringSystem = new SteeringSystem(virtualFunctionBus);
        speedMetersPerSeconds = 0;
        camera = new Camera(virtualFunctionBus);
        radar = new Radar(virtualFunctionBus);
        turningHandler = new TurningHandler();
        new Driver(virtualFunctionBus);

    }

    public AutomatedCar(final int x, final int y, final String imageFileName, List<WorldObject> worldObjects) {
        super(x, y, imageFileName);
        orientation = new double[]{0, -1};
        inputManager = new InputManager(virtualFunctionBus);
        virtualFunctionBus.velocityPacket = velocityPacket;
        virtualFunctionBus.positionPacket = positionPacket;
        axisx = x;
        axisy = y;
        axisrotation = 0;
        positionPacket.setPostion(new double[] { x, y });
        positionPacket.setRotation(rotation);
        powertrainSystem = new PowertrainSystem(virtualFunctionBus);
        dashboardManager = new DashboardManager(virtualFunctionBus);
        steeringSystem = new SteeringSystem(virtualFunctionBus);
        speedMetersPerSeconds = 0;
        camera = new Camera(virtualFunctionBus);
        radar = new Radar(virtualFunctionBus);
        turningHandler = new TurningHandler();
        new Driver(virtualFunctionBus);
        this.worldObjects = worldObjects;

    }

    /**
     * Provides a sample method for modifying the position of the car.
     */
    public void drive() {
        dashboardManager.actualisePosition(x, y);
        virtualFunctionBus.loop();
        calculatePositionAndOrientation();
        if (virtualFunctionBus.indicationPacket.getIndicatorDirection() == 1)
        {
            this.cameraObjects = this.camera.findWorldObjectsInRadarTriangle(worldObjects);
            this.radarObjects = this.radar.findWorldObjectsInRadarTriangle(worldObjects);

            parkingspotss.add(new Point(535, 902));
            parkingspotss.add(new Point(535, 1202));
            parkingspotss.add(new Point(535, 1502));
            parkingspotss.add(new Point(535, 1817));

            for(int i=0; i< radarObjects.size(); i++)
            {
                System.out.print(radarObjects.get(i).getImageFileName());
                if(radarObjects.get(i).getClass() == ParkingSpot.class) {
                    System.out.print(this.radarObjects.get(i).getImageFileName());

                }
            }
            if(!parkingspotss.isEmpty())
            {
                double[] velocity = new double[] { 0, 0 };
                Point parkingspace = best_parking_spot(parkingspotss);
                this.setX(parkingspace.x);
                this.setY(parkingspace.y);
                this.setRotation(6.30f);
                this.velocityPacket.setVelocity(velocity);
            }
        }
    }

    public double distanceCalc(int x, int y)
    {
        return Math.sqrt(Math.pow(Math.abs(this.getX() - x), 2) + Math.pow(Math.abs(this.getY()-y), 2));
    }

    public Point best_parking_spot(List<Point> parkingspots){
        List<Double> distances = new ArrayList<Double>();
        for(int i = 0; i < parkingspots.size(); i++)
        {
            distances.add(distanceCalc(parkingspots.get(i).x, parkingspots.get(i).y));
        }

        int index = distances.indexOf(Collections.min(distances));

        return parkingspots.get(index);
    }

    /**
     * Return dashboard data to caller
     *
     * @return - returns the packet containing the necessary information
     */
    public IReadOnlyDashboardPacket getDashboardPacket() {
        return virtualFunctionBus.dashboardPacket;
    }

    /**
     * Return controls data to caller
     *
     * @return - returns the packet containing the necessary information
     */
    public IReadOnlyControlsPacket getControlsPacket() {
        return virtualFunctionBus.controlsPacket;
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
        axisrotation += virtualFunctionBus.steeringPacket.getAngularSpeed();
        double[] velocity = calcVelocity();
        System.out.println("velocety" + velocity[0] + "     " + velocity[1]);
        velocityPacket.setVelocity(velocity);
        int plusx = (int) Math.round(timeFrame * velocity[0] * 50 * orientation[0]);
        int plusy = (int) Math.round(timeFrame * velocity[1] * 50 * orientation[1]);
        axisx += plusx;
        axisy += plusy;
        calcXAndY();
        positionPacket.setRotation(axisrotation);
        positionPacket.setPostion(new double[]{axisx, axisy});
    }

    private void calcXAndY() {
        x = axisx - 45;
        y = axisy + 84;
        rotation -= virtualFunctionBus.steeringPacket.getAngularSpeed();
    }

    private double[] calcVelocity() {
        double[] sumForces = calculateSummedForces();
        double[] acceleration = new double[]{sumForces[0] / 1500, sumForces[1] / 1500};
        return new double[]{(velocityPacket.getVelocity()[0]) + (timeFrame * acceleration[0]),
                velocityPacket.getVelocity()[1] + (timeFrame * acceleration[1])};
    }

    private double[] calculateSummedForces() {
        orientation = turningHandler.angularVector(orientation, virtualFunctionBus.steeringPacket.getAngularSpeed());
        double[] tractionForce = powertrainSystem.calculateTractionForce();
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