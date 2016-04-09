package View;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Controller.Controller;
import Model.Model;

public class View extends JFrame {
	Model model = new Model();
	private Controller controller = new Controller();
	private String currentFilePath;

	public View()
	{
		initialize();
	}
	
	private void initialize()
	{ 
		model = new Model();
		myLabel = new javax.swing.JLabel();
        loadData = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        picLabel = new JLabel();
        detectCorrosionButton = new javax.swing.JButton();
        
        //bufferd image
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
        
        
        
        
        
        
        
        
        
        
        
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName("myFrame"); // NOI18N

        myLabel.setText("Ok, the text is currently not loaded...");

        loadData.setText("Load Data");
        loadData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loadDataMouseClicked(evt);
            }
        });

     
        detectCorrosionButton.setText("Convert to Grey Scale");
        detectCorrosionButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GreyScaleMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(myLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(loadData)
                        .addGap(158, 158, 158))))
            .addGroup(layout.createSequentialGroup()
                .addGap(154, 154, 154)
                .addComponent(detectCorrosionButton)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(myLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loadData)
                .addGap(39, 39, 39)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(detectCorrosionButton)
                .addContainerGap(82, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

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
            		int response = fc.showOpenDialog(this);
            		if (response == JFileChooser.APPROVE_OPTION){
            			File newFile = fc.getSelectedFile();
            			//open the file here and update the picture!
            			myLabel.setText(newFile.getName());
            			System.out.println(newFile.toString());
            			myImage= ImageIO.read(new File(newFile.toString()));
            			setCurrentFilePath(newFile.toString());
            			myPic.setImage(myImage);
            			picLabel.setVisible(false);
            			picLabel.setIcon(myPic);
            			picLabel.setVisible(true);
            			
            			
            			
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

    // Variables declaration - do not modify                     
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton loadData;
    public javax.swing.JLabel myLabel;
    private javax.swing.JButton detectCorrosionButton;
    private JLabel picLabel;
    private ImageIcon myPic;
    private BufferedImage myImage;

    // End of variables declaration                   

	
	
	
}
