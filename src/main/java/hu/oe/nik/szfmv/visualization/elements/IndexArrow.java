package hu.oe.nik.szfmv.visualization.elements;
import hu.oe.nik.szfmv.visualization.Dashboard;
import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class IndexArrow extends JPanel {
    private Dashboard.IndexTypes type;
    private Color color = Color.WHITE;
    private Point position;
    private Timer timer = new Timer();
    private int secondPassed = 0;
    private boolean hasStarted = false;

    /**
     * Gives the index's position on the dashboard back
     */
    public Point getPosition() {
        return this.position;
    }

    /**
     * Sets the arrow fill color
     * @param color the new color
     */
    private void changeIndexColor(Color color) {
        this.color = color;
    }

    /**
     * Sets the index's status on
     * Only the stop method stops it
     */
    public void startIndex() {
        if(this.hasStarted == true) {
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
                if(secondPassed % 2 == 0) {
                    changeIndexColor(color.WHITE);
                } else {
                    changeIndexColor(color.YELLOW);
                }
                secondPassed++;
                repaint();
            }
        };
        this.timer.scheduleAtFixedRate(task,0, 500);
    }

    /**
     * Stops the flashing
     */
    public void stopIndex() {
        if(this.hasStarted == true) {
            this.timer.cancel();
            this.timer.purge();
            secondPassed = 0;
            this.color = Color.WHITE;
            repaint();
            this.hasStarted = false;
        }
    }

    /**
     * Constructor with index side type, and position on the board
     */
    public IndexArrow(Dashboard.IndexTypes type, Point position) {
        this.type = type;
        this.position = position;
    }

    /**
     * Paints the arrow depends on the side
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //fill the background
        g.setColor(new Color(0x888888));
        g.fillRect(0, 0, 51, 31);

        //check sides and draws arrow
        if (type == Dashboard.IndexTypes.RIGHT) {
            int[] x = new int[] { 0, 30, 30, 40, 30,
                    30, 0 };
            int[] y = new int[] { 10, 10, 0, 14, 28,
                    18, 18 };
            g.setColor(Color.BLACK);
            g.drawPolygon(x, y, 7);
            g.setColor(color);
            g.fillPolygon(x, y, 7);
        } else {
            int[] x = new int[] { 0, 10, 10, 40, 40,
                    10, 10 };
            int[] y = new int[] { 14, 0, 10, 10, 18,
                    18, 28 };
            g.setColor(Color.BLACK);
            g.drawPolygon(x, y, 7);
            g.setColor(color);
            g.fillPolygon(x, y, 7);
        }
    }
}
