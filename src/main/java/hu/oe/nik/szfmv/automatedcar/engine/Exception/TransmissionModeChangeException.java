package hu.oe.nik.szfmv.automatedcar.engine.Exception;

import hu.oe.nik.szfmv.automatedcar.engine.TransmissionModes;

public class TransmissionModeChangeException extends Exception {


    public TransmissionModes getCurrent() {
        return current;
    }


    private TransmissionModes current;

    public TransmissionModes getNext() {
        return next;
    }

    private TransmissionModes next;

    public TransmissionModeChangeException(TransmissionModes current, TransmissionModes next) {
        this.current = current;
        this.next = next;
    }
}
