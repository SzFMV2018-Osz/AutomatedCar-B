package hu.oe.nik.szfmv.automatedcar.engine;

public interface CarEngineType {

    /**
     * @return the step size between the torque curve point, given in RPM
     */
    int getTorqueCurveStepSize();

    /**
     * @return array of samples from the torque curve of the engine
     */
    int[] getTorqueCurve();

    /**
     * @return the gear ratio values for each of the gears
     */
    double[] getGearRatios();

    /**
     * @return the number of gears
     */
    int getGearCount();

    /**
     * @return the maximum RPM for the engine
     */
    int getMaxRpm();

    /**
     * @return the RPM value when the automated gear shifting happens
     */
    int getGearShiftRpm();

    /**
     * @return the RPM value when the automated down shifting happens
     */
    int getBackGearShiftRpm();

    /**
     * @return the differential ration for the gears
     */
    double getGearDifferentialRatio();

    /**
     * @return the transmission effience for the gearbox
     */
    double getTransmissionEffiency();

    /**
     * @return return with wheel radius
     */
    double getWheelRadius();
}
