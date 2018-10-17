package hu.oe.nik.szfmv;

import hu.oe.nik.szfmv.automatedcar.AutomatedCar;
import hu.oe.nik.szfmv.common.ConfigProvider;
import hu.oe.nik.szfmv.environment.World;
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
    private static int carPosX = 20;
    private static int carPosY = 20;

    /**
     * Main entrypoint of the software.
     * @param args command line arguments
     * @throws IOException exception
     * @throws SAXException exception
     * @throws ParserConfigurationException exception
     */
    public static void main(final String[] args) throws IOException, SAXException, ParserConfigurationException {

        // log the current debug mode in config
        LOGGER.info(ConfigProvider.provide().getBoolean("general.debug"));

        // create the world
        World w = new World(800, 600);

        // create an automated car
        AutomatedCar car = new AutomatedCar(0, 0, "car_2_white.png");
        w.setAutomatedCar(car);

        //loadRoads();
        //w.setWorldObjects(roads);
        w.setHeight(3000);
        w.setWidth(5120);


        // create gui
        Gui gui = new Gui();

        // draw world to course display
        gui.getCourseDisplay().drawWorld(w);

        while (true) {
            car.drive();

            gui.getCourseDisplay().drawWorld(w);
        }
    }
}
