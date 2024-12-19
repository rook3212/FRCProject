package frc.robot.subsystems.arm;


import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import frc.robot.RobotContainer;

public class ArmCommands {
    public static Command getSetTargetAngleCommand(double angle) {
        return new FunctionalCommand(
                () -> RobotContainer.ARM.getToTargetedAngle(angle),
                () -> RobotContainer.ARM.getToTargetedAngle(angle),
                (interrupted) -> RobotContainer.ARM.stop(),
                () -> ArmConstants.isFinished,
                RobotContainer.ARM
        );
    }
}
