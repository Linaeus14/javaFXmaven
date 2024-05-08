package com.pbo.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import com.pbo.App;
import com.pbo.data.*;

public class authController extends dataControl {

    @FXML
    private ImageView profileImage;

    @FXML
    private Button continueButton, skipButton;

    @FXML
    private Label infoLabelL, infoLabelR;

    @FXML
    private TextField tMailL, tMailR, tName;

    @FXML
    private PasswordField tPassL, tPassR;

    @FXML
    private void hyperLog() throws IOException {
        App.setRoot("authL");
    }

    @FXML
    private void hyperReg() throws IOException {
        App.setRoot("authR");
    }

    @FXML
    private void loginAcc() throws Exception {
        infoLabelL.setVisible(false);
        if (getAkun(tMailL.getText(), tPassL.getText())) {
            App.setScene("main");
        }
        infoLabelL.setText("Credential error");
        infoLabelL.setVisible(true);
    }

    @FXML
    private void registerAcc() throws Exception {
        infoLabelR.setVisible(false);
        switch (createAkun(new data(-1, tMailR.getText(), tPassR.getText(), tName.getText(), ""))) {
            case 0:
                App.setRoot("pick");
                break;
            case 1:
                infoLabelR.setText("Email has already been used!");
                break;
            case 2:
                infoLabelR.setText("Register Failed, Try again later!");
                break;
            default:
                infoLabelR.setText("Something went wrong");
                break;
        }
        infoLabelR.setVisible(true);
    }

    @FXML
    private void browsePicture() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Picture");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(
                        "Image Files", "*.png", "*.jpg", "*.jpeg"));
        java.io.File file = fileChooser.showOpenDialog((Stage) App.scene.getWindow());
        if (file != null) {
            String path = file.toURI().toString();
            imageClip.mask(path, profileImage);
            App.akun.setFoto(path);
            try {
                continueButton.setDisable(false);
                skipButton.setVisible(false);
            } catch (Exception e) {
                return;
            }
        }
    }

    @FXML
    private void continuePress() throws Exception {
        updateAkun(App.akun);
        App.setScene("main");
    }

    @FXML
    private void skipPicture() throws Exception {
        App.setScene("main");
    }

    @FXML
    private void editPress() throws Exception {
        App.akun.setNama(tName.getText());
        App.akun.setPwd(tPassR.getText());
        updateAkun(App.akun);
        App.setRoot("main");
    }

    @FXML
    private void backMain() throws Exception {
        App.setRoot("main");
    }
}