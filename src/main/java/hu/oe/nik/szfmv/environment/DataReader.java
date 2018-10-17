package hu.oe.nik.szfmv.environment;

import hu.oe.nik.szfmv.automatedcar.AutomatedCar;
import hu.oe.nik.szfmv.common.Utils;
import hu.oe.nik.szfmv.environment.worldobjectclasses.Human;
import hu.oe.nik.szfmv.environment.worldobjectclasses.Road;
import hu.oe.nik.szfmv.environment.worldobjectclasses.RoadSign;
import hu.oe.nik.szfmv.environment.worldobjectclasses.Tree;
import hu.oe.nik.szfmv.environment.worldobjectclasses.Crosswalk;
import hu.oe.nik.szfmv.environment.worldobjectclasses.Bicycle;
import hu.oe.nik.szfmv.environment.worldobjectclasses.ParkingSpot;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataReader {

    static final String imageExtension = ".png";

    /**
     *
     * @param path The path of the xml file
     * @return Document object that contains the whole content of the xml file.
     */
    public static Document xmlReader(String path) {

        File testWorldXml = new File(path);
        if (testWorldXml.exists()) {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = null;
            try {
                documentBuilder = dbFactory.newDocumentBuilder();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }
            Document testWorld = null;
            try {
                testWorld = documentBuilder.parse(testWorldXml);
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            testWorld.getDocumentElement().normalize();
            return testWorld;
        }
        return null;
    }

    /**
     * Generates a list of StaticObjects from the parameter
     *
     * @param doc contains the whole content of the xml
     * @return a list of WorldObjects
     */
    public static List<WorldObject> getDataFromDocument(Document doc) {
        NodeList objectNodeList = doc != null ? doc.getElementsByTagName("Object") : null;
        List<WorldObject> worldObjects = new ArrayList<>();
        int x = 0;
        int y = 0;
        String type = null;
        double[][] matrix = null;
        if (objectNodeList != null) {
            for (int i = 0; i < objectNodeList.getLength(); i++) {
                Node objectNode = objectNodeList.item(i);
                if (objectNode.getNodeType() == Node.ELEMENT_NODE) {
                    type = ((Element) objectNode).getAttribute("type");
                    NodeList childNodes = (objectNode).getChildNodes();
                    for (int j = 0; j < childNodes.getLength(); j++) {
                        if (childNodes.item(j).getNodeName().equals("Position")) {
                            x = Integer.parseInt(((Element) childNodes.item(j)).getAttribute("x"));
                            y = Integer.parseInt(((Element) childNodes.item(j)).getAttribute("y"));
                        } else if (childNodes.item(j).getNodeName().equals("Transform")) {
                            matrix = createTransformMatrix((Element) childNodes.item(j));
                        }
                    }
                }
                WorldObject worldObject = createObjectFromType(x, y, type);
                worldObject.setRotation((float) Utils.convertMatrixToRadians(matrix));
                worldObjects.add(worldObject);
            }
        }
        return worldObjects;
    }

    /**
     * @param x    x parameter for the WorldObject object
     * @param y    y parameter for the WorldObject object
     * @param type type parameter for the WorldObject object
     * @return WorldObject object
     */
    public static WorldObject createObjectFromType(int x, int y, String type) {
        WorldObject worldObject;
        String originalType = type;
        if (type.indexOf('_') != -1) {
            type = type.substring(0, type.indexOf('_'));
        }
        switch (type) {
            case "car":
                worldObject = new AutomatedCar(x, y, originalType + imageExtension);
                break;
            case "man":
                worldObject = new Human(x, y, originalType + imageExtension);
                break;
            case "woman":
                worldObject = new Human(x, y, originalType + imageExtension);
                break;
            case "tree":
                worldObject = new Tree(x, y, originalType + imageExtension);
                break;
            case "roadsign":
                worldObject = new RoadSign(x, y, originalType + imageExtension);
                break;
            case "road":
                worldObject = new Road(x, y, originalType + imageExtension);
                break;
            case "parking":
                worldObject = new ParkingSpot(x, y, originalType + imageExtension);
                break;
            case "crosswalk":
                worldObject = new Crosswalk(x, y, originalType + imageExtension);
                break;
            case "bicycle":
                worldObject = new Bicycle(x, y, originalType + imageExtension);
                break;
            default:
                worldObject = null;
        }
        return worldObject;
    }

    /**
     * @param doc contains the whole content of the xml
     * @return a list of XmlObjects
     */
    public static List<XmlObject> getDataFromXmlDocument(Document doc) {
        NodeList objectNodeList = doc != null ? doc.getElementsByTagName("Object") : null;
        List<XmlObject> xmlObjects = new ArrayList<>();
        int x = 0;
        int y = 0;
        String type = null;
        double[][] matrix = null;
        if (objectNodeList != null) {
            for (int i = 0; i < objectNodeList.getLength(); i++) {
                Node objectNode = objectNodeList.item(i);
                if (objectNode.getNodeType() == Node.ELEMENT_NODE) {
                    type = ((Element) objectNode).getAttribute("type");
                    NodeList childNodes = (objectNode).getChildNodes();
                    for (int j = 0; j < childNodes.getLength(); j++) {
                        if (childNodes.item(j).getNodeName().equals("Position")) {
                            x = Integer.parseInt(((Element) childNodes.item(j)).getAttribute("x"));
                            y = Integer.parseInt(((Element) childNodes.item(j)).getAttribute("y"));
                        } else if (childNodes.item(j).getNodeName().equals("Transform")) {
                            matrix = createTransformMatrix((Element) childNodes.item(j));
                        }
                    }
                    xmlObjects.add(new XmlObject(x, y, type, matrix));
                }
            }
        }
        return xmlObjects;
    }

    /**
     * @param transformElement an element from the NodeList that contains the coords for the transform.
     * @return the 2x2 transformation matrix providing the angle of rotation
     */

    public static double[][] createTransformMatrix(Element transformElement) {
        double[][] matrix = new double[2][2];
        if (transformElement != null) {
            matrix[0][0] = Double.parseDouble(transformElement.getAttribute("m11"));
            matrix[0][1] = Double.parseDouble(transformElement.getAttribute("m12"));
            matrix[1][0] = Double.parseDouble(transformElement.getAttribute("m21"));
            matrix[1][1] = Double.parseDouble(transformElement.getAttribute("m22"));
        }
        return matrix;
    }

    /**
     * @param doc contains the whole content of the xml
     * @return a World object with the width and height
     */
    public static World getSceneFromDocument(Document doc) {
        NodeList sceneList = doc != null ? doc.getElementsByTagName("Scene") : null;
        if (sceneList != null) {
            for (int i = 0; i < sceneList.getLength(); i++) {
                Node sceneNode = sceneList.item(i);
                if (sceneNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element sceneElement = (Element) sceneNode;
                    int width = Integer.parseInt(sceneElement.getAttribute("width"));
                    int height = Integer.parseInt(sceneElement.getAttribute("height"));
                    return new World(width, height);
                }
            }
        }
        return null;
    }

}
