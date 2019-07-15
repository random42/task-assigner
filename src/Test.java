import java.sql.SQLException;
import db.DataManager;

public class Test {

  public static void testDataManager(DataManager dm) {
    
  }

  public static void main(String[] args) {
    DataManager dm = new DataManager();
    try {
        dm.initialize();
        testDataManager(dm);
    } catch (SQLException exc) {
        exc.printStackTrace();
    }
  }
}
