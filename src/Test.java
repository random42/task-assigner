import java.sql.SQLException;
import db.DataManager;
import models.*;

public class Test {

  public static void testDataManager(DataManager dm) {
    System.out.println("DATA\n");
    dm.printData();
  }

  public static void main(String[] args) {
    DataManager dm = new DataManager();
    try {
        dm.initialize();
    } catch (SQLException exc) {
        exc.printStackTrace();
    }
    testDataManager(dm);
  }
}
