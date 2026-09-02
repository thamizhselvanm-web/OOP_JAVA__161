package com.t2verify.controller;

import com.t2verify.Main;
import com.t2verify.model.Document;
import com.t2verify.model.User;
import com.t2verify.service.VerificationService;
import com.t2verify.util.AlertUtil;
import com.t2verify.util.UserSession;
import com.t2verify.util.ValidationUtil;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.text.SimpleDateFormat;

public class VerificationController {

    @FXML private Label userLabel;
    @FXML private Label roleLabel;
    @FXML private Label filePathLabel;
    @FXML private Button verifyBtn;
    @FXML private ProgressIndicator progressIndicator;

    @FXML private VBox resultContainer;
    @FXML private Label resultBadgeLabel;
    @FXML private Label resultMessageLabel;
    @FXML private Label resultSubMessageLabel;

    @FXML private TextField calculatedHashField;
    @FXML private Label matchedDocIdLabel;
    @FXML private Label originalFileNameLabel;
    @FXML private Label registeredOwnerLabel;
    @FXML private Label registrationDateLabel;

    private File selectedFile;
    private final VerificationService verificationService = new VerificationService();
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @FXML
    public void initialize() {
        User user = UserSession.getCurrentUser();
        if (user != null) {
            userLabel.setText(user.getFullName());
            roleLabel.setText("Role: " + user.getRole());
        }
    }

    @FXML
    public void handleChooseFile(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select Document to Verify");
        chooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("All Supported Files", "*.*")
        );

        Stage stage = (Stage) filePathLabel.getScene().getWindow();
        File file = chooser.showOpenDialog(stage);
        if (file != null) {
            if (!ValidationUtil.isWithinSizeLimit(file)) {
                AlertUtil.showError("File Too Large", "Selected file exceeds the maximum 50 MB limit.");
                return;
            }

            this.selectedFile = file;
            filePathLabel.setText(file.getAbsolutePath());
            verifyBtn.setDisable(false);
            resultContainer.setVisible(false);
        }
    }

    @FXML
    public void handleVerifyDocument(ActionEvent event) {
        if (selectedFile == null) {
            AlertUtil.showWarning("No File Selected", "Please select a file to verify.");
            return;
        }

        progressIndicator.setVisible(true);
        verifyBtn.setDisable(true);

        User verifier = UserSession.getCurrentUser();

        Task<VerificationService.VerificationResult> verifyTask = new Task<>() {
            @Override
            protected VerificationService.VerificationResult call() throws Exception {
                return verificationService.verifyDocument(selectedFile, verifier);
            }
        };

        verifyTask.setOnSucceeded(e -> {
            progressIndicator.setVisible(false);
            verifyBtn.setDisable(false);
            VerificationService.VerificationResult result = verifyTask.getValue();
            displayResult(result);
        });

        verifyTask.setOnFailed(e -> {
            progressIndicator.setVisible(false);
            verifyBtn.setDisable(false);
            AlertUtil.showError("Verification Failed", "An error occurred during verification: " + verifyTask.getException().getMessage());
        });

        new Thread(verifyTask).start();
    }

    private void displayResult(VerificationService.VerificationResult result) {
        resultContainer.setVisible(true);
        calculatedHashField.setText(result.getHash());

        if (result.isVerified()) {
            resultBadgeLabel.setText("✓ VERIFIED MATCH");
            resultBadgeLabel.getStyleClass().clear();
            resultBadgeLabel.getStyleClass().add("badge-verified");

            resultMessageLabel.setText("Exact matching document found!");
            resultSubMessageLabel.setText("The SHA-256 hash matches a registered document in the database.");

            Document matchedDoc = result.getMatchedDocument();
            if (matchedDoc != null) {
                matchedDocIdLabel.setText("#" + matchedDoc.getId());
                originalFileNameLabel.setText(matchedDoc.getFileName());
                registeredOwnerLabel.setText(matchedDoc.getOwnerName() != null ? matchedDoc.getOwnerName() : "User ID #" + matchedDoc.getUserId());
                registrationDateLabel.setText(matchedDoc.getRegisteredAt() != null ? dateFormat.format(matchedDoc.getRegisteredAt()) : "—");
            }
        } else {
            resultBadgeLabel.setText("❓ NOT REGISTERED");
            resultBadgeLabel.getStyleClass().clear();
            resultBadgeLabel.getStyleClass().add("badge-unverified");

            resultMessageLabel.setText("No exact matching document found");
            resultSubMessageLabel.setText("No registered document matches the SHA-256 hash of the submitted file.");

            matchedDocIdLabel.setText("None (Not Registered)");
            originalFileNameLabel.setText("N/A");
            registeredOwnerLabel.setText("N/A");
            registrationDateLabel.setText("N/A");
        }
    }

    @FXML public void handleNavDashboard(ActionEvent event) { Main.switchScene("/fxml/dashboard.fxml", "T2Verify — Dashboard"); }
    @FXML public void handleNavUpload(ActionEvent event) { Main.switchScene("/fxml/upload.fxml", "T2Verify — Register Document"); }
    @FXML public void handleNavVerify(ActionEvent event) { /* Already on verify */ }
    @FXML public void handleNavHistory(ActionEvent event) { Main.switchScene("/fxml/history.fxml", "T2Verify — Verification History"); }

    @FXML
    public void handleLogout(ActionEvent event) {
        UserSession.clear();
        Main.switchScene("/fxml/login.fxml", "T2Verify — Log In");
    }
}
