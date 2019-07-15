package models;

import java.util.*;

public class Workshift extends Model {

  public Date from;
  public Date to;
  public Set<User> cooks;
  public Collection<Assignment> assignments;

  public Workshift() {
    cooks = new HashSet<>();
    assignments = new ArrayList<>();
  }

  public Workshift(Date from, Date to, Set<User> cooks) {
    this.from = from;
    this.to = to;
    this.cooks = cooks;
  }

}
