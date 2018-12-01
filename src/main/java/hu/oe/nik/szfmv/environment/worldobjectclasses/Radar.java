package hu.oe.nik.szfmv.environment.worldobjectclasses;

public class Radar extends NonCollidable {

    public Radar(int x, int y, String imageFileName) {
        super(x, y, imageFileName);
    }

    public void move(int x, int y, float rotation) {
        this.x = x;
        this.y = y;
        this.rotation = rotation;
    }


}
