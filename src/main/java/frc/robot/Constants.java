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
  public static class JoystickConstants {
    public static final int kDriverControllerPort = 0;
    public static final int kOperatorControllerPort = 1;
  }

  public static class CAN_IDs {
    public static final int frontLeftID = 1;
    public static final int backLeftID = 2;
    public static final int frontRightID = 3;
    public static final int backRightID = 4;

    public static final int shoulderID = 0;
    public static final int forearmID = 0;
    public static final int wristID = 0;
    public static final int intakeID = 0;
  }

  public static class ArmSetpoints {
    // High
    public static final double highShoulder = 3.5;
    public static final double highForearm = 135;
    public static final double highWrist = 117.15;


    // Low
    public static final double lowShoulder = -10;
    public static final double lowForearm = 90;
    public static final double lowWrist = 107.10;

    // Floor Intake
    public static final double floorShoulder = 13;
    public static final double floorForearm = 34.83;
    public static final double floorWrist = 45;

    // Substation
    public static final double substationShoulder = 5.14;
    public static final double substationForearm = 120;
    public static final double substationWrist = 108;

    // Reset
    public static final double resetShoulder = 0;
    public static final double resetForearm = 0;
    public static final double resetWrist = 0;
  }

  public static final class RobotConstants {
    public static final double WHEEL_DIAMETER = 8;
    public static final double GEAR_RATIO = 8.4;
  }

  /* Joystick controls:
    driver:
      drive: rightStick Y axis
      strafe: rightStick X axis
      rotate: leftStick X axis
      armAutomated: A button
    operator;
      shoulderManual: rightStick X axis
      forearmManual: rightStick Y axis
      wristManual: leftStick Y axis
      intake: rightTrigger
      outake: leftTrigger

      highSetpoint: Y Button
      lowStepoint: B Button
      floorSetpoint: A Button
      substationSetpoint: X button
      resetSetpoint: the button with 3 lines
  */
}
