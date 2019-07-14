package db;

import models.*;
import java.sql.*;
import java.util.*;
import com.mysql.cj.jdbc.Driver;


public class DataManager {
  private String userName = "root";
  private String password = "ciaociao";
  private String serverName = "localhost";
  private String portNumber = "3306";

  private Connection connection;

  private Map<Integer,User> users = new HashMap<Integer,User>();
  private Map<Integer,Recipe> recipes = new HashMap<Integer,Recipe>();
  private Map<Integer,Menu> menus = new HashMap<Integer,Menu>();
  private Map<Integer,Task> tasks = new HashMap<Integer,Task>();
  private Map<Integer,Assignment> assignments = new HashMap<Integer,Assignment>();
  private Map<Integer,Workshift> workshifts = new HashMap<Integer,Workshift>();
  private Map<Integer,Event> events = new HashMap<Integer,Event>();

  public DataManager() {
  }

  public void initialize() throws SQLException {
      Connection conn = null;
      Properties connectionProps = new Properties();
      connectionProps.put("user", this.userName);
      connectionProps.put("password", this.password);
      connectionProps.put("useUnicode", true);
      connectionProps.put("useJDBCCompliantTimezoneShift", true);
      connectionProps.put("useLegacyDatetimeCode", false);
      connectionProps.put("serverTimezone", "UTC");
      conn = DriverManager.getConnection(
              "jdbc:mysql://" +
                      this.serverName +
                      ":" + this.portNumber + "/catering",
              connectionProps);

      System.out.println("Connected to database");
      this.connection = conn;
  }

  public User loadUser(String name) {
      PreparedStatement pst = null;
      String sql = "SELECT * FROM users WHERE name=?";
      User u = null;
      try {
          pst = this.connection.prepareStatement(sql);
          pst.setString(1, name);
          ResultSet rs = pst.executeQuery();
          if (rs.next()) {
            int id = rs.getInt("id");
            u = new User(rs.getString("name"), rs.getString("role"));
            this.users.put(id, u);
            u.id = id;
          }
          pst.close();
      } catch (SQLException exc) {
          exc.printStackTrace();
      } finally {
          try {
              if (pst != null) pst.close();
          } catch (SQLException exc2) {
              exc2.printStackTrace();
          }
      }
      return u;
  }
}
