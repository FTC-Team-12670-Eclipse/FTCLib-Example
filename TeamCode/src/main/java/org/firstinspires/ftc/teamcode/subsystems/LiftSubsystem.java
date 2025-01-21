package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.command.WaitUntilCommand;

import org.firstinspires.ftc.teamcode.RobotHardware;

public class LiftSubsystem extends SubsystemBase {
    private static LiftSubsystem INSTANCE;
    private final RobotHardware robotHardware;

    public enum Height {
        ZERO(0),
        WALL(100),
        ASCENT(2000),
        FULL(3000);

        final int encoderTicks;

        Height(int encoderTicks) {
            this.encoderTicks = encoderTicks;
        }
    }

    private LiftSubsystem(RobotHardware robotHardware) {
        this.robotHardware = robotHardware;
    }

    public static LiftSubsystem getInstance(RobotHardware robotHardware) {
        if (INSTANCE == null) {
            INSTANCE = new LiftSubsystem(robotHardware);
        }
        return INSTANCE;
    }

    public Command moveAndWaitUntilReachCommand(Height height) {
        return new SequentialCommandGroup() {
            {
                addCommands(
                        new InstantCommand(() -> {
                            robotHardware.liftMotor.setTargetPosition(height.encoderTicks);
                        }),
                        new WaitUntilCommand(() -> !robotHardware.liftMotor.isBusy())
                );
                addRequirements(INSTANCE);
            }
        };
    }
}
