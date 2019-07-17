package views;

import controllers.CateringAppManager;
import models.*;
import java.util.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.collections.*;

import java.io.IOException;

public class EventController {

  @FXML
  private ChoiceBox<Event> events;

  @FXML
  private Button action;


  @FXML
  public void initialize() {
    ObservableList<Event> list = FXCollections.observableList(CateringAppManager.taskManager.getEvents());
    events.setItems(list);
    action.setDisable(true);
    events.getSelectionModel().selectedIndexProperty().addListener((observable) -> {
    	action.setDisable(false);
    });
  }

  @FXML
  private void action() {
    Event selected = events.getSelectionModel().getSelectedItem();
    CateringAppManager.taskManager.chooseEvent(selected);
    cancel();
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
    Scene scene;
	try {
		scene = new Scene(fxmlLoader.load());
	} catch (IOException e) {
		e.printStackTrace();
		return;
	}
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.show();
  }
  
  @FXML
  private void cancel() {
	  Stage stage = (Stage) action.getScene().getWindow();
	  stage.close();
  }
}