package jp.jaxa.iss.kibo.rpc.sampleapk;

import jp.jaxa.iss.kibo.rpc.api.KiboRpcService;

import java.util.List;
import gov.nasa.arc.astrobee.Result;
import gov.nasa.arc.astrobee.android.gs.MessageType;
import gov.nasa.arc.astrobee.types.Point;
import gov.nasa.arc.astrobee.types.Quaternion;

import org.opencv.core.Mat;

/**
 * Class meant to handle commands from the Ground Data System and execute them in Astrobee
 */

public class YourService extends KiboRpcService {
    @Override
    protected void runPlan1(){
        // the mission starts
        api.startMission();
        int loop_counter = 0;

        while (true){
            // get the list of active target id
            List<Integer> list = api.getActiveTargets();

            //initialization
            Point point1 = new Point(11.2746d, -9.92284d, 5.2988d);
            Quaternion quaternion_p1 = new Quaternion(0f, 0f, -0.707f, 0.707f);

            Point point2 = new Point(10.612d, -9.0709d, 4.48d);
            Quaternion quaternion_p2 = new Quaternion(0.5f, 0.5f, -0.5f, 0.5f);

            Point point3 = new Point(10.71d, -7.7d, 4.48d);
            Quaternion quaternion_p3 = new Quaternion(0f, 0.707f, 0f, 0.707f);

            Point point4 = new Point(10.51d, -6.7185d, 5.1804d);
            Quaternion quaternion_p4 = new Quaternion(0f, 0f, -1f, 1f);

            Point point5 = new Point(11.114d, -7.9756d, 5.3393d);
            Quaternion quaternion_p5 = new Quaternion(-0.5f, -0.5f, -0.5f, 0.5f);

            Point point6 = new Point(11.355d, -8.9929d, 4.7818d);
            Quaternion quaternion_p6 = new Quaternion(0f, 0f, 0f, 1f);

            Point point7 = new Point(11.369d, -8.5518d, 4.48d);
            Quaternion quaternion_p7 = new Quaternion(0f, 0.707f, 0f, 0.707f);

            Point target1 = new Point(10.4d, -10.1d, 4.47d);
            Quaternion quaternion_t1 = new Quaternion(0f, 0f, 0f, 1f);

            Point target2 = new Point(10.4d, -10.1d, 4.47d);
            Quaternion quaternion_t2 = new Quaternion(0f, 0f, 0f, 1f);

            Point target3 = new Point(10.4d, -10.1d, 4.47d);
            Quaternion quaternion_t3 = new Quaternion(0f, 0f, 0f, 1f);

            Point target4 = new Point(10.4d, -10.1d, 4.47d);
            Quaternion quaternion_t4 = new Quaternion(0f, 0f, 0f, 1f);

            Point target5 = new Point(10.4d, -10.1d, 4.47d);
            Quaternion quaternion_t5 = new Quaternion(0f, 0f, 0f, 1f);

            Point target6 = new Point(10.4d, -10.1d, 4.47d);
            Quaternion quaternion_t6 = new Quaternion(0f, 0f, 0f, 1f);


            // move to a point
            Point point = new Point(10.4d, -10.1d, 4.47d);
            Quaternion quaternion = new Quaternion(0f, 0f, 0f, 1f);
            api.moveTo(point, quaternion, false);

            // get a camera image
            Mat image = api.getMatNavCam();

            // irradiate the laser
            api.laserControl(true);

            // take active target snapshots
            int target_id = 1;
            api.takeTargetSnapshot(target_id);

            /* ************************************************ */
            /* write your own code and repair the ammonia leak! */
            /* ************************************************ */

            // get remaining active time and mission time
            List<Long> timeRemaining = api.getTimeRemaining();

            // check the remaining milliseconds of mission time
            if (timeRemaining.get(1) < 60000){
                break;
            }

            loop_counter++;
            if (loop_counter == 2){
                break;
            }
        }
        // turn on the front flash light
        api.flashlightControlFront(0.05f);
        
        // get QR code content
        String mQrContent = yourMethod();

        // turn off the front flash light
        api.flashlightControlFront(0.00f);

        // notify that astrobee is heading to the goal
        api.notifyGoingToGoal();

        /* ********************************************************** */
        /* write your own code to move Astrobee to the goal positiion */
        /* ********************************************************** */

        // send mission completion
        api.reportMissionCompletion(mQrContent);
    }

    @Override
    protected void runPlan2(){
       // write your plan 2 here
    }

    @Override
    protected void runPlan3(){
        // write your plan 3 here
    }

    // You can add your method
    private String yourMethod(){
        return "your method";
    }
}
