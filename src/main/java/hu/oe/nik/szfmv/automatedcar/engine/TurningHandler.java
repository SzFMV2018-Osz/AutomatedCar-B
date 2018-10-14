package hu.oe.nik.szfmv.automatedcar.engine;

public class TurningHandler {


    CarAxisParams carAxisParams;

    /**
     * turning handler constructor inicialize car axis params
     */
    public TurningHandler() {
        this.carAxisParams = new CarAxisParamsImpl();
    }

    /**
     * Calculate new angular velocity(/speed)
     *
     * @param steeringWheelState steering wheel state [-60,60]
     * @param speed              car actual speed
     * @return current angular velocity
     */
    public double angularVelocityCalculation(final int steeringWheelState, double speed) {
        if (steeringWheelState == 0) {
            return 0;
        } else {
            return speed / turningCircleCalculation(steeringWheelState);
        }
    }


    private double turningCircleCalculation(final int steeringWheelState) {
        return (carAxisParams.getAxisLengthPixel()
                / Math.tan(Math.toRadians(steeringWheelState))
                + carAxisParams.getCarWidthPixel())
                / carAxisParams.getPixelToMeter();
    }

    public double[] angularVector(double[] currentAngularVector, double angularVelocity) {
        currentAngularVector[0] = currentAngularVector[0] * Math.cos(angularVelocity);
        currentAngularVector[1] = currentAngularVector[1] * Math.sin(angularVelocity);
        return currentAngularVector;
    }


}
