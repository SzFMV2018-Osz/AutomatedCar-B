package hu.oe.nik.szfmv.automatedcar;

/**
 * Contains functions and constants to calculate physical forces
 */
public class BrakingForces {
    private static double aeroDrag = 0.05;
    private static double maxBrakeForce = 0.5;
    private static double breakPedalPercentageMultiplyer = 0.01;

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
            final double len = Math.sqrt((vx * vx) + (vy * vy));
            final double[] vUnit = { vx / len, vy / len };

            final double percentage = brakePedal * breakPedalPercentageMultiplyer;

            // calc break force
            brakeForce[0] = (-1 * vUnit[0]) * maxBrakeForce * percentage;
            brakeForce[1] = (-1 * vUnit[1]) * maxBrakeForce * percentage;

            // if break force is larger than the heading eq it.
            final double breakLen = Math.sqrt((brakeForce[0] * brakeForce[0]) + (brakeForce[1] * brakeForce[1]));

            if (breakLen > len) {
                brakeForce[0] = -vx;
                brakeForce[1] = -vy;
            }
        }

        return brakeForce;
    }
}