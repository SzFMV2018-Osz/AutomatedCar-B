package hu.oe.nik.szfmv.environment.worldobjectclasses;

import hu.oe.nik.szfmv.automatedcar.bus.packets.VelocityPacket;

public abstract class Movable extends Collidable {
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
}
