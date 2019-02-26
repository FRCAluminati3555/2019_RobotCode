package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;



public class Pannel{

    private DoubleSolenoid control;
    private DoubleSolenoid clamp;
    private Joystick controlJoystick;
    private final Value closed = Value.kForward;
    private final Value open = Value.kReverse;

    private static Pannel instance = new Pannel();

    public static Pannel getInstance() {
        return instance;
    }

    public Pannel(){
        control = new DoubleSolenoid(2, 3);
        clamp = new DoubleSolenoid(0, 1);
        controlJoystick = new Joystick(0);
    }

    public void control(){
        if (controlJoystick.getPOV() == 0){
            control.set(Value.kForward);
        }
        else if (controlJoystick.getPOV() == 180){
            control.set(Value.kReverse);
        }
    }
    
    public void grab(){
        if (controlJoystick.getRawButton(3)){
            clamp.set(closed);
        }
        if (controlJoystick.getRawButton(4)){
            clamp.set(open);
        }        
    }
}