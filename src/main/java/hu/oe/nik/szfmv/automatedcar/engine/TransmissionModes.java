package hu.oe.nik.szfmv.automatedcar.engine;

public enum TransmissionModes {
    Park(false), Reverse(true), Drive(true), Neutral(false);

    private final boolean canItMove;

    TransmissionModes(final boolean canItMove) {
        this.canItMove = canItMove;
    }

    public boolean getCanItMove() {
        return this.canItMove;
    }
}



