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
      loadData();
  }

  public void clearMaps() {
    users.clear();
    recipes.clear();
    menus.clear();
    tasks.clear();
    assignments.clear();
    workshifts.clear();
    events.clear();
  }

  public void printData() {
    System.out.println("\nUSERS\n");
    System.out.println(users.values());
    System.out.println("\nRECIPES\n");
    System.out.println(recipes.values());
    System.out.println("\nMENUS\n");
    System.out.println(menus.values());
    System.out.println("\nTASKS\n");
    System.out.println(tasks.values());
    System.out.println("\nASSIGNMENTS\n");
    System.out.println(assignments.values());
    System.out.println("\nWORKSHIFTS\n");
    System.out.println(workshifts.values());
    System.out.println("\nEVENTS\n");
    System.out.println(events.values());
  }

  public void loadData() {
    loadUsers();
    loadMenusRecipes();
    loadEventsTasks();
    loadWorkshifts();
    loadAssignments();
  }

  public void loadUsers() {
    String query = "SELECT * FROM users";
    try {
      Statement st = this.connection.createStatement();
      ResultSet rs = st.executeQuery(query);
      while (rs.next()) {
        User u = new User(rs.getString("name"), rs.getString("role"));
        u.id = rs.getInt("id");
        this.users.put(u.id, u);
      }
      st.close();
    } catch (SQLException exc) {
      exc.printStackTrace();
    }
  }

  public void loadWorkshifts() {
    Statement st = null;
    String query = "SELECT * FROM workshifts w JOIN workshift_cooks wc ON "
    + "w.id = wc.workshift";
    try {
      st = this.connection.createStatement();
      ResultSet rs = st.executeQuery(query);
      if (!rs.next()) return;
      while (!rs.isAfterLast()) {
        Workshift current = new Workshift();
        int id = rs.getInt("workshift");
        current.id = id;
        current.from = rs.getDate("from_date");
        current.to = rs.getDate("to_date");
        this.workshifts.put(id, current);
        while (!rs.isAfterLast() && rs.getInt("workshift") == id) {
          int c = rs.getInt("cook");
          User cook = this.users.get(c);
          current.cooks.add(cook);
          rs.next();
        }
      }
      st.close();
    } catch (SQLException exc) {
      exc.printStackTrace();
    }
  }

  public void loadAssignments() {
    String query = "SELECT * FROM assignments a JOIN assignment_cooks ac "
    + "ON a.id = ac.assignment";
    try {
      Statement st = this.connection.createStatement();
      ResultSet rs = st.executeQuery(query);
      if (!rs.next()) return;
      while (!rs.isAfterLast()) {
        Assignment current = new Assignment();
        current.id = rs.getInt("id");
        current.description = rs.getString("description");
        current.task = this.tasks.get(rs.getInt("task"));
        current.done = rs.getBoolean("done");
        current.workshift = this.workshifts.get(rs.getInt("workshift"));
        current.workshift.assignments.add(current);
        this.assignments.put(current.id, current);
        while (!rs.isAfterLast() && rs.getInt("id") == current.id) {
          current.cooks.add(this.users.get(rs.getInt("cook")));
          rs.next();
        }
      }
      st.close();
    } catch (SQLException exc) {
      exc.printStackTrace();
    }

  }

  public void loadMenusRecipes() {
    String query = "SELECT * FROM menus m LEFT JOIN menu_recipes mr ON "
    + "m.id = mr.menu JOIN recipes r ON mr.recipe = r.id";
    try {
      Statement st = this.connection.createStatement();
      ResultSet rs = st.executeQuery(query);
      if (!rs.next()) return;
      while (!rs.isAfterLast()) {
        Menu current = new Menu();
        current.id = rs.getInt("menu");
        current.title = rs.getString("title");
        this.menus.put(current.id, current);
        while (!rs.isAfterLast() && rs.getInt("menu") == current.id) {
          if (rs.getInt("recipe") != 0) {
            Recipe r = new Recipe(rs.getString("name"));
            r.id = rs.getInt("recipe");
            current.recipes.add(r);
            this.recipes.put(r.id, r);
          }
          rs.next();
        }
      }
      st.close();
    } catch (SQLException exc) {
      exc.printStackTrace();
    }
  }

  public void loadEventsTasks() {
    String query = "SELECT e.id as event_id, e.name as name, e.chef as chef, e.menu as menu, t.id as task_id, t.description as description, t.time as time, t.done as done, t.recipe as recipe "
    + " FROM events e LEFT JOIN tasks t ON e.id = t.event";
    try {
      Statement st = this.connection.createStatement();
      ResultSet rs = st.executeQuery(query);
      if (!rs.next()) return;
      while (!rs.isAfterLast()) {
        Event current = new Event();
        current.id = rs.getInt("event_id");
        current.name = rs.getString("name");
        current.chef = this.users.get(rs.getInt("chef"));
        current.menu = this.menus.get(rs.getInt("menu"));
        this.events.put(current.id, current);
        while (!rs.isAfterLast() && rs.getInt("event_id") == current.id) {
          if (rs.getInt("task_id") != 0) {
            Task t = new Task();
            t.id = rs.getInt("task_id");
            t.event = current;
            t.description = rs.getString("description");
            t.done = rs.getBoolean("done");
            t.time = rs.getInt("time");
            t.recipe = this.recipes.get(rs.getInt("recipe"));
            this.tasks.put(t.id, t);
            current.tasks.add(t);
          }
          rs.next();
        }
      }
      st.close();
    } catch (SQLException exc) {
      exc.printStackTrace();
    }
  }

  public Collection<Event> getEvents(User chef) {
    String query = "SELECT id FROM events WHERE chef=?";
    Collection<Event> e = new ArrayList<>();
    try {
      PreparedStatement st = this.connection.prepareStatement(query);
      st.setInt(1, chef.id);
      ResultSet rs = st.executeQuery();
      while (rs.next()) {
        e.add(this.events.get(rs.getInt("id")));
      }
      st.close();
    } catch (SQLException exc) {
      exc.printStackTrace();
    }
    return e;
  }

  public User getUser(String name) {
    String query = "SELECT id FROM users WHERE name=?";
    User u = null;
    try {
      PreparedStatement st = this.connection.prepareStatement(query);
      st.setString(1, name);
      ResultSet rs = st.executeQuery();
      if (!rs.next()) {
        return null;
      }
      u = this.users.get(rs.getInt("id"));
      st.close();
    } catch (SQLException exc) {
      exc.printStackTrace();
    }
    return u;
  }

  public void createTask(Task task) {
    String sql = "INSERT INTO tasks(description,time,done,toPrepare,recipe,event)\n"
    + "VALUES (?,?,?,?,?,?)";
    try {
      PreparedStatement st = this.connection.prepareStatement(sql,
        Statement.RETURN_GENERATED_KEYS);
      st.setString(1, task.description);
      st.setInt(2, task.time);
      st.setInt(3, task.done ? 1 : 0);
      st.setInt(4, task.toPrepare ? 1 : 0);
      st.setInt(5, task.recipe.id);
      st.setInt(6, task.event.id);
      int r = st.executeUpdate();
      if (r != 1) return;
      ResultSet rs = st.getGeneratedKeys();
      if (rs.next()) {
        task.id = rs.getInt(1);
        this.tasks.put(task.id, task);
      }
      st.close();
    } catch (SQLException exc) {
      exc.printStackTrace();
    }
  }

  public void updateTask(Task task) {
    String sql = "UPDATE tasks SET description=? time=? done=? toPrepare=? recipe=? WHERE id=?";
    try {
      PreparedStatement st = this.connection.prepareStatement(sql);
      st.setString(1, task.description);
      st.setInt(2, task.time);
      st.setInt(3, task.done ? 1 : 0);
      st.setInt(4, task.toPrepare ? 1 : 0);
      st.setInt(5, task.recipe.id);
      st.setInt(6, task.id);
      st.executeUpdate();
      st.close();
    } catch (SQLException exc) {
      exc.printStackTrace();
    }
  }

  public void deleteTask(Task task) {
    String sql = "DELETE FROM tasks WHERE id=?";
    try {
      PreparedStatement st = this.connection.prepareStatement(sql);
      st.setInt(1, task.id);
      st.executeUpdate();
      st.close();
      this.tasks.remove(task.id);
    } catch (SQLException exc) {
      exc.printStackTrace();
    }
  }

  public void createAssignment(Assignment a) {
    String sql = "INSERT INTO assignments(description,done,task,workshift)\n"
    + "VALUES (?,?,?,?)";
    try {
      PreparedStatement st = this.connection.prepareStatement(sql,
        Statement.RETURN_GENERATED_KEYS);
      st.setString(1, a.description);
      st.setInt(2, a.done ? 1 : 0);
      st.setInt(3, a.task.id);
      st.setInt(4, a.workshift.id);
      int r = st.executeUpdate();
      if (r != 1) return;
      ResultSet rs = st.getGeneratedKeys();
      if (rs.next()) {
        a.id = rs.getInt(1);
        this.assignments.put(a.id, a);
      }
      st.close();
      sql = "INSERT INTO assignment_cooks(cook,assignment)\n"
      + "VALUES ";
      for (int i = 0; i < a.cooks.size() - 1; i++) {
        sql += "(?,?),\n";
      }
      sql += "(?,?);";
      st = this.connection.prepareStatement(sql);
      int i = 1;
      for (User c : a.cooks) {
        st.setInt(i, c.id);
        st.setInt(i+1, a.id);
        i+=2;
      }
      r = st.executeUpdate();
      st.close();
    } catch (SQLException exc) {
      exc.printStackTrace();
    }
  }

  public void deleteAssignment(Assignment a) {
    String sql = "DELETE FROM assignment_cooks WHERE assignment=?;\n"
    + "DELETE FROM assignment WHERE id=?";
    try {
      PreparedStatement st = this.connection.prepareStatement(sql);
      st.setInt(1, a.id);
      st.setInt(2, a.id);
      st.executeUpdate();
      st.close();
      this.assignments.remove(a.id);
    } catch (SQLException exc) {
      exc.printStackTrace();
    }
  }
}
