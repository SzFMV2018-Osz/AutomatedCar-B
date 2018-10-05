package hu.oe.nik.szfmv.automatedcar;

/**
 * Contains functions and constants to calculate
 * physical forces
 */
public class CarPhysics {
    private static double aeroDrag = 0.05;

    /**
     * @param vx the x component of the velocity vector
     * @param vy the y component of the velocity vector
     * @return air resistance array of component values
     */
    public static double[] calcAirResistanceVector(double vx, double vy) {
        double[] aDrag = new double[2];

        if (vx != 0.0 || vy != 0.0) {
            double vLen = Math.sqrt(vx * vx + vy * vy);

            aDrag[0] = vx * vLen * -aeroDrag;
            aDrag[1] = vy * vLen * -aeroDrag;
        }

        return aDrag;
    }
}