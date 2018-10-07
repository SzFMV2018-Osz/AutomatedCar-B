package hu.oe.nik.szfmv.common;

public abstract class ParentObject {

    private Position Position;
    private Transform Transform;

    public ParentObject(Position p, Transform t)
    {
        this.Position = p;
        this.Transform = t;
    }

    public Position GetPosition()
    {
        return this.Position;
    }

    public Transform GetTransform()
    {
        return  this.GetTransform();
    }


    public void SetPosition(Position p)
    {
        this.Position = p;
    }

    public void SetTransform(Transform t) { this.Transform  = t; }
}
