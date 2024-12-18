package frc.robot.subsystems.arm;

import com.ctre.phoenix6.controls.VoltageOut;

public class Arm {


    public static double getAngleCANCoderPositionSignal() {
        return ArmConstants.ANGLE_CAN_CODER_POSITION_SIGNAL.refresh().getValue();
    }
    public void FeedbackLoopOutput(double setPoint) {
        ArmConstants.MOTOR.set(ArmConstants.PID_CONTROLLER.calculate(getAngleCANCoderPositionSignal(), setPoint));
    }
    private final VoltageOut voltageRequest = new VoltageOut(0).withEnableFOC(ArmConstants.FOC_ENABLED);

    public void setTargetVoltage(double voltage) {
        ArmConstants.MOTOR.setControl(voltageRequest.withOutput(voltage));
    }

    public Arm() {

    }

    public void up() {
       setTargetVoltage(2.5); FeedbackLoopOutput(98);
    }




}
