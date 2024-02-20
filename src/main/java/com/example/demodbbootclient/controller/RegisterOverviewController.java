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
import okhttp3.OkHttpClient;
import okhttp3.Response;

import java.io.IOException;

@Data
public class RegisterOverviewController {
    public static String api = "http://localhost:8080/api/v1/register/";
    public static ObservableList<Register> registerData = FXCollections.observableArrayList();
    static HTTPUtils http = new HTTPUtils();

    static Gson gson = new Gson();


    @FXML
    public TableView<Register> tableRegister;

    @FXML
    public TableColumn<Register, String> id;

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
        getData();
        updateData();
    }

    private void updateData() throws Exception {
        id.setCellValueFactory(new PropertyValueFactory<Register, String>("id"));
        numFlight.setCellValueFactory(new PropertyValueFactory<Register,String>("numFlight"));
        trip.setCellValueFactory(new PropertyValueFactory<Register,String>("trip"));
        stopoverPoints.setCellValueFactory(new PropertyValueFactory<Register,String>("stopoverPoints"));
        timeFlight.setCellValueFactory(new PropertyValueFactory<Register,String>("timeFlight"));
        dayFlight.setCellValueFactory(new PropertyValueFactory<Register,String>("dayFlight"));
        availabilitySeatsFlight.setCellValueFactory(new PropertyValueFactory<Register,String>("availabilitySeatsFlight"));
        tableRegister.setItems(registerData);
    }

    @FXML
    private void click_newRegister() throws Exception {
        Register tempReg = new Register();
        registerData.add(tempReg);
        App.showRegisterEditDialog(tempReg, registerData.size()-1);
        addRegister(tempReg);
    }

    @FXML
    private void click_deleteRegister() throws IOException {
        Register selectedRegister = tableRegister.getSelectionModel().getSelectedItem();
        if (selectedRegister != null) {
            registerData.remove(selectedRegister);
            System.out.println(http.delete(api, selectedRegister.getId()));
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ничего не выбрано");
            alert.setHeaderText("Отсутствует выбранный рейс ");
            alert.setContentText("Выберите рейс в таблице");
            alert.showAndWait();
        }
    }

    @FXML
    private void click_dublicatedRegister() throws Exception, IOException {
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
            App.showRegisterEditDialog(selectedRegister, registerData.indexOf(selectedRegister));
            System.out.println(http.post(api + "", gson.toJson(selectedRegister).toString(), ""));
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ничего не выбрано");
            alert.setHeaderText("Отсутствует выбранный рейс ");
            alert.setContentText("Выберите рейс в таблице");
            alert.showAndWait();
        }
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

    public static void addRegister(Register register) throws IOException {
        System.out.println(register.toString());
        register.setId(null);
        http.post(api + "add", gson.toJson(register).toString());
        System.out.println("Рейс добавлен");
    }

    public static void updateRegister(Register register) throws IOException {
        System.out.println(register.toString());
        System.out.println(register.getId());
        http.post(api + "", gson.toJson(register).toString(), "" );
    }
}