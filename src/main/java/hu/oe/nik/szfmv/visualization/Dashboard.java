package hu.oe.nik.szfmv.visualization;

import hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces.IReadOnlyControlsPacket;
import hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces.IReadOnlyDashboardPacket;
import hu.oe.nik.szfmv.common.enums.Gear;
import hu.oe.nik.szfmv.visualization.elements.*;

import javax.swing.*;
import java.awt.*;

/**
 * Dashboard shows the state of the ego car, thus helps in debugging.
 */
public class Dashboard extends JPanel {

    public int speed;
    public int rpm;

    /**
     * Integer for dashboard width.
     */
    private final int width = 250;
    /**
     * Integer for dashboard height.
     */
    private final int height = 700;
    private final int boundsX = 770;
    private final int boundsY = 0;
    /**
     * Integer for dashboard color.
     */
    private final int backgroundColor = 0x888888;
    private final int gearLabelPosX = 100;
    private final int gearLabelPosY = 130;
    private final int gearLabelWidth = 70;
    private final int gearLabelHeight = 30;
    private final int circleMeterWidth = 115;
    private final int circleMeterHeight = 115;
    private final int indexWidth = 51;
    private final int indexHeight = 31;
    private final Point brakePedal = new Point(40, 290);
    private final Point gasPedal = new Point(40, 260);

    private PedalBar bPB = new PedalBar();
    private JLabel brakePedalLabel = bPB.getPedalProgressBarLabel(brakePedal.x, brakePedal.y, "Brake pedal");
    private JProgressBar brakePedalBar = bPB.getPedalProgressBar(brakePedal.x, brakePedal.y,
            brakePedalLabel.getHeight());
    private JLabel gasPedalLabel = bPB.getPedalProgressBarLabel(gasPedal.x, gasPedal.y, "Gas pedal");
    private JProgressBar gasPedalBar = bPB.getPedalProgressBar(gasPedal.x, gasPedal.y, gasPedalLabel.getHeight());
    private JLabel gearLabel;
    private CircleCalculator speedMeter = new CircleCalculator(this, MeterTypes.SPEED, new Point(115, 0));
    private CircleCalculator rpmMeter = new CircleCalculator(this, MeterTypes.RPM, new Point(0, 0));
    private IndexArrow leftTurnSignal = new IndexArrow(IndexTypes.LEFT, new Point(20, 130));
    private IndexArrow rightTurnSignal = new IndexArrow(IndexTypes.RIGHT, new Point(180, 130));
    private boolean controlsSectionIsSetup = false;
    private ControlsSection controlsSection = new ControlsSection();
    private JLabel mainControlsLabel = controlsSection.initialiseMainControlsLabel();
    private JLabel pedalControlsLabel = controlsSection.initialisePedalControlsLabel();
    private JLabel steerControlsLabel = controlsSection.initialiseSteeringControlsLabel();
    private JLabel indicateControlsLabel = controlsSection.initialiseIndicateControlsLabel();
    private JLabel gearsFirstControlsLabel = controlsSection.initialiseGearsFirstControlsLabel();
    private JLabel gearsSecondControlsLabel = controlsSection.initialiseGearsSecondControlsLabel();
    private boolean debugSectionIsVisible = true;
    private DebugSection debugSection = new DebugSection();
    private JLabel debugLabel = debugSection.initialiseDebugLabel();
    private JLabel steeringWheelLabel = debugSection.initialiseSteeringWheelLabel();
    private JLabel positionLabel = debugSection.initialisePositionLabel();
    private static JLabel dangerZoneLabel = new JLabel("<html><font color='red' size=\"32\">DANGER ZONE</font></html>");
    private static JLabel driveFastLabel = new JLabel("<html><font color='red' size=\"22\">Too fast!!!</font></html>");
    /**
     * Initialize the dashboard.
     */
    Dashboard() {
        // Not using any layout manager, but fixed coordinates
        setLayout(null);
        setBackground(new Color(backgroundColor));
        setBounds(boundsX, boundsY, width, height);

        // Speed and RPM initialized with 0 rpm and km/h value
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

        add(mainControlsLabel);
        add(pedalControlsLabel);
        add(steerControlsLabel);
        add(indicateControlsLabel);
        add(gearsFirstControlsLabel);
        add(gearsSecondControlsLabel);

        add(debugLabel);
        add(steeringWheelLabel);
        add(positionLabel);
        dangerZoneLabel.setVisible(false);
        dangerZoneLabel.setBounds(35, 225, 200, 300);

        driveFastLabel.setVisible(false);
        driveFastLabel.setBounds(35, 350, 200, 200);

        add(dangerZoneLabel);
        add(driveFastLabel);
    }

