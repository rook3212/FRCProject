package frc.robot.subsystems.arm;

import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {
    private final VoltageOut voltageRequest = new VoltageOut(0).withEnableFOC(ArmConstants.FOC_ENABLED);
    private final TalonFX motor = ArmConstants.MOTOR;

    public Arm() {
    }

    public void setTargetAngle(Rotation2d targetAngle) {
        double pidOutput = ArmConstants.PID_CONTROLLER.calculate(GET_ANGLE_CANCODER_POSITION().getDegrees(), targetAngle.getDegrees());
        setTargetVoltage(pidOutput);
    }

    public void stop() {
        motor.stopMotor();
    }

    private static Rotation2d GET_ANGLE_CANCODER_POSITION() {
        return Rotation2d.fromRotations(ArmConstants.ANGLE_ENCODER_POSITION_SIGNAL.refresh().getValue());
    }

    private void setTargetVoltage(double voltage) {
        motor.setControl(voltageRequest.withOutput(voltage));
    }
}
