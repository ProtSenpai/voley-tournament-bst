package ui;


import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
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
         FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("", "*.csv");
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
			loadDataLabel.setText("Potential spectators have been successfully loaded");
		} catch (IOException e) {
			e.printStackTrace();
			loadDataLabel.setText("An error has occurred loading the file ");
		}

    }

    @FXML
    public void searchParticipantButton(ActionEvent event) {

    }

    @FXML
    public void searchSpectatorButton(ActionEvent event) {

    }

    @FXML
    public void showParticipants(ActionEvent event) {

    }

    @FXML
    public void showSpectators(ActionEvent event) {

    }


}
