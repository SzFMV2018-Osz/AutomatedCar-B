package hu.oe.nik.szfmv.automatedcar.engine;

public class CarAxisParamsImpl implements CarAxisParams {

    final int carWidthPixel = 90;
    final int axisLengthPixel = 130;
    final int maxTurningPercent = 60;

    @Override
    public int getCarWidthPixel() {
        return carWidthPixel;
    }

    @Override
    public int getAxisLengthPixel() {
        return axisLengthPixel;
    }

    @Override
    public int getMaxTurningPercent() {
        return maxTurningPercent;
    }


}
