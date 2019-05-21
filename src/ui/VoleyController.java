package ui;


import java.io.File;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Participant;
import model.VoleyTournament;

public class VoleyController {

    @FXML
    private TextField directoryDataField;

    @FXML
    private Label loadDataLabel;

    @FXML
    private TextField idSearchField;

    @FXML
    private Label timeLabel;

    @FXML
    private Label foundSpectatorLabel;

    @FXML
    private TextField idParticipantField;

    @FXML
    private Label timeParticipant;

    @FXML
    private Label foundParticipantLabel;

    @FXML
    private ImageView imageView;

    @FXML
    private Label informationFoundLabel;

    @FXML
    private Label loadDataLabel1;
    
    //Relation
    
    VoleyTournament tournament= new VoleyTournament();

    @FXML
    public void exploreDataButton(ActionEvent event) {
    	
    	 FileChooser chooser = new FileChooser();
         FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Select a file (.csv)", "*.csv");
         chooser.getExtensionFilters().add(filter);
         File file = chooser.showOpenDialog(new Stage());
         if ( file !=null){
        	 
        	 directoryDataField.setText(file.getPath().toString());
         }

    }

    @FXML
    public void loadDataButton(ActionEvent event) {
    	
    	try {
			tournament.LoadFileAndAddToTree(directoryDataField.getText());
			
			loadDataLabel.setText("Potential spectators have been successfully" + "\n" + "loaded");
		} catch (IOException e) {
			e.printStackTrace();
			loadDataLabel.setText("An error has occurred loading the file ");
		} 
    	

    }

    @FXML
    public void searchParticipantButton(ActionEvent event) {
    	try {
    		long startTime = System.currentTimeMillis();
    		
    		
    		int id = Integer.parseInt(idParticipantField.getText());
    		Participant part;
    		Image image; 
    		
    		part = tournament.searchParticipant(id);
    		
    		if(part.getGender().equals("Male")) {
        		image=new Image(new File("images/avatar-man.png").toURI().toString());
        		imageView.setImage(image);
        	} else {
        		image=new Image(new File("images/avatar-woman.png").toURI().toString());
        		imageView.setImage(image);
        	}
        	
        	informationFoundLabel.setText("The name is: " + part.getFirstName() + part.getLastName() + "\n" + "The email is: "
        	+ part.getEmail() + "\n" + "The gender is: " + part.getGender() + "\n" + "The Country is: " + part.getCountry());
        	
        	foundSpectatorLabel.setText("The spectator was found");
    		
    		long endTime = System.currentTimeMillis();
        	long duration = (endTime - startTime);
        	timeParticipant.setText(duration+""+ " ms");
        	
    	} catch(NullPointerException np) {
    		
    		foundParticipantLabel.setText("The participant with id: "+ idParticipantField.getText() + " wasn't found.");
    	} catch(NumberFormatException nf) {
    		
    		foundParticipantLabel.setText("Please introduce a valid number.");
    	}
    }

    @FXML
    public void searchSpectatorButton(ActionEvent event) {
    	try {
    	long startTime = System.currentTimeMillis();
 
    	Participant found;
    	Image image;
    	
    	found = tournament.searchSpectador(Integer.parseInt(idSearchField.getText()));
    	
    	
    	if(found.getGender().equals("Male")) {
    		image=new Image(new File("images/avatar-man.png").toURI().toString());
    		imageView.setImage(image);
    	} else {
    		image=new Image(new File("images/avatar-woman.png").toURI().toString());
    		imageView.setImage(image);
    	}
    	
    	informationFoundLabel.setText("The name is: " + found.getFirstName() + found.getLastName() + "\n" + "The email is: "
    	+ found.getEmail() + "\n" + "The gender is: " + found.getGender() + "\n" + "The Country is: " + found.getCountry());
    	
    	foundSpectatorLabel.setText("The spectator was found");
    	
    	long endTime = System.currentTimeMillis();
    	long duration = (endTime - startTime);
    	timeLabel.setText(duration+""+ " ms");
    	
    	} catch (NullPointerException e) {
    		
    		foundSpectatorLabel.setText("not know found the spectator with the id " + idSearchField.getText());
		}
    	
    }

    @FXML
    public void showParticipants(ActionEvent event) throws IOException {
    
    }

    @FXML
    public void showSpectators(ActionEvent event) {

    }
    
    @FXML
    public void addParticipants(ActionEvent event) {
    	
    }


}
