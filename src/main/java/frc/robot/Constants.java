// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static final class JoystickConstants {
    public static final int kDriverControllerPort = 0;
    public static final int kOperatorControllerPort = 1;
  }

  public static final class CAN_IDs {
    public static final int frontLeft1ID = 1;
    public static final int backLeft1ID = 2;
    public static final int frontRight1ID = 3;
    public static final int backRight1ID = 4;
    public static final int frontLeft2ID = 5;
    public static final int backLeft2ID = 6;
    public static final int frontRight2ID = 7;
    public static final int backRight2ID = 8;

    public static final int shoulderID = 0;
    public static final int forearmID = 0;
    public static final int wristID = 0;
    public static final int intakeID = 0;
  }

  public static final class RobotConstants {
    public static final double wheelDiameter = 8;
    public static final double limelightMountHeight = 35;

    // gear ratios
    public static final double driveGearRatio = 8.4;
    public static final double forearmGearRatio = 0.005208;
    public static final double shoulderGearRatio = 0.008;
    public static final double wristGearRatio = 0.0052;
    
  }

  public static final class FieldElements {
    // all measurements in inches
    public static final double topNodeHeight = 41.875;
    public static final double middleNodeHeight = 22.125;
    public static final double apriltagLowerHeight = 14.55;
    public static final double apriltagUpperHeight = 52;
    public static final double apriltagHeight = 25;
  }


  public static final class ArmSetpoints {
    // High
    public static final double highShoulder = 15;
    public static final double highForearm = 115;
    public static final double highWrist = 140;

    // Mid
    public static final double lowShoulder = -20;
    public static final double lowForearm = 60;
    public static final double lowWrist = 107.10;

    // Floor Intake
    public static final double floorShoulder = 25;
    public static final double floorForearm = 20;
    public static final double floorWrist = 66;

    // Double Substation
    public static final double doubleSubstationShoulder = -25;
    public static final double doubleSubstationForearm = 85;
    public static final double doubleSubstationWrist = 155;

    // Single Substation
    public static final double singleSubstationShoulder = 17;
    public static final double singleSubstationForearm = -60;
    public static final double singleSubstationWrist = 160;

    // Reset
    public static final double resetShoulder = 0;
    public static final double resetForearm = 0;
    public static final double resetWrist = 0;
  }

  /* Joystick controls:
    driver:
      drive: rightStick Y axis
      strafe: rightStick X axis
      rotate: leftStick X axis

      armAutomated: X button

      apriltag pipeline: A Button
      low pipeline: B Button
      high pipeline: B Button

    operator:
      shoulderManual: rightStick X axis
      forearmManual: rightStick Y axis
      wristManual: leftStick Y axis
      
      intake: rightTrigger
      outake: leftTrigger

      highSetpoint: Y Button
      lowStepoint: B Button
      floorSetpoint: A Button
      doubleSubstationSetpoint: X button
      singleSubstationSetpoint: none
      resetSetpoint: Start button (the button with 3 lines)
  */
}
