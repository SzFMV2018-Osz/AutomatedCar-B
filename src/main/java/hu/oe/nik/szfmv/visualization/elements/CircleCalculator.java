package hu.oe.nik.szfmv.visualization.elements;
import javax.swing.*;
import java.awt.*;
import hu.oe.nik.szfmv.visualization.Dashboard;

public class CircleCalculator extends JPanel {
    private static final int diameter = 110;
    private int viewValue;
    private int value = 0;
    private Point position;
    private Dashboard.MeterTypes meterType;
    private Dashboard dashboard;

    /**
     * The meter's position on the dashboard
     */
    public Point getPosition() {
        return this.position;
    }

    /**
     * Constructor for the meter
     * @param dashboard parent dashboard
     * @param type meter type, rpm or speed
     * @param position position on dashboard
     */
    public CircleCalculator(Dashboard dashboard, Dashboard.MeterTypes type, Point position) {
        if(type == Dashboard.MeterTypes.RPM) {
            viewValue = 70;
        } else {
            viewValue = 2;
        }

        this.dashboard = dashboard;
        this.meterType = type;
        this.position = position;
    }

    /**
     * Sets the meter's value, error if its lower than 0 or higher than 280 km/h or 9800 rpm
     * @param value speed or rpm
     */
    public void setValue(int value) {
        if(value <= viewValue * 140 && value  >= 0) {
            double scale = value / (viewValue * 10);
            double fractional = value % (viewValue * 10);
            int remnant = (int) (fractional / viewValue);
            if (scale == 14) {
                this.value = 115 + (int) (scale * 10 -10) + remnant;
            } else {
                this.value = 115 + (int) (scale * 10) + remnant;
            }
            repaint();
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Magic, paints on the dashboard
     * @param g random java thing
     */
    @Override
    protected void paintComponent(Graphics g) {
        //fill the background
        g.setColor(new Color(0x888888));
        g.fillRect(0, 0, 115, 115);

        //black circle
        g.setColor(Color.BLACK);
        g.fillOval(3, 3, diameter, diameter); // 125,125

        //inner white circle
        g.setColor(Color.WHITE);
        g.fillOval(6, 6, diameter - 6, diameter - 6);

        //black point on the middle
        g.setColor(Color.BLACK);
        g.fillOval(diameter / 2 + 3, diameter / 2 + 3, 5, 5);

        //values on the diameter
        g.setColor(Color.BLACK);
        g.setFont(g.getFont().deriveFont(Font.PLAIN, 9));
        for (int i = 110; i <= 250; i++) {
            int x = 50 + (int) (43 * Math.sin(i * Math.PI / 90));
            int y = 60 - (int) (43 * Math.cos(i * Math.PI / 90));
            if (meterType == Dashboard.MeterTypes.RPM) {
                if ((i - 110) % 35 == 0) {
                    g.drawString(Integer.toString((i - 110) * viewValue), x, y);
                }
            } else {
                if ((i - 110) % 10 == 0) {
                    g.drawString(Integer.toString((i - 110) * viewValue), x, y);
                }
            }
        }

        //gets the speed or rpm
        if (meterType == Dashboard.MeterTypes.RPM) {
            setValue(dashboard.rpm);
        } else {
            setValue(dashboard.speed);
        }

        //black line from the middle
        int initX = 55 + (int) (45 * Math.sin(value * Math.PI / 90));
        int initY = 65 - (int) (45 * Math.cos(value * Math.PI / 90));
        g.setColor(Color.BLACK);
        g.drawLine(diameter / 2 + 5, diameter / 2 + 5, initX, initY);

        //rpm or km/h at the bottom
        g.setColor(Color.BLACK);
        g.setFont(g.getFont().deriveFont(Font.BOLD, 12));
        if (meterType == Dashboard.MeterTypes.RPM) {
            g.drawString("rpm", diameter / 2 -8, diameter - 5);
        } else {
            g.drawString("km/h", diameter / 2 -8, diameter - 5);
        }
    }
}

