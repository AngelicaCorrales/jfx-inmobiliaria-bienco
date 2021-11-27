package ui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;


import exceptions.NegativeValueException;
import exceptions.NoValueException;
import exceptions.SimpleGraphException;
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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Bienco;
import model.Building;

public class BiencoGUI {

	private Bienco bienco;

	@FXML
	private BorderPane mainPane;

	//------addDistanceBetweeNearbyProperties.fxml
	@FXML
	private ComboBox<Building> cBoxChoiceDistance1;

	@FXML
	private TextField txtFDistanceInM;

	@FXML
	private ComboBox<Building> cBoxChoiceDistance2;

	@FXML
	private TextArea taFFinalDistance;


	//------filter-buildings.fxml

	@FXML
	private TableView<Building> tvOfFoundedBuildings;

	@FXML
	private TableColumn<Building, String> tcAddressFilter;

	@FXML
	private TableColumn<Building, String> tcNbdFilter;

	@FXML
	private TableColumn<Building, String> tcZoneFilter;

	@FXML
	private TableColumn<Building, String> tcTypeFilter;

	@FXML
	private TableColumn<Building, Double> tcPriceFilter;

	@FXML
	private TableColumn<Building, String> tcVorAFilter;

	@FXML
	private TableColumn<Building, String> tcObsFilter;

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
	private TableView<Building> tvOfAddedBuildings;
	
	@FXML
	private Button btUpdate;

	@FXML
	private Button btDelete;

	@FXML
	private Button btAdd;
	
	@FXML
	private TextField txtAddress;

	@FXML
	private TextField txtPrice;

	@FXML
	private TextArea txaObs;

	@FXML
	private TableColumn<Building, String> tcAddress;

	@FXML
	private TableColumn<Building, String> tcNbd;

	@FXML
	private TableColumn<Building, String> tcZone;

	@FXML
	private TableColumn<Building, String> tcType;

	@FXML
	private TableColumn<Building, Double> tcPrice;

	@FXML
	private TableColumn<Building, String> tcVorA;

	@FXML
	private TableColumn<Building, String> tcObs;


	//------routes.fxml
	@FXML
	private ComboBox<Building> cmbBuildings;

	@FXML
	private TextArea taRoutes;
	//--------------------------------------


	public BiencoGUI(Bienco b) {
		bienco=b;
	}



