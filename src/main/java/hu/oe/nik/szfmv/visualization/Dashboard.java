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

    /**
     * Initialize the dashboard
     */
    public Dashboard() {
        // Not using any layout manager, but fixed coordinates
        setLayout(null);
        setBackground(new Color(backgroundColor));
        setBounds(770, 0, width, height);

    }

    public void drawDashboardDebugDisplay(DebugInfoContainer debugInfo) {
        Graphics graphics = this.getGraphics();
        super.paintComponent(graphics);

        int firstRow = (int)Math.round(height * 0.9);
        int firstColumn = (int)Math.round(width * 0.05);

        graphics.drawString("debug:", firstColumn, firstRow);
        graphics.drawString("x: " + debugInfo.getCarX() + ", y: " + debugInfo.getCarY(), firstColumn, firstRow + 20);
    }
}
