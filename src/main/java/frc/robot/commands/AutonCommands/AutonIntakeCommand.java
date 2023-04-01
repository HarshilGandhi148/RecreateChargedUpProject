// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutonCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer.Subsystems;

public class AutonIntakeCommand extends CommandBase {

  Timer timer;
  double time;
  boolean intake;
  /** Creates a new AutonIntakeCommand. */
  public AutonIntakeCommand(boolean intake, double time) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Subsystems.armSubsystem);
    this.intake = intake;
    this.time = time;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.reset();
    timer.start();
    Subsystems.armSubsystem.setArmBrakeEnabled();
    Subsystems.armSubsystem.moveIntake(0);

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (intake) {
      Subsystems.armSubsystem.moveIntake(0.4);
    } else if (!intake) {
      Subsystems.armSubsystem.moveIntake(-0.4);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Subsystems.armSubsystem.moveIntake(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return timer.get() > time;
  }
}
