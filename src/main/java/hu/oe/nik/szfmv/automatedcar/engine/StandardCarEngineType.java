package hu.oe.nik.szfmv.automatedcar.engine;

public class StandardCarEngineType implements CarEngineType {

    private final int gearCount = 6;
    private final int maxRpm = 6000;
    private final int gearShiftRpm = 3000;
    private final double gearDifferentialRatio = 3;
    private final double[] gearRatios = new double[]{2.9, 2.6, 1.7, 1.3, 1, 0.7, 0.5};
    private final int[] torqueCurve = new int[]{400, 440, 460, 480, 470, 400};
    private final int torqueCurveStepSize = 1000;
    private final int backGearShiftRpm = 1500;
    private final double wheelRadius = 0.33;
    private double transmissionEffiency = 0.7;

    @Override
    public double getWheelRadius() {
        return wheelRadius;
    }

    public int getGearCount() {
        return gearCount;
    }

    @Override
    public int getMaxRpm() {
        return maxRpm;
    }

    @Override
    public int getGearShiftRpm() {
        return gearShiftRpm;
    }

    @Override
    public double getGearDifferentialRatio() {
        return gearDifferentialRatio;
    }

    @Override
    public double[] getGearRatios() {
        return gearRatios;
    }

    @Override
    public int[] getTorqueCurve() {
        return torqueCurve;
    }

    @Override
    public int getTorqueCurveStepSize() {
        return torqueCurveStepSize;
    }

    @Override
    public int getBackGearShiftRpm() {
        return backGearShiftRpm;
    }

    @Override
    public double getTransmissionEffiency() {
        return transmissionEffiency;
    }
}
