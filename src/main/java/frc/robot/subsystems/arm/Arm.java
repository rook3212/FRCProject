package frc.robot.subsystems.arm;

import com.ctre.phoenix6.controls.VoltageOut;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {
    private final VoltageOut voltageRequest = new VoltageOut(0).withEnableFOC(ArmConstants.FOC_ENABLED);

    public Arm() {
    }

    public void getToTargetedAngle(double targetedAngle) {
        double targetVoltage = ArmConstants.PID_CONTROLLER.calculate(getAngleCANCoderPositionSignal(), targetedAngle);
        setTargetVoltage(targetVoltage);
    }

    public void stop(){
        ArmConstants.MOTOR.disable();
    }
    private static double getAngleCANCoderPositionSignal() {
        return ArmConstants.ANGLE_CAN_CODER_POSITION_SIGNAL.refresh().getValue();
    }

    private void setTargetVoltage(double voltage) {
        ArmConstants.MOTOR.setControl(voltageRequest.withOutput(voltage));
    }
}
