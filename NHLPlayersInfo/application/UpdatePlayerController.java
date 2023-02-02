package application;

import java.io.IOException;
import java.sql.SQLException;

import heritage.model.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class UpdatePlayerController {

	private Stage stage;
	private Scene scene;
	private Parent root;
	private Player player;
	private Main main;

	public UpdatePlayerController(Player p,Main parent) throws IOException {

		stage = new Stage();
		player = p;
		main = parent;
                 
		FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdatePlayerView.fxml"));
		loader.setController(this);

		root = loader.load();
		Scene scene = new Scene(root, 300, 250);
		
		txtFirst.setText(player.getFirst());
		txtLast.setText(player.getLast());
		slAssists.setValue(player.getNumAssists());
		slGoals.setValue(player.getNumGoals());
		slGP.setValue(player.getNumGamesPlayed());
		
		onAssistsDrag(null);
		onGoalsDrag(null);
		onGPsDrag(null);
		

		stage.setScene(scene);
		stage.show();
		
		slAssists.addEventHandler(MouseEvent.MOUSE_DRAGGED, this::onAssistsDrag );
		slGP.addEventHandler(MouseEvent.MOUSE_DRAGGED, this::onGPsDrag );
		slGoals.addEventHandler(MouseEvent.MOUSE_DRAGGED, this::onGoalsDrag );

	}

	@FXML
	private Label lblAssists;

	@FXML
	private Label lblGP;

	@FXML
	private Label lblGoals;

	@FXML
	private Button btnCancel;

	@FXML
	private Button btnOk;

	@FXML
	private Slider slAssists;

	@FXML
	private Slider slGP;

	@FXML
	private Slider slGoals;

	@FXML
	private TextField txtFirst;

	@FXML
	private TextField txtLast;


	void onAssistsDrag(MouseEvent event) {
		lblAssists.setText(Math.round(slAssists.getValue())+"");
	}
	void onGPsDrag(MouseEvent event) {
		lblGP.setText(Math.round(slGP.getValue())+"");
	}
	void onGoalsDrag(MouseEvent event) {
		lblGoals.setText(Math.round(slGoals.getValue())+"");
	}

	@FXML
	void btnCancelClick(ActionEvent event) {
		stage.close();
	}

	@FXML
	void btnOkClick(ActionEvent event) {

		String last = txtLast.getText(), first = txtFirst.getText();
		int gp = (int) Math.round(slGP.getValue());
		int goals = (int) Math.round(slGoals.getValue());
		int assists = (int) Math.round(slAssists.getValue());

		player.setFirst(first);
		player.setLast(last);
		player.setNumAssists(assists);
		player.setNumGamesPlayed(gp);
		player.setNumGoals(goals);
		
		try {
			player.update();
			main.refreshPlayers();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		stage.close();

	}
}

