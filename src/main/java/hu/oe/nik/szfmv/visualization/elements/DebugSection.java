package hu.oe.nik.szfmv.visualization.elements;

import javax.swing.*;
import java.awt.*;

public class DebugSection extends JPanel {
    private final int sectionPositionX = 10;
    private final int sectionPositionY = 610;

    private final int sectionRowSize = 15;

    private final int mainDebugOffset = 0;
    private final int steeringDebugOffset = 1;
    private final int positionDebugOffset = 2;

    public int getMainDebugOffset() {
        return sectionRowSize * mainDebugOffset;
    }

    public int getSteeringDebugOffset() {
        return sectionRowSize * steeringDebugOffset;
    }

    public int getPositionDebugOffset() {
        return sectionRowSize * positionDebugOffset;
    }

    public String getMainDebugText() {
        return "debug:";
    }

    public String getSteeringDebugText() {
        return "steering wheel: ";
    }

    public String getPositionTextX() {
        return "x: ";
    }

    public String getPositionTextY() {
        return ", y: ";
    }

    /**
     * Creates and returns a JLabel with the specified parameters
     *
     * @param text      - text for the label
     * @param rowOffset - determines the y coordinate for the label
     * @return - returns the created JLabel
     */
    public JLabel getDebugLabel(String text, int rowOffset) {
        JLabel debugLabel = new JLabel(text);

        setupLabel(debugLabel, rowOffset);

        return debugLabel;
    }

    /**
     * Generates a main debug label
     *
     * @return - returns the configured label object
     */
    public JLabel initialiseDebugLabel() {
        return getDebugLabel(getMainDebugText(), getMainDebugOffset());
    }

    /**
     * Generates a steering wheel debug label
     *
     * @return - returns the configured label object
     */
    public JLabel initialiseSteeringWheelLabel() {
        return getDebugLabel(getSteeringDebugText(), getSteeringDebugOffset());
    }

    /**
     * Generates a position debug label
     *
     * @return - returns the configured label object
     */
    public JLabel initialisePositionLabel() {
        return getDebugLabel(getPositionTextX() + 0 + getPositionTextY() + 0, getPositionDebugOffset());
    }

    /**
     * Actualises the bounds and text color for the given JLabel
     *
     * @param label     - the modifiable JLabel's reference
     * @param rowOffset - determines the y coordinate for the label
     */
    public void setupLabel(JLabel label, int rowOffset) {
        Insets insets = getInsets();
        Dimension labelSize = label.getPreferredSize();

        int boundsX = sectionPositionX + insets.left;
        int boundsY = sectionPositionY + insets.top + rowOffset;

        label.setBounds(boundsX, boundsY, labelSize.width, labelSize.height);
        label.setForeground(Color.black);
    }
}