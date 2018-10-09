package hu.oe.nik.szfmv.environment;




public class StaticObject extends WorldObject {
    public enum TypeEnum {
        TREE, ROADSIGN_PARKING_RIGHT, ROADSIGN_SPEED_40, ROADSIGN_SPEED_50, ROADSIGN_SPEED_60, ROADSIGN_PRIORITY_STOP, BOUNDARY, BOLLARD, CROSSROAD, GARAGE, CROSSWALK, PARKING_SPACE_PARALELL, ROAD_2LANE_STRAIGHT,
        ROAD_2LANE_90RIGHT, ROAD_2LANE_45RIGHT,ROAD_2LANE_TJUNCTIONLEFT, ROAD_2LANE_TJUNCTIONRIGHT, PARKING_90
    }

    private TypeEnum Type;
    private boolean Collidable;


    public StaticObject(int x, int y, String imageFileName, String type) {
        super(x, y, imageFileName);
        switch (type)
        {
            case "tree": this.Type = TypeEnum.TREE; this.Collidable = true;
                break;
            case "road_2lane_straight": this.Type = TypeEnum.ROAD_2LANE_STRAIGHT; this.Collidable = false;
                break;
            case "road_2lane_90right": this.Type = TypeEnum.ROAD_2LANE_90RIGHT; this.Collidable = false;
                break;
            case "road_2lane_45right": this.Type = TypeEnum.ROAD_2LANE_45RIGHT; this.Collidable = false;
                break;
            case "road_2lane_tjunctionleft": this.Type = TypeEnum.ROAD_2LANE_TJUNCTIONLEFT; this.Collidable = false;
                break;
            case "road_2lane_tjunctionright": this.Type = TypeEnum.ROAD_2LANE_TJUNCTIONRIGHT; this.Collidable = false;
                break;
            case "parking_space_parallel": this.Type = TypeEnum.PARKING_SPACE_PARALELL; this.Collidable = false;
                break;
            case "roadsign_speed_50": this.Type = TypeEnum.ROADSIGN_SPEED_50; this.Collidable = true;
                break;
            case "roadsign_speed_60": this.Type = TypeEnum.ROADSIGN_SPEED_60; this.Collidable = true;
                break;
            case "roadsign_speed_40": this.Type = TypeEnum.ROADSIGN_SPEED_40; this.Collidable = true;
                break;
            case "roadsign_priority_stop": this.Type = TypeEnum.ROADSIGN_PRIORITY_STOP; this.Collidable = true;
                break;
            case "roadsign_parking_right": this.Type = TypeEnum.ROADSIGN_PARKING_RIGHT; this.Collidable = true;
                break;
            case "crosswalk": this.Type = TypeEnum.CROSSWALK; this.Collidable = false;
                break;
            case "parking_90": this.Type = TypeEnum.PARKING_90; this.Collidable = false;
                break;
            case "boundary": this.Type = TypeEnum.BOUNDARY; this.Collidable = true;
                break;
            case "bollard": this.Type = TypeEnum.BOLLARD; this.Collidable = true;
                break;
            case "crossroad": this.Type = TypeEnum.CROSSROAD; this.Collidable = false;
                break;
            case "garage": this.Type = TypeEnum.GARAGE; this.Collidable = true;
                break;

            default: this.Type = null; this.Collidable = false;
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


    public TypeEnum GetType()
    {
        return this.Type;
    }

    public void SetType (TypeEnum Type)
    {
        this.Type = Type;
    }

    public boolean GetCollidable()
    {
        return  this.Collidable;
    }

    public void SetCollidable(boolean collidable)
    {
        this.Collidable = collidable;
    }



}
