package frc.robot.subsystems.arm;

import com.ctre.phoenix6.controls.VoltageOut;
import edu.wpi.first.math.MathUtil;

public class Arm {
    private final VoltageOut voltageRequest = new VoltageOut(0).withEnableFOC(ArmConstants.FOC_ENABLED);

    public static double getAngleCANCoderPositionSignal() {
        return ArmConstants.ANGLE_CAN_CODER_POSITION_SIGNAL.refresh().getValue();
    }

    public void setTargetVoltage(double voltage) {
        ArmConstants.MOTOR.setControl(voltageRequest.withOutput(voltage));
    }

    public Arm() {

    }

    public void clampControllerOutput(double setpoint) {
        MathUtil.clamp(ArmConstants.PID_CONTROLLER.calculate(getAngleCANCoderPositionSignal(), setpoint), -0.5, 0.5);
    }

    public void move(double setPoint) {
        setTargetVoltage(2.5);
        clampControllerOutput(setPoint);
    }
}
