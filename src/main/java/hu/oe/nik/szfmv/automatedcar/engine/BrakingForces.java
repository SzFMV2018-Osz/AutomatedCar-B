package hu.oe.nik.szfmv.automatedcar.engine;

/**
 * Contains functions and constants to calculate physical forces
 */
public class BrakingForces {
    private static double aeroDrag = 10;
    private static double maxBrakeForce = 5000;
    private static double breakPedalPercentageMultiplyer = 0.01;
    private static double frictionFactor = 30 * aeroDrag;

    /**
     * @param vx the x component of the velocity vector
     * @param vy the y component of the velocity vector
     * @return air resistance array of vector component values
     */
    public static double[] calcAirResistanceVector(final double vx, final double vy) {
        final double[] aDrag = new double[2];

        if ((vx != 0.0) || (vy != 0.0)) {
            final double vLen = Math.sqrt((vx * vx) + (vy * vy));

            aDrag[0] = vx * vLen * -aeroDrag;
            aDrag[1] = vy * vLen * -aeroDrag;
        }

        return aDrag;
    }

    /**
     * @param vx         the x component of the velocity vector
     * @param vy         the y component of the velocity vector
     * @param brakePedal the state of the break pedal
     * @return array of brake force vector component values
     */
    public static double[] calcBrakeForceVector(final double vx, final double vy, final int brakePedal) {
        final double[] brakeForce = new double[2];

        if ((brakePedal != 0) && ((vx != 0.0) || (vy != 0.0))) {
            // get the unit of the vector

            final double percentage = brakePedal * breakPedalPercentageMultiplyer;

            // calc break force
            brakeForce[0] = (-1 * vx) * maxBrakeForce * percentage;
            brakeForce[1] = (-1 * vy) * maxBrakeForce * percentage;

            // if break force is larger than the heading eq it.
            // final double breakLen = Math.sqrt((brakeForce[0] * brakeForce[0]) +
            // (brakeForce[1] * brakeForce[1]));

//            if (breakLen > len) {
//                brakeForce[0] = -vx;
//                brakeForce[1] = -vy;
//            }
        }

        return brakeForce;
    }

    /**
     * @param vx the x component of the velocity vector
     * @param vy the y component of the velocity vector
     * @return array of rolling resistance vector component values
     */
    public static double[] calcRollingResistanceVector(double vx, double vy) {
        double[] rResistance = new double[2];

        if ((vx != 0.0) || (vy != 0.0)) {
            rResistance[0] = vx * (-frictionFactor);
            rResistance[1] = vy * (-frictionFactor);
        }

        return rResistance;
    }
}