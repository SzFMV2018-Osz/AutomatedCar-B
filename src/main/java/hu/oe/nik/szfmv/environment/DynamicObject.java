package hu.oe.nik.szfmv.environment;


public class DynamicObject extends WorldObject {

    private TypeEnumDynamic type;

    /**
     * @param x             x koordináta
     * @param y             y koordináta
     * @param imageFileName kép neve
     */
    public DynamicObject(int x, int y, String imageFileName) {
        super(x, y, imageFileName);
    }

    /**
     * @param x             x koordináta
     * @param y             y koordináta
     * @param imageFileName kép neve
     */
    public DynamicObject(int x, int y, String imageFileName, String type) {
        super(x, y, imageFileName);
        switch (type) {
            case "car":
                this.type = TypeEnumDynamic.CAR;
                break;
            case "man":
                this.type = TypeEnumDynamic.MAN;
                break;
            case "bicycle":
                this.type = TypeEnumDynamic.BICYCLE;
                break;
            case "woman":
                this.type = TypeEnumDynamic.WOMAN;
                break;
            default:
                this.type = null;
        }
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

    public enum TypeEnumDynamic {
        CAR, MAN, WOMAN, BICYCLE
    }

    /**
     *
     */
    public void move() {

    }

    public TypeEnumDynamic getType() {
        return this.type;
    }

    public void setType(TypeEnumDynamic type) {
        this.type = type;
    }
}
