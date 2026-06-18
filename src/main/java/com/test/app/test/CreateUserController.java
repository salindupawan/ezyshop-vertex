package com.test.app.test;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateUserController {

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

    @FXML
    private void createUser(ActionEvent event) {

    }
}
