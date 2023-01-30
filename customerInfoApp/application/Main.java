package application;

import java.util.ArrayList;
import heritage.Customer;
import heritage.DBconnection;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		DBconnection.init();
		
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			
			ArrayList<Customer> Customers = Customer.load();
					
			VBox vbox = new VBox();
			
			for(Customer c : Customers) {
				Button btn = new Button(c.getLast()+ ", " + c.getFirst());
				btn.setOnAction((ae)->{
					Alert dlgInfo = new Alert(AlertType.INFORMATION);

					dlgInfo.setTitle("Message");
					dlgInfo.setHeaderText(c.getFirst()+" "+c.getLast());
					dlgInfo.setContentText(c.toString());					
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
		DBconnection.close();		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
