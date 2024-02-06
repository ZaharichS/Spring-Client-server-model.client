package com.example.demodbbootclient.controller;

import com.example.demodbbootclient.App;
import com.example.demodbbootclient.entity.Register;
import com.example.demodbbootclient.utils.HTTPUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;

public class RegisterOverviewController {
    public static ObservableList<Register> registerData = FXCollections.observableArrayList();
    HTTPUtils http = new HTTPUtils();

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
    private TableColumn<Register, String> AvailabilitySeatsFlight;

    @FXML
    private void initialize() throws Exception {
        System.out.println(http.get("http://localhost:2825/api/v1/register/all"));
    }
    @FXML
    void addRegister(ActionEvent event) throws IOException {
        App.showRegisterEditDialog(new Register(),123);
    }
}