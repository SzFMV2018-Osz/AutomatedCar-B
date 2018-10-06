package hu.oe.nik.szfmv.automatedcar.engine;

public class CarEngine {

    private CarEngineType engineType;
    private int rpm;

    public CarEngine(final CarEngineType engineType) {
        this.engineType = engineType;
        rpm = 0;
    }

    public int getRpm() {
        return rpm;
    }

    public void updateRpm(final double wheelRotationRate, final int currentGear) {
        rpm = (int) ((wheelRotationRate * engineType.getGearRatios()[currentGear]
                * engineType.getGearDifferentialRatio() * 60) / (2 * Math.PI));
    }
}
