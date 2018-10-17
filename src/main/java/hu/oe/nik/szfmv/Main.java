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
import hu.oe.nik.szfmv.common.Utils;
import hu.oe.nik.szfmv.visualization.ReferencePointsXMLReadClass;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

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
        AutomatedCar car = new AutomatedCar(carPosX, carPosY, "car_2_white.png");
        w.setAutomatedCar(car);

        w.setHeight(3000);
        w.setWidth(5120);

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
        // create gui
            gui.getCourseDisplay().drawWorld(w);
        }
    }
}
