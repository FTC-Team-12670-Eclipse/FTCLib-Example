package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.HookSubsystem;

public class RobotHardware {
    // Singleton instance of RobotHardware
    private static RobotHardware INSTANCE;

    // Hardware components are publicly accessible
    public final DcMotorEx liftMotor;
    public final Servo hookServo;

    // Subsystems are publicly accessible
    public final LiftSubsystem liftSubsystem;
    public final HookSubsystem hookSubsystem;

    private RobotHardware(HardwareMap hMap) {
        // Hardware mapping
        liftMotor = hMap.get(DcMotorEx.class, "lift-motor-name");
        hookServo = hMap.get(Servo.class, "hook-servo-name");

        // Instantiate subsystems
        liftSubsystem = LiftSubsystem.getInstance(this);
        hookSubsystem = HookSubsystem.getInstance(this);
    }

    public static RobotHardware getInstance(HardwareMap hMap) {
        if (INSTANCE == null) {
            INSTANCE = new RobotHardware(hMap);
        }
        return INSTANCE;
    }
}
