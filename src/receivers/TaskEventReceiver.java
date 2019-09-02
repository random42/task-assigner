package receivers;

import models.*;

public interface TaskEventReceiver {
  public void notifyTaskCreated(Task t);
  public void notifyTaskEdited(Task t);
  public void notifyTaskDeleted(Task t);
  public void notifyTaskAssigned(TaskAssignment a);
  public void notifyAssignmentDeleted(TaskAssignment a);
}
