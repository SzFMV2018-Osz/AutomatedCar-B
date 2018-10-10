package hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces;

public interface IGraduallyChangeable {
    void startNew(int from, int to, int milliseconds);

    int getCurrentValue();
}
