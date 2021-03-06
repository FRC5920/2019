/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class HatchHeight extends Command {
  int HatchLevel;
  Boolean HasRan = false;
  public HatchHeight(int HatchHeight) {
    requires(Robot.m_Elevator);
    HatchLevel = HatchHeight;
  }
  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    switch (HatchLevel){
    case 0:
      Robot.m_Elevator.Home();
      if (RobotMap.ElevatorLevels[0] -RobotMap.ElevatorError < RobotMap._elevatorMotor.getSelectedSensorPosition(0) && RobotMap._elevatorMotor.getSelectedSensorPosition(0) < RobotMap.ElevatorLevels[0]+RobotMap.ElevatorError){HasRan=true;}
      break;
    case 1:
      Robot.m_Elevator.Level1();
      if (RobotMap.ElevatorLevels[1] -RobotMap.ElevatorError < RobotMap._elevatorMotor.getSelectedSensorPosition(0) && RobotMap._elevatorMotor.getSelectedSensorPosition(0) < RobotMap.ElevatorLevels[1]+RobotMap.ElevatorError){HasRan=true;}
      break;
    case 2:
      Robot.m_Elevator.Level2();
      if (RobotMap.ElevatorLevels[2] -RobotMap.ElevatorError < RobotMap._elevatorMotor.getSelectedSensorPosition(0) && RobotMap._elevatorMotor.getSelectedSensorPosition(0) < RobotMap.ElevatorLevels[2]+RobotMap.ElevatorError){HasRan=true;}
      break;
    case 3: 
      Robot.m_Elevator.Level3();
      if (RobotMap.ElevatorLevels[3] -RobotMap.ElevatorError < RobotMap._elevatorMotor.getSelectedSensorPosition(0) && RobotMap._elevatorMotor.getSelectedSensorPosition(0) < RobotMap.ElevatorLevels[3]+RobotMap.ElevatorError){HasRan=true;}
      break;
    case 4: 
      Robot.m_Elevator.StartClimb();
      if (RobotMap.ElevatorClimb[0] -RobotMap.ElevatorError < RobotMap._elevatorMotor.getSelectedSensorPosition(0) && RobotMap._elevatorMotor.getSelectedSensorPosition(0) < RobotMap.ElevatorClimb[0]+RobotMap.ElevatorError){HasRan=true;}
      break;
    case 5:
      Robot.m_Elevator.FinishClimb();
      if (RobotMap.ElevatorClimb[1] -RobotMap.ElevatorError < RobotMap._elevatorMotor.getSelectedSensorPosition(0) && RobotMap._elevatorMotor.getSelectedSensorPosition(0) < RobotMap.ElevatorClimb[1]+RobotMap.ElevatorError){HasRan=true;}
      break;
    default:
      Robot.m_Elevator.Goto(HatchLevel);
      if (HatchLevel - RobotMap.ElevatorError < RobotMap._elevatorMotor.getSelectedSensorPosition(0) && RobotMap._elevatorMotor.getSelectedSensorPosition(0) < HatchLevel+RobotMap.ElevatorError){HasRan=true;}
    break;
    }
    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return HasRan;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    HasRan = false;
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
