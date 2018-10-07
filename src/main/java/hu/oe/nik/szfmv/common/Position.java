package hu.oe.nik.szfmv.common;

public class Position {

    private int X;
    private int Y;

    public Position(int x, int y)
    {
        this.X = x;
        this.Y = y;
    }

    public int GetX()
    {
        return this.X;
    }

    public void SetX(int x)
    {
        this.X = x;
    }

    public int GetY()
    {
        return this.Y;
    }

    public void SetY(int y)
    {
        this.Y = y;
    }
}
