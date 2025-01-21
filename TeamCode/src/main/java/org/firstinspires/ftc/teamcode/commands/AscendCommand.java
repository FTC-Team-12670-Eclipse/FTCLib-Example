package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitUntilCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.RobotHardware;
import org.firstinspires.ftc.teamcode.subsystems.HookSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystem;

public class AscendCommand extends SequentialCommandGroup {
    public AscendCommand(RobotHardware robotHardware, GamepadEx gamepad) {
        LiftSubsystem liftSubsystem = robotHardware.liftSubsystem;
        HookSubsystem hookSubsystem = robotHardware.hookSubsystem;

        // 1. Move lift to ascent height and release hooks in parallel
        addCommands(
                new ParallelCommandGroup(
                        liftSubsystem.moveAndWaitUntilReachCommand(LiftSubsystem.Height.ASCENT),
                        hookSubsystem.moveAndWaitUntilReachCommand(HookSubsystem.Position.RELEASE)
                )
        );

        // 2. Wait for driver to press right bumper
        addCommands(new WaitUntilCommand(() -> gamepad.wasJustPressed(GamepadKeys.Button.RIGHT_BUMPER)));

        // 3. Move lift down
        addCommands(liftSubsystem.moveAndWaitUntilReachCommand(LiftSubsystem.Height.ZERO));
    }
}
