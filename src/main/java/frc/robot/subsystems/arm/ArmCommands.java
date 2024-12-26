package frc.robot.subsystems.arm;


import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import frc.robot.RobotContainer;

public class ArmCommands {
    public static Command getSetTargetAngleCommand(Rotation2d angle) {
        return new FunctionalCommand(
                () -> RobotContainer.ARM.setTargetAngle(angle),
                () -> RobotContainer.ARM.setTargetAngle(angle),
                (interrupted) -> RobotContainer.ARM.stop(),
                () -> false,
                RobotContainer.ARM
        );
    }
}
