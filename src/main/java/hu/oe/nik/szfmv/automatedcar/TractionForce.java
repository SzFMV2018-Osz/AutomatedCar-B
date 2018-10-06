package hu.oe.nik.szfmv.automatedcar;

public class TractionForce {

    public static double[] calculateTractionForce(final double[] orientationVector, final double driveTorque,
            final double wheelRadius) {
        final double tractionForceScalar = driveTorque / wheelRadius;
        return new double[] { orientationVector[0] * tractionForceScalar, orientationVector[1] * tractionForceScalar };
    }
}
