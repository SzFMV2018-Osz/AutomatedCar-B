package hu.oe.nik.szfmv.automatedcar.sensor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import hu.oe.nik.szfmv.automatedcar.bus.VirtualFunctionBus;
import hu.oe.nik.szfmv.automatedcar.bus.packets.CameraPacket;
import hu.oe.nik.szfmv.automatedcar.systemcomponents.SystemComponent;
import hu.oe.nik.szfmv.environment.WorldObject;
import hu.oe.nik.szfmv.environment.worldobjectclasses.RoadSign;

public class Camera extends SystemComponent implements ISensor {

    private static final int VISUAL_RANGE = 80;
    private static final int METER_PIXEL_RATIO = 50;
    // 60°
    private static final double ANGLE_OF_VIEW = 60f;

    private Triangle viewArea;

    private CameraPacket cameraPacket;

    /**
     * @param x               x coordinate
     * @param y               y coordinate
     * @param facingDirection facing direction
     */
    public Camera(VirtualFunctionBus virtualFunctionBus) {
        super(virtualFunctionBus);

        viewArea = new Triangle(VISUAL_RANGE * METER_PIXEL_RATIO, ANGLE_OF_VIEW,
                virtualFunctionBus.positionPacket.getPosition()[0], virtualFunctionBus.positionPacket.getPosition()[1]);

        cameraPacket = new CameraPacket();
        virtualFunctionBus.cameraPacket = cameraPacket;
    }

    /**
     * @param position        position
     * @param facingDirection facingDirection
     */
    public void updatePosition(double[] position, double rotation) {
        viewArea.calculateNextPosition(rotation, position[0], position[1]);
    }

    /**
     * @param worldObjects list of world objects to sort though
     * @return list of world objects that are inside the view of the camera
     */
    public List<WorldObject> findWorldObjectsInRadarTriangle(List<WorldObject> worldObjects) {
        return worldObjects.stream()
                .filter(worldObject -> viewArea.isInTheTriangle(worldObject.getX(), worldObject.getY()))
                .collect(Collectors.toList());
    }

    public Optional<WorldObject> findClosestRoadSign(List<WorldObject> objectsInRange) {
        List<WorldObject> roadSigns = objectsInRange.stream().filter(object -> object.getClass().equals(RoadSign.class))
                .collect(Collectors.toList());
        WorldObject closestRoadSign = selectClosestRoadSign(roadSigns);
        return Optional.ofNullable(closestRoadSign);
    }

    private WorldObject selectClosestRoadSign(List<WorldObject> roadSigns) {
        double minDinstace = Double.MAX_VALUE;
        WorldObject closestRoadSign = null;
        for (WorldObject roadSign : roadSigns) {
            double distance = calculateDistanceFromCamera(roadSign);
            if (distance < minDinstace) {
                closestRoadSign = roadSign;
                minDinstace = distance;
            }
        }
        return closestRoadSign;
    }

    private double calculateDistanceFromCamera(WorldObject object) {
        return Math.sqrt(Math.pow(virtualFunctionBus.positionPacket.getPosition()[0] - object.getX(), 2)
                + Math.pow(virtualFunctionBus.positionPacket.getPosition()[1] - object.getY(), 2));
    }

    @Override
    public List<WorldObject> getCollidableWorldObjectsFromArea(int x1, int x2, int x3) {
        return null;
    }

    @Override
    public List<WorldObject> getNonCollidableWorldObjectsFromArea(int x1, int x2, int x3) {
        return null;
    }

    @Override
    public void loop() {
        updatePosition(virtualFunctionBus.positionPacket.getPosition(),
                virtualFunctionBus.positionPacket.getRotation());
        List<WorldObject> objectsInView = findWorldObjectsInRadarTriangle(new ArrayList<>()); // TODO: lekerni az osszes
                                                                                              // worldobjectet
        Optional<WorldObject> closestRoadSign = findClosestRoadSign(objectsInView);
        if (closestRoadSign.isPresent()) {
            cameraPacket.setClosestRoadSign((RoadSign) closestRoadSign.get());
        }
    }
}
