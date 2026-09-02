package com.t2verify;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {

    private static Stage primaryStage;

    @Override
    public void init() {
        if (getParameters() != null) {
            if (getParameters().getNamed().containsKey("db.password")) {
                System.setProperty("db.password", getParameters().getNamed().get("db.password"));
            } else if (getParameters().getNamed().containsKey("password")) {
                System.setProperty("db.password", getParameters().getNamed().get("password"));
            } else if (!getParameters().getRaw().isEmpty()) {
                String arg = getParameters().getRaw().get(0).trim();
                if (!arg.startsWith("-")) {
                    System.setProperty("db.password", arg);
                }
            }
        }
    }

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        primaryStage.setTitle("T2Verify — Log In");
        primaryStage.setResizable(true);

        switchScene("/fxml/login.fxml", "T2Verify — Log In");
        primaryStage.show();
    }

    public static void switchScene(String fxmlPath, String title) {
        try {
            URL url = Main.class.getResource(fxmlPath);
            if (url == null) {
                System.err.println("Cannot find FXML file at resource path: " + fxmlPath);
                return;
            }
            Parent root = FXMLLoader.load(url);
            Scene currentScene = primaryStage.getScene();
            if (currentScene == null) {
                Scene newScene = new Scene(root, 1050, 700);
                primaryStage.setScene(newScene);
            } else {
                currentScene.setRoot(root);
            }
            primaryStage.setTitle(title);
        } catch (IOException e) {
            System.err.println("Error loading scene: " + fxmlPath);
            e.printStackTrace();
        }
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
