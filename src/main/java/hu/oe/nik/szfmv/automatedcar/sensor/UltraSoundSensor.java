package hu.oe.nik.szfmv.automatedcar.sensor;

import hu.oe.nik.szfmv.environment.WorldObject;
import hu.oe.nik.szfmv.environment.worldobjectclasses.Collidable;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UltraSoundSensor extends SensorBase implements ISensor {
    /**
     * Constructor for the UltraSoundSensor class
     * @param worldObjects the list of the world objects needed for the sensor
     * @param coordinate the coordinate of the sensor
     */
    public UltraSoundSensor(List<WorldObject> worldObjects, Point coordinate) {
        super(worldObjects, coordinate, SensorType.Ultrasound);
    }

    @Override
    public List<WorldObject> getCollidableWorldObjectsFromArea(Point x1, Point x2, Point x3) {
        List<WorldObject> collidableObjectsInsideTriangle =
                getWorldObjectsWithinTriangle(new ArrayList<>(), x1, x2, x3);
        return getClosestObjects(collidableObjectsInsideTriangle);
    }

    @Override
    public List<WorldObject> getNonCollidableWorldObjectsFromArea(Point x1, Point x2, Point x3) {
        throw new UnsupportedOperationException("UltraSound does not need nonCollidable objects");
    }

    // Calculates the area of a triangle
    private double calculateAreaFromCoordinates(Point x1, Point x2, Point x3) {
        return Math.abs(x1.x * (x2.y - x3.y) + x2.x * (x3.y - x1.y) + x3.x * (x1.y - x2.y));
    }

    // Determines wether a given point (x) is inside of a triangle(x1,x2,x3)
    private boolean isPointInsideArea(Point x, Point x1, Point x2, Point x3) {
        {

            double a = calculateAreaFromCoordinates(x1, x2, x3);

            double a1 = calculateAreaFromCoordinates(x, x2, x3);

            double a2 = calculateAreaFromCoordinates(x1, x, x3);

            double a3 = calculateAreaFromCoordinates(x1, x2, x);

            return (a == a1 + a2 + a3);
        }
    }

    // Returns the closest coordinate of a world object to the sensor
    private double distanceFromPoint(WorldObject worldObject) {
        if (worldObject == null) {
            return Integer.MAX_VALUE;
        }
        int x = worldObject.getX();
        int y = worldObject.getY();
        double distance1 = calculateDistance(new Point(x, y));
        double distance2 = calculateDistance(new Point(x + worldObject.getWidth(), y));
        double distance3 = calculateDistance(new Point(x + worldObject.getWidth(), y + worldObject.getHeight()));
        double distance4 = calculateDistance(new Point(x, y + worldObject.getHeight()));

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

    private List<WorldObject> getClosestObjects(List<WorldObject> collidableObjectsInsideTriangle) {
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

    private List<WorldObject> getWorldObjectsWithinTriangle(
            List<WorldObject> collidableObjectsInsideTriangle, Point x1, Point x2, Point x3) {
        for (WorldObject worldObject: getWorldObjects()
        ) {
            int x = worldObject.getX();
            int y = worldObject.getY();
            if (worldObject.getClass() == Collidable.class) {
                Point worldObjectCoordinate0 = new Point(x, y);
                Point worldObjectCoordinate1 = new Point(x + worldObject.getWidth(), y);
                Point worldObjectCoordinate2 = new Point(x + worldObject.getWidth(), y - worldObject.getHeight());
                Point worldObjectCoordinate3 = new Point(x, y - worldObject.getHeight());

                if (isPointInsideArea(worldObjectCoordinate0, x1, x2, x3) ||
                        isPointInsideArea(worldObjectCoordinate1, x1, x2, x3) ||
                        isPointInsideArea(worldObjectCoordinate2, x1, x2, x3) ||
                        isPointInsideArea(worldObjectCoordinate3, x1, x2, x3)) {
                    collidableObjectsInsideTriangle.add(worldObject);
                }
            }
        }

        return collidableObjectsInsideTriangle;
    }
}
