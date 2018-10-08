package hu.oe.nik.szfmv.visualization.Elements;

import javax.swing.*;
import java.awt.*;

public class PedalBar extends JPanel {
    private static final int MIN_PEDAL_VALUE = 0;
    private static final int MAX_PEDAL_VALUE = 100;

    public JProgressBar getPedalProgressBar(int offsetX, int offsetY, int labelHeight) {
        JProgressBar progressBar = new JProgressBar(MIN_PEDAL_VALUE, MAX_PEDAL_VALUE);
        Insets insets = getInsets();

        Dimension size = progressBar.getPreferredSize();
        progressBar.setBounds(insets.left + offsetX, insets.top + offsetY + labelHeight, size.width, size.height);

        progressBar.setStringPainted(true);
        progressBar.setVisible(true);
        progressBar.setValue(0);

        return progressBar;
    }

    public JLabel getPedalProgressBarLabel(int offsetX, int offsetY, String label) {
        JLabel breakLabel = new JLabel(label);
        Insets insets = getInsets();

        Dimension labelSize = breakLabel.getPreferredSize();
        breakLabel.setBounds(insets.left + offsetX, insets.top + offsetY, labelSize.width, labelSize.height);

        breakLabel.setForeground(Color.black);

        return breakLabel;
    }

    public void setProgress(JProgressBar progressBar, int value) {
        if (value >= MIN_PEDAL_VALUE && value <= MAX_PEDAL_VALUE) {
            progressBar.setValue(value);
        }
    }
}
