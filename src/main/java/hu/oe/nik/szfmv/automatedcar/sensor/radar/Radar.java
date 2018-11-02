package hu.oe.nik.szfmv.automatedcar.sensor.radar;

import hu.oe.nik.szfmv.automatedcar.sensor.Triangle;
import hu.oe.nik.szfmv.environment.WorldObject;

import java.util.ArrayList;
import java.util.List;

public class Radar {

    Triangle triangle;

    /**
     * find world object in radar triangle
     *
     * @param worldObjects list of world objects
     * @return These are in triangle
     */
    public List<WorldObject> finWorldObjectsInRadarTriangle(List<WorldObject> worldObjects) {
        List<WorldObject> inTriangleList = new ArrayList<>();
        for (WorldObject wordObject : worldObjects) {
            if (triangle.isInTheTriangle(wordObject.getX(), wordObject.getY())) {
                inTriangleList.add(wordObject);
            }
        }
        return inTriangleList;
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

}
