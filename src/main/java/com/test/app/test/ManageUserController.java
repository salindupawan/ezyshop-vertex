package com.test.app.test;

import com.test.app.test.entity.User;
import com.test.app.test.service.UserService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class ManageUserController {
    @FXML private Label lblTotalUsers;
    @FXML private Label lblActiveUsers;
    @FXML private Label lblInactiveUsers;

    @FXML private TextField txtSearch;
    @FXML private Button btnSearchAction;
    @FXML private Button btnClear;

    @FXML private TableView<User> tblUsers;
    @FXML private TableColumn<User, String> colId;
    @FXML private TableColumn<User, String> colName;
    @FXML private TableColumn<User, String> colRole;
    @FXML private TableColumn<User, String> colStatus;
    @FXML private TableColumn<User, String> colCreatedBy;
    @FXML private TableColumn<User, String> colCreatedAt;
    @FXML private TableColumn<User, String> colModifiedBy;
    @FXML private TableColumn<User, String> colModifiedAt;
    @FXML private TableColumn<User, Void> colAction;

    @FXML private Button createUser;
    @FXML private Button btnEditUser;
    @FXML private Button btnResetPassword;
    @FXML private Button btnRefresh;
    @FXML private Button btnManageRoles;
    @FXML private Button btnBack;


    private final ObservableList<User> masterUserDataList = FXCollections.observableArrayList();
    private FilteredList<User> filteredDataList;
    private UserService userService;

    @FXML
    public void initialize() {
        userService = new UserService();
        configureTableColumns();
        loadUserDataFromSource();
        setupLiveSearchingAndFiltering();
        updateSummaryMetricBadges();
        configureActionListeners();
    }

    private void configureTableColumns() {
        colId.setCellValueFactory(new PropertyValueFactory<>("userId"));

        colName.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getFirstName() + " " + cellData.getValue().getLastName())
        );

        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        colCreatedBy.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCreatedBy() != null ? cellData.getValue().getCreatedBy() : "System"));
        colCreatedAt.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCreatedAt() != null ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cellData.getValue().getCreatedAt()) : "N/A"));
        colModifiedBy.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getModifiedBy() != null ? cellData.getValue().getModifiedBy() : "-"));
        colModifiedAt.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getModifiedAt() != null ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cellData.getValue().getModifiedAt()) : "-"));
    }

    private void loadUserDataFromSource() {
        masterUserDataList.setAll(userService.getUsers());
    }

    private void setupLiveSearchingAndFiltering() {
        filteredDataList = new FilteredList<>(masterUserDataList, p -> true);

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredDataList.setPredicate(user -> {
                if (newValue == null || newValue.trim().isEmpty()) {
                    return true;
                }

                String searchTargetKey = newValue.toLowerCase().trim();

                if (user.getUserId() != null && user.getUserId().toLowerCase().contains(searchTargetKey)) {
                    return true;
                }
                if (user.getFirstName() != null && user.getFirstName().toLowerCase().contains(searchTargetKey)) {
                    return true;
                }
                if (user.getLastName() != null && user.getLastName().toLowerCase().contains(searchTargetKey)) {
                    return true;
                }
                if (user.getRole() != null && user.getRole().toLowerCase().contains(searchTargetKey)) {
                    return true;
                }
                if (user.getStatus() != null && user.getStatus().toLowerCase().contains(searchTargetKey)) {
                    return true;
                }

                return false;
            });
            updateSummaryMetricBadges();
        });

        SortedList<User> sortedDataList = new SortedList<>(filteredDataList);
        sortedDataList.comparatorProperty().bind(tblUsers.comparatorProperty());

        tblUsers.setItems(sortedDataList);
    }

    private void updateSummaryMetricBadges() {
        long totalCount = filteredDataList.size();
        long activeCount = filteredDataList.stream().filter(u -> "Active".equalsIgnoreCase(u.getStatus())).count();
        long inactiveCount = totalCount - activeCount;

        lblTotalUsers.setText(String.format("%02d", totalCount));
        lblActiveUsers.setText(String.format("%02d", activeCount));
        lblInactiveUsers.setText(String.format("%02d", inactiveCount));
    }

    private void configureActionListeners() {
        btnClear.setOnAction(e -> txtSearch.clear());

        btnRefresh.setOnAction(e -> {
            loadUserDataFromSource();
            updateSummaryMetricBadges();
        });
    }

    @FXML
    private void openAddUserWindow(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("create-user.fxml"));
            Parent root = loader.load();

            Stage stage =  (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
