package hu.oe.nik.szfmv.visualization;

import hu.oe.nik.szfmv.automatedcar.AutomatedCar;
import hu.oe.nik.szfmv.environment.WorldObject;
import hu.oe.nik.szfmv.environment.worldobjectclasses.Bicycle;
import hu.oe.nik.szfmv.environment.worldobjectclasses.Collidable;
import hu.oe.nik.szfmv.environment.worldobjectclasses.Human;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CollisionDetector {

    private static final double criticalHitSpeed = 60;
    private static final double survivableHitSpeed = 30;

    private static CollisionDetector instance = null;
    private List<ColliderModel> colliders;
    private AutomatedCar carObject;
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

    private List<WorldObject> obstacles = new ArrayList<>();

    /***
     * Az ütközhető objektumok megkeresése, listába gyűjtése
     */
    public void findObstacles(List<WorldObject> list){
        for (WorldObject object: list) {
            if(object instanceof Collidable)
            {
                obstacles.add(object);
            }
        }
    }

    public void setCarObject(AutomatedCar car){
        carObject = car;
    }

    public boolean checkCollisions() {
        Shape carShape= null;
        for(ColliderModel collider: colliders)
        {
            if(getPureImageName(collider.getName()).equals(getPureImageName(carObject.getImageFileName())))
            {
                carShape = createTransformedShapeForCollision(carObject,collider);
            }
        }
        for (WorldObject object: obstacles) {
            for(ColliderModel collider: colliders)
            {
                if(getPureImageName(collider.getName()).equals(getPureImageName(object.getImageFileName())))
                {
                    Shape tempShape = createTransformedShapeForCollision(object,collider);
                    if(carShape.getBounds2D().intersects(tempShape.getBounds2D()))
                    {
                        if(object instanceof Human || object instanceof Bicycle) return true;
//                        if(!(object instanceof Movable) && critHitHappened(carObject.getSpeed() ,0))
//                        {
//                            return true;
//                        }
//                        else if((object instanceof Movable) && critHitHappened(carObject.getSpeed(),object.getSpeed()))
//                        {
//                            return true;
//                        }
//                        else
//                        {
//                            if(object instanceof Movable)
//                            {
//                                object.addDamage(calculateDamage(carObject.getSpeed(),object.getSpeed()));
//                                carObject.addDamage(calculateDamage(carObject.getSpeed(),object.getSpeed()));
//                            }
//                            else
//                            {
//                                object.addDamage(calculateDamage(carObject.getSpeed(),0));
//                                carObject.addDamage(calculateDamage(carObject.getSpeed(),0));
//                            }
//                        }
                    }
                    break;
                }
            }
        }
        return false;
    }

//    private void imageChanger()
//    {
//        for(WorldObject object : obstacles)
//        {
//            if (object.getDamage() >= 60)
//            {
//                changeImage(object, 2);
//            }
//            else if (object.getDamage() >= 30)
//            {
//                changeImage(object, 1);
//            }
//            else if (object instanceof Car && object.getDamage() >= 0)
//            {
//                //since we have 3 state of cars, we should define another case
//                changeImage(object, 0);
//            }
//        }
//    }

//    private void changeImage(WorldObject object, int status) {
//        if (object instanceof Car)
//        {
            // itt lehet hogy inkabb a BufferedImaget kellene atallitani, amit mar ugyis beolvasunk construktorban
//            object.setImageFileName(getPureImageName(object.getImageFileName()) + "_" + status + 1 + ".png");
//        }
//        else
//        {
//            object.setImageFileName(getPureImageName(object.getImageFileName()) + "_" + status + ".png");
//        }
//    }

    private Shape createTransformedShapeForCollision(WorldObject object, ColliderModel collider)
    {
        Shape finalShape = null;
        AffineTransform at = new AffineTransform();
        at.setToTranslation(object.getX() + collider.getX(), object.getY() + collider.getY());
        Point refPoint = ReferencePointsXMLReadClass.checkIsReferenceOrNot(object.getImageFileName());
        at.translate(-refPoint.x, -refPoint.y);
        at.rotate(-object.getRotation(), refPoint.x - collider.getX(), refPoint.y - collider.getY());
        Shape baseShape = collider.getShape();
        finalShape = at.createTransformedShape(baseShape);
        return finalShape;
    }

    public List<WorldObject> getObstacles() {
        return obstacles;
    }

    private boolean critHitHappened(double speedOfObject1, double speedOfObject2) {
        return Math.abs(speedOfObject1 - speedOfObject2) >= criticalHitSpeed;
    }

    private double calculateDamage(double speed1, double speed2)
    {
        return Math.abs(speed1 - speed2);
    }

    private boolean acceptableHitHappened(double speedOfObject1, double speedOfObject2) {
        return Math.abs(speedOfObject1 - speedOfObject2) >= survivableHitSpeed;
    }

    private String getPureImageName(String imageName) {
        //if the imagename does not contain underscore, it's still the original image
        if (imageName.contains("_")) {
            return imageName.substring(0, imageName.lastIndexOf('_') - 1);
        }
        else {
            return imageName.substring(0, imageName.lastIndexOf('.') - 1);
        }
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
