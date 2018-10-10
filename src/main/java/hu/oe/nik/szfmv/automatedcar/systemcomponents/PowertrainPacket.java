package hu.oe.nik.szfmv.automatedcar.systemcomponents;

/**
 * The Data that will be passed to the VB thru the PacketInterface
 */
public class PowertrainPacket implements ReadonlyPowertrainPacket {
    private int rpm;
    private int gear;

    /**
     * @param rpm sets RPM
     */
    public void setRpm(int rpm) {
        this.rpm = rpm;
    }

    /**
     * @param gear sets gear
     */
    public void setGear(int gear) {
        this.gear = gear;
    }

    @Override
    public int getRpm() {
        return rpm;
    }

    @Override
    public int getGear() {
        return gear;
    }
}
