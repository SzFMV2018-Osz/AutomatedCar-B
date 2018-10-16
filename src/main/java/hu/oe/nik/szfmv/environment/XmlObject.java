package hu.oe.nik.szfmv.environment;

public class XmlObject {

    int x;
    int y;
    String type;
    double[][] mx;

    public XmlObject(int x, int y, String type, double[][] mx)
    {
        this.x = x;
        this.y = y;
        this.type = type;
        this.mx = mx;
    }
}
