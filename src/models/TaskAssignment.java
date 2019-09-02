package models;

import java.util.*;

public class TaskAssignment extends Model {

  public String description;
  public Task task;
  public Boolean done;
  public Workshift workshift;
  public List<User> cooks;

  public TaskAssignment() {
    cooks = new ArrayList<>();
  }

  public TaskAssignment(String d, Task t, Workshift w, List<User> c) {
    description = d;
    done = false;
    task = t;
    workshift = w;
    cooks = c;
  }

  public String toString() {
    String d = ", ";
    return
    	"Descrizione: "+ description + d + "Terminato: " + (done ? "si" : "no") + d +
    	"Cuochi: " + cooks.toString();
  }

}
