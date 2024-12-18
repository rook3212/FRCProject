package frc.robot.subsystems.arm;


import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.RobotContainer;

public class ArmCommands {
    public static Command getSetTargetAngleCommand() {
        return new InstantCommand(
                () -> RobotContainer.ARM.up(),
                (Subsystem) RobotContainer.ARM
        );
    }

}
