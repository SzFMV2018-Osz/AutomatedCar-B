package hu.oe.nik.szfmv.visualization.elements;
import javax.swing.*;
import java.awt.*;

public class PedalBar extends JPanel {
    private static final int MIN_PEDAL_VALUE = 0;
    private static final int MAX_PEDAL_VALUE = 100;

    /**
     * Draws a progressbar for the pedals
     * @param offsetX position x on board
     * @param offsetY position y on board
     * @param labelHeight label's height
     * @return progressbar design for the pedal
     */
    public JProgressBar getPedalProgressBar(int offsetX, int offsetY, int labelHeight) {
        JProgressBar progressBar = new JProgressBar(MIN_PEDAL_VALUE, MAX_PEDAL_VALUE);
        Insets insets = getInsets();

        progressBar.setBounds(insets.left + offsetX, insets.top + offsetY + labelHeight, 160, 15);

        progressBar.setStringPainted(true);
        progressBar.setVisible(true);
        progressBar.setValue(MIN_PEDAL_VALUE);

        return progressBar;
    }

    /**
     * Label for the progressbar
     * @param offsetX position x on board
     * @param offsetY position y on board
     * @param label label's string value
     * @return label for the pedal
     */
    public JLabel getPedalProgressBarLabel(int offsetX, int offsetY, String label) {
        JLabel breakLabel = new JLabel(label);
        Insets insets = getInsets();

        Dimension labelSize = breakLabel.getPreferredSize();
        breakLabel.setBounds(insets.left + offsetX, insets.top + offsetY, labelSize.width, labelSize.height);

        breakLabel.setForeground(Color.black);

        return breakLabel;
    }

    /**
     * Sets the value of the bar
     * @param progressBar the bar that we want to change
     * @param value the new value of the bar, must be between 0 and 100
p    */
    public void setProgress(JProgressBar progressBar, int value) {
        if (value >= MIN_PEDAL_VALUE && value <= MAX_PEDAL_VALUE) {
            progressBar.setValue(value);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
