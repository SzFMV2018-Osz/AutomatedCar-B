package hu.oe.nik.szfmv.automatedcar.engine;

public class StandardCarEngine implements CarEngine {

    private final int gearCount;
    private final int maxRpm;
    private final int gearShiftRpm;
    private final double gearDifferentialRatio;
    private final double[] gearRatios;
    private final int[] torqueCurve;
    private final int torqueCurveStepSize;
    private final int backGearShiftRpm;

    public StandardCarEngine() {
	this.gearCount = 6;
	this.maxRpm = 6000;
	this.gearShiftRpm = 3000;
	this.gearDifferentialRatio = 3;
	this.gearRatios = new double[] { 2.9, 2.6, 1.7, 1.3, 1, 0.7, 0.5 };
	this.torqueCurve = new int[] { 400, 440, 460, 480, 470, 400 };
	this.torqueCurveStepSize = 1000;
	this.backGearShiftRpm = 1500;
    }

    @Override
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
}
