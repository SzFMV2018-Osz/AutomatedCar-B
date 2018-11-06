package hu.oe.nik.szfmv.environment.worldobjectclasses;

import hu.oe.nik.szfmv.common.Utils;

public class Human extends Movable {
    /**
     * Creates an object of the virtual world on the given coordinates with the given image.
     *
     * @param x             the initial x coordinate of the object
     * @param y             the initial y coordinate of the object
     * @param imageFileName the filename of the image representing the object in the virtual world
     */

    private float[][] script;
    private int globalI = 0;

    public Human(int x, int y, String imageFileName) {

        super(x, y, imageFileName);
        this.script = Utils.readScript("man_script.csv");
    }

    public void move()
    {
        if(globalI > 334)
        {
            globalI = 0;
        }
        this.x = (int)script[globalI][0];
        this.y = (int)script[globalI][1];
        this.setRotation(script[globalI][2]);
        globalI++;
    }

}
