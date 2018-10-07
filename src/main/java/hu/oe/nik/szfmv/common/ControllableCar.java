package hu.oe.nik.szfmv.common;

public class ControllableCar extends DynamicObject {


    public ControllableCar(hu.oe.nik.szfmv.common.Position p, hu.oe.nik.szfmv.common.Transform t, String type) {
        super(p, t, type);
    }

    @Override
    public void Move() {
        super.Move();
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

    @Override
    public TypeEnumDynamic GetType() {
        return super.GetType();
    }

    @Override
    public void SetType(TypeEnumDynamic Type) {
        super.SetType(Type);
    }


}