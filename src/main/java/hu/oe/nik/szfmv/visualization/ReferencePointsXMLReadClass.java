package hu.oe.nik.szfmv.visualization;

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
    private List<ReferencePointClass> ReferencesList = new ArrayList<>();

    public void ReadXML4ReferencePoints() throws ParserConfigurationException, IOException, SAXException {

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
                RPC.setX(Integer.parseInt(eElement.getAttribute("x")));
                RPC.setY(Integer.parseInt(eElement.getAttribute("y")));

                ReferencesList.add(RPC);
            }
        }
    }

    public Point CheckIsReferenceOrNot(String PictureName) {
        for (ReferencePointClass act : ReferencesList) {
            if (act.getName() == PictureName) {
                return new Point(act.getX(), act.getY());
            }
        }
        return new Point(0, 0);
    }
}

