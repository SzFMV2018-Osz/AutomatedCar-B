package hu.oe.nik.szfmv.visualization;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.awt.*;
import java.util.*;
import java.io.*;
import java.util.List;
import javax.xml.parsers.*;

public class ReferencePointsXMLReadClass {
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
}