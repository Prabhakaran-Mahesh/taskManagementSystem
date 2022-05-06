package objects;

public class Issues extends Task{
    public Issues(String taskName, Members taskOwner, String description, String deadline, String priority) {
        super(taskName, taskOwner, description, deadline, priority);
    }
}
