// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class LimelightSubsystem extends SubsystemBase {
  /* Pipelines:
    0 - Apriltag
    1 - Low Cone
    2 - High Cone
   */
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  NetworkTableEntry txEntry = table.getEntry("tx");;
  NetworkTableEntry tyEntry = table.getEntry("ty");;
  NetworkTableEntry taEntry = table.getEntry("ta");
  NetworkTableEntry tidEntry = table.getEntry("tid");
  NetworkTableEntry tvEntry = table.getEntry("tv");
  NetworkTableEntry pipelineEntry = table.getEntry("pipeline");
  double tx;
  double ty;
  double ta;
  public double tid;
  boolean tv;
  public int pipeline = 0;

  /** Creates a new LimelightSubsystem. */
  public LimelightSubsystem() {
    pipelineEntry.setInteger(0);
  }

  public void setPipeline(int m_pipeline) {
    pipelineEntry.setInteger(m_pipeline);
    pipeline = m_pipeline;
  }

  @Override
  public void periodic() {
    tx = txEntry.getDouble(0.0);
    ty = tyEntry.getDouble(0.0);
    ta = taEntry.getDouble(0.0);
    tid = tidEntry.getDouble(0.0);
    if ((tvEntry.getInteger(0)) == 1) {
      tv = true;
    } else if (((tvEntry.getInteger(0)) == 0)) {
      tv = false;
    }
  }
}
