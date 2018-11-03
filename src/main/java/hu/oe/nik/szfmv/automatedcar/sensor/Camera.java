package hu.oe.nik.szfmv.automatedcar.sensor;

import hu.oe.nik.szfmv.environment.WorldObject;

import java.util.ArrayList;
import java.util.List;

public class Camera {

    /**
     * @param triangle the view triangle of the camera
     * @param worldObjects the list of world objects to scan through
     * @return the list of world objects that are inside the view of the camera
     */
    public List<WorldObject> getCameraVisibleObjects(Triangle triangle, List<WorldObject> worldObjects) {
        List<WorldObject> inTriangleList = new ArrayList<>();
        for (WorldObject wordObject : worldObjects) {
            if (triangle.isInTheTriangle(wordObject.getX(), wordObject.getY())) {
                inTriangleList.add(wordObject);
            }
        }
        return inTriangleList;
    }
}
