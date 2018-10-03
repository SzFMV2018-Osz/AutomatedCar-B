package hu.oe.nik.szfmv.common;

public class Position {

    public int X;
    public int Y;

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
}
