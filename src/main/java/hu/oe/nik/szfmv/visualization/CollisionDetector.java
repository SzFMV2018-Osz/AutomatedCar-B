package hu.oe.nik.szfmv.visualization;

import hu.oe.nik.szfmv.automatedcar.AutomatedCar;
import hu.oe.nik.szfmv.environment.WorldObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CollisionDetector {

    private static CollisionDetector instance = null;
    private List<ColliderModel> colliders;
    private CollisionDetector() {
        colliders = new ArrayList<>();
        try
        {
            readXML4Colliders();
        }
        catch (IOException e) {

        } catch (ParserConfigurationException e) {

        } catch (SAXException e) {

        }
        // Exists only to defeat instantiation.
    }
    public static CollisionDetector getInstance() {
        if(instance == null) {
            instance = new CollisionDetector();
        }
        return instance;
    }

    private static List<WorldObject> obstacles = new ArrayList<>();

    /***
     * Az ütközhető objektumok megkeresése, listába gyűjtése
     */
    public void findObstacles(List<WorldObject> list){
        // TODO: ehhez még kell az ütközős XML lista beolvasása

        for (WorldObject object: list) {
            for(ColliderModel collider: colliders) {
                if(collider.getName().equals(object.getImageFileName()))
                {
                    obstacles.add(object);
                    break;
                }
            }
        }
    }

    public boolean checkCollisions(AutomatedCar car) {
        boolean crashed = false;
        Shape carShape= null;
        for(ColliderModel collider: colliders)
        {
            if(collider.getName().equals(car.getImageFileName()))
            {
                carShape = createTransformedShapeForCollision(car,collider);
            }
        }
        for (WorldObject object: obstacles) {
            for(ColliderModel collider: colliders)
            {
                if(collider.getName().equals(object.getImageFileName()))
                {
                    Shape tempShape = createTransformedShapeForCollision(object,collider);
                    crashed = carShape.intersects((Rectangle2D)tempShape);
                    //TODO: Történjen valami itt valami.
                    break;
                }
            }
        }

        return crashed;
    }

    private Shape createTransformedShapeForCollision(WorldObject object, ColliderModel collider)
    {
        Shape finalShape = null;
        try
        {   //Szomorú hogy ezt még mindig így kell megcsinálnunk.....
            //TODO: Törölni ezt innen amint a B1 végre megcsinálja a dolgokat.
            BufferedImage image = ImageIO.read(new File(ClassLoader.getSystemResource(object.getImageFileName()).getFile()));
            AffineTransform at = new AffineTransform();
            at.setToTranslation(object.getX()+collider.getX(), object.getY()+collider.getY());
            Point refPoint = ReferencePointsXMLReadClass.checkIsReferenceOrNot(object.getImageFileName());
            at.translate(-refPoint.x, -refPoint.y);
            at.rotate(-object.getRotation(), refPoint.x-collider.getX(), refPoint.y-collider.getY());
            Shape baseShape = collider.getShape();
            finalShape = at.createTransformedShape(baseShape);
        }catch (IOException e) {}
        return finalShape;
    }

    private Shape createTransformedShapeForCollision(AutomatedCar object, ColliderModel collider)
    {
        Shape finalShape = null;
        try
        {   //Szomorú hogy ezt még mindig így kell megcsinálnunk.....
            //TODO: Törölni ezt innen amint a B1 végre megcsinálja a dolgokat.
            BufferedImage image = ImageIO.read(new File(ClassLoader.getSystemResource(object.getImageFileName()).getFile()));
            AffineTransform at = new AffineTransform();
            at.setToTranslation(object.getX()+collider.getX(), object.getY()+collider.getY());
            Point refPoint = ReferencePointsXMLReadClass.checkIsReferenceOrNot(object.getImageFileName());
            at.translate(-refPoint.x, -refPoint.y);
            at.rotate(-object.getRotation(), refPoint.x-collider.getX(), refPoint.y-collider.getY());
            Shape baseShape = collider.getShape();
            finalShape = at.createTransformedShape(baseShape);
        }catch (IOException e) {}
        return finalShape;
    }

    public static List<WorldObject> getObstacles() {
        return obstacles;
    }

    private void readXML4Colliders()
            throws ParserConfigurationException, IOException, SAXException {

        File inputFile = new File(ClassLoader
                .getSystemResource("collision.xml").getFile());
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("Object");

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                ColliderModel model = new ColliderModel(eElement.getAttribute("name"),  Integer.parseInt(eElement.getAttribute("x")),
                        Integer.parseInt(eElement.getAttribute("y")), Integer.parseInt(eElement.getAttribute("w")), Integer.parseInt(eElement.getAttribute("h")),
                        eElement.getAttribute("geometry"));
                colliders.add(model);
            }
        }
    }
}
