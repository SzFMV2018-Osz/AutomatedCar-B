package hu.oe.nik.szfmv.environment;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


import java.util.ArrayList;

import static org.junit.Assert.*;

public class DataReaderTest {

    @Test
    public void XmlReader() {
        Document d = null;
        assertEquals(d,DataReader.xmlReader(""));}

    @Test
    public void getDataFromDocument() {
        Document d = null;
        assertEquals(new ArrayList<>(),DataReader.getDataFromDocument(d));
    }

    @Test
    public void createTransformMatrix() {
        Element e = null;
        assertEquals(new double[2][2], DataReader.createTransformMatrix(e));
    }

    @Test
    public void getSceneFromDocument() {
        Document d = null;
        assertEquals(null,DataReader.getSceneFromDocument(d));
    }

    @Test
    public void createObjectFromType() {
    }

    @Test
    public void getDataFromXmlDocument() {
    }
}
