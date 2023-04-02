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
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {
  
  // driveMotorControllers
  public CANSparkMax frontLeftMotor1 = new CANSparkMax(Constants.CAN_IDs.frontLeft1ID, MotorType.kBrushless);
  public CANSparkMax frontRightMotor1 = new CANSparkMax(Constants.CAN_IDs.frontRight1ID, MotorType.kBrushless);
  public CANSparkMax backLeftMotor1 = new CANSparkMax(Constants.CAN_IDs.backLeft1ID, MotorType.kBrushless);
  public CANSparkMax backRightMotor1 = new CANSparkMax(Constants.CAN_IDs.backRight1ID, MotorType.kBrushless);
  public CANSparkMax frontLeftMotor2 = new CANSparkMax(Constants.CAN_IDs.frontLeft2ID, MotorType.kBrushless);
  public CANSparkMax frontRightMotor2 = new CANSparkMax(Constants.CAN_IDs.frontRight2ID, MotorType.kBrushless);
  public CANSparkMax backLeftMotor2 = new CANSparkMax(Constants.CAN_IDs.backLeft2ID, MotorType.kBrushless);
  public CANSparkMax backRightMotor2 = new CANSparkMax(Constants.CAN_IDs.backRight2ID, MotorType.kBrushless);

  MecanumDrive mecanumDrive;

  public AHRS gyro;
  Rotation2d rotation2d;

  // driveEncoders
  public RelativeEncoder frontLeftEncoder1;
  public RelativeEncoder frontRightEncoder1;
  public RelativeEncoder backLeftEncoder1;
  public RelativeEncoder backRightEncoder1;
  public RelativeEncoder frontLeftEncoder2;
  public RelativeEncoder frontRightEncoder2;
  public RelativeEncoder backLeftEncoder2;
  public RelativeEncoder backRightEncoder2;

  public double frontLeftEncoder;
  public double frontRightEncoder;
  public double backLeftEncoder;
  public double backRightEncoder;

  // motor controller groups
  public MotorControllerGroup frontLeftGroup = new MotorControllerGroup(frontLeftMotor1, frontLeftMotor2);
  public MotorControllerGroup frontRightGroup = new MotorControllerGroup(frontRightMotor1, frontRightMotor2);
  public MotorControllerGroup backLeftGroup = new MotorControllerGroup(backLeftMotor1, backLeftMotor2);
  public MotorControllerGroup backRightGroup = new MotorControllerGroup(backRightMotor1, backRightMotor2);


  
  /** Creates a new Subsystem. */
  public DriveSubsystem() {
    frontLeftEncoder1 = frontLeftMotor1.getEncoder();
    frontRightEncoder1 = frontRightMotor1.getEncoder();
    backLeftEncoder1 = backLeftMotor1.getEncoder();
    backRightEncoder1 = backRightMotor1.getEncoder();
    frontLeftEncoder2 = frontLeftMotor1.getEncoder();
    frontRightEncoder2 = frontRightMotor1.getEncoder();
    backLeftEncoder2 = backLeftMotor1.getEncoder();
    backRightEncoder2 = backRightMotor1.getEncoder();

    frontLeftEncoder = (frontLeftEncoder1.getPosition() + frontLeftEncoder2.getPosition())/2;
    frontRightEncoder = (frontRightEncoder1.getPosition() + frontRightEncoder2.getPosition())/2;
    backLeftEncoder = (backLeftEncoder1.getPosition() + backLeftEncoder2.getPosition())/2;
    backRightEncoder = (backRightEncoder1.getPosition() + backRightEncoder2.getPosition())/2;



    frontRightGroup.setInverted(true);
    backRightGroup.setInverted(true);

    mecanumDrive = new MecanumDrive(frontLeftGroup, backLeftGroup, frontRightGroup, backRightGroup);

    gyro = new AHRS(Port.kMXP);
    }

  public void setCurrentLimits(int currentLimit){
    frontLeftMotor1.setSmartCurrentLimit(currentLimit);
    frontRightMotor1.setSmartCurrentLimit(currentLimit);
    backLeftMotor1.setSmartCurrentLimit(currentLimit);
    backRightMotor1.setSmartCurrentLimit(currentLimit);
    frontLeftMotor2.setSmartCurrentLimit(currentLimit);
    frontRightMotor2.setSmartCurrentLimit(currentLimit);
    backLeftMotor2.setSmartCurrentLimit(currentLimit);
    backRightMotor2.setSmartCurrentLimit(currentLimit);
  }
  
  public void enableDriveVoltage(double voltage){
    //default is 11
    frontLeftMotor1.enableVoltageCompensation(voltage); 
    frontRightMotor1.enableVoltageCompensation(voltage);
    backLeftMotor1.enableVoltageCompensation(voltage);
    backRightMotor1.enableVoltageCompensation(voltage);
    frontLeftMotor2.enableVoltageCompensation(voltage); 
    frontRightMotor2.enableVoltageCompensation(voltage);
    backLeftMotor2.enableVoltageCompensation(voltage);
    backRightMotor2.enableVoltageCompensation(voltage);
  }
  
  public void disableDriveVoltage(){
    frontLeftMotor1.disableVoltageCompensation();
    frontRightMotor1.disableVoltageCompensation();
    backLeftMotor1.disableVoltageCompensation();
    backRightMotor1.disableVoltageCompensation();
    frontLeftMotor2.disableVoltageCompensation();
    frontRightMotor2.disableVoltageCompensation();
    backLeftMotor2.disableVoltageCompensation();
    backRightMotor2.disableVoltageCompensation();
  }
  
  public void setRampRate(double rate){
    frontLeftMotor1.setOpenLoopRampRate(rate);
    frontRightMotor1.setOpenLoopRampRate(rate);
    backLeftMotor1.setOpenLoopRampRate(rate);
    backRightMotor1.setOpenLoopRampRate(rate);
    frontLeftMotor2.setOpenLoopRampRate(rate);
    frontRightMotor2.setOpenLoopRampRate(rate);
    backLeftMotor2.setOpenLoopRampRate(rate);
    backRightMotor2.setOpenLoopRampRate(rate);
  }

  public void setBrakeEnabled() {
    frontLeftMotor1.setIdleMode(IdleMode.kBrake);
    frontRightMotor1.setIdleMode(IdleMode.kBrake);
    backLeftMotor1.setIdleMode(IdleMode.kBrake);
    backRightMotor1.setIdleMode(IdleMode.kBrake);
    frontLeftMotor2.setIdleMode(IdleMode.kBrake);
    frontRightMotor2.setIdleMode(IdleMode.kBrake);
    backLeftMotor2.setIdleMode(IdleMode.kBrake);
    backRightMotor2.setIdleMode(IdleMode.kBrake);
  }

  public void setCoastEnabled() {
    frontLeftMotor1.setIdleMode(IdleMode.kCoast);
    frontRightMotor1.setIdleMode(IdleMode.kCoast);
    backLeftMotor1.setIdleMode(IdleMode.kCoast);
    backRightMotor1.setIdleMode(IdleMode.kCoast);
    frontLeftMotor2.setIdleMode(IdleMode.kCoast);
    frontRightMotor2.setIdleMode(IdleMode.kCoast);
    backLeftMotor2.setIdleMode(IdleMode.kCoast);
    backRightMotor2.setIdleMode(IdleMode.kCoast);
  }

  public void resetEncoders() {
    frontLeftEncoder1.setPosition(0);
    frontRightEncoder1.setPosition(0);
    backLeftEncoder1.setPosition(0);
    backRightEncoder1.setPosition(0);
    frontLeftEncoder2.setPosition(0);
    frontRightEncoder2.setPosition(0);
    backLeftEncoder2.setPosition(0);
    backRightEncoder2.setPosition(0);
  }

  public double getAverageEncoderDistance() {
    double conversionFactor = Math.PI * Constants.RobotConstants.WHEEL_DIAMETER / Constants.RobotConstants.GEAR_RATIO;
    return (frontLeftEncoder*conversionFactor + frontRightEncoder*conversionFactor + backLeftEncoder*conversionFactor + backRightEncoder*conversionFactor)/4;
  }

  public void resetGyro () {
    gyro.reset();
  }

  public double getGyroYaw () {
    return gyro.getYaw() % 360;
  }

  public double getGyroPitch () {
    // 8.92 is offset
    return (gyro.getPitch() + 8.92) % 360;
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
