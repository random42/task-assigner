package controllers;

import db.DataManager;

import java.sql.SQLException;

public class CateringAppManager {
  public static UserManager userManager;
  public static TaskManager taskManager;
  public static DataManager dataManager;

  public static void initialize() {
    CateringAppManager.dataManager = new DataManager();
    CateringAppManager.userManager = new UserManager();
    CateringAppManager.taskManager = new TaskManager();
    try {
        CateringAppManager.dataManager.initialize();
    } catch (SQLException exc) {
        exc.printStackTrace();
    }
    CateringAppManager.userManager.initialize();
    CateringAppManager.taskManager.initialize();
  }
}
