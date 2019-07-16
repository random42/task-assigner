package controllers;

import models.*;
import java.util.*;
import receivers.TaskEventReceiver;

public class TaskManager {
  private List<TaskEventReceiver> receivers = new ArrayList<>();
  private Task currentTask;
  private Event currentEvent;
  private Workshift currentWorkshift;
  private Assignment currentAssignment;

  public TaskManager() {}

  public List<Workshift> getWorkshifts() {
    return CateringAppManager.dataManager.getWorkshifts();
  }

  public List<Event> getEvents() {
    User chef = CateringAppManager.userManager.getCurrentUser();
    return CateringAppManager.dataManager.getEvents(chef);
  }

  public Workshift getCurrentWorkshift() {
    return currentWorkshift;
  }

  public Task getCurrentTask() {
    return currentTask;
  }

  public Assignment getCurrentAssignment() {
    return currentAssignment;
  }

  public Event getCurrentEvent() {
    return currentEvent;
  }

  public void chooseEvent(Event e) {
    User u = CateringAppManager.userManager.getCurrentUser();
    if (u != e.chef) {
      throw new UseCaseLogicException("User must be the event's chef.");
    }
    currentEvent = e;
  }

  public void chooseTask(Task t) {
    currentTask = t;
  }

  public void chooseWorkshift(Workshift w) {
    currentWorkshift = w;
  }

  public void chooseAssignment(Assignment a) {
    currentAssignment = a;
  }

  public Task createTask(String description, Boolean toPrepare, Recipe recipe, Integer time) {
    if (currentEvent == null) {
      throw new UseCaseLogicException("Creating task with no event chosen.");
    }
    Task t = new Task(currentEvent, description, toPrepare, recipe, time);
    currentTask = t;
    for (TaskEventReceiver r : receivers) {
      r.notifyTaskCreated(t);
    }
    return t;
  }

  public void editTask(String description, Boolean toPrepare, Recipe recipe, Integer time) {
    if (currentTask == null) {
      throw new UseCaseLogicException("Choose task before editing it.");
    }
    Task t = currentTask;
    t.description = description;
    t.toPrepare = toPrepare;
    t.recipe = recipe;
    t.time = time;
    for (TaskEventReceiver r : receivers) {
      r.notifyTaskEdited(currentTask);
    }
  }

  public void deleteTask() {
    if (currentTask == null) {
      throw new UseCaseLogicException("Choose task before deleting it.");
    }
    for (TaskEventReceiver r : receivers) {
      r.notifyTaskDeleted(currentTask);
    }
    currentTask = null;
  }

  public Assignment assignTask(String description, List<User> cooks) {
    if (currentTask == null || currentWorkshift == null) {
      throw new UseCaseLogicException("Choose task and workshift before creating an assignment.");
    }
    Assignment a = new Assignment(description, currentTask, currentWorkshift, cooks);
    currentAssignment = a;
    for (TaskEventReceiver r : receivers) {
      r.notifyTaskAssigned(a);
    }
    return a;
  }

  public void deleteAssignment() {
    if (currentAssignment == null) {
      throw new UseCaseLogicException("Choose assignment before deleting it.");
    }
    for (TaskEventReceiver r : receivers) {
      r.notifyAssignmentDeleted(currentAssignment);
    }
    currentAssignment = null;
  }

  public void initialize() {
  }

  public void addReceiver(TaskEventReceiver r) {
    receivers.add(r);
  }
}
