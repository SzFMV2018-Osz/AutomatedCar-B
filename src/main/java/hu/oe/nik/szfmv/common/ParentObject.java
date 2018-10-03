package hu.oe.nik.szfmv.common;

public abstract class ParentObject {

    private Position Position;
    private Transform Transform;

    public ParentObject(Position p, Transform t)
    {
        this.Position = p;
        this.Transform = t;
    }

}
