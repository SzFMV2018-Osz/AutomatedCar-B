package hu.oe.nik.szfmv.visualization;

import hu.oe.nik.szfmv.common.DebugInfoContainer;

import javax.swing.*;
import java.awt.*;

/**
 * Dashboard shows the state of the ego car, thus helps in debugging.
 */
public class Dashboard extends JPanel {

    private final int width = 250;
    private final int height = 700;
    private final int backgroundColor = 0x888888;

    private final double debugSectionXRatio = 0.05;
    private final double debugSectionYRatio = 0.9;
    private final int debugSectionRowSize = 20;

    /**
     * Initialize the dashboard
     */
    public Dashboard() {
        // Not using any layout manager, but fixed coordinates
        setLayout(null);
        setBackground(new Color(backgroundColor));
        setBounds(770, 0, width, height);

    }

    /**
     * Draws the debug section of the dashboard
     * @param debugInfo - object containing values to display for debug purposes
     */
    public void drawDashboardDebugDisplay(DebugInfoContainer debugInfo) {
        Graphics graphics = this.getGraphics();
        super.paintComponent(graphics);

        int firstColumn = (int)Math.round(width * debugSectionXRatio);
        int firstRow = (int)Math.round(height * debugSectionYRatio);

        int rowWithOffset = firstRow;

        graphics.drawString("debug:", firstColumn, rowWithOffset);
        rowWithOffset += debugSectionRowSize;
        graphics.drawString("x: " + debugInfo.getCarX() + ", y: " + debugInfo.getCarY(), firstColumn, rowWithOffset);
    }
}
