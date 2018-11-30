package hu.oe.nik.szfmv.automatedcar.sensor;

import hu.oe.nik.szfmv.environment.WorldObject;
import hu.oe.nik.szfmv.environment.worldobjectclasses.Collidable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.awt.*;

public class UltraSoundSensor extends SensorBase implements IUltraSoundSensor {

    /**
     * Constructor for the UltraSoundSensor class
     * @param worldObjects the list of the world objects needed for the sensor
     * @param coordinate the coordinate of the sensor
     */
    public UltraSoundSensor(List<WorldObject> worldObjects, Point coordinate, int viewDistance, float rotation) {
        super(worldObjects, coordinate, SensorType.Ultrasound, viewDistance, rotation);
    }


    public List<WorldObject> getCollidableWorldObjects() {
        List<WorldObject> collidableObjectsInsideTriangle = new ArrayList<WorldObject>();
        for (WorldObject worldObject : worldObjects){
            if(getTriangle().isInTheTriangle(worldObject.getX(), worldObject.getY())){
                collidableObjectsInsideTriangle.add(worldObject);
            }
        }

        return collidableObjectsInsideTriangle;
    }
}
