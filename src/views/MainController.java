package views;

import controllers.CateringAppManager;
import models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

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



}
