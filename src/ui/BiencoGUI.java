package ui;

import java.io.IOException;
import java.util.Optional;

import exceptions.NegativeValueException;
import exceptions.NoValueException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import model.TypeOfBuilding;
import model.Zone;

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
	private ComboBox<String> cbxZone;

	@FXML
	private ComboBox<String> cbxType;

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
	private TextField txtPrice;

	@FXML
	private TextArea txaObs;

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

















	private void initializeCmbxOfZone() {
		ObservableList<String> zoneList = FXCollections.observableArrayList("Sur","Norte","Centro","Este","Oeste");
		cbxZone.setItems(zoneList);
		cbxZone.setValue(null);
		cbxZone.setPromptText("Elija una Zona");
	}

	private void initializeCmbxOfTB() {
		ObservableList<String> TBList = FXCollections.observableArrayList("Apartaestudio","Apartamento","Casa","Local","Edificio", "Finca", "Lote","Oficina");
		cbxType.setItems(TBList);
		cbxType.setValue(null);
		cbxType.setPromptText("Elija un Tipo");
	}

	public String getRadioButtonSaleOrRent() {
		String option = "";
		if(rbSale.isSelected()) {
			option = "Venta";
		} else {
			option = "Alquiler";
		}
		return option;
	}

	@FXML
	public void addBuilding(ActionEvent event) {
		if(!txtAddress.getText().equals("") && !txtNbd.getText().equals("") && cbxZone.getValue()!=null && cbxType.getValue()!=null && !txtPrice.getText().equals("") && !getRadioButtonSaleOrRent().equals("") && !txaObs.getText().equals("")) {
			Alert alert1 = new Alert(AlertType.INFORMATION);
			alert1.setTitle("Error de validacion");
			alert1.setHeaderText(null);
			try {
				boolean added = bienco.addBuilding(txtAddress.getText(),txtNbd.getText(),cbxZone.getValue(),cbxType.getValue(),txtPrice.getText(),getRadioButtonSaleOrRent(),txaObs.getText());
				if(added==false) {
					alert1.setContentText("El inmueble ha sido agregado exitosamente");
					alert1.showAndWait();
				}else {
					alert1.setContentText("Ya existe un inmueble con la direccion ingresada, intentelo nuevamente");
					alert1.showAndWait();
				}
			} catch (NoValueException nv) {
				alert1.setContentText(nv.getMessage());
				alert1.showAndWait();
			} catch (NegativeValueException e) {
				alert1.setContentText(e.getMessage());
				alert1.showAndWait();
			}catch(NumberFormatException num) {
				alert1.setContentText("Debe ingresar numeros dentro de los campos presentados que asi lo requieran");
				alert1.showAndWait();
			}
		}else {
			showValidationErrorAlert();
		}
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
		alert.setContentText("¿Esta seguro que desea continuar? Recuerde que no podra realizar ningun cambio despues.");
		Optional<ButtonType> result = alert.showAndWait();
		return result;
	}




}
