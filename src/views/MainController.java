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
import java.util.ArrayList;

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
	 userName.setText("Utente: " + CateringAppManager.userManager.getCurrentUser().name);
    tasks.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    workshifts.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    assignments.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    Button[] disable = {delete,edit,assign,deleteAss};
    for (Button b : disable) {
    	b.setDisable(true);
    }
    ObservableList<Task> t = FXCollections.observableList(CateringAppManager.taskManager.getCurrentEvent().tasks);
    ObservableList<Workshift> w = FXCollections.observableList(CateringAppManager.taskManager.getWorkshifts());
    tasks.setItems(t);
    workshifts.setItems(w);
    
    tasks.getSelectionModel().selectedIndexProperty().addListener((observable) -> {
        Task task = tasks.getSelectionModel().getSelectedItem();
        CateringAppManager.taskManager.chooseTask(task);
        ArrayList<Button> able = new ArrayList<>();
        able.add(edit);
        if (CateringAppManager.taskManager.getCurrentWorkshift() != null) {
        	able.add(assign);
        }
        for (Button b : able) {
        	b.setDisable(false);
        }
        delete.setDisable(!CateringAppManager.taskManager.canTaskBeDeleted());
    });
    workshifts.getSelectionModel().selectedIndexProperty().addListener((observable) -> {
    	Workshift ws = workshifts.getSelectionModel().getSelectedItem();
    	CateringAppManager.taskManager.chooseWorkshift(ws);
    	if (CateringAppManager.taskManager.getCurrentTask() != null) {
        	assign.setDisable(false);
        }
    	refreshAssignments();
    });
    
    assignments.getSelectionModel().selectedIndexProperty().addListener((observable) -> {
    	Assignment a = assignments.getSelectionModel().getSelectedItem();
    	CateringAppManager.taskManager.chooseAssignment(a);
    	deleteAss.setDisable(false);
    });
  }
  
  @FXML
  private void refreshTasks() {
	  ObservableList<Task> t = FXCollections.observableList(CateringAppManager.taskManager.getCurrentEvent().tasks);
	  tasks.setItems(t);
	  tasks.getSelectionModel().clearSelection();
	  Button[] disable = {delete,edit,assign};
	    for (Button b : disable) {
	    	b.setDisable(true);
	    }
  }
  
  @FXML
  private void refreshAssignments() {
	  Workshift w = CateringAppManager.taskManager.getCurrentWorkshift();
	  ObservableList<Assignment> a = FXCollections.observableList(w == null ? new ArrayList<>() : w.assignments);
	  assignments.setItems(a);
	  assignments.getSelectionModel().clearSelection();
	  boolean del = CateringAppManager.taskManager.canTaskBeDeleted();
	  delete.setDisable(!del);
	  deleteAss.setDisable(true);
  }

  @FXML
  private void createTask() {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("task.fxml"));
    Scene scene;
	try {
		scene = new Scene(fxmlLoader.load());
	} catch (IOException e) {
		e.printStackTrace();
		return;
	}
	TaskController tc = fxmlLoader.getController();
    Stage stage = new Stage();
    stage.setTitle("Create task");
    stage.setScene(scene);
    stage.showAndWait();
    refreshTasks();
  }

  @FXML
  private void editTask() {
	  Task t = CateringAppManager.taskManager.getCurrentTask();
	  if (t == null) return;
	    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("task.fxml"));
	    Scene scene;
		try {
			scene = new Scene(fxmlLoader.load());
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		TaskController tc = fxmlLoader.getController();
	    tc.setTask(t);
	    Stage stage = new Stage();
	    stage.setTitle("Edit task");
	    stage.setScene(scene);
	    stage.showAndWait();
	    refreshTasks();
  }

  @FXML
  private void deleteTask() {
	  CateringAppManager.taskManager.deleteTask();
	  refreshTasks();
  }

  @FXML
  private void assignTask() {
	  if (CateringAppManager.taskManager.getCurrentTask() == null || CateringAppManager.taskManager.getCurrentWorkshift() == null) {
		  return;
	  }
	  
	    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ass.fxml"));
	    Scene scene;
		try {
			scene = new Scene(fxmlLoader.load());
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	    Stage stage = new Stage();
	    stage.setTitle("Assign task");
	    stage.setScene(scene);
	    stage.showAndWait();
	    refreshAssignments();
  }

  @FXML
  private void deleteAssignment() {
	  CateringAppManager.taskManager.deleteAssignment();
	  refreshAssignments();
  }
  

}
