package models;

import java.util.*;

public class Event extends Model {

  public String name;
  public User chef;
  public Menu menu;
  public ArrayList<Task> tasks;

  public Event(String n, User c, Menu m, ArrayList<Task> t) {
    name = n;
    chef = c;
    menu = m;
    tasks = t;
  }

}
