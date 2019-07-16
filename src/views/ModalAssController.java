package views;

import controllers.CateringAppManager;
import models.*;
import java.util.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.collections.*;

import java.io.IOException;

public class ModalAssController {

  @FXML
  private ListView<User> cooks;

  @FXML
  private Button create;

  @FXML
  private TextArea description;


  @FXML
  public void initialize() {
    cooks.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    ObservableList<User> c = FXCollections.observableList(CateringAppManager.taskManager.getCurrentWorkshift().cooks);
    cooks.setItems(c);
  }

  @FXML
  private void createAssignment() {
    List<User> selection = cooks.getSelectionModel().getSelectedItems();
    String desc = description.getText();
    System.out.println(selection);
    System.out.println(desc);
    //CateringAppManager.taskManager.assignTask(desc, new ArrayList<User>(selection));
  }
}
