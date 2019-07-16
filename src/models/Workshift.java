package models;

import java.util.*;

public class Workshift extends Model {

  public Date from;
  public Date to;
  public List<User> cooks;
  public List<Assignment> assignments;

  public Workshift() {
    cooks = new ArrayList<>();
    assignments = new ArrayList<>();
  }

  public Workshift(Date from, Date to, List<User> cooks) {
    this.from = from;
    this.to = to;
    this.cooks = cooks;
  }

  public String toString() {
    String d = ", ";
    return
      "(" + super.toString() + d + from.toString() + d
      + to.toString() + d + cooks.toString() + d + assignments.size() + ")";
  }

}
