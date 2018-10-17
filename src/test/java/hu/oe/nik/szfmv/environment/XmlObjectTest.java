package hu.oe.nik.szfmv.environment;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class XmlObjectTest {


    private static final double THRESHOLD = 0.0001d;
    private double[][] multD = new double[5][];
    private double[][] mu;

    @Before
    public void setUp(){
        multD[0] = new double[10];
    }


    private XmlObject xo = new XmlObject(1, 1, "man", multD);


    @Test
    public void getX() {
        assertEquals(xo.getX(), 1);
    }

    @Test
    public void getY() {
        assertEquals(xo.getY(), 1);
    }

    @Test
    public void getType() {
        assertEquals("man", xo.getType());
    }

    @Test
    public void getMx() {
        mu = multD;
        assertEquals(multD[0], xo.getMx()[0]);
    }
}
