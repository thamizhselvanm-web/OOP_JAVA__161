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

public class RegisterController {

    @FXML private TextField fullNameField;
    @FXML private TextField emailField;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Label errorLabel;
    @FXML private Button registerButton;

    private final AuthenticationService authService = new AuthenticationService();

    @FXML
    public void handleRegister(ActionEvent event) {
        errorLabel.setVisible(false);

        String fullName = fullNameField.getText();
        String email = emailField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        try {
            boolean success = authService.registerUser(fullName, email, username, password, confirmPassword);
            if (success) {
                AlertUtil.showInfo("Registration Successful", "Account registered successfully! You can now log in.");
                Main.switchScene("/fxml/login.fxml", "T2Verify — Log In");
            } else {
                errorLabel.setText("Failed to register account. Please try again.");
                errorLabel.setVisible(true);
            }
        } catch (IllegalArgumentException e) {
            errorLabel.setText(e.getMessage());
            errorLabel.setVisible(true);
        } catch (Exception e) {
            AlertUtil.showError("Database Error", "An error occurred while registering: " + e.getMessage());
        }
    }

    @FXML
    public void handleGoToLogin(ActionEvent event) {
        Main.switchScene("/fxml/login.fxml", "T2Verify — Log In");
    }
}
