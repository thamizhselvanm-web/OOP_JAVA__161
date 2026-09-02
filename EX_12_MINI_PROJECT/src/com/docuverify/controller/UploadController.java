package com.docuverify.controller;

import com.docuverify.Main;
import com.docuverify.model.Document;
import com.docuverify.model.User;
import com.docuverify.service.DocumentService;
import com.docuverify.util.AlertUtil;
import com.docuverify.util.HashUtil;
import com.docuverify.util.UserSession;
import com.docuverify.util.ValidationUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

public class UploadController {

    @FXML private Label userLabel;
    @FXML private Label roleLabel;
    @FXML private Label filePathLabel;
    @FXML private Label fileNameVal;
    @FXML private Label fileTypeVal;
    @FXML private Label fileSizeVal;
    @FXML private TextField hashValField;
    @FXML private Button registerDocBtn;
    @FXML private ProgressIndicator progressIndicator;

    @FXML private TableView<Document> docTable;
    @FXML private TableColumn<Document, Integer> colDocId;
    @FXML private TableColumn<Document, String> colDocName;
    @FXML private TableColumn<Document, String> colDocType;
    @FXML private TableColumn<Document, String> colDocSize;
    @FXML private TableColumn<Document, String> colDocHash;
    @FXML private TableColumn<Document, String> colDocOwner;
    @FXML private TableColumn<Document, String> colDocDate;

    private File selectedFile;
    private final DocumentService documentService = new DocumentService();
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @FXML
    public void initialize() {
        User user = UserSession.getCurrentUser();
        if (user != null) {
            userLabel.setText(user.getFullName());
            roleLabel.setText("Role: " + user.getRole());
        }

        setupTableColumns();
        loadRegisteredDocuments();
    }

    private void setupTableColumns() {
        colDocId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDocName.setCellValueFactory(new PropertyValueFactory<>("fileName"));
        colDocType.setCellValueFactory(new PropertyValueFactory<>("fileType"));
        colDocSize.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFormattedSize()));
        colDocHash.setCellValueFactory(new PropertyValueFactory<>("documentHash"));
        colDocOwner.setCellValueFactory(cellData -> {
            String owner = cellData.getValue().getOwnerName();
            return new SimpleStringProperty(owner != null ? owner : "Unknown");
        });
        colDocDate.setCellValueFactory(cellData -> {
            Timestamp ts = cellData.getValue().getRegisteredAt();
            return new SimpleStringProperty(ts != null ? dateFormat.format(ts) : "—");
        });
    }

    @FXML
    public void handleChooseFile(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select Document to Register");
        chooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Supported Files (*.pdf, *.docx, *.txt, *.png, *.jpg, *.jpeg)", "*.pdf", "*.docx", "*.txt", "*.png", "*.jpg", "*.jpeg"),
            new FileChooser.ExtensionFilter("All Files", "*.*")
        );

        Stage stage = (Stage) filePathLabel.getScene().getWindow();
        File file = chooser.showOpenDialog(stage);
        if (file != null) {
            if (!ValidationUtil.isSupportedFile(file)) {
                AlertUtil.showError("Invalid File Type", "Selected file type is not supported. Please select PDF, DOCX, TXT, PNG, JPG, or JPEG.");
                return;
            }
            if (!ValidationUtil.isWithinSizeLimit(file)) {
                AlertUtil.showError("File Too Large", "Selected file exceeds the maximum 50 MB limit.");
                return;
            }

            this.selectedFile = file;
            filePathLabel.setText(file.getAbsolutePath());
            fileNameVal.setText(file.getName());
            fileTypeVal.setText(ValidationUtil.getFileExtension(file.getName()).toUpperCase());
            
            Document dummyDoc = new Document();
            dummyDoc.setFileSize(file.length());
            fileSizeVal.setText(dummyDoc.getFormattedSize());

            // Compute SHA-256 hash in background thread using Task
            computeHashInBackground(file);
        }
    }

    private void computeHashInBackground(File file) {
        progressIndicator.setVisible(true);
        registerDocBtn.setDisable(true);
        hashValField.setText("Computing SHA-256 hash...");

        Task<String> hashTask = new Task<>() {
            @Override
            protected String call() throws Exception {
                return HashUtil.generateSHA256(file);
            }
        };

        hashTask.setOnSucceeded(e -> {
            progressIndicator.setVisible(false);
            String hash = hashTask.getValue();
            hashValField.setText(hash);
            registerDocBtn.setDisable(false);
        });

        hashTask.setOnFailed(e -> {
            progressIndicator.setVisible(false);
            hashValField.setText("Error computing hash");
            AlertUtil.showError("Hashing Error", "Failed to compute file hash: " + hashTask.getException().getMessage());
        });

        new Thread(hashTask).start();
    }

    @FXML
    public void handleRegisterDocument(ActionEvent event) {
        if (selectedFile == null || hashValField.getText().isEmpty()) {
            AlertUtil.showWarning("Missing Input", "Please select a valid document file first.");
            return;
        }

        try {
            User user = UserSession.getCurrentUser();
            Document registeredDoc = documentService.registerDocument(selectedFile, user);
            AlertUtil.showInfo("Registration Successful", "Document registered successfully!\n\nDocument ID: #" 
                + registeredDoc.getId() + "\nSHA-256 Hash: " + registeredDoc.getDocumentHash());

            // Reset form
            selectedFile = null;
            filePathLabel.setText("No file selected (PDF, DOCX, TXT, PNG, JPG supported)");
            fileNameVal.setText("—");
            fileTypeVal.setText("—");
            fileSizeVal.setText("—");
            hashValField.clear();
            registerDocBtn.setDisable(true);

            loadRegisteredDocuments();
        } catch (DocumentService.DuplicateDocumentException e) {
            AlertUtil.showWarning("Duplicate Registration", e.getMessage());
        } catch (Exception e) {
            AlertUtil.showError("Registration Error", "Failed to register document: " + e.getMessage());
        }
    }

    private void loadRegisteredDocuments() {
        try {
            User user = UserSession.getCurrentUser();
            List<Document> docList = documentService.getDocumentsForCurrentUser(user);
            ObservableList<Document> obsList = FXCollections.observableArrayList(docList);
            docTable.setItems(obsList);
        } catch (Exception e) {
            AlertUtil.showError("Data Error", "Could not load registered documents: " + e.getMessage());
        }
    }

    @FXML public void handleNavDashboard(ActionEvent event) { Main.switchScene("/fxml/dashboard.fxml", "DocuVerify — Dashboard"); }
    @FXML public void handleNavUpload(ActionEvent event) { /* Already on upload */ }
    @FXML public void handleNavVerify(ActionEvent event) { Main.switchScene("/fxml/verify.fxml", "DocuVerify — Verify Document"); }
    @FXML public void handleNavHistory(ActionEvent event) { Main.switchScene("/fxml/history.fxml", "DocuVerify — Verification History"); }

    @FXML
    public void handleLogout(ActionEvent event) {
        UserSession.clear();
        Main.switchScene("/fxml/login.fxml", "DocuVerify — Log In");
    }
}
