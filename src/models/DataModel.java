package models;

import objects.Members;

import java.util.ArrayList;
import java.util.HashMap;

public class DataModel {

    static ArrayList<Members> membersArrayList = new ArrayList<>();
    static ArrayList<String> projectStatus = new ArrayList<>();
    static ArrayList<String> priority = new ArrayList<>();
    static ArrayList<String> taskStatus = new ArrayList<>();
    static  ArrayList<String> issueStatus = new ArrayList<>();
    static ArrayList<String> issueSeverity = new ArrayList<>();
    static ArrayList<String> issueClassification = new ArrayList<>();
    static ArrayList<String> recurringTaskType = new ArrayList<>();

    static ArrayList<String> configurations = new ArrayList<>();
    static HashMap<String, ArrayList<String>> typeOfUser = new HashMap<>();


    static public ArrayList<Members> getMembersArrayList() {
        return membersArrayList;
    }

    static public void setMembersArrayList() {
        membersArrayList.add(new Members("Prabha", "prabha@gmail.com", "prabha", "Manager"));
        membersArrayList.add(new Members("Member1", "member1@gmail.com", "member1", "Teamlead"));
        membersArrayList.add(new Members("Member2", "member2@gmail.com", "member2", "Teamlead"));
        membersArrayList.add(new Members("Member3", "member3@gmail.com", "member3", "Member"));
        membersArrayList.add(new Members("Member4", "member4@gmail.com", "member4", "Member"));
        membersArrayList.add(new Members("Member5", "member5@gmail.com", "member5", "Member"));
        membersArrayList.add(new Members("Member6", "member6@gmail.com", "member6", "Member"));
        membersArrayList.add(new Members("Member7", "member7@gmail.com", "member7", "Member"));
        membersArrayList.add(new Members("Member8", "member8@gmail.com", "member8", "Member"));
    }

    public static ArrayList<String> getModelProjectStatus() {
        return projectStatus;
    }

    public static void setModelProjectStatus() {
        projectStatus.add("Not yet Started");
        projectStatus.add("Implementation");
        projectStatus.add("Beta version Deployed");
        projectStatus.add("Deployed");
        projectStatus.add("Closed");
    }

    public static ArrayList<String> getPriority() {
        return priority;
    }

    public static void setPriority() {
        priority.add("None");
        priority.add("Low");
        priority.add("Medium");
        priority.add("High");
        priority.add("Very High");
    }

    public static ArrayList<String> getTaskStatus() {
        return taskStatus;
    }

    public static void setTaskStatus() {
        taskStatus.add("Not yet Started");
        taskStatus.add("Requirement Analysis");
        taskStatus.add("Designing");
        taskStatus.add("Implementation");
        taskStatus.add("Optimization");
        taskStatus.add("On hold");
        taskStatus.add("Delayed");
        taskStatus.add("Submitted for test");
        taskStatus.add("Issue Reported");
        taskStatus.add("Completed");
    }

    public static ArrayList<String> getIssueStatus() {
        return issueStatus;
    }

    public static void setIssueStatus() {
        issueStatus.add("Analysis");
        issueStatus.add("Implementation");
        issueStatus.add("Optimization");
        issueStatus.add("Submitted for Test");
        issueStatus.add("Committed");
        issueStatus.add("Delayed");
    }

    public static ArrayList<String> getRecurringTaskType() {
        return recurringTaskType;
    }

    public static void setRecurringTaskType() {
        recurringTaskType.add("None");
        recurringTaskType.add("Daily");
        recurringTaskType.add("Weekly");
        recurringTaskType.add("BiWeekly");
        recurringTaskType.add("Monthly");
        recurringTaskType.add("Annually");
    }

    public static ArrayList<String> getIssueSeverity() {
        return issueSeverity;
    }

    public static void setIssueSeverity() {
        issueSeverity.add("Critical");
        issueSeverity.add("Major");
        issueSeverity.add("Minor");
    }

    public static ArrayList<String> getIssueClassification() {
        return issueClassification;
    }

    public static void setIssueClassification() {
        issueClassification.add("Security");
        issueClassification.add("Crash/Hang");
        issueClassification.add("Data Loss");
        issueClassification.add("Performance");
        issueClassification.add("UI/Usability");
        issueClassification.add("Enhancement");
        issueClassification.add("Other bugs");
    }

    public static ArrayList<String> getConfigurations() {
        return configurations;
    }

    public static void setConfigurations() {
        configurations.add("Create Projects");
        configurations.add("Update Projects");
        configurations.add("Create Tasks");
        configurations.add("Update Tasks");
        configurations.add("Delete Tasks");
        configurations.add("Create Issues");
        configurations.add("Update Issues");
        configurations.add("Test");
        configurations.add("Update Deployment Status");
        configurations.add("Create Milestones");
        configurations.add("Update Milestones");
    }

    public static HashMap<String, ArrayList<String>> getTypeOfUser() {
        return typeOfUser;
    }

    public static void setTypeOfUser() {
        typeOfUser.put("Manager", getConfigurations());

        ArrayList<String> teamlead = new ArrayList<>();
        teamlead.add(getConfigurations().get(2));
        teamlead.add(getConfigurations().get(3));
        teamlead.add(getConfigurations().get(4));
        teamlead.add(getConfigurations().get(5));
        teamlead.add(getConfigurations().get(6));
        teamlead.add(getConfigurations().get(9));
        teamlead.add(getConfigurations().get(10));
        typeOfUser.put("Teamlead", teamlead);

        ArrayList<String> member = new ArrayList<>();
        member.add(getConfigurations().get(3));
        typeOfUser.put("Member", member);
    }
}
