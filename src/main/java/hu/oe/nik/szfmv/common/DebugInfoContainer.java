package hu.oe.nik.szfmv.common;

public class DebugInfoContainer {
    private int xCar;
    private int yCar;

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