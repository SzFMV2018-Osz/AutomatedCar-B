package hu.oe.nik.szfmv.common;

import org.junit.Test;
import org.xml.sax.SAXException;

import javax.swing.text.Document;
import javax.xml.parsers.ParserConfigurationException;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UtilsTest {

    private static final double THRESHOLD = 0.0001d;

    @Test
    public void testPixelConverter() {
        assertEquals(7, Utils.convertPixelToMeter(350), THRESHOLD);
    }

    @Test
    public void testMatrixToRadiansConverter() {
        assertEquals(4.71238898038469, Utils.convertMatrixToRadians(0, 1, -1, 0), THRESHOLD);
        double actual = Utils.convertMatrixToRadians(0.7071068286895752, -0.7071068286895752, 0.7071068286895752, 0.7071068286895752);
        assertEquals(0.7853981633974484, actual, THRESHOLD);

        double[][] matrix = {{0, 1}, {-1, 0}};
        assertEquals(4.71238898038468985769, Utils.convertMatrixToRadians(matrix), THRESHOLD);
    }

    @Test
    public void testRadianToDegree() {
        assertEquals(45, Utils.radianToDegree(0.7853981633974484), THRESHOLD);
    }

    @Test
    public void testXmlReader() throws IOException, SAXException, ParserConfigurationException { assertEquals(null, Utils.xmlReader(), THRESHOLD);}

    @Test
    public void getDataFromDocument() { assertEquals(null, Utils.getDataFromDocument(null), THRESHOLD);}
}

