package objects;

import java.util.ArrayList;

public class Milestone extends Task{
    public Milestone(String taskName, Members taskOwner, String description, String deadline, String priority) {
        super(taskName, taskOwner, description, deadline, priority);
    }

    public Milestone(String taskName, Members taskOwner, String description, String deadline, String priority, ArrayList<Members> assignedMembers, int type) {
        super(taskName, taskOwner, description, deadline, priority, assignedMembers, type);
    }

    ArrayList<Task> milestoneTasksArrayList = new ArrayList<>();

    public ArrayList<Task> getMilestoneTasksArrayList() {
        return milestoneTasksArrayList;
    }

    public void setMilestoneTasksArrayList(ArrayList<Task> milestoneTasksArrayList) {
        this.milestoneTasksArrayList = milestoneTasksArrayList;
    }
}
