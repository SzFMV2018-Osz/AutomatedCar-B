package hu.oe.nik.szfmv.environment;


public class StaticObject extends WorldObject {
    public enum TypeEnum {
        TREE, ROADSIGN_PARKING_RIGHT, ROADSIGN_SPEED_40, ROADSIGN_SPEED_50, ROADSIGN_SPEED_60, ROADSIGN_PRIORITY_STOP,
        BOUNDARY, BOLLARD, CROSSROAD, GARAGE, CROSSWALK, PARKING_SPACE_PARALELL, ROAD_2LANE_STRAIGHT,
        ROAD_2LANE_90RIGHT, ROAD_2LANE_45RIGHT, ROAD_2LANE_TJUNCTIONLEFT, ROAD_2LANE_TJUNCTIONRIGHT, PARKING_90
    }

    private TypeEnum type;
    private boolean collidable;

    /**
     * @param x             x koordináta
     * @param y             x koordináta
     * @param imageFileName kép neve
     * @param type          typus
     */
    public StaticObject(int x, int y, String imageFileName, String type) {
        super(x, y, imageFileName);
        switch (type) {
            case "tree":
                this.type = TypeEnum.TREE;
                this.collidable = true;
                break;
            case "road_2lane_straight":
                this.type = TypeEnum.ROAD_2LANE_STRAIGHT;
                this.collidable = false;
                break;
            case "road_2lane_90right":
                this.type = TypeEnum.ROAD_2LANE_90RIGHT;
                this.collidable = false;
                break;
            case "road_2lane_45right":
                this.type = TypeEnum.ROAD_2LANE_45RIGHT;
                this.collidable = false;
                break;
            case "road_2lane_tjunctionleft":
                this.type = TypeEnum.ROAD_2LANE_TJUNCTIONLEFT;
                this.collidable = false;
                break;
            case "road_2lane_tjunctionright":
                this.type = TypeEnum.ROAD_2LANE_TJUNCTIONRIGHT;
                this.collidable = false;
                break;
            case "parking_space_parallel":
                this.type = TypeEnum.PARKING_SPACE_PARALELL;
                this.collidable = false;
                break;
            case "roadsign_speed_50":
                this.type = TypeEnum.ROADSIGN_SPEED_50;
                this.collidable = true;
                break;
            case "roadsign_speed_60":
                this.type = TypeEnum.ROADSIGN_SPEED_60;
                this.collidable = true;
                break;
            case "roadsign_speed_40":
                this.type = TypeEnum.ROADSIGN_SPEED_40;
                this.collidable = true;
                break;
            case "roadsign_priority_stop":
                this.type = TypeEnum.ROADSIGN_PRIORITY_STOP;
                this.collidable = true;
                break;
            case "roadsign_parking_right":
                this.type = TypeEnum.ROADSIGN_PARKING_RIGHT;
                this.collidable = true;
                break;
            case "crosswalk":
                this.type = TypeEnum.CROSSWALK;
                this.collidable = false;
                break;
            case "parking_90":
                this.type = TypeEnum.PARKING_90;
                this.collidable = false;
                break;
            case "boundary":
                this.type = TypeEnum.BOUNDARY;
                this.collidable = true;
                break;
            case "bollard":
                this.type = TypeEnum.BOLLARD;
                this.collidable = true;
                break;
            case "crossroad":
                this.type = TypeEnum.CROSSROAD;
                this.collidable = false;
                break;
            case "garage":
                this.type = TypeEnum.GARAGE;
                this.collidable = true;
                break;

            default:
                this.type = null;
                this.collidable = false;
                break;
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


    public TypeEnum getType() {
        return this.type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    public boolean getCollidable() {
        return this.collidable;
    }

    public void setCollidable(boolean collidable) {
        this.collidable = collidable;
    }


}
