package hu.oe.nik.szfmv;

import hu.oe.nik.szfmv.automatedcar.AutomatedCar;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.UserInputProvider;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.enums.InputType;
import hu.oe.nik.szfmv.common.ConfigProvider;
import hu.oe.nik.szfmv.environment.World;
import hu.oe.nik.szfmv.environment.WorldObject;
import hu.oe.nik.szfmv.environment.worldobjectclasses.Bicycle;
import hu.oe.nik.szfmv.environment.worldobjectclasses.Human;
import hu.oe.nik.szfmv.environment.worldobjectclasses.NpcCar;
import hu.oe.nik.szfmv.visualization.CollisionDetector;
import hu.oe.nik.szfmv.visualization.Gui;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

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

    /**
     * Main entrypoint of the software.
     *
     * @param args command line arguments
     * @throws IOException                  exception
     * @throws SAXException                 exception
     * @throws ParserConfigurationException exception
     */
    public static void main(final String[] args) throws IOException, SAXException, ParserConfigurationException {

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

        WorldObject npc = new NpcCar(3086,240,"car_1_red.png");
        w.addObjectToWorld(npc);

        WorldObject bicycle = new Bicycle(3020,194, "bicycle.png");
        w.addObjectToWorld(bicycle);

        WorldObject human = new Human(1550, 2, "man.png");
        w.addObjectToWorld(human);

        // create gui
        Gui gui = new Gui();
        gui.addKeyListener(UserInputProvider.getUserInput(InputType.Keyboard));

        // draw world to course display
        gui.getCourseDisplay().drawWorld(w);

        // Collision detection
        CollisionDetector singleton = CollisionDetector.getInstance();
        singleton.findObstacles(w.getWorldObjects());

        //setup the instance of collisiondetector
        singleton.setCarObject(car);

        while (!singleton.checkCollisions()) {
            car.drive();
            // create gui
            ((NpcCar)npc).move();
            ((Human)human).move();
            ((Bicycle)bicycle).move();
            gui.getCourseDisplay().drawWorld(w);
            gui.getDashboard().display(car.getDashboardPacket(), car.getControlsPacket(), dashboardDebugIsEnabled);

        }

    }
}