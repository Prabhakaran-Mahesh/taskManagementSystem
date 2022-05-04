package objects;

import java.util.ArrayList;

public class Project {
    String projectId;
    String projectName;
    String projectDescription;
    Members projectOwner;
    String deadline;
    String duration;
    String status;
    ArrayList<Members> teamMemberArrayList = new ArrayList<>();
    ArrayList<String> chatGroup = new ArrayList<>();
    ArrayList<String> fileManager = new ArrayList<>();
    ArrayList<Task> taskArrayList = new ArrayList<>();
    ArrayList<Task> cancelledTaskArrayList = new ArrayList<>();
    ArrayList<Milestone> mileStonesArrayList = new ArrayList<>();
    ArrayList<String> workflow = new ArrayList<>();
    ArrayList<Object> recycleBin = new ArrayList<>();
    ArrayList<String> materials = new ArrayList<>();

    public Project(String projectName, String projectDescription, String deadline) {
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.deadline = deadline;
        this.status = "Not yet started";
    }

    public Project(String projectName, Members projectOwner, String projectDescription, String deadline, ArrayList<Members> teamMemberArrayList) {
        this.projectName = projectName;
        this.projectOwner = projectOwner;
        this.projectDescription = projectDescription;
        this.deadline = deadline;
        this.status = "Not yet Started";
        this.teamMemberArrayList = teamMemberArrayList;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Members getProjectOwner() {
        return projectOwner;
    }

    public void setProjectOwner(Members projectOwner) {
        this.projectOwner = projectOwner;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
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

    public ArrayList<Members> getTeamMemberArrayList() {
        return teamMemberArrayList;
    }

    public void setTeamMemberArrayList(ArrayList<Members> teamMemberArrayList) {
        this.teamMemberArrayList = teamMemberArrayList;
    }


    public ArrayList<String> getChatGroup() {
        return chatGroup;
    }

    public void setChatGroup(ArrayList<String> chatGroup) {
        this.chatGroup = chatGroup;
    }

    public ArrayList<String> getFileManager() {
        return fileManager;
    }

    public void setFileManager(ArrayList<String> fileManager) {
        this.fileManager = fileManager;
    }

    public ArrayList<Task> getTaskArrayList() {
        return taskArrayList;
    }

    public void setTaskArrayList(ArrayList<Task> taskArrayList) {
        this.taskArrayList = taskArrayList;
    }

    public ArrayList<Task> getCancelledTaskArrayList() {
        return cancelledTaskArrayList;
    }

    public void setCancelledTaskArrayList(ArrayList<Task> cancelledTaskArrayList) {
        this.cancelledTaskArrayList = cancelledTaskArrayList;
    }

    public ArrayList<String> getWorkflow() {
        return workflow;
    }

    public void setWorkflow(ArrayList<String> workflow) {
        this.workflow = workflow;
    }

    public ArrayList<Object> getRecycleBin() {
        return recycleBin;
    }

    public void setRecycleBin(ArrayList<Object> recycleBin) {
        this.recycleBin = recycleBin;
    }

    public ArrayList<String> getMaterials() {
        return materials;
    }

    public void setMaterials(ArrayList<String> materials) {
        this.materials = materials;
    }

    public ArrayList<Milestone> getMileStonesArrayList() {
        return mileStonesArrayList;
    }

    public void setMileStonesArrayList(ArrayList<Milestone> mileStonesArrayList) {
        this.mileStonesArrayList = mileStonesArrayList;
    }
}

