package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.RobotHardware;

public class HookSubsystem extends SubsystemBase {
    private static HookSubsystem INSTANCE;
    private final RobotHardware robotHardware;

    public enum Position {
        HOLD(0),
        RELEASE(1);

        final double hookServoPosition;

        Position(double hookServoPosition) {
            this.hookServoPosition = hookServoPosition;
        }
    }

    private HookSubsystem(RobotHardware robotHardware) {
        this.robotHardware = robotHardware;
    }

    public static HookSubsystem getInstance(RobotHardware robotHardware) {
        if (INSTANCE == null) {
            INSTANCE = new HookSubsystem(robotHardware);
        }
        return INSTANCE;
    }

    public Command moveAndWaitUntilReachCommand(Position position) {
        return new SequentialCommandGroup() {
            {
                // Rotate hook servo to position
                addCommands(new InstantCommand(() -> {
                    robotHardware.hookServo.setPosition(position.hookServoPosition);
                }));

                // Wait for 100ms
                addCommands(new WaitCommand(100));

                addRequirements(INSTANCE);
            }
        };
    }
}
