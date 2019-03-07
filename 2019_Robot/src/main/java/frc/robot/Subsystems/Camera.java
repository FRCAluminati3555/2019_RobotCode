package frc.robot.Subsystems;

import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;

public class Camera{
    public Camera(){
        
    }
    public void Camera1(){
		new Thread(() -> {
			UsbCamera camera1 = CameraServer.getInstance().startAutomaticCapture(0);
			CvSink cvSink1 = CameraServer.getInstance().getVideo(camera1);
			CvSource outputStream1 = CameraServer.getInstance().putVideo("Grayscale1", 160, 120);

			Mat source1 = new Mat();
			Mat output1 = new Mat();

			while(!Thread.interrupted()) {			
				long rv1 = cvSink1.grabFrame(source1);

				if(rv1 != 0){
					output1.setTo(new Scalar(1));
					Imgproc.cvtColor(source1, output1, Imgproc.COLOR_RGB2GRAY);
					outputStream1.putFrame(output1); 								
				}
			}
		}).start();
	}

    public void Camera2(){
		new Thread(() -> {
			UsbCamera camera2 = CameraServer.getInstance().startAutomaticCapture(1);
			CvSink cvSink1 = CameraServer.getInstance().getVideo(camera2);
			CvSource outputStream1 = CameraServer.getInstance().putVideo("Grayscale2", 160, 120);

			Mat source2 = new Mat();
			Mat output2 = new Mat();

			while(!Thread.interrupted()) {			
				long rv2 = cvSink1.grabFrame(source2);

				if(rv2 != 0){
					output2.setTo(new Scalar(1));
					Imgproc.cvtColor(source2, output2, Imgproc.COLOR_RGB2GRAY);
					outputStream1.putFrame(output2); 								
				}
			}
		}).start();
	}
}