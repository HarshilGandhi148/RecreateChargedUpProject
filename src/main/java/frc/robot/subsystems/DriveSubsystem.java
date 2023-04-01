// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {
  
  // driveMotorControllers
  public CANSparkMax frontLeftMotor = new CANSparkMax(Constants.CAN_IDs.frontLeftID, MotorType.kBrushless);
  public CANSparkMax frontRightMotor = new CANSparkMax(Constants.CAN_IDs.frontRightID, MotorType.kBrushless);
  public CANSparkMax backLeftMotor = new CANSparkMax(Constants.CAN_IDs.backLeftID, MotorType.kBrushless);
  public CANSparkMax backRightMotor = new CANSparkMax(Constants.CAN_IDs.backRightID, MotorType.kBrushless);

  MecanumDrive mecanumDrive;

  public AHRS gyro;
  Rotation2d rotation2d;

  // driveEncoders
  public RelativeEncoder frontLeftEncoder;
  public RelativeEncoder frontRightEncoder;
  public RelativeEncoder backLeftEncoder;
  public RelativeEncoder backRightEncoder;


  
  /** Creates a new Subsystem. */
  public DriveSubsystem() {
    frontLeftEncoder = frontLeftMotor.getEncoder();
    frontRightEncoder = frontRightMotor.getEncoder();
    backLeftEncoder = backLeftMotor.getEncoder();
    backRightEncoder = backRightMotor.getEncoder();

    frontRightMotor.setInverted(true);
    backRightMotor.setInverted(true);

    mecanumDrive = new MecanumDrive(frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor);

    gyro = new AHRS(Port.kMXP);
    }

  public void setCurrentLimits(int currentLimit){
    frontLeftMotor.setSmartCurrentLimit(currentLimit);
    frontRightMotor.setSmartCurrentLimit(currentLimit);
    backLeftMotor.setSmartCurrentLimit(currentLimit);
    backRightMotor.setSmartCurrentLimit(currentLimit);
  }
  
  public void enableDriveVoltage(double voltage){
    //default is 11
    frontLeftMotor.enableVoltageCompensation(voltage); 
    frontRightMotor.enableVoltageCompensation(voltage);
    backLeftMotor.enableVoltageCompensation(voltage);
    backRightMotor.enableVoltageCompensation(voltage);
  }
  
  public void disableDriveVoltage(){
    frontLeftMotor.disableVoltageCompensation();
    frontRightMotor.disableVoltageCompensation();
    backLeftMotor.disableVoltageCompensation();
    backRightMotor.disableVoltageCompensation();
  }
  
  public void setRampRate(double rate){
    frontLeftMotor.setOpenLoopRampRate(rate);
    frontRightMotor.setOpenLoopRampRate(rate);
    backLeftMotor.setOpenLoopRampRate(rate);
    backRightMotor.setOpenLoopRampRate(rate);
  }

  public void setBrakeEnabled() {
    frontLeftMotor.setIdleMode(IdleMode.kBrake);
    frontRightMotor.setIdleMode(IdleMode.kBrake);
    backLeftMotor.setIdleMode(IdleMode.kBrake);
    backRightMotor.setIdleMode(IdleMode.kBrake);
  }

  public void setCoastEnabled() {
    frontLeftMotor.setIdleMode(IdleMode.kCoast);
    frontRightMotor.setIdleMode(IdleMode.kCoast);
    backLeftMotor.setIdleMode(IdleMode.kCoast);
    backRightMotor.setIdleMode(IdleMode.kCoast);
  }

  public void resetEncoders() {
    frontLeftEncoder.setPosition(0);
    frontRightEncoder.setPosition(0);
    backLeftEncoder.setPosition(0);
    backRightEncoder.setPosition(0);
  }

  public double getAverageEncoderDistance() {
    double conversionFactor = Math.PI * Constants.RobotConstants.WHEEL_DIAMETER / Constants.RobotConstants.GEAR_RATIO;
    return (frontLeftEncoder.getPosition()*conversionFactor + frontRightEncoder.getPosition()*conversionFactor + backLeftEncoder.getPosition()*conversionFactor + backRightEncoder.getPosition()*conversionFactor)/4;
  }

  public void resetGyro () {
    gyro.reset();
  }

  public double getGyroYaw () {
    return gyro.getYaw();
  }

  public double getGyroPitch () {
    // 8.92 is offset
    return gyro.getPitch() + 8.92;
  }

  public void mechDrive(double forward, double strafe, double rotation, boolean isFieldOriented){
    rotation2d = Rotation2d.fromDegrees(gyro.getYaw());

    if(!isFieldOriented){
      mecanumDrive.driveCartesian(forward, strafe, rotation);
    }
    else{
      mecanumDrive.driveCartesian(forward, strafe, rotation, rotation2d);
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
