package hu.oe.nik.szfmv.automatedcar.sensor.radar;

import hu.oe.nik.szfmv.automatedcar.bus.VirtualFunctionBus;
import hu.oe.nik.szfmv.automatedcar.bus.packets.RadarPacket;
import hu.oe.nik.szfmv.automatedcar.sensor.Triangle;
import hu.oe.nik.szfmv.automatedcar.systemcomponents.SystemComponent;
import hu.oe.nik.szfmv.environment.WorldObject;
import hu.oe.nik.szfmv.environment.worldobjectclasses.Collidable;

import java.util.ArrayList;
import java.util.List;

public class Radar extends SystemComponent {

    private static final int VISUAL_RANGE = 80;
    private static final int METER_PIXEL_RATIO = 50;
    // 60°
    private static final double ANGLE_OF_VIEW = 60f;

    private Triangle triangle;

    private RadarPacket radarPacket;

    /**
     * @param virtualFunctionBus
     */
    public Radar(VirtualFunctionBus virtualFunctionBus) {
        super(virtualFunctionBus);
        triangle = new Triangle(VISUAL_RANGE * METER_PIXEL_RATIO, ANGLE_OF_VIEW,
                virtualFunctionBus.positionPacket.getPosition()[0], virtualFunctionBus.positionPacket.getPosition()[1]);
        radarPacket = new RadarPacket();
        virtualFunctionBus.radarPacket = radarPacket;
    }

    /**
     * find world object in radar triangle
     *
     * @param worldObjects list of world objects
     * @return These are in triangle
     */
    public List<WorldObject> findWorldObjectsInRadarTriangle(List<WorldObject> worldObjects) {
        List<WorldObject> inTriangleList = new ArrayList<>();
        for (WorldObject worldObject : worldObjects) {
            if (worldObject instanceof Collidable && triangle.isInTheTriangle(worldObject.getX(), worldObject.getY())) {
                inTriangleList.add(worldObject);
            }
        }
        return inTriangleList;
    }

    public boolean isObjectInRadarTriangle(double x, double y){
        return triangle.isInTheTriangle(x, y);
    }

    /**
     * radar triangle follow the car
     *
     * @param rotation car rotation
     * @param p0x      car radar x coordinate
     * @param p0y      car radar y coordinate
     */
    public void updateTriangle(double rotation, double p0x, double p0y) {
        triangle.calculateNextPosition(rotation, p0x, p0y);
    }

    @Override
    public void loop() {
        updateTriangle(virtualFunctionBus.positionPacket.getRotation(),
                virtualFunctionBus.positionPacket.getPosition()[0], virtualFunctionBus.positionPacket.getPosition()[1]);
    }

}
