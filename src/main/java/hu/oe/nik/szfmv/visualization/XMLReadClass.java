package hu.oe.nik.szfmv.visualization;

import hu.oe.nik.szfmv.Main;
import hu.oe.nik.szfmv.common.Utils;
import hu.oe.nik.szfmv.environment.World;
import hu.oe.nik.szfmv.environment.WorldObject;
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
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLReadClass {
    private static List<ReferencePointClass> ReferencesList = new ArrayList<>();
    private static final Logger LOGGER = LogManager.getLogger();
    private static void ReadXML4ReferencePoints() throws ParserConfigurationException, IOException, SAXException {

        File inputFile = new File("src\\main\\resources\\reference_points.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("Image");

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                ReferencePointClass RPC = new ReferencePointClass();

                RPC.setName(eElement.getAttribute("name"));
                RPC.setX(Integer.parseInt(((Element)(eElement.getElementsByTagName("Refpoint")
                        .item(0))).getAttribute("x")));
                RPC.setY(Integer.parseInt(((Element)(eElement.getElementsByTagName("Refpoint")
                        .item(0))).getAttribute("y")));

                ReferencesList.add(RPC);
            }
        }
    }

    public static Point CheckIsReferenceOrNot(String PictureName) {
        if(ReferencesList.size() <= 0)
        {
            try
            {
                ReadXML4ReferencePoints();
            }
            catch (IOException e) {
                LOGGER.error(e.getMessage());
            }
            catch (ParserConfigurationException e) {
                LOGGER.error(e.getMessage());
            }
            catch (SAXException e) {
                LOGGER.error(e.getMessage());
            }
        }
        for (ReferencePointClass act : ReferencesList) {
            if (act.getName().equals(PictureName)) {
                return new Point(act.getX(), act.getY());
            }
        }
        return new Point(0, 0);
    }

    public static void loadRoads(Gui gui, World world){
        String filePath = "src\\main\\resources\\test_world.xml";
        //String filePath = "src\\main\\resources\\lane_keeping_test_world.xml";
        File xmlFile = new File(filePath);

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            world.setHeight(Integer.parseInt(doc.getDocumentElement().getAttribute("height")));
            world.setWidth(Integer.parseInt(doc.getDocumentElement().getAttribute("width")));
            gui.getCourseDisplay().setBackground(Color.decode(doc.getDocumentElement().getAttribute("color")));
            NodeList nodeList = doc.getElementsByTagName("Object");

            for ( int i = 0; i< nodeList.getLength(); i++) {
                Main.Roads.add(getObject(nodeList.item(i)));
            }
            world.setWorldObjects(Main.Roads);

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
            obj.setRotation((float)Math.toRadians(-obj.getRotation()));

            Point asd = XMLReadClass.CheckIsReferenceOrNot(obj.getImageFileName());

            return obj;
        }
        return null;
    }
}

