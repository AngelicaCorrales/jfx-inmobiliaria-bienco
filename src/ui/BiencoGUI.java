package ui;

import java.io.IOException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Alert.AlertType;
import model.Bienco;

public class BiencoGUI {

	private Bienco bienco;

	@FXML
    private BorderPane mainPane;
	
	//------addDistanceBetweeNearbyProperties.fxml
	@FXML
	private ComboBox<?> cBoxChoiceDistance1;

	@FXML
	private TextField txtFDistanceInM;

	@FXML
	private ComboBox<?> cBoxChoiceDistance2;

	@FXML
	private TextArea taFFinalDistance;


	//------filter-buildings.fxml

	@FXML
	private TableView<?> tvOfFoundedBuildings;

	@FXML
	private TableColumn<?, ?> tcAddressFilter;

	@FXML
	private TableColumn<?, ?> tcNbdFilter;

	@FXML
	private TableColumn<?, ?> tcZoneFilter;

	@FXML
	private TableColumn<?, ?> tcTypeFilter;

	@FXML
	private TableColumn<?, ?> tcPriceFilter;

	@FXML
	private TableColumn<?, ?> tcVorAFilter;

	@FXML
	private TableColumn<?, ?> tcObsFilter;
	@FXML
	private TextField txtNbd;

	@FXML
	private ComboBox<?> cbxZone;

	@FXML
	private ComboBox<?> cbxType;

	@FXML
	private TextField txtFromPrice;

	@FXML
	private RadioButton rbSale;

	@FXML
	private ToggleGroup VorA;

	@FXML
	private RadioButton rbRent;

	@FXML
	private TextField txtToPrice;


	//------manage-building.fxml

	@FXML
	private TableView<?> tvOfAddedBuildings;


	@FXML
	private TextField txtAddress;


	@FXML
	private TableColumn<?, ?> tcAddress;

	@FXML
	private TableColumn<?, ?> tcNbd;

	@FXML
	private TableColumn<?, ?> tcZone;

	@FXML
	private TableColumn<?, ?> tcType;

	@FXML
	private TableColumn<?, ?> tcPrice;

	@FXML
	private TableColumn<?, ?> tcVorA;

	@FXML
	private TableColumn<?, ?> tcObs;


	//------routes.fxml
	@FXML
	private ComboBox<?> cmbBuildings;

	@FXML
	private TextArea taRoutes;
	//--------------------------------------


	public BiencoGUI(Bienco b) {
		bienco=b;
	}



	@FXML
	public void calculateRoute(ActionEvent event) {

	}


	@FXML
	public void downloadReport(ActionEvent event) {

	}
































	@FXML
	public void addDistances(ActionEvent event) {

	}

	@FXML
	public void nextPageRoutes(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/routes.fxml"));
		fxmlLoader.setController(this);
		Parent menuPane = fxmlLoader.load();
		mainPane.setCenter(menuPane);
	}




























	@FXML
	public void clickOnTVofFoundedBuildings(MouseEvent event) {

	}

	@FXML
	public void filterBuildings(ActionEvent event) {

	}

	@FXML
	public void nextScreen(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/addDistanceBetweeNearbyProperties.fxml"));
		fxmlLoader.setController(this);
		Parent menuPane = fxmlLoader.load();
		mainPane.setCenter(menuPane);
	}

	@FXML
	public void returnToMenu(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/homePage.fxml"));
		fxmlLoader.setController(this);
		Parent menuPane = fxmlLoader.load();
		mainPane.setCenter(menuPane);
		
	}



































	@FXML
	public void filterPropertyDefineRoute(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/filter-buildings.fxml"));
		fxmlLoader.setController(this);
		Parent menuPane = fxmlLoader.load();
		mainPane.setCenter(menuPane);
	}

	@FXML
	public  void importProperty(ActionEvent event) throws IOException {
		
	}

	@FXML
	public void manageProperty(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/manage-building.fxml"));
		fxmlLoader.setController(this);
		Parent menuPane = fxmlLoader.load();
		mainPane.setCenter(menuPane);
	}




















	@FXML
	public void addBuilding(ActionEvent event) {

	}

	@FXML
	public void clickOnTVofAddedBuildings(MouseEvent event) {

	}

	@FXML
	public void deleteBuilding(ActionEvent event) {

	}



	@FXML
	public void updateBuilding(ActionEvent event) {

	}


































	public void showValidationErrorAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error de validacion");
		alert.setHeaderText(null);
		alert.setContentText("Recuerde diligenciar cada uno de los campos");
		alert.showAndWait();
	}

	public Optional<ButtonType> askToContinue() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText("�Esta seguro que desea continuar? Recuerde que no podra realizar ningun cambio despues.");
		Optional<ButtonType> result = alert.showAndWait();
		return result;
	}




}