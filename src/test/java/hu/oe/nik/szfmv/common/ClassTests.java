package hu.oe.nik.szfmv.common;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ClassTests {

    private static final double THRESHOLD = 0.0001d;

    @Test
    public void testPositionClass() {
        final int x = 1;
        final int y = 2;

        Position m = new Position(x, y);

        assertEquals(1, m.GetX(), THRESHOLD);
        assertEquals(2, m.GetY(), THRESHOLD);
    }

    @Test
    public void testTransformClass() {
        final double m11 = 1.01;
        final double m12 = 2.00;
        final double m21 = 1.00;
        final double m22 = 2.01;

        Transform m = new Transform(m11, m12, m21, m22);

        assertEquals(1.01, m.GetM11(), THRESHOLD);
        assertEquals(2.00, m.GetM12(), THRESHOLD);
        assertEquals(1.00, m.GetM21(), THRESHOLD);
        assertEquals(2.01, m.GetM22(), THRESHOLD);

    }
    @Test
    public void testStaticObjectClass() {
        Position p = new Position(1, 1);
        Transform t = new Transform(1.0, 1.1, 2.1, 2.1);
        String type = "tree";

        StaticObject dy = new StaticObject(p, t, type);
        assertEquals(p, dy.GetPosition());
        assertEquals(t, dy.GetTransform());
        assertEquals(StaticObject.TypeEnum.TREE, dy.GetType());
    }
    @Test
    public void testControllableCar() {
        Position p = new Position(1, 1);
        Transform t = new Transform(1.0, 1.1, 2.1, 2.1);
        String type = "car";

        ControllableCar dy = new ControllableCar(p, t, type);
        assertEquals(p, dy.GetPosition());
        assertEquals(t, dy.GetTransform());
        assertEquals(DynamicObject.TypeEnumDynamic.CAR, dy.GetType());
    }

    @Test
    public void testSceneClass() {
        final int x = 5;
        final int y = 5;
        Position p = new Position(1, 1);
        Transform t = new Transform(1.0, 1.1, 2.1, 2.1);
        String type = "car";
        List<DynamicObject> dyList = new ArrayList<DynamicObject>();
        dyList.add(new DynamicObject(p, t , type));
        List<StaticObject> statList = new ArrayList<StaticObject>();
        statList.add(new StaticObject(p, t , type));
        ControllableCar car = new ControllableCar(p, t , type);

        Scene sc = new Scene(x, y, car, dyList, statList);

        assertEquals(car, sc.GetCar());
        assertEquals(x, sc.GetXSize());
        assertEquals(y, sc.GetYSize());
        assertEquals(dyList, sc.GetDynamicList());
        assertEquals(statList, sc.GetStaticList());
    }



}
