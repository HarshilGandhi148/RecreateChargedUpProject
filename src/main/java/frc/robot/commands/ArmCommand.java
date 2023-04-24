// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.RobotContainer.Subsystems;

public class ArmCommand extends CommandBase {

  boolean armAutomated;
  /** Creates a new ArmCommand. */
  public ArmCommand() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Subsystems.armSubsystem);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Subsystems.armSubsystem.setArmBrakeEnabled();
    Subsystems.armSubsystem.moveShoulder(0);
    Subsystems.armSubsystem.moveForearm(0);
    Subsystems.armSubsystem.moveWrist(0);
    Subsystems.armSubsystem.moveIntake(0);
    armAutomated = true;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    if (RobotContainer.driver.getAButtonReleased()) {
      armAutomated = !armAutomated;
    }
    SmartDashboard.putBoolean("Arm automated?", armAutomated);

    //movement of the intake
    if((RobotContainer.operator.getRightTriggerAxis() >= 0.2) && (RobotContainer.operator.getLeftTriggerAxis() < 0.2)) {
      Subsystems.armSubsystem.moveIntake(0.4);
    } else if ((RobotContainer.operator.getRightTriggerAxis() < 0.2) && (RobotContainer.operator.getLeftTriggerAxis() >= 0.2)) {
      Subsystems.armSubsystem.moveIntake(-0.4);
    } else {
      Subsystems.armSubsystem.moveIntake(0);
    }

    if (armAutomated) {
      if (RobotContainer.operator.getYButtonReleased()) {
        Subsystems.armSubsystem.shoulderSetpoint = Constants.ArmSetpoints.highShoulder;
        Subsystems.armSubsystem.forearmSetpoint = Constants.ArmSetpoints.highForearm;
        Subsystems.armSubsystem.wristSetpoint = Constants.ArmSetpoints.highWrist;

      } else if (RobotContainer.operator.getBButtonReleased()) {
        Subsystems.armSubsystem.shoulderSetpoint = Constants.ArmSetpoints.lowShoulder;
        Subsystems.armSubsystem.forearmSetpoint = Constants.ArmSetpoints.lowForearm;
        Subsystems.armSubsystem.wristSetpoint = Constants.ArmSetpoints.lowWrist;

      } else if (RobotContainer.operator.getAButtonReleased()) {
        Subsystems.armSubsystem.shoulderSetpoint = Constants.ArmSetpoints.floorShoulder;
        Subsystems.armSubsystem.forearmSetpoint = Constants.ArmSetpoints.floorForearm;
        Subsystems.armSubsystem.wristSetpoint = Constants.ArmSetpoints.floorWrist;

      } else if (RobotContainer.operator.getRawButtonReleased(8)) {
        // button with three lines
        Subsystems.armSubsystem.shoulderSetpoint = Constants.ArmSetpoints.doubleSubstationShoulder;
        Subsystems.armSubsystem.forearmSetpoint = Constants.ArmSetpoints.doubleSubstationForearm;
        Subsystems.armSubsystem.wristSetpoint = Constants.ArmSetpoints.doubleSubstationWrist;

      } else if (RobotContainer.operator.getXButtonPressed()) {
        Subsystems.armSubsystem.shoulderSetpoint = Constants.ArmSetpoints.resetShoulder;
        Subsystems.armSubsystem.forearmSetpoint = Constants.ArmSetpoints.resetForearm;
        Subsystems.armSubsystem.wristSetpoint = Constants.ArmSetpoints.resetWrist;
      }

      Subsystems.armSubsystem.runArmPID();

    } else if (!armAutomated) {
      //movement of the wrist
      if(Math.abs(RobotContainer.operator.getLeftY())>=0.2) {
        Subsystems.armSubsystem.moveWrist(RobotContainer.operator.getLeftY());
      }
      
      //movement of the forearm
      if(Math.abs(RobotContainer.operator.getRightY())>=0.2) {
        Subsystems.armSubsystem.moveForearm(RobotContainer.operator.getRightY());
      }

      //movement of the shoulder
      if(Math.abs(RobotContainer.operator.getRightX())>=0.2) {
        Subsystems.armSubsystem.moveShoulder(RobotContainer.operator.getRightX());
      }

      if ((!(Math.abs(RobotContainer.operator.getRightX())>=0.2)) && (!(Math.abs(RobotContainer.operator.getRightY())>=0.2)) && (!(Math.abs(RobotContainer.operator.getLeftY())>=0.2))) {
        Subsystems.armSubsystem.resetSetpoints();
        Subsystems.armSubsystem.runArmPID();
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
