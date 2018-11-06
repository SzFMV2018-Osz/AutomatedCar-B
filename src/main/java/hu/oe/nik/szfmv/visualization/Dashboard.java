package hu.oe.nik.szfmv.visualization;

import javax.swing.*;
import java.awt.*;

/**
 * Dashboard shows the state of the ego car, thus helps in debugging.
 */
public class Dashboard extends JPanel {

    /**
     * Integer for dashboard width.
     */
    private final int width = 250;
    /**
     * Integer for dashboard height.
     */
    private final int height = 700;
    /**
     * Integer for dashboard color.
     */
    private final int backgroundColor = 0x888888;
    private final int xBound = 770;

    /**
     * Initialize the dashboard.
     */
    public Dashboard() {
        // Not using any layout manager, but fixed coordinates
        setLayout(null);
        setBackground(new Color(backgroundColor));
        setBounds(xBound, 0, width, height);

    }

}
