package hu.oe.nik.szfmv.automatedcar.sensor;

import hu.oe.nik.szfmv.common.enums.Coordinate;
import hu.oe.nik.szfmv.environment.World;
import hu.oe.nik.szfmv.environment.WorldObject;
import hu.oe.nik.szfmv.environment.worldobjectclasses.Collidable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.awt.*;

public class UltraSoundSensor extends SensorBase implements ISensor {
    public UltraSoundSensor(List<WorldObject> worldObjects, Point coordinate) {
        super(worldObjects, coordinate, SensorType.Ultrasound);
    }

    @Override
    public List<WorldObject> getCollidableWorldObjectsFromArea(Point x1, Point x2, Point x3) {
        double triangleArea = calculateAreaFromCoordinates(x1, x2, x3);
        List<WorldObject> collidableObjectsInsideTriangle = new ArrayList<>();
        for (WorldObject worldObject: getWorldObjects()
             ) {
            if (worldObject.getClass() == Collidable.class){
                Point worldObjectCoordinate_1 = new Point(worldObject.getX(), worldObject.getY());
                Point worldObjectCoordinate_2 = new Point(worldObject.getX() + worldObject.getWidth(), worldObject.getY());
                Point worldObjectCoordinate_3 = new Point(worldObject.getX() + worldObject.getWidth(), worldObject.getY() - worldObject.getHeight());
                Point worldObjectCoordinate_4 = new Point(worldObject.getX(), worldObject.getY() - worldObject.getHeight());

                if (isPointInsideArea(worldObjectCoordinate_1, x1, x2, x3) ||
                    isPointInsideArea(worldObjectCoordinate_2, x1, x2, x3) ||
                    isPointInsideArea(worldObjectCoordinate_3, x1, x2, x3) ||
                    isPointInsideArea(worldObjectCoordinate_4, x1, x2, x3)){
                    collidableObjectsInsideTriangle.add(worldObject);
                }
                }
            }
        WorldObject closestObject = null;
        for (WorldObject worldObject: collidableObjectsInsideTriangle
             ) {
            if (distanceFromPoint(worldObject) < distanceFromPoint(closestObject)) {
                closestObject = worldObject;
            }
        }

        List<WorldObject> closesObjects = new ArrayList<>();
        closesObjects.add(closestObject);

        return closesObjects;
        }

    @Override
    public List<WorldObject> getNonCollidableWorldObjectsFromArea(Point x1, Point x2, Point x3) {
        throw new UnsupportedOperationException("UltraSound does not need nonCollidable objects");
    }

    // Calculates the area of a triangle
    private double calculateAreaFromCoordinates(Point x1, Point x2, Point x3){
        return Math.abs(x1.x*(x2.y - x3.y) + x2.x*(x3.y - x1.y) + x3.x*(x1.y - x2.y));
    }

    // Determines wether a given point (x) is inside of a triangle(x1,x2,x3)
    private boolean isPointInsideArea(Point x, Point x1, Point x2, Point x3){
        {

            double A = calculateAreaFromCoordinates(x1, x2, x3);

            double A1 = calculateAreaFromCoordinates(x, x2, x3);

            double A2 = calculateAreaFromCoordinates(x1, x, x3);

            double A3 = calculateAreaFromCoordinates(x1, x2, x);

            return (A == A1 + A2 + A3);
        }
    }

    // Returns the closest coordinate of a world object to the sensor
    private double distanceFromPoint(WorldObject worldObject) {
        if (worldObject == null) {
            return Integer.MAX_VALUE;
        }

        double distance1 = calculateDistance(new Point(worldObject.getX(), worldObject.getY()));
        double distance2 = calculateDistance(new Point(worldObject.getX() + worldObject.getWidth(), worldObject.getY()));
        double distance3 = calculateDistance(new Point(worldObject.getX() + worldObject.getWidth(), worldObject.getY() + worldObject.getHeight()));
        double distance4 = calculateDistance(new Point(worldObject.getX(), worldObject.getY() + worldObject.getHeight()));

        List<Double> distances = new ArrayList<>();
        distances.add(distance1);
        distances.add(distance2);
        distances.add(distance3);
        distances.add(distance4);

        return Collections.min(distances);
    }

    // Calculates the distance between a point and the sensor
    private double calculateDistance(Point coordinate) {
        return Math.sqrt((getPosition().getY() - coordinate.getY()) *
                (getPosition().getY() - coordinate.getY()) +
                (getPosition().getX() - coordinate.getX()) *
                        (getPosition().getX() - coordinate.getX()));
    }
}
