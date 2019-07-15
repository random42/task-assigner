package models;

public class Task extends Model {

  public Event event;
  public String description;
  public Boolean done;
  public Boolean toPrepare;
  public Integer time;
  public Recipe recipe;

  public Task() {}

  public Task(Event e, String d, Boolean t, Recipe r, Integer t1) {
    event = e;
    description = d;
    done = false;
    toPrepare = t;
    time = t1;
    recipe = r;
  }

  public Boolean isDone() {
    return done;
  }

  public String toString() {
    String d = ", ";
    return
      "(" + super.toString() + d + description + d + done + d + toPrepare + d
      + time + d + recipe.id + d + event.id + ")";
  }

}
