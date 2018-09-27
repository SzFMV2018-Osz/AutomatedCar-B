package hu.oe.nik.szfmv.automatedcar.engine;

public interface CarEngine {

    int getTorqueCurveStepSize();

    int[] getTorqueCurve();

    double[] getGearRatios();

    int getGearCount();

    int getMaxRpm();

    int getGearShiftRpm();

    int getBackGearShiftRpm();

    double getGearDifferentialRatio();
}
