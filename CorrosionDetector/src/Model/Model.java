package Model;

import org.opencv.imgproc.Imgproc;
import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.awt.image.DataBufferByte;
import java.io.File;

import org.bytedeco.javacpp.opencv_core.CvScalar;
import org.bytedeco.javacpp.opencv_core.IplImage;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
//static imports
import org.bytedeco.javacpp.opencv_highgui;
import org.bytedeco.javacpp.opencv_imgproc;
import org.bytedeco.javacpp.opencv_core;
import static org.bytedeco.javacpp.opencv_imgcodecs.cvLoadImage;


import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.OpenCVFrameConverter;

import static org.bytedeco.javacpp.flandmark.*;
import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgproc.*;
import static org.bytedeco.javacpp.opencv_objdetect.*;

import static org.bytedeco.javacpp.opencv_imgcodecs.*;









public class Model {
	
	   public String getData() throws FileNotFoundException, IOException {
	        
	        if(!(new File("F:\\file.txt").isFile())) {
	            // Create -- Make sure file exists -- the file before continuing
	            Files.createFile(Paths.get("F:\\file.txt"));
	        }
	        
	        String data;
	        // We will be using a try-with-resource block
	        try (BufferedReader reader = new BufferedReader(
	                new FileReader("F:\\file.txt"))) {
	            // Access the data from the file
	            // Create a new StringBuilder
	            StringBuilder string = new StringBuilder();
	            
	            // Read line-by-line
	            String line = reader.readLine();
	            string.append("<html>");
	            // While there comes a new line, execute this
	            while(line != null) {
	                // Add these lines to the String builder
	                string.append(line);
	                string.append("<br />");
	                // Read the next line
	                line = reader.readLine();
	            }
	            string.append("</html>");
	            data = string.toString();
	        } catch (Exception er) {
	            // Since there was an error, you probably want to notify the user
	            // For that error. So return the error.
	            data = er.getMessage();
	        }
	        // Return the string read from the file
	        return data;
	    }
	    
	    public void ChangeToGreyScale(String imagePathToEdit)
	    {
	     //OPENCV STUFF
	     System.out.println("hello");
	     
	     
	     //RED DETECTION
	     detectRed("colour-pic-swatch_2883168b.jpg");
	     
	     
	     //GREY SCALE WORKS!!
	     /*
	 
	     
	    
         try {
        	

       	     System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        	 File input = new File(imagePathToEdit);
    	    System.out.println(imagePathToEdit);
        	 System.out.println("1");
			 BufferedImage image = ImageIO.read(new File(imagePathToEdit));
				System.out.println("2");
			 byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
			
	         Mat mat = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
	         mat.put(0, 0, data);
	     
	         Mat mat1 = new Mat(image.getHeight(),image.getWidth(),CvType.CV_8UC1);
	         Imgproc.cvtColor(mat, mat1, Imgproc.COLOR_RGB2GRAY);
	     	System.out.println("3");
	         byte[] data1 = new byte[mat1.rows() * mat1.cols() * (int)(mat1.elemSize())];
	         mat1.get(0, 0, data1);
	         BufferedImage image1 = new BufferedImage(mat1.cols(),mat1.rows(), BufferedImage.TYPE_BYTE_GRAY);
	         image1.getRaster().setDataElements(0, 0, mat1.cols(), mat1.rows(), data1);
	     	System.out.println("4");
	         File ouptut = new File("grayscale.jpg");
	         ImageIO.write(image1, "jpg", ouptut);
	     	System.out.println("5");
	         
		} catch (Exception e) {
			System.out.println("ERROR WITH CORROSION DETECT");
		}	
       */
	}
	    
	    public void detectRed(String imagePathToEdit)
	    {
	    	 //color range of red like color
	        CvScalar min = opencv_core.cvScalar(0, 0, 130, 0);//BGR-A
	        CvScalar max= opencv_core.cvScalar(140, 110, 255, 0);//BGR-A
	        System.out.println(imagePathToEdit);
	        System.out.println(imagePathToEdit);
	        System.out.println(imagePathToEdit);
	        System.out.println(imagePathToEdit);
	            //read image
	            IplImage orgImg = cvLoadImage(imagePathToEdit);
	          
	       
	        
	            //create binary image of original size
	            IplImage imgThreshold = cvCreateImage(cvGetSize(orgImg), 8, 1);
	            
	            //apply thresholding
	            cvInRangeS(orgImg, min, max, imgThreshold);
	            //smooth filter- median
	            cvSmooth(imgThreshold, imgThreshold, CV_MEDIAN, 13, 0, 0, 0);
	            //save
	            cvSaveImage("threshold.jpg", imgThreshold);
	        
	            
	    }
	    
	    
	    
	    
	    //OPEN CV CLEAN UP
	  
	    
	    
	    
	   
	    
	    
	}