package models;

public class Task extends Model {

  public Event event;
  public String description;
  public boolean done;
  public boolean toPrepare;
  public int time;
  public Recipe recipe;

  public Task() {}

  public Task(Event e, String d, boolean d1, boolean t, int t1, Recipe r) {
    event = e;
    description = d;
    done = d1;
    toPrepare = t;
    time = t1;
    recipe = r;
  }

  public boolean isDone() {
    return done;
  }

  public String toString() {
    String d = ", ";
    return
      "(" + super.toString() + d + description + d + done + d + toPrepare + d
      + time + d + recipe.id + d + event.id + ")";
  }

}
