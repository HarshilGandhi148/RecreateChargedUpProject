// // Copyright (c) FIRST and other WPILib contributors.
// // Open Source Software; you can modify and/or share it under the terms of
// // the WPILib BSD license file in the root directory of this project.

// package frc.robot.commands;

// import edu.wpi.first.wpilibj2.command.Command;
// import frc.robot.RobotContainer;
// import frc.robot.RobotContainer.Subsystems;

// public class PipelineSwitchCommand extends Command {
//   /** Creates a new PipelineSwicthCommand. */
//   public PipelineSwitchCommand() {
//     // Use addRequirements() here to declare subsystem dependencies.
//     addRequirements(Subsystems.limelightSubsystem);
//   }

//   // Called when the command is initially scheduled.
//   @Override
//   public void initialize() {
//     Subsystems.limelightSubsystem.setPipeline(0);
//   }

//   // Called every time the scheduler runs while the command is scheduled.
//   @Override
//   public void execute() {
//     if (RobotContainer.driver.getYButtonPressed()) {
//       Subsystems.limelightSubsystem.setPipeline(2);
//     } else if (RobotContainer.driver.getBButtonPressed()) {
//       Subsystems.limelightSubsystem.setPipeline(1);
//     } else if (RobotContainer.driver.getAButtonPressed()) {
//       Subsystems.limelightSubsystem.setPipeline(0);
//     }
//   }

//   // Called once the command ends or is interrupted.
//   @Override
//   public void end(boolean interrupted) {}

//   // Returns true when the command should end.
//   @Override
//   public boolean isFinished() {
//     return false;
//   }
// }
