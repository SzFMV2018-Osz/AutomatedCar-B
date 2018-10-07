package hu.oe.nik.szfmv.common;


public class StaticObject extends ParentObject {


    public enum TypeEnum {
        TREE, ROADSIGN_PARKING_RIGHT, ROADSIGN_SPEED_40, ROADSIGN_SPEED_50, ROADSIGN_SPEED_60, ROADSIGN_PRIORITY_STOP, CAR, MAN,
        WOMAN, BOUNDARY, BOLLARD, CROSSROAD, BICYCLE, GARAGE, CROSSWALK, PARKING_SPACE_PARALELL, ROAD_2LANE_STRAIGHT,
        ROAD_2LANE_90RIGHT, ROAD_2LANE_45RIGHT,ROAD_2LANE_TJUNCTIONLEFT, ROAD_2LANE_TJUNCTIONRIGHT, PARKING_90
    }

    private TypeEnum Type;
    private boolean Collidable;

    public StaticObject(hu.oe.nik.szfmv.common.Position p, hu.oe.nik.szfmv.common.Transform t, String type) {
        super(p, t);


        switch (type)
        {
            case "tree": this.Type = TypeEnum.TREE; this.Collidable = true;
            case "road_2lane_straight": this.Type = TypeEnum.ROAD_2LANE_STRAIGHT; this.Collidable = false;
            case "road_2lane_90right": this.Type = TypeEnum.ROAD_2LANE_90RIGHT; this.Collidable = false;
            case "road_2lane_45right": this.Type = TypeEnum.ROAD_2LANE_45RIGHT; this.Collidable = false;
            case "road_2lane_tjunctionleft": this.Type = TypeEnum.ROAD_2LANE_TJUNCTIONLEFT; this.Collidable = false;
            case "road_2lane_tjunctionright": this.Type = TypeEnum.ROAD_2LANE_TJUNCTIONRIGHT; this.Collidable = false;
            case "parking_space_parallel": this.Type = TypeEnum.PARKING_SPACE_PARALELL; this.Collidable = false;
            case "roadsign_speed_50": this.Type = TypeEnum.ROADSIGN_SPEED_50; this.Collidable = true;
            case "roadsign_speed_60": this.Type = TypeEnum.ROADSIGN_SPEED_60; this.Collidable = true;
            case "roadsign_speed_40": this.Type = TypeEnum.ROADSIGN_SPEED_40; this.Collidable = true;
            case "roadsign_priority_stop": this.Type = TypeEnum.ROADSIGN_PRIORITY_STOP; this.Collidable = true;
            case "roadsign_parking_right": this.Type = TypeEnum.ROADSIGN_PARKING_RIGHT; this.Collidable = true;
            case "crosswalk": this.Type = TypeEnum.CROSSWALK; this.Collidable = false;

            case "parking_90": this.Type = TypeEnum.PARKING_90; this.Collidable = false;
            case "man": this.Type = TypeEnum.MAN; this.Collidable = true;
            case "woman": this.Type = TypeEnum.WOMAN; this.Collidable = true;
            case "car": this.Type = TypeEnum.CAR; this.Collidable = true;
            case "boundary": this.Type = TypeEnum.BOUNDARY; this.Collidable = true;
            case "bollard": this.Type = TypeEnum.BOLLARD; this.Collidable = true;
            case "crossroad": this.Type = TypeEnum.CROSSROAD; this.Collidable = false;
            case "bicycle": this.Type = TypeEnum.BICYCLE; this.Collidable = true;
            case "garage": this.Type = TypeEnum.GARAGE; this.Collidable = true;


            default: this.Type = null; this.Collidable = false;
            break;
        }
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
