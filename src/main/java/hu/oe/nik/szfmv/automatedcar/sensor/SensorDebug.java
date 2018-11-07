package hu.oe.nik.szfmv.automatedcar.sensor;

import hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers.ISensorDebugEventHandler;
import hu.oe.nik.szfmv.environment.World;
import hu.oe.nik.szfmv.environment.WorldObject;
import hu.oe.nik.szfmv.environment.worldobjectclasses.NonCollidable;
import hu.oe.nik.szfmv.environment.worldobjectclasses.ParkingSpot;

import java.util.ArrayList;
import java.util.List;


/**
 * This class can visually mark objects relevant to the Camera and Radar
 */
public class SensorDebug implements ISensorDebugEventHandler {
    private boolean isActive;

    WorldObject tagObject;

    public SensorDebug() {
        tagObject = new ParkingSpot(-1, -1, "car-icon.png");
    }

    /**
     *  Toggles the activation of the camera/radar debug mode
     */
    public void toggleActive() {
        isActive = !isActive;
        System.out.println("Debug mode is: " + isActive);

        /*
        if(isActive) {
            List<WorldObject> tags = new ArrayList<>();
            tags.add(tagObject);
            World.objects.addAll(tags);
        } else {
            World.objects.remove(tagObject);
        }
        */
    }

    /**
     * @return state of the debugger
     */
    public boolean isActive() {
        return this.isActive;
    }

    /**
     * Will tag a world object with some graphical thing
     * @param o the object to tag
     */
    public void tag(WorldObject o) {
        tagObject.setX(o.getX());
        tagObject.setY(o.getY());
    }

    @Override
    public void onSensorDebugToggle() {
        toggleActive();
    }
}
