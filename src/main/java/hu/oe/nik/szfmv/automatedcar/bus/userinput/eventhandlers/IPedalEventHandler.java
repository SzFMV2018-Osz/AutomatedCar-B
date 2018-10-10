package hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers;

public interface IPedalEventHandler {
    /**
     * Notifies when the pedal is pushed
     */
    void onPedalPush();

    /**
     * Notifies when the pedal is released
     */
    void onPedalRelease();
}
