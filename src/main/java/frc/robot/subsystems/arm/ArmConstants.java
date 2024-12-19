package frc.robot.subsystems.arm;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.math.controller.PIDController;

public class ArmConstants {
    static final TalonFX MOTOR = new TalonFX(7);
    static final CANcoder ENCODER = new CANcoder(34);

    private static final double P = 3;
    private static final double I = 0;
    private static final double D = 2;
    static final PIDController PID_CONTROLLER = new PIDController(P, I, D);

    public static final boolean FOC_ENABLED = true;

    static final StatusSignal<Double> ANGLE_CAN_CODER_POSITION_SIGNAL = ENCODER.getPosition();

    private static final InvertedValue INVERTED_VALUE = InvertedValue.CounterClockwise_Positive;
    private static final NeutralModeValue NEUTRAL_MODE_VALUE = NeutralModeValue.Brake;
    private static final double GEAR_RATIO = 2.5;

    static {
        TalonFXConfiguration config = new TalonFXConfiguration();

        config.Audio.BeepOnConfig = false;
        config.Audio.BeepOnBoot = false;
        config.MotorOutput.Inverted = INVERTED_VALUE;
        config.MotorOutput.NeutralMode = NEUTRAL_MODE_VALUE;
        config.Feedback.SensorToMechanismRatio = GEAR_RATIO;
        MOTOR.getConfigurator().apply(config);

        ANGLE_CAN_CODER_POSITION_SIGNAL.setUpdateFrequency(100);
        ENCODER.optimizeBusUtilization();
    }
}
