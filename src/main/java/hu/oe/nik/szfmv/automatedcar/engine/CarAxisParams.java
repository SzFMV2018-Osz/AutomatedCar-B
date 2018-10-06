package hu.oe.nik.szfmv.automatedcar.engine;

public interface CarAxisParams {

    /**
     * @return car width in pixel
     */
    int getCarWidthPixel();

    /**
     * @return axis legth in pixel
     */
    int getAxisLengthPixel();

    /**
     * @return magic number to calculation meter
     */
    int getPixelToMeter();

}
