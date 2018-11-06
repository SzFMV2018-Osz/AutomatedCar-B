package hu.oe.nik.szfmv;

import hu.oe.nik.szfmv.automatedcar.AutomatedCar;
import hu.oe.nik.szfmv.common.ConfigProvider;
import hu.oe.nik.szfmv.environment.World;
import hu.oe.nik.szfmv.environment.WorldObject;
import hu.oe.nik.szfmv.environment.worldobjectclasses.NpcCar;
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
     *
     * @param args command line arguments
     * @throws IOException                  exception
     * @throws SAXException                 exception
     * @throws ParserConfigurationException exception
     */
    public static void main(final String[] args) throws IOException, SAXException, ParserConfigurationException {

        // log the current debug mode in config
        LOGGER.info(ConfigProvider.provide().getBoolean("general.debug"));
        // create the world
        World w = new World(800, 600);

        // create an automated car
        AutomatedCar car = new AutomatedCar(carPosX, carPosY, "car_2_white.png");
        w.setAutomatedCar(car);

        w.setHeight(3000);
        w.setWidth(5120);

        WorldObject npc = new NpcCar(3086,240,"car_1_red.png");
        w.addObjectToWorld(npc);

        // create gui
        Gui gui = new Gui();

        // draw world to course display
        gui.getCourseDisplay().drawWorld(w);

        while (true) {
            car.drive();
            // create gui
            ((NpcCar)npc).move();
            gui.getCourseDisplay().drawWorld(w);


        }
    }
}