package hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers;

public interface IPedalEventHandler {
    void onGasPedalPush();
    void onGasPedalRelease();
    void onBrakePedalPush();
    void onBrakePedalRelease();
}
