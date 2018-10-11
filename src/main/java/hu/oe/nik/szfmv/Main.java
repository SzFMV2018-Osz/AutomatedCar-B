package hu.oe.nik.szfmv;

import hu.oe.nik.szfmv.automatedcar.AutomatedCar;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.UserInputProvider;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.enums.InputType;
import hu.oe.nik.szfmv.common.ConfigProvider;
import hu.oe.nik.szfmv.common.DebugInfoContainer;
import hu.oe.nik.szfmv.environment.World;
import hu.oe.nik.szfmv.visualization.Gui;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int CYCLE_PERIOD = 40;

    /**
     * Main entrypoint of the software
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {

        // log the current debug mode in config
        LOGGER.info(ConfigProvider.provide().getBoolean("general.debug"));

        // debug info toggle for future modularity
        boolean debugInfoIsEnabled = ConfigProvider.provide().getBoolean("dashboard.debug");

        // create the world
        World w = new World(800, 600);
        // create an automated car
        AutomatedCar car = new AutomatedCar(20, 20, "car_2_white.png");
        // add car to the world
        w.addObjectToWorld(car);

        // create gui
        Gui gui = new Gui();
        gui.addKeyListener(UserInputProvider.getUserInput(InputType.Keyboard));

        // draw world to course display
        gui.getCourseDisplay().drawWorld(w);

        while (true) {
            try {
                car.drive();
                gui.getCourseDisplay().drawWorld(w);
              
                debugDisplayer(debugInfoIsEnabled , car, gui);

                gui.getDashboard().display(car.getDashboardInfo());
              
                Thread.sleep(CYCLE_PERIOD);
            } catch (InterruptedException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

    private static void debugDisplayer(boolean debugInfoIsEnabled, AutomatedCar car, Gui gui) {
        if (debugInfoIsEnabled) {
            DebugInfoContainer debugInfoContainer = new DebugInfoContainer(car.getX(), car.getY());
            gui.getDashboard().drawDashboardDebugDisplay(debugInfoContainer);
        }
    }
}
