package views;

import controllers.CateringAppManager;
import models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

  @FXML
  private Label userName;

  @FXML
  private Button create;

  @FXML
  private Button delete;

  @FXML
  private Button edit;

  @FXML
  private Button assign;

  @FXML
  private Button deleteAss;

  @FXML
  private ListView<Task> tasks;

  @FXML
  private ListView<Assignment> assignments;

  @FXML
  private ListView<Workshift> workshifts;

  @FXML
  public void initialize() {
    tasks.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    workshifts.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    assignments.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    ObservableList<Task> t = FXCollections.observableList(CateringAppManager.taskManager.getCurrentEvent().tasks);
    ObservableList<Workshift> w = FXCollections.observableList(CateringAppManager.taskManager.getWorkshifts());
    tasks.setItems(t);
    workshifts.setItems(w);
  }

  @FXML
  private void createTask() {
	System.out.println("createTask");
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("task.fxml"));
    Scene scene;
	try {
		scene = new Scene(fxmlLoader.load());
	} catch (IOException e) {
		e.printStackTrace();
		return;
	}
	TaskController tc = fxmlLoader.getController();
    tc.setType(TaskController.Type.CREATE);
    Stage stage = new Stage();
    stage.setTitle("Create task");
    stage.setScene(scene);
    stage.showAndWait();
  }

  @FXML
  private void deleteTask() {

  }

  @FXML
  private void editTask() {

  }

  @FXML
  private void assignTask() {

  }

  @FXML
  private void deleteAssignment() {

  }

}
