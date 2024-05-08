package com.pbo.controller;

import java.io.IOException;
import java.sql.SQLException;

import com.pbo.App;
import com.pbo.data.dataControl;
import com.pbo.service.connection;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class mainController extends dataControl {

    @FXML
    private ImageView profileImage;

    @FXML
    private Text tMail, tName;

    @FXML
    public void initialize() {
        imageClip.mask(App.akun.getFoto(), profileImage);
        tName.setText(App.akun.getNama());
        tMail.setText(App.akun.getEmail());
    }

    @FXML
    private void editPress() throws IOException {
        App.setRoot("update");
    }

    @FXML
    private void logOut() throws SQLException, IOException {
        connection.conn.close();
        App.akun.empty();
        App.setScene("launch");
    }

    @FXML
    private void deleteAcc() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Delete Account");
        alert.setContentText("Are you sure you want to delete your account?");

        // Handle user response
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    deleteAkun(App.akun.getId());
                    logOut();
                } catch (SQLException | IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
