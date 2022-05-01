package objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Members {
    String name;
    String email;
    String password;

    String type;

    ArrayList<Task> assignedTaskArrayList = new ArrayList<>();
    //ArrayList<Issue> assignedIssueArrayList = new ArrayList<>();

    ArrayList<Project> projectArrayList = new ArrayList<>();
    ArrayList<String> notification = new ArrayList<>();

    //ArrayList<MileStones> mileStonesArrayList = new ArrayList<>();

    HashMap<String, String> permissions = new HashMap<>();

    static public Scanner scanner = new Scanner(System.in);

    public Members(){}

    public Members(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = "Member";
    }

    public Members(String name, String email, String password, String type) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Task> getAssignedTaskArrayList() {
        return assignedTaskArrayList;
    }

    public void setAssignedTaskArrayList(ArrayList<Task> assignedTaskArrayList) {
        this.assignedTaskArrayList = assignedTaskArrayList;
    }

    public ArrayList<Project> getProjectArrayList() {
        return projectArrayList;
    }

    public void setProjectArrayList(ArrayList<Project> projectArrayList) {
        this.projectArrayList = projectArrayList;
    }

    public ArrayList<String> getNotification() {
        return notification;
    }

    public void setNotification(ArrayList<String> notification) {
        this.notification = notification;
    }

    public HashMap<String, String> getPermissions() {
        return permissions;
    }

    public void setPermissions(HashMap<String, String> permissions) {
        this.permissions = permissions;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void workOfMember(){

    }
}
