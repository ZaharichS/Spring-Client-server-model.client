package com.example.demodbbootclient;

import com.example.demodbbootclient.controller.ModifyController;
import com.example.demodbbootclient.controller.RegisterOverviewController;
import com.example.demodbbootclient.entity.Register;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

//import javax.swing.*;
import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(RegisterOverviewController.class.getResource("/view/RegisterOverview.fxml"));
        AnchorPane app = fxmlLoader.load();

        Scene scene = new Scene(app, 705,430);
        stage.setTitle("CashRegister");
        stage.setScene(scene);

        stage.setResizable(false);

        RegisterOverviewController controller = fxmlLoader.getController();
        stage.show();
    }

    public static boolean showRegisterEditDialog(Register register, Integer id){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(RegisterOverviewController.class.getResource("/view/Modify.fxml"));
            AnchorPane page = (AnchorPane) fxmlLoader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Изменить данные");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            ModifyController controller = fxmlLoader.getController();
            controller.setDialogStage(dialogStage);
            controller.setLabels(register, id);

            dialogStage.showAndWait();
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static void main(String[] args) {
        launch();
    }
}