// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutonCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.RobotContainer.Subsystems;

public class AutonArmCommand extends Command {
  int level;
  Timer timer;

  /** Creates a new AutonArmCommand. */
  // level {int} - 0 = reset, 1 = ground, 2 = middle, 3 = high
  public AutonArmCommand(int level) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Subsystems.armSubsystem);
    this.level = level;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.reset();
    timer.start();
    Subsystems.armSubsystem.setArmBrakeEnabled();
    Subsystems.armSubsystem.moveShoulder(0);
    Subsystems.armSubsystem.moveForearm(0);
    Subsystems.armSubsystem.moveWrist(0);
    
    switch (level) {
      case 0:
        Subsystems.armSubsystem.shoulderSetpoint = Constants.ArmSetpoints.resetShoulder;
        Subsystems.armSubsystem.forearmSetpoint = Constants.ArmSetpoints.resetForearm;
        Subsystems.armSubsystem.wristSetpoint = Constants.ArmSetpoints.resetWrist;
        break;
      case 1:
        // numbers make it ground place not floor intake
        Subsystems.armSubsystem.shoulderSetpoint = Constants.ArmSetpoints.floorShoulder;
        Subsystems.armSubsystem.forearmSetpoint = Constants.ArmSetpoints.floorForearm;
        Subsystems.armSubsystem.wristSetpoint = Constants.ArmSetpoints.floorWrist + 1.5;
        break;
      case 2:
        Subsystems.armSubsystem.shoulderSetpoint = Constants.ArmSetpoints.lowShoulder;
        Subsystems.armSubsystem.forearmSetpoint = Constants.ArmSetpoints.lowForearm;
        Subsystems.armSubsystem.wristSetpoint = Constants.ArmSetpoints.lowWrist;
        break;
      case 3:
        Subsystems.armSubsystem.shoulderSetpoint = Constants.ArmSetpoints.highShoulder;
        Subsystems.armSubsystem.forearmSetpoint = Constants.ArmSetpoints.highForearm;
        Subsystems.armSubsystem.wristSetpoint = Constants.ArmSetpoints.highWrist;
        break;

      default:
        break;
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Subsystems.armSubsystem.runArmPID();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Subsystems.armSubsystem.hasFinished() || timer.get() > 3.5;
  }
}
