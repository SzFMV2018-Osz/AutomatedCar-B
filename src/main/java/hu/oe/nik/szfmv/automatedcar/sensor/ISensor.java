package hu.oe.nik.szfmv.automatedcar.sensor;

import hu.oe.nik.szfmv.environment.WorldObject;

import java.awt.*;
import java.util.List;

/**
 * Provides world objects needed for the sensors.
 */
public interface ISensor {

    /**
     * Method for providing collidable world objects from a specific area to the sensors
     * @param x1 first coordinate of the triangle area
     * @param x2 second coordinate of the triangle area
     * @param x3 third coordinate of the triangle area
     * @return the collidable world objects found in the specified area
     */
    List<WorldObject> getCollidableWorldObjectsFromArea(Point x1, Point x2, Point x3);

    /**
     * Method for providing  non-collidable world objects from a specific area to the sensors
     * @param x1 first coordinate of the triangle area
     * @param x2 second coordinate of the triangle area
     * @param x3 third coordinate of the triangle area
     * @return the non-collidable world objects found in the specified area
     */
    List<WorldObject> getNonCollidableWorldObjectsFromArea(Point x1, Point x2, Point x3);
}
