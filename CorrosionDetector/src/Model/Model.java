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
	    
	   
	    public void ChangeToGreyScale(String imagePathToEdit)
	    {
	    	
	    	/*
	     //OPENCV STUFF
	     System.out.println("hello");
	     
	     
	     //RED DETECTION
	//     detectRed("colour-pic-swatch_2883168b.jpg");
	     
	     
	     //GREY SCALE WORKS!!
	     
	 
	     
	    
         try {
        	

       	     System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        	 File input = new File(imagePathToEdit);
    	    System.out.println("Image path to edit " + imagePathToEdit);
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
	         File output = new File((imagePathToEdit + " grayscale.jpg"));
	         System.out.println("output  " +   output);
	         ImageIO.write(image1, "jpg", output);
	     	System.out.println("5");
	     
	         
		} catch (Exception e) {
			System.out.println("ERROR WITH CORROSION DETECT");
		}	
       */
	}
	    
	    
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
		    	    System.out.println("index forward-lash was last" + str.substring(index+1, dotIndex));
		    	    savePrefix = (str.substring(index+1, dotIndex));
	    	  }
	    	  return savePrefix;
	    }
	    
	    public void detectRed(String imagePathToEdit)
	    {
	    	cutString(imagePathToEdit);
	    	//gets substring for naming of files
	    	 
	    		  
	    	  //check to see if directory exists:
	    	  File PictureDirectory = new File(savePrefix);
	    	  if (!PictureDirectory.exists()) {
	    		  boolean directoryExists = false;
	    		
	    		  try{
	    			  PictureDirectory.mkdir();
	    			  directoryExists = true;
	    		  }
	    		  catch(SecurityException se){
	    		        //handle it
	    		    }        
	    	  }
	        //range other

		     //orig   
		        CvScalar min = opencv_core.cvScalar(31, 72, 94, 255);//BGR-A
		        CvScalar max= opencv_core.cvScalar(110, 149, 226, 255);//BGR-A
		        
	     //   CvScalar min = opencv_core.cvScalar(50, 70, 94, 255);//BGR-A
	      //  CvScalar max= opencv_core.cvScalar(110, 145, 226, 255);//BGR-A
	        
	        System.out.println(imagePathToEdit);

	            //read image
	            IplImage orgImg = cvLoadImage(imagePathToEdit);
	            IplImage deepImg = orgImg;
	            //create binary image of original size
	         
	            IplImage imgThreshold = cvCreateImage(cvGetSize(orgImg), orgImg.depth(), 1);
	            
	      
	            //apply thresholding
	            cvInRangeS(orgImg, min, max, imgThreshold);
	 
	            //smooth filter- median
	            cvSmooth(imgThreshold, imgThreshold, CV_MEDIAN, 13, 0, 0, 0);
	            
	            //SAVE BLACK AND WHITE SURFACE RUST _detectedColour.jpg
	          
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
	            //cvSaveImage("C:\\Users\\Liam\\Documents\\Software Engineering\\Ge.jpg", orgImg);
	           // System.out.println("database location is: " + dataBaseLocation);
	           // System.out.println("C:/Users/Liam/Documents/Software Engineering.jps");
	            //cvSaveImage(savePrefix+ "/contouredColour.jpg", orgImg);
	          //SAVE CONTOUR SURFACE RUST _contouredColour.jpg
	            System.out.println("saving at " +databaseDirectory+ "\\"+savePrefix+ "_contouredColour.jpg" );
	          
	            
	            
	            //get new image
	            IplImage imgDeepThreshold = cvCreateImage(cvGetSize(orgImg), orgImg.depth(), 1);
	            IplImage imgDeepThreshold2 = cvCreateImage(cvGetSize(orgImg), orgImg.depth(), 1);
	            IplImage imgDeepThreshold3 = cvCreateImage(cvGetSize(orgImg), orgImg.depth(), 1);
	            //orig 
	            //Set new min max to reflect heavy rust
	         //   min = opencv_core.cvScalar(50, 70, 105, 255);//BGR-A
		    //    max= opencv_core.cvScalar(102, 119, 151, 255);//BGR-A
		       //altered
	           // min = opencv_core.cvScalar(45, 43, 82, 255);//BGR-A
		      //  max= opencv_core.cvScalar(102, 119, 151, 255);//BGR-A
	            
	            
	            //overall min
		        //min = opencv_core.cvScalar(15, 37, 41, 255);//BGR-A
		        //max= opencv_core.cvScalar(41, 70, 41, 255);//BGR-A
		        
		        //overall max
		        //CvScalar min2 = opencv_core.cvScalar(71, 92, 73, 255);//BGR-A
		        //CvScalar max2 = opencv_core.cvScalar(102, 119, 151, 255);//BGR-A
		        
		        
		        //overall min
		        min = opencv_core.cvScalar(48, 58, 90, 255);//BGR-A
		        max= opencv_core.cvScalar(70, 80, 165, 255);//BGR-A
		        
		        //overall max
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
	            
	         //   System.out.println(squares.total());
	            	 for (int i=0; i<squares2.total(); i++)
	 	            {
	 	                cvDrawContours(deepImg, squares2, CvScalar.ONE, CvScalar.ONE, 127, 2, 1);

	 	            }
	            }
	            //save the contours
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
		             //if clause due to overlap of rust colours
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
		    		checkedFlag = true;
		    		System.out.println("no idea");
		    		rustStatus="Visual Inspection Required";
		    	}
		        
		        
		    	}
		    rustPercentage = round(rustPercentage, 4);
		    deepRustPercentage = round(deepRustPercentage, 4);
	    	}
		    
		    
		    
	    }
	    public static double round(double value, int places) {
	        if (places < 0) throw new IllegalArgumentException();

	        BigDecimal bd = new BigDecimal(value);
	        bd = bd.setScale(places, RoundingMode.HALF_UP);
	        return bd.doubleValue();
	    }

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

	    
	    
	    //OPEN CV CLEAN UP
	  
	    
	    
	    
	   
	    
	    
	}