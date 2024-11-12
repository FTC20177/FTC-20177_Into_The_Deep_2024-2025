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
package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp

public class Drive_Old extends LinearOpMode {

    //private Blinker control_Hub;
    private DcMotor backleftMotor;
    private DcMotor backrightMotor;
    private DcMotor frontleftMotor;
    private DcMotor frontrightMotor;
    private DcMotor Slide;
    private DcMotor Pivot;
    private CRServo Intake;
    private CRServo Bucket;

    double tgtPower = 0;
    double tgtPower_piv = 0.5;

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
        Bucket = hardwareMap.get(CRServo.class, "Bucket");



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


        while (opModeIsActive()) {
            telemetry.addData("Status", "Running");

            frontleftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            frontrightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            backleftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            backrightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


            telemetry.update();

            //setMotorPower
            double r = Math.hypot(-gamepad1.left_stick_x, gamepad1.left_stick_y);
            double robotAngle = Math.atan2(-gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
            //these had 'final' before them at one point "final double v1 = r * Math.cos(robotangle) + rightx"
            //-Team 15036


            double rightx = gamepad1.right_stick_x * .5;
            double v1 = (r * Math.cos(robotAngle)) * 1 - rightx;
            double v2 = (r * Math.sin(robotAngle)) * 1 + rightx;
            double v3 = (r * Math.sin(robotAngle)) * 1 - rightx;
            double v4 = (r * Math.cos(robotAngle)) * 1 + rightx;
            frontleftMotor.setPower(v1);
            frontrightMotor.setPower(-v2);
            backleftMotor.setPower(v3);
            backrightMotor.setPower(-v4);


            tgtPower = this.gamepad1.left_trigger;
            Slide.setPower(-tgtPower);
            tgtPower = this.gamepad1.right_trigger;
            Slide.setPower(tgtPower);

            if (gamepad1.right_bumper) {
                Pivot.setPower(-tgtPower_piv);
            } else if (gamepad1.left_bumper) {
                Pivot.setPower(tgtPower_piv);
            }else{
                Pivot.setPower(0);
            }

            if (gamepad1.x) {
                Intake.setPower(1);
            } else if (gamepad1.y) {
                Intake.setPower(-1);
            }else{
                Intake.setPower(0);
            }

            if (gamepad1.dpad_up) {
                Bucket.setPower(0.5);
            } else if (gamepad1.dpad_down) {
                Bucket.setPower(-0.5);
            }else{
                Bucket.setPower(0);
            }


            if (isStopRequested()){
                frontleftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                frontrightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                backleftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                backrightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                sleep(1000);
            }

        }
    }
}
