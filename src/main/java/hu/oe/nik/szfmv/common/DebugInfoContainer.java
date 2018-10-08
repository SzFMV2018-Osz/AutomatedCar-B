package hu.oe.nik.szfmv.common;

public class DebugInfoContainer {
    private final int xCar;
    private final int yCar;

    /**
     * Constructor for the debug info container object
     * @param xCar - the car's 'x' coordinate
     * @param yCar - the car's 'y' coordinate
     */
    public DebugInfoContainer(int xCar, int yCar) {
        this.xCar = xCar;
        this.yCar = yCar;
    }

    public int getCarX() {
        return xCar;
    }
    public int getCarY() {
        return yCar;
    }
}