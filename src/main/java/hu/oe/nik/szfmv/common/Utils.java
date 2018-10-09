package hu.oe.nik.szfmv.common;

import hu.oe.nik.szfmv.environment.StaticObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public final class Utils {

    private static final int METER_PIXEL_RATIO = 50;

    /**
     * Converts the length defined in pixels to meters according the rule of 350 pixel = 7 meters.
     *
     * @param pixel is the length in pixels
     * @return the length in meters
     */
    public static double convertPixelToMeter(int pixel) {
        return pixel / METER_PIXEL_RATIO;
    }


    // Copied from https://github.com/SzFMV2017-Tavasz/AutomatedCar
    // /src/main/java/hu/oe/nik/szfmv17t/environment/utils/XmlParser.java#L257

    /**
     * Converts a 2x2 transformation matrix, read from the virtual world xml to radians
     *
     * @param m11 first element of the 2x2 transformation matrix providing the angle of rotation
     * @param m12 second element of the 2x2 transformation matrix providing the angle of rotation
     * @param m21 third element of the 2x2 transformation matrix providing the angle of rotation
     * @param m22 fourth element of the 2x2 transformation matrix providing the angle of rotation
     * @return the rotation in radian
     */
    public static double convertMatrixToRadians(double m11, double m12, double m21, double m22) {
        //formula of the angle between the two vectors: a * b = |a| * |b| * cos(beta)
        //where a * b is the scalarProduct
        //Our zero degree will be the horizontal right:
        double defaultX = 1;
        double defaultY = 0;

        double transformedX = m11 * defaultX + m12 * defaultY;
        double transformedY = m21 * defaultX + m22 * defaultY;

        double scalarProduct = defaultX * transformedX + defaultY * transformedY;

        double defaultVectorLength = Math.sqrt(defaultX * defaultX + defaultY * defaultY);
        double transformedVectorLength = Math.sqrt(transformedX * transformedX + transformedY * transformedY);

        double angleInRad = Math.acos(scalarProduct / (defaultVectorLength * transformedVectorLength));
        if (transformedY < 0) {
            angleInRad = 2 * Math.PI - angleInRad;
        }
        //If angle is NaN as a result of transformedVectorLength=0, Math.round() returns 0. It is correct in our cases.
        //angleInRad = Math.round(angleInRad * 100.0) / 100.0;
        return angleInRad;
//        double m13 = 0;
//        double m23 = 0;
//        double m33 = 1;
//        return 6.2831853072d - Math.acos((m11 + m22) / 2);
    }

    /**
     * Converts a 2x2 transformation matrix, read from the virtual world xml to radians
     *
     * @param matrix the 2x2 transformation matrix providing the angle of rotation
     * @return the rotation in radian
     */
    public static double convertMatrixToRadians(double[][] matrix) {
        return convertMatrixToRadians(matrix[0][0], matrix[0][1], matrix[1][0], matrix[1][1]);
    }

    /**
     * Converts radian to degree. It basically just a wrapper for Math.toDegrees()
     *
     * @param rad is the angle in radian
     * @return the angle in degree
     */
    public static double radianToDegree(double rad) {
        return Math.toDegrees(rad);
    }

    public static Document xmlReader() throws ParserConfigurationException, IOException, SAXException {

        File testWorldXml = new File("src/main/resources/test_world.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = dbFactory.newDocumentBuilder();
        Document testWorld = documentBuilder.parse(testWorldXml);
        testWorld.getDocumentElement().normalize();

        return testWorld;
    }

    public static List<StaticObject> getDataFromDocument(Document doc)
    {
        NodeList objectNodeList = doc.getElementsByTagName("Object");
        Element objectElement;
        Element positionElement = null;
        Element transformElement = null;
        List<StaticObject> StaticObjectList = new ArrayList<>();
        int x = 0;
        int y = 0;
        String type = null;
        double m11 = 0;
        double m12 = 0;
        double m21 = 0;
        double m22 = 0;

        for (int i = 0; i < objectNodeList.getLength(); i++) {
            Node objectNode = objectNodeList.item(i);
            if(objectNode.getNodeType() == Node.ELEMENT_NODE){
                objectElement = (Element) objectNode;
                type = objectElement.getAttribute("type");
                NodeList childNodes = objectElement.getChildNodes();

                for(int j = 0; j < childNodes.getLength(); j++) {
                    if (childNodes.item(j).getNodeName() == "Position"){
                        positionElement = (Element)childNodes.item(j);
                        x = Integer.parseInt(positionElement.getAttribute("x"));
                        y = Integer.parseInt(positionElement.getAttribute("y"));
                    }
                    else if(childNodes.item(j).getNodeName() == "Transform"){
                        transformElement = (Element)childNodes.item(j);
                        m11 = Double.parseDouble(transformElement.getAttribute("m11"));
                        m12 = Double.parseDouble(transformElement.getAttribute("m12"));
                        m21 = Double.parseDouble(transformElement.getAttribute("m21"));
                        m22 = Double.parseDouble(transformElement.getAttribute("m22"));
                    }
                }
            }
            StaticObjectList.add(new StaticObject(x,y,type+".png",type));
        }
        return StaticObjectList;
    }

}
