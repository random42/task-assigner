package models;

import java.util.*;

public class Workshift extends Model {

  public Date from;
  public Date to;
  public ArrayList<User> cooks;

  public Workshift(Date from, Date to, ArrayList<User> cooks) {
    this.from = from;
    this.to = to;
    this.cooks = cooks;
  }

}
