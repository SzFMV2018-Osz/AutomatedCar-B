package hu.oe.nik.szfmv.common;

import java.util.List;

public class Scene {
    public int xSize;
    public int ySize;
    public ControllableCar Car;
    public List<DynamicObject> dynamicList;
    public List<StaticObject> staticList;

    public Scene(int xsize, int ysize, ControllableCar car, List<DynamicObject> dyn, List<StaticObject> stat)
    {
        this.xSize = xsize;
        this.ySize = ysize;
        this.Car = car;
        this.dynamicList = dyn;
        this.staticList = stat;
    }

    public int GetXSize()
    {
        return this.xSize;
    }

    public void SetXSize(int xs)
    {
        this.xSize = xs;
    }

    public int GetYSize()
    {
        return  this.ySize;
    }

    public void SetYSize(int ys)
    {
        this.ySize = ys;
    }

    public ControllableCar GetCar()
    {
        return  this.Car;
    }

    public void SetCar(ControllableCar c)
    {
        this.Car = c;
    }

    public List<DynamicObject> GetDynamicList()
    {
        return this.dynamicList;
    }

    public void SetDynamicList(List<DynamicObject> dy)
    {
        this.dynamicList = dy;
    }

    public List<StaticObject> GetStaticList()
    {
        return this.staticList;
    }

    public void SetStaticList(List<StaticObject> s)
    {
        this.staticList = s;
    }

}
