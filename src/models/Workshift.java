package models;

import java.util.*;
import java.sql.Timestamp;

public class Workshift extends Model {

  public Timestamp from;
  public Timestamp to;
  public List<User> cooks;
  public List<Assignment> assignments;

  public Workshift() {
    cooks = new ArrayList<>();
    assignments = new ArrayList<>();
  }

  public Workshift(Timestamp from, Timestamp to, List<User> cooks) {
    this.from = from;
    this.to = to;
    this.cooks = cooks;
  }
  
  public static String time(Timestamp t) {
	  return t.toString().substring(11,16);
  }
  
  public static String date(Timestamp t) {
	  return t.toString().substring(0,10);
  }
 

  public String toString() {
    return
      date(from) + " da: " + time(from) + " a: " + time(to) + " Cuochi: " + cooks.toString();
  }
}
