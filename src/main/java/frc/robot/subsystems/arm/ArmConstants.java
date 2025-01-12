package frc.robot.subsystems.arm;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.MagnetSensorConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.signals.SensorDirectionValue;
import edu.wpi.first.math.controller.PIDController;

public class ArmConstants {
    private static final int MOTOR_ID = 5;
    static final TalonFX MOTOR = new TalonFX(MOTOR_ID);
    private static final int ENCODER_ID = 34;
    private static final CANcoder ENCODER = new CANcoder(ENCODER_ID);
    static final StatusSignal<Double> ANGLE_ENCODER_POSITION_SIGNAL = ENCODER.getPosition();
    private static final double
            P = 3,
            I = 0,
            D = 2;
    static final PIDController PID_CONTROLLER = new PIDController(P, I, D);

    private static final SensorDirectionValue SENSOR_DIRECTION_VALUE = SensorDirectionValue.CounterClockwise_Positive;
    private static final InvertedValue INVERTED_VALUE = InvertedValue.CounterClockwise_Positive;
    private static final NeutralModeValue NEUTRAL_MODE_VALUE = NeutralModeValue.Brake;
    private static final double GEAR_RATIO = 2.5;
    public static final boolean FOC_ENABLED = true;

    static {
        configureMotor();
        configureEncoder();
    }
    public static void configureMotor() {
        TalonFXConfiguration config = new TalonFXConfiguration();
        config.Audio.BeepOnConfig = false;
        config.Audio.BeepOnBoot = false;
        config.MotorOutput.Inverted = INVERTED_VALUE;
        config.MotorOutput.NeutralMode = NEUTRAL_MODE_VALUE;
        config.Feedback.SensorToMechanismRatio = GEAR_RATIO;
        MOTOR.getConfigurator().apply(config);
        MOTOR.optimizeBusUtilization();
    }
    public static void configureEncoder() {
        CANcoderConfiguration config = new CANcoderConfiguration();
        config.withMagnetSensor(new MagnetSensorConfigs().withSensorDirection(SENSOR_DIRECTION_VALUE));
        ENCODER.getConfigurator().apply(config);
        ENCODER.optimizeBusUtilization();
        ANGLE_ENCODER_POSITION_SIGNAL.setUpdateFrequency(100);
    }
}