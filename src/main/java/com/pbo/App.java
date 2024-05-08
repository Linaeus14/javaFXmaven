package com.pbo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import atlantafx.base.theme.Dracula;
import java.io.IOException;

import com.pbo.data.data;

public class App extends Application {
    public static Scene scene;
    public static data akun;

    @Override
    public void start(Stage stage) throws IOException {
        Application.setUserAgentStylesheet(new Dracula().getUserAgentStylesheet());
        scene = new Scene(loadFXML("launch"));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static void setScene(String fxml) throws IOException {
        Stage stage = (Stage) scene.getWindow();
        scene = new Scene(loadFXML(fxml));
        stage.setScene(scene);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}
