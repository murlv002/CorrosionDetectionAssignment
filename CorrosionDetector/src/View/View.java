package View;
////////////////// THIS CLASS CONTAINS ALL THINGS VIEW RELATED. ALL LOGIC IS STORED IN THE MODEL CLASS
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Controller.Controller;
import Model.Model;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Window.Type;
import java.awt.Color;
import java.awt.SystemColor;

public class View extends JFrame {
	//default vars
	Model model = new Model();
	private Controller controller = new Controller();
	private String currentFilePath;
	JLabel databaseLocation;
	String state;
	javax.swing.JLabel percentageRustLabel;
	boolean directoryChosen = false;
	String directoryChosenString = "";
	JLabel deepRustPercentageLabel;
	JLabel lblStatus;
	boolean detectedThisSession;
	private int lastCode = 0;
	boolean isDefault = true;
	
	public View()
	{
		setMinimumSize(new Dimension(870, 660));
		getContentPane().setBackground(Color.WHITE);
		setTitle("Corrosion Detector\r\n");
		initialize();
	}
	
	private void initialize()
	{ 
		//default vars.
		databaseLocation = new JLabel();
		state = "contour";
		percentageRustLabel = new JLabel();
		model = new Model();
		myLabel = new javax.swing.JLabel();
        loadData = new javax.swing.JButton();
        loadData.setBackground(SystemColor.activeCaption);
        jScrollPane1 = new javax.swing.JScrollPane();
        picLabel = new JLabel();
        deepRustPercentageLabel = new JLabel();
        JLabel lblStatus = new JLabel("Status:");
        detectCorrosionHeavyButton = new javax.swing.JButton();
        detectCorrosionHeavyButton.setBackground(SystemColor.activeCaption);
        detectCorrosionHeavyButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        detectCorrosionColourButton = new javax.swing.JButton();
        detectCorrosionColourButton.setBackground(SystemColor.activeCaption);
        
        
        //default image set here!
        String defaultImagePath = "default.png";
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("default.png");
        //URL defaultURLImagePath = getClass().getResource("default.png");
        setCurrentFilePath(defaultImagePath);
        myImage = new BufferedImage(1, 1, 1);
  
        try {
      
        myImage= ImageIO.read((stream));
   //     myImage = Toolkit.getDefaultToolkit().getImage(defaultURLImagePath);
       
        	
        
        System.out.println(myImage.getGraphics());

        } catch (Exception e)
        {
        	System.out.println("Failed to print out image");
        
        }
        
        myPic = new ImageIcon(myImage);
       // setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("src/default.png")));
        
        picLabel.setIcon(myPic);
        picLabel.setSize(20, 20);
        
        jScrollPane1.setViewportView(picLabel);
        //try to find the document, if it is real then yay, if not then no!
        final JFileChooser temp = new JFileChooser();
        databaseLocation.setText(temp.getFileSystemView().getDefaultDirectory().toString());
        System.out.println("Default directory is : " + temp.getFileSystemView().getDefaultDirectory().toString());
        model.setDataBaseLocation(databaseLocation.getText());
		directoryChosenString = databaseLocation.getText();
		model.setDatabaseDirectory(directoryChosenString);
		directoryChosen = true;
		model.setDirectoryChosen(directoryChosen);
		//set the database label to the directory.
		databaseLocation.setText(directoryChosenString);
        
        
        
        
        
        
        
        //default close window operation (close jvm)
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName("myFrame"); // NOI18N

        myLabel.setText("No Image File Loaded");

        //load image file listener
        loadData.setText("Load Image File");
        loadData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loadDataMouseClicked(evt);
            }
        });

     
        //heavy rust detection button listener
        detectCorrosionHeavyButton.setText("Heavy Rust: CONTRAST (Toggle)");
        detectCorrosionHeavyButton.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                heavyRustMouseClicked(evt);
            }
        });
        //surface rust detection button listener
        detectCorrosionColourButton.setText("Surface Rust: CONTRAST (Toggle)");
        detectCorrosionColourButton.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ColourMouseClicked(evt);
            }
        });
      

        //select a database button listener
        JButton btnSelectDatabaseFolder = new JButton("Select Database Folder");
        btnSelectDatabaseFolder.setBackground(SystemColor.activeCaption);
        btnSelectDatabaseFolder.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	loadDirectoryMouseClicked(evt);
            }
        });
        
        JLabel lblTotalRustPercentage = new JLabel("Surface Rust:");
        lblHardScaleRust = new JLabel("Hard Scale Rust:");
        statusLabel = new JLabel();
        //noramalize image button listener
        JButton revertButton = new JButton();
        revertButton.setText("Normalize Image");
        revertButton.setBackground(SystemColor.activeCaption);
        
        revertButton.addMouseListener(new java.awt.event.MouseAdapter()
        {
        	public void mouseClicked(java.awt.event.MouseEvent evt) {
                revert(evt);
            }
        });
        
        JLabel lblShipName = new JLabel("Ship Name");
        
        lblShipPart = new JLabel("Ship Part");
        
        shipNameField = new JTextField();
        shipNameField.setColumns(10);
        
        shipPartField = new JTextField();
        shipPartField.setColumns(10);
        
        saveButton = new JButton("Save Report");
        saveButton.setBackground(SystemColor.activeCaption);
        
        saveButton.addMouseListener(new java.awt.event.MouseAdapter()
        {
        	public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveDescription(evt);
            }
        });
        
        JLabel lblDatabaseLocation = new JLabel("Database Location:");
        
        
        
        //Layout stuff...I used and included the package to update the java design via the gui.
        //If using eclipse do the following: right vlick on the View.java -> Open with -> WindowBuild Editor
        
        
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(lblDatabaseLocation)
        					.addPreferredGap(ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
        					.addComponent(databaseLocation, GroupLayout.PREFERRED_SIZE, 362, GroupLayout.PREFERRED_SIZE)
        					.addGap(34)
        					.addComponent(btnSelectDatabaseFolder)
        					.addGap(131))
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 834, Short.MAX_VALUE)
        					.addContainerGap())
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(myLabel, GroupLayout.DEFAULT_SIZE, 844, Short.MAX_VALUE)
        					.addGap(0))
        				.addGroup(layout.createSequentialGroup()
        					.addGap(35)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        							.addGroup(layout.createSequentialGroup()
        								.addGroup(layout.createParallelGroup(Alignment.LEADING)
        									.addComponent(lblTotalRustPercentage)
        									.addComponent(lblHardScaleRust)
        									.addComponent(lblShipPart))
        								.addPreferredGap(ComponentPlacement.RELATED)
        								.addGroup(layout.createParallelGroup(Alignment.LEADING)
        									.addGroup(layout.createSequentialGroup()
        										.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        											.addComponent(shipPartField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
        											.addComponent(shipNameField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
        											.addGroup(layout.createSequentialGroup()
        												.addComponent(percentageRustLabel, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
        												.addPreferredGap(ComponentPlacement.RELATED, 137, Short.MAX_VALUE)
        												.addComponent(lblStatus)))
        										.addGap(8))
        									.addGroup(layout.createSequentialGroup()
        										.addComponent(deepRustPercentageLabel, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
        										.addPreferredGap(ComponentPlacement.RELATED))))
        							.addGroup(layout.createSequentialGroup()
        								.addComponent(loadData, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
        								.addPreferredGap(ComponentPlacement.UNRELATED)
        								.addComponent(detectCorrosionColourButton)))
        						.addComponent(lblShipName))
        					.addGap(71)
        					.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        						.addGroup(layout.createParallelGroup(Alignment.LEADING)
        							.addGroup(layout.createSequentialGroup()
        								.addGap(4)
        								.addComponent(detectCorrosionHeavyButton)
        								.addPreferredGap(ComponentPlacement.RELATED)
        								.addComponent(revertButton, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE))
        							.addComponent(statusLabel, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
        						.addComponent(saveButton, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE))
        					.addContainerGap(47, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(myLabel)
        			.addGap(25)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(databaseLocation, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnSelectDatabaseFolder, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblDatabaseLocation))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        				.addComponent(loadData, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(detectCorrosionColourButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        					.addComponent(revertButton)
        					.addComponent(detectCorrosionHeavyButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        						.addGroup(layout.createSequentialGroup()
        							.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
        								.addComponent(percentageRustLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        								.addComponent(lblTotalRustPercentage, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
        								.addComponent(lblHardScaleRust)
        								.addComponent(deepRustPercentageLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)))
        						.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        							.addComponent(lblStatus)
        							.addComponent(statusLabel)))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(lblShipName)
        						.addComponent(shipNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(shipPartField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(lblShipPart)))
        				.addComponent(saveButton, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap())
        );
        getContentPane().setLayout(layout);
        
        
     //   Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      
        this.setSize(830, 644);
    }      
	
	
	
	//Load the database Directory
	private void loadDirectoryMouseClicked(java.awt.event.MouseEvent evt)
	{
		  try {
			  
    		final JFileChooser fc = new JFileChooser();
    		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    		int response = fc.showOpenDialog(this);
    		if (response == JFileChooser.APPROVE_OPTION){
    			System.out.println("CHANGE MY DIRECTORY TO THIS: " + fc.getSelectedFile());
    			directoryChosenString = fc.getSelectedFile().toString();
    			//escape backslashes
    			directoryChosenString.replace("\\","\\\\");
	    					
    			model.setDatabaseDirectory(directoryChosenString);
    			directoryChosen = true;
    			model.setDirectoryChosen(directoryChosen);
    			//set the database label to the directory.
    			
    			databaseLocation.setText(directoryChosenString);
    		    detectedThisSession = false;	
    		    highlightCurrentToggle(5);
    		}
    		    
		  } catch (Exception er) {
	            System.out.println("Some has gone wrong with the loadDataMouseClick");
		  }
	}

	
	//Loads the image file and displays to the screen. 
    private void loadDataMouseClicked(java.awt.event.MouseEvent evt) {                                      
        try {
        	
        	//vars
            String data = controller.getMessage();
            myLabel.setText(data);
            myLabel.setVisible(true);
            boolean flag = true;
            //Flag that checks to see if this image has been loaded for the first time.
            if (flag == true)
            	{
            		final JFileChooser fc = new JFileChooser();
            		FileNameExtensionFilter filter = new FileNameExtensionFilter(
            		        "JPG & GIF Images", "jpg", "gif");
            		    fc.setFileFilter(filter);

            		int response = fc.showOpenDialog(this);
            		if (response == JFileChooser.APPROVE_OPTION){
            			File newFile = fc.getSelectedFile();
            			//open the file here and update the picture!
            			myLabel.setText(newFile.getName());
            
            			myImage= ImageIO.read(new File(newFile.toString()));
            			setCurrentFilePath(newFile.toString());
            			picLabel.setSize(jScrollPane1.getWidth()-10, jScrollPane1.getHeight()-10);
            			myPic.setImage(myImage.getScaledInstance(picLabel.getWidth(), picLabel.getHeight(), Image.SCALE_SMOOTH));
            			picLabel.setIcon(myPic);
            			picLabel.setVisible(true);
            			state = "contour";
            			percentageRustLabel.setVisible(false);
            			deepRustPercentageLabel.setVisible(false);
            			statusLabel.setVisible(false);
            			detectedThisSession = false;
                		isDefault = false;
            		}
            		//green highlight of button
            		highlightCurrentToggle(5);
            	}
        } catch (Exception er) {
            System.out.println("Some has gone wrong with the loadDataMouseClick");
        }
        
    }                                     

    private void heavyRustMouseClicked(java.awt.event.MouseEvent evt) {  
    	// Tech Debt: Merge these two very similar methods into one.
    	//Checks to see if this is the default image (Starting screen image - If it is then don't allow toggles to work).
    	if (isDefault == false)
    	{
    		//Toggle is either in one state or another. Contour (Circles around rust) or Contrast (Black background against white rust).
        if (state == "contour"){
        	highlightCurrentToggle(3);
        	try{
        		highlightCurrentToggle(3);
        		//If the rust detection has been done, don't bother wasting precious cpu and memory. Just select the file and redisplay.
        		if (detectedThisSession == true)
        		{
        			Image contouredImage = ImageIO.read(new File(directoryChosenString + "\\" + model.sendCutString(currentFilePath) +"_contouredHardRust.jpg"));
        			picLabel.setVisible(false);
        			picLabel.setSize(jScrollPane1.getWidth()-10, jScrollPane1.getHeight()-10);
        			myPic.setImage(contouredImage.getScaledInstance(picLabel.getWidth(), picLabel.getHeight(), Image.SCALE_SMOOTH));
        			picLabel.setIcon(myPic);
        			picLabel.setVisible(true);
        			setState("contrast");
        		}
        		else
        		{
        //Contour! Go to Model and process image.			
        Image imageToEdit = myPic.getImage();
		System.out.println("my current file path is: " + getCurrentFilePath());
		model.detectSurfaceRust(getCurrentFilePath());
		//set image to contour colour and resize image to fit window.
		Image contouredImage = ImageIO.read(new File(directoryChosenString + "\\" + model.sendCutString(currentFilePath) +"_contouredHardRust.jpg"));
		picLabel.setVisible(false);
		picLabel.setSize(jScrollPane1.getWidth()-10, jScrollPane1.getHeight()-10);
		myPic.setImage(contouredImage.getScaledInstance(picLabel.getWidth(), picLabel.getHeight(), Image.SCALE_SMOOTH));
		picLabel.setIcon(myPic);
		picLabel.setVisible(true);
		setState("contrast");
		percentageRustLabel.setText(Double.toString(model.getRustPercentage()*100) + "%");
		percentageRustLabel.setVisible(true);
		System.out.println(model.getDeepRustPercentage());
		deepRustPercentageLabel.setText(Double.toString(model.getDeepRustPercentage()*100) + "%");
		deepRustPercentageLabel.setVisible(true);
		statusLabel.setText(model.getRustStatus());
		statusLabel.setVisible(true);
		detectedThisSession = true;
        		}
		}catch(Exception e)
		{	
			//catch any errors in detection
			System .out.println("CAUGHT tried to CONTOUR from : " + e.toString());

	
		}
        }
        else 
		{
			//do the contrast. (exactly the same but contrasts).
        	try{
        		highlightCurrentToggle(4);
        		if (detectedThisSession == true)
        		{
        			Image contouredImage = ImageIO.read(new File(directoryChosenString + "\\" + model.sendCutString(currentFilePath) +"_detectedHardRustColour.jpg"));
        			picLabel.setVisible(false);
        			picLabel.setSize(jScrollPane1.getWidth()-10, jScrollPane1.getHeight()-10);
        			myPic.setImage(contouredImage.getScaledInstance(picLabel.getWidth(), picLabel.getHeight(), Image.SCALE_SMOOTH));
        			picLabel.setIcon(myPic);
        			picLabel.setVisible(true);
        			setState("contour");
        		}
        		else
        		{
			Image imageToEdit = myPic.getImage();

			model.detectSurfaceRust(getCurrentFilePath());
			//set image to contrast colour and resize image to fit window.
			Image contouredImage = ImageIO.read(new File(directoryChosenString + "\\" + model.sendCutString(currentFilePath) +"_detectedHardRustColour.jpg"));
			picLabel.setSize(jScrollPane1.getWidth()-10, jScrollPane1.getHeight()-10);
			myPic.setImage(contouredImage.getScaledInstance(picLabel.getWidth(), picLabel.getHeight(), Image.SCALE_SMOOTH));
			picLabel.setVisible(false);
			picLabel.setIcon(myPic);
			picLabel.setVisible(true);
			setState("contour");
        		}
			}catch(Exception e)
			{
			//catch any errors in detection
				System .out.println("CAUGHT tried to CONTRAST exception: " + e.toString());
	
			}
	        }
    	}
    }       
    
    private void ColourMouseClicked(java.awt.event.MouseEvent evt) {                                       
        // Tech Debt: Merge these two very similar methods into one.
    	if (isDefault == false)
    	{
        if (state == "contour"){
        //TODO
        //IF FILE HAS BEEN DRAWN, THEN JUST READ!
        	try{
        		highlightCurrentToggle(1);
        		if (detectedThisSession == true)
        		{
        			Image contouredImage = ImageIO.read(new File(directoryChosenString + "\\" + model.sendCutString(currentFilePath) +"_contouredColour.jpg"));
        			picLabel.setVisible(false);
        			picLabel.setSize(jScrollPane1.getWidth()-10, jScrollPane1.getHeight()-10);
        			myPic.setImage(contouredImage.getScaledInstance(picLabel.getWidth(), picLabel.getHeight(), Image.SCALE_SMOOTH));
        			picLabel.setIcon(myPic);
        			picLabel.setVisible(true);
        			setState("contrast");
        		}
        		else
        		{
        //Contour! Go to Model and process image.		
        Image imageToEdit = myPic.getImage();
		System.out.println("my current file path is: " + getCurrentFilePath());
		model.detectSurfaceRust(getCurrentFilePath());
		//set image to contour colour and resize image to fit window.
		Image contouredImage = ImageIO.read(new File(directoryChosenString + "\\" + model.sendCutString(currentFilePath) +"_contouredColour.jpg"));
		System.out.println("did i make it");
		picLabel.setVisible(false);
		picLabel.setSize(jScrollPane1.getWidth()-10, jScrollPane1.getHeight()-10);
		myPic.setImage(contouredImage.getScaledInstance(picLabel.getWidth(), picLabel.getHeight(), Image.SCALE_SMOOTH));
		picLabel.setIcon(myPic);
		picLabel.setVisible(true);
		setState("contrast");
		
		percentageRustLabel.setText(Double.toString(model.getRustPercentage()*100) + "%");
		percentageRustLabel.setVisible(true);
		deepRustPercentageLabel.setText(Double.toString(model.getDeepRustPercentage()*100) + "%");
		deepRustPercentageLabel.setVisible(true);
		
		statusLabel.setText(model.getRustStatus());
		statusLabel.setVisible(true);
		detectedThisSession = true;
        		}
		}catch(Exception e)
		{
	
			System .out.println("CAUGHT tried to CONTOUR from : " + e.toString());
			System.out.println(model.sendCutString(currentFilePath)+"/contouredColour.jpg");
			
		}
        }
		
        //CONTRAST
        else 
		{
			//Same as the Contour but contrasts!
        	try{
        		highlightCurrentToggle(2);
        		if (detectedThisSession == true)
        		{
        			Image contouredImage = ImageIO.read(new File(directoryChosenString + "\\" + model.sendCutString(currentFilePath) +"_detectedColour.jpg"));
        			picLabel.setVisible(false);
        			picLabel.setSize(jScrollPane1.getWidth()-10, jScrollPane1.getHeight()-10);
        			myPic.setImage(contouredImage.getScaledInstance(picLabel.getWidth(), picLabel.getHeight(), Image.SCALE_SMOOTH));
        			picLabel.setIcon(myPic);
        			picLabel.setVisible(true);
        			setState("contour");
        		}
        		else
        		{
        	//contrast! Go to Model and process image.	
			System.out.println("DO THE CONTRAST THING");
			Image imageToEdit = myPic.getImage();
			System.out.println(getCurrentFilePath());
			model.detectSurfaceRust(getCurrentFilePath());
			//set image to contour colour and resize image to fit window.
			Image contouredImage = ImageIO.read(new File(directoryChosenString + "\\" + model.sendCutString(currentFilePath) +"_detectedColour.jpg"));
			picLabel.setSize(jScrollPane1.getWidth()-10, jScrollPane1.getHeight()-10);
			myPic.setImage(contouredImage.getScaledInstance(picLabel.getWidth(), picLabel.getHeight(), Image.SCALE_SMOOTH));
			picLabel.setVisible(false);
			picLabel.setIcon(myPic);
			picLabel.setVisible(true);
			setState("contour");
        		}
			}catch(Exception e)
			{
				//Catch any errors in image processing process
				System .out.println("CAUGHT tried to CONTRAST exception: " + e.toString());
				System.out.println(model.sendCutString(currentFilePath)+"/detectedColour.jpg");
				
			}
	      }
    } }
    
    
    private void highlightCurrentToggle(int code)
    {
    	//if 1, change to contrast surface (currently contour)
    	if (code == 1)
    	{
    		detectCorrosionColourButton.setText("Surface Rust: CONTOUR (Toggle) ");
    		detectCorrosionColourButton.setBackground(Color.decode("#90ee90"));
    		detectCorrosionHeavyButton.setBackground(SystemColor.activeCaption);

    	}
    	//if 2, change to contour surface (currently contrast)
    	if (code == 2)
    	{
    		detectCorrosionColourButton.setText("Surface Rust: CONTRAST (Toggle)");
    		detectCorrosionColourButton.setBackground(Color.decode("#90ee90"));
    		detectCorrosionHeavyButton.setBackground(SystemColor.activeCaption);
    	}
    	//if 3, change to contrast heavy (currently contour)
    	if (code == 3)
    	{
    		detectCorrosionHeavyButton.setText("Heavy Rust: CONTOUR   (Toggle)");
    		detectCorrosionHeavyButton.setBackground(Color.decode("#90ee90"));
    		detectCorrosionColourButton.setBackground(SystemColor.activeCaption);
    	}
    	//if 4, change to contour heavy (currently contrast)
    	if (code == 4)
    	{
    		detectCorrosionHeavyButton.setText("Heavy Rust: CONTRAST (Toggle)");
    		detectCorrosionHeavyButton.setBackground(Color.decode("#90ee90"));
    		detectCorrosionColourButton.setBackground(SystemColor.activeCaption);
    	}
    	if (code == 5)
    	{
    		detectCorrosionHeavyButton.setText("Heavy Rust: CONTOUR   (Toggle)");
        	detectCorrosionColourButton.setText("Surface Rust: CONTOUR (Toggle)");
        	detectCorrosionColourButton.setBackground(SystemColor.activeCaption);
        	detectCorrosionHeavyButton.setBackground(SystemColor.activeCaption);
        	saveButton.setBackground(SystemColor.activeCaption);
    	}
    	if ( code == 9)
    	{
    		saveButton.setBackground(Color.decode("#90ee90"));
    	}
   
    }
    //Reverts all of the toggle buttons back to their default state and resizes the image to fit the window.
    private void revert(java.awt.event.MouseEvent s)
    {
    	if (isDefault == false)
    	{
    	if (detectedThisSession == true)
    	{
    	detectCorrosionHeavyButton.setText("Heavy Rust: CONTOUR (Toggle)  ");
    	detectCorrosionColourButton.setText("Surface Rust: CONTOUR (Toggle)");
    	detectCorrosionColourButton.setBackground(SystemColor.activeCaption);
    	detectCorrosionHeavyButton.setBackground(SystemColor.activeCaption);
    	try {
    		//resize or 'normalizes' the image in relation to the window
			Image contouredImage = ImageIO.read(new File(currentFilePath));
			picLabel.setVisible(false);
			picLabel.setSize(jScrollPane1.getWidth()-10, jScrollPane1.getHeight()-10);
			myPic.setImage(contouredImage.getScaledInstance(picLabel.getWidth(), picLabel.getHeight(), Image.SCALE_SMOOTH));
			picLabel.setIcon(myPic);
			picLabel.setVisible(true);
			setState("contour");
		} catch (IOException e) {

			e.printStackTrace();
		}
    	}
    }}
    
    //Saves the ship name, part and amount of rusts to a report in the save directory.
    void saveDescription(java.awt.event.MouseEvent s)
    {
    	//FORCE ALL PARAMETERS TO BE FILLED IN.
    		if (shipNameField.getText().isEmpty() == true  || shipPartField.getText().isEmpty() == true || percentageRustLabel.isVisible() == false || deepRustPercentageLabel.isVisible() == false || statusLabel.getText().isEmpty())

    		{
    			JOptionPane.showMessageDialog(this, "Please fill in the Ship Name, Ship Part and toggle on image detection  to save the file.");
    		}
    		else
    		{
    			PrintWriter writer;
				try {
			
					writer = new PrintWriter((model.getDatabaseDirectory() + "\\"+ model.getSavePrefix() + "_Report"), "UTF-8");
					writer.println("Ship Name : " + shipNameField.getText());
					writer.println("Ship Part : " + shipPartField.getText());
					writer.println("Surface Rust : " + percentageRustLabel.getText());
					writer.println("Heavy Rust : " +  deepRustPercentageLabel.getText());
					writer.println("Status : " +  statusLabel.getText());
	    			writer.close();
	    			highlightCurrentToggle(9);
				} catch (FileNotFoundException | UnsupportedEncodingException e) {
					e.printStackTrace();
				}
    			
    		}
    }
    
    
    private String getCurrentFilePath()
    {
    	return this.currentFilePath;
    }
    private void setCurrentFilePath(String s)
    {
    	this.currentFilePath=s;
    }

    
    //Ignore: Log generator for the GUI.
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
            
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new View().setVisible(true);
            }
       
        });
    }

  

	public void setState(String state) {
		this.state = state;
	}

	// Variables declaration - do not modify                     
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton loadData;
    public javax.swing.JLabel myLabel;
    private javax.swing.JButton detectCorrosionHeavyButton;
    private javax.swing.JButton detectCorrosionColourButton;
    private JLabel picLabel;
    private ImageIcon myPic;
    private Image myImage;
    private JLabel lblHardScaleRust;
    private JLabel statusLabel;
    private JLabel lblShipPart;
    private JTextField shipNameField;
    private JTextField shipPartField;
    private JButton saveButton;
}
