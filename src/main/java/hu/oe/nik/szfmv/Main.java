package hu.oe.nik.szfmv;

import hu.oe.nik.szfmv.automatedcar.AutomatedCar;
import hu.oe.nik.szfmv.common.ConfigProvider;
import hu.oe.nik.szfmv.environment.World;
import hu.oe.nik.szfmv.visualization.Gui;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int CYCLE_PERIOD = 40;

    private static int worldWidth = 800;
    private static int worldHeight = 600;
    private static int carPosX = 20;
    private static int carPosY = 20;

    /**
     * Main entrypoint of the software
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {

        // log the current debug mode in config
        LOGGER.info(ConfigProvider.provide().getBoolean("general.debug"));

        // create the world
        World w = new World(worldWidth, worldHeight);
        // create an automated car
        AutomatedCar car = new AutomatedCar(carPosX, carPosY, "car_2_white.png");
        // add car to the world
        w.addObjectToWorld(car);

        // create gui
        Gui gui = new Gui();

        // draw world to course display
        gui.getCourseDisplay().drawWorld(w);

        while (true) {
            try {
                car.drive();
                gui.getCourseDisplay().drawWorld(w);
                Thread.sleep(CYCLE_PERIOD);
            } catch (InterruptedException e) {
                LOGGER.error(e.getMessage());
            }
        }

    }
}
