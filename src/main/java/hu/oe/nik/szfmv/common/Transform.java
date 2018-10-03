package hu.oe.nik.szfmv.common;

public class Transform {

    public double M11;
    public double M12;
    public double M21;
    public double M22;

    public Transform(double m11, double m12, double m21, double m22)
    {
        this.M11 = m11;
        this.M12 = m12;
        this.M21 = m21;
        this.M22 = m22;
    }

    public double GetM11()
    {
        return this.M11;
    }

    public void SetM11(double m11)
    {
        this.M11 = m11;
    }

    public double GetM12()
    {
        return this.M12;
    }

    public void SetM12(double m12)
    {
        this.M12 = m12;
    }

    public double GetM21()
    {
        return this.M21;
    }

    public void SetM21(double m21)
    {
        this.M21 = m21;
    }

    public double GetM22()
    {
        return this.M22;
    }

    public void SetM22(double m22)
    {
        this.M22 = m22;
    }




}
