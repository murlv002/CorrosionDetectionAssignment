package Model;

import org.opencv.imgproc.Imgproc;
import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;

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
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.OpenCVFrameConverter;

import static org.bytedeco.javacpp.flandmark.*;
import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgproc.*;
import static org.bytedeco.javacpp.opencv_objdetect.*;

import static org.bytedeco.javacpp.opencv_imgcodecs.*;







public class Model {
	
	private String dataBaseLocation = "C:\\";
	String str;
	String savePrefix;
	double rustPercentage;
	String databaseDirectory = "";
	boolean directoryChosen = false;
	double deepRustPercentage;
	String rustStatus;
	
	
	
	
	public void setDataBaseLocation(String s)
	{
		this.dataBaseLocation = s;
	}
	
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
	    
	   //just a normal substring definition.  (removes prefix of image file up to / or \)
	    public void cutString(String imagePathToEdit)
	    {
	    	str=imagePathToEdit;
	    	  
	    	  if(str.lastIndexOf('\\') > str.lastIndexOf('/'))
	    	  {
	    	    int index=str.lastIndexOf('\\');
	    	    int dotIndex = str.lastIndexOf('.');
	    	    System.out.println(str.substring(index+1, dotIndex));
	    	    savePrefix = (str.substring(index+1, dotIndex));
	    	  }
	    	  else
	    	  {
	    		  int index=str.lastIndexOf('/');
		    	    int dotIndex = str.lastIndexOf('.');
		    	    System.out.println(str.substring(index+1, dotIndex));
		    	    savePrefix = (str.substring(index+1, dotIndex));
	    	  }
	    }
	    //just a normal substring definition that returns a substring (removes prefix of image file up to / or \)
	    public String sendCutString(String imagePathToEdit)
	    {
	    	str=imagePathToEdit;
	    	  
	    	  if(str.lastIndexOf('\\') > str.lastIndexOf('/'))
	    	  {
	    		  
	    	  
	    	    int index=str.lastIndexOf('\\');
	    	    int dotIndex = str.lastIndexOf('.');
	    	    System.out.println("index backslash was last" + str.substring(index+1, dotIndex));
	    	    savePrefix = (str.substring(index+1, dotIndex));
	    	  }
	    	  else
	    	  {
	    		    int index=str.lastIndexOf('/');
		    	    int dotIndex = str.lastIndexOf('.');
		    	    savePrefix = (str.substring(index+1, dotIndex));
	    	  }
	    	  return savePrefix;
	    }
	    //Logic for detecting surface rust, it takes an image path as a param
	    public void detectSurfaceRust(String imagePathToEdit)
	    {
	    	//gets substring for naming of files
	    	cutString(imagePathToEdit);		  
	    	  //check to see if directory exists:
	    //	  File PictureDirectory = new File(savePrefix);
	    //	  if (!PictureDirectory.exists()) {
	    //		  boolean directoryExists = false;
	    		//if it doesn't then make it!
	   // 		  try{
	   // 			  PictureDirectory.mkdir();
	   // 			  directoryExists = true;
	   // 		  }
	   // 		  catch(SecurityException se){
	    		        //handle 
	    //			  System.out.println("I fail here");
	    //		    }        
	   // 	  }
	   
		     // ---------------------------------------------------------
	    	 //  SURFACE RUST DETECTION
	    	 //  SETS THE RGB COLOUR RANGE (NOTE IT IS BLUE-GREEN-RED ORDER)
	    	 //  NOTE THAT IT HAS A MIN AND MAX.
	    	 // ---------------------------------------------------------
		        CvScalar min = opencv_core.cvScalar(31, 72, 94, 255);//BGR-A
		        CvScalar max= opencv_core.cvScalar(110, 149, 226, 255);//BGR-A
		      
	            //read image
	            IplImage orgImg = cvLoadImage(imagePathToEdit);
	            IplImage deepImg = orgImg;
	            //create binary image of original size
	         
	            IplImage imgThreshold = cvCreateImage(cvGetSize(orgImg), orgImg.depth(), 1);
	            
	      
	            //apply thresholding
	            cvInRangeS(orgImg, min, max, imgThreshold);
	 
	            //smooth filter- median
	            cvSmooth(imgThreshold, imgThreshold, CV_MEDIAN, 13, 0, 0, 0);
	            
	            //SAVE BLACK AND WHITE (CONTRAST) SURFACE RUST _detectedColour.jpg
	          
	            cvSaveImage(databaseDirectory + "\\" + savePrefix+ "_detectedColour.jpg", imgThreshold);
	         

	            //test contours
	         
	            CvMemStorage storage=CvMemStorage.create();
	            CvSeq squares = new CvContour();
	            cvFindContours(imgThreshold, storage, squares, Loader.sizeof(CvContour.class),    CV_RETR_LIST, CV_CHAIN_APPROX_SIMPLE);
	            if (squares.isNull() != true)
	            {
	         //   System.out.println(squares.total());
	            	 for (int i=0; i<squares.total(); i++)
	 	            {
	 	                cvDrawContours(orgImg, squares, CvScalar.ONE, CvScalar.ONE, 127, 2, 1);
	 	      
	 	            }
	            }
	            setPercentageOfRust(imgThreshold, "allRust");
	            //save the contour image
	            System.out.println("Rust percentage is    " + rustPercentage +"%");
	            cvSaveImage(databaseDirectory + "\\" + savePrefix+ "_contouredColour.jpg", orgImg);
	            System.out.println("saving at " +databaseDirectory+ "\\"+savePrefix+ "_contouredColour.jpg" );
	          
	            
	            
	            // SETS UP three images to check for additional ranges - Important of you find a subset of colours that are not rust inside of rust range. eg. 1-10 = rust but 3-4 are not rust. ergo. 1-2 + 5-10 = rust.
	            // ADD More and merge later to add additional ranges.
	            IplImage imgDeepThreshold = cvCreateImage(cvGetSize(orgImg), orgImg.depth(), 1);
	            IplImage imgDeepThreshold2 = cvCreateImage(cvGetSize(orgImg), orgImg.depth(), 1);
	            IplImage imgDeepThreshold3 = cvCreateImage(cvGetSize(orgImg), orgImg.depth(), 1);
	  
			     // ---------------------------------------------------------
		    	 //  HEAVY  RUST DETECTION
		    	 //  SETS THE RGB COLOUR RANGE (NOTE IT IS BLUE-GREEN-RED ORDER)
		    	 //  NOTE THAT IT HAS A MIN AND MAX.
		    	 // ---------------------------------------------------------
		        
		        // Range 1:
		        min = opencv_core.cvScalar(48, 58, 90, 255);//BGR-A
		        max= opencv_core.cvScalar(70, 80, 165, 255);//BGR-A
		        
		        // Range 2
		        CvScalar min2 = opencv_core.cvScalar(80, 90, 105, 255);//BGR-A
		        CvScalar max2 = opencv_core.cvScalar(120, 128, 160, 255);//BGR-A
		  
		        
	            //run a the image against new parameters.
		        cvSmooth(imgDeepThreshold, imgDeepThreshold, CV_MEDIAN, 13, 0, 0, 0);
		        cvInRangeS(deepImg, min, max, imgDeepThreshold3);
		        cvInRangeS(deepImg, min2, max2, imgDeepThreshold2);
		        cvAdd(imgDeepThreshold2, imgDeepThreshold3, imgDeepThreshold);
		        setPercentageOfRust(imgDeepThreshold, "deepRust");
		        
		        //SAVE CONTOUR HARD RUST _detectedHardRustColour.jpg
		        cvSaveImage(databaseDirectory + "\\" + savePrefix+ "_detectedHardRustColour.jpg", imgDeepThreshold);
	            //Find deep rust contours
		    	CvMemStorage storage2=CvMemStorage.create();
	            CvSeq squares2 = new CvContour();
		        cvFindContours(imgDeepThreshold, storage2, squares2, Loader.sizeof(CvContour.class),    CV_RETR_LIST, CV_CHAIN_APPROX_SIMPLE);
		        
	            if (squares.isNull() != true)
	            
	            {
	           
	            	 for (int i=0; i<squares2.total(); i++)
	 	            {
	 	                cvDrawContours(deepImg, squares2, CvScalar.ONE, CvScalar.ONE, 127, 2, 1);

	 	            }
	            }
	            //SAVE CONTOUR HARD RUST _contouredHardRust.jpg
	            cvSaveImage(databaseDirectory + "\\" + savePrefix+ "_contouredHardRust.jpg", deepImg);
	            
	      
	    }
	    public void setPercentageOfRust(IplImage imgThreshold,  String type)
	    {
	    	System.out.println("Setting percentage");
	    	   int rustCount = cvCountNonZero(imgThreshold);
	           int totalPixels = imgThreshold.arraySize();
	          
	    	if (type == "allRust")
	    	{
	    	 
	            rustPercentage = (double)rustCount/ (double) totalPixels;
	            
	            System.out.println("ALL RUST IS: " + deepRustPercentage);
	    	}
		    if (type == "deepRust")
		    {
		    	deepRustPercentage = (double)rustCount/ (double) totalPixels;
		    	System.out.println("DEEP RUST IS: " + deepRustPercentage);
		    	 boolean checkedFlag = false;
		    	//set rust status
		    	//logic
		    	//Grade B but not Heavy is less than 10%
		    		System.out.println("CHECKED");
		    if (checkedFlag == false)
		    	{
		    	
		    	System.out.println("CHECKED");
		             if (((rustPercentage > 0.4 && rustPercentage <= 1.0) || deepRustPercentage >= 0.1) && checkedFlag != true)
		    	{
		    		checkedFlag = true;
		    		System.out.println("POOR");
		    		rustStatus="Poor";
		    	}
		             //if case due to overlap of rust colours - can be perfected if we perfect rust detection using  artificial intelligence eg. Baysian classifier.
		    	else if (((rustPercentage >= 0 && rustPercentage < 0.2) && checkedFlag != true ))
		    	{
		    		checkedFlag = true;
		    		System.out.println("GOOD");
		    		rustStatus="Good";
		    	}
		    		//Less than 20% Grade C or Grade B is greater than 20% and total rust < 40%
		    		else if (((rustPercentage > 0.2 && rustPercentage < 0.4) ||  (deepRustPercentage < 0.1 && deepRustPercentage >= 0.01)) && checkedFlag != true)
		    	{
		    		checkedFlag = true;
		    		System.out.println("FAIR");
		    		rustStatus="Fair";
		    	}
		    	
		    		else
		    	{
		    			//This condition should never occur, May occur if decimal is to 10 or more places? 
		    		checkedFlag = true;
		    		System.out.println("no idea");
		    		rustStatus="Visual Inspection Required";
		    	}
		        
		        
		    	}
		   
		    //This rounds the percent to 4 decimal places which then gets reduced later to two. 
		    //Important as it removes long doubles.
		    rustPercentage = round(rustPercentage, 4);
		    deepRustPercentage = round(deepRustPercentage, 4);
		    if (Double.toString(rustPercentage).contains("E") == true)
		    {
		    	rustPercentage = 0.0001;
		    }
		    if (Double.toString(deepRustPercentage).contains("E") == true)
		    {
		    	deepRustPercentage = 0.0001;
		    }
	    	}
		    
		    
		    
	    }
	    //Big decimal roundin method.
	    public static double round(double value, int places) {
	        if (places < 0) throw new IllegalArgumentException();

	        BigDecimal bd = new BigDecimal(value);
	        bd = bd.setScale(places, RoundingMode.HALF_UP);
	        return bd.doubleValue();
	    }
	    //getters and setters
		public double getRustPercentage() {
			return rustPercentage;
		}

		public String getDatabaseDirectory() {
			return databaseDirectory;
		}

		public void setDatabaseDirectory(String databaseDirectory) {
			this.databaseDirectory = databaseDirectory;
		}

		public String getSavePrefix() {
			return savePrefix;
		}

		public boolean isDirectoryChosen() {
			return directoryChosen;
		}

		public void setDirectoryChosen(boolean directoryChosen) {
			this.directoryChosen = directoryChosen;
		}

		public double getDeepRustPercentage() {
			return deepRustPercentage;
		}

		public void setDeepRustPercentage(double deepRustPercentage) {
			this.deepRustPercentage = deepRustPercentage;
		}
		public String getRustStatus() {
			return rustStatus;
		}

		public void setRustStatus(String rustStatus) {
			this.rustStatus = rustStatus;
		}

	    
 
	    
	    
	}