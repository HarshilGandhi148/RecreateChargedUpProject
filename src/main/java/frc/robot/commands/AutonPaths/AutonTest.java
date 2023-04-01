// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutonPaths;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer.Subsystems;
import frc.robot.commands.AutonCommands.AutonBalanceCommand;
import frc.robot.commands.AutonCommands.AutonDriveCommand;
import frc.robot.commands.AutonCommands.AutonRotateCommand;
import frc.robot.commands.AutonCommands.AutonStrafeCommand;


/** Add your docs here. */
public class AutonTest {

    public AutonTest() {
        Subsystems.driveSubsystem.resetEncoders();
    }
    public static Command getCommand(){
        return new AutonDriveCommand(100, 0.2)
    /*  .andThen(new AutonStrafeCommand(3, 0.2))
        .andThen (new AutonIntakeCommand(true, 3.5))
        .andThen(new AutonRotateCommand(180))
        .andThen(new AutonDriveCommand(50, 0.2))
        .andThen(new AutonBalanceCommand()) */;
    }
}
