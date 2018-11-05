package hu.oe.nik.szfmv.visualization.elements;

import javax.swing.*;
import java.awt.*;

public class ControlsSection extends JPanel {
    private final int sectionPositionX = 10;
    private final int sectionPositionY = 610;

    private final int sectionRowSize = 15;

    private final int mainDebugOffset = 0;
    private final int steeringDebugOffset = 1;
    private final int positionDebugOffset = 2;

    private int getMainDebugOffset() {
        return sectionRowSize * mainDebugOffset;
    }

    private int getSteeringDebugOffset() {
        return sectionRowSize * steeringDebugOffset;
    }

    private int getPositionDebugOffset() {
        return sectionRowSize * positionDebugOffset;
    }

    private String getMainDebugText() {
        return "debug:";
    }

    private String getSteeringDebugText() {
        return "steering wheel: ";
    }

    private String getPositionTextX() {
        return "x: ";
    }

    private String getPositionTextY() {
        return ", y: ";
    }

    /**
     * Creates and returns a JLabel with the specified parameters
     *
     * @param text      - text for the label
     * @param rowOffset - determines the y coordinate for the label
     * @return - returns the created JLabel
     */
    private JLabel getDebugSectionLabel(String text, int rowOffset) {
        JLabel debugLabel = new JLabel(text);

        setupDebugSectionLabel(debugLabel, rowOffset);

        return debugLabel;
    }

    /**
     * Actualises the bounds and text color for the given JLabel
     *
     * @param label     - the modifiable JLabel's reference
     * @param rowOffset - determines the y coordinate for the label
     */
    private void setupDebugSectionLabel(JLabel label, int rowOffset) {
        Insets insets = getInsets();
        Dimension labelSize = label.getPreferredSize();

        int boundsX = sectionPositionX + insets.left;
        int boundsY = sectionPositionY + insets.top + rowOffset;

        label.setBounds(boundsX, boundsY, labelSize.width, labelSize.height);
        label.setForeground(Color.black);
    }

    /**
     * Generates a main debug label
     *
     * @return - returns the configured label object
     */
    public JLabel initialiseDebugLabel() {
        return getDebugSectionLabel(getMainDebugText(), getMainDebugOffset());
    }

    /**
     * Generates a steering wheel debug label
     *
     * @return - returns the configured label object
     */
    public JLabel initialiseSteeringWheelLabel() {
        return getDebugSectionLabel(getSteeringDebugText(), getSteeringDebugOffset());
    }

    /**
     * Generates a position debug label
     *
     * @return - returns the configured label object
     */
    public JLabel initialisePositionLabel() {
        return getDebugSectionLabel(getPositionTextX() + 0 + getPositionTextY() + 0, getPositionDebugOffset());
    }

    /**
     * Refreshes the steering wheel position label
     *
     * @param steeringWheelLabel - the modifiable JLabel
     * @param value - the new steering wheel value
     */
    public void setSteeringLabelText(JLabel steeringWheelLabel, int value) {
        if (value > 0) {
            steeringWheelLabel.setText(getSteeringDebugText() + "+" + value);
        } else {
            steeringWheelLabel.setText(getSteeringDebugText() + value);
        }

        setupDebugSectionLabel(steeringWheelLabel, getSteeringDebugOffset());
    }

    /**
     * Refreshes the position label
     *
     * @param positionLabel - the modifiable JLabel
     * @param x - the car's x position
     * @param y - the car's y position
     */
    public void setPositionLabelText(JLabel positionLabel, int x, int y) {
        positionLabel.setText(getPositionTextX() + x + getPositionTextY() + y);

        setupDebugSectionLabel(positionLabel, getPositionDebugOffset());
    }
}