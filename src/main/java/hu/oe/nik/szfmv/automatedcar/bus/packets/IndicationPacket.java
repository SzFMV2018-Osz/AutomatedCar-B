package hu.oe.nik.szfmv.automatedcar.bus.packets;

import hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces.IReadonlyIndicationPacket;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.IUserInput;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.enums.Direction;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers.IIndicationEventHandler;

/**
 * This packet manages and provide information of the indicator's state
 */
public class IndicationPacket implements IReadonlyIndicationPacket, IIndicationEventHandler {

    private final IUserInput input;

    private int indicatorDirection;
    private int indicatorDirectionSnapshot;

    /**
     * Create an instance of this class
     *
     * @param input - an interface which gives access to the indicator state change events
     */
    public IndicationPacket(IUserInput input) {
        this.input = input;
        this.input.subscribeIndicationEvents(this);
        this.indicatorDirection = 0;
        this.indicatorDirectionSnapshot = 0;
    }

    @Override
    public int getIndicatorDirection() {
        return indicatorDirectionSnapshot;
    }

    private void setIndicatorDirection(int value) {
        this.indicatorDirection = value;
    }

    @Override
    public void onIndication(Direction direction) {
        switch (direction) {
            case Left:
                this.handleLeftSign();
                break;
            case Right:
                this.handleRightSign();
                break;
            default:
                throw new UnsupportedOperationException("No implementation exists for this direction type: "
                        + direction.name());
        }
    }

    /**
     * Refresh the indicator state and ensure that its value will be the same until the next call of this function
     */
    public void createSnapshot() {
        this.indicatorDirectionSnapshot = this.indicatorDirection;
    }

    private void handleRightSign() {
        if (this.indicatorDirection == 1) {
            this.setIndicatorDirection(0);
        } else {
            this.setIndicatorDirection(1);
        }
    }

    private void handleLeftSign() {
        if (this.indicatorDirection == -1) {
            this.setIndicatorDirection(0);
        } else {
            this.setIndicatorDirection(-1);
        }
    }
}