	@FXML
	public void calculateRoute(ActionEvent event) {
		if(cmbBuildings.getValue()!=null) {
			taRoutes.setText(bienco.calculateRoute(cmbBuildings.getValue()));

		}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("Debe elegir la direccion desde la cual planea iniciar su recorrido.");
			alert.showAndWait();
		}
	}

	private void initializeComboBoxBuildingsFindRoutes() {
		ObservableList<Building> options =FXCollections.observableArrayList(bienco.getFilterBuildings());
		cmbBuildings.setItems(options);
	}



	@FXML
	public void downloadReport(ActionEvent event) {

	}































	private void initializeComboBoxDistances() {
		ObservableList<Building> options =FXCollections.observableArrayList(bienco.getFilterBuildings());
		cBoxChoiceDistance1.setItems(options);
                cBoxChoiceDistance2.setItems(options);
	}
        
        @FXML
	public void nextScreenAddDistances(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/addDistanceBetweeNearbyProperties.fxml"));
		fxmlLoader.setController(this);
		Parent menuPane = fxmlLoader.load();
		mainPane.setCenter(menuPane);
                initializeComboBoxDistances();
	}

	@FXML
	public void addDistances(ActionEvent event) throws SimpleGraphException {
            if(cBoxChoiceDistance1.getValue()!=null && cBoxChoiceDistance2.getValue()!=null && !txtFDistanceInM.getText().equals("")) {
                Optional<ButtonType> result = askToContinue();
                Alert alert1 = new Alert(AlertType.INFORMATION);
                alert1.setTitle("Error de validacion");
                alert1.setHeaderText(null);
                
                try {
                    if (result.get() == ButtonType.OK){
                        if(cBoxChoiceDistance1.getValue()==cBoxChoiceDistance2.getValue()){
                            alert1.setContentText("No puede elegir la misma distancia, por favor seleccione una distancia diferente");
                            alert1.showAndWait();
                        }
                        else{
                            taFFinalDistance.setText(bienco.addDistancesBetweenProperties(cBoxChoiceDistance1.getValue(), cBoxChoiceDistance2.getValue(), txtFDistanceInM.getText()));
                            alert1.setContentText("Distancia agregada exitosamente entre los dos inmuebles");
                            alert1.showAndWait();
                            txtFDistanceInM.setText("");
                        }
                    }
                } catch (SimpleGraphException ge) {
                    alert1.setContentText("No debe agregar mas de una arista, el grafo de ser de tipo: Grafo Simple. Intente de nuevo por favor");
                    alert1.showAndWait();
                }
            }
            else {
                showValidationErrorAlert();
            }
	}

	@FXML
	public void nextPageRoutes(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/routes.fxml"));
		fxmlLoader.setController(this);
		Parent menuPane = fxmlLoader.load();
		mainPane.setCenter(menuPane);
		initializeComboBoxBuildingsFindRoutes();
	}




























	@FXML
	public void clickOnTVofFoundedBuildings(MouseEvent event) {

	}

	@FXML
	public void filterBuildings(ActionEvent event) {

	}

	@FXML
	public void returnToMenu(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/homePage.fxml"));
		fxmlLoader.setController(this);
		Parent menuPane = fxmlLoader.load();
		mainPane.setCenter(menuPane);

	}
























	private void initializeTableViewOfFoundedBuildings(ArrayList<Building> buildings) {
		ObservableList<Building> observableList;
		observableList = FXCollections.observableArrayList(buildings);
		tvOfAddedBuildings.setItems(observableList);
		tcAddress.setCellValueFactory(new PropertyValueFactory<Building, String>("Address"));
		tcNbd.setCellValueFactory(new PropertyValueFactory<Building, String>("Neighborhood"));
		tcZone.setCellValueFactory(new PropertyValueFactory<Building, String>("Zone"));
		tcType.setCellValueFactory(new PropertyValueFactory<Building, String>("Type"));
		tcPrice.setCellValueFactory(new PropertyValueFactory<Building, Double>("Price"));
		tcVorA.setCellValueFactory(new PropertyValueFactory<Building, String>("Purpose"));
		tcObs.setCellValueFactory(new PropertyValueFactory<Building, String>("Observations"));
	}


	@FXML
	public void filterPropertyDefineRoute(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/filter-buildings.fxml"));
		fxmlLoader.setController(this);
		Parent menuPane = fxmlLoader.load();
		mainPane.setCenter(menuPane);
		initializeCmbxOfZone();
		initializeCmbxOfTB();
	}

	@FXML
	public  void importProperty(ActionEvent event) throws IOException {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(null);

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Abrir el archivo");
		File f=fileChooser.showOpenDialog(mainPane.getScene().getWindow());
		if(f!=null) {
			alert.setTitle("Importar inmuebles");
			try {
				bienco.importData(f.getAbsolutePath());

				alert.setContentText("Los inmuebles fueron importados exitosamente");
				alert.showAndWait();

				bienco.saveDataBienco();
			}catch(IOException e){
				alert.setContentText("Los inmuebles no se importaron.");
				alert.showAndWait();
			}
		}
	}

	@FXML
	public void manageProperty(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/manage-building.fxml"));
		fxmlLoader.setController(this);
		Parent menuPane = fxmlLoader.load();
		mainPane.setCenter(menuPane);
		initializeTableViewOfAddedPlayers();
		initializeCmbxOfZone();
		initializeCmbxOfTB();
	}









	private void initializeTableViewOfAddedPlayers() {
		ObservableList<Building> observableList;
		observableList = FXCollections.observableArrayList(bienco.getBuildings());
		tvOfAddedBuildings.setItems(observableList);
		tcAddress.setCellValueFactory(new PropertyValueFactory<Building, String>("Address"));
		tcNbd.setCellValueFactory(new PropertyValueFactory<Building, String>("Neighborhood"));
		tcZone.setCellValueFactory(new PropertyValueFactory<Building, String>("Zone"));
		tcType.setCellValueFactory(new PropertyValueFactory<Building, String>("Type"));
		tcPrice.setCellValueFactory(new PropertyValueFactory<Building, Double>("Price"));
		tcVorA.setCellValueFactory(new PropertyValueFactory<Building, String>("Purpose"));
		tcObs.setCellValueFactory(new PropertyValueFactory<Building, String>("Observations"));
		tvOfAddedBuildings.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
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

	public boolean getRadioButtonSaleOrRent() {
		boolean option =false;
		if(rbSale.isSelected()) {
			option = true;
		} 
		return option;
	}

	@FXML
	public void addBuilding(ActionEvent event) {
		if(!txtAddress.getText().equals("") && !txtNbd.getText().equals("") && cbxZone.getValue()!=null && cbxType.getValue()!=null && !txtPrice.getText().equals("") && VorA.getSelectedToggle()!=null && !txaObs.getText().equals("")) {
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
		initializeTableViewOfAddedPlayers();
		initializeCmbxOfZone();
		initializeCmbxOfTB();
		txtAddress.clear();
		txtNbd.clear();
		txtPrice.clear();
		txaObs.clear();
		VorA.getSelectedToggle().setSelected(false);
	}

	@FXML
	public void clickOnTVofAddedBuildings(MouseEvent event) {
		Building selectedBuilding = tvOfAddedBuildings.getSelectionModel().getSelectedItem();
		if (selectedBuilding!= null) {
			btAdd.setDisable(true);
			txtAddress.setText(selectedBuilding.getAddress());
			txtNbd.setText(selectedBuilding.getNeighborhood());
			txtPrice.setText(""+selectedBuilding.getPrice());
			cbxZone.setValue(selectedBuilding.getZone());
			cbxType.setValue(selectedBuilding.getType());
			txaObs.setText(selectedBuilding.getObservations());
			if(selectedBuilding.isForSale()) {
				rbSale.setSelected(true);
			}else {
				rbRent.setSelected(true);	
			}
		}
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
