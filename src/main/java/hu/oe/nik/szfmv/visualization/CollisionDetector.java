package hu.oe.nik.szfmv.visualization;

import hu.oe.nik.szfmv.automatedcar.AutomatedCar;
import hu.oe.nik.szfmv.environment.WorldObject;
import hu.oe.nik.szfmv.environment.worldobjectclasses.*;
import hu.oe.nik.szfmv.visualization.elements.DebugSection;
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

/**
 * Class for the collision.
 */
public class CollisionDetector {

    /**
     * Constans double, for the critical hit speed.
     */
    private static final double CRITICALHITSPEED = 55;
    /**
     * Constans double, for the survivable hit speed.
     */
    private static final double SURVIVABLEHITSPEED = 30;
    /**
     * The instance about the collision.
     */
    private static CollisionDetector instance = null;
    /**
     * List for the collider images.
     */
    private List<ColliderModel> colliders;
    /**
     * The automated car.
     */
    private AutomatedCar carObject;
    /**
     * All world object.
     */
    private List<WorldObject> obstacles = new ArrayList<>();


    /**
     * Constructor for the class.
     */
    private CollisionDetector() {
        colliders = new ArrayList<>();
        try {
            readXML4Colliders();
        } catch (IOException e) {

        } catch (ParserConfigurationException e) {

        } catch (SAXException e) {

        }
        // Exists only to defeat instantiation.
    }
    /**
     * Get the instance about the collision.
     * @return Instance about the collision.
     */
    public static CollisionDetector getInstance() {
        if (instance == null) {
            instance = new CollisionDetector();
        }
        return instance;
    }
    /**
     * Find and take in a list the collidable objects.
     * @param list World object list.
     */
    public void findObstacles(List<WorldObject> list) {
        for (WorldObject object: list) {
            if (object instanceof Collidable) {
                obstacles.add(object);
            }
        }
    }

    /**
     * Set the automated car.
     * @param car The car object.
     */
    public void setCarObject(AutomatedCar car) {
        carObject = car;
    }

    /**
     * Method to check the collisions.
     * @return False (while we can not get a speed).
     */
    public boolean checkCollisions() {
        Shape carShape = null;
        for (ColliderModel collider: colliders) {
            if (getPureImageName(collider.getName()).
                    equals(getPureImageName(carObject.
                            getImageFileName()))) {
                carShape = createTransformedShapeForCollision(carObject, collider);
            }
        }
        boolean thereIsSomethingInTheWay = false;
        for (WorldObject object: obstacles) {
            for (ColliderModel collider: colliders) {
                if (getPureImageName(collider.getName()).equals(getPureImageName(object.getImageFileName()))) {
                    Shape tempShape = createTransformedShapeForCollision(object, collider);
                    if (carShape.getBounds2D().intersects(tempShape.getBounds2D())) {
                        if (object instanceof Human || object instanceof Bicycle) {
                            System.out.println("Gyalogost vagy bicajost gázoltál, játék vége!");
                            return true;
                        }
                        else if (object instanceof Movable) {

                            object.addDamage(calculateDamage(((Movable)carObject).getSpeed(), ((Movable)object).getSpeed()));
                            carObject.addDamage(calculateDamage(((Movable)carObject).getSpeed(), ((Movable)object).getSpeed()));
                            imageChanger(object);
                            imageChanger(carObject);
                            if( critHitHappened(carObject.getSpeed(), ((Movable) object).getSpeed())) {
                                System.out.println("Kritikus utkozes tortent, játék vége!");
                                return true;
                            }
                            else {
                                System.out.println("Utkozes tortent, de nem kritikus...");
                                return false;
                            }
                        }
                        else {
                            System.out.println("Fanak mentel vaze??");
                            double dmg = calculateDamage(((Movable)carObject).getSpeed(), 0);
                            object.addDamage(dmg);
                            carObject.addDamage(dmg);
                            imageChanger(object);
                            imageChanger(carObject);
                            if( critHitHappened(carObject.getSpeed(), 0)) {
                                System.out.println("Kritikus utkozes tortent, nekimentel egy fanak. Damage: " + dmg + " játék vége!");
                                return true;
                            }
                        }
                    }
                    if (object instanceof Collidable && carObject.getRadar().isObjectInRadarTriangle(tempShape.getBounds2D().getX(), tempShape.getBounds2D().getY())) {
                        thereIsSomethingInTheWay = true;
                    }
                    break;
                }
            }
        }

        Dashboard.setDangerZoneVisibility(thereIsSomethingInTheWay);

        return false;
    }

