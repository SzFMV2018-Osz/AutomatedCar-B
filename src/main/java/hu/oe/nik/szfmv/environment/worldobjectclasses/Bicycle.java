package hu.oe.nik.szfmv.environment.worldobjectclasses;

import hu.oe.nik.szfmv.common.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Bicycle extends Movable {
    /**
     * Creates an object of the virtual world on the given coordinates with the given image.
     *
     * @param x             the initial x coordinate of the object
     * @param y             the initial y coordinate of the object
     * @param imageFileName the filename of the image representing the object in the virtual world
     */

    private float[][] coordinates;
    private int Gi = 0;

    public Bicycle(int x, int y, String imageFileName) {
        super(x, y, imageFileName);

        this.coordinates = ReadScript("bicycle_coordinates.csv");
        this.setRotation(1.55f);
    }

    public void move() {
        if(Gi > 916)
        {
            Gi = 0;
        }
        if (coordinates != null) {
            this.x = (int)(coordinates[Gi][0]);
            this.y = (int)(coordinates[Gi][1]);
            this.setRotation(coordinates[Gi][2]);
            Gi++;
        }
    }

    private  float[][] ReadScript(String path)
    {
        String fileName= "src" + File.separator + "main" + File.separator + "resources" + File.separator + path;
        File file= new File(fileName);
        int i = 0;
        float[][] script = new float[4000][3];
        try{
            Scanner inputStream= new Scanner(file);
            while(inputStream.hasNext()){
                String data= inputStream.next();
                String[] values = data.split(",");
                script[i][0] = Integer.parseInt(values[0]);
                script[i][1] = Integer.parseInt(values[1]);
                script[i][2] = Float.parseFloat(values[2]);
                i++;
            }
            inputStream.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return script;
    }
}