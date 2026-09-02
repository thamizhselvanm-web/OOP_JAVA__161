package com.t2verify.controller;

import com.t2verify.Main;
import com.t2verify.dao.DocumentDAO;
import com.t2verify.dao.VerificationDAO;
import com.t2verify.model.User;
import com.t2verify.model.Verification;
import com.t2verify.util.AlertUtil;
import com.t2verify.util.UserSession;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

public class DashboardController {

    @FXML private Label userLabel;
    @FXML private Label roleLabel;
    @FXML private Label totalDocsLabel;
    @FXML private Label totalVerificationsLabel;
    @FXML private Label verifiedCountLabel;
    @FXML private Label unregisteredCountLabel;

    @FXML private TableView<Verification> activityTable;
    @FXML private TableColumn<Verification, String> colFile;
    @FXML private TableColumn<Verification, String> colResult;
    @FXML private TableColumn<Verification, String> colVerifier;
    @FXML private TableColumn<Verification, String> colDate;

    private final DocumentDAO documentDAO = new DocumentDAO();
    private final VerificationDAO verificationDAO = new VerificationDAO();
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @FXML
    public void initialize() {
        User user = UserSession.getCurrentUser();
        if (user != null) {
            userLabel.setText(user.getFullName());
            roleLabel.setText("Role: " + user.getRole());
        }

        setupTableColumns();
        loadDashboardData();
    }

    private void setupTableColumns() {
        colFile.setCellValueFactory(new PropertyValueFactory<>("submittedFileName"));
        colResult.setCellValueFactory(new PropertyValueFactory<>("result"));
        colVerifier.setCellValueFactory(cellData -> {
            String vName = cellData.getValue().getVerifierName();
            return new SimpleStringProperty(vName != null ? vName : "Guest / System");
        });
        colDate.setCellValueFactory(cellData -> {
            Timestamp ts = cellData.getValue().getVerifiedAt();
            return new SimpleStringProperty(ts != null ? dateFormat.format(ts) : "—");
        });

        // Custom Cell Factory for Result column (Badge styling)
        colResult.setCellFactory(column -> new TableCell<Verification, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item);
                    if ("VERIFIED".equalsIgnoreCase(item)) {
                        setStyle("-fx-text-fill: #34d399; -fx-font-weight: bold;");
                    } else {
                        setStyle("-fx-text-fill: #f87171; -fx-font-weight: bold;");
                    }
                }
            }
        });
    }

    private void loadDashboardData() {
        try {
            totalDocsLabel.setText(String.valueOf(documentDAO.countTotalDocuments()));
            totalVerificationsLabel.setText(String.valueOf(verificationDAO.countTotalVerifications()));
            verifiedCountLabel.setText(String.valueOf(verificationDAO.countVerificationsByResult("VERIFIED")));
            unregisteredCountLabel.setText(String.valueOf(verificationDAO.countVerificationsByResult("NOT REGISTERED")));

            List<Verification> recentList = verificationDAO.getRecentActivity(10);
            ObservableList<Verification> obsList = FXCollections.observableArrayList(recentList);
            activityTable.setItems(obsList);
        } catch (Exception e) {
            AlertUtil.showError("Data Error", "Could not load dashboard statistics: " + e.getMessage());
        }
    }

    @FXML public void handleNavDashboard(ActionEvent event) { /* Already on dashboard */ }
    @FXML public void handleNavUpload(ActionEvent event) { Main.switchScene("/fxml/upload.fxml", "T2Verify — Register Document"); }
    @FXML public void handleNavVerify(ActionEvent event) { Main.switchScene("/fxml/verify.fxml", "T2Verify — Verify Document"); }
    @FXML public void handleNavHistory(ActionEvent event) { Main.switchScene("/fxml/history.fxml", "T2Verify — Verification History"); }

    @FXML
    public void handleLogout(ActionEvent event) {
        UserSession.clear();
        Main.switchScene("/fxml/login.fxml", "T2Verify — Log In");
    }
}
