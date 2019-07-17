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

public class TaskController {

  private Task task;

  public void setTask(Task t) {
	  task = t;
	  description.setText(t.description);
      toPrepare.setSelected(t.toPrepare);
      if (t.time != null) {
        time.setText(Integer.toString(t.time));
      }
      recipe.getSelectionModel().select(t.recipe);
      action.setText("Salva");
  }

  @FXML
  private ChoiceBox<Recipe> recipe;

  @FXML
  private CheckBox toPrepare;

  @FXML
  private TextField description;

  @FXML
  private TextField time;

  @FXML
  private Button action;

  @FXML
  private Button cancel;


  @FXML
  public void initialize() {
    ObservableList<Recipe> r = FXCollections.observableList(CateringAppManager.taskManager.getCurrentEvent().menu.recipes);
    r.add(0, null);
    recipe.setItems(r);
  }

  @FXML
  private void action() {
    Recipe r = recipe.getSelectionModel().getSelectedItem();
    String desc = description.getText();
    Boolean tp = toPrepare.isSelected();
    Integer t = 0;
    boolean checkFields = desc.length() > 0 && r != null;
    if (time.getText().trim().length() > 0) {
      try {
        t = Integer.valueOf(time.getText().trim());
      } catch(NumberFormatException e) {
        System.out.println("Must enter an integer.");
        return;
      }
    }
    if (!checkFields)
    	return;
    if (this.task == null) {
    	CateringAppManager.taskManager.createTask(desc, tp, r, t);
    }
      
    else {
    	CateringAppManager.taskManager.editTask(desc, tp, r, t);
    }
      
    cancel();
  }

  @FXML
  private void cancel() {
    Stage stage = (Stage) action.getScene().getWindow();
    stage.close();
  }
}
