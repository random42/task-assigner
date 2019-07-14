package models;

public class Task extends Model {

  public Event event;
  public String description;
  public boolean done;
  public int time;
  public Recipe recipe;

  public Task(Event e, String d, boolean d1, int t, Recipe r) {
    event = e;
    description = d;
    done = d1;
    time = t;
    recipe = r;
  }

  public boolean isDone() {
    return done;
  }

}
