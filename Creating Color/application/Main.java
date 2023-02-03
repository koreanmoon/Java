package application;

import java.util.ArrayList;
import java.util.Random;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {

		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);

			HBox menu = new HBox();
			Button btn = new Button("Add New Colour");
			Button btn1 = new Button("Clear Colours");

			TilePane TP = new TilePane();

			menu.getChildren().add(btn);
			menu.getChildren().add(btn1);
			root.setTop(menu);
			root.setCenter(TP);

			Random rnd = new Random();

			btn.setOnAction((ae)-> {
				for(int i=0; i<10; i++) {
					Rectangle rectangle = new Rectangle(25,25,Color.rgb(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
					TP.getChildren().add(rectangle);					
				}				
			});

			btn1.setOnAction((ae)-> {
				TP.getChildren().clear();
			});

			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
