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
			UsbCamera camera = CameraServer.getInstance().startAutomaticCapture(0);
			CvSink cvSink = CameraServer.getInstance().getVideo(camera);
			CvSource outputStream = CameraServer.getInstance().putVideo("Camera 1", 160, 120);
			
			Mat source = new Mat();
			Mat output = new Mat();
			
			while(!Thread.interrupted()) {
				long rv = cvSink.grabFrame(source);
			
				if(rv != 0) {
					output.setTo(Scalar.all(0));
					Imgproc.Canny(source, output, 100, 300, 3, true);
					outputStream.putFrame(output);
				}
			}
		}).start();
	}

    public void Camera2(){
		new Thread(() -> {
			UsbCamera camera1 = CameraServer.getInstance().startAutomaticCapture(1);
			CvSink cvSink1 = CameraServer.getInstance().getVideo(camera1);
			CvSource outputStream1 = CameraServer.getInstance().putVideo("Camera 2", 160, 120);

			Mat source1 = new Mat();
			Mat output1 = new Mat();

			while(!Thread.interrupted()) {			
				long rv1 = cvSink1.grabFrame(source1);
				
				if(rv1 != 0){
					output1.setTo(Scalar.all(0));
					Imgproc.Canny(source1, output1, 100, 300, 3, false); 
					outputStream1.putFrame(output1);
								
				}
			}
		}).start();
	}
}