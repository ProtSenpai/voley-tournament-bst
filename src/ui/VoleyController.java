package ui;


import java.io.File;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
    
    @FXML
    private Pane paneShow;
    
    //Relation
    
    VoleyTournament tournament= new VoleyTournament();

	
    
    public static final int DIAMETRO = 10;
    public static final int RADIO = DIAMETRO / 2;
    public static final int ANCHO = 30;

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

    public void showParticipants(ActionEvent event) {
    	
    	paneShow.getChildren().clear();


    }

    @FXML
    public void showSpectators(ActionEvent event) {
    	
		try {
			paneShow.getChildren().clear();
			 Alert info = new Alert(AlertType.INFORMATION);
            	info.setTitle("Spectator Infotmation");
            	info.setHeaderText(null);
            	info.initStyle(StageStyle.UTILITY);
            	info.setContentText("Click on the circles to show the information ");
            	info.show();
			tournament.LoadFileAndAddToTree(directoryDataField.getText());
			
			pintar((int)paneShow.getWidth()/2, 20, tournament.getRoot());
		} catch (IOException e) {
			
			e.printStackTrace();
		}

    	

    }

	
	private void pintar(int x, int y, Participant n) {
	
		
        if (n == null){
        	
        }
        else {
        	
    
        	
            int EXTRA = 1 * (ANCHO / 2);
            Circle c= new Circle(x, y, RADIO);
            c.setOnMouseClicked(new EventHandler<MouseEvent>() {
               @Override
               public void handle(MouseEvent clicked) {
                   if(clicked.getClickCount() == 1) {
                	   Alert info = new Alert(AlertType.INFORMATION);
                   	info.setTitle("Spectator Infotmation");
                   	info.setHeaderText(null);
                   	info.initStyle(StageStyle.UTILITY);
                   	info.setContentText("The country of this spectator is " + n.getCountry());
                   	info.show();
                       
                   }
               }
           });
            paneShow.getChildren().add(c);
            
            
            
			if (n.getLeft() != null) {
              Line line = new Line(x, y, x - ANCHO - EXTRA  ,y + ANCHO);
              if(x==paneShow.getLayoutX()) {
            	  
              }else {
              line.setFill(Color.RED);
			paneShow.getChildren().add(line);
              }
			}
			
			if (n.getRigth() != null) {
	              Line line = new Line(x, y , x + ANCHO + EXTRA  ,y + ANCHO);
	              
				paneShow.getChildren().add(line);
				
			}
			
			pintar(x-ANCHO-EXTRA, y+ANCHO, n.getLeft());
			pintar(x+ANCHO+EXTRA, y+ANCHO, n.getRigth());
			
        	
        
        }
    }

    
    @FXML
    public void addParticipants(ActionEvent event) {
    	
    }



}

