package hu.oe.nik.szfmv.automatedcar.sensor;

import hu.oe.nik.szfmv.common.Utils;

public class Triangle {

    private double a0x;
    private double a1x;
    private double a2x;
    private double a0y;
    private double a1y;
    private double a2y;
    private double distanceFromA0InPixel;
    private double a0Angle;
    private double distanceFromMidPointInPixel;


    /**
     * inicialize class
     *
     * @param distanceFromA0InPixel line from a0 to a1 and 2 mid point
     * @param a0Angle               a0 point's angle
     * @param a0x                   a0 x coordinate
     * @param a0y                   a0 y coordinate
     */
    public Triangle(double distanceFromA0InPixel, double a0Angle, double a0x, double a0y, double[] orientation) {
        this.distanceFromA0InPixel = distanceFromA0InPixel;
        this.a0Angle = a0Angle;
        this.a0x = a0x;
        this.a0y = a0y;
        this.distanceFromMidPointInPixel = calcDistanceFromMidPoint(a0Angle, distanceFromA0InPixel);
        this.calculateNextPosition(0, a0x, a0y, orientation);
    }

    private double calcDistanceFromMidPoint(double a0Angle, double distanceFromA0InPixel) {
        return Math.tan(a0Angle) * distanceFromA0InPixel;
    }

    public double getA0x() {
        return a0x;
    }

    public void setA0x(double a0x) {
        this.a0x = a0x;
    }

    public double getA1x() {
        return a1x;
    }

    public void setA1x(double a1x) {
        this.a1x = a1x;
    }

    public double getA2x() {
        return a2x;
    }

    public void setA2x(double a2x) {
        this.a2x = a2x;
    }

    public double getA0y() {
        return a0y;
    }

    public void setA0y(double a0y) {
        this.a0y = a0y;
    }

    public double getA1y() {
        return a1y;
    }

    public void setA1y(double a1y) {
        this.a1y = a1y;
    }

    public double getA2y() {
        return a2y;
    }

    public void setA2y(double a2y) {
        this.a2y = a2y;
    }

    /**
     * check element is in the triangle
     *
     * @param p0x element x coordinate
     * @param p0y element y coordinate
     * @return true if element is in the triangle
     */
    public boolean isInTheTriangle(double p0x, double p0y) {
        boolean d1;
        boolean d2;
        boolean d3;

        d1 = sign(p0x, p0y, a0x, a0y, a1x, a1y) <= 0d;
        d2 = sign(p0x, p0y, a1x, a1y, a2x, a2y) <= 0d;
        d3 = sign(p0x, p0y, a2x, a2y, a0x, a0y) <= 0d;

        return ((d1 == d2) && (d2 == d3));
    }


    private double sign(double p0x, double p0y, double p1x, double p1y, double p2x, double p2y) {
        return (p0x - p2x) * (p1y - p2y) - (p1x - p2x) * (p0y - p2y);
    }

    /**
     * calculate net points of triangle
     *
     * @param rotation triangle rotation
     * @param p0x      a0 x point of triangle
     * @param p0y      a0 y point of triangle
     */
    public void calculateNextPosition(double rotation, double p0x, double p0y, double[] orientation) {
        a0x = p0x - (50 * (orientation[1] / Math.abs(orientation[1])));
        a0y = p0y - ((104 + this.distanceFromA0InPixel) * (orientation[0] / Math.abs(orientation[0])));
        System.out.println(orientation[1] / Math.abs(orientation[1]));


        a1x = p0x + distanceFromA0InPixel;
        a1y = p0y + distanceFromMidPointInPixel;
        a2x = p0x + distanceFromA0InPixel;
        a2y = p0y - distanceFromMidPointInPixel;

        double[] point = Utils.rotationPointToOtherPoint(rotation, a0x, a0y, a1x, a1y);
        a1x = point[0];
        a1y = point[1];
        point = Utils.rotationPointToOtherPoint(rotation, a0x, a0y, a2x, a2y);
        a2x = point[0];
        a2y = point[1];


    }


}
