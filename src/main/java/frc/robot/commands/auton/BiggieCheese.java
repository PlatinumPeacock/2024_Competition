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
public class BiggieCheese extends SequentialCommandGroup {
  /** Creates a new BiggieCheese. */
  public BiggieCheese(Feeder f, Intake i, Shooter s, CommandSwerveDrivetrain dt, SwerveRequest.FieldCentric d) {
    addCommands(new AutoShoot(f, s));
    addCommands(new AutoDrive(dt, d, f, 1, 0.1, -0.28, 0.3));
    addCommands(new DriveIntake(f, i, dt, d, 1.5, 0.4));
    addCommands(new AutoDrive(dt, d, f, 1.5, -0.4, 0, 0));
    addCommands(new AutoDrive(dt, d, f, 1, -0.1, 0.2, -0.25));
    addCommands(new AutoShoot(f, s));
    addCommands(new AutoDrive(dt, d, f, 1, 0.1, -0.2, 0.25));
    addCommands(new DriveIntake(f, i, dt, d, 4, 0.5));
  }
}
/*

⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⣀⣤⣴⠖⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⣾⣻⠽⣯⣟⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⣀⣀⡀⠀⠀⠀⠀⠀⢠⣾⡿⢿⣷⣴⣿⣿⣿⣿⣿⣽⣿⣤⣴⡾⠿⣿⣶⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⢀⣤⣿⣿⣿⣿⣿⣿⣿⣤⣤⣄⣀⣘⣿⣜⢣⣿⣿⣿⣿⣿⣿⢿⡿⣟⣼⣿⣿⣼⠿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠻⣿⣿⣿⣿⡛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠈⠛⢿⣿⣿⣽⣿⣿⣿⣿⣿⣿⡏⣸⢿⣿⣿⣿⣿⣿⣿⣿⣵⣶⣿⣿⣿⣿⣦⣾⣽⣷⣽⣷⣿⣿⡿⣷⣶⣤⡀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠨⢿⣿⣿⣿⣿⣿⣿⣯⣟⣧⢳⡌⠉⠻⢿⣿⣿⣿⣿⣿⣿⣿⣿⣟⣿⠏⠹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣯⣿⣦⠙⣦⣀⠀⠈⢉⠛⠻⡛⠛⠻⡑⠞⠉⢀⠄⣿⣿⣿⣿⣿⡿⠿⠿⠿⠛⠉⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣤⡙⢷⣦⣤⣀⣢⣤⣴⣯⣡⡤⠖⣁⣴⣿⣾⣿⣿⣿⣷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⣿⣿⣿⣿⣿⣿⣿⣷⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⣴⣿⣟⣿⣻⢟⣿⣿⣿⣿⡾⣽⣧⣿⣿⣿⣻⣷⣿⣿⠿⢿⡿⣯⢽⡲⣤⠂⠉⠛⣷⣝⡲⠛⠻⣬⡑⢤⡀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⢠⣾⣿⣵⡿⣮⣽⣿⣿⣛⠛⠩⡤⠹⢿⣿⣿⣛⣿⣳⣎⢯⣿⡠⣌⠤⣀⠁⣔⢈⡄⣆⣈⠙⣿⣦⣷⣾⣿⣷⣼⣧⡀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⣰⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣥⣒⣴⣿⣿⣾⣿⣿⣿⣿⣿⣿⣽⣦⣟⣼⣳⣦⣶⣻⣶⢾⣻⣵⣿⣯⣿⣿⣿⣿⣿⡇⠀⠀⠀
⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣽⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀
⠀⠀⠀⠀⠀⠀⣻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡏⠀⠀⠀⠀
⡄⡄⠂⣤⠐⢠⠙⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⠀⠀⠀⠀⠀
⠀⠀⠐⡀⠒⢠⢂⡉⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠛⠁⡀⠀⠀⠀⠀⠀
⠀⠀⠀⠒⠧⢤⣦⣔⣦⣘⣿⣿⣿⣿⣿⣿⣿⣯⣯⣽⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢿⠻⣍⢏⡛⠿⠿⠿⢿⣿⣬⣽⣿⣮⣶⠄
⠀⠀⠀⠀⠀⠀⠀⠈⢉⡉⣿⡀⣹⣏⢙⣿⠛⠛⠛⠛⠛⠛⠛⠛⠛⠻⠛⠟⠻⠛⠛⠻⠙⠫⠍⠓⠈⠊⠁⠈⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
*/