package ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Bienco;

public class Main extends Application{

	private Bienco bienco;
	private BiencoGUI biencoGUI;
	
	public Main() {
		Bienco biencoData = new Bienco(0);
		bienco=new Bienco(0);
		
		try {
			bienco = bienco.loadDataBienco(biencoData);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Bienco S.A.S");
			alert.setHeaderText(null);
			if(!bienco.getBuildings().isEmpty()) {
				alert.setContentText("Se cargaron datos de archivo");
				alert.showAndWait();
			}
			
			
		
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Bienco S.A.S");
			alert.setHeaderText(null);
			alert.setContentText("Error al cargar datos de archivo");
			alert.showAndWait();
		} 
		
		
		biencoGUI = new BiencoGUI(bienco);
	}


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
		primaryStage.setTitle("Bienco S.A.S");
		primaryStage.show();
		biencoGUI.returnToMenu(null);

	}

}
