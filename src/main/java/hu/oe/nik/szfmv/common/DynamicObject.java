package hu.oe.nik.szfmv.common;

public class DynamicObject extends ParentObject {



    public enum TypeEnumDynamic {
        CAR, MAN, WOMAN, BICYCLE
    }

    public TypeEnumDynamic Type;

    public DynamicObject(hu.oe.nik.szfmv.common.Position p, hu.oe.nik.szfmv.common.Transform t, String type) {
        super(p, t);

        switch (type)
        {
            case "car":
                this.Type = TypeEnumDynamic.CAR;
            case "man":
                this.Type = TypeEnumDynamic.MAN;
            case "bicycle":
                this.Type = TypeEnumDynamic.BICYCLE;
            case "woman":
                this.Type = TypeEnumDynamic.WOMAN;
            default:
                this.Type = null;
                break;
        }

    }

    public void Move()
    {

    }


    @Override
    public hu.oe.nik.szfmv.common.Position GetPosition() {
        return super.GetPosition();
    }

    @Override
    public hu.oe.nik.szfmv.common.Transform GetTransform() {
        return super.GetTransform();
    }

    @Override
    public void SetPosition(hu.oe.nik.szfmv.common.Position p) {
        super.SetPosition(p);
    }

    @Override
    public void SetTransform(hu.oe.nik.szfmv.common.Transform t) {
        super.SetTransform(t);
    }

    public TypeEnumDynamic GetType()
    {
        return this.Type;
    }

    public void SetType (TypeEnumDynamic Type)
    {
        this.Type = Type;
    }
}
