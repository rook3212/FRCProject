package frc.robot.subsystems.arm;

import com.ctre.phoenix6.controls.VoltageOut;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {
    private final VoltageOut voltageRequest = new VoltageOut(0).withEnableFOC(ArmConstants.FOC_ENABLED);


    public static double getAngleCANCoderPositionSignal() {
        return ArmConstants.ANGLE_CAN_CODER_POSITION_SIGNAL.refresh().getValue();
    }


    public void setTargetVoltage(double voltage) {
        ArmConstants.MOTOR.setControl(voltageRequest.withOutput(voltage));
    }

    public Arm() {
    }


    public void move(double setPoint) {
      double voltageNeed = ArmConstants.PID_CONTROLLER.calculate(getAngleCANCoderPositionSignal(),setPoint);
      setTargetVoltage(voltageNeed);
    }
}
