package application;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import heritage.DBConnection;
import heritage.model.Player;
import heritage.model.Salary;
import heritage.model.Team;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class Main extends Application {

	private ChoiceBox<Player> cbPlayers;

	@Override
	public void start(Stage primaryStage) {

		DBConnection.init();

		try {

			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,700);

			VBox vbSideMenu = new VBox();

			Button btnAddPlayer = new Button("+ Player");
			Button btnUpdatePlayer = new Button("Update");
			Button btnDeletePlayer = new Button("Delete");


			btnAddPlayer.setOnAction((ae)->{

				try {
					AddPlayerController apc = new AddPlayerController(this);
				} catch (IOException e) {
					e.printStackTrace();
				}


			});


			btnDeletePlayer.setOnAction((ae2)->{

				try {
					Player p = (Player) cbPlayers.getSelectionModel().getSelectedItem();
					p.delete();
					refreshPlayers();

				} catch (SQLException e) {
					e.printStackTrace();
				}


			});


			vbSideMenu.getChildren().add(btnAddPlayer);
			vbSideMenu.getChildren().add(btnUpdatePlayer);
			vbSideMenu.getChildren().add(btnDeletePlayer);

			root.setLeft(vbSideMenu);


			ArrayList<Player> Players = Player.load();

			cbPlayers = new ChoiceBox<>();
			cbPlayers.getItems().addAll(Players);
			VBox vbPlayerDetails = new VBox();

			btnUpdatePlayer.setOnAction((ae1)->{

				try {
					Player p = (Player) cbPlayers.getSelectionModel().getSelectedItem();
					UpdatePlayerController upc = new UpdatePlayerController(p,this);

				} catch (IOException e) {
					e.printStackTrace();
				}


			});


			cbPlayers.setOnAction((ae)->{
				Player p = (Player) cbPlayers.getSelectionModel().getSelectedItem();
				if (p!=null) {


					ObservableList<Node> vChildren =vbPlayerDetails.getChildren(); 
					vChildren.clear();

					vChildren.add(new Text(p.toString()));

					vChildren.add(new Text("Goals: " + p.getNumGoals()));
					vChildren.add(new Text("Assists: " + p.getNumAssists()));

					Text txtTeams = new Text();

					try {
						ArrayList<Team> pTeams = p.getTeams();

						for(Team t : pTeams) {

							txtTeams.setText(txtTeams.getText() + "\n"+t.getName());

						}
						vChildren.add(txtTeams);

					} catch (SQLException e) {
						e.printStackTrace();
					}
					vChildren.add(new Text("\n"));
					vChildren.add(new Text("Ordered By Salary ( from high to low ) \n"));

					try {
						ArrayList<Salary> Salarys = Salary.loadForSalary();

						for(Salary s : Salarys) {
							Button btns1 = new Button(s.getLast()+", "+s.getFirst());
							btns1.setOnAction((ae1)->{
								Alert dlgInfo = new Alert(AlertType.INFORMATION);

								dlgInfo.setTitle("Message");
								dlgInfo.setHeaderText(null);
								dlgInfo.setContentText(s.toString());					
								dlgInfo.showAndWait();

							});
							vChildren.add(btns1);
						}

					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			});


			cbPlayers.getSelectionModel().selectFirst();


			root.setTop(cbPlayers);
			root.setCenter(vbPlayerDetails);

			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

	public void refreshPlayers()  {


		ArrayList<Player> Players;
		try {
			cbPlayers.getSelectionModel().clearSelection();
			Players = Player.load();
			cbPlayers.getItems().clear();
			cbPlayers.getItems().addAll(Players);
			cbPlayers.getSelectionModel().selectFirst();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
