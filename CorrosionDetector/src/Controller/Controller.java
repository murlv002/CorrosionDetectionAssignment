package Controller;

import java.awt.Image;

import Model.Model;
import View.View;


public class Controller {
    
	
	
    public void startApplication() {
        // View the application's GUI
        View view = new View();
        view.setVisible(true);
    }
    
    public String getMessage() {
        try {
            Model model = new Model();
            return model.getData();
        } catch (Exception er) {
            return "No Image File Loaded";
        }
    }
    /*
    public boolean writeMessage(String message) {
        try {
            Model model = new Model();
        } catch (Exception er) {
            return false;
        }
    }
    */

	public void detectCorrosion(Image imageToEdit) {

		
	}
}