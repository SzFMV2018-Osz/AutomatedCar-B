package hu.oe.nik.szfmv.visualization.elements;

import hu.oe.nik.szfmv.visualization.Dashboard;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Index arrow class for UI.
 */
public class IndexArrow extends JPanel {
    private Dashboard.IndexTypes type;
    private Color color = Color.WHITE;
    private Color backgroundColor = new Color(0x888888);
    private Point position;
    private Timer timer = new Timer();
    private int indicatorPeriod = 500;
    private int secondPassed = 0;
    private boolean hasStarted = false;
    private int[] rightArrowXCoordinates = new int[]{0, 30, 30, 40, 30, 30, 0};
    private int[] rightArrowYCoordinates = new int[]{10, 10, 0, 14, 28, 18, 18};
    private int[] leftArrowXCoordinates = new int[]{0, 10, 10, 40, 40, 10, 10};
    private int[] leftArrowYCoordinates = new int[]{14, 0, 10, 10, 18, 18, 28};

    /**
     * Constructor with index side type, and position on the board.
     *
     * @param type     - type of the index
     * @param position - position of the UI element
     */
    public IndexArrow(Dashboard.IndexTypes type, Point position) {
        this.type = type;
        this.position = position;
    }

    /**
     * Gives the index's position on the dashboard back.
     *
     * @return the index's position on the dashboard back
     */
    public Point getPosition() {
        return this.position;
    }

    /**
     * @return true if this index arrow is active.
     */
    public boolean hasStarted() {
        return this.hasStarted;
    }

    /**
     * Sets the arrow fill color.
     *
     * @param color the new color
     */
    private void changeIndexColor(Color color) {
        this.color = color;
    }

    /**
     * Sets the index's status on
     * Only the stop method stops it.
     */
    public void startIndex() {
        if (this.hasStarted) {
            this.timer.cancel();
            this.timer.purge();
            secondPassed = 0;
            this.color = Color.WHITE;
            repaint();
            this.hasStarted = false;
        }

        this.hasStarted = true;
        this.timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                if (secondPassed % 2 == 0) {
                    changeIndexColor(color.WHITE);
                } else {
                    changeIndexColor(color.YELLOW);
                }
                secondPassed++;
                repaint();
            }
        };
        this.timer.scheduleAtFixedRate(task, 0, indicatorPeriod);
    }

    /**
     * Stops the flashing.
     */
    public void stopIndex() {
        if (this.hasStarted) {
            this.timer.cancel();
            this.timer.purge();
            secondPassed = 0;
            this.color = Color.WHITE;
            repaint();
            this.hasStarted = false;
        }
    }

    /**
     * Paints the arrow depends on the side.
     *
     * @param g - graphics to paint
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //fill the background
        g.setColor(backgroundColor);
        g.fillRect(0, 0, getWidth(), getHeight());

        //check sides and draws arrow
        if (type == Dashboard.IndexTypes.RIGHT) {
            g.setColor(Color.BLACK);
            g.drawPolygon(rightArrowXCoordinates, rightArrowYCoordinates, rightArrowXCoordinates.length);
            g.setColor(color);
            g.fillPolygon(rightArrowXCoordinates, rightArrowYCoordinates, rightArrowXCoordinates.length);
        } else {
            g.setColor(Color.BLACK);
            g.drawPolygon(leftArrowXCoordinates, leftArrowYCoordinates, leftArrowXCoordinates.length);
            g.setColor(color);
            g.fillPolygon(leftArrowXCoordinates, leftArrowYCoordinates, leftArrowXCoordinates.length);
        }
    }
}