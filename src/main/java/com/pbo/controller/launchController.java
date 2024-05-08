package com.pbo.controller;

import com.pbo.App;
import com.pbo.service.connection;

import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class launchController {

    @FXML
    private Button retryButton;

    @FXML
    private Label failedLabel;

    public void initialize() {
        final connection connection = new connection();
        connection.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                if (connection.getValue()) {
                    try {
                        Thread.sleep(2000);
                        App.setRoot("authL");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    failedLabel.setText("Failed to Connect!");
                    retryButton.setVisible(true);
                }
            }
        });
        connection.restart(); // here you start your service
    }

    @FXML
    private void retryConn() throws Exception {
        retryButton.setVisible(false);
        failedLabel.setText("Connecting...");
        initialize();
    }
}
