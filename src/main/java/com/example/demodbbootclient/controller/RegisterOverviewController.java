package com.example.demodbbootclient.controller;

import com.example.demodbbootclient.App;
import com.example.demodbbootclient.entity.Register;
import com.example.demodbbootclient.utils.HTTPUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Data;

import java.io.IOException;

import static com.example.demodbbootclient.controller.RegisterOverviewController.registerData;
import static com.example.demodbbootclient.controller.RegisterOverviewController.updateRegister;

@Data
public class RegisterOverviewController {
    public static String api = "http://localhost:2825/api/v1/register/";
    public static ObservableList<Register> registerData = FXCollections.observableArrayList();
    static HTTPUtils http = new HTTPUtils();
    static Gson gson = new Gson();

    @FXML
    public TableView<Register> tableRegister;
    @FXML
    private TableColumn<Register, String> numFlight;
    @FXML
    private TableColumn<Register, String> trip;
    @FXML
    private TableColumn<Register, String> stopoverPoints;
    @FXML
    private TableColumn<Register, String> timeFlight;
    @FXML
    private TableColumn<Register, String> dayFlight;
    @FXML
    private TableColumn<Register, String> availabilitySeatsFlight;

    @FXML
    private void initialize() throws Exception {
        //System.out.println(http.get("http://localhost:2825","/api/v1/register/all"));
        getData();
        updateData();
    }

    private void updateData() throws Exception {
        numFlight.setCellValueFactory(new PropertyValueFactory<Register,String>("numFlight"));
        trip.setCellValueFactory(new PropertyValueFactory<Register,String>("trip"));
        stopoverPoints.setCellValueFactory(new PropertyValueFactory<Register,String>("stopoverPoints"));
        timeFlight.setCellValueFactory(new PropertyValueFactory<Register,String>("timeFlight"));
        dayFlight.setCellValueFactory(new PropertyValueFactory<Register,String>("dayFlight"));
        availabilitySeatsFlight.setCellValueFactory(new PropertyValueFactory<Register,String>("availabilitySeatsFlight"));
        tableRegister.setItems(registerData);
    }

    public static void getData() throws Exception {
        String res = http.get(api,"all");
        System.out.println(res);

        JsonObject base = gson.fromJson(res, JsonObject.class);
        JsonArray data = base.getAsJsonArray("data");

        for (int i = 0; i < data.size(); i++) {
            Register newRegister = gson.fromJson(data.get(i).toString(), Register.class);
            registerData.add(newRegister);
        }
    }

    @FXML
    private void click_newRegister() throws Exception {
        Register reg = new Register();
        registerData.add(reg);
        App.showRegisterEditDialog(reg,registerData.size()-1);
        addRegister(reg);
    }

    @FXML
    private void click_deleteRegister() throws IOException {
        Register selectedRegister = tableRegister.getSelectionModel().getSelectedItem();
        if (selectedRegister != null) {
            System.out.println(selectedRegister.getId());
            System.out.println(http.delete(api, selectedRegister.getId()));
            registerData.remove(selectedRegister);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ничего не выбрано");
            alert.setHeaderText("Отсутствует выбранный рейс ");
            alert.setContentText("Выберите рейс в таблице");
            alert.showAndWait();
        }
    }

    @FXML
    private void click_dublicatedRegister() throws IOException {
        Register selectedRegister = tableRegister.getSelectionModel().getSelectedItem();
        if (selectedRegister != null) {
            addRegister(selectedRegister);
            registerData.add(registerData.indexOf(selectedRegister) + 1, selectedRegister);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ничего не выбрано");
            alert.setHeaderText("Отсутствует выбранный рейс ");
            alert.setContentText("Выберите рейс в таблице");
            alert.showAndWait();
        }
    }

    @FXML
    private void click_editRegister() throws IOException {
        Register selectedRegister = tableRegister.getSelectionModel().getSelectedItem();
        if (selectedRegister != null) {
            App.showRegisterEditDialog(selectedRegister,registerData.indexOf(selectedRegister));
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ничего не выбрано");
            alert.setHeaderText("Отсутствует выбранный рейс ");
            alert.setContentText("Выберите рейс в таблице");
            alert.showAndWait();
        }
    }

    /*    @FXML
    void addRegister(ActionEvent event) throws IOException {
        App.showRegisterEditDialog(new Register(),123);
    }*/

    public static void addRegister(Register register) throws IOException {
        System.out.println(register.toString());
        register.setId(null);
        http.post(api + "add", gson.toJson(register).toString());
    }
    public static void updateRegister(Register register) throws IOException {
        http.put(api + "update", gson.toJson(register).toString());
    }
}