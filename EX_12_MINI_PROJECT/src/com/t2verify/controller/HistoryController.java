package com.t2verify.controller;

import com.t2verify.Main;
import com.t2verify.model.User;
import com.t2verify.model.Verification;
import com.t2verify.service.VerificationService;
import com.t2verify.util.AlertUtil;
import com.t2verify.util.UserSession;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

public class HistoryController {

    @FXML private Label userLabel;
    @FXML private Label roleLabel;

    @FXML private TextField searchField;
    @FXML private ComboBox<String> filterComboBox;

    @FXML private TableView<Verification> historyTable;
    @FXML private TableColumn<Verification, Integer> colId;
    @FXML private TableColumn<Verification, String> colFileName;
    @FXML private TableColumn<Verification, String> colResult;
    @FXML private TableColumn<Verification, String> colHash;
    @FXML private TableColumn<Verification, String> colMatchedDocId;
    @FXML private TableColumn<Verification, String> colVerifier;
    @FXML private TableColumn<Verification, String> colTimestamp;

    private final VerificationService verificationService = new VerificationService();
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private ObservableList<Verification> masterData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        User user = UserSession.getCurrentUser();
        if (user != null) {
            userLabel.setText(user.getFullName());
            roleLabel.setText("Role: " + user.getRole());
        }

        setupComboBox();
        setupTableColumns();
        loadHistoryData();
        setupSearchAndFilter();
    }

    private void setupComboBox() {
        filterComboBox.setItems(FXCollections.observableArrayList("All Results", "VERIFIED", "NOT REGISTERED"));
        filterComboBox.getSelectionModel().selectFirst();
    }

    private void setupTableColumns() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFileName.setCellValueFactory(new PropertyValueFactory<>("submittedFileName"));
        colResult.setCellValueFactory(new PropertyValueFactory<>("result"));
        colHash.setCellValueFactory(new PropertyValueFactory<>("submittedHash"));
        colMatchedDocId.setCellValueFactory(cellData -> {
            Integer id = cellData.getValue().getMatchedDocumentId();
            return new SimpleStringProperty(id != null ? "#" + id : "None");
        });
        colVerifier.setCellValueFactory(cellData -> {
            String name = cellData.getValue().getVerifierName();
            return new SimpleStringProperty(name != null ? name : "Guest / System");
        });
        colTimestamp.setCellValueFactory(cellData -> {
            Timestamp ts = cellData.getValue().getVerifiedAt();
            return new SimpleStringProperty(ts != null ? dateFormat.format(ts) : "—");
        });

        // Badge styling for Result column
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

    private void loadHistoryData() {
        try {
            User user = UserSession.getCurrentUser();
            List<Verification> list = verificationService.getHistoryForUser(user);
            masterData.setAll(list);
        } catch (Exception e) {
            AlertUtil.showError("Data Error", "Could not load verification history: " + e.getMessage());
        }
    }

    private void setupSearchAndFilter() {
        FilteredList<Verification> filteredData = new FilteredList<>(masterData, p -> true);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> applyFilter(filteredData));
        filterComboBox.valueProperty().addListener((observable, oldValue, newValue) -> applyFilter(filteredData));

        historyTable.setItems(filteredData);
    }

    private void applyFilter(FilteredList<Verification> filteredData) {
        String searchText = searchField.getText() == null ? "" : searchField.getText().toLowerCase().trim();
        String selectedFilter = filterComboBox.getValue();

        filteredData.setPredicate(v -> {
            boolean matchesSearch = searchText.isEmpty() || 
                v.getSubmittedFileName().toLowerCase().contains(searchText) ||
                v.getSubmittedHash().toLowerCase().contains(searchText);

            boolean matchesResult = selectedFilter == null || "All Results".equalsIgnoreCase(selectedFilter) ||
                v.getResult().equalsIgnoreCase(selectedFilter);

            return matchesSearch && matchesResult;
        });
    }

    @FXML
    public void handleRefresh(ActionEvent event) {
        loadHistoryData();
    }

    @FXML public void handleNavDashboard(ActionEvent event) { Main.switchScene("/fxml/dashboard.fxml", "T2Verify — Dashboard"); }
    @FXML public void handleNavUpload(ActionEvent event) { Main.switchScene("/fxml/upload.fxml", "T2Verify — Register Document"); }
    @FXML public void handleNavVerify(ActionEvent event) { Main.switchScene("/fxml/verify.fxml", "T2Verify — Verify Document"); }
    @FXML public void handleNavHistory(ActionEvent event) { /* Already on history */ }

    @FXML
    public void handleLogout(ActionEvent event) {
        UserSession.clear();
        Main.switchScene("/fxml/login.fxml", "T2Verify — Log In");
    }
}
