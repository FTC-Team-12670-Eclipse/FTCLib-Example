package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.AscendCommand;

@TeleOp
public class ExampleOpMode extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        RobotHardware robotHardware = RobotHardware.getInstance(hardwareMap);
        CommandScheduler commandScheduler = CommandScheduler.getInstance();
        GamepadEx gamepad = new GamepadEx(gamepad1);

        // Ascend command
        commandScheduler.schedule(
                new AscendCommand(robotHardware, gamepad)
        );

        // Print telemetry
        commandScheduler.schedule(
                new RunCommand(()->{
                    telemetry.addData("Seconds elapsed", (int) getRuntime());
                    telemetry.update();
                })
        );

        telemetry.addLine("Ready to go!");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            commandScheduler.run();
            gamepad.readButtons();
        }
    }
}
