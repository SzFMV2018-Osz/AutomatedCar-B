package hu.oe.nik.szfmv;

import hu.oe.nik.szfmv.automatedcar.AutomatedCar;
import hu.oe.nik.szfmv.common.ConfigProvider;
import hu.oe.nik.szfmv.common.Utils;
import hu.oe.nik.szfmv.environment.World;
import hu.oe.nik.szfmv.environment.WorldObject;
import hu.oe.nik.szfmv.visualization.Gui;
import hu.oe.nik.szfmv.visualization.ReferencePointClass;
import hu.oe.nik.szfmv.visualization.ReferencePointsXMLReadClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main {

    private static final Logger LOGGER = LogManager.getLogger();

    private static List<WorldObject> roads = new ArrayList<WorldObject>();

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
        AutomatedCar car = new AutomatedCar(0, 0, "car_2_white.png");
        w.setAutomatedCar(car);
        car.setRotation((float)(Math.PI/4));
        /////////////////////////////////

        // create gui
        Gui gui = new Gui();


        /// ASDASDASDASD
        loadRoads();
        for (WorldObject r: roads) {
            w.addObjectToWorld(r);
        }

        // add teszt road to the world
        //WorldObject road = new WorldObject(0, 0, "2_crossroad_2.png");
        //w.addObjectToWorld(road);

        // draw world to course display
        gui.getCourseDisplay().drawWorld(w);

        while (true) {
            car.drive();

            gui.getCourseDisplay().drawWorld(w);
        }
    }

    private static void loadRoads(){
        String filePath = "src\\main\\resources\\lane_keeping_test_world.xml";
        File xmlFile = new File(filePath);

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("Object");

            for ( int i = 0; i< nodeList.getLength(); i++) {
                roads.add(getObject(nodeList.item(i)));
            }

        }  catch (SAXException | ParserConfigurationException | IOException e1) {
            e1.printStackTrace();
        }
    }

    private static WorldObject getObject(Node node){
        if(node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;

            int x = Integer.parseInt(((Element)(element.getElementsByTagName("Position")
                    .item(0))).getAttribute("x")) ;

            int y = Integer.parseInt(((Element)(element.getElementsByTagName("Position")
                    .item(0))).getAttribute("y")) ;

            WorldObject obj = new WorldObject(x, y, element.getAttribute("type")+".png");

            double m11 = Double.parseDouble(((Element)(element.getElementsByTagName("Transform")
                    .item(0))).getAttribute("m11"));
            double m12 = Double.parseDouble(((Element)(element.getElementsByTagName("Transform")
                    .item(0))).getAttribute("m12"));
            double m21 = Double.parseDouble(((Element)(element.getElementsByTagName("Transform")
                    .item(0))).getAttribute("m21"));
            double m22 = Double.parseDouble(((Element)(element.getElementsByTagName("Transform")
                    .item(0))).getAttribute("m22"));
            obj.setRotation((float)Math.toDegrees(Utils.convertMatrixToRadians(m11,m12,m21,m22)));

            Point asd = ReferencePointsXMLReadClass.CheckIsReferenceOrNot(obj.getImageFileName());

            double x1 = asd.getX();
            double y1 = asd.getY();


            obj.setRotPointX(asd.x);
            obj.setRotPointY(asd.y);

            System.out.println(obj.getImageFileName() + ": " + " pos: "+ obj.getX() + ", " + obj.getY() + "rot p: " + asd + ", " + " rot: " + obj.getRotation());

            return obj;
        }
        return null;
    }

}
