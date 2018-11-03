package hu.oe.nik.szfmv.visualization;

import hu.oe.nik.szfmv.environment.WorldObject;

import java.util.ArrayList;
import java.util.List;

public class CollisionDetector {

    private static final double criticalHitSpeed = 60;
    private static final double survivableHitSpeed = 30;

    private static CollisionDetector instance = null;
    protected CollisionDetector() {
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
    public static void findObstacles(List<WorldObject> list){
        // TODO: ehhez még kell az ütközős XML lista beolvasása
        List<String> obstacleNames = new ArrayList<String>();

        for (WorldObject object: list) {
            if (  obstacleNames.contains(object.getImageFileName()) ) {
                obstacles.add(object);
            }
        }
    }

    public static List<WorldObject> getObstacles() {
        return obstacles;
    }

    private static boolean critHitHappened(double speedOfObject1, double speedOfObject2) {
        return Math.abs(speedOfObject1 - speedOfObject2) >= criticalHitSpeed;
    }

    private static boolean acceptableHitHappened(double speedOfObject1, double speedOfObject2) {
        return Math.abs(speedOfObject1 - speedOfObject2) >= survivableHitSpeed;
    }
}
