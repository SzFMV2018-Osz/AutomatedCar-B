package hu.oe.nik.szfmv;

import hu.oe.nik.szfmv.automatedcar.AutomatedCar;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.UserInputProvider;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.enums.InputType;
import hu.oe.nik.szfmv.common.ConfigProvider;
import hu.oe.nik.szfmv.environment.World;
import hu.oe.nik.szfmv.visualization.Gui;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Main Class.
 */
public class Main {

    private static final Logger LOGGER = LogManager.getLogger();

    private static int worldWidth = 800;
    private static int worldHeight = 600;
    private static int worldWidth2 = 5120;
    private static int worldHeight2 = 3000;
    private static int carPosX = 20;
    private static int carPosY = 20;

    private static boolean simIsRunning = true;

    /**
     * Main entrypoint of the software.
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {

        // log the current debug mode in config
        LOGGER.info(ConfigProvider.provide().getBoolean("general.debug"));

        // debug section display toggle
        boolean dashboardDebugIsEnabled = ConfigProvider.provide().getBoolean("dashboard.debug");

        // create the world
        World w = new World(worldWidth, worldHeight);

        // create an automated car
        AutomatedCar car = new AutomatedCar(carPosX, carPosY, "car_2_white.png");
        w.setAutomatedCar(car);

        w.setWidth(worldWidth2);
        w.setHeight(worldHeight2);

        // create gui
        Gui gui = new Gui();
        gui.addKeyListener(UserInputProvider.getUserInput(InputType.Keyboard));

        // draw world to course display
        gui.getCourseDisplay().drawWorld(w);

        while (simIsRunning) {
            car.drive();

            gui.getCourseDisplay().drawWorld(w);
            gui.getDashboard().display(car.getDashboardPacket(), car.getControlsPacket(), dashboardDebugIsEnabled);
        }
    }
}