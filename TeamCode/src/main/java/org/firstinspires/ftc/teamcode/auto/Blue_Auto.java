/* Copyright (c) 2019 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

double equation = 30/26;

@Autonomous(name = "Blue_Auto", group = "Concept")
public class Blue_Auto extends LinearOpMode {
    private Blinker control_Hub;
    private DcMotorEx backleftMotor;
    private DcMotorEx backrightMotor;
    private DcMotorEx frontleftMotor;
    private DcMotorEx frontrightMotor;

    private DcMotorEx Lift_Motor_1;

    @Override
    public void runOpMode() {


        //hardware map
        control_Hub = hardwareMap.get(Blinker.class, "Control Hub");
        backleftMotor = hardwareMap.get(DcMotorEx.class, "backleftMotor");
        backrightMotor = hardwareMap.get(DcMotorEx.class, "backrightMotor");
        frontleftMotor = hardwareMap.get(DcMotorEx.class, "frontleftMotor");
        frontrightMotor = hardwareMap.get(DcMotorEx.class, "frontrightMotor");
        Lift_Motor_1 = hardwareMap.get(DcMotorEx.class, "Lift_Motor_1");

        while (!opModeIsActive() && !isStopRequested()) {
            // Wait for the DS start button to be touched.
            telemetry.addData("DS preview on/off", "3 dots, Camera Stream");
            telemetry.addData(">", "Touch Play to start OpMode");
            telemetry.update();

        }


        if (opModeIsActive()) {
            while (opModeIsActive()) {
                telemetry.addData("Status", "Running");
                telemetry.addData("Encoder", Lift_Motor_1.getCurrentPosition());
                telemetry.update();

                frontleftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                frontrightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                backleftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                backrightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                Lift_Motor_1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                frontleftMotor.setTargetPosition(0);
                frontrightMotor.setTargetPosition(0);
                backleftMotor.setTargetPosition(0);
                backrightMotor.setTargetPosition(0);

                Lift_Motor_1.setTargetPosition(0);

                frontleftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                frontrightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                backleftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                backrightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                Lift_Motor_1.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            }   // end for() loop

        }

    }// end of code

    void forward(double distance, double power ){

        frontleftMotor.setTargetPosition(frontleftMotor.getTargetPosition()-(int)(distance*(537.7/12.1211)*(30/26)));
        backleftMotor.setTargetPosition(backleftMotor.getTargetPosition()-(int)(distance*(537.7/12.1211)*(30/26)));
        frontrightMotor.setTargetPosition(frontrightMotor.getTargetPosition()+(int)(distance*(537.7/12.1211)*(30/26)));
        backrightMotor.setTargetPosition(backrightMotor.getTargetPosition()+(int)(distance*(537.7/12.1211)*(30/26)));
        frontleftMotor.setPower(power);
        frontrightMotor.setPower(power);
        backleftMotor.setPower(power);
        backrightMotor.setPower(power);
        while(frontleftMotor.isBusy() && backleftMotor.isBusy() && frontrightMotor.isBusy() && backrightMotor.isBusy()){
            updatedTelemetry();
        }
        sleep(1000);
    }
    void backwards(double distance, double power ){

        frontleftMotor.setTargetPosition(frontleftMotor.getTargetPosition()+(int)(distance*(537.7/12.1211)*(30/26)));
        backleftMotor.setTargetPosition(backleftMotor.getTargetPosition()+(int)(distance*(537.7/12.1211)*(30/26)));
        frontrightMotor.setTargetPosition(frontrightMotor.getTargetPosition()-(int)(distance*(537.7/12.1211)*(30/26)));
        backrightMotor.setTargetPosition(backrightMotor.getTargetPosition()-(int)(distance*(537.7/12.1211)*(30/26)));
        frontleftMotor.setPower(power);
        frontrightMotor.setPower(power);
        backleftMotor.setPower(power);
        backrightMotor.setPower(power);
        while(frontleftMotor.isBusy() && backleftMotor.isBusy() && frontrightMotor.isBusy() && backrightMotor.isBusy()){
            updatedTelemetry();
        }
        sleep(1000);
    }
    void left(double distance, double power ){

        frontleftMotor.setTargetPosition(frontleftMotor.getTargetPosition()+(int)((distance*(537.7/12.1211)*(30/26))));
        backleftMotor.setTargetPosition(backleftMotor.getTargetPosition()-(int)((distance*(537.7/12.1211)*(30/26))));
        frontrightMotor.setTargetPosition(frontrightMotor.getTargetPosition()+(int)((distance*(537.7/12.1211)*(30/26))));
        backrightMotor.setTargetPosition(backrightMotor.getTargetPosition()-(int)((distance*(537.7/12.1211)*(30/26))));
        frontleftMotor.setPower(power);
        frontrightMotor.setPower(power);
        backleftMotor.setPower(power);
        backrightMotor.setPower(power);
        while(frontleftMotor.isBusy() && backleftMotor.isBusy() && frontrightMotor.isBusy() && backrightMotor.isBusy()){
            updatedTelemetry();
        }
        sleep(2000);
    }

    void right(double distance, double power ){

        frontleftMotor.setTargetPosition(frontleftMotor.getTargetPosition()-(int)((distance*(537.7/12.1211)*(30/26))));
        backleftMotor.setTargetPosition(backleftMotor.getTargetPosition()+(int)((distance*(537.7/12.1211)*(30/26))));
        frontrightMotor.setTargetPosition(frontrightMotor.getTargetPosition()-(int)((distance*(537.7/12.1211)*(30/26))));
        backrightMotor.setTargetPosition(backrightMotor.getTargetPosition()+(int)((distance*(537.7/12.1211)*(30/26))));
        frontleftMotor.setPower(power);
        frontrightMotor.setPower(power);
        backleftMotor.setPower(power);
        backrightMotor.setPower(power);
        while(frontleftMotor.isBusy() && backleftMotor.isBusy() && frontrightMotor.isBusy() && backrightMotor.isBusy()){
            updatedTelemetry();
            telemetry.update();
        }
        sleep(2000);

    }
    void updatedTelemetry(){
        telemetry.addData("Status", "Running");
        telemetry.addData("FL", frontleftMotor.getCurrentPosition());
        telemetry.addData("FR", frontrightMotor.getCurrentPosition());
        telemetry.addData("BL", backleftMotor.getCurrentPosition());
        telemetry.addData("BR", backrightMotor.getCurrentPosition());
        telemetry.update();
    }


}





