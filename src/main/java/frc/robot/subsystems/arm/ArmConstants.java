package frc.robot.subsystems.arm;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;

public class ArmConstants {
    private static final int motorId = 13;
    static final TalonFX MOTOR = new TalonFX(motorId);
    private static final int canCoderId = 12;
    static final CANcoder ENCODER = new CANcoder(canCoderId);
    private static double P;
    private static double I;
    private static double D;
    static final PIDController PID_CONTROLLER = new PIDController(P, I, D);
    public static final boolean FOC_ENABLED = true;
     public  void setPID(double P, double I, double D){
        this.P=P;
        this.I=I;
        this.D=D;
    }

    static final StatusSignal<Double> ANGLE_CAN_CODER_POSITION_SIGNAL = ENCODER.getPosition();
    private static final InvertedValue INVERTED_VALUE = InvertedValue.CounterClockwise_Positive;
    private static final NeutralModeValue NEUTRAL_MODE_VALUE = NeutralModeValue.Brake;
    private static final double GEAR_RATIO = 2.5;

    static {
        TalonFXConfiguration config = new TalonFXConfiguration();
        PID_CONTROLLER.setIZone(Double.POSITIVE_INFINITY);
        config.Audio.BeepOnConfig = false;
        config.Audio.BeepOnBoot = false;
        config.MotorOutput.Inverted = INVERTED_VALUE;
        config.MotorOutput.NeutralMode = NEUTRAL_MODE_VALUE;
        config.Feedback.SensorToMechanismRatio = GEAR_RATIO;
        ANGLE_CAN_CODER_POSITION_SIGNAL.setUpdateFrequency(100);
        MOTOR.getConfigurator().apply(config);
        ENCODER.optimizeBusUtilization();
    }

}
