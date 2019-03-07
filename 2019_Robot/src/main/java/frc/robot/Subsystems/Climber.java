package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.Subsystems.DriveBase;

public class Climber {
    // 1 + joystick Manual CAM Control joystick 0
    // 2 For automatic climb
    // 3 Manual climber piston down joystick 0 
    // 5 Manual climber piston up joystick 0
    // 8 CAM to up position joystick 0
    // 12 CAM to down position joystick 0

    // 8 Manual climber piston down joystick 1
    // 9 Manual climber piston up joystick 1
    
    private Joystick joystick;
    private DoubleSolenoid climbingPiston;
    private TalonSRX m1;
    private DriveBase driveTrain;
    private int i = 0;
    private int stageD = 0;
    private int stageA = 0;
    private final Value extended = Value.kForward;
    private final Value retracted = Value.kReverse;
    private final int CAMDown = 0;
    private final int CAMUp = 0;
    private Joystick joystick2;
    
   private static Climber instance = new Climber();

   public static Climber getInstance() {
       return instance;
   } 
   // 450
   // 4
    public Climber() {
        driveTrain = DriveBase.getInstance();
        climbingPiston = new DoubleSolenoid(4, 5);
        m1 = new TalonSRX(45);
        m1.configSelectedFeedbackSensor(FeedbackDevice.Analog);
        joystick = new Joystick(0);
        joystick2 = new Joystick(1);
        m1.enableCurrentLimit(true);
        m1.configPeakCurrentLimit(30);
        m1.configContinuousCurrentLimit(30);
    }
    public void readValue() {
        System.out.println("Potentiometer Value: " + m1.getSelectedSensorPosition());
    }
    public void Acsending() {
        if(joystick.getRawButton(10)){
            stageA ++;
        }
        if (i == 0) {
            i ++;
            driveTrain.resetEncoders();
        }

        // Move CAM down
        //if (stageA == 0) {
            System.out.println("Joystick: " + joystick.getY());
            System.out.println("Potentiometer Value: " + m1.getSelectedSensorPosition());
            m1.set(ControlMode.PercentOutput, (joystick.getY() * .3));        
       // }

        // Drive forward
        //else if (stageA == 1) {
            driveTrain.drive(ControlMode.PercentOutput, 0, joystick2.getY());
            System.out.println("FLM Value:" + driveTrain.getFLMValues());
            System.out.println("FRM Value:" + driveTrain.getFRMValues());
        //}

        // Move CAM up
        //else if (stageA == 2) {
        //    System.out.println("Joystick: " + joystick.getY());
        //    System.out.println("Potentiometer Value: " + m1.getSelectedSensorPosition());
        //    m1.set(ControlMode.PercentOutput, (joystick.getY() * .3));                    
        //}

        // Extend piston
         if (joystick.getRawButton(3)) {
            climbingPiston.set(extended);           
        }

        // Drive forward again
        /*else if (stageA == 4) {
            driveTrain.drive(ControlMode.PercentOutput, 0, joystick.getY());
            System.out.println("FLM Value:" + driveTrain.getFLMValues());
            System.out.println("FRM Value:" + driveTrain.getFRMValues());
        }*/

        // Retract piston
        else if (joystick.getRawButton(4)) {
            climbingPiston.set(retracted);
        }       
    } 

    public void Desending() {
        if (i == 0){
            i ++;
            driveTrain.resetEncoders();
        }

        // Extend piston
        if (stageD == 0) {
            climbingPiston.set(extended);
        }

        // Drive forward
        else if (stageD == 1) {
            driveTrain.drive(ControlMode.PercentOutput, 0, joystick.getY());
            System.out.println("FLM Value:" + driveTrain.getFLMValues());
            System.out.println("FRM Value:" + driveTrain.getFRMValues());
        }

        // Lower piston
        else if (stageD == 2) {
            climbingPiston.set(retracted);
        }

        //Drive off
        else if (stageD == 3) {
            driveTrain.drive(ControlMode.PercentOutput, 0, joystick.getY());
            System.out.println("FLM Value:" + driveTrain.getFLMValues());
            System.out.println("FRM Value:" + driveTrain.getFRMValues());    
        }
    }
}