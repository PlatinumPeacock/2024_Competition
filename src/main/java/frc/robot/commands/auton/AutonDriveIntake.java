// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auton;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.auton.AutoDrive;
import com.ctre.phoenix6.mechanisms.swerve.SwerveRequest;
import frc.robot.CommandSwerveDrivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Feeder;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutonDriveIntake extends SequentialCommandGroup {
  /** Creates a new Auton. */
  public AutonDriveIntake(Feeder f, Intake i, CommandSwerveDrivetrain dt, SwerveRequest.FieldCentric d, double t) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new DriveIntake(f, i, dt, d, t, -0.2, 0, 0));
    
  }
}
 