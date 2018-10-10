package hu.oe.nik.szfmv.visualization;

import hu.oe.nik.szfmv.common.DebugInfoContainer;
import hu.oe.nik.szfmv.visualization.elements.PedalBar;

import javax.swing.*;
import java.awt.*;

/**
 * Dashboard shows the state of the ego car, thus helps in debugging.
 */
public class Dashboard extends JPanel {

    private final int width = 250;
    private final int height = 700;
    private final int backgroundColor = 0x888888;

    private final Point debugSectionPosition = new Point(10, 630);
    private final int debugSectionRowSize = 20;

    private final Point brakePedal = new Point(40, 290);
    private final Point gasPedal = new Point(40, 260);
  
    private PedalBar bPB = new PedalBar();
    private JLabel brakePedalLabel = bPB.getPedalProgressBarLabel(brakePedal.x, brakePedal.y, "Brake pedal");
    private JProgressBar brakePedalBar = bPB.getPedalProgressBar(
            brakePedal.x,
            brakePedal.y,
            brakePedalLabel.getHeight()
    );
    private JLabel gasPedalLabel = bPB.getPedalProgressBarLabel(gasPedal.x, gasPedal.y, "Gas pedal");
    private JProgressBar gasPedalBar = bPB.getPedalProgressBar(gasPedal.x, gasPedal.y, gasPedalLabel.getHeight());

    /**
     * Initialize the dashboard
     */
    public Dashboard() {
        // Not using any layout manager, but fixed coordinates
        setLayout(null);
        setBackground(new Color(backgroundColor));
        setBounds(770, 0, width, height);

        add(brakePedalLabel);
        add(brakePedalBar);
        add(gasPedalLabel);
        add(gasPedalBar);
    }
  
    /**
     * Draws the debug section of the dashboard
     * @param debugInfo - object containing values to display for debug purposes
     */
    public void drawDashboardDebugDisplay(DebugInfoContainer debugInfo) {
        Graphics g = this.getGraphics();
        super.paintComponent(g);

        int currentRow = debugSectionPosition.y;

        g.drawString("debug:", debugSectionPosition.x, currentRow);
        currentRow += debugSectionRowSize;
        g.drawString("x: " + debugInfo.getCarX() + ", y: " + debugInfo.getCarY(), debugSectionPosition.x, currentRow);
    }
}
