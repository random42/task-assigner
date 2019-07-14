package controllers;

import db.DataManager;

import java.sql.SQLException;

public class CateringAppManager {
    private static CateringAppManager singleInstance;
    public static UserManager userManager;
    public static TaskManager taskManager;

    // il data manager non è presente nel DSD perché non fa parte della business logic
    public static DataManager dataManager;

    public static CateringAppManager getInstance() {
        if (CateringAppManager.singleInstance == null){
            CateringAppManager.singleInstance = new CateringAppManager();
        }
        return CateringAppManager.singleInstance;
    }
    private CateringAppManager() {
        CateringAppManager.dataManager = new DataManager();
        CateringAppManager.userManager = new UserManager();
        CateringAppManager.taskManager = new TaskManager();
        // Inizializza i GRASP controller e i servizi da utilizzare
        try {
            CateringAppManager.dataManager.initialize();
        } catch (SQLException exc) {
            // Rimando l'eccezione a terminale
            exc.printStackTrace();
        }
        CateringAppManager.userManager.initialize();
        CateringAppManager.taskManager.initialize();
    }


}
