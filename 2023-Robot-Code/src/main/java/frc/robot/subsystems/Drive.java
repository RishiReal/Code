// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class Drive extends SubsystemBase {

  final static WPI_TalonSRX leftDrive = new WPI_TalonSRX(RobotMap.LEFT_MOTOR);
  final static WPI_TalonSRX leftFollow = new WPI_TalonSRX(RobotMap.LEFT_FOLLOWER);
  
  final static WPI_TalonSRX rightDrive = new WPI_TalonSRX(RobotMap.RIGHT_MOTOR);
  final static WPI_TalonSRX rightFollow = new WPI_TalonSRX(RobotMap.RIGHT_FOLLOWER);

  int rEncoder = 0;
  int lEncoder = 0;

  double target = 0;

  MotorControllerGroup lGroup = null;
  MotorControllerGroup rGroup = null;
  DifferentialDrive diffDrive = null;

  boolean isDone = false;
  
  /** Creates a new Drive. */
  public Drive() {
    leftDrive.setInverted(true);
    leftFollow.setInverted(true);
    leftFollow.follow(leftDrive);

    rightDrive.setInverted(false);
    rightFollow.setInverted(false);
    rightFollow.follow(rightDrive);

    leftDrive.configOpenloopRamp(0);
    rightDrive.configOpenloopRamp(0);
    
    resetEncoders();

    diffDrive = new DifferentialDrive(leftDrive, rightDrive);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  
  public void vDriveTank(double left, double right){
    diffDrive.tankDrive(left, right, true);
    
  }
  public void vDriveArcade (double left, double right) {
    diffDrive.arcadeDrive(left, right, true);
  }

  public void vDriveCurvature (double left, double right, boolean turn) {
    diffDrive.arcadeDrive(left, right, turn);
  } 

  public void resetEncoders(){
    leftDrive.setSelectedSensorPosition(0);
    rightDrive.setSelectedSensorPosition(0);
  }

  public static double getLeftEncoder() {
    return leftDrive.getSelectedSensorPosition();
  }

  public static double getRightEncoder() {
    return rightDrive.getSelectedSensorPosition();
  }

  public boolean isDone() {
    return isDone;
  }

  public void reset() {
    isDone = false;
    resetEncoders();
  }



}
