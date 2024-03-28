// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auton;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.CommandSwerveDrivetrain;
import com.ctre.phoenix6.mechanisms.swerve.SwerveRequest;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Tester extends SequentialCommandGroup {
  /** Creates a new Tester. */
  public Tester(Feeder f, Intake i, Shooter s, CommandSwerveDrivetrain dt, SwerveRequest.FieldCentric d) {
    addCommands(new DriveOnly(dt, d, 1.6, 0.3, -0.9, 0.85));
  }
}
