package hu.oe.nik.szfmv.environment.worldobjectclasses;

public class Bicycle extends Movable {
    /**
     * Creates an object of the virtual world on the given coordinates with the given image.
     *
     * @param x             the initial x coordinate of the object
     * @param y             the initial y coordinate of the object
     * @param imageFileName the filename of the image representing the object in the virtual world
     */
    public Bicycle(int x, int y, String imageFileName) {
        super(x, y, imageFileName);
    }
}
