package com.test.app.test;

import com.test.app.test.entity.User;
import com.test.app.test.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.controlsfx.validation.*;
import org.controlsfx.validation.decoration.ValidationDecoration;

import java.io.IOException;
import java.util.Date;

public class CreateUserController {
    @FXML private TextField txtFirstName;
    @FXML private TextField txtLastName;
    @FXML private TextField txtUsername;
    @FXML private TextField txtEmail;
    @FXML private TextField txtPhoneNumber;
    @FXML private TextField txtUserId;

    @FXML private DatePicker dpStartDate;
    @FXML private ComboBox<String> cmbDuration;

    @FXML private PasswordField txtPassword;
    @FXML private PasswordField txtConfirmPassword;
    @FXML private TextField txtMaxAttempts;
    @FXML private TextField txtLockoutDuration;
    @FXML private ComboBox<String> cmbRole;
    @FXML private ComboBox<String> cmbStatus;

    @FXML private Button btnSave;

    private final ValidationSupport validationSupport = new ValidationSupport();
    private UserService userService;

    @FXML
    public void initialize() {
        userService  = new UserService();
        populateChoiceContainers();
        configureDefaultFormStates();

        validationSupport.setErrorDecorationEnabled(false);
        validationSupport.setValidationDecorator(new ValidationDecoration() {

            @Override
            public void removeDecorations(Control control) {

            }

            @Override
            public void applyValidationDecoration(ValidationMessage validationMessage) {

            }

            @Override
            public void applyRequiredDecoration(Control control) {

            }
        });

        setupFormValidationRules();
        configureActionListeners();
    }

    @FXML
    private void openManageUserWindow(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("manage-user.fxml"));
            Parent root = loader.load();

            Stage stage =  (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void populateChoiceContainers() {
        cmbDuration.getItems().addAll("6", "12", "24", "36");
        cmbRole.getItems().addAll("Admin", "Manager", "Cashier", "Auditor");
        cmbStatus.getItems().addAll("Active", "Inactive");
    }

    private void configureDefaultFormStates() {
        txtUserId.setText("USR-" + System.currentTimeMillis() % 100000);

        txtMaxAttempts.setText("3");
        txtLockoutDuration.setText("15");
        cmbDuration.getSelectionModel().selectFirst();
        cmbStatus.getSelectionModel().selectFirst();
        cmbRole.getSelectionModel().selectFirst();
    }

    private void setupFormValidationRules() {
        validationSupport.registerValidator(txtFirstName, Validator.createEmptyValidator("First name is required."));
        validationSupport.registerValidator(txtLastName, Validator.createEmptyValidator("Last name is required."));
        validationSupport.registerValidator(txtUsername, Validator.createEmptyValidator("Username is required."));

        validationSupport.registerValidator(txtPhoneNumber, Validator.createPredicateValidator(
                input -> input != null && input.toString().matches("\\d{10}"),
                "Phone number must be exactly 10 digits."
        ));

        validationSupport.registerValidator(txtEmail, false, (control, innerValue) -> {
            String emailStr = (innerValue == null) ? "" : innerValue.toString().trim();
            boolean isValid = !emailStr.isEmpty() && !emailStr.matches("^[A-Za-z0-9+_.-]+@(.+)$");
            return ValidationResult.fromErrorIf(control,"Enter Valid email.", isValid);
        });

        validationSupport.registerValidator(dpStartDate, false, (control, innerValue) -> {
            java.time.LocalDate selectedDate = dpStartDate.getValue();

            if (selectedDate == null) {
                return org.controlsfx.validation.ValidationResult.fromError(control, "Employment start date is required.");
            }

            boolean isFutureDate = selectedDate.isAfter(java.time.LocalDate.now());

            return org.controlsfx.validation.ValidationResult.fromErrorIf(
                    control,
                    "Start date cannot be a future date.",
                    isFutureDate
            );
        });

        validationSupport.registerValidator(cmbRole, Validator.createEmptyValidator("Role profile must be assigned."));
        validationSupport.registerValidator(cmbStatus, Validator.createEmptyValidator("Status level selection is required."));

        validationSupport.registerValidator(txtPassword, Validator.createPredicateValidator(
                input -> input != null && input.toString().length() >= 6,
                "Password must contain at least 6 characters."
        ));

        validationSupport.registerValidator(txtConfirmPassword, false, (control, innerValue) -> {
            String originalPass = txtPassword.getText();
            String verificationPass = (innerValue == null) ? "" : innerValue.toString();
            boolean isMatching = !verificationPass.equals(originalPass);
            return ValidationResult.fromErrorIf(control, "Password does not match.", isMatching);
        });


    }

    private void configureActionListeners() {
        btnSave.setOnAction(event -> {
            if (validationSupport.isInvalid()) {
                StringBuilder errorReport = new StringBuilder("Please correct the following errors:\n");

                validationSupport.getValidationResult().getMessages().forEach(message -> {
                    errorReport.append("- ").append(message.getText()).append("\n");
                });

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Validation Error");
                alert.setHeaderText("Form Submission Incomplete");
                alert.setContentText(errorReport.toString());
                alert.showAndWait();

            } else {
                try {
                    User newUser = mapInputToUserEntity();
                    userService.saveUser(newUser);

                    openManageUserWindow(event);

                } catch (Exception e) {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Failed to compile user payload: " + e.getMessage());
                    errorAlert.showAndWait();
                }
            }
        });
    }

    private User mapInputToUserEntity() {
        Date parsedStartDate = null;
        if (dpStartDate.getValue() != null) {
            parsedStartDate = Date.from(
                    dpStartDate.getValue()
                            .atStartOfDay(java.time.ZoneId.systemDefault())
                            .toInstant()
            );
        }

        long duration = cmbDuration.getValue() == null ? 0L : Long.parseLong(cmbDuration.getValue());
        int maxAttempts = txtMaxAttempts.getText().trim().isEmpty() ? 3 : Integer.parseInt(txtMaxAttempts.getText().trim());
        int lockout = txtLockoutDuration.getText().trim().isEmpty() ? 15 : Integer.parseInt(txtLockoutDuration.getText().trim());

        User user = new User();
        user.setFirstName(txtFirstName.getText().trim());
        user.setLastName(txtLastName.getText().trim());
        user.setUserName(txtUsername.getText().trim());
        user.setEmail(txtEmail.getText().trim().toLowerCase());
        user.setPhoneNumber(txtPhoneNumber.getText().trim());
        user.setUserId(txtUserId.getText());
        user.setStartDate(parsedStartDate);
        user.setContractDuration(duration);
        user.setPassword(txtPassword.getText());
        user.setMaxLoginAttempts(maxAttempts);
        user.setLockoutDuration(lockout);
        user.setRole(cmbRole.getValue());
        user.setStatus(cmbStatus.getValue());
        user.setCreatedBy("Admin");
        user.setCreatedAt(new Date());

        return user;
    }




}
