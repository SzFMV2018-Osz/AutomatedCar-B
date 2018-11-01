package hu.oe.nik.szfmv.automatedcar.sensor;

import hu.oe.nik.szfmv.environment.WorldObject;

import java.util.List;

public class Camera implements ISensor {

    private final static int VISUAL_RANGE = 80;
    private final static int METER_PIXEL_RATIO = 50;
    // 60°
    private final static double ANGLE_OF_VIEW = 60f;
    private static double TRIANGULAR_STEM;
    // camera position
    private double x;
    private double y;

    // another 2 point of the triangle
    private double[] triangleAPoint;
    private double[] triangleBPoint;

    private double[] facingDirection;

    public Camera(int x, int y, double[] facingDirection) {
        this.x = x;
        this.y = y;
        this.facingDirection = facingDirection;

        triangleAPoint = new double[2];
        triangleBPoint = new double[2];

        double m = VISUAL_RANGE;
        double a = m * (
                Math.toRadians(Math.sin(ANGLE_OF_VIEW / 2)) / Math.toRadians(Math.sin(ANGLE_OF_VIEW)));
        TRIANGULAR_STEM = Math.sqrt(Math.pow(a, 2) + Math.pow(m, 2));

        calculateTriangleOfView();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double[] getTriangleAPoint() {
        return triangleAPoint;
    }

    public double[] getTriangleBPoint() {
        return triangleBPoint;
    }

    public void updatePosition(double[] position, double[] facingDirection) {
        // get the new position and calculate the new triangle
        this.facingDirection = facingDirection;
        x = position[0];
        y = position[1];
        calculateTriangleOfView();
    }

    private void calculateTriangleOfView() {
        double[] shiftVector = calculatedDirectionVectorWithRotationMatrix(ANGLE_OF_VIEW / 2);
        shiftVector[0] = (shiftVector[0] * TRIANGULAR_STEM + x) / METER_PIXEL_RATIO;
        shiftVector[1] = (shiftVector[1] * TRIANGULAR_STEM + y) / METER_PIXEL_RATIO;
        triangleAPoint = shiftVector;

        shiftVector = calculatedDirectionVectorWithRotationMatrix(360 - ANGLE_OF_VIEW / 2);
        shiftVector[0] = (shiftVector[0] * TRIANGULAR_STEM + x) / METER_PIXEL_RATIO;
        shiftVector[1] = (shiftVector[1] * TRIANGULAR_STEM + y) / METER_PIXEL_RATIO;
        triangleBPoint = shiftVector;
    }

    private double[] calculatedDirectionVectorWithRotationMatrix(double angle) {
        angle = angle % 360;
        // transformation matrix
        return new double[]{
                facingDirection[0] * Math.cos(angle) + facingDirection[1] * (Math.sin(angle)),
                facingDirection[0] * (-Math.sin(angle)) + facingDirection[1] * Math.cos(angle)
        };
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
