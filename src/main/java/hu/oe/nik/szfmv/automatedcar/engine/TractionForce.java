package hu.oe.nik.szfmv.automatedcar.engine;

public class TractionForce {

    /**
     * @param driveTorque drive torque that the motor produces
     * @param wheelRadius radius of the car wheel
     * @return array of traction force vector components
     */
    public static double[] calculateTractionForce(final double driveTorque,
                                                  final double wheelRadius) {
        final double tractionForceScalar = driveTorque / wheelRadius;
        return new double[]{tractionForceScalar, tractionForceScalar};
    }
}
