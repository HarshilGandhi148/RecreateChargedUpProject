// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.net.PortForwarder;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.RobotContainer.Subsystems;
//import frc.robot.commands.ArmCommand;
import frc.robot.commands.BalanceCommand;
import frc.robot.commands.DriveCommand;
//import frc.robot.commands.PipelineSwitchCommand;


/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();

    //TODO: test
    // sets up port forwarding for use of limelight with ethernet (ports 5800, 5801, 5802, 5803, 5804, and 5805)
      //for (int port = 5800; port <= 5805; port++) {
      //  PortForwarder.add(port, "limelight.local", port);
      //}

  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {
     Subsystems.driveSubsystem.setCoastEnabled();
     Subsystems.armSubsystem.setArmBrakeEnabled();
     CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {

    Subsystems.driveSubsystem.setBrakeEnabled();
    Subsystems.driveSubsystem.setCurrentLimits(60);
    Subsystems.driveSubsystem.resetGyro();
    Subsystems.driveSubsystem.resetEncoders();
    Subsystems.armSubsystem.resetArmEncoders();
    Subsystems.armSubsystem.resetArmEncoders();
    Subsystems.armSubsystem.resetSetpoints();

    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    Subsystems.driveSubsystem.setBrakeEnabled();
    Subsystems.driveSubsystem.setRampRate(0.16667);
    Subsystems.driveSubsystem.setCurrentLimits(35);
    Subsystems.armSubsystem.setArmBrakeEnabled();

    // not used in competition as teleop should not reset encoders (comment it out)
      Subsystems.driveSubsystem.resetEncoders();
      Subsystems.armSubsystem.resetArmEncoders();
      Subsystems.driveSubsystem.resetGyro();
      Subsystems.armSubsystem.resetSetpoints();


    CommandScheduler.getInstance().setDefaultCommand(Subsystems.driveSubsystem, (new DriveCommand(() -> RobotContainer.driver.getLeftY(), () -> RobotContainer.driver.getLeftX(), () -> RobotContainer.driver.getRightX())));
    //CommandScheduler.getInstance().schedule(new ArmCommand());
    //CommandScheduler.getInstance().schedule(new BalanceCommand());
    //CommandScheduler.getInstance().schedule(new PipelineSwitchCommand());

    /*--------------------------------------------------- 
      This makes sure that the autonomous stops running when
      teleop starts running. If you want the autonomous to
      continue until interrupted by another command, remove
      this line or comment it out. 
    -----------------------------------------------------*/
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    SmartDashboard.putNumber("Drive Encoder Distance", Subsystems.driveSubsystem.getAverageEncoderDistance());
    SmartDashboard.putNumber("Gyro Angle", Subsystems.driveSubsystem.getGyroYaw());

    SmartDashboard.putNumber("Shoulder Angle", Subsystems.armSubsystem.getShoulderAngle());
    SmartDashboard.putNumber("Forearm Angle", Subsystems.armSubsystem.getForearmAngle());
    SmartDashboard.putNumber("Wrist Angle", Subsystems.armSubsystem.getWristAngle());

    if (Subsystems.limelightSubsystem.pipeline == 0) {
      SmartDashboard.putString("Pipeline mode", "Apriltag: " + Subsystems.limelightSubsystem.tid);
    } else if (Subsystems.limelightSubsystem.pipeline == 1) {
      SmartDashboard.putString("Pipeline mode", "Low cone");
    } else if (Subsystems.limelightSubsystem.pipeline == 2) {
      SmartDashboard.putString("Pipeline mode", "High cone");
    } else {
      SmartDashboard.putString("Pipeline mode", "Unknown");
    }
    
    // Also SmartDashboard value for armAutomated in ArmCommand
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
    Subsystems.driveSubsystem.setCoastEnabled();
    Subsystems.armSubsystem.setArmCoastEnabled();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}