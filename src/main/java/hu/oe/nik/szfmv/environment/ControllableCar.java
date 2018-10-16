package hu.oe.nik.szfmv.environment;

import hu.oe.nik.szfmv.environment.worldobjectclasses.Car;

public class ControllableCar extends Car {


    /**
     * Creates an object of the virtual world on the given coordinates with the given image.
     *
     * @param x             the initial x coordinate of the object
     * @param y             the initial y coordinate of the object
     * @param imageFileName the filename of the image representing the object in the virtual world
     */
    public ControllableCar(int x, int y, String imageFileName) {
        super(x, y, imageFileName);
    }

    @Override
    public int getX() {
        return super.getX();
    }

    @Override
    public int getY() {
        return super.getY();
    }

    @Override
    public int getWidth() {
        return super.getWidth();
    }

    @Override
    public int getHeight() {
        return super.getHeight();
    }

    @Override
    public float getRotation() {
        return super.getRotation();
    }

    @Override
    public String getImageFileName() {
        return super.getImageFileName();
    }

    @Override
    public void setX(int x) {
        super.setX(x);
    }

    @Override
    public void setY(int y) {
        super.setY(y);
    }

    @Override
    public void setWidth(int width) {
        super.setWidth(width);
    }

    @Override
    public void setHeight(int height) {
        super.setHeight(height);
    }

    @Override
    public void setRotation(float rotation) {
        super.setRotation(rotation);
    }

    @Override
    public void setImageFileName(String imageFileName) {
        super.setImageFileName(imageFileName);
    }
}