package hu.oe.nik.szfmv.automatedcar.engine;

public class CarAxisParamsImpl implements CarAxisParams {


    final int pixelToMeter = 50;
    final int carWidthPixel = 90;
    final int axisLengthPixel = 130;

    @Override
    public int getCarWidthPixel() {
        return carWidthPixel;
    }

    @Override
    public int getAxisLengthPixel() {
        return axisLengthPixel;
    }

    @Override
    public int getPixelToMeter() {
        return pixelToMeter;
    }

}
