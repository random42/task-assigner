package models;

import java.util.*;

public class Event extends Model {

  public String name;
  public User chef;
  public Menu menu;
  public Collection<Task> tasks;

  public Event() {
    tasks = new ArrayList<>();
  }

  public Event(String n, User c, Menu m, Collection<Task> t) {
    name = n;
    chef = c;
    menu = m;
    tasks = t;
  }

}
