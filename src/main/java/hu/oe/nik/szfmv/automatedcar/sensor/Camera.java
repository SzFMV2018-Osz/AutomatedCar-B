package hu.oe.nik.szfmv.automatedcar.sensor;

import hu.oe.nik.szfmv.environment.WorldObject;

import java.util.ArrayList;
import java.util.List;

public class Camera implements ISensor {

    private static final int VISUAL_RANGE = 80;
    private static final int HAROMSZAZHATVAN = 80;
    private static final int METER_PIXEL_RATIO = 50;
    // 60°
    private static final double ANGLE_OF_VIEW = 60f;
    private static double TRIANGULAR_STEM;
    // camera position
    private double x;
    private double y;

    // another 2 point of the triangle
    private double[] triangleAPoint;
    private double[] triangleBPoint;

    private double[] facingDirection;

    /**
     * @param x               x coordinate
     * @param y               y coordinate
     * @param facingDirection facing direction
     */
    public Camera(int x, int y, double[] facingDirection) {
        this.x = x;
        this.y = y;
        this.facingDirection = facingDirection;

        triangleAPoint = new double[2];
        triangleBPoint = new double[2];

        double m = VISUAL_RANGE;
        double a = m * (
                Math.sin(Math.toRadians(ANGLE_OF_VIEW / 2)) / (Math.sin(Math.toRadians(ANGLE_OF_VIEW))));
        TRIANGULAR_STEM = Math.sqrt(Math.pow(a, 2) + Math.pow(m, 2));

        calculateTriangleOfView();
    }

    /**
     * @return returns the x coordinate of the camera position
     */
    public double getX() {
        return x;
    }

    /**
     * @return returns the y coordinate of the camera position
     */
    public double getY() {
        return y;
    }

    /**
     * @return returns one point of the camera view triangle
     */
    public double[] getTriangleAPoint() {
        return triangleAPoint;
    }

    /**
     * @return returns the other point of the camera view triangle
     */
    public double[] getTriangleBPoint() {
        return triangleBPoint;
    }

    /**
     * @param position        position
     * @param facingDirection facingDirection
     */
    public void updatePosition(double[] position, double[] facingDirection) {
        // get the new position and calculate the new triangle
        this.facingDirection = facingDirection;
        x = position[0];
        y = position[1];
        calculateTriangleOfView();
    }

    /**
     * Calculates the current view triangle of the camera
     */
    private void calculateTriangleOfView() {
        double[] shiftVector = calculatedDirectionVectorWithRotationMatrix(ANGLE_OF_VIEW / 2);
        shiftVector[0] = (shiftVector[0] * TRIANGULAR_STEM + x) / METER_PIXEL_RATIO;
        shiftVector[1] = (shiftVector[1] * TRIANGULAR_STEM + y) / METER_PIXEL_RATIO;
        triangleAPoint = shiftVector;

        shiftVector = calculatedDirectionVectorWithRotationMatrix(HAROMSZAZHATVAN - ANGLE_OF_VIEW / 2);
        shiftVector[0] = (shiftVector[0] * TRIANGULAR_STEM + x) / METER_PIXEL_RATIO;
        shiftVector[1] = (shiftVector[1] * TRIANGULAR_STEM + y) / METER_PIXEL_RATIO;
        triangleBPoint = shiftVector;
    }

    /**
     * @param angle where the camera looks
     * @return the direction vector
     */
    private double[] calculatedDirectionVectorWithRotationMatrix(double angle) {
        angle = angle % HAROMSZAZHATVAN;
        // transformation matrix
        return new double[]{
                facingDirection[0] * Math.cos(angle) + facingDirection[1] * (Math.sin(angle)),
                facingDirection[0] * (-Math.sin(angle)) + facingDirection[1] * Math.cos(angle)
        };
    }

    /**
     * @param worldObjects list of world objects to sort though
     * @return list of world objects that are inside the view of the camera
     */
    public List<WorldObject> findWorldObjectsInRadarTriangle(List<WorldObject> worldObjects) {
        Triangle triangle = new Triangle(VISUAL_RANGE * METER_PIXEL_RATIO,
                0 , x, y);

        triangle.a0x = x;
        triangle.a0y = y;
        triangle.a1x = triangleAPoint[0];
        triangle.a1y = triangleAPoint[1];
        triangle.a2x = triangleBPoint[0];
        triangle.a2y = triangleBPoint[1];

        List<WorldObject> inTriangleList = new ArrayList<>();
        for (WorldObject wordObject : worldObjects) {
            if (triangle.isInTheTriangle(wordObject.getX(), wordObject.getY())) {
                inTriangleList.add(wordObject);
            }
        }
        return inTriangleList;
    }

    @Override
    public List<WorldObject> getCollidableWorldObjectsFromArea(int x1, int x2, int x3) {
        return null;
    }

    @Override
    public List<WorldObject> getNonCollidableWorldObjectsFromArea(int x1, int x2, int x3) {
        return null;
    }
}
