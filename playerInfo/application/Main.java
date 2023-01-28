package application;
	
import heritage.Player;

import java.util.ArrayList;

import heritage.DBConnection;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		DBConnection.init();
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			
			ArrayList<Player> Players = Player.load();
			
			VBox vbox = new VBox();
			
			for(Player p : Players) {
				Button btn = new Button("NHL Player: "+p.getLast()+ ", " + p.getFirst());
				
				btn.setOnAction((ae)->{
					Alert dlgInfo = new Alert(AlertType.INFORMATION);

					dlgInfo.setTitle("In Details");
					dlgInfo.setHeaderText(p.getFirst()+" "+p.getLast());
					dlgInfo.setContentText(p.toString());					
					dlgInfo.showAndWait();
				});
				
				vbox.getChildren().add(btn);			
			}
			root.setTop(vbox);
				
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void stop() {
		DBConnection.close();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
