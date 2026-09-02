package com.t2verify.controller;

import com.t2verify.Main;
import com.t2verify.service.AuthenticationService;
import com.t2verify.util.AlertUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;
    @FXML private Button loginButton;

    private final AuthenticationService authService = new AuthenticationService();

    @FXML
    public void handleLogin(ActionEvent event) {
        errorLabel.setVisible(false);
        String username = usernameField.getText();
        String password = passwordField.getText();

        try {
            authService.authenticate(username, password);
            Main.switchScene("/fxml/dashboard.fxml", "T2Verify — Dashboard");
        } catch (IllegalArgumentException e) {
            errorLabel.setText(e.getMessage());
            errorLabel.setVisible(true);
        } catch (Exception e) {
            AlertUtil.showError("Database Error", "Failed to connect to database: " + e.getMessage());
        }
    }

    @FXML
    public void handleGoToRegister(ActionEvent event) {
        Main.switchScene("/fxml/register.fxml", "T2Verify — Register Account");
    }
}
