package ui;

import java.io.File;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {

		public void start(Stage stage) throws Exception {
			
			Parent root = FXMLLoader.load(getClass().getResource("voley.fxml"));
			
			Scene scene = new Scene(root);
			stage.setTitle("IV Panamerican Male Volleyball Cup U-21");
			stage.setScene(scene);
			stage.getIcons().add(new Image(new File("images/icon.png").toURI().toString()));
			stage.setResizable(false);
			stage.show();
			
		}
		
		public static void main(String [] args) {
			launch(args);
		}
		
}
