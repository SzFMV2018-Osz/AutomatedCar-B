package hu.oe.nik.szfmv.automatedcar.engine;

public class TurningHandler {


    CarAxisParams carAxisParams;


    public TurningHandler() {
        this.carAxisParams = new CarAxisParamsImpl();
    }

    public double angularVelocityCalculation(final int steeringWheelState,int speed)
    {
        if (steeringWheelState==0) {
            return 0;
        } else {
            return speed / turningCircleCalculation(steeringWheelState);
        }
    }


    private double turningCircleCalculation(final int steeringWheelState)
    {
        return (carAxisParams.getAxisLengthPixel()/ Math.tan(Math.toRadians(steeringWheelState))+carAxisParams.getCarWidthPixel())/50;
    }


}
