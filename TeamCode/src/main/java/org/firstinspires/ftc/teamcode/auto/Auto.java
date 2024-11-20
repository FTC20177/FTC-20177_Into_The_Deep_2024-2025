/*
Copyright 2022 FIRST Tech Challenge Team 20177

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
associated documentation files (the "Software"), to deal in the Software without restriction,
including without limitation the rights to use, copy, modify, merge, publish, distribute,
sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial
portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.Presets;

@Autonomous

public class Auto extends LinearOpMode {

    //private Blinker control_Hub;
    private DcMotor backleftMotor;
    private DcMotor backrightMotor;
    private DcMotor frontleftMotor;
    private DcMotor frontrightMotor;
    private DcMotor Slide;
    private DcMotor Pivot;
    private CRServo Intake;
    private Servo Bucket;

    double tgtPower = 0;

    int kPivStartingPosition = Presets.kPivStartingPosition;
    int kPivMidPosition = Presets.kPivMidPosition;
    int kPivLowPosition = Presets.kPivLowPosition;
    int kPivEndPosition = Presets.kPivEndPosition;

    @Override
    public void runOpMode() {

        //control_Hub = hardwareMap.get(Blinker.class, "Control Hub");
        backleftMotor = hardwareMap.get(DcMotor.class, "backleftMotor");
        backrightMotor = hardwareMap.get(DcMotor.class, "backrightMotor");
        frontleftMotor = hardwareMap.get(DcMotor.class, "frontleftMotor");
        frontrightMotor = hardwareMap.get(DcMotor.class, "frontrightMotor");
        Slide = hardwareMap.get(DcMotor.class, "Slide");
        Pivot = hardwareMap.get(DcMotor.class, "Pivot");
        Intake = hardwareMap.get(CRServo.class, "Intake");
        Bucket = hardwareMap.get(Servo.class, "Bucket");



        telemetry.addData("Status", "Initialized");
        telemetry.update();


        frontleftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontrightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backleftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backrightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        // Wait for the game to start (driver presses PLAY)
        //claw.setPosition(0);
        waitForStart();
        // run until the end of the match (driver presses STOP)
        Pivot.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Pivot.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Pivot.setTargetPosition(0);

        Slide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        while (opModeIsActive()) {
            Pivot.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            telemetry.addData("Status", "Running");
            telemetry.addData("Piv_Pos", Pivot.getCurrentPosition());
            telemetry.addData("tgtPower", tgtPower);
            //telemetry.addData("Slide_Pos", Slide.getCurrentPosition());

            frontleftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            frontrightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            backleftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            backrightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


            telemetry.update();

            frontleftMotor.setPower(-0.25);
            frontrightMotor.setPower(0.25);
            backleftMotor.setPower(-0.25);
            backrightMotor.setPower(0.25);

            sleep(1000);

            frontleftMotor.setPower(0);
            frontrightMotor.setPower(0);
            backleftMotor.setPower(0);
            backrightMotor.setPower(0);

            stop();
        }
    }
}
