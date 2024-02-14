package com.example.demodbbootclient.controller;

import com.example.demodbbootclient.entity.Register;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Getter;

import java.io.IOException;

import static com.example.demodbbootclient.controller.RegisterOverviewController.registerData;
import static com.example.demodbbootclient.controller.RegisterOverviewController.updateRegister;

public class ModifyController {
    @FXML
    private TextField numFlight_field;
    @FXML
    private TextField trip_field;
    @FXML
    private TextField stopoverPoints_field;
    @FXML
    private TextField timeFlight_field;
    @FXML
    private TextField dayFlight_field;
    @FXML
    private TextField availabilitySeatsFlight_field;

    private Stage modifyStage;
    private Register register;
    private Integer register_id;

    private boolean okClicked = false;

    public void setDialogStage(Stage dialogStage) {
        this.modifyStage = dialogStage;
    }

    public boolean isOkClicked() { return okClicked; }

    public void setLabels(Register regIn, Integer reg_id) {
        this.register = regIn;
        this.register_id = reg_id;

        numFlight_field.setText(register.getNumFlight());
        trip_field.setText(register.getTrip());
        stopoverPoints_field.setText(register.getStopoverPoints());
        timeFlight_field.setText(register.getTimeFlight());
        dayFlight_field.setText(register.getDayFlight());
        availabilitySeatsFlight_field.setText(register.getAvailabilitySeatsFlight());
    }

    @FXML
    private void handleOk() throws IOException {
        if (isInputValid()) {
            register.setNumFlight(numFlight_field.getText());
            register.setTrip(trip_field.getText());
            register.setStopoverPoints(stopoverPoints_field.getText());
            register.setTimeFlight(timeFlight_field.getText());
            register.setDayFlight(dayFlight_field.getText());
            register.setAvailabilitySeatsFlight(availabilitySeatsFlight_field.getText());

            okClicked = true;
            modifyStage.close();
            registerData.set(register_id, register);
            updateRegister(register);
        }
    }

    @FXML
    private void handleCancel() { modifyStage.close(); }

    private boolean isInputValid() {
        String errorMessage = "";

        if (numFlight_field.getText() == null || numFlight_field.getText().isEmpty()) { errorMessage += "Не обнаружен номер полета"; }
        else {
            try {
                Integer.parseInt(numFlight_field.getText());
            } catch (NumberFormatException e ) {
                errorMessage += "Некоректное значение номера рейса (должно быть целочисленным)";
            }
        }
        if (trip_field.getText() == null || trip_field.getText().isEmpty()) { errorMessage += "Не обнаружен маршрут"; }
        if (stopoverPoints_field.getText() == null || stopoverPoints_field.getText().isEmpty()) { errorMessage += "Не обнаружен промежуточный пункт посадки"; }
        if (timeFlight_field.getText() == null || timeFlight_field.getText().isEmpty()) { errorMessage += "Не обнаружено время отправления"; }
        if (dayFlight_field.getText() == null || dayFlight_field.getText().isEmpty()) { errorMessage += "Не обнаружены дни отправления"; }
        if (availabilitySeatsFlight_field.getText() == null || availabilitySeatsFlight_field.getText().isEmpty()) { errorMessage += "Не обнаружено количество свободных мест"; }
        else {
            try {
                Integer.parseInt(availabilitySeatsFlight_field.getText());
            } catch (NumberFormatException e ) {
                errorMessage += "Некоректное значение свободных мест (должно быть целочисленным)";
            }
        }

        if (errorMessage.isEmpty()) { return true; }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(modifyStage);
            alert.setTitle("Ошибка ввода");
            alert.setHeaderText("Введите корректные значения! ");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
