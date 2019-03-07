package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.Ultrasonic.Unit;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import com.ctre.phoenix.motorcontrol.ControlMode;

public class Ball {
    private VictorSPX frontMotor;
    private VictorSPX backMotor;
    private Joystick joystick;
    private Relay r1;
    private Ultrasonic u1;
    private DoubleSolenoid elevationControl;

    private boolean isTiming = false;
    private double time = 0;

    private final Value  on = Value.kForward;
    private final Value off = Value.kOff;
   
    private static Ball instance = new Ball();

   public static Ball getInstance() {
       return instance;
   } 

    public Ball() {
        frontMotor = new VictorSPX(46);
        backMotor = new VictorSPX(47);
        r1 = new Relay(1);
        elevationControl = new DoubleSolenoid(6, 7);
        joystick = new Joystick(0);
        u1 = new Ultrasonic(1, 0);
        u1.setAutomaticMode(true);
        u1.setDistanceUnits(Unit.kInches);
    }
    public void ballControl(){
        System.out.println("Ultrasonic Value: " + u1.pidGet());
        System.out.println("JoystickY: " + joystick.getY());

        if (isTiming) {
            isTiming = false;
            time = Timer.getFPGATimestamp();
        }

        if (joystick.getRawButton(2)) {
            r1.set(off);
            isTiming = true;
            frontMotor.set(ControlMode.PercentOutput, -.1);
            backMotor.set(ControlMode.PercentOutput, -.1);    
        }

        if (time + 1 >= Timer.getFPGATimestamp() && time + 2 <= Timer.getFPGATimestamp()){
            frontMotor.set(ControlMode.PercentOutput, 0);
            backMotor.set(ControlMode.PercentOutput, 0);
        }
        
        if (joystick.getRawButton(7)) {
            r1.set(off);
            frontMotor.set(ControlMode.PercentOutput, -.1);
            backMotor.set(ControlMode.PercentOutput, -.1);
        }

        else if (u1.pidGet() <= 7) {
            r1.set(on);
            frontMotor.set(ControlMode.PercentOutput, 0);
            backMotor.set(ControlMode.PercentOutput, 0);       
        }
    }
}