    /**
     * Method gets called 'every tick' to display the dashboard portion of the
     * application
     *
     * @param dbPacket    - object containing stats
     * @param ctrlsPacket - object containing controls
     * @param inDebug     - toggle for the debug section's display
     */
    public void display(IReadOnlyDashboardPacket dbPacket, IReadOnlyControlsPacket ctrlsPacket, boolean inDebug) {
        bPB.setProgress(gasPedalBar, dbPacket.getGasPedalPosition());
        bPB.setProgress(brakePedalBar, dbPacket.getBrakePedalPosition());
        setGearLabelText(dbPacket.getCurrentGear());
        indicateTo(dbPacket.getIndicatorDirection());
        rpm = dbPacket.getRPM();
        speed = dbPacket.getSpeed();

        checkControlsSectionState(ctrlsPacket);
        checkDebugSectionVisibility(inDebug, dbPacket);
    }

    private void checkControlsSectionState(IReadOnlyControlsPacket ctrlsPacket) {
        if (!controlsSectionIsSetup) {
            String gasKey = ctrlsPacket.getGasKeyText();
            String breakKey = ctrlsPacket.getBrakeKeyText();
            String steerLeftKey = ctrlsPacket.getSteerLeftKeyText();
            String steerRightKey = ctrlsPacket.getSteerRightKeyText();
            String indicateLeftKey = ctrlsPacket.getIndicateLeftKeyText();
            String indicateRightKey = ctrlsPacket.getIndicateRightKeyText();
            String gearDKey = ctrlsPacket.getGearDKeyText();
            String gearRKey = ctrlsPacket.getGearRKeyText();
            String gearNKey = ctrlsPacket.getGearNKeyText();
            String gearPKey = ctrlsPacket.getGearPKeyText();

            controlsSection.refreshPedalLabel(pedalControlsLabel, gasKey, breakKey);
            controlsSection.refreshSteerLabel(steerControlsLabel, steerLeftKey, steerRightKey);
            controlsSection.refreshIndicateLabel(indicateControlsLabel, indicateLeftKey, indicateRightKey);
            controlsSection.refreshGearsFirstLabel(gearsFirstControlsLabel, gearDKey, gearRKey);
            controlsSection.refreshGearsSecondLabel(gearsSecondControlsLabel, gearNKey, gearPKey);

            controlsSectionIsSetup = true;
        }
    }

    private void checkDebugSectionVisibility(boolean inDebug, IReadOnlyDashboardPacket dbP) {
        if (!inDebug && debugSectionIsVisible) {
            debugLabel.setVisible(false);
            steeringWheelLabel.setVisible(false);
            positionLabel.setVisible(false);

            debugSectionIsVisible = false;
        } else if (inDebug && !debugSectionIsVisible) {
            debugLabel.setVisible(true);
            steeringWheelLabel.setVisible(true);
            positionLabel.setVisible(true);

            debugSectionIsVisible = true;
        }

        if (inDebug) {
            debugSection.refreshSteeringLabelText(steeringWheelLabel, dbP.getSteeringWheelValue());
            debugSection.refreshPositionLabelText(positionLabel, dbP.getAutomatedCarX(), dbP.getAutomatedCarY());
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
        meter.setBounds(meter.getPosition().x, meter.getPosition().y, circleMeterWidth, circleMeterHeight);
        meter.setVisible(true);
        add(meter);
    }

    /**
     * Sets position and adds index to the dashboard
     *
     * @param index index that we want to add the dashboard
     */
    private void createIndex(IndexArrow index) {
        index.setBounds(index.getPosition().x, index.getPosition().y, indexWidth, indexHeight);
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

    /**
     * To set danger zone on dashboard.
     * @param state
     */
    public static void setDangerZoneVisibility(boolean state) {
        dangerZoneLabel.setVisible(state);
    }

    /**
     * To see danger zone on dashboard.
     * @return
     */
    public static boolean getDangerZoneVisibility() {
        return dangerZoneLabel.isVisible();
    }

    /**
     * To set drive fast on dashboard.
     * @param state ture or false.
     */
    public static void setDriveFastVisibility(boolean state) {
        driveFastLabel.setVisible(state);
    }

    /**
     * To see drive fast on dashboard.
     * @return ture or false.
     */
    public static boolean getDriveFastVisibility(){
        return driveFastLabel.isVisible();
    }
}
