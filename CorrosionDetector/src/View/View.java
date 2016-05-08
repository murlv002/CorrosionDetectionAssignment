package View;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

public class View extends JFrame {
	Model model = new Model();
	private Controller controller = new Controller();
	private String currentFilePath;
	JLabel databaseLocation;
	javax.swing.JLabel databaseLabel;
	String state;
	javax.swing.JLabel percentageRustLabel;
	boolean directoryChosen = false;
	String directoryChosenString = "";
	public View()
	{
		
	  
		initialize();
	}
	
	private void initialize()
	{ 
		
		databaseLocation = new JLabel();
		databaseLabel = new javax.swing.JLabel();
		state = "contour";
		percentageRustLabel = new JLabel();
		model = new Model();
		myLabel = new javax.swing.JLabel();
        loadData = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        picLabel = new JLabel();
        detectCorrosionBlackandWhiteButton = new javax.swing.JButton();
        detectCorrosionBlackandWhiteButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        detectCorrosionColourButton = new javax.swing.JButton();
        
        
        //buffered image
        String defaultImagePath = "src/default.jpg";
        setCurrentFilePath(defaultImagePath);
        myImage = new BufferedImage(1, 1, 1);
        //image code
        //default image
        try {
        	
        
          
             
        myImage= ImageIO.read(new File(defaultImagePath));
        System.out.println(myImage.getGraphics());

        } catch (Exception e)
        {
        	System.out.println("Failed to print out image");
        
        }
        
        myPic = new ImageIcon(myImage);
     
        
        picLabel.setIcon(myPic);
        picLabel.setSize(20, 20);
        

       
        jScrollPane1.setViewportView(picLabel);
        
        
        
        databaseLabel.setText("Data Saved - IMPLEMENT THIS LATER:");
        //try to find the document, if it is real then yay, if not then no!
        
        //keep
        final JFileChooser temp = new JFileChooser();
        databaseLocation.setText(temp.getFileSystemView().getDefaultDirectory().toString());
        System.out.println("Default directory is : " + temp.getFileSystemView().getDefaultDirectory().toString());
        model.setDataBaseLocation(databaseLocation.getText());
       //
        
		directoryChosenString = databaseLocation.getText();
		//escape backslashes
	//	directoryChosenString.replace("\\","\\\\");
					
		model.setDatabaseDirectory(directoryChosenString);
		directoryChosen = true;
		model.setDirectoryChosen(directoryChosen);
		//set the database label to the directory.
		
		databaseLocation.setText(directoryChosenString);
        
        
        
        
        
        
        
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName("myFrame"); // NOI18N

        myLabel.setText("Ok, the text is currently not loaded...");

        loadData.setText("Load Image File");
        loadData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loadDataMouseClicked(evt);
            }
        });

     
        detectCorrosionBlackandWhiteButton.setText("Convert to Grey Scale");
        detectCorrosionBlackandWhiteButton.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GreyScaleMouseClicked(evt);
            }
        });

        detectCorrosionColourButton.setText("Colour Detection");
        detectCorrosionColourButton.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ColourMouseClicked(evt);
            }
        });
        
        
        
        
        
        
        
        JLabel rustDefinition = new JLabel("New label");
        
        JLabel officialRustPercentageLabel = new JLabel("New label");
        
        JLabel lblNewLabel = new JLabel("New label");
        
        JButton btnSelectDatabaseFolder = new JButton("Select Database Folder");
        btnSelectDatabaseFolder.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	loadDirectoryMouseClicked(evt);
            }
        });
        
        
        
        
        
        
        
        
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(databaseLocation, GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
        					.addGap(34)
        					.addComponent(btnSelectDatabaseFolder)
        					.addGap(131))
        				.addGroup(Alignment.LEADING, layout.createSequentialGroup()
        					.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 595, Short.MAX_VALUE)
        					.addContainerGap())
        				.addGroup(Alignment.LEADING, layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(myLabel, GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE)
        						.addComponent(databaseLabel))
        					.addGap(0))
        				.addComponent(lblNewLabel, Alignment.LEADING)
        				.addGroup(Alignment.LEADING, layout.createSequentialGroup()
        					.addComponent(rustDefinition)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(officialRustPercentageLabel))
        				.addGroup(Alignment.LEADING, layout.createSequentialGroup()
        					.addComponent(loadData, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(detectCorrosionColourButton)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(detectCorrosionBlackandWhiteButton)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(percentageRustLabel, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
        					.addContainerGap())))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(myLabel)
        			.addComponent(databaseLabel)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(databaseLocation, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnSelectDatabaseFolder, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        				.addComponent(percentageRustLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(loadData, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(detectCorrosionColourButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(detectCorrosionBlackandWhiteButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        			.addGap(18)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(rustDefinition)
        				.addComponent(officialRustPercentageLabel))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(lblNewLabel)
        			.addContainerGap())
        );
        getContentPane().setLayout(layout);
        
     //   Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      
        this.setSize(631, 599);
    }// </editor-fold>         
	
	
	
	
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
    			
    			databaseLocation.setText(directoryChosenString);}
		  } catch (Exception er) {
	            System.out.println("Some has gone wrong with the loadDataMouseClick");
		  }
	}

    private void loadDataMouseClicked(java.awt.event.MouseEvent evt) {                                      
        // TODO add your handling code here:
        try {
        	
        	
            String data = controller.getMessage();
            myLabel.setText(data);
            myLabel.setVisible(true);
            boolean flag = true;
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
            			System.out.println(newFile.toString());
            			myImage= ImageIO.read(new File(newFile.toString()));
            			setCurrentFilePath(newFile.toString());
            			
            			//myPic.setImage(myImage);
            			//SCALE THE PIC BUT IT SHOULD NOT GO BIGGER THAN THE HEIGHT/WIDTH.
            			picLabel.setSize(jScrollPane1.getWidth()-10, jScrollPane1.getHeight()-10);
            			//reduce size of image
            			myPic.setImage(myImage.getScaledInstance(picLabel.getWidth(), picLabel.getHeight(), Image.SCALE_SMOOTH));
            			
            			
            		//	picLabel.setVisible(false);
            			picLabel.setIcon(myPic);
            			
            			picLabel.setVisible(true);
            			state = "contour";
            			percentageRustLabel.setVisible(false);
            		  //  this.pack();
            	
            			//IF STATEMENT: IF WINDOW IS TOO SMALL, MAKE IT BIGGER
            			//IF PICLABEL IS BIGGER THAN JSCROLLPANE1 MAKE THIS.SETSIZE BIGGER
            			//jScrollPane1.setSize(myImage.getWidth(), myImage.getHeight());
            		//	this.setSize(myImage.getWidth(), myImage.getHeight());
            			
            		//	this.pack();
            		}
            		
            	}
        } catch (Exception er) {
            System.out.println("Some has gone wrong with the loadDataMouseClick");
        }
        
    }                                     

    private void GreyScaleMouseClicked(java.awt.event.MouseEvent evt) {                                       
        // TODO add your handling code here:
        Image imageToEdit = myPic.getImage();
		System.out.println(getCurrentFilePath());
		model.ChangeToGreyScale(getCurrentFilePath());
     
    }       
    
    private void ColourMouseClicked(java.awt.event.MouseEvent evt) {                                       
        // TODO add your handling code here:
    	
    	
        if (state == "contour"){
        System.out.println("DO THE CONTOUR THING");
        Image imageToEdit = myPic.getImage();
		System.out.println("my current file path is: " + getCurrentFilePath());
		model.detectRed(getCurrentFilePath());
		//set image to contour colour
		try{
			
	//TODO
	//IF FILE HAS BEEN DRAWN, THEN JUST READ!
		System.out.println("Trying to Read File Path: " + directoryChosenString + "\\" + model.sendCutString(currentFilePath) +"_contouredColour.jpg");
		Image contouredImage = ImageIO.read(new File(directoryChosenString + "\\" + model.sendCutString(currentFilePath) +"_contouredColour.jpg"));
		//System.out.println(getCurrentFilePath()+"/contouredColour.jpg");
	//	myPic.setImage(contouredImage);
		System.out.println("did i make it");
		picLabel.setVisible(false);
		picLabel.setSize(jScrollPane1.getWidth()-10, jScrollPane1.getHeight()-10);
		myPic.setImage(contouredImage.getScaledInstance(picLabel.getWidth(), picLabel.getHeight(), Image.SCALE_SMOOTH));
		
		picLabel.setIcon(myPic);
		picLabel.setVisible(true);
		setState("contrast");
		//set the piclabel size
		
		//reduce size of image
		
		
		percentageRustLabel.setText(Double.toString((model.getRustPercentage())));
		percentageRustLabel.setVisible(true);
	   // pack();

		
		}catch(Exception e)
		{
			//caught
		
			System .out.println("CAUGHT tried to CONTOUR from : " + e.toString());
			System.out.println(model.sendCutString(currentFilePath)+"/contouredColour.jpg");
			
		}
        }
		
        //CONTRAST
        else 
		{
			//do this
			System.out.println("DO THE CONTRAST THING");
			Image imageToEdit = myPic.getImage();
			System.out.println(getCurrentFilePath());
			model.detectRed(getCurrentFilePath());
			//set image to contour colour
			try{
				
			Image contouredImage = ImageIO.read(new File(directoryChosenString + "\\" + model.sendCutString(currentFilePath) +"_detectedColour.jpg"));
			//System.out.println(getCurrentFilePath()+"/contouredColour.jpg");
	//		myPic.setImage(contouredImage);
			picLabel.setSize(jScrollPane1.getWidth()-10, jScrollPane1.getHeight()-10);
			myPic.setImage(contouredImage.getScaledInstance(picLabel.getWidth(), picLabel.getHeight(), Image.SCALE_SMOOTH));
			picLabel.setVisible(false);
			picLabel.setIcon(myPic);
			picLabel.setVisible(true);
			setState("contour");
			 
			
			}catch(Exception e)
			{
				//caught
			
				System .out.println("CAUGHT tried to CONTRAST exception: " + e.toString());
				System.out.println(model.sendCutString(currentFilePath)+"/detectedColour.jpg");
				
			}
	        }
    //    pack();
    } 
    
    
    private String getCurrentFilePath()
    {
    	return this.currentFilePath;
    }
    private void setCurrentFilePath(String s)
    {
    	this.currentFilePath=s;
    }

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
    private javax.swing.JButton detectCorrosionBlackandWhiteButton;
    private javax.swing.JButton detectCorrosionColourButton;
    private JLabel picLabel;
    private ImageIcon myPic;
    private BufferedImage myImage;
}
