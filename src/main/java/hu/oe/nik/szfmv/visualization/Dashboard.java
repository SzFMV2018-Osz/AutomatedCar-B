package hu.oe.nik.szfmv.visualization;
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

    private PedalBar bPB = new PedalBar();
    private JLabel brakePedalLabel = bPB.getPedalProgressBarLabel(40, 290, "Brake pedal");
    private JProgressBar brakePedalBar = bPB.getPedalProgressBar(40, 290, brakePedalLabel.getHeight());
    private JLabel gasPedalLabel = bPB.getPedalProgressBarLabel(40, 260, "Gas pedal");
    private JProgressBar gasPedalBar = bPB.getPedalProgressBar(40, 260, gasPedalLabel.getHeight());

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
}
