package hu.oe.nik.szfmv.visualization;

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
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**List.
 */
public class ReferencePointsXMLReadClass {
/**List.
     */
    private static List<ReferencePointClass> referencesList = new ArrayList<>();
    /**List.
     */
    private static final Logger LOGGER = LogManager.getLogger();
    /**Method.
     * @exception     ParserConfigurationException     To catch this
     * @exception     IOException                      To catch this
     * @exception     SAXException                     To catch this
     */
    private static void readXML4ReferencePoints()
            throws ParserConfigurationException, IOException, SAXException {

        File inputFile = new File(ClassLoader
                .getSystemResource("reference_points.xml").getFile());
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("Image");

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                ReferencePointClass referencePointClass =
                        new ReferencePointClass();

                referencePointClass.setName(eElement.getAttribute("name"));
                referencePointClass.setX(Integer.parseInt(((Element)
                        (eElement.getElementsByTagName("Refpoint")
                        .item(0))).getAttribute("x")));
                referencePointClass.setY(Integer.parseInt(((Element)
                        (eElement.getElementsByTagName("Refpoint")
                        .item(0))).getAttribute("y")));

                referencesList.add(referencePointClass);
            }
        }
    }
    /**Method.
     * @param pictureName picturename
     * @return Point that needed
     * @see Point
     */
    public static Point checkIsReferenceOrNot(final String pictureName) {
        if (referencesList.size() <= 0) {
            try {
                readXML4ReferencePoints();
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            } catch (ParserConfigurationException e) {
                LOGGER.error(e.getMessage());
            } catch (SAXException e) {
                LOGGER.error(e.getMessage());
            }
        }
        for (ReferencePointClass act : referencesList) {
            if (act.getName().equals(pictureName)) {
                return new Point(act.getX(), act.getY());
            }
        }
        return new Point(0, 0);
    }
}

