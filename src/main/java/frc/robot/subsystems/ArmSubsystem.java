// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.utils.GalacPIDController;

public class ArmSubsystem extends SubsystemBase {

  // armMotorControllers
  public CANSparkMax shoulderMotor = new CANSparkMax(Constants.CAN_IDs.shoulderID, MotorType.kBrushless);
  public CANSparkMax forearmMotor = new CANSparkMax(Constants.CAN_IDs.forearmID, MotorType.kBrushless);
  public CANSparkMax wristMotor = new CANSparkMax(Constants.CAN_IDs.wristID, MotorType.kBrushless);
  public CANSparkMax intakeMotor = new CANSparkMax(Constants.CAN_IDs.intakeID, MotorType.kBrushless);

  //armEncoders
  public RelativeEncoder shoulderEncoder;
  public RelativeEncoder forearmEncoder;
  public RelativeEncoder wristEncoder;
  public RelativeEncoder intakeEncoder;

  //setpoints
  public double shoulderSetpoint;
  public double forearmSetpoint;
  public double wristSetpoint;

  //gearRatios
  double forearmGearRatio = 0.008;
  double shoulderGearRatio = 60;
  double wristGearRatio = 0.0052;

  // PID
  public GalacPIDController shoulderPID = new GalacPIDController(0.017, 0, 0, 0.01, () -> getShoulderAngle(), 0, 0);
  public GalacPIDController forearmPID = new GalacPIDController(0.02, 0.00, 0, 0.01, () -> getForearmAngle(), 0, 0);
  public GalacPIDController wristPID = new GalacPIDController(0.005, 0, 0, 0.007, () -> getWristAngle(), 0, 0);
  
  /** Creates a new ArmSubsystem. */
  public ArmSubsystem() {
    shoulderEncoder = shoulderMotor.getEncoder();
    forearmEncoder = forearmMotor.getEncoder();
    wristEncoder = wristMotor.getEncoder();
    intakeEncoder = intakeMotor.getEncoder();
  }

  public void resetArmEncoders() {  
    shoulderEncoder.setPosition(0);
    forearmEncoder.setPosition(0);
    wristEncoder.setPosition(0);
    intakeEncoder.setPosition(0);
  }

  public void setArmBrakeEnabled() {
    shoulderMotor.setIdleMode(IdleMode.kBrake);
    forearmMotor.setIdleMode(IdleMode.kBrake);
    wristMotor.setIdleMode(IdleMode.kBrake);
    intakeMotor.setIdleMode(IdleMode.kBrake);
  }

  public void setArmCoastEnabled() {
    shoulderMotor.setIdleMode(IdleMode.kCoast);
    forearmMotor.setIdleMode(IdleMode.kCoast);
    wristMotor.setIdleMode(IdleMode.kCoast);
    intakeMotor.setIdleMode(IdleMode.kCoast);
  }

  public void moveShoulder(double shoulderEffort) {
    shoulderMotor.set(shoulderEffort);
  }

  public void moveForearm(double forearmEffort) {
    forearmMotor.set(forearmEffort);
  } 
  
  public void moveWrist(double wristEffort) {
    forearmMotor.set(wristEffort);
  }

  public void moveIntake(double intakeEffort) {
    intakeMotor.set(intakeEffort);
  }

  public void resetSetpoints() {
    shoulderSetpoint = 0;
    forearmSetpoint = 0;
    wristSetpoint = 0;
  }

  public double getShoulderAngle() {
    return shoulderEncoder.getPosition();
  }

  public double getForearmAngle() {
    return (forearmEncoder.getPosition() * 360 * forearmGearRatio);
  }

  public double getWristAngle() {
    return (wristEncoder.getPosition() * 360 * wristGearRatio);
  }

  public boolean hasFinished() {
    return ((Math.abs(getShoulderAngle() - shoulderPID.getSetpoint()) < 5) && 
            (Math.abs(getForearmAngle() - forearmPID.getSetpoint()) < 12) && 
            (Math.abs(getWristAngle() - wristPID.getSetpoint()) < 5));
  }

  public void runArmPID () {
    shoulderPID.setSetpoint(shoulderSetpoint);
    forearmPID.setSetpoint(forearmSetpoint);
    wristPID.setSetpoint(wristSetpoint);

    moveShoulder(shoulderPID.getEffort());
    moveForearm(forearmPID.getEffort());
    moveWrist(wristPID.getEffort());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
