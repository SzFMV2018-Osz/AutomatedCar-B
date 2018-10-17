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

    /**
     * Calculate car oriente vector
     *
     * @param currentAngularVector current angular vector
     * @param angularVelocity      current angular speed
     * @return new oriente vector
     */
    public double[] angularVector(double[] currentAngularVector, double angularVelocity) {
        double[] newAngularVector = new double[2];
        newAngularVector[0] = currentAngularVector[0] *
                Math.cos(angularVelocity) - currentAngularVector[1] * Math.sin(angularVelocity);
        newAngularVector[1] = currentAngularVector[0] *
                Math.sin(angularVelocity) + currentAngularVector[1] * Math.cos(angularVelocity);
        return newAngularVector;
    }


}
