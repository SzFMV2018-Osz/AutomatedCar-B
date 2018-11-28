package hu.oe.nik.szfmv.visualization.elements;

import javax.swing.*;
import java.awt.*;

public class ControlsSection extends JPanel {
    private final int sectionPositionX = 10;
    private final int sectionPositionY = 510;

    private final int sectionRowSize = 15;

    private final int mainControlsOffset = 0;
    private final int pedalsOffset = 1;
    private final int steerOffset = 2;
    private final int indicateOffset = 3;
    private final int gearsFirstOffset = 4;
    private final int gearsSecondOffset = 5;

    private final String placeholderText = "...";

    private int getMainControlsOffset() {
        return sectionRowSize * mainControlsOffset ;
    }
    private int getPedalsOffset() {
        return sectionRowSize * pedalsOffset ;
    }
    private int getSteerOffset() {
        return sectionRowSize * steerOffset;
    }
    private int getIndicateOffset() {
        return sectionRowSize * indicateOffset;
    }
    private int getGearsFirstOffset() {
        return sectionRowSize * gearsFirstOffset;
    }
    private int getGearsSecondOffset() {
        return sectionRowSize * gearsSecondOffset;
    }

    private String getMainControlsText() {
        return "Controls:";
    }
    private String getPedalsText(String gasKey, String breakKey) {
        return "Gas: " + gasKey + ", Break: " + breakKey;
    }
    private String getSteerText(String steerLeftKey, String steerRightKey) {
        return "Steer left: " + steerLeftKey + ", right: " + steerRightKey;
    }
    private String getIndicateText(String indicateLeftKey, String indicateRightKey) {
        return "Indicate left: " + indicateLeftKey + ", right: " + indicateRightKey;
    }
    private String getGearsFirstText(String gearDKey, String gearRKey) {
        return "Gear D: " + gearDKey + ", R: " + gearRKey;
    }
    private String getGearsSecondText(String gearNKey, String gearPKey) {
        return "Gear N: " + gearNKey + ", P: " + gearPKey;
    }

    /**
     * Creates and returns a JLabel with the specified parameters
     *
     * @param text      - text for the label
     * @param rowOffset - determines the y coordinate for the label
     * @return - returns the created JLabel
     */
    private JLabel createControlsSectionLabel(String text, int rowOffset) {
        JLabel controlsLabel = new JLabel(text);

        actualiseControlsSectionLabel(controlsLabel, rowOffset);

        return controlsLabel;
    }

    /**
     * Actualises the bounds and text color for the given JLabel
     *
     * @param label     - the modifiable JLabel's reference
     * @param rowOffset - determines the y coordinate for the label
     */
    private void actualiseControlsSectionLabel(JLabel label, int rowOffset) {
        Insets insets = getInsets();
        Dimension labelSize = label.getPreferredSize();

        int boundsX = sectionPositionX + insets.left;
        int boundsY = sectionPositionY + insets.top + rowOffset;

        label.setBounds(boundsX, boundsY, labelSize.width, labelSize.height);
        label.setForeground(Color.black);
    }

    /**
     * Generates a main controls label
     *
     * @return - returns the configured label object
     */
    public JLabel initialiseMainControlsLabel() {
        return createControlsSectionLabel(getMainControlsText(), getMainControlsOffset());
    }

    /**
     * Generates a pedal controls label
     *
     * @return - returns the configured label object
     */
    public JLabel initialisePedalControlsLabel() {
        return createControlsSectionLabel(getPedalsText(placeholderText, placeholderText), getPedalsOffset());
    }

    /**
     * Generates a steering controls label
     *
     * @return - returns the configured label object
     */
    public JLabel initialiseSteeringControlsLabel() {
        return createControlsSectionLabel(getSteerText(placeholderText, placeholderText), getSteerOffset());
    }

    /**
     * Generates a indicate controls label
     *
     * @return - returns the configured label object
     */
    public JLabel initialiseIndicateControlsLabel() {
        return createControlsSectionLabel(getIndicateText(placeholderText, placeholderText), getIndicateOffset());
    }

    /**
     * Generates a gear (first row) controls label
     *
     * @return - returns the configured label object
     */
    public JLabel initialiseGearsFirstControlsLabel() {
        return createControlsSectionLabel(getGearsFirstText(placeholderText, placeholderText), getGearsFirstOffset());
    }

    /**
     * Generates a gear (second row) controls label
     *
     * @return - returns the configured label object
     */
    public JLabel initialiseGearsSecondControlsLabel() {
        return createControlsSectionLabel(getGearsSecondText(placeholderText, placeholderText), getGearsSecondOffset());
    }

    /**
     * Refreshes the given label
     *
     * @param label - the label object
     * @param gasKey - the appropriate key name
     * @param breakKey - the appropriate key name
     */
    public void refreshPedalLabel(JLabel label, String gasKey, String breakKey) {
        label.setText(getPedalsText(gasKey, breakKey));

        actualiseControlsSectionLabel(label, getPedalsOffset());
    }

    /**
     * Refreshes the given label
     *
     * @param label - the label object
     * @param steerLeftKey - the appropriate key name
     * @param steerRightKey - the appropriate key name
     */
    public void refreshSteerLabel(JLabel label, String steerLeftKey, String steerRightKey) {
        label.setText(getSteerText(steerLeftKey, steerRightKey));

        actualiseControlsSectionLabel(label, getSteerOffset());
    }

    /**
     * Refreshes the given label
     *
     * @param label - the label object
     * @param indicateLeftKey - the appropriate key name
     * @param indicateRightKey - the appropriate key name
     */
    public void refreshIndicateLabel(JLabel label, String indicateLeftKey, String indicateRightKey) {
        label.setText(getIndicateText(indicateLeftKey, indicateRightKey));

        actualiseControlsSectionLabel(label, getIndicateOffset());
    }

    /**
     * Refreshes the given label
     *
     * @param label - the label object
     * @param gearDKey - the appropriate key name
     * @param gearRKey - the appropriate key name
     */
    public void refreshGearsFirstLabel(JLabel label, String gearDKey, String gearRKey) {
        label.setText(getGearsFirstText(gearDKey, gearRKey));

        actualiseControlsSectionLabel(label, getGearsFirstOffset());
    }

    /**
     * Refreshes the given label
     *
     * @param label - the label object
     * @param gearNKey - the appropriate key name
     * @param gearPKey - the appropriate key name
     */
    public void refreshGearsSecondLabel(JLabel label, String gearNKey, String gearPKey) {
        label.setText(getGearsSecondText(gearNKey, gearPKey));

        actualiseControlsSectionLabel(label, getGearsSecondOffset());
    }
}