package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Subsystems.Camera;
import frc.robot.Subsystems.DriveBase;
import frc.robot.Subsystems.Pannel;
import frc.robot.Subsystems.Climber;
import frc.robot.Subsystems.Ball;

public class Robot extends TimedRobot {

  // 11 toggle line following joystick 1 
  private Camera camera;
  private DriveBase driveTrain;
  private Joystick joystick;
  private Compressor compressor;
  private Pannel pannel;
  private Climber climber;
  private Ball ball;


  @Override
  public void robotInit() {
    compressor = new Compressor();
    joystick = new Joystick(1);
    driveTrain = DriveBase.getInstance();
    pannel = Pannel.getInstance();
    camera = new Camera();
    camera.Camera1();
    camera.Camera2();
    compressor.setClosedLoopControl(true);
    compressor.start();
    climber = Climber.getInstance();
    ball = Ball.getInstance();
    
  }
  private boolean direction;

  @Override
  public void robotPeriodic() {
 
  }

  @Override
  public void autonomousInit() {

  }

  @Override
  public void autonomousPeriodic() {

  }

  @Override
  public void teleopPeriodic() {
    if (Climber.climberRunning != true){
      if (joystick.getRawButton(10)){
        if (direction) {
          direction = false;
        }
        else {
          direction = true;
        }
      }
      if (direction){
        driveTrain.drive(ControlMode.PercentOutput, joystick.getX(), joystick.getY());
      }
      else {
        driveTrain.drive(ControlMode.PercentOutput, -joystick.getX(), -joystick.getY());
      }
      pannel.grab();
      pannel.control();
      //climber.Acsending();
      //ball.ballControl();
    }
    else {
      
    }
  }
  @Override
  public void testPeriodic() {
  }
}
