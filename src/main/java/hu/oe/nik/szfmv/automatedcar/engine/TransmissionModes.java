package hu.oe.nik.szfmv.automatedcar.engine;

public enum TransmissionModes {
    Park(false), Reverse(true), Drive(true), Neutral(false);

    private final boolean canItMove;

    /**
     * transsmission mode constructor
     * @param canItMove you accelerate if it is true
     */
    TransmissionModes(final boolean canItMove) {
        this.canItMove = canItMove;
    }

    public boolean getCanItMove() {
        return this.canItMove;
    }
}



