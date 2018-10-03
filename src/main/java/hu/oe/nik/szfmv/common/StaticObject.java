package hu.oe.nik.szfmv.common;


public class StaticObject extends ParentObject {

    public enum TypeEnum {
        TREE, ROADSIGN_PARKING_RIGHT, ROADSIGN_SPEED_40, ROADSIGN_SPEED_50, ROADSIGN_SPEED_60, ROADSIGN_PRIORITY_STOP, CAR, MAN,
        WOMAN, BOUNDARY, BOLLARD, CROSSROAD, BICYCLE, GARAGE, CROSSWALK, PARKING_SPACE_PARALELL, ROAD_2LANE_STRAIGHT,
        ROAD_2LANE_90RIGHT, ROAD_2LANE_45RIGHT,ROAD_2LANE_TJUNCTIONLEFT, ROAD_2LANE_TJUNCTIONRIGHT, PARKING_90
    }

    public TypeEnum Type;
    public boolean Collidable;

    public StaticObject(hu.oe.nik.szfmv.common.Position p, hu.oe.nik.szfmv.common.Transform t, String type, boolean collidabe) {
        super(p, t);
        this.Collidable = collidabe;

        switch (type)
        {
            case "tree": this.Type = TypeEnum.TREE;
            case "road_2lane_straight": this.Type = TypeEnum.ROAD_2LANE_STRAIGHT;
            case "road_2lane_90right": this.Type = TypeEnum.ROAD_2LANE_90RIGHT;
            case "road_2lane_45right": this.Type = TypeEnum.ROAD_2LANE_45RIGHT;
            case "road_2lane_tjunctionleft": this.Type = TypeEnum.ROAD_2LANE_TJUNCTIONLEFT;
            case "road_2lane_tjunctionright": this.Type = TypeEnum.ROAD_2LANE_TJUNCTIONRIGHT;
            case "parking_space_parallel": this.Type = TypeEnum.PARKING_SPACE_PARALELL;
            case "roadsign_speed_50": this.Type = TypeEnum.ROADSIGN_SPEED_50;
            case "roadsign_speed_60": this.Type = TypeEnum.ROADSIGN_SPEED_60;
            case "roadsign_speed_40": this.Type = TypeEnum.ROADSIGN_SPEED_40;
            case "roadsign_priority_stop": this.Type = TypeEnum.ROADSIGN_PRIORITY_STOP;
            case "roadsign_parking_right": this.Type = TypeEnum.ROADSIGN_PARKING_RIGHT;
            case "crosswalk": this.Type = TypeEnum.CROSSWALK;

            case "parking_90": this.Type = TypeEnum.PARKING_90;
            case "man": this.Type = TypeEnum.MAN;
            case "woman": this.Type = TypeEnum.WOMAN;
            case "car": this.Type = TypeEnum.CAR;
            case "boundary": this.Type = TypeEnum.BOUNDARY;
            case "bollard": this.Type = TypeEnum.BOLLARD;
            case "crossroad": this.Type = TypeEnum.CROSSROAD;
            case "bicycle": this.Type = TypeEnum.BICYCLE;
            case "garage": this.Type = TypeEnum.GARAGE;


            default: this.Type = null;
            break;
        }
    }


    public TypeEnum GetType()
    {
        return this.Type;
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
