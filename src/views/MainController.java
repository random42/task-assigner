package views;

import controllers.CateringAppManager;
import models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainController {

    @FXML
    private BorderPane mainPane;

    @FXML
    private Label userName;

    @FXML
    public void initialize() {
      String name = CateringAppManager.userManager.getCurrentUser().toString();
      userName.setText(name);
    }
}
