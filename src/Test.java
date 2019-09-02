import db.DataManager;
import controllers.*;
import models.*;
import java.util.*;

public class Test {

  public static void testTaskManager() {
    DataManager dm = CateringAppManager.dataManager;
    TaskManager tm = CateringAppManager.taskManager;
    Event e1 = dm.events.get(1);
    List<Recipe> recipes = e1.menu.recipes;
    tm.chooseEvent(e1);
    Task t = tm.createTask("Daje", true, recipes.get(0), 120);
    Workshift w = dm.workshifts.get(1);
    tm.chooseTask(t);
    tm.chooseWorkshift(w);
    TaskAssignment a = tm.assignTask("fate tutto", w.cooks);
    dm.printTasks();
    dm.printAssignments();
    tm.chooseAssignment(a);
    tm.deleteAssignment();
    dm.printAssignments();
    tm.deleteTask();
    dm.printTasks();
    dm.clearMaps();
    dm.loadData();
    dm.printData();
  }

  public static void main(String[] args) {
    CateringAppManager.initialize();
    testTaskManager();
  }
}
