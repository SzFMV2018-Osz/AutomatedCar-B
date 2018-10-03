package hu.oe.nik.szfmv;

import hu.oe.nik.szfmv.automatedcar.AutomatedCar;
import hu.oe.nik.szfmv.common.ConfigProvider;
import hu.oe.nik.szfmv.environment.World;
import hu.oe.nik.szfmv.environment.WorldObject;
import hu.oe.nik.szfmv.visualization.Gui;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Calendar;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger();
    private static int CYCLE_PERIOD = 40;

    // Ezt az FPS-t szeretnénk tartani
    public static final int TARGET_FPS = 24;

    // Az aktuális renderelési és számítási ciklus kezdetének időpontja
    private static long cycle_start;
    // Az aktuális renderelési és számítási ciklus hossza
    private static long cycle_length;

    //Proba dlfaséjdf
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
        AutomatedCar car = new AutomatedCar(20, 20, "car_2_white.png");
        // add car to the world
        w.addObjectToWorld(car);

        // create gui
        Gui gui = new Gui();

        // draw world to course display
        gui.getCourseDisplay().drawWorld(w);

        Calendar cal = Calendar.getInstance();

        while (true) {
            try {
                cycle_start = cal.getTimeInMillis();
                car.drive();
                gui.getCourseDisplay().drawWorld(w);

                // FIX FPS
                cycle_length = cal.getTimeInMillis() - cycle_start;
                // Szükséges késleltetési idő kiszámítása (eltelt idő * TARGET FPS)
                CYCLE_PERIOD = (int)(1000 - (cycle_length * TARGET_FPS)) / TARGET_FPS;
                System.out.println("FPS/TARGET FPS: " + (1000/CYCLE_PERIOD) + "/" + TARGET_FPS);
                
                Thread.sleep(CYCLE_PERIOD);
            } catch (InterruptedException e) {
                LOGGER.error(e.getMessage());
            }
        }

    }
}
