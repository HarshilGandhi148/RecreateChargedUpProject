// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutonCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer.Subsystems;
import frc.robot.utils.GalacPIDController;

public class AutonStrafeCommand extends CommandBase {

  double effort;
  double time;
  Timer timer;
  GalacPIDController turnPID;
  /** Creates a new AutonDriveCommand. */
  public AutonStrafeCommand(double time, double effort) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Subsystems.driveSubsystem);
    this.effort = effort;
    this.time = time;
    turnPID = new GalacPIDController(0.01, 0, 0, 0.05, () -> Subsystems.driveSubsystem.getGyroYaw(), 0, 1);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.reset();
    timer.start();
    Subsystems.driveSubsystem.setBrakeEnabled();
    Subsystems.driveSubsystem.mechDrive(0, 0, 0, true);
    Subsystems.driveSubsystem.resetEncoders();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Subsystems.driveSubsystem.mechDrive(0, -effort, -turnPID.getEffort(), true);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Subsystems.driveSubsystem.setBrakeEnabled();
    Subsystems.driveSubsystem.mechDrive(0, 0, 0, true);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return timer.get() > time;
  }
}