    private void imageChanger(WorldObject object)
    {
        if(object instanceof  Car) {
            if (object.getDamage() >= 60) {
                changeImage(object, 2);
            }
            else if (object.getDamage() >= 30) {
                changeImage(object,1);
            }
            else if (object.getDamage() >= 5) {
                changeImage(object,0);
            }
        }
        else{
            if (object.getDamage() >= 60)
            {
                changeImage(object, 2);
            }
            else if (object.getDamage() >= 30)
            {
                changeImage(object, 1);
            }
        }
    }

    /**
     * ChangeImage, if its broken or destroyed.
     * @param object The world object, we want to change.
     * @param status The status of the word object.
     */
    private void changeImage(WorldObject object, int status) {
        if (object instanceof Car) {
            object.setImageFileName(getPureImageName(object.
                    getImageFileName()) + "_" + (status + 1) + ".png");
        }
        else {
            object.setImageFileName(getPureImageName(object.
                    getImageFileName()) + "_" + status + ".png");
        }
    }

    /**
     * To create a transformed shape for collision.
     * @param object The world object we want to manipulate.
     * @param collider The collider, it has inside.
     * @return The finel shape.
     */
    private Shape createTransformedShapeForCollision(WorldObject object, ColliderModel collider)
    {
        Shape finalShape;
        AffineTransform at = new AffineTransform();
        at.setToTranslation(object.getX() + collider.getX(), object.getY() + collider.getY());
        Point refPoint = ReferencePointsXMLReadClass.checkIsReferenceOrNot(object.getImageFileName());
        at.translate(-refPoint.x, -refPoint.y);
        at.rotate(-object.getRotation(), refPoint.x - collider.getX(), refPoint.y - collider.getY());
        Shape baseShape = collider.getShape();
        finalShape = at.createTransformedShape(baseShape);
        return finalShape;
    }

    /**
     * Get the list witch contains the world objects.
     * @return List witch contains the world objects.
     */
    public List<WorldObject> getObstacles() {
        return obstacles;
    }

    /**
     * Bool method, to watch if there is a hit.
     * @param speedOfObject1 1. object speed.
     * @param speedOfObject2 2. object speed.
     * @return true/false (if the 1. is  faster than the 2.).
     */
    private boolean critHitHappened(double speedOfObject1, double speedOfObject2) {
        return Math.abs(speedOfObject1 - speedOfObject2) >= CRITICALHITSPEED;
    }

    /**
     * Calculate the damage.
     * @param speed1 1. object speed.
     * @param speed2 2. object speed.
     * @return Double, witch contains the difference.
     */
    private double calculateDamage(double speed1, double speed2) {
        return Math.abs(speed1 - speed2);
    }

    /**
     * Acceptable hit happened method, to watch there is a definitive hit.
     * @param speedOfObject1 1. object speed.
     * @param speedOfObject2 2. object speed.
     * @return true/false (if the 1. is  faster than the 2.).
     */
    private boolean acceptableHitHappened(double speedOfObject1, double speedOfObject2) {
        return Math.abs(speedOfObject1 - speedOfObject2) >= SURVIVABLEHITSPEED;
    }

    /**
     * Get the name of the image, without markers (".","_").
     * @param imageName The name of the image.
     * @return The name of the image without markers.
     */
    private String getPureImageName(String imageName) {
        if (imageName.contains("_")) {
            int tempCount = imageName.length() - imageName.replace("_", "").length();
            if (tempCount == 2) {
                return imageName.substring(0, imageName.lastIndexOf('.'));
            }
            return imageName.substring(0, imageName.lastIndexOf('_'));
        } else {
            return imageName.substring(0, imageName.lastIndexOf('.'));
        }
    }

    /**
     * To read the collider coordinates from an XML.
     * @throws ParserConfigurationException     To catch this
     * @throws IOException                      To catch this
     * @throws SAXException                     To catch this
     */
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
                ColliderModel model = new ColliderModel(eElement.
                        getAttribute("name"),
                        Integer.parseInt(eElement.getAttribute("x")),
                        Integer.parseInt(eElement
                                .getAttribute("y")),
                        Integer.parseInt(eElement.getAttribute("w")),
                        Integer.parseInt(eElement.getAttribute("h")),
                        eElement.getAttribute("geometry"));
                colliders.add(model);
            }
        }
    }
}
