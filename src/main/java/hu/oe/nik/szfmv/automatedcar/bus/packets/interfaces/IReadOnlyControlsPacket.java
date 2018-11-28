package hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces;

public interface IReadOnlyControlsPacket {
    /**
     * Gets the name of the key of Gas
     *
     * @return returns the name as a String type
     */
    String getGasKeyText();

    /**
     * Gets the name of the key of Break
     *
     * @return returns the name as a String type
     */
    String getBrakeKeyText();

    /**
     * Gets the name of the key of SteerLeft
     *
     * @return returns the name as a String type
     */
    String getSteerLeftKeyText();

    /**
     * Gets the name of the key of SteerRight
     *
     * @return returns the name as a String type
     */
    String getSteerRightKeyText();

    /**
     * Gets the name of the key of IndicateLeft
     *
     * @return returns the name as a String type
     */
    String getIndicateLeftKeyText();

    /**
     * Gets the name of the key of IndicateRight
     *
     * @return returns the name as a String type
     */
    String getIndicateRightKeyText();

    /**
     * Gets the name of the key of gear R
     *
     * @return returns the name as a String type
     */
    String getGearRKeyText();

    /**
     * Gets the name of the key of gear P
     *
     * @return returns the name as a String type
     */
    String getGearPKeyText();

    /**
     * Gets the name of the key of gear N
     *
     * @return returns the name as a String type
     */
    String getGearNKeyText();

    /**
     * Gets the name of the key of gear D
     *
     * @return returns the name as a String type
     */
    String getGearDKeyText();
}