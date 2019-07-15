package models;

import java.util.*;

public class Assignment extends Model {

  public String description;
  public Task task;
  public boolean done;
  public Workshift workshift;
  public Set<User> cooks;

  public Assignment() {
    cooks = new HashSet<>();
  }

  public Assignment(String d, Task t, boolean d1, Workshift w, Set<User> c) {
    description = d;
    task = t;
    done = d1;
    workshift = w;
    cooks = c;
  }

  public String toString() {
    String d = ", ";
    return
      "(" + super.toString() + d + description + d + done + d + task.id + d
      + workshift.id + d + cooks.toString() + ")";
  }

}
