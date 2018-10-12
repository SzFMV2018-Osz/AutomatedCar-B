package hu.oe.nik.szfmv.visualization;

import hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces.IReadOnlyDashboardPacket;
import hu.oe.nik.szfmv.common.enums.Gear;
import hu.oe.nik.szfmv.visualization.elements.DebugSection;
import hu.oe.nik.szfmv.visualization.elements.CircleCalculator;
import hu.oe.nik.szfmv.visualization.elements.IndexArrow;
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
    private final int gearLabelPosX = 100;
    private final int gearLabelPosY = 130;
    private final int gearLabelWidth = 50;
    private final int gearLabelHeight = 30;

    private final Point brakePedal = new Point(40, 290);
    private final Point gasPedal = new Point(40, 260);
  
    public int speed;
    public int rpm;
  
    private PedalBar bPB = new PedalBar();
    private JLabel brakePedalLabel = bPB.getPedalProgressBarLabel(brakePedal.x, brakePedal.y, "Brake pedal");
    private JProgressBar brakePedalBar = bPB.getPedalProgressBar(
            brakePedal.x,
            brakePedal.y,
            brakePedalLabel.getHeight()
    );
    private JLabel gasPedalLabel = bPB.getPedalProgressBarLabel(gasPedal.x, gasPedal.y, "Gas pedal");
    private JProgressBar gasPedalBar = bPB.getPedalProgressBar(gasPedal.x, gasPedal.y, gasPedalLabel.getHeight());
    private JLabel gearLabel;
    private CircleCalculator speedMeter = new CircleCalculator(this, MeterTypes.SPEED, new Point(115, 0));
    private CircleCalculator rpmMeter = new CircleCalculator(this, MeterTypes.RPM, new Point(0, 0));
    private IndexArrow leftTurnSignal = new IndexArrow(IndexTypes.LEFT, new Point(40, 130));
    private IndexArrow rightTurnSignal = new IndexArrow(IndexTypes.RIGHT, new Point(160, 130));

    private DebugSection debugSection = new DebugSection();
    private JLabel debugLabel = debugSection.getDebugLabel(debugSection.getMainDebugText(), debugSection.getMainDebugOffset());
    private JLabel steeringWheelLabel = debugSection.getDebugLabel(debugSection.getSteeringDebugText(), debugSection.getSteeringDebugOffset());
    private JLabel positionLabel = debugSection.getDebugLabel(debugSection.getPositionTextX() + 0 + debugSection.getPositionTextY() + 0, debugSection.getPositionDebugOffset());

    /**
     * Initialize the dashboard
     */
    public Dashboard() {
        // Not using any layout manager, but fixed coordinates
        setLayout(null);
        setBackground(new Color(backgroundColor));
        setBounds(770, 0, width, height);

        //Speed and RPM initialized with 0 rpm and km/h value
        rpm = 0;
        speed = 0;
        createCircleMeter(speedMeter);
        createCircleMeter(rpmMeter);
        createIndex(leftTurnSignal);
        createIndex(rightTurnSignal);
        add(brakePedalLabel);
        add(brakePedalBar);
        add(gasPedalLabel);
        add(gasPedalBar);
        initGearLabel();

        add(debugLabel);
        add(steeringWheelLabel);
        add(positionLabel);
    }

    public void display(IReadOnlyDashboardPacket dbPacket, boolean debugInfoIsEnabled) {
        bPB.setProgress(gasPedalBar, dbPacket.getGasPedalPosition());
        bPB.setProgress(brakePedalBar, dbPacket.getBrakePedalPosition());
        setGearLabelText(dbPacket.getCurrentGear());
        indicateTo(dbPacket.getIndicatorDirection());

        if (debugInfoIsEnabled) {
            setSteeringLabelText(dbPacket.getSteeringWheelValue());
            setPositionLabelText(dbPacket.getAutomatedCarX(), dbPacket.getAutomatedCarY());
        }

    }

    private void initGearLabel() {
        gearLabel = new JLabel();
        gearLabel.setBounds(gearLabelPosX, gearLabelPosY, gearLabelWidth, gearLabelHeight);
        setGearLabelText(Gear.N);
        add(gearLabel);
    }

    private void setGearLabelText(Gear gear) {
        gearLabel.setText("Gear: " + gear);
    }

    private void setSteeringLabelText(int value) {
        if (value > 0) {
            steeringWheelLabel.setText(debugSection.getSteeringDebugText() + "+" + value);
        } else {
            steeringWheelLabel.setText(debugSection.getSteeringDebugText() + value);
        }

        debugSection.setupLabel(steeringWheelLabel, debugSection.getSteeringDebugOffset());
    }
    private void setPositionLabelText(int x, int y) {
        positionLabel.setText(debugSection.getPositionTextX() + x + debugSection.getPositionTextY() + y);

        debugSection.setupLabel(positionLabel, debugSection.getPositionDebugOffset());
    }

    private void indicateTo(int direction) {
        switch (direction) {
            case -1:
                indicateLeft();
                break;
            case 0:
                indicationStop();
                break;
            case 1:
                indicateRight();
                break;
            default:
                break;
        }
    }

    private void indicateLeft() {
        if (rightTurnSignal.hasStarted()) {
            rightTurnSignal.stopIndex();
        }
        if (!leftTurnSignal.hasStarted()) {
            leftTurnSignal.startIndex();
        }
    }

    private void indicateRight() {
        if (leftTurnSignal.hasStarted()) {
            leftTurnSignal.stopIndex();
        }
        if (!rightTurnSignal.hasStarted()) {
            rightTurnSignal.startIndex();
        }
    }

    private void indicationStop() {
        if (rightTurnSignal.hasStarted()) {
            rightTurnSignal.stopIndex();
        }
        if (leftTurnSignal.hasStarted()) {
            leftTurnSignal.stopIndex();
        }
    }

    /**
     * Creates and adds meter to the dashboard
     *
     * @param meter position x on board
     */
    private void createCircleMeter(CircleCalculator meter) {
        meter.setBounds(meter.getPosition().x, meter.getPosition().y, 115, 115);
        meter.setVisible(true);
        add(meter);
    }

    /**
     * Sets position and adds index to the dashboard
     *
     * @param index index that we want to add the dashboard
     */
    private void createIndex(IndexArrow index) {
        index.setBounds(index.getPosition().x, index.getPosition().y, 51, 31);
        add(index);
    }

    /**
     * Speed or RPM meter
     */
    public enum MeterTypes {
        SPEED, RPM
    }

    /**
     * Left or right sided index
     */
    public enum IndexTypes {
        LEFT, RIGHT
    }
}
