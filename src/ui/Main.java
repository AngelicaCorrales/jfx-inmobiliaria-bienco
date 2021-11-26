package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Bienco;

public class Main extends Application{

	private Bienco bienco;
	private BiencoGUI biencoGUI;


	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainPane.fxml"));

		fxmlLoader.setController(biencoGUI);
		Parent root= fxmlLoader.load();

		Scene scene= new Scene(root);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Sistema FIBA");
		primaryStage.show();
		biencoGUI.returnToMenu(null);

	}

}
