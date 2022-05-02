package objects;

import java.util.ArrayList;
import java.util.HashMap;

public class Task {
    String id;
    String taskName;
    String taskOwner;
    String description;
    String startDate;
    String deadline;
    String duration;
    String status;
    String priority;
    ArrayList<Task> subTask = new ArrayList<>();
    double percentage;
    HashMap<String, Integer> recurrance = new HashMap<>();
    ArrayList<String> comments = new ArrayList<>();
    ArrayList<String> starredComments = new ArrayList<>();
    //ArrayList<Issue> associatedIssues = new ArrayList<>();
    ArrayList<String> documents = new ArrayList<>();
    String remainder;
    ArrayList<String> activityStream = new ArrayList<>();
    ArrayList<Members> assignedMembers = new ArrayList<>();
    ArrayList<Members> followers = new ArrayList<>();
    ArrayList<String> forums = new ArrayList<>();
    String links;

    public Task(String taskName, String taskOwner, String description, String deadline, String priority) {
        this.taskName = taskName;
        this.taskOwner = taskOwner;
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
        this.status = "Not yet Started";
        this.links = "https://task_management_system/project/"+taskName.replace(" ", "_")+"/";
    }

    public Task(String taskName, String taskOwner, String description, String deadline, String priority, ArrayList<Members> assignedMembers, int type) {
        this.taskName = taskName;
        this.taskOwner = taskOwner;
        this.description = description;
        if(type ==1){
            this.deadline = deadline;
        }
        else{
            this.duration = deadline;
        }
        this.status = "Not yet Started";
        this.priority = priority;
        this.assignedMembers = assignedMembers;
        this.links = "https://task_management_system/project/"+taskName.replace(" ", "_")+"/";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskOwner() {
        return taskOwner;
    }

    public void setTaskOwner(String taskOwner) {
        this.taskOwner = taskOwner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public ArrayList<Task> getSubTask() {
        return subTask;
    }

    public void setSubTask(ArrayList<Task> subTask) {
        this.subTask = subTask;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public HashMap<String, Integer> getRecurrance() {
        return recurrance;
    }

    public void setRecurrance(HashMap<String, Integer> recurrance) {
        this.recurrance = recurrance;
    }

    public ArrayList<String> getComments() {
        return comments;
    }

    public void setComments(ArrayList<String> comments) {
        this.comments = comments;
    }

    public ArrayList<String> getStarredComments() {
        return starredComments;
    }

    public void setStarredComments(ArrayList<String> starredComments) {
        this.starredComments = starredComments;
    }

    /*public ArrayList<Issue> getAssociatedIssues() {
        return associatedIssues;
    }

    public void setAssociatedIssues(ArrayList<Issue> associatedIssues) {
        this.associatedIssues = associatedIssues;
    }*/

    public ArrayList<String> getDocuments() {
        return documents;
    }

    public void setDocuments(ArrayList<String> documents) {
        this.documents = documents;
    }

    public String getRemainder() {
        return remainder;
    }

    public void setRemainder(String remainder) {
        this.remainder = remainder;
    }

    public ArrayList<String> getActivityStream() {
        return activityStream;
    }

    public void setActivityStream(ArrayList<String> activityStream) {
        this.activityStream = activityStream;
    }

    public ArrayList<Members> getAssignedMembers() {
        return assignedMembers;
    }

    public void setAssignedMembers(ArrayList<Members> assignedMembers) {
        this.assignedMembers = assignedMembers;
    }

    public ArrayList<Members> getFollowers() {
        return followers;
    }

    public void setFollowers(ArrayList<Members> followers) {
        this.followers = followers;
    }

    public ArrayList<String> getForums() {
        return forums;
    }

    public void setForums(ArrayList<String> forums) {
        this.forums = forums;
    }

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }
}

