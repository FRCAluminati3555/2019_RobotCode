package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

public class DriveBase {
    private TalonSRX FLM;
    private TalonSRX FRM;
    private TalonSRX BLM;
    private TalonSRX BRM;

   private static DriveBase instance = new DriveBase();

    public static DriveBase getInstance() {
        return instance;
    } 

    private DriveBase() {
        FLM = new TalonSRX(41);
        FRM = new TalonSRX(42);
        BLM = new TalonSRX(43);
        BRM = new TalonSRX(44);

        FRM.setInverted(true);
        BRM.setInverted(true);
        FLM.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
        FRM.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
        BLM.follow(FLM);
        BRM.follow(FRM);
    }
    public void drive(ControlMode controlMode, double controlX, double controlY) {
        FLM.set(controlMode, (controlY + controlX));
        FRM.set(controlMode, (controlY + -controlX));

    }
    public void resetEncoders() {
        FLM.setSelectedSensorPosition(0);
        FRM.setSelectedSensorPosition(0);
    }
    public int getFLMValues() { return FLM.getSelectedSensorPosition(); }
    public int getFRMValues() { return FRM.getSelectedSensorPosition(); }
}