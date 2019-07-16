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
  private ListView tasks;

  @FXML
  private ListView assignments;

  @FXML
  private ListView workshifts;

  @FXML
  public void initialize() {
    tasks.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    workshifts.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    assignments.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    ObservableList t = FXCollections.observableList(CateringAppManager.taskManager.getCurrentEvent().tasks);
    ObservableList w = FXCollections.observableList(CateringAppManager.taskManager.getWorkshifts());
    tasks.setItems(t);
    workshifts.setItems(w);
  }



}
