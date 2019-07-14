package models;

import java.util.*;

public class Assignment extends Model {

  public String description;
  public Task task;
  public boolean done;
  public Workshift workshift;
  public ArrayList<User> cooks;

  public Assignment(String d, Task t, boolean d1, Workshift w, ArrayList<User> c) {
    description = d;
    task = t;
    done = d1;
    workshift = w;
    cooks = c;
  }

}
