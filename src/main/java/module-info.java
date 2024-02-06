module com.example.demodbbootclient {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires com.google.gson;
    requires okhttp3;
    requires static lombok;
    requires annotations;

    opens com.example.demodbbootclient to javafx.fxml;
    exports com.example.demodbbootclient;
    exports com.example.demodbbootclient.entity;
    opens com.example.demodbbootclient.entity to com.google.gson;
    exports com.example.demodbbootclient.controller;
    opens com.example.demodbbootclient.controller to javafx.fxml;
    exports com.example.demodbbootclient.response;
    opens com.example.demodbbootclient.response to com.google.gson;
}