package hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers;

public interface IPedalEventHandler {

    /**
     * Method to call on pedal push.
     */
    void onPedalPush();

    /**
     * Method to call on pedal release.
     */
    void onPedalRelease();
}
