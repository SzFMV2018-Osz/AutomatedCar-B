package hu.oe.nik.szfmv.automatedcar.engine.exception;

import hu.oe.nik.szfmv.automatedcar.engine.TransmissionModes;

public class TransmissionModeChangeException extends Exception {

    private TransmissionModes current;
    private TransmissionModes next;

    /**
     * Transmission mode change exception when you cant changed mode
     *
     * @param current current transmission
     * @param next    next transmission
     */
    public TransmissionModeChangeException(TransmissionModes current, TransmissionModes next) {
        this.current = current;
        this.next = next;
    }

    public TransmissionModes getCurrent() {
        return current;
    }

    public TransmissionModes getNext() {
        return next;
    }
}
