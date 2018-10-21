package hu.oe.nik.szfmv.visualization.elements;

import javax.swing.*;
import java.awt.*;
import hu.oe.nik.szfmv.visualization.Dashboard;

public class CircleCalculator extends JPanel {
    private static final int DIAMETER = 110;
    private static final int VIEWVALUE_DEFAULT_RPM = 200;

    private static final int VIEWVALUE_MULTIPLIER_RPM_SPECIFIC = 50;
    private static final int VIEWVALUE_MULTIPLIER_SPEED_SPECIFIC = 140;
    private static final int VIEWVALUE_MULTIPLIER_SCALE = 10;
    private static final int SCALE_THRESHOLD = 14;
    private static final int SCALE_BASE = 115;
    private static final int SCALE_MULTIPLIER_RPM_SPECIFIC = 26;
    private static final int SCALE_MULTIPLIER_SPEED_SPECIFIC = 10;

    private static final Color COLOR_MAIN = new Color(0x888888);
    private static final int RECTANGLE_FILL_DIMENSION = 115;
    private static final int OVAL_OUTER_FILL_XY = 3;
    private static final int OVAL_INNER_FILL_XY = 6;
    private static final int OVAL_INNER_POINT_DIMENSION = 5;
    private static final int OVAL_FONT_SIZE = 9;

    private int viewValue;
    private int value;
    private Point position;
    private Dashboard.MeterTypes meterType;
    private Dashboard dashboard;

    /**
     * Constructor for the meter
     *
     * @param dashboard parent dashboard
     * @param type      meter type, rpm or speed
     * @param position  position on dashboard
     */
    public CircleCalculator(Dashboard dashboard, Dashboard.MeterTypes type, Point position) {
        if (type == Dashboard.MeterTypes.RPM) {
            viewValue = VIEWVALUE_DEFAULT_RPM;
        } else {
            viewValue = 2;
        }
        value = 0;

        this.dashboard = dashboard;
        this.meterType = type;
        this.position = position;
    }

    /**
     * The meter's position on the dashboard
     *
     * @return - returns the 'Point' object
     */
    public Point getPosition() {
        return this.position;
    }

    /**
     * Sets the meter's value, error if its lower than 0 or higher than 280 km/h or 9800 rpm
     *
     * @param value speed or rpm
     */
    public void setValue(int value) {
        if (meterType == Dashboard.MeterTypes.RPM) {
            helperMethodSetValueRPM(value);
        } else {
            helpetMethodSetValueSPEED(value);
        }
    }

    private void helperMethodSetValueRPM(int value) {
        if (value <= viewValue * VIEWVALUE_MULTIPLIER_RPM_SPECIFIC && value >= 0) {
            double scale = value / (viewValue * VIEWVALUE_MULTIPLIER_SCALE);
            double fractional = value % (viewValue * VIEWVALUE_MULTIPLIER_SCALE);
            int remnant = (int) (fractional / viewValue);
            if (scale == SCALE_THRESHOLD) {
                int tempCalc = (int) (scale * SCALE_MULTIPLIER_RPM_SPECIFIC - SCALE_MULTIPLIER_RPM_SPECIFIC);
                this.value = SCALE_BASE + tempCalc + remnant;
            } else {
                this.value = SCALE_BASE + (int) (scale * SCALE_MULTIPLIER_RPM_SPECIFIC) + remnant;
            }
            repaint();
        } else {
            throw new IllegalArgumentException();
        }
    }
    private void helpetMethodSetValueSPEED(int value) {
        if (value <= viewValue * VIEWVALUE_MULTIPLIER_SPEED_SPECIFIC && value >= 0) {
            double scale = value / (viewValue * VIEWVALUE_MULTIPLIER_SCALE);
            double fractional = value % (viewValue * VIEWVALUE_MULTIPLIER_SCALE);
            int remnant = (int) (fractional / viewValue);
            if (scale == SCALE_THRESHOLD) {
                int tempCalc = (int) (scale * SCALE_MULTIPLIER_SPEED_SPECIFIC - SCALE_MULTIPLIER_SPEED_SPECIFIC);
                this.value = SCALE_BASE + tempCalc + remnant;
            } else {
                this.value = SCALE_BASE + (int) (scale * SCALE_MULTIPLIER_SPEED_SPECIFIC) + remnant;
            }
            repaint();
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Magic, paints on the dashboard
     *
     * @param g random java thing
     */
    @Override
    protected void paintComponent(Graphics g) {
        paintComponentPreProcessing(g);

        if (meterType == Dashboard.MeterTypes.RPM) {
            paintComponentMainIfRPM(g);
        } else {
            paintComponentMainIfSPEED(g);
        }

        paintCOmponentPostProcessing(g);
    }
    private void paintComponentPreProcessing(Graphics g) {
        //fill the background
        g.setColor(COLOR_MAIN);
        g.fillRect(0, 0, RECTANGLE_FILL_DIMENSION, RECTANGLE_FILL_DIMENSION);

        //black circle
        g.setColor(Color.BLACK);
        g.fillOval(OVAL_OUTER_FILL_XY, OVAL_OUTER_FILL_XY, DIAMETER, DIAMETER);

        //inner white circle
        g.setColor(Color.WHITE);
        int tempDimension = DIAMETER - OVAL_INNER_FILL_XY;
        g.fillOval(OVAL_INNER_FILL_XY, OVAL_INNER_FILL_XY, tempDimension, tempDimension);

        //black point on the middle
        g.setColor(Color.BLACK);
        tempDimension = DIAMETER / 2 + OVAL_OUTER_FILL_XY;
        g.fillOval(tempDimension, tempDimension, OVAL_INNER_POINT_DIMENSION, OVAL_INNER_POINT_DIMENSION);

        //values on the DIAMETER
        g.setColor(Color.BLACK);
        g.setFont(g.getFont().deriveFont(Font.BOLD, OVAL_FONT_SIZE));
    }
    private void paintComponentMainIfRPM(Graphics g) {
        int number = 0;
        for (int i = 110; i <= 250; i = i + 14) {
            int x = 55 + (int) (43 * Math.sin(i * Math.PI / 90));
            int y = 60 - (int) (43 * Math.cos(i * Math.PI / 90));
            if ((i - 110) % 28 == 0) {
                g.drawString(Integer.toString(number), x, y);
                number += 2;
            }
        }
    }
    private void paintComponentMainIfSPEED(Graphics g) {
        for (int i = 110; i <= 250; i++) {
            int x = 50 + (int) (43 * Math.sin(i * Math.PI / 90));
            int y = 60 - (int) (43 * Math.cos(i * Math.PI / 90));
            if ((i - 110) % 10 == 0) {
                g.drawString(Integer.toString((i - 110) * viewValue), x, y);
            }
        }
    }
    private void paintCOmponentPostProcessing(Graphics g) {
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
        g.drawLine(DIAMETER / 2 + 5, DIAMETER / 2 + 5, initX, initY);

        //rpm or km/h at the bottom
        g.setColor(Color.BLACK);
        g.setFont(g.getFont().deriveFont(Font.BOLD, 12));
        if (meterType == Dashboard.MeterTypes.RPM) {
            g.drawString("rpm", DIAMETER / 2 -8, DIAMETER - 5);
            g.drawString("x1000", DIAMETER / 2 -12, DIAMETER - 20);
        } else {
            g.drawString("km/h", DIAMETER / 2 -8, DIAMETER - 5);
        }
    }
}