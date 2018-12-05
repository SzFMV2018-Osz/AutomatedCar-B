package hu.oe.nik.szfmv.environment.worldobjectclasses;

import hu.oe.nik.szfmv.automatedcar.bus.packets.VelocityPacket;
import hu.oe.nik.szfmv.common.Utils;
import hu.oe.nik.szfmv.visualization.CourseDisplay;

public abstract class Movable extends Collidable {

    protected int lastX;
    protected int lastY;

    /**
     * Creates an object of the virtual world on the given coordinates with the given image.
     *
     * @param x             the initial x coordinate of the object
     * @param y             the initial y coordinate of the object
     * @param imageFileName the filename of the image representing the object in the virtual world
     */
    private VelocityPacket velocityPacket = new VelocityPacket();
    public Movable(int x, int y, String imageFileName) {
        super(x, y, imageFileName);
    }

    // Mi a fenéért nem volt alapból egy ilyen metódus, ha egyszer astract class? :D
    // AutomatedCar -ban is ebből kell hívni a drive-t és kész
    public abstract void move();

    protected void updateLastPosition(){
        lastX = x;
        lastY = y;
    }

    public double getSpeed() {
        return calculateSpeed(this);
    }

    private double calculateSpeed(Movable movable) {

        System.out.print("lastX: " + lastX + ", LastY: " + lastY + ", X: " + x + ", Y: " + y + ", ");
        double distanceTraveled = Utils.convertPixelToMeter((int) Math.sqrt(Math.pow(x - lastX, 2) + Math.pow(y - lastY, 2)));
        System.out.print("Distance traveled in this frame: " + distanceTraveled + " m");
        double currentSpeed = distanceTraveled / ((double)(1000))/ CourseDisplay.cyclePeriodCONSTANST * 1000 * 60 * 60;
        System.out.println(", current Speed: " + currentSpeed);
        return currentSpeed;
    }
}
