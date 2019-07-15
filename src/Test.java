import java.sql.SQLException;
import db.DataManager;
import controllers.*;
import models.*;

public class Test {

  public static void testDataManager(DataManager dm) {
    dm.printData();
  }

  public static void main(String[] args) {
    CateringAppManager.initialize();
    testDataManager(CateringAppManager.dataManager);
  }
}
