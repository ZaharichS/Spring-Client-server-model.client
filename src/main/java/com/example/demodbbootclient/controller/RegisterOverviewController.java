package com.example.demodbbootclient.controller;

import com.example.demodbbootclient.App;
import com.example.demodbbootclient.entity.Register;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class RegisterOverviewController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    void addRegister(ActionEvent event) throws IOException {
        App.showRegisterEditDialog(new Register(),123);
    }
}