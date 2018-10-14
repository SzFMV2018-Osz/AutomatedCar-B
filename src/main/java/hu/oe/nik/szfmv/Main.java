package hu.oe.nik.szfmv;

import hu.oe.nik.szfmv.automatedcar.AutomatedCar;
import hu.oe.nik.szfmv.common.ConfigProvider;
import hu.oe.nik.szfmv.environment.World;
import hu.oe.nik.szfmv.environment.WorldObject;
import hu.oe.nik.szfmv.visualization.Gui;
import hu.oe.nik.szfmv.visualization.XMLReadClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger();
    public static ArrayList<WorldObject> Roads = new ArrayList<>();
    /**
     * Main entrypoint of the software
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {

        // log the current debug mode in config
        LOGGER.info(ConfigProvider.provide().getBoolean("general.debug"));

        // create the world
        World w = new World(800, 600);
        // create an automated car

        // AUTOMATED CAR RENDERING TESZT
        AutomatedCar car = new AutomatedCar(70, 200, "car_2_white.png");
        w.setAutomatedCar(car);
        /////////////////////////////////

        // add car to the world
//        WorldObject road = new WorldObject(0, 150, "road_2lane_45right.png");
//        road.setRotation((float)(Math.PI*0.6));
//        w.addObjectToWorld(road);
//
//        WorldObject asd = new WorldObject(1500, 50, "road_2lane_tjunctionleft.png");
//        road.setRotation((float)(Math.PI*1.3));
//        w.addObjectToWorld(asd);
        // create gui
        Gui gui = new Gui();
        XMLReadClass.loadRoads(gui, w);

        // draw world to course display
        gui.getCourseDisplay().drawWorld(w);

        while (true) {
            car.drive();

            gui.getCourseDisplay().drawWorld(w);
        }
    }
}
