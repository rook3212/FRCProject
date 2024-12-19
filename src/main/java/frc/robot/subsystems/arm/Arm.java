package frc.robot.subsystems.arm;

import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {
    private final VoltageOut voltageRequest = new VoltageOut(0).withEnableFOC(ArmConstants.FOC_ENABLED);

    private final TalonFX MOTOR = ArmConstants.MOTOR;

    public Arm() {
    }

    public void moveToTargetedAngle(double targetedAngle) {
        double targetVoltage = ArmConstants.PID_CONTROLLER.calculate(GET_ANGLE_CANCODER_POSITION(), targetedAngle);
        setTargetVoltage(targetVoltage);
    }

    public void stop() {
        MOTOR.stopMotor();
    }

    private static double GET_ANGLE_CANCODER_POSITION() {
        return ArmConstants.ANGLE_CAN_CODER_POSITION_SIGNAL.refresh().getValue();
    }

    private void setTargetVoltage(double voltage) {
        MOTOR.setControl(voltageRequest.withOutput(voltage));
    }
}
