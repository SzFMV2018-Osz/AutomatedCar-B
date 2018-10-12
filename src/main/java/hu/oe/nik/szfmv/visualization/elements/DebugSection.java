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

    public JLabel getDebugLabel(String text, int rowOffset) {
        JLabel debugLabel = new JLabel(text);

        setupLabel(debugLabel, rowOffset);

        return debugLabel;
    }

    public void setupLabel(JLabel label, int rowOffset) {
        Insets insets = getInsets();
        Dimension labelSize = label.getPreferredSize();

        label.setBounds(sectionPositionX + insets.left , sectionPositionY + insets.top + rowOffset, labelSize.width, labelSize.height);
        label.setForeground(Color.black);
    }
}