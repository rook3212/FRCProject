package frc.robot.subsystems.arm;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.math.controller.PIDController;

public class ArmConstants {
    private static final int MOTOR_ID = 5;
    public static final TalonFX MOTOR = new TalonFX(MOTOR_ID);

    private static final int CANCODER_ID = 34;
    public static final CANcoder ENCODER = new CANcoder(CANCODER_ID);

    private static final double P = 3;
    private static final double I = 0;
    private static final double D = 2;
    protected static final PIDController PID_CONTROLLER = new PIDController(P, I, D);

    public static final boolean FOC_ENABLED = true;

    protected static final StatusSignal<Double> ANGLE_ENCODER_POSITION_SIGNAL = ENCODER.getPosition();

    private static final InvertedValue INVERTED_VALUE = InvertedValue.CounterClockwise_Positive;

    private static final NeutralModeValue NEUTRAL_MODE_VALUE = NeutralModeValue.Brake;

    private static final double GEAR_RATIO = 2.5;
    
    private static void SET_MOTOR_CONFIGURATION() {
        TalonFXConfiguration TALON_CONFIG = new TalonFXConfiguration();
        TALON_CONFIG.Audio.BeepOnConfig = false;
        TALON_CONFIG.Audio.BeepOnBoot = false;
        TALON_CONFIG.MotorOutput.Inverted = INVERTED_VALUE;
        TALON_CONFIG.MotorOutput.NeutralMode = NEUTRAL_MODE_VALUE;
        TALON_CONFIG.Feedback.SensorToMechanismRatio = GEAR_RATIO;
        MOTOR.getConfigurator().apply(TALON_CONFIG);
        MOTOR.optimizeBusUtilization();
    }

    private static void SET_ENCODER_CONFIGURATION() {
        ANGLE_ENCODER_POSITION_SIGNAL.setUpdateFrequency(100);
        ENCODER.getPositionSinceBoot();
        ENCODER.optimizeBusUtilization();
    }
}
