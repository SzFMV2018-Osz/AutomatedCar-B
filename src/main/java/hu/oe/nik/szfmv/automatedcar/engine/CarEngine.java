package hu.oe.nik.szfmv.automatedcar.engine;

public class CarEngine {
    private final int gearRatioMultiplyer = 60;
    private final int throttlePositionDivider = 100;

    private CarEngineType engineType;
    private int rpm;

    /**
     * @param engineType the type of the engine
     */
    public CarEngine(final CarEngineType engineType) {
        this.engineType = engineType;
        rpm = 0;
    }

    public int getRpm() {
        return rpm;
    }

    private double getWheelrotationRate(int speed) {
        return speed / engineType.getWheelRadius();
    }

    /**
     * @param currentGear the current gear
     */
    public void updateRpm(int speed, final int currentGear) {
        rpm = (int) ((getWheelrotationRate(speed) * engineType.getGearRatios()[currentGear]
                * engineType.getGearDifferentialRatio() * gearRatioMultiplyer) / (2 * Math.PI));
    }

    /**
     * @param throttlePosition the position of the gas pedal
     * @return the engine torque that the motor produces
     */
    public double calculateEngineTorque(final int throttlePosition) {
        return ((double) throttlePosition / throttlePositionDivider) * lookupMaxTorque();
    }

    private double lookupMaxTorque() {
        if (rpm < engineType.getTorqueCurveStepSize()) {
            return engineType.getTorqueCurve()[0];
        } else {
            return calculateMaxTorque();
        }
    }

    private double calculateMaxTorque() {
        int closestLookupPoint = getClosestLookupPoint();
        if (closestLookupPoint != (engineType.getTorqueCurve().length - 1)) {
            return linearInterpolarMaxTorque(closestLookupPoint);
        } else {
            return engineType.getTorqueCurve()[closestLookupPoint];
        }
    }

    private int getClosestLookupPoint() {
        int closestLookupPoint = (rpm / 1000) - 1;
        if (closestLookupPoint >= engineType.getTorqueCurve().length) {
            closestLookupPoint = engineType.getTorqueCurve().length - 1;
        }
        return closestLookupPoint;
    }

    private double linearInterpolarMaxTorque(final int closestLookupPoint) {
        final double lookupTorqueDiff = engineType.getTorqueCurve()[closestLookupPoint + 1]
                - engineType.getTorqueCurve()[closestLookupPoint];
        final double rpmDiffToLookupPoint = (rpm - (engineType.getTorqueCurveStepSize() * (closestLookupPoint + 1)));
        final double unit = lookupTorqueDiff / engineType.getTorqueCurveStepSize();
        return engineType.getTorqueCurve()[closestLookupPoint] + (rpmDiffToLookupPoint * unit);
    }

    /**
     * @param throttlePosition the position of the gas pedal
     * @param currentGear      the current gear of the gearbox
     * @return the drive torque that the motor produces
     */
    public double calculateDriveTorque(final int throttlePosition, final int currentGear) {
        final double engineTorque = calculateEngineTorque(throttlePosition);
        return engineTorque * engineType.getGearRatios()[currentGear] * engineType.getGearDifferentialRatio()
                * engineType.getTransmissionEffiency();
    }
}
