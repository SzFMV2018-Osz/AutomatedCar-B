package hu.oe.nik.szfmv.automatedcar;

public class TractionForce {

    /**
     * @param orientationVector unit vector that represents the orientation of the
     *                          car
     * @param driveTorque       drive torque that the motor produces
     * @param wheelRadius       radius of the car wheel
     * @return array of traction force vector components
     */
    public static double[] calculateTractionForce(final double[] orientationVector, final double driveTorque,
            final double wheelRadius) {
        final double tractionForceScalar = driveTorque / wheelRadius;
        return new double[] { orientationVector[0] * tractionForceScalar, orientationVector[1] * tractionForceScalar };
    }
}
