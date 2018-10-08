package hu.oe.nik.szfmv.environment;

public class ControllableCar extends DynamicObject {


    public ControllableCar(int x, int y, String imageFileName, String type) {
        super(x, y, imageFileName, type);
    }

    @Override
    public void Move() {
        super.Move();
    }

    @Override
    public TypeEnumDynamic GetType() {
        return super.GetType();
    }

    @Override
    public void SetType(TypeEnumDynamic Type) {
        super.SetType(Type);
    }

    @Override
    public int getX() {
        return super.getX();
    }

    @Override
    public int getY() {
        return super.getY();
    }

    @Override
    public int getWidth() {
        return super.getWidth();
    }

    @Override
    public int getHeight() {
        return super.getHeight();
    }

    @Override
    public float getRotation() {
        return super.getRotation();
    }

    @Override
    public String getImageFileName() {
        return super.getImageFileName();
    }

    @Override
    public void setX(int x) {
        super.setX(x);
    }

    @Override
    public void setY(int y) {
        super.setY(y);
    }

    @Override
    public void setWidth(int width) {
        super.setWidth(width);
    }

    @Override
    public void setHeight(int height) {
        super.setHeight(height);
    }

    @Override
    public void setRotation(float rotation) {
        super.setRotation(rotation);
    }

    @Override
    public void setImageFileName(String imageFileName) {
        super.setImageFileName(imageFileName);
    }
}