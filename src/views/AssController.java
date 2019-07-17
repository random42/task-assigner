package views;

import controllers.CateringAppManager;
import models.*;
import java.util.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.collections.*;

import java.io.IOException;

public class AssController {

  @FXML
  private ListView<User> cooks;

  @FXML
  private Button action;

  @FXML
  private TextField description;


  @FXML
  public void initialize() {
    cooks.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    ObservableList<User> c = FXCollections.observableList(CateringAppManager.taskManager.getCurrentWorkshift().cooks);
    cooks.setItems(c);
  }

  @FXML
  private void action() {
    List<User> selection = cooks.getSelectionModel().getSelectedItems();
    String desc = description.getText();
    boolean check = selection.size() > 0 && desc.length() > 0;
    if (!check)
    	return;
    CateringAppManager.taskManager.assignTask(desc, new ArrayList<User>(selection));
    cancel();
  }
  
  @FXML
  private void cancel() {
	  Stage stage = (Stage) action.getScene().getWindow();
	  stage.close();
  }
}
