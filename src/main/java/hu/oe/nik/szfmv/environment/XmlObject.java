package hu.oe.nik.szfmv.environment;
public class XmlObject {

    int x;
    int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getType() {
        return type;
    }

    public double[][] getMx() {
        return mx;
    }

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
