package hu.oe.nik.szfmv.common.enums;

public class Coordinate {

    private int x;
    private int y;

    /**
     * Creates an instance which represents a point on the map
     *
     * @param x represents the width on the screen
     * @param y represents the height on the screen
     */
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double distanceTo(Coordinate c) {
        int dX = this.x - c.x;
        int dY = this.y - c.y;

        return Math.sqrt(Math.pow(dX, 2) + Math.pow(dY, 2));
    }
}
