package objects;

import activities.Validation;
import activities.WelcomePage;
import com.opencsv.CSVWriter;
import models.DataModel;
import models.DesignModel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;

public class Members {
    String name;
    String email;
    String password;

    String type;

    ArrayList<Task> assignedTaskArrayList = new ArrayList<>();
    ArrayList<Issues> assignedIssueArrayList = new ArrayList<>();

    ArrayList<Project> projectArrayList = new ArrayList<>();
    ArrayList<String> notification = new ArrayList<>();
    ArrayList<String> workflow = new ArrayList<>();

    //ArrayList<MileStones> mileStonesArrayList = new ArrayList<>();

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<String> getWorkflow() {
        return workflow;
    }

    public void setWorkflow(ArrayList<String> workflow) {
        this.workflow = workflow;
    }

    public ArrayList<Issues> getAssignedIssueArrayList() {
        return assignedIssueArrayList;
    }

    public void setAssignedIssueArrayList(ArrayList<Issues> assignedIssueArrayList) {
        this.assignedIssueArrayList = assignedIssueArrayList;
    }

    // This function is used to create a sample project with 5 tasks. so that i dont want to create project each time i run.
    public void sampleProject(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        ArrayList<Members>membersArrayList = new ArrayList<>();
        for(Members members : DataModel.getMembersArrayList()){
            if(!members.getType().equalsIgnoreCase("Manager")){
                membersArrayList.add(members);

            }
        }

        membersArrayList.get(0).setType("Teamlead");
        membersArrayList.get(1).setType("Tester");

        Project project = new Project("Project1", this, "This is project 1", "07-01-2023", membersArrayList);

        System.out.println("\n\t\tProject created!");

        for(Members m : membersArrayList){
            m.getProjectArrayList().add(project);
            m.getWorkflow().add("Project : "+ "Project1" + " has been Created! on "+ formatter.format(date));
        }
        projectArrayList.add(project);
        getWorkflow().add("Project : "+ "Project1" + " has been Created! on "+ formatter.format(date));
        DesignModel.printLine();


        Task task1 = new Task("Task1", project.getProjectOwner(), "This is task 1", "-", DataModel.getPriority().get(0));
        project.getTaskArrayList().add(task1);
        for(Members members : membersArrayList){
            members.getAssignedTaskArrayList().add(task1);
            task1.getAssignedMembers().add(members);
            members.getNotification().add("\t\t\tTask ->" + task1.getTaskName() + " Added!");
        }

        Task task2 = new Task("Task2", project.getProjectOwner(), "This is task 2", "-", DataModel.getPriority().get(1));
        project.getTaskArrayList().add(task2);
        for(Members members : membersArrayList){
            members.getAssignedTaskArrayList().add(task2);
            task2.getAssignedMembers().add(members);
            members.getNotification().add("\t\t\tTask ->" + task2.getTaskName() + " Added!");
        }

        Task task3 = new Task("Task3", project.getProjectOwner(), "This is task 3", "-", DataModel.getPriority().get(2));
        project.getTaskArrayList().add(task3);
        for(Members members : membersArrayList){
            members.getAssignedTaskArrayList().add(task3);
            task3.getAssignedMembers().add(members);
            members.getNotification().add("\t\t\tTask ->" + task3.getTaskName() + " Added!");
        }

        Task task4 = new Task("Task4", project.getProjectOwner(), "This is task 1", "-", DataModel.getPriority().get(3));
        project.getTaskArrayList().add(task4);
        for(Members members : membersArrayList){
            members.getAssignedTaskArrayList().add(task4);
            task4.getAssignedMembers().add(members);
            members.getNotification().add("\t\t\tTask ->" + task4.getTaskName() + " Added!");
        }

        Task task5 = new Task("Task5", project.getProjectOwner(), "This is task 5", "-", DataModel.getPriority().get(0));
        project.getTaskArrayList().add(task5);
        for(Members members : membersArrayList){
            members.getAssignedTaskArrayList().add(task5);
            task5.getAssignedMembers().add(members);
            members.getNotification().add("\t\t\tTask ->" + task5.getTaskName() + " Added!");
        }
    }

    // This function is used to logout verification of a user
    public void exitVerification(){
        String exit;
        while(true){
            System.out.print("\t\tAre you sure, Do you want to Logout? yes/no : ");
            exit = scanner.next();
            if(exit.equalsIgnoreCase("yes") || exit.equalsIgnoreCase("no")){
                break;
            }
            else{
                System.out.println("\n\t\tInvalidInput\n");
                DesignModel.printLine();
                System.out.println("\n");
            }
        }

        if("yes".equalsIgnoreCase(exit)){
            System.out.println();
            DesignModel.printLine();
            WelcomePage.loginDisplay();
            System.exit(0);

        }
        else{
            System.out.println();
            DesignModel.printLine();

        }
    }

    // This function is used to change the user password
    public void changePassword(){
        String oldPassword, newPassword;
        System.out.println();
        DesignModel.printLine();
        System.out.println("\t\tPassword Change");
        System.out.print("\t\t\tEnter your Current Password : ");
        oldPassword = scanner.next();
        if(this.getPassword().equals(oldPassword)){
            System.out.print("\t\t\tEnter your New Password : ");
            newPassword = scanner.next();

            this.setPassword(newPassword);
            System.out.println("\t\tPassword Altered");
            DesignModel.printLine();
        }
        else{
            System.out.println("\t\tOld Password Incorrect");
            DesignModel.printLine();
        }
    }

    // This function is used to add members to the organisation
    public void addMembersToTheCompany(){
        String name, password, email;

        System.out.println();
        DesignModel.printLine();
        System.out.println("\t\tAdd a User to Your Team");

        System.out.print("\t\t\tEnter Name of the User : ");
        while ((name = scanner.nextLine()).isEmpty()) {
            System.out.print("Enter a Valid Project name : ");
        }

        System.out.print("\t");
        email = Validation.emailValidation();

        System.out.print("\t\t\tEnter Temporary Password : ");
        while ((password = scanner.nextLine()).isEmpty()) {
            System.out.print("Enter a Valid password : ");
        }
        Members member = new Members(name, email, password, "Member");
        DataModel.getMembersArrayList().add(member);
        System.out.println("\t\tUser added to the Organisation");
        DesignModel.printLine();
    }

    // This function is used to create a user type
    public String setUserType(){
        String types;
        System.out.print("\n\t\tEnter Type name : ");
        while ((types = scanner.nextLine()).isEmpty()) {
            System.out.print("Enter a Valid Name : ");
        }

        int i=0;
        for(String config : DataModel.getConfigurations()){
            i++;
            System.out.println("\t\t"+i+". "+config);
        }
        System.out.println("\n\t\tSelect the Permissions that you allow for the users of type " + types + "\n");
        ArrayList<String> configurations = new ArrayList<>();

        System.out.println("\n\t\tEnter your choices\n");
        int memberChoice = -1;
        while(true){
            while(memberChoice == -1){
                System.out.print("\t\t S.no: ");
                memberChoice = Validation.numberCheck(scanner);
            }

            if(memberChoice == -2){
                break;
            }
            else if(memberChoice<1 || memberChoice>DataModel.getConfigurations().size()){
                System.out.println("\n\t\t S.no not found!");
                memberChoice = -1;
                continue;
            }
            configurations.add(DataModel.getConfigurations().get(memberChoice-1));
            memberChoice = -1;

        }

        DataModel.getTypeOfUser().put(types, configurations);
        System.out.println("\t\tType of User Added Successfully!");
        return types;
    }

    // This function is used to edit user type according to the input. Each user type.
    public void editUserType(){
        System.out.println("\n\t\tEdit User Type");
        int i=0;
        for(Members members : DataModel.getMembersArrayList()){
            i++;
            System.out.println("\t\t"+i+". "+members.getName()+"\t\t"+members.getType());
        }
        System.out.println("\n\t\tEnter s.no of which User Type You want to change\t\tEnter -1 to go back");
        int choice;
        while(true) {
            do {
                System.out.print("\n\t\tEnter your choice : ");
                choice = Validation.numberCheck(scanner);
            } while (choice == -1);

            if (choice == 0 || choice < -2 || choice > DataModel.getMembersArrayList().size()) {
                System.out.println("\t\tWrong Input!");
            } else if (choice == -2) {
                break;
            } else{
                Members member = DataModel.getMembersArrayList().get(choice-1);
                System.out.println();
                i=0;
                for(String key : DataModel.getTypeOfUser().keySet().stream().toList()){
                    i++;
                    System.out.println("\n\t\t" + i + ". " + key + " : Permissions Allowed\n");
                    for(String configs : DataModel.getTypeOfUser().get(key)){
                        System.out.println("\t\t\t"+configs);
                    }
                }
                System.out.println("\n\t\t Enter the S.no of User Type who you want to edit Permissions.\n\t\t Enter 0 to add new user Type.\n\t\t Enter -1 to go back");
                int c;
                do {
                    System.out.print("\n\t\tEnter your choice : ");
                    c = Validation.numberCheck(scanner);
                } while (c == -1);
                if(c > DataModel.getTypeOfUser().keySet().size() || c < -2){
                    System.out.println("\t\tEnter correct number!");
                } else if(c == -2){
                    break;
                } else if(c == 0){
                    String types = this.setUserType();
                    member.setType(types);
                    break;
                } else {
                    String typeofUser = DataModel.getTypeOfUser().keySet().stream().toList().get(c - 1);
                    member.setType(typeofUser);
                    break;
                }
            }
        }
    }

    // This function is used to create projects for the managers respective organisation
    public void createProjects(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        String projectName, description, deadline;
        ArrayList<Members> memberArrayList = new ArrayList<>();
        ArrayList<Members> memberEligibleForTester = new ArrayList<>();

        System.out.println();
        DesignModel.printLine();
        System.out.println("\t\tCreate a Project");

        System.out.print("\t\t\tEnter Name of the Project * : ");
        //projectName = scanner.nextLine();
        while ((projectName = scanner.nextLine()).isEmpty()) {
            System.out.print("Enter a Valid Project name : ");
        }

        System.out.print("");
        System.out.print("\t\t\tProduct Description : ");
        //scanner.nextLine();
        description = scanner.nextLine();
        System.out.print("");
        if(description.isEmpty()){
            description = "No Description";
        }

        while(true){
            System.out.println("\n\t\t\tEnter 1 to give a Deadline. \n\t\t\tEnter 2 to give Duration. \n\t\t\tEnter 3 to skip");
            int dead = -1;
            while(dead == -1){
                System.out.print("\t\t S.no: ");
                dead = Validation.numberCheck(scanner);
            }

            if(dead == 1){
                int flag = 0;
                do {
                    System.out.println("\t\t\tEnter -1 to go back");
                    System.out.print("\t\t\tProject Deadline (Date format : dd-MM-yyyy) : ");
                    deadline = scanner.next();
                    if(deadline.equalsIgnoreCase("-1")){
                        flag = 1;
                        break;
                    }

                } while (!Validation.dateValidation(deadline));
                if(flag == 0) {
                    break;
                }
            }
            else if(dead == 2){
                int flag=-1;
                System.out.println("\t\t\tProject Duration : ");
                System.out.println("\n\t\t\tEnter -1 to go back");
                System.out.println("\t\t\tEnter 1 to Set years");
                System.out.println("\t\t\tEnter 2 to Set months");
                System.out.println("\t\t\tEnter 3 to Set weeks");
                System.out.println("\t\t\tEnter 4 to Set days");
                System.out.print("\t\t\tEnter your choice : ");

                while(flag == -1){
                    System.out.print("\t\t S.no: ");
                    flag = Validation.numberCheck(scanner);
                }

                if(flag == -2){
                    continue;
                } else if(flag == 1){
                    System.out.println("\n\t\t\tEnter number of Years");
                    deadline = Validation.numberCheck(scanner) +" Years";
                    break;
                } else if(flag == 2){
                    System.out.println("\n\t\t\tEnter number of Months");
                    deadline = Validation.numberCheck(scanner) +" Months";
                } else if(flag == 3){
                    System.out.println("\n\t\t\tEnter number of Weeks");
                    deadline = Validation.numberCheck(scanner) +" Weeks";
                } else if(flag == 4){
                    System.out.println("\n\t\t\tEnter number of Days");
                    deadline = Validation.numberCheck(scanner) +" Days";
                } else{
                    System.out.println("\t\t\tWrong input!");
                    continue;
                }
                break;
            } else if(dead == 3){
                deadline = "   -   ";
                break;
            }
            else{
                System.out.println("\t\t\tWrong input!");
            }
        }

        ArrayList<Members> membersArrayList = new ArrayList<>();
        for(Members members : DataModel.getMembersArrayList()){
            if(!members.getType().equalsIgnoreCase("Manager")){
                membersArrayList.add(members);
            }
        }


        System.out.print("\n\t\t\tEnter the ID number of the users you want for this project\n\t\t\tEnter -1 to stop. ");
        int i=0;
        for(Members m : membersArrayList){
            i++;
            System.out.print("\n\t\t S.no : " + i + ". Name : " + m.getName());
        }

        System.out.println("\n\t\tEnter your choices\n");
        int memberChoice = -1;
        while(true){
            while(memberChoice == -1){
                System.out.print("\t\t S.no: ");
                memberChoice = Validation.numberCheck(scanner);
            }

            if(memberChoice == -2){
                break;
            }
            else if(memberChoice<1 || memberChoice>membersArrayList.size()){
                System.out.println("\n\t\t S.no not found!");
                memberChoice = -1;
                continue;
            }
            memberArrayList.add(membersArrayList.get(memberChoice-1));
            memberEligibleForTester.add(membersArrayList.get(memberChoice-1));
            memberChoice = -1;
        }

        System.out.println();
        DesignModel.printLine();

        if(memberArrayList.size() == 0){
            Project project = new Project(projectName, description, deadline);
            projectArrayList.add(project);
            this.getWorkflow().add("Project : "+ projectName + " has been Created! on "+ formatter.format(date));
            System.out.println("\n\t\tProject created!");
            return;
        }
        else {
            System.out.println("\t\tMembers Added to the Project : ");
            i = 0;
            for (Members m : memberArrayList) {
                i++;
                System.out.print("\n\t\t\t S.no : " + i + ". Name : " + m.getName());
            }
            System.out.println();
            DesignModel.printLine();

            System.out.print("\t\tChoose your ProjectLead/ ProjectLeads! Enter -1 to stop. Enter their");
            memberChoice = -1;
            while (true) {
                while (memberChoice == -1) {
                    System.out.print("\t\t S.no: ");
                    memberChoice = Validation.numberCheck(scanner);
                }

                if (memberChoice == -2) {
                    break;
                } else if (memberChoice < 1 || memberChoice > memberArrayList.size()) {
                    System.out.println("\n\t\t S.no not found!");
                    memberChoice = -1;
                    continue;
                }
                Members mem = memberArrayList.get(memberChoice - 1);
                memberEligibleForTester.remove(mem);

                mem.setType("Teamlead");
                memberChoice = -1;
            }

            System.out.println();
            DesignModel.printLine();
            System.out.println("\t\tMembers Added to the Project Except TeamLeaders: ");

            i = 0;
            for (Members m : memberEligibleForTester) {
                i++;
                System.out.print("\n\t\t\t S.no : " + i + ". Name : " + m.getName());
            }
            System.out.println();
            DesignModel.printLine();
        }

        if(memberEligibleForTester.size() == 0){

            Project project = new Project(projectName, this,description, deadline, memberArrayList);
            System.out.println("\n\t\tProject created!");
            for(Members m : memberArrayList){
                m.getProjectArrayList().add(project);
                m.getWorkflow().add("Project : "+ projectName + " has been Created! on "+ formatter.format(date));
            }
            projectArrayList.add(project);
            this.getWorkflow().add("Project : "+ projectName + " has been Created! on "+ formatter.format(date));
            return;
        }
        else{
            System.out.print("\t\tChoose your Tester");
            memberChoice = -1;
            while(true){
                while(memberChoice == -1){
                    System.out.print("\t\t S.no: ");
                    memberChoice = Validation.numberCheck(scanner);
                }

                if(memberChoice == -2){
                    Project project = new Project(projectName, this, description, deadline, memberArrayList);

                    projectArrayList.add(project);
                    for(Members m : memberArrayList){
                        m.getProjectArrayList().add(project);
                    }
                    System.out.println("\n\t\tProject created!");
                    return;
                }
                else if(memberChoice<1 || memberChoice>memberEligibleForTester.size()){
                    System.out.println("\n\t\tS.no not found!");
                    memberChoice = -1;
                }
                else{
                    break;
                }
            }
            //System.out.println(memberChoice);
            Members mem = memberEligibleForTester.get(memberChoice-1);
            mem.setType("Tester");
        }


        Project project = new Project(projectName, this, description, deadline, memberArrayList);

        System.out.println("\n\t\tProject created!");

        for(Members m : memberArrayList){
            m.getProjectArrayList().add(project);
            m.getWorkflow().add("Project : "+ projectName + " has been Created! on "+ formatter.format(date));
        }
        projectArrayList.add(project);
        getWorkflow().add("Project : "+ projectName + " has been Created! on "+ formatter.format(date));
        DesignModel.printLine();
    }

    // This function is used to add excess members to the project. after creating the project
    public void addMembersToProject(Project selectedProject){
        System.out.println();
        DesignModel.printLine();

        ArrayList<Members> additionMemberArrayList = DataModel.getMembersArrayList();

        for(Members member : selectedProject.getTeamMemberArrayList()) {
            additionMemberArrayList.remove(member);
        }

        System.out.print("\n\t\t\tEnter the ID number of the users you want for this project\n\t\t\tEnter -1 to stop. ");
        int i=0;
        for(Members m : additionMemberArrayList){

            i++;
            System.out.print("\n\t\t S.no : " + i + ". Name : " + m.getName());


        }

        System.out.println("\n\t\tEnter your choices\n");
        int memberChoice = -1;
        while(true){
            while(memberChoice == -1){
                System.out.print("\t\t S.no: ");
                memberChoice = Validation.numberCheck(scanner);
            }

            if(memberChoice == -2){
                break;
            }
            else if(memberChoice<1 || memberChoice>additionMemberArrayList.size()){
                System.out.println("\n\t\t S.no not found!");
                memberChoice = -1;
                continue;
            }
            selectedProject.getTeamMemberArrayList().add(additionMemberArrayList.get(memberChoice-1));
            additionMemberArrayList.get(memberChoice-1).getProjectArrayList().add(selectedProject);
            memberChoice = -1;
        }
        System.out.println("\n\t\tMembers Added to the Project!");

        /*System.out.println("\t\t This user has the role as " + additionMemberArrayList.get(memberChoice-1).getType(). "\t Do you want to change it to : ");
        i=0;
        for(String types : DataModel.getTypeOfUser().keySet().stream().toList()){
            i++;
            System.out.println("s.no. " + i + "  " + types);
        }*/



        System.out.println();
        DesignModel.printLine();


    }

    // This function is used to edit the project details like name, desc, status..
    public void editProjectDetails(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        int choice=1;

        if(getProjectArrayList().size()==1){
            Project selectedProject = projectArrayList.get(choice-1);
        }
        else{
            while(true){
                System.out.print("\n\t\tEnter the s.no of the Project which you want to update : ");
                choice = Validation.numberCheck(scanner);
                if(choice>0 && choice<=projectArrayList.size()){
                    break;
                }
                else{
                    System.out.println("\t\tWrong input");
                }
            }
        }
        Project selectedProject = projectArrayList.get(choice-1);

        boolean update = true;
        while(update){
            System.out.println("\n\t\t\tEnter the s.no of credential you want to change!");
            System.out.println("\t\t\t Enter 1 to Project Name ");
            System.out.println("\t\t\t Enter 2 to Project Deadline");
            System.out.println("\t\t\t Enter 3 to Project Description");
            System.out.println("\t\t\t Enter 4 to Project Status");
            System.out.println("\t\t\t Enter 5 to Add Members to the Project");
            System.out.println("\t\t\t Enter -1 to back\n");

            int updateChoice = -1;
            while(updateChoice == -1){
                System.out.print("\t\t\t Enter your Choice : ");
                updateChoice = Validation.numberCheck(scanner);
            }

            switch (updateChoice){
                case -2 -> {
                    update = false;

                    System.out.println();
                    DesignModel.printLine();
                }

                case 1 -> {
                    System.out.println("\n\t\tCurrent Name : " + selectedProject.getProjectName());
                    System.out.print("\t\tEnter the new Project Name : ");
                    String chat;
                    while ((chat = scanner.nextLine()).isEmpty()) {
                        System.out.print("Enter a Valid Project name : ");
                    }
                    System.out.print("");

                    if(Validation.messageValidation(chat)){
                        selectedProject.setProjectName(chat);
                    }
                }

                case 2 -> {
                    System.out.println("\n\t\tCurrent Deadline : " + selectedProject.getDeadline());
                    String deadline;


                    while(true){
                        System.out.println("\n\t\t\tEnter 1 to give a Deadline. \n\t\t\tEnter 2 to give Duration. \n\t\t\tEnter 3 to skip");
                        int dead = -1;
                        while(dead == -1){
                            System.out.print("\t\t S.no: ");
                            dead = Validation.numberCheck(scanner);
                        }

                        if(dead == 1){
                            int flag = 0;
                            do {
                                System.out.println("\t\t\tEnter -1 to go back");
                                System.out.print("\t\t\tProject Deadline (Date format : dd-MM-yyyy) : ");
                                deadline = scanner.next();
                                if(deadline.equalsIgnoreCase("-1")){
                                    flag = 1;
                                    break;
                                }

                            } while (!Validation.dateValidation(deadline));
                            if(flag == 0) {
                                break;
                            }
                        }
                        else if(dead == 2){
                            int flag=-1;
                            System.out.println("\t\t\tProject Duration : ");
                            System.out.println("\n\t\t\tEnter -1 to go back");
                            System.out.println("\t\t\tEnter 1 to Set years");
                            System.out.println("\t\t\tEnter 2 to Set months");
                            System.out.println("\t\t\tEnter 3 to Set weeks");
                            System.out.println("\t\t\tEnter 4 to Set days");
                            System.out.print("\t\t\tEnter your choice : ");

                            while(flag == -1){
                                System.out.print("\t\t S.no: ");
                                flag = Validation.numberCheck(scanner);
                            }

                            if(flag == -2){
                                continue;
                            } else if(flag == 1){
                                System.out.print("\n\t\t\tEnter number of Years : ");
                                deadline = Validation.numberCheck(scanner) +" Years";
                                break;
                            } else if(flag == 2){
                                System.out.print("\n\t\t\tEnter number of Months : ");
                                deadline = Validation.numberCheck(scanner) +" Months";
                            } else if(flag == 3){
                                System.out.print("\n\t\t\tEnter number of Weeks : ");
                                deadline = Validation.numberCheck(scanner) +" Weeks";
                            } else if(flag == 4){
                                System.out.print("\n\t\t\tEnter number of Days : ");
                                deadline = Validation.numberCheck(scanner) +" Days";
                            } else{
                                System.out.print("\t\t\tWrong input!");
                                continue;
                            }
                            break;
                        } else if(dead == 3){
                            deadline = "   -   ";
                            break;
                        }
                        else{
                            System.out.println("\t\t\tWrong input!");
                        }
                    }

                    selectedProject.setDeadline(deadline);
                }

                case 3 -> {
                    System.out.println("\n\t\tCurrent Description : " + selectedProject.getProjectDescription());
                    String description;
                    System.out.print("\t\t\tNew Product Description : ");
                    description = scanner.nextLine();
                    System.out.print("");
                    if(description.isEmpty()){
                        description = "No Description";
                    }

                    selectedProject.setProjectDescription(description);
                }

                case 4 -> {
                    int i=0;
                    for(String stat : DataModel.getModelProjectStatus()){
                        i++;
                        System.out.println("\t\t" + i + ". " + stat);
                    }

                    System.out.println("\n\t\tCurrent Status : " + selectedProject.getStatus());

                    System.out.println("\n\t\tEnter 1 to select a Status. Enter 2 to add Custom Status");
                    int select = -1;
                    while(select == -1){
                        System.out.print("\t\t\t Enter your Choice : ");
                        select = Validation.numberCheck(scanner);
                        if(select < 1 || select > 2){
                            System.out.println("\t\tWrong input! Enter 1 or 2");
                            select = -1;
                        }
                    }
                    if(select == 1){
                        int status=-1;
                        while(status == -1){
                            System.out.print("\t\t\t Enter your Choice of Status : ");
                            status = Validation.numberCheck(scanner);
                            if(status > DataModel.getModelProjectStatus().size() || status < 1){
                                System.out.println("\t\t Wrong input!");
                            }
                        }
                        //System.out.print("\t\tEnter the S.no :");
                        selectedProject.setStatus(DataModel.getModelProjectStatus().get(status-1));
                        getWorkflow().add("Project : "+ selectedProject.getProjectName() + " status Has been Updated! to " + DataModel.getModelProjectStatus().get(status-1) + " on " + formatter.format(date));
                    }
                    else{
                        System.out.print("\t\tEnter the custom Status : ");
                        String chat;
                        //scanner.nextLine();
                        chat = scanner.nextLine();
                        System.out.print("");

                        DataModel.getModelProjectStatus().add(chat);
                        getWorkflow().add("Project : "+ selectedProject.getProjectName() + " status Has been Updated! to " + chat + " on " + formatter.format(date));
                        selectedProject.setStatus(chat);
                    }

                }

                case 5 -> addMembersToProject(selectedProject);

                default -> System.out.println("\n\tWrong value. Give correct input number!\n");
            }
        }

    }

    //This function is used to view the list of projects
    public void viewProjects(){
        System.out.println();
        DesignModel.printLine();

        System.out.println("\t\tProject Details\n");

        if(getProjectArrayList().size() == 0){
            System.out.print("\t\tNo Projects found!\n");
            DesignModel.printLine();
        }
        else{
            System.out.printf("\n\t\t%15s %15s %15s %25s %35s\n", "S.no", "ProjectName", "Deadline", "Status", "Description");
            int i=0;
            for(Project project : getProjectArrayList()){

                i++;
                System.out.printf("\t\t%15s %15s %15s %25s %35s\n", i, project.getProjectName(), project.getDeadline(), project.getStatus(), project.getProjectDescription());
            }
            DesignModel.printLine();

            System.out.println("\n\t\tDo you want to update Project Details? Enter 1 to yes, Enter -1 to no");
            int ver;
            while(true){
                System.out.print("\t\tEnter your choice : ");
                ver = Validation.numberCheck(scanner);
                if(ver == -2 || ver == 1) {
                    break;
                }
                else{
                    System.out.println("\t\tWrong input");
                }
            }

            if(ver == 1){
                editProjectDetails();
            }
        }

    }

    // This function is used to create tasks in a project. Main and important function of the task management system
    public void createTasks(){
        //ArrayList<Task> taskArrayList = new ArrayList<>();

        System.out.println();
        DesignModel.printLine();

        if(projectArrayList.size() == 0){
            System.out.print("\t\tNo Projects found!\n");
            DesignModel.printLine();
        }
        else {
            int choice;
            if(projectArrayList.size() == 1){
                choice = 1;
            }
            else{
               System.out.printf("\n\t\t%15s %15s %15s %25s %35s\n", "S.no", "ProjectName", "Deadline", "Status", "Description");
                int i = 0;
                for (Project project : projectArrayList) {

                    i++;
                    System.out.printf("\t\t%15s %15s %15s %25s %35s\n", i, project.getProjectName(), project.getDeadline(), project.getStatus(), project.getProjectDescription());
                }
                DesignModel.printLine();



                while(true){
                    System.out.print("\n\t\tEnter the s.no of the Project which you want to add Tasks : ");
                    choice = Validation.numberCheck(scanner);
                    if(choice>0 && choice<=projectArrayList.size()){
                        break;
                    }
                    else{
                        System.out.println("\t\tWrong input");
                    }
                }
            }

            Project selectedProject = projectArrayList.get(choice-1);


            System.out.println("\t\tAdd tasks to the Project");

            boolean done = false;
            while (!done) {
                System.out.println("\t\t\t1. Add task");
                System.out.println("\t\t\t-1. Task Adding completed");
                do {
                    System.out.print("\n\t\tEnter your choice : ");
                    choice = Validation.numberCheck(scanner);
                } while (choice == -1);

                if (choice == 1) {
                    Task task;

                    String taskName, taskDescription, taskDeadline;

                    System.out.print("\t\t\tEnter Name of the Task : ");
                    while ((taskName = scanner.nextLine()).isEmpty()) {
                        System.out.print("Enter a Valid Task name : ");
                    }

                    System.out.print("\t\t\tTask Description : ");
                    //scanner.nextLine();
                    taskDescription = scanner.nextLine();
                    System.out.print("");
                    if(taskDescription.isEmpty()){
                        taskDescription = "No Description";
                    }
                    System.out.print("");


                    while(true){
                        System.out.println("\n\t\t\tEnter 1 to give a Deadline. \n\t\t\tEnter 2 to give Duration. \n\t\t\tEnter 3 to skip");
                        int dead = -1;
                        while(dead == -1){
                            System.out.print("\t\t S.no: ");
                            dead = Validation.numberCheck(scanner);
                        }

                        if(dead == 1){
                            int flag = 0;
                            do {
                                System.out.println("\t\t\tEnter -1 to go back");
                                System.out.print("\t\t\tProject Deadline (Date format : dd-MM-yyyy) : ");
                                taskDeadline = scanner.next();
                                if(taskDeadline.equalsIgnoreCase("-1")){
                                    flag = 1;
                                    break;
                                }

                            } while (!Validation.dateValidation(taskDeadline));
                            if(flag == 0) {
                                break;
                            }
                        }
                        else if(dead == 2){
                            int flag=-1;
                            System.out.println("\t\t\tProject Duration : ");
                            System.out.println("\n\t\t\tEnter -1 to go back");
                            System.out.println("\t\t\tEnter 1 to Set years");
                            System.out.println("\t\t\tEnter 2 to Set months");
                            System.out.println("\t\t\tEnter 3 to Set weeks");
                            System.out.println("\t\t\tEnter 4 to Set days");
                            System.out.print("\t\t\tEnter your choice : ");

                            while(flag == -1){
                                System.out.print("\t\t S.no: ");
                                flag = Validation.numberCheck(scanner);
                            }

                            if(flag == -2){
                                continue;
                            } else if(flag == 1){
                                System.out.println("\n\t\t\tEnter number of Years");
                                taskDeadline = Validation.numberCheck(scanner) +" Years";
                                break;
                            } else if(flag == 2){
                                System.out.println("\n\t\t\tEnter number of Months");
                                taskDeadline = Validation.numberCheck(scanner) +" Months";
                            } else if(flag == 3){
                                System.out.println("\n\t\t\tEnter number of Weeks");
                                taskDeadline = Validation.numberCheck(scanner) +" Weeks";
                            } else if(flag == 4){
                                System.out.println("\n\t\t\tEnter number of Days");
                                taskDeadline = Validation.numberCheck(scanner) +" Days";
                            } else{
                                System.out.println("\t\t\tWrong input!");
                                continue;
                            }
                            break;
                        } else if(dead == 3){
                            taskDeadline = "   -   ";
                            break;
                        }
                        else{
                            System.out.println("\t\t\tWrong input!");
                        }
                    }


                    System.out.println("\t\tPriority List : ");

                    int i = 0;
                    for (String m : DataModel.getPriority()) {
                        i++;
                        System.out.print("\n\t\t\t S.no : " + i + ". " + m);
                    }
                    System.out.println();
                    DesignModel.printLine();

                    System.out.print("\t\tChoose task Priority! Enter");
                    int priorityChoice = -1;
                    while (true) {
                        while (priorityChoice == -1) {
                            System.out.print("\t\t S.no: ");
                            priorityChoice = Validation.numberCheck(scanner);
                        }

                        if (priorityChoice < 1 || priorityChoice > DataModel.getPriority().size()) {
                            System.out.println("\n\t\t S.no not found!");
                            priorityChoice = -1;
                        } else {
                            break;
                        }
                    }
                    task = new Task(taskName, selectedProject.getProjectOwner(), taskDescription, taskDeadline, DataModel.getPriority().get(priorityChoice - 1));
                    selectedProject.getTaskArrayList().add(task);

                    System.out.println("\t\t\tSelect Members for the task. Choose their S.no. Enter -1 to Stop");
                    i = 0;
                    int mem;
                    for (Members m : selectedProject.getTeamMemberArrayList()) {
                        i++;
                        System.out.println("\t\t\tS.no. " + i + " Name : " + m.getName());
                    }
                    System.out.println();
                    while (true) {

                        System.out.print("\t\t\tS.no : ");
                        mem = scanner.nextInt();
                        if (mem == -1) {
                            break;
                        } else if (mem < -1 || mem > selectedProject.getTeamMemberArrayList().size() || mem == 0) {
                            System.out.println("\n\t\t User not found! Enter the correct S.no");
                        } else {
                            selectedProject.getTeamMemberArrayList().get(mem - 1).getAssignedTaskArrayList().add(task);
                            task.getAssignedMembers().add(selectedProject.getTeamMemberArrayList().get(mem - 1));
                            selectedProject.getTeamMemberArrayList().get(mem - 1).getNotification().add("\t\t\tTask ->" + task.getTaskName() + " Added!");
                        }
                    }

                    System.out.println("\t\t\tSelect Followers for the task. Choose their S.no. Enter -1 to Stop");
                    i = 0;
                    ArrayList<Members> membersOption = new ArrayList<>();
                    for(Members m : selectedProject.getTeamMemberArrayList()){
                        if(!task.getAssignedMembers().contains(m)){
                            membersOption.add(m);
                        }
                    }
                    for (Members m : membersOption) {
                        i++;
                        System.out.println("\t\t\tS.no. " + i + " Name : " + m.getName());
                    }
                    System.out.println();
                    while (true) {

                        System.out.print("\t\t\tS.no : ");
                        mem = scanner.nextInt();
                        if (mem == -1) {
                            break;
                        } else if (mem < -1 || mem > selectedProject.getTeamMemberArrayList().size() || mem == 0) {
                            System.out.println("\n\t\t User not found! Enter the correct S.no");
                        } else {
                            //selectedProject.getTeamMemberArrayList().get(mem - 1).assignedTasks.add(task);
                            task.getFollowers().add(selectedProject.getTeamMemberArrayList().get(mem - 1));
                        }
                    }

                    System.out.println("\t\t\tRecurrance : ");
                    i = 0;
                    for (String m : DataModel.getRecurringTaskType()) {
                        i++;
                        System.out.print("\n\t\t\t S.no : " + i + ". " + m);
                    }
                    System.out.println();
                    DesignModel.printLine();

                    System.out.print("\t\tChoose Recurrance! Enter");
                    int recurranceChoice = -1;
                    while (true) {
                        while (recurranceChoice == -1) {
                            System.out.print("\t\t S.no: ");
                            recurranceChoice = Validation.numberCheck(scanner);
                        }

                        if (recurranceChoice < 1 || recurranceChoice > DataModel.getRecurringTaskType().size()) {
                            System.out.println("\n\t\t S.no not found!");
                            recurranceChoice = -1;
                        } else {
                            break;
                        }
                    }

                    HashMap<String, Integer> hashMap = new HashMap<>();
                    if(recurranceChoice != 1){
                        System.out.print("\t\t\tEnter recurrance Count. Enter 0 for Infinite Recurrance :");
                        int count = -1;
                        while (count == -1) {
                            System.out.print("\t\t S.no: ");
                            count = Validation.numberCheck(scanner);
                        }

                        hashMap.put(DataModel.getRecurringTaskType().get(recurranceChoice-1), count);
                    }
                    else{
                        hashMap.put(DataModel.getRecurringTaskType().get(0), -1);
                    }
                    task.setRecurrance(hashMap);

                    System.out.print("\t\tDo you want Remainder For this Task?! Enter 1 to yes, Enter -1 to no :");
                    int remainder = -1;
                    while (remainder == -1) {
                        System.out.print("\t\t S.no: ");
                        remainder = Validation.numberCheck(scanner);
                        if(remainder!=-2 && remainder!=1){
                            remainder = -1;
                        }
                    }
                    String remainderDate;
                    if(remainder == 1){
                        int flag = 0;
                        do {
                            System.out.println("\t\t\tEnter -1 to go back");
                            System.out.print("\t\t\tTask Remainder (Date format : dd-MM-yyyy) : ");
                            remainderDate = scanner.next();
                            if(remainderDate.equalsIgnoreCase("-1")){
                                flag = 1;
                                break;
                            }

                        } while (!Validation.dateValidation(taskDeadline));
                        if(flag == 0) {
                            task.setRemainder(remainderDate);
                        }
                    }

                    System.out.println("\t\tTasks Added to the Project");
                    System.out.println();
                    DesignModel.printLine();
                } else if (choice == -2) {
                    done = true;
                    System.out.println();
                    DesignModel.printLine();
                } else {
                    System.out.println("\t\tWrong number. check your Input!\n");
                }

            }
        }

    }

    // This function is used to view the list of tasks in a project
    public void viewTask(){
        System.out.println();
        DesignModel.printLine();

        System.out.println("\t\tView the Project tasks");
        System.out.println();

        if(projectArrayList.size() == 0){
            System.out.print("\t\tNo Projects found!\n");
            DesignModel.printLine();
        }
        else {
            int choice;
            if(projectArrayList.size() == 1){
                choice = 1;
            }
            else{
                System.out.printf("\n\t\t%15s %15s %15s %25s %35s\n", "S.no", "ProjectName", "Deadline", "Status", "Description");
                int i = 0;
                for (Project project : projectArrayList) {

                    i++;
                    System.out.printf("\t\t%15s %15s %15s %25s %35s\n", i, project.getProjectName(), project.getDeadline(), project.getStatus(), project.getProjectDescription());
                }
                DesignModel.printLine();



                while (true) {
                    System.out.print("\n\t\tEnter the s.no of the Project which you want to View Tasks : ");
                    choice = Validation.numberCheck(scanner);
                    if (choice > 0 && choice <= projectArrayList.size()) {
                        break;
                    } else {
                        System.out.println("\t\tWrong input");
                    }
                }
            }

            Project selectedProject = projectArrayList.get(choice - 1);

            if (selectedProject.getTaskArrayList().size() == 0) {
                System.out.println("\t\t\tNo task is created yet!");
            } else {
                int i = 0;
                System.out.printf("\n\t\t%15s %15s %15s %20s %25s %25s\n", "S.no", "TaskName", "Priority", "Deadline", "Status", "Description");
                for (Task task : selectedProject.getTaskArrayList()) {
                    i++;
                    String s = this.assignedTaskArrayList.contains(task) ? "Assigned" : "Not assigned";
                    System.out.printf("\t\t%15s %15s %15s %20s %25s %25s %20s\n", i, task.getTaskName(), task.getPriority(), task.getDeadline(), task.getStatus(), task.getDescription(), s);
                }

                DesignModel.printLine();

                System.out.println("\n\t\tEnter 1 to update Task Details,\n\t\tEnter 2 to view Activity Stream,\n\t\tEnter 3 to Comments,\n\t\tEnter -1 to go back");
                int ver;
                while (true) {
                    System.out.print("\t\tEnter your choice : ");
                    ver = Validation.numberCheck(scanner);
                    if (ver == -2 || ver == 1 || ver == 2 || ver == 3) {
                        break;
                    } else {
                        System.out.println("\t\tWrong input");
                    }
                }

                if (ver == 1) {
                    updateTaskDetails(selectedProject);
                }

                else if(ver == 2){
                    this.viewActivityStream(selectedProject);
                } else if (ver == 3) {
                    this.viewComments(selectedProject);
                }
            }
        }
    }

    // This function is used to update the task details
    private void updateTaskDetails(Project selectedProject){
        int choice;

        while(true){
            if(selectedProject.getTaskArrayList().size()==1){
                choice=1;
                break;
            }
            else{
                System.out.print("\n\t\tEnter the s.no of the Task which you want to update : ");
                choice = Validation.numberCheck(scanner);
                if(choice>0 && choice<=selectedProject.getTaskArrayList().size()){
                    break;
                }
                else{
                    System.out.println("\t\tWrong input");
                }
            }

        }

        Task selectedTask = selectedProject.getTaskArrayList().get(choice-1);

        if(DataModel.getTypeOfUser().get(this.type).contains("Update Tasks")){
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            boolean update = true;
            while(update){
                System.out.println("\n\t\t\tEnter the s.no of credential you want to change!");
                System.out.println("\t\t\t Enter 1 to Task Name ");
                System.out.println("\t\t\t Enter 2 to Task Deadline");
                System.out.println("\t\t\t Enter 3 to Task Description");
                System.out.println("\t\t\t Enter 4 to Task Priority ");
                System.out.println("\t\t\t Enter 5 to Task Status ");
                System.out.println("\t\t\t Enter 6 to Task Remainder");
                System.out.println("\t\t\t Enter -1 to Go back\n");

                int updateChoice = -1;
                while(updateChoice == -1){
                    System.out.print("\t\t\t Enter your Choice : ");
                    updateChoice = Validation.numberCheck(scanner);
                }

                switch (updateChoice){
                    case -2 -> {
                        update = false;

                        System.out.println();
                        DesignModel.printLine();
                    }

                    case 1 -> {
                        System.out.println("\n\t\tCurrent Name : " + selectedTask.getTaskName());
                        System.out.print("\t\tEnter the new Task Name : ");
                        String chat;
                        //scanner.nextLine();
                        chat = scanner.nextLine();
                        System.out.print("");

                        if(Validation.messageValidation(chat)){
                            selectedTask.getActivityStream().add("The Task Name is changed from "+selectedTask.getTaskName()+" to "+chat+" by "+this.getName());
                            for(Members members : selectedTask.getAssignedMembers()){
                                if(!members.getName().equalsIgnoreCase(this.getName())){
                                    members.getWorkflow().add("Project: "+selectedProject.getProjectName()+" :-> The Task Name is changed from "+selectedTask.getTaskName()+" to "+chat+" by "+this.getName()+" on "+formatter.format(date));
                                    members.getNotification().add("The Task Name is changed from "+selectedTask.getTaskName()+" to "+chat+" by "+this.getName());
                                }
                            }
                            for(Milestone milestone : selectedTask.getAssociatedMilestones()){
                                milestone.getActivityStream().add("The Task Name is changed from "+selectedTask.getTaskName()+" to "+chat+" by "+this.getName());
                            }
                            selectedTask.setTaskName(chat);

                        }

                    }

                    case 2 -> {
                        System.out.println("\n\t\tCurrent Deadline : " + selectedTask.getDeadline());
                        String deadline;
                        do {
                            System.out.print("\t\t\tTask Deadline (Date format : dd-MM-yyyy) : ");
                            deadline = scanner.next();

                        } while (!Validation.deadlineDateValidation(selectedProject.getDeadline(), deadline));
                        selectedTask.getActivityStream().add("The Task Deadline is changed from "+selectedTask.getDeadline()+" to "+deadline+" by "+this.getName());
                        for(Members members : selectedTask.getAssignedMembers()){
                            if(!members.getName().equalsIgnoreCase(this.getName())){
                                members.getWorkflow().add("Project: "+selectedProject.getProjectName()+" :-> " + selectedTask.getTaskName() + ":-> The Task Deadline is changed from "+selectedTask.getDeadline()+" to "+deadline+" by "+this.getName() + " on " + formatter.format(date));
                                members.getNotification().add("The Task Deadline is changed from "+selectedTask.getDeadline()+" to "+deadline+" by "+this.getName());
                            }
                        }
                        for(Milestone milestone : selectedTask.getAssociatedMilestones()){
                            milestone.getActivityStream().add(selectedTask.getTaskName() + ":-> The Task Deadline is changed from "+selectedTask.getDeadline()+" to "+deadline+" by "+this.getName());
                        }
                        selectedTask.setDeadline(deadline);
                    }

                    case 3 -> {
                        System.out.println("\n\t\tCurrent Description : " + selectedTask.getDescription());
                        System.out.print("\t\tEnter the new Project Name : ");
                        String description;
                        //scanner.nextLine();
                        description = scanner.nextLine();
                        System.out.print("");

                        selectedTask.getActivityStream().add("The Task Description is changed from "+selectedTask.getDescription()+" to "+description+" by "+this.getName());
                        for(Members members : selectedTask.getAssignedMembers()){
                            if(!members.getName().equalsIgnoreCase(this.getName())){
                                members.getWorkflow().add("Project: "+selectedProject.getProjectName()+" :-> " + selectedTask.getTaskName() + ":-> The Task Description is changed from "+selectedTask.getDescription()+" to "+description+" by "+this.getName() + " on " + formatter.format(date));
                                members.getNotification().add("The Task Description is changed from "+selectedTask.getDescription()+" to "+description+" by "+this.getName());
                            }
                        }
                        for(Milestone milestone : selectedTask.getAssociatedMilestones()){
                            milestone.getActivityStream().add(selectedTask.getTaskName() + ":-> The Task Description is changed from "+selectedTask.getDescription()+" to "+description+" by "+this.getName());
                        }
                        selectedTask.setDescription(description);
                    }

                    case 4 -> {
                        System.out.println("\n\t\tCurrent Priority : " + selectedTask.getPriority());
                        System.out.print("\t\tEnter the new New Priority S.no : ");

                        int i = 0;
                        for(String m : DataModel.getPriority()){
                            i++;
                            System.out.print("\n\t\t\t S.no : " + i + ". " + m);
                        }
                        System.out.println();
                        DesignModel.printLine();

                        System.out.print("\t\tChoose task Priority! Enter");
                        int priorityChoice = -1;
                        while(true) {
                            while (priorityChoice == -1) {
                                System.out.print("\t\t S.no: ");
                                priorityChoice = Validation.numberCheck(scanner);
                            }

                            if (priorityChoice < 1 || priorityChoice > DataModel.getPriority().size()) {
                                System.out.println("\n\t\t S.no not found!");
                            } else {
                                break;
                            }
                        }

                        selectedTask.getActivityStream().add("The Task Priority is changed from "+selectedTask.getPriority()+" to "+DataModel.getPriority().get(priorityChoice-1)+" by "+this.getName());
                        for(Members members : selectedTask.getAssignedMembers()){
                            if(!members.getName().equalsIgnoreCase(this.getName())){
                                members.getWorkflow().add("Project: "+selectedProject.getProjectName()+" :-> " + selectedTask.getTaskName() + ":-> The Task Priority is changed from "+selectedTask.getPriority()+" to "+DataModel.getPriority().get(priorityChoice-1)+" by "+this.getName() + " on " + formatter.format(date));
                                members.getNotification().add("The Task Priority is changed from "+selectedTask.getPriority()+" to "+DataModel.getPriority().get(priorityChoice-1)+" by "+this.getName());
                            }
                        }
                        for(Milestone milestone : selectedTask.getAssociatedMilestones()){
                            milestone.getActivityStream().add(selectedTask.getTaskName() + ":-> The Task Priority is changed from "+selectedTask.getPriority()+" to "+DataModel.getPriority().get(priorityChoice-1)+" by "+this.getName());
                        }
                        selectedTask.setPriority(DataModel.getPriority().get(priorityChoice-1));
                    }

                    case 5 -> {
                        System.out.println("\n\t\tCurrent Status : " + selectedTask.getStatus());
                        System.out.print("\t\tEnter the new New Priority S.no : ");

                        int i = 0;
                        for(String m : DataModel.getTaskStatus()){
                            i++;
                            System.out.print("\n\t\t\t S.no : " + i + ". " + m);
                        }
                        System.out.println();
                        DesignModel.printLine();

                        System.out.println("\n\t\tEnter 1 to select a Status. Enter 2 to add Custom Status");
                        int select = -1;
                        while(select == -1){
                            System.out.print("\t\t\t Enter your Choice : ");
                            select = Validation.numberCheck(scanner);
                            if(select < 1 || select > 2){
                                System.out.println("\t\tWrong input! Enter 1 or 2");
                                select = -1;
                            }
                        }
                        if(select == 1){
                            System.out.print("\t\tChoose task Status! Enter");
                            int priorityChoice = -1;
                            while(true) {
                                while (priorityChoice == -1) {
                                    System.out.print("\t\t S.no: ");
                                    priorityChoice = Validation.numberCheck(scanner);
                                }

                                if (priorityChoice < 1 || priorityChoice > DataModel.getTaskStatus().size()) {
                                    System.out.println("\n\t\t S.no not found!");
                                } else {
                                    break;
                                }
                            }

                            selectedTask.getActivityStream().add("The Task Status is changed from "+selectedTask.getStatus()+" to "+DataModel.getTaskStatus().get(priorityChoice-1)+" by "+this.getName());
                            for(Members members : selectedTask.getAssignedMembers()){
                                if(!members.getName().equalsIgnoreCase(this.getName())){
                                    members.getWorkflow().add("Project: "+selectedProject.getProjectName()+" :-> " + selectedTask.getTaskName() + ":->The Task Status is changed from "+selectedTask.getStatus()+" to "+DataModel.getTaskStatus().get(priorityChoice-1)+" by "+this.getName()+ " on " + formatter.format(date));
                                    members.getNotification().add("The Task Status is changed from "+selectedTask.getStatus()+" to "+DataModel.getTaskStatus().get(priorityChoice-1)+" by "+this.getName());
                                }
                            }
                            for(Milestone milestone : selectedTask.getAssociatedMilestones()){
                                milestone.getActivityStream().add(selectedTask.getTaskName() + ":->The Task Status is changed from "+selectedTask.getStatus()+" to "+DataModel.getTaskStatus().get(priorityChoice-1)+" by "+this.getName());
                            }
                            selectedTask.getTaskOwner().getWorkflow().add("Project: "+selectedProject.getProjectName()+" :-> " + selectedTask.getTaskName() + ":->The Task Status is changed from "+selectedTask.getStatus()+" to "+DataModel.getTaskStatus().get(priorityChoice-1)+" by "+this.getName()+ " on " + formatter.format(date));
                            selectedTask.setStatus(DataModel.getTaskStatus().get(priorityChoice-1));
                            if(selectedTask.getStatus().equalsIgnoreCase("Submitted for test")){
                                for(Members members : selectedProject.getTeamMemberArrayList()){
                                    if(members.getType().equalsIgnoreCase("Tester")){
                                        members.getAssignedTaskArrayList().add(selectedTask);
                                        members.getNotification().add("\t\t\tTask -> " +selectedTask.getTaskName()+ " Added for testing!");
                                    }
                                }
                            }
                        }
                        else{
                            System.out.print("\t\tEnter the custom Status : ");
                            String chat;
                            //scanner.nextLine();
                            chat = scanner.nextLine();
                            System.out.print("");

                            DataModel.getTaskStatus().add(chat);
                            selectedTask.getActivityStream().add("The Task Status is changed from "+selectedTask.getStatus()+" to "+chat+" by "+this.getName());
                            for(Members members : selectedTask.getAssignedMembers()){
                                if(!members.getName().equalsIgnoreCase(this.getName())){
                                    members.getWorkflow().add("Project: "+selectedProject.getProjectName()+" :-> " + selectedTask.getTaskName() + ":->The Task Status is changed from "+selectedTask.getStatus()+" to "+chat+" by "+this.getName()+ " on " + formatter.format(date));
                                    members.getNotification().add("The Task Status is changed from "+selectedTask.getStatus()+" to "+chat+" by "+this.getName());
                                }
                            }
                            for(Milestone milestone : selectedTask.getAssociatedMilestones()){
                                milestone.getActivityStream().add(selectedTask.getTaskName() + ":->The Task Status is changed from "+selectedTask.getStatus()+" to "+chat+" by "+this.getName());
                            }
                            selectedTask.getTaskOwner().getWorkflow().add("Project: "+selectedProject.getProjectName()+" :-> " + selectedTask.getTaskName() + ":->The Task Status is changed from "+selectedTask.getStatus()+" to "+chat+" by "+this.getName()+ " on " + formatter.format(date));
                            selectedTask.setStatus(chat);
                        }

                    }
                    case 6 -> {
                        System.out.println("\n\t\tCurrent Remainder date : " + selectedTask.getRemainder());
                        String deadline;
                        do {
                            System.out.print("\t\t\tTask Remainder (Date format : dd-MM-yyyy) : ");
                            deadline = scanner.next();

                        } while (!Validation.dateValidation(deadline));
                        selectedTask.getActivityStream().add("The Task Remainder is changed from "+selectedTask.getRemainder()+" to "+deadline+" by "+this.getName());
                        for(Members members : selectedTask.getAssignedMembers()){
                            if(!members.getName().equalsIgnoreCase(this.getName())){
                                members.getWorkflow().add("Project: "+selectedProject.getProjectName()+" :-> " + selectedTask.getTaskName() + ":->The Task Remainder is changed from " +selectedTask.getRemainder()+" to "+deadline+" by "+this.getName()+ " on " + formatter.format(date));
                                members.getNotification().add("The Task Remainder is changed from "+selectedTask.getRemainder()+" to "+deadline+" by "+this.getName());
                            }
                        }
                        for(Milestone milestone : selectedTask.getAssociatedMilestones()){
                            milestone.getActivityStream().add(selectedTask.getTaskName() + ":->The Task Remainder is changed from " +selectedTask.getRemainder()+" to "+deadline+" by "+this.getName());
                        }
                        selectedTask.setRemainder(deadline);
                    }

                    default -> System.out.println("\n\tWrong value. Give correct input number!\n");
                }
            }
        }
    }

    // This function is used to view activity stream of a particular task
    private void viewActivityStream(Project selectedProject){
        int choice;

        while(true){
            if(selectedProject.getTaskArrayList().size()==1){
                choice=1;
                break;
            }
            else{
                System.out.print("\n\t\tEnter the s.no of the Task which you want to update : ");
                choice = Validation.numberCheck(scanner);
                if(choice>0 && choice<=selectedProject.getTaskArrayList().size()){
                    break;
                }
                else{
                    System.out.println("\t\tWrong input");
                }
            }

        }

        Task selectedTask = selectedProject.getTaskArrayList().get(choice-1);

        if(selectedTask.getActivityStream().size() == 0){
            System.out.println("\t\tNo Update On This Task");
        }
        else {
            for(String activity : selectedTask.getActivityStream()){
                System.out.println("\t\t\t"+activity);
            }
        }

        System.out.println();
        DesignModel.printLine();
    }

    // this function is used to view comments of a particular project
    private void viewComments(Project selectedProject){
        int choice;

        while(true){
            if(selectedProject.getTaskArrayList().size()==1){
                choice=1;
                break;
            }
            else{
                System.out.print("\n\t\tEnter the s.no of the Task which you want to add Comments : ");
                choice = Validation.numberCheck(scanner);
                if(choice>0 && choice<=selectedProject.getTaskArrayList().size()){
                    break;
                }
                else{
                    System.out.println("\t\tWrong input");
                }
            }

        }

        Task selectedTask = selectedProject.getTaskArrayList().get(choice-1);

        while(true){
            if(selectedTask.getComments().size()==0){
                System.out.println("\t\tThere are no comments for this Task");
            }
            else{
                for(String string : selectedTask.getComments()){
                    System.out.println("\t\t\t" + string);
                }
            }


            System.out.println("\n\t\tDo you wan to add comments? Enter 1 to add Comments, Enter -1 to go back");
            int ver;
            while (true) {
                System.out.print("\t\tEnter your choice : ");
                ver = Validation.numberCheck(scanner);
                if (ver == -2 || ver == 1) {
                    break;
                } else {
                    System.out.println("\t\tWrong input");
                }
            }

            if (ver == -2){
                break;
            } else {
                String comment;
                System.out.print("\t\t\tEnter your Comment : ");
                while ((comment = scanner.nextLine()).isEmpty()) {
                    System.out.print("Enter a Valid Task name : ");
                }
                selectedTask.getComments().add(comment);
            }
        }

    }

    // This function is used to create a milestone in a project.
    public void createMilestone(){
        //ArrayList<Task> taskArrayList = new ArrayList<>();

        System.out.println();
        DesignModel.printLine();

        if(projectArrayList.size() == 0){
            System.out.print("\t\tNo Projects found!\n");
            DesignModel.printLine();
        }
        else {
            int choice;
            if(projectArrayList.size() == 1){
                choice = 1;
            }
            else{
                System.out.printf("\n\t\t%15s %15s %15s %25s %35s\n", "S.no", "ProjectName", "Deadline", "Status", "Description");
                int i = 0;
                for (Project project : projectArrayList) {

                    i++;
                    System.out.printf("\t\t%15s %15s %15s %25s %35s\n", i, project.getProjectName(), project.getDeadline(), project.getStatus(), project.getProjectDescription());
                }
                DesignModel.printLine();



                while(true){
                    System.out.print("\n\t\tEnter the s.no of the Project which you want to add Milestone : ");
                    choice = Validation.numberCheck(scanner);
                    if(choice>0 && choice<=projectArrayList.size()){
                        break;
                    }
                    else{
                        System.out.println("\t\tWrong input");
                    }
                }
            }

            Project selectedProject = projectArrayList.get(choice-1);


            System.out.println("\t\tAdd Milestone to the Project");

            boolean done = false;
            while (!done) {
                System.out.println("\t\t\t1. Add Milestone");
                System.out.println("\t\t\t-1. Milestone Adding completed");
                do {
                    System.out.print("\n\t\tEnter your choice : ");
                    choice = Validation.numberCheck(scanner);
                } while (choice == -1);

                if (choice == 1) {
                    Milestone task;

                    String taskName, taskDescription, taskDeadline;

                    System.out.print("\t\t\tEnter Name of the Milestone : ");
                    while ((taskName = scanner.nextLine()).isEmpty()) {
                        System.out.print("Enter a Valid Milestone name : ");
                    }

                    System.out.print("\t\t\tMilestone Description : ");
                    //scanner.nextLine();
                    taskDescription = scanner.nextLine();
                    System.out.print("");
                    if(taskDescription.isEmpty()){
                        taskDescription = "No Description";
                    }
                    System.out.print("");


                    while(true) {
                        System.out.println("\n\t\t\tEnter 1 to give a Deadline.\n\t\t\tEnter 2 to skip");
                        int dead = -1;
                        while (dead == -1) {
                            System.out.print("\t\t S.no: ");
                            dead = Validation.numberCheck(scanner);
                        }

                        if (dead == 1) {
                            int flag = 0;
                            do {
                                System.out.println("\t\t\tEnter -1 to go back");
                                System.out.print("\t\t\tProject Deadline (Date format : dd-MM-yyyy) : ");
                                taskDeadline = scanner.next();
                                if (taskDeadline.equalsIgnoreCase("-1")) {
                                    flag = 1;
                                    break;
                                }

                            } while (!Validation.dateValidation(taskDeadline));
                            if (flag == 0) {
                                break;
                            }
                        }
                    }

                    System.out.println("\t\tPriority List : ");

                    int i = 0;
                    for (String m : DataModel.getPriority()) {
                        i++;
                        System.out.print("\n\t\t\t S.no : " + i + ". " + m);
                    }
                    System.out.println();
                    DesignModel.printLine();

                    System.out.print("\t\tChoose task Priority! Enter");
                    int priorityChoice = -1;
                    while (true) {
                        while (priorityChoice == -1) {
                            System.out.print("\t\t S.no: ");
                            priorityChoice = Validation.numberCheck(scanner);
                        }

                        if (priorityChoice < 1 || priorityChoice > DataModel.getPriority().size()) {
                            System.out.println("\n\t\t S.no not found!");
                            priorityChoice = -1;
                        } else {
                            break;
                        }
                    }
                    task = new Milestone(taskName, selectedProject.getProjectOwner(), taskDescription, taskDeadline, DataModel.getPriority().get(priorityChoice - 1));
                    selectedProject.getMileStonesArrayList().add(task);

                    System.out.println("\t\tEnter the List of Tasks for the Milestone\n");
                    i = 0;
                    System.out.printf("\n\t\t%15s %15s %15s %20s %25s %25s\n", "S.no", "TaskName", "Priority", "Deadline", "Status", "Description");
                    for (Task milestoneTask : selectedProject.getTaskArrayList()) {
                        i++;
                        System.out.printf("\t\t%15s %15s %15s %20s %25s %25s\n", i, milestoneTask.getTaskName(), milestoneTask.getPriority(), milestoneTask.getDeadline(), milestoneTask.getStatus(), milestoneTask.getDescription());
                    }
                    int mem;
                    while (true) {

                        System.out.print("\t\t\tS.no : ");
                        mem = scanner.nextInt();
                        if (mem == -1) {
                            break;
                        } else if (mem < -1 || mem > selectedProject.getTaskArrayList().size() || mem == 0) {
                            System.out.println("\n\t\tTask not found! Enter the correct S.no");
                        } else {
                            task.getMilestoneTasksArrayList().add(selectedProject.getTaskArrayList().get(mem-1));
                            selectedProject.getTaskArrayList().get(mem-1).getAssociatedMilestones().add(task);
                        }
                    }


                } else if (choice == -2) {
                    done = true;
                    System.out.println();
                    DesignModel.printLine();
                } else {
                    System.out.println("\t\tWrong number. check your Input!\n");
                }

            }
        }

    }

    // This function is used to view a milestone in a project
    public void viewMilestone(){
        System.out.println();
        DesignModel.printLine();

        System.out.println("\t\tView the Project tasks");
        System.out.println();

        if(projectArrayList.size() == 0){
            System.out.print("\t\tNo Projects found!\n");
            DesignModel.printLine();
        }
        else {
            int choice;
            if(projectArrayList.size() == 1){
                choice = 1;
            }
            else{
                System.out.printf("\n\t\t%15s %15s %15s %25s %35s\n", "S.no", "ProjectName", "Deadline", "Status", "Description");
                int i = 0;
                for (Project project : projectArrayList) {

                    i++;
                    System.out.printf("\t\t%15s %15s %15s %25s %35s\n", i, project.getProjectName(), project.getDeadline(), project.getStatus(), project.getProjectDescription());
                }
                DesignModel.printLine();



                while (true) {
                    System.out.print("\n\t\tEnter the s.no of the Project which you want to View Tasks : ");
                    choice = Validation.numberCheck(scanner);
                    if (choice > 0 && choice <= projectArrayList.size()) {
                        break;
                    } else {
                        System.out.println("\t\tWrong input");
                    }
                }
            }

            Project selectedProject = projectArrayList.get(choice - 1);

            if (selectedProject.getMileStonesArrayList().size() == 0) {
                System.out.println("\t\t\tNo task is created yet!");
            } else {
                int i = 0;
                System.out.printf("\n\t\t%15s %15s %15s %20s %25s %25s\n", "S.no", "TaskName", "Priority", "Deadline", "Status", "Description");
                for (Milestone task : selectedProject.getMileStonesArrayList()) {
                    i++;
                    System.out.printf("\t\t%15s %15s %15s %20s %25s %25s\n", i, task.getTaskName(), task.getPriority(), task.getDeadline(), task.getStatus(), task.getDescription());
                }

                DesignModel.printLine();

                System.out.println("\n\t\tEnter 1 to update Milestones Details,\n\t\tEnter 2 to view Activity Stream of MileStone,\n\t\tEnter 3 to View/Add comments on Milestone,\n\t\tEnter -1 to go back");
                int ver;
                while (true) {
                    System.out.print("\t\tEnter your choice : ");
                    ver = Validation.numberCheck(scanner);
                    if (ver == -2 || ver == 1 || ver == 2 || ver == 3) {
                        break;
                    } else {
                        System.out.println("\t\tWrong input");
                    }
                }

                if (ver == 1) {
                    updateMilestoneDetails(selectedProject);
                }

                else if(ver == 2){
                    this.viewMilestoneActivityStream(selectedProject);
                } else if (ver == 3) {
                    this.viewMilestoneComments(selectedProject);
                }
            }
        }
    }

    // This function is used to update details of a milestone
    private void updateMilestoneDetails(Project selectedProject){
        int choice;

        while(true){
            if(selectedProject.getMileStonesArrayList().size()==1){
                choice=1;
                break;
            }
            else{
                System.out.print("\n\t\tEnter the s.no of the Milestone which you want to update : ");
                choice = Validation.numberCheck(scanner);
                if(choice>0 && choice<=selectedProject.getMileStonesArrayList().size()){
                    break;
                }
                else{
                    System.out.println("\t\tWrong input");
                }
            }

        }

        Milestone selectedTask = selectedProject.getMileStonesArrayList().get(choice-1);

        if(DataModel.getTypeOfUser().get(this.type).contains("Update Milestones")){
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            boolean update = true;
            while(update){
                System.out.println("\n\t\t\tEnter the s.no of credential you want to change!");
                System.out.println("\t\t\t Enter 1 to Milestone Name ");
                System.out.println("\t\t\t Enter 2 to Milestone Deadline");
                System.out.println("\t\t\t Enter 3 to Milestone Description");
                System.out.println("\t\t\t Enter 4 to Milestone Priority ");
                System.out.println("\t\t\t Enter 5 to Milestone Status ");
                System.out.println("\t\t\t Enter -1 to Go back\n");

                int updateChoice = -1;
                while(updateChoice == -1){
                    System.out.print("\t\t\t Enter your Choice : ");
                    updateChoice = Validation.numberCheck(scanner);
                }

                switch (updateChoice){
                    case -2 -> {
                        update = false;

                        System.out.println();
                        DesignModel.printLine();
                    }

                    case 1 -> {
                        System.out.println("\n\t\tCurrent Name : " + selectedTask.getTaskName());
                        System.out.print("\t\tEnter the new Task Name : ");
                        String chat;
                        //scanner.nextLine();
                        chat = scanner.nextLine();
                        System.out.print("");

                        if(Validation.messageValidation(chat)){
                            selectedTask.getActivityStream().add("The Milestone Name is changed from "+selectedTask.getTaskName()+" to "+chat+" by "+this.getName());
                            selectedTask.setTaskName(chat);

                        }

                    }

                    case 2 -> {
                        System.out.println("\n\t\tCurrent Deadline : " + selectedTask.getDeadline());
                        String deadline;
                        do {
                            System.out.print("\t\t\tMilestone Deadline (Date format : dd-MM-yyyy) : ");
                            deadline = scanner.next();

                        } while (!Validation.deadlineDateValidation(selectedProject.getDeadline(), deadline));
                        selectedTask.getActivityStream().add("The Milestone Deadline is changed from "+selectedTask.getDeadline()+" to "+deadline+" by "+this.getName());
                        selectedTask.setDeadline(deadline);
                    }

                    case 3 -> {
                        System.out.println("\n\t\tCurrent Description : " + selectedTask.getDescription());
                        System.out.print("\t\tEnter the new MileStone description : ");
                        String description;
                        //scanner.nextLine();
                        description = scanner.nextLine();
                        System.out.print("");

                        selectedTask.getActivityStream().add("The Milestone Description is changed from "+selectedTask.getDescription()+" to "+description+" by "+this.getName());
                        selectedTask.setDescription(description);
                    }

                    case 4 -> {
                        System.out.println("\n\t\tCurrent Priority : " + selectedTask.getPriority());
                        System.out.print("\t\tEnter the new New Priority S.no : ");

                        int i = 0;
                        for(String m : DataModel.getPriority()){
                            i++;
                            System.out.print("\n\t\t\t S.no : " + i + ". " + m);
                        }
                        System.out.println();
                        DesignModel.printLine();

                        System.out.print("\t\tChoose Milestone Priority! Enter");
                        int priorityChoice = -1;
                        while(true) {
                            while (priorityChoice == -1) {
                                System.out.print("\t\t S.no: ");
                                priorityChoice = Validation.numberCheck(scanner);
                            }

                            if (priorityChoice < 1 || priorityChoice > DataModel.getPriority().size()) {
                                System.out.println("\n\t\t S.no not found!");
                            } else {
                                break;
                            }
                        }

                        selectedTask.getActivityStream().add("The Milestone Priority is changed from "+selectedTask.getPriority()+" to "+DataModel.getPriority().get(priorityChoice-1)+" by "+this.getName());
                        selectedTask.setPriority(DataModel.getPriority().get(priorityChoice-1));
                    }

                    case 5 -> {
                        System.out.println("\n\t\tCurrent Status : " + selectedTask.getStatus());
                        System.out.print("\t\tEnter the new New Status S.no : ");

                        int i = 0;
                        for(String m : DataModel.getTaskStatus()){
                            i++;
                            System.out.print("\n\t\t\t S.no : " + i + ". " + m);
                        }
                        System.out.println();
                        DesignModel.printLine();

                        System.out.println("\n\t\tEnter 1 to select a Status. Enter 2 to add Custom Status");
                        int select = -1;
                        while(select == -1){
                            System.out.print("\t\t\t Enter your Choice : ");
                            select = Validation.numberCheck(scanner);
                            if(select < 1 || select > 2){
                                System.out.println("\t\tWrong input! Enter 1 or 2");
                                select = -1;
                            }
                        }
                        if(select == 1) {
                            System.out.print("\t\tChoose Milestone Status! Enter");
                            int priorityChoice = -1;
                            while (true) {
                                while (priorityChoice == -1) {
                                    System.out.print("\t\t S.no: ");
                                    priorityChoice = Validation.numberCheck(scanner);
                                }

                                if (priorityChoice < 1 || priorityChoice > DataModel.getTaskStatus().size()) {
                                    System.out.println("\n\t\t S.no not found!");
                                } else {
                                    break;
                                }
                            }

                            selectedTask.getActivityStream().add("The Milestone Status is changed from " + selectedTask.getStatus() + " to " + DataModel.getTaskStatus().get(priorityChoice - 1) + " by " + this.getName());
                            selectedTask.getTaskOwner().getWorkflow().add("Project: " + selectedProject.getProjectName() + " :-> " + selectedTask.getTaskName() + ":->The Milestone Status is changed from " + selectedTask.getStatus() + " to " + DataModel.getTaskStatus().get(priorityChoice - 1) + " by " + this.getName() + " on " + formatter.format(date));
                            selectedTask.setStatus(DataModel.getTaskStatus().get(priorityChoice - 1));
                        }
                        else{
                            System.out.print("\t\tEnter the custom Status : ");
                            String chat;
                            //scanner.nextLine();
                            chat = scanner.nextLine();
                            System.out.print("");

                            DataModel.getTaskStatus().add(chat);
                            selectedTask.getActivityStream().add("The Milestone Status is changed from "+selectedTask.getStatus()+" to "+chat+" by "+this.getName());
                            selectedTask.getTaskOwner().getWorkflow().add("Project: "+selectedProject.getProjectName()+" :-> " + selectedTask.getTaskName() + ":->The Milestone Status is changed from "+selectedTask.getStatus()+" to "+chat+" by "+this.getName()+ " on " + formatter.format(date));
                            selectedTask.setStatus(chat);
                        }

                    }

                    default -> System.out.println("\n\tWrong value. Give correct input number!\n");
                }
            }
        }
    }

    // This function is used to view the activity stream of a milestone.
    private void viewMilestoneActivityStream(Project selectedProject){
        int choice;

        while(true){
            if(selectedProject.getMileStonesArrayList().size()==1){
                choice=1;
                break;
            }
            else{
                System.out.print("\n\t\tEnter the s.no of the Milestone which you want to update : ");
                choice = Validation.numberCheck(scanner);
                if(choice>0 && choice<=selectedProject.getMileStonesArrayList().size()){
                    break;
                }
                else{
                    System.out.println("\t\tWrong input");
                }
            }

        }

        Milestone selectedTask = selectedProject.getMileStonesArrayList().get(choice-1);

        if(selectedTask.getActivityStream().size() == 0){
            System.out.println("\t\tNo Update On This Task");
        }
        else {
            for(String activity : selectedTask.getActivityStream()){
                System.out.println("\t\t\t"+activity);
            }
        }

        System.out.println();
        DesignModel.printLine();
    }

    // This function is used to view the comments of a milestone.
    private void viewMilestoneComments(Project selectedProject){
        int choice;

        while(true){
            if(selectedProject.getMileStonesArrayList().size()==1){
                choice=1;
                break;
            }
            else{
                System.out.print("\n\t\tEnter the s.no of the Milestone which you want to add Comments : ");
                choice = Validation.numberCheck(scanner);
                if(choice>0 && choice<=selectedProject.getMileStonesArrayList().size()){
                    break;
                }
                else{
                    System.out.println("\t\tWrong input");
                }
            }

        }

        Task selectedTask = selectedProject.getMileStonesArrayList().get(choice-1);

        while(true){
            if(selectedTask.getComments().size()==0){
                System.out.println("\t\tThere are no comments for this Milestone");
            }
            else{
                for(String string : selectedTask.getComments()){
                    System.out.println("\t\t\t" + string);
                }
            }


            System.out.println("\n\t\tDo you wan to add comments? Enter 1 to add Comments, Enter -1 to go back");
            int ver;
            while (true) {
                System.out.print("\t\tEnter your choice : ");
                ver = Validation.numberCheck(scanner);
                if (ver == -2 || ver == 1) {
                    break;
                } else {
                    System.out.println("\t\tWrong input");
                }
            }

            if (ver == -2){
                break;
            } else {
                String comment;
                System.out.print("\t\t\tEnter your Comment : ");
                while ((comment = scanner.nextLine()).isEmpty()) {
                    System.out.print("Enter a Valid Task name : ");
                }
                selectedTask.getComments().add(comment);
            }
        }

    }

    // This function is used to view the assigned tasks for a member.
    public void viewAssignedTask(){
        System.out.println();
        DesignModel.printLine();

        System.out.println("\t\tView the Project tasks");
        System.out.println();

        if(this.getAssignedTaskArrayList().size() == 0){
            System.out.print("\t\tNo Tasks are assigned to you yet!\n");
            DesignModel.printLine();
        }
        else {

            int i = 0;
            System.out.printf("\n\t\t%15s %15s %15s %20s %25s %25s\n", "S.no", "TaskName", "Priority", "Deadline", "Status", "Description");
            for (Task task : this.getAssignedTaskArrayList()) {
                i++;
                System.out.printf("\t\t%15s %15s %15s %20s %25s %25s\n", i, task.getTaskName(), task.getPriority(), task.getDeadline(), task.getStatus(), task.getDescription());
            }

            DesignModel.printLine();

            System.out.println("\n\t\tEnter 1 to update Task Details,\n\t\tEnter 2 to view Activity Stream,\n\t\tEnter 3 to Comments,\n\t\tEnter -1 to go back");
            int ver;
            while (true) {
                System.out.print("\t\tEnter your choice : ");
                ver = Validation.numberCheck(scanner);
                if (ver == -2 || ver == 1 || ver == 2 || ver == 3) {
                    break;
                } else {
                    System.out.println("\t\tWrong input");
                }
            }

            if (ver == 1) {
                updateAssignedTaskDetails();
            }

            else if(ver == 2){
                this.viewAssignedActivityStream();
            } else if (ver == 3) {
                this.viewAssignedComments();
            }

        }
    }

    // This function is used to update the status of the assigned tasks of members
    private void updateAssignedTaskDetails() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        int choice;

        while(true){
            if(this.getAssignedTaskArrayList().size()==1){
                choice=1;
                break;
            }
            else{
                System.out.print("\n\t\tEnter the s.no of the Task which you want to update : ");
                choice = Validation.numberCheck(scanner);
                if(choice>0 && choice<=this.getAssignedTaskArrayList().size()){
                    break;
                }
                else{
                    System.out.println("\t\tWrong input");
                }
            }

        }

        Task selectedTask = this.getAssignedTaskArrayList().get(choice-1);
        boolean update = true;
        while(update) {
            System.out.println("\n\t\t\tEnter the s.no of credential you want to change!");
            System.out.println("\t\t\t Enter 1 to Task Status ");
            System.out.println("\t\t\t Enter 2 to Task Remainder");
            System.out.println("\t\t\t Enter -1 to Go back\n");

            int updateChoice = -1;
            while(updateChoice == -1){
                System.out.print("\t\t\t Enter your Choice : ");
                updateChoice = Validation.numberCheck(scanner);
            }

            switch (updateChoice) {
                case -2 -> {
                    update = false;

                    System.out.println();
                    DesignModel.printLine();
                }
                case 1 -> {
                    if (getType().equalsIgnoreCase("Tester")) {
                        System.out.println("\n\t\tCurrent Status : " + selectedTask.getStatus());
                        System.out.print("\t\tEnter the new New Priority S.no : ");
                        System.out.print("\n\t\t\t S.no : 1. Issue Reported");
                        System.out.print("\n\t\t\t S.no : 2. Completed");

                        System.out.print("\n\t\t\tChoose task Status! Enter");
                        int priorityChoice = -1;
                        while (true) {
                            while (priorityChoice == -1) {
                                System.out.print("\t\t S.no: ");
                                priorityChoice = Validation.numberCheck(scanner);
                            }

                            if (priorityChoice < 1 || priorityChoice > 2) {
                                System.out.println("\n\t\t S.no not found!");
                            } else {
                                break;
                            }
                        }
                        if (priorityChoice == 1) {
                            selectedTask.getActivityStream().add("The Task Status is changed from " + selectedTask.getStatus() + " to Issue Reported by " + this.getName());
                            for(Members members : selectedTask.getAssignedMembers()){
                                if(!members.getName().equalsIgnoreCase(this.getName())){
                                    members.getWorkflow().add("Project: "+projectArrayList.get(0).getProjectName()+" :-> " + selectedTask.getTaskName() + ":-> The Task Status is changed from " + selectedTask.getStatus() + " to Issue Reported by " + this.getName() + " on " + formatter.format(date));
                                    members.getNotification().add("The Task Status is changed from " + selectedTask.getStatus() + " to Issue Reported by " + this.getName());
                                }
                            }
                            selectedTask.getTaskOwner().getWorkflow().add("Project: "+projectArrayList.get(0).getProjectName()+" :-> " + selectedTask.getTaskName() + ":-> The Task Status is changed from " + selectedTask.getStatus() + " to Issue Reported by " + this.getName() + " on " + formatter.format(date));
                            selectedTask.setStatus("Issue Reported");

                            this.createIssue(selectedTask);
                        } else {
                            selectedTask.getActivityStream().add("The Task Status is changed from " + selectedTask.getStatus() + " to Completed by " + this.getName());
                            for(Members members : selectedTask.getAssignedMembers()){
                                if(!members.getName().equalsIgnoreCase(this.getName())){
                                    members.getWorkflow().add("Project: "+projectArrayList.get(0).getProjectName()+" :-> " + selectedTask.getTaskName() + ":-> The Task Status is changed from " + selectedTask.getStatus() + " to Completed by " + this.getName() + " on " + formatter.format(date));
                                    members.getNotification().add("The Task Status is changed from " + selectedTask.getStatus() + " to Completed by " + this.getName());
                                }
                            }
                            selectedTask.getTaskOwner().getWorkflow().add("Project: "+projectArrayList.get(0).getProjectName()+" :-> " + selectedTask.getTaskName() + ":-> The Task Status is changed from " + selectedTask.getStatus() + " to Completed by " + this.getName() + " on " + formatter.format(date));
                            selectedTask.setStatus("Completed");
                        }
                    } else if (getType().equalsIgnoreCase("Member")) {
                        System.out.println("\n\t\tCurrent Status : " + selectedTask.getStatus());
                        System.out.print("\t\tEnter the new New Priority S.no : ");

                        int i = 0;
                        for (String m : DataModel.getTaskStatus()) {
                            if (!m.equalsIgnoreCase("Completed") || !m.equalsIgnoreCase("Issue Reported")) {
                                i++;
                                System.out.print("\n\t\t\t S.no : " + i + ". " + m);
                            }
                        }
                        System.out.println();
                        DesignModel.printLine();

                        System.out.print("\t\tChoose task Status! Enter");
                        int priorityChoice = -1;
                        while (true) {
                            while (priorityChoice == -1) {
                                System.out.print("\t\t S.no: ");
                                priorityChoice = Validation.numberCheck(scanner);
                            }

                            if (priorityChoice < 1 || priorityChoice > DataModel.getTaskStatus().size() - 2) {
                                System.out.println("\n\t\t S.no not found!");
                            } else {
                                break;
                            }
                        }
                        for(Members members : selectedTask.getAssignedMembers()){
                            if(!members.getName().equalsIgnoreCase(this.getName())){
                                members.getWorkflow().add("Project: "+projectArrayList.get(0).getProjectName()+" :-> " + selectedTask.getTaskName() + ":-> The Task Status is changed from " + selectedTask.getStatus() + " to +" + DataModel.getTaskStatus().get(priorityChoice - 1) + " by " + this.getName() + " on " + formatter.format(date));
                                members.getNotification().add("The Task Status is changed from " + selectedTask.getStatus() + " to +" + DataModel.getTaskStatus().get(priorityChoice - 1) + " by " + this.getName());
                            }
                        }
                        selectedTask.getActivityStream().add("The Task Status is changed from " + selectedTask.getStatus() + " to +" + DataModel.getTaskStatus().get(priorityChoice - 1) + " by " + this.getName());
                        selectedTask.setStatus(DataModel.getTaskStatus().get(priorityChoice - 1));

                        if(selectedTask.getStatus().equalsIgnoreCase("Submitted for test")){
                            for(Members members : this.getProjectArrayList().get(0).getTeamMemberArrayList()){
                                if(members.getType().equalsIgnoreCase("Tester")){
                                    members.getAssignedTaskArrayList().add(selectedTask);
                                    members.getNotification().add("\t\t\tTask -> " +selectedTask.getTaskName()+ " Added for testing!");
                                }
                            }
                        }
                    }
                }
                case 2 -> {
                    System.out.println("\n\t\tCurrent Remainder date : " + selectedTask.getRemainder());
                    String deadline;
                    do {
                        System.out.print("\t\t\tTask Remainder (Date format : dd-MM-yyyy) : ");
                        deadline = scanner.next();

                    } while (!Validation.dateValidation(deadline));
                    selectedTask.getActivityStream().add("The Task Remainder is changed from "+selectedTask.getRemainder()+" to "+deadline+" by "+this.getName());
                    for(Members members : selectedTask.getAssignedMembers()){
                        if(!members.getName().equalsIgnoreCase(this.getName())){
                            members.getNotification().add("The Task Remainder is changed from "+selectedTask.getRemainder()+" to "+deadline+" by "+this.getName());
                        }
                    }
                    selectedTask.setRemainder(deadline);
                }

                default -> System.out.println("\n\tWrong value. Give correct input number!\n");
            }
        }
    }

    // This function is used to view activity stream of the assigned Tasks of a user
    private void viewAssignedActivityStream(){
        int choice;

        while(true){
            if(this.getAssignedTaskArrayList().size()==1){
                choice=1;
                break;
            }
            else{
                System.out.print("\n\t\tEnter the s.no of the Task which you want to update : ");
                choice = Validation.numberCheck(scanner);
                if(choice>0 && choice<=this.getAssignedTaskArrayList().size()){
                    break;
                }
                else{
                    System.out.println("\t\tWrong input");
                }
            }

        }

        Task selectedTask = this.getAssignedTaskArrayList().get(choice-1);

        if(selectedTask.getActivityStream().size() == 0){
            System.out.println("\t\tNo Update On This Task");
        }
        else {
            for(String activity : selectedTask.getActivityStream()){
                System.out.println("\t\t\t"+activity);
            }
        }

        System.out.println();
        DesignModel.printLine();
    }

    // This function is used to view assigned comments of the tasks of a member
    private void viewAssignedComments(){
        int choice;

        while(true){
            if(this.getAssignedTaskArrayList().size()==1){
                choice=1;
                break;
            }
            else{
                System.out.print("\n\t\tEnter the s.no of the Task which you want to update : ");
                choice = Validation.numberCheck(scanner);
                if(choice>0 && choice<=this.getAssignedTaskArrayList().size()){
                    break;
                }
                else{
                    System.out.println("\t\tWrong input");
                }
            }

        }

        Task selectedTask = this.getAssignedTaskArrayList().get(choice-1);

        while(true){
            if(selectedTask.getComments().size()==0){
                System.out.println("\t\tThere are no comments for this Task");
            }
            else{
                for(String string : selectedTask.getComments()){
                    System.out.println("\t\t\t" + string);
                }
            }


            System.out.println("\n\t\tDo you wan to add comments? Enter 1 to add Comments, Enter -1 to go back");
            int ver;
            while (true) {
                System.out.print("\t\tEnter your choice : ");
                ver = Validation.numberCheck(scanner);
                if (ver == -2 || ver == 1) {
                    break;
                } else {
                    System.out.println("\t\tWrong input");
                }
            }

            if (ver == -2){
                break;
            } else {
                String comment;
                System.out.print("\t\t\tEnter your Comment : ");
                while ((comment = scanner.nextLine()).isEmpty()) {
                    System.out.print("Enter a Valid Task name : ");
                }
                selectedTask.getComments().add(comment);
            }
        }

    }

    // this function is used to delete a task
    public void deleteTask(){
        System.out.println();
        DesignModel.printLine();

        System.out.println("\t\tView the Project tasks");
        System.out.println();

        if (projectArrayList.size() == 0) {
            System.out.print("\t\tNo Projects found!\n");
            DesignModel.printLine();
        } else {
            int choice;
            if (projectArrayList.size() == 1) {
                choice = 1;
            } else {
                System.out.printf("\n\t\t%15s %15s %15s %25s %35s\n", "S.no", "ProjectName", "Deadline", "Status", "Description");
                int i = 0;
                for (Project project : projectArrayList) {

                    i++;
                    System.out.printf("\t\t%15s %15s %15s %25s %35s\n", i, project.getProjectName(), project.getDeadline(), project.getStatus(), project.getProjectDescription());
                }
                DesignModel.printLine();


                while (true) {
                    System.out.print("\n\t\tEnter the s.no of the Project which you want to View Tasks : ");
                    choice = Validation.numberCheck(scanner);
                    if (choice > 0 && choice <= projectArrayList.size()) {
                        break;
                    } else {
                        System.out.println("\t\tWrong input");
                    }
                }
            }

            Project selectedProject = projectArrayList.get(choice - 1);

            if (selectedProject.getTaskArrayList().size() == 0) {
                System.out.println("\t\t\tNo task is created yet!");
            } else {
                int i = 0;
                System.out.printf("\n\t\t%15s %15s %15s %20s %25s %25s\n", "S.no", "TaskName", "Priority", "Deadline", "Status", "Description");
                for (Task task : selectedProject.getTaskArrayList()) {
                    i++;
                    System.out.printf("\t\t%15s %15s %15s %20s %25s %25s\n", i, task.getTaskName(), task.getPriority(), task.getDeadline(), task.getStatus(), task.getDescription());
                }

                DesignModel.printLine();
            }

            while(true){
                if(selectedProject.getTaskArrayList().size()==1){
                    choice=1;
                    break;
                }
                else{
                    System.out.print("\n\t\tEnter the s.no of the Task which you want to update : ");
                    choice = Validation.numberCheck(scanner);
                    if(choice>0 && choice<=selectedProject.getTaskArrayList().size()){
                        break;
                    }
                    else{
                        System.out.println("\t\tWrong input");
                    }
                }

            }

            Task selectedTask = selectedProject.getTaskArrayList().get(choice-1);
            for(Members members :selectedTask.getAssignedMembers()){
                members.getAssignedTaskArrayList().remove(selectedTask);
            }
            selectedProject.getTaskArrayList().remove(selectedTask);
            selectedTask.setStatus("Cancelled");
            selectedProject.getCancelledTaskArrayList().add(selectedTask);
            System.out.println("\n\t\tTask Deleted form the Project! The deleted tasks will be stored as Cancelled Tasks!");
            System.out.println();
            DesignModel.printLine();
        }
    }

    // This function is used to create subtasks
    public void createSubTasks(){
        //ArrayList<Task> taskArrayList = new ArrayList<>();

        System.out.println();
        DesignModel.printLine();

        if(projectArrayList.size() == 0){
            System.out.print("\t\tNo Projects found!\n");
            DesignModel.printLine();
        }
        else {
            int choice;
            if (projectArrayList.size() == 1) {
                choice = 1;
            } else {
                System.out.printf("\n\t\t%15s %15s %15s %25s %35s\n", "S.no", "ProjectName", "Deadline", "Status", "Description");
                int i = 0;
                for (Project project : projectArrayList) {

                    i++;
                    System.out.printf("\t\t%15s %15s %15s %25s %35s\n", i, project.getProjectName(), project.getDeadline(), project.getStatus(), project.getProjectDescription());
                }
                DesignModel.printLine();


                while (true) {
                    System.out.print("\n\t\tEnter the s.no of the Project which you want to add Tasks : ");
                    choice = Validation.numberCheck(scanner);
                    if (choice > 0 && choice <= projectArrayList.size()) {
                        break;
                    } else {
                        System.out.println("\t\tWrong input");
                    }
                }
            }

            Project selectedProject = projectArrayList.get(choice - 1);

            if (selectedProject.getTaskArrayList().size() == 0) {
                System.out.print("\t\tNo Tasks found!\n");
                DesignModel.printLine();
            } else {
                if (selectedProject.getTaskArrayList().size() == 1) {
                    choice = 1;
                } else {
                    System.out.printf("\n\t\t%15s %15s %15s %25s %35s\n", "S.no", "ProjectName", "Deadline", "Status", "Description");
                    int i = 0;
                    for (Task task : selectedProject.getTaskArrayList()) {

                        i++;
                        System.out.printf("\t\t%15s %15s %15s %25s %35s\n", i, task.getTaskName(), task.getDeadline(), task.getStatus(), task.getDescription());
                    }
                    DesignModel.printLine();


                    while (true) {
                        System.out.print("\n\t\tEnter the s.no of the Project which you want to add Tasks : ");
                        choice = Validation.numberCheck(scanner);
                        if (choice > 0 && choice <= projectArrayList.size()) {
                            break;
                        } else {
                            System.out.println("\t\tWrong input");
                        }
                    }
                }

                Task selectedTask = selectedProject.getTaskArrayList().get(choice - 1);

                System.out.println("\t\tAdd subtasks to the Task");

                boolean done = false;
                while (!done) {
                    System.out.println("\t\t\t1. Add task");
                    System.out.println("\t\t\t-1. Task Adding completed");
                    do {
                        System.out.print("\n\t\tEnter your choice : ");
                        choice = Validation.numberCheck(scanner);
                    } while (choice == -1);

                    if (choice == 1) {
                        Task task;

                        String taskName, taskDescription, taskDeadline;

                        System.out.print("\t\t\tEnter Name of the Task : ");
                        while ((taskName = scanner.nextLine()).isEmpty()) {
                            System.out.print("Enter a Valid Task name : ");
                        }

                        System.out.print("\t\t\tTask Description : ");
                        //scanner.nextLine();
                        taskDescription = scanner.nextLine();
                        System.out.print("");
                        if (taskDescription.isEmpty()) {
                            taskDescription = "No Description";
                        }
                        System.out.print("");


                        while (true) {
                            System.out.println("\n\t\t\tEnter 1 to give a Deadline. \n\t\t\tEnter 2 to give Duration. \n\t\t\tEnter 3 to skip");
                            int dead = -1;
                            while (dead == -1) {
                                System.out.print("\t\t S.no: ");
                                dead = Validation.numberCheck(scanner);
                            }

                            if (dead == 1) {
                                int flag = 0;
                                do {
                                    System.out.println("\t\t\tEnter -1 to go back");
                                    System.out.print("\t\t\tProject Deadline (Date format : dd-MM-yyyy) : ");
                                    taskDeadline = scanner.next();
                                    if (taskDeadline.equalsIgnoreCase("-1")) {
                                        flag = 1;
                                        break;
                                    }

                                } while (!Validation.dateValidation(taskDeadline));
                                if (flag == 0) {
                                    break;
                                }
                            } else if (dead == 2) {
                                int flag = -1;
                                System.out.println("\t\t\tProject Duration : ");
                                System.out.println("\n\t\t\tEnter -1 to go back");
                                System.out.println("\t\t\tEnter 1 to Set years");
                                System.out.println("\t\t\tEnter 2 to Set months");
                                System.out.println("\t\t\tEnter 3 to Set weeks");
                                System.out.println("\t\t\tEnter 4 to Set days");
                                System.out.print("\t\t\tEnter your choice : ");

                                while (flag == -1) {
                                    System.out.print("\t\t S.no: ");
                                    flag = Validation.numberCheck(scanner);
                                }

                                if (flag == -2) {
                                    continue;
                                } else if (flag == 1) {
                                    System.out.println("\n\t\t\tEnter number of Years");
                                    taskDeadline = Validation.numberCheck(scanner) + " Years";
                                    break;
                                } else if (flag == 2) {
                                    System.out.println("\n\t\t\tEnter number of Months");
                                    taskDeadline = Validation.numberCheck(scanner) + " Months";
                                } else if (flag == 3) {
                                    System.out.println("\n\t\t\tEnter number of Weeks");
                                    taskDeadline = Validation.numberCheck(scanner) + " Weeks";
                                } else if (flag == 4) {
                                    System.out.println("\n\t\t\tEnter number of Days");
                                    taskDeadline = Validation.numberCheck(scanner) + " Days";
                                } else {
                                    System.out.println("\t\t\tWrong input!");
                                    continue;
                                }
                                break;
                            } else if (dead == 3) {
                                taskDeadline = "   -   ";
                                break;
                            } else {
                                System.out.println("\t\t\tWrong input!");
                            }
                        }


                        System.out.println("\t\tPriority List : ");

                        int i = 0;
                        for (String m : DataModel.getPriority()) {
                            i++;
                            System.out.print("\n\t\t\t S.no : " + i + ". " + m);
                        }
                        System.out.println();
                        DesignModel.printLine();

                        System.out.print("\t\tChoose task Priority! Enter");
                        int priorityChoice = -1;
                        while (true) {
                            while (priorityChoice == -1) {
                                System.out.print("\t\t S.no: ");
                                priorityChoice = Validation.numberCheck(scanner);
                            }

                            if (priorityChoice < 1 || priorityChoice > DataModel.getPriority().size()) {
                                System.out.println("\n\t\t S.no not found!");
                                priorityChoice = -1;
                            } else {
                                break;
                            }
                        }
                        task = new Task(taskName, selectedTask.getTaskOwner(), taskDescription, taskDeadline, DataModel.getPriority().get(priorityChoice - 1));
                        selectedTask.getSubTask().add(task);
                        task.setAssignedMembers(selectedTask.getAssignedMembers());
                        task.setFollowers(selectedTask.getFollowers());

                        System.out.println("\t\t\tRecurrance : ");
                        i = 0;
                        for (String m : DataModel.getRecurringTaskType()) {
                            i++;
                            System.out.print("\n\t\t\t S.no : " + i + ". " + m);
                        }
                        System.out.println();
                        DesignModel.printLine();

                        System.out.print("\t\tChoose Recurrance! Enter");
                        int recurranceChoice = -1;
                        while (true) {
                            while (recurranceChoice == -1) {
                                System.out.print("\t\t S.no: ");
                                recurranceChoice = Validation.numberCheck(scanner);
                            }

                            if (recurranceChoice < 1 || recurranceChoice > DataModel.getRecurringTaskType().size()) {
                                System.out.println("\n\t\t S.no not found!");
                                recurranceChoice = -1;
                            } else {
                                break;
                            }
                        }

                        HashMap<String, Integer> hashMap = new HashMap<>();
                        if (recurranceChoice != 1) {
                            System.out.print("\t\t\tEnter recurrance Count. Enter 0 for Infinite Recurrance :");
                            int count = -1;
                            while (count == -1) {
                                System.out.print("\t\t S.no: ");
                                count = Validation.numberCheck(scanner);
                            }

                            hashMap.put(DataModel.getRecurringTaskType().get(recurranceChoice - 1), count);
                        } else {
                            hashMap.put(DataModel.getRecurringTaskType().get(0), -1);
                        }
                        task.setRecurrance(hashMap);

                        System.out.println("\t\tSubTasks Added to the Task");
                        System.out.println();
                        DesignModel.printLine();
                    } else if (choice == -2) {
                        done = true;
                        System.out.println();
                        DesignModel.printLine();
                    } else {
                        System.out.println("\t\tWrong number. check your Input!\n");
                    }

                }
            }
        }

    }

    // This function is used to view the subtasks
    public void viewSubTask(){
        System.out.println();
        DesignModel.printLine();

        System.out.println("\t\tView the Project tasks");
        System.out.println();

        if(projectArrayList.size() == 0){
            System.out.print("\t\tNo Projects found!\n");
            DesignModel.printLine();
        }
        else {
            int choice;
            if (projectArrayList.size() == 1) {
                choice = 1;
            } else {
                System.out.printf("\n\t\t%15s %15s %15s %25s %35s\n", "S.no", "ProjectName", "Deadline", "Status", "Description");
                int i = 0;
                for (Project project : projectArrayList) {

                    i++;
                    System.out.printf("\t\t%15s %15s %15s %25s %35s\n", i, project.getProjectName(), project.getDeadline(), project.getStatus(), project.getProjectDescription());
                }
                DesignModel.printLine();


                while (true) {
                    System.out.print("\n\t\tEnter the s.no of the Project which you want to View Tasks : ");
                    choice = Validation.numberCheck(scanner);
                    if (choice > 0 && choice <= projectArrayList.size()) {
                        break;
                    } else {
                        System.out.println("\t\tWrong input");
                    }
                }
            }

            Project selectedProject = projectArrayList.get(choice - 1);

            if (selectedProject.getTaskArrayList().size() == 0) {
                System.out.print("\t\tNo Task found!\n");
                DesignModel.printLine();
            } else {
                if (selectedProject.getTaskArrayList().size() == 1) {
                    choice = 1;
                } else {
                    System.out.printf("\n\t\t%15s %15s %15s %25s %35s\n", "S.no", "ProjectName", "Deadline", "Status", "Description");
                    int i = 0;
                    for (Task task : selectedProject.getTaskArrayList()) {

                        i++;
                        System.out.printf("\t\t%15s %15s %15s %25s %35s\n", i, task.getTaskName(), task.getDeadline(), task.getStatus(), task.getDescription());
                    }
                    DesignModel.printLine();


                    while (true) {
                        System.out.print("\n\t\tEnter the s.no of the Task which you want to View SubTasks : ");
                        choice = Validation.numberCheck(scanner);
                        if (choice > 0 && choice <= projectArrayList.size()) {
                            break;
                        } else {
                            System.out.println("\t\tWrong input");
                        }
                    }
                }

                Task selectedTask = selectedProject.getTaskArrayList().get(choice - 1);

                if (selectedTask.getSubTask().size() == 0) {
                    System.out.println("\t\t\tNo subTask is created yet!");
                } else {
                    int i = 0;
                    System.out.printf("\n\t\t%15s %15s %15s %20s %25s %25s\n", "S.no", "TaskName", "Priority", "Deadline", "Status", "Description");
                    for (Task task : selectedTask.getSubTask()) {
                        i++;
                        System.out.printf("\t\t%15s %15s %15s %20s %25s %25s\n", i, task.getTaskName(), task.getPriority(), task.getDeadline(), task.getStatus(), task.getDescription());
                    }

                    DesignModel.printLine();

                    System.out.println("\n\t\tDo you want to update Task Details? Enter 1 to yes, Enter -1 to no");
                    int ver;
                    while (true) {
                        System.out.print("\t\tEnter your choice : ");
                        ver = Validation.numberCheck(scanner);
                        System.out.println(ver);
                        if (ver == -2 || ver == 1) {
                            System.out.println(ver);
                            break;
                        } else {
                            System.out.println("\t\tWrong input");
                        }
                    }

                    if (ver == 1) {
                        System.out.println(ver);
                        updateSubTaskDetails(selectedTask);
                    }
                }
            }
        }
    }

    // This function is used to update the status of the subtask
    private void updateSubTaskDetails(Task selectedTask){
        int choice;

        while(true){
            if(selectedTask.getSubTask().size()==1){
                choice=1;
                break;
            }
            else{
                System.out.print("\n\t\tEnter the s.no of the Task which you want to update : ");
                choice = Validation.numberCheck(scanner);
                if(choice>0 && choice<=selectedTask.getSubTask().size()){
                    break;
                }
                else{
                    System.out.println("\t\tWrong input");
                }
            }

        }

        Task selectedSubTask = selectedTask.getSubTask().get(choice-1);

        if(DataModel.getTypeOfUser().get(this.type).contains("Update Tasks")){
            boolean update = true;
            while(update){
                System.out.println("\n\t\t\tEnter the s.no of credential you want to change!");
                System.out.println("\t\t\t Enter 1 to Task Name ");
                System.out.println("\t\t\t Enter 2 to Task Deadline");
                System.out.println("\t\t\t Enter 3 to Task Description");
                System.out.println("\t\t\t Enter 4 to Task Priority ");
                System.out.println("\t\t\t Enter 5 to Task Status ");
                System.out.println("\t\t\t Enter -1 to Go back\n");

                int updateChoice = -1;
                while(updateChoice == -1){
                    System.out.print("\t\t\t Enter your Choice : ");
                    updateChoice = Validation.numberCheck(scanner);
                }

                switch (updateChoice){
                    case -2 -> {
                        update = false;

                        System.out.println();
                        DesignModel.printLine();
                    }

                    case 1 -> {
                        System.out.println("\n\t\tCurrent Name : " + selectedSubTask.getTaskName());
                        System.out.print("\t\tEnter the new Task Name : ");
                        String chat;
                        //scanner.nextLine();
                        chat = scanner.nextLine();
                        System.out.print("");

                        if(Validation.messageValidation(chat)){
                            selectedSubTask.setTaskName(chat);
                        }
                    }

                    case 2 -> {
                        System.out.println("\n\t\tCurrent Deadline : " + selectedSubTask.getDeadline());
                        String deadline;
                        do {
                            System.out.print("\t\t\tTask Deadline (Date format : dd-MM-yyyy) : ");
                            deadline = scanner.next();

                        } while (!Validation.deadlineDateValidation(selectedTask.getDeadline(), deadline));

                        selectedSubTask.setDeadline(deadline);
                    }

                    case 3 -> {
                        System.out.println("\n\t\tCurrent Description : " + selectedSubTask.getDescription());
                        System.out.print("\t\tEnter the new Project Name : ");
                        String description;
                        //scanner.nextLine();
                        description = scanner.nextLine();
                        System.out.print("");

                        selectedSubTask.setDescription(description);
                    }

                    case 4 -> {
                        System.out.println("\n\t\tCurrent Priority : " + selectedSubTask.getPriority());
                        System.out.print("\t\tEnter the new New Priority S.no : ");

                        int i = 0;
                        for(String m : DataModel.getPriority()){
                            i++;
                            System.out.print("\n\t\t\t S.no : " + i + ". " + m);
                        }
                        System.out.println();
                        DesignModel.printLine();

                        System.out.print("\t\tChoose task Priority! Enter");
                        int priorityChoice = -1;
                        while(true) {
                            while (priorityChoice == -1) {
                                System.out.print("\t\t S.no: ");
                                priorityChoice = Validation.numberCheck(scanner);
                            }

                            if (priorityChoice < 1 || priorityChoice > DataModel.getPriority().size()) {
                                System.out.println("\n\t\t S.no not found!");
                            } else {
                                break;
                            }
                        }

                        selectedSubTask.setPriority(DataModel.getPriority().get(priorityChoice-1));
                    }

                    case 5 -> {
                        System.out.println("\n\t\tCurrent Status : " + selectedSubTask.getStatus());
                        System.out.print("\t\tEnter the new New Priority S.no : ");

                        int i = 0;
                        for(String m : DataModel.getTaskStatus()){
                            i++;
                            System.out.print("\n\t\t\t S.no : " + i + ". " + m);
                        }
                        System.out.println();
                        DesignModel.printLine();

                        System.out.println("\n\t\tEnter 1 to select a Status. Enter 2 to add Custom Status");
                        int select = -1;
                        while(select == -1){
                            System.out.print("\t\t\t Enter your Choice : ");
                            select = Validation.numberCheck(scanner);
                            if(select < 1 || select > 2){
                                System.out.println("\t\tWrong input! Enter 1 or 2");
                                select = -1;
                            }
                        }
                        if(select == 1){
                            System.out.print("\t\tChoose task Status! Enter");
                            int priorityChoice = -1;
                            while(true) {
                                while (priorityChoice == -1) {
                                    System.out.print("\t\t S.no: ");
                                    priorityChoice = Validation.numberCheck(scanner);
                                }

                                if (priorityChoice < 1 || priorityChoice > DataModel.getTaskStatus().size()) {
                                    System.out.println("\n\t\t S.no not found!");
                                } else {
                                    break;
                                }
                            }

                            selectedSubTask.setStatus(DataModel.getTaskStatus().get(priorityChoice-1));
                            //selectedProject.getProgressArrayList().add(selectedTask);
                            if(selectedSubTask.getStatus().equalsIgnoreCase("Submitted for test")){
                                for(Members members : this.getProjectArrayList().get(0).getTeamMemberArrayList()){
                                    if(members.getType().equalsIgnoreCase("Tester")){
                                        members.getAssignedTaskArrayList().add(selectedTask);
                                        members.getNotification().add("\t\t\tTask -> " +selectedTask.getTaskName()+ " Added for testing!");
                                    }
                                }
                            }
                        }
                        else{
                            System.out.print("\t\tEnter the custom Status : ");
                            String chat;
                            //scanner.nextLine();
                            chat = scanner.nextLine();
                            System.out.print("");

                            DataModel.getTaskStatus().add(chat);
                            selectedSubTask.setStatus(chat);
                        }

                    }

                    default -> System.out.println("\n\tWrong value. Give correct input number!\n");
                }
            }
        }
        else{
            if(getType().equalsIgnoreCase("Tester")){
                System.out.println("\n\t\tCurrent Status : " + selectedSubTask.getStatus());
                System.out.print("\t\tEnter the new New Priority S.no : ");
                System.out.print("\n\t\t\t S.no : 1. Issue Reported");
                System.out.print("\n\t\t\t S.no : 1. Completed");

                System.out.print("\t\tChoose task Status! Enter");
                int priorityChoice = -1;
                while(true) {
                    while (priorityChoice == -1) {
                        System.out.print("\t\t S.no: ");
                        priorityChoice = Validation.numberCheck(scanner);
                    }

                    if (priorityChoice < 1 || priorityChoice > 2) {
                        System.out.println("\n\t\t S.no not found!");
                    } else {
                        break;
                    }
                }
                if(priorityChoice==1){
                    selectedSubTask.setStatus("Issue Reported");
                } else{
                    selectedSubTask.setStatus("Completed");
                }


            }
            else if(getType().equalsIgnoreCase("Member")){
                System.out.println("\n\t\tCurrent Status : " + selectedSubTask.getStatus());
                System.out.print("\t\tEnter the new New Priority S.no : ");

                int i = 0;
                for(String m : DataModel.getTaskStatus()){
                    if(!m.equalsIgnoreCase("Completed") || !m.equalsIgnoreCase("Issue Reported")){
                        i++;
                        System.out.print("\n\t\t\t S.no : " + i + ". " + m);
                    }
                }
                System.out.println();
                DesignModel.printLine();

                System.out.print("\t\tChoose task Status! Enter");
                int priorityChoice = -1;
                while(true) {
                    while (priorityChoice == -1) {
                        System.out.print("\t\t S.no: ");
                        priorityChoice = Validation.numberCheck(scanner);
                    }

                    if (priorityChoice < 1 || priorityChoice > DataModel.getTaskStatus().size()-2) {
                        System.out.println("\n\t\t S.no not found!");
                    } else {
                        break;
                    }
                }

                selectedSubTask.setStatus(DataModel.getTaskStatus().get(priorityChoice-1));
            }
        }

    }

    //This function is used to delete the subtask
    public void deleteSubTask(){
        System.out.println();
        DesignModel.printLine();

        System.out.println("\t\tView the Project tasks");
        System.out.println();

        if(projectArrayList.size() == 0){
            System.out.print("\t\tNo Projects found!\n");
            DesignModel.printLine();
        }
        else {
            int choice;
            if (projectArrayList.size() == 1) {
                choice = 1;
            } else {
                System.out.printf("\n\t\t%15s %15s %15s %25s %35s\n", "S.no", "ProjectName", "Deadline", "Status", "Description");
                int i = 0;
                for (Project project : projectArrayList) {

                    i++;
                    System.out.printf("\t\t%15s %15s %15s %25s %35s\n", i, project.getProjectName(), project.getDeadline(), project.getStatus(), project.getProjectDescription());
                }
                DesignModel.printLine();


                while (true) {
                    System.out.print("\n\t\tEnter the s.no of the Project which you want to View Tasks : ");
                    choice = Validation.numberCheck(scanner);
                    if (choice > 0 && choice <= projectArrayList.size()) {
                        break;
                    } else {
                        System.out.println("\t\tWrong input");
                    }
                }
            }

            Project selectedProject = projectArrayList.get(choice - 1);

            if (selectedProject.getTaskArrayList().size() == 0) {
                System.out.print("\t\tNo Task found!\n");
                DesignModel.printLine();
            } else {
                if (selectedProject.getTaskArrayList().size() == 1) {
                    choice = 1;
                } else {
                    System.out.printf("\n\t\t%15s %15s %15s %25s %35s\n", "S.no", "ProjectName", "Deadline", "Status", "Description");
                    int i = 0;
                    for (Task task : selectedProject.getTaskArrayList()) {

                        i++;
                        System.out.printf("\t\t%15s %15s %15s %25s %35s\n", i, task.getTaskName(), task.getDeadline(), task.getStatus(), task.getDescription());
                    }
                    DesignModel.printLine();


                    while (true) {
                        System.out.print("\n\t\tEnter the s.no of the Task which you want to View SubTasks : ");
                        choice = Validation.numberCheck(scanner);
                        if (choice > 0 && choice <= projectArrayList.size()) {
                            break;
                        } else {
                            System.out.println("\t\tWrong input");
                        }
                    }
                }

                Task selectedTask = selectedProject.getTaskArrayList().get(choice - 1);

                if (selectedTask.getSubTask().size() == 0) {
                    System.out.println("\t\t\tNo subTask is created yet!");
                } else {
                    int i = 0;
                    System.out.printf("\n\t\t%15s %15s %15s %20s %25s %25s\n", "S.no", "TaskName", "Priority", "Deadline", "Status", "Description");
                    for (Task task : selectedTask.getSubTask()) {
                        i++;
                        System.out.printf("\t\t%15s %15s %15s %20s %25s %25s\n", i, task.getTaskName(), task.getPriority(), task.getDeadline(), task.getStatus(), task.getDescription());
                    }

                    DesignModel.printLine();
                }

                while(true){
                    if(selectedTask.getSubTask().size()==1){
                        choice=1;
                        break;
                    }
                    else{
                        System.out.print("\n\t\tEnter the s.no of the Task which you want to update : ");
                        choice = Validation.numberCheck(scanner);
                        if(choice>0 && choice<=selectedTask.getSubTask().size()){
                            break;
                        }
                        else{
                            System.out.println("\t\tWrong input");
                        }
                    }

                }

                Task selectedSubTask = selectedTask.getSubTask().get(choice-1);
                selectedTask.getSubTask().remove(selectedSubTask);
                System.out.println("\t\tSub Task Deleted");
            }
        }
    }

    // This function is used to view the chat group
    public int readDiscussionBox(){
        System.out.println();
        DesignModel.printLine();

        System.out.println("\t\tCHAT BOX");
        if(getProjectArrayList().size() == 0){
            System.out.println("\t\tNo chatBoxes are available for you!");
            return -1;
        }
        System.out.printf("\n\t\t%15s %15s %15s %25s %35s\n", "S.no", "ProjectName", "Deadline", "Status", "Description");
        int i = 0;
        for (Project project : projectArrayList) {

            i++;
            System.out.printf("\t\t%15s %15s %15s %25s %35s\n", i, project.getProjectName(), project.getDeadline(), project.getStatus(), project.getProjectDescription());
        }
        DesignModel.printLine();

        int choice;

        while(true){
            System.out.print("\n\t\tEnter the s.no of the Project which you want to chat : ");
            choice = Validation.numberCheck(scanner);
            if(choice>0 && choice<=projectArrayList.size()){
                break;
            }
            else{
                System.out.println("\t\tWrong input");
            }
        }

        Project selectedProject = projectArrayList.get(choice-1);
        if(selectedProject.getChatGroup().size() == 0){
            System.out.println("\t\tChatbox is Empty");
        }
        else{
            for(String msg : selectedProject.getChatGroup()){
                System.out.println("\t\t\t" + msg);
            }
        }
        return choice-1;
    }

    // This function is used to add a chat
    public void writeDiscussionBox(){

        int chatboxResult = readDiscussionBox();
        while(true){

            if(chatboxResult == -1){
                break;
            }
            else{
                System.out.println("\n\t\t\t Enter 1 to Add a chat.");
                System.out.println("\t\t\t Enter -1 to End chat");

                int choice;
                do {
                    System.out.print("\n\t\tEnter your choice : ");
                    choice = Validation.numberCheck(scanner);
                } while (choice == -1);

                if (choice == -2) {
                    System.out.println();
                    DesignModel.printLine();

                    break;
                } else if (choice == 1) {
                    String chat;
                    System.out.print("\n\t\tYour message : ");
                    //scanner.nextLine();
                    chat = scanner.nextLine();
                    System.out.print("");

                    if(Validation.messageValidation(chat)){
                        getProjectArrayList().get(chatboxResult).getChatGroup().add("\t\t\t\t" +  this.getType() + " -> " + this.getName() + " : " + chat);
                    }
                } else {
                    System.out.println("\t\tWrong number. check your Input!\n");
                }
            }
        }
    }

    // This function is used to view the files uploaded for the project
    public int viewFiles(){
        System.out.println();
        DesignModel.printLine();

        System.out.println("\t\tFile Folder");
        if(getProjectArrayList().size() == 0){
            System.out.println("\t\tNo File Folder are available for you!");
            return -1;
        }
        System.out.printf("\n\t\t%15s %15s %15s %25s %35s\n", "S.no", "ProjectName", "Deadline", "Status", "Description");
        int i = 0;
        for (Project project : projectArrayList) {

            i++;
            System.out.printf("\t\t%15s %15s %15s %25s %35s\n", i, project.getProjectName(), project.getDeadline(), project.getStatus(), project.getProjectDescription());
        }
        DesignModel.printLine();

        int choice;

        while(true){
            System.out.print("\n\t\tEnter the s.no of the Project which you want to add Files : ");
            choice = Validation.numberCheck(scanner);
            if(choice>0 && choice<=projectArrayList.size()){
                break;
            }
            else{
                System.out.println("\t\tWrong input");
            }
        }

        Project selectedProject = projectArrayList.get(choice-1);
        if(selectedProject.getFileManager().size() == 0){
            System.out.println("\t\tFileFolder is Empty");
        }
        else{
            for(String msg : selectedProject.getFileManager()){
                System.out.println("\t\t\t" + msg + "\t\tDownload!");
            }
        }
        return choice-1;
    }

    // This function is used to upload files to a project
    public void inputFiles(){

        int chatboxResult = viewFiles();
        while(true){

            if(chatboxResult == -1){
                break;
            }
            else{
                System.out.println("\n\t\t\t Enter 1 to Add a File.");
                System.out.println("\n\t\t\t Enter 2 to Download a File.");
                System.out.println("\t\t\t Enter -1 to Close");

                int choice;
                do {
                    System.out.print("\n\t\tEnter your choice : ");
                    choice = Validation.numberCheck(scanner);
                } while (choice == -1);

                if (choice == -2) {
                    System.out.println();
                    DesignModel.printLine();

                    break;
                } else if (choice == 1) {
                    String chat;
                    System.out.print("\n\t\tYour FileName : ");
                    //scanner.nextLine();
                    chat = scanner.nextLine();
                    System.out.print("");

                    if(Validation.messageValidation(chat)){
                        File file = new File("E:/Java/projects/taskManagement/src/files/"+chat);
                        if(file.exists()) {
                            System.out.println("\t\tFile Uploaded Successfully");
                            getProjectArrayList().get(chatboxResult).getFileManager().add("\t\t\t\t" + this.getType() + " -> " + this.getName() + " : " + chat);
                        }
                        else{
                            System.out.println("\t\tFile not Found in your Directory");
                        }
                    }
                } else if (choice == 2) {
                    int size = getProjectArrayList().get(chatboxResult).getFileManager().size();
                    if(size == 0)
                        System.out.println("\t\t\tNo Files are found!");
                    else{
                        int i=0;
                        for(String string : getProjectArrayList().get(chatboxResult).getFileManager()){
                            i++;
                            System.out.printf("\t\t%s. %s\n", i, string);
                        }

                        int file;

                        while(true){
                            System.out.print("\n\t\tEnter the s.no of the file you want to download : ");
                            file = Validation.numberCheck(scanner);
                            if(file>0 && file<=getProjectArrayList().get(chatboxResult).getFileManager().size()){
                                break;
                            }
                            else{
                                System.out.println("\t\tWrong input");
                            }
                        }

                        System.out.println("\n\t\t\t" + getProjectArrayList().get(chatboxResult).getFileManager().get(file-1) + " file Downloaded");
                    }
                } else {
                    System.out.println("\t\tWrong number. check your Input!\n");
                }
            }
        }
    }

    // This function is used for the permission settings
    public void permissionSettings(){
        while(true){

            int i=0;
            for(String key : DataModel.getTypeOfUser().keySet().stream().toList()){
                i++;
                System.out.println("\n\t\t" + i + ". " + key + " : Permissions Allowed\n");
                for(String configs : DataModel.getTypeOfUser().get(key)){
                    System.out.println("\t\t\t"+configs);
                }
            }
            System.out.println("\n\t\t Enter the S.no of User Type who you want to edit Permissions.\n\t\t Enter 0 to add new user Type.\n\t\t Enter -1 to go back");

            int choice;
            do {
                System.out.print("\n\t\tEnter your choice : ");
                choice = Validation.numberCheck(scanner);
            } while (choice == -1);

            if(choice > DataModel.getTypeOfUser().keySet().size() || choice < -2){
                System.out.println("\t\tEnter correct number!");
            } else if(choice == -2){
                break;
            } else if(choice == 0){
                this.setUserType();
            } else{
                String typeofUser = DataModel.getTypeOfUser().keySet().stream().toList().get(choice-1);
                while(true){
                    System.out.println("\n\t\t\tEnter 1 to Remove Permission.\t\t\tEnter 2 to Add Permission");
                    int choices;
                    do {
                        System.out.print("\n\t\tEnter your choice : ");
                        choices = Validation.numberCheck(scanner);
                    } while (choices == -1);

                    if(choices == 1) {
                        i = 0;
                        for (String string : DataModel.getTypeOfUser().get(typeofUser)) {
                            i++;
                            System.out.println("\t\t" + i + ". " + string);
                        }
                        System.out.println("\n\t\tEnter -1 to go back");
                        while (true) {
                            ArrayList<Integer> indexes = new ArrayList<>();
                            int c;
                            do {
                                System.out.print("\t\tEnter your choice : ");
                                c = Validation.numberCheck(scanner);
                            } while (c == -1);
                            if(c > DataModel.getTypeOfUser().get(typeofUser).size() || c < -2 || c == 0){
                                System.out.println("\t\tEnter correct number!");
                                c = -1;
                            }
                            if(c == -2){
                                for(int index : indexes){
                                    DataModel.getTypeOfUser().get(typeofUser).remove(index);
                                }
                                System.out.println("\t\tPermission Removing completed!\n");
                                break;
                            } else {
                                indexes.add(c-1);
                            }
                        }
                    } else if(choices == 2){
                        ArrayList<String> leftConfigs = DataModel.getConfigurations();
                        for(String string : DataModel.getTypeOfUser().get(typeofUser)){
                            leftConfigs.remove(string);
                        }

                        i = 0;
                        for(String string : leftConfigs){
                            i++;
                            System.out.println("\t\t" + i + ". " + string);
                        }
                        System.out.println("\t\tEnter -1 to go back");
                        while(true) {
                            int c;
                            do {
                                System.out.print("\n\t\tEnter your choice : ");
                                c = Validation.numberCheck(scanner);
                            } while (c == -1);

                            if(c > leftConfigs.size() || c < -2){
                                System.out.println("\t\tEnter correct number!");
                                c = -1;
                            }
                            if(c == -2){
                                break;
                            } else {
                                DataModel.getTypeOfUser().get(typeofUser).add(leftConfigs.get(c-1));
                            }
                        }

                    } else if(choices == -2){
                        break;
                    }else{
                        System.out.println("\t\tEnter correct number!");
                    }
                }
            }


        }
    }

    public void createIssue(Task selectedTask) {

        System.out.println("\t\tAdd Associated Issue to the Task");

        boolean done = false;
        int choice;
        while (!done) {
            System.out.println("\t\t\t1. Add Issue");
            System.out.println("\t\t\t-1. Issue Adding completed");
            do {
                System.out.print("\n\t\tEnter your choice : ");
                choice = Validation.numberCheck(scanner);
            } while (choice == -1);

            if (choice == 1) {
                Issues tasks;

                String taskName, taskDescription, taskDeadline;

                System.out.print("\t\t\tEnter Name of the Issue : ");
                while ((taskName = scanner.nextLine()).isEmpty()) {
                    System.out.print("Enter a Valid Issue name : ");
                }

                System.out.print("\t\t\tIssue Description : ");
                //scanner.nextLine();
                taskDescription = scanner.nextLine();
                System.out.print("");
                if (taskDescription.isEmpty()) {
                    taskDescription = "No Description";
                }
                System.out.print("");


                while (true) {
                    System.out.println("\n\t\t\tEnter 1 to give a Deadline. \n\t\t\tEnter 2 to give Duration. \n\t\t\tEnter 3 to skip");
                    int dead = -1;
                    while (dead == -1) {
                        System.out.print("\t\t S.no: ");
                        dead = Validation.numberCheck(scanner);
                    }

                    if (dead == 1) {
                        int flag = 0;
                        do {
                            System.out.println("\t\t\tEnter -1 to go back");
                            System.out.print("\t\t\tIssue Deadline (Date format : dd-MM-yyyy) : ");
                            taskDeadline = scanner.next();
                            if (taskDeadline.equalsIgnoreCase("-1")) {
                                flag = 1;
                                break;
                            }

                        } while (!Validation.dateValidation(taskDeadline));
                        if (flag == 0) {
                            break;
                        }
                    } else if (dead == 2) {
                        int flag = -1;
                        System.out.println("\t\t\tIssue Duration : ");
                        System.out.println("\n\t\t\tEnter -1 to go back");
                        System.out.println("\t\t\tEnter 1 to Set years");
                        System.out.println("\t\t\tEnter 2 to Set months");
                        System.out.println("\t\t\tEnter 3 to Set weeks");
                        System.out.println("\t\t\tEnter 4 to Set days");
                        System.out.print("\t\t\tEnter your choice : ");

                        while (flag == -1) {
                            System.out.print("\t\t S.no: ");
                            flag = Validation.numberCheck(scanner);
                        }

                        if (flag == -2) {
                            continue;
                        } else if (flag == 1) {
                            System.out.println("\n\t\t\tEnter number of Years");
                            taskDeadline = Validation.numberCheck(scanner) + " Years";
                            break;
                        } else if (flag == 2) {
                            System.out.println("\n\t\t\tEnter number of Months");
                            taskDeadline = Validation.numberCheck(scanner) + " Months";
                        } else if (flag == 3) {
                            System.out.println("\n\t\t\tEnter number of Weeks");
                            taskDeadline = Validation.numberCheck(scanner) + " Weeks";
                        } else if (flag == 4) {
                            System.out.println("\n\t\t\tEnter number of Days");
                            taskDeadline = Validation.numberCheck(scanner) + " Days";
                        } else {
                            System.out.println("\t\t\tWrong input!");
                            continue;
                        }
                        break;
                    } else if (dead == 3) {
                        taskDeadline = "   -   ";
                        break;
                    } else {
                        System.out.println("\t\t\tWrong input!");
                    }
                }


                System.out.println("\t\tPriority List : ");

                int i = 0;
                for (String m : DataModel.getIssueSeverity()) {
                    i++;
                    System.out.print("\n\t\t\t S.no : " + i + ". " + m);
                }
                System.out.println();
                DesignModel.printLine();

                System.out.print("\t\tChoose Issue Priority! Enter");
                int priorityChoice = -1;
                while (true) {
                    while (priorityChoice == -1) {
                        System.out.print("\t\t S.no: ");
                        priorityChoice = Validation.numberCheck(scanner);
                    }

                    if (priorityChoice < 1 || priorityChoice > DataModel.getIssueSeverity().size()) {
                        System.out.println("\n\t\t S.no not found!");
                        priorityChoice = -1;
                    } else {
                        break;
                    }
                }
                tasks = new Issues(taskName, selectedTask.getTaskOwner(), taskDescription, taskDeadline, DataModel.getIssueSeverity().get(priorityChoice - 1));
                selectedTask.getAssociatedIssues().add(tasks);
                tasks.setAssignedMembers(selectedTask.getAssignedMembers());
                tasks.setFollowers(selectedTask.getFollowers());
                for (Members members : selectedTask.getAssignedMembers()) {
                    members.getAssignedIssueArrayList().add(tasks);
                }

                System.out.println();
                DesignModel.printLine();
            } else if (choice == -2) {
                done = true;
                System.out.println();
                DesignModel.printLine();
            } else {
                System.out.println("\t\tWrong number. check your Input!\n");
            }
        }
    }

    public void viewAssignedIssue(){
        System.out.println();
        DesignModel.printLine();

        System.out.println("\t\tView the Project tasks");
        System.out.println();

        if(this.getAssignedTaskArrayList().size() == 0){
            System.out.print("\t\tNo Tasks are assigned to you yet!\n");
            DesignModel.printLine();
        }
        else {

            int i = 0;
            System.out.printf("\n\t\t%15s %15s %15s %20s %25s %25s\n", "S.no", "TaskName", "Priority", "Deadline", "Status", "Description");
            for (Task task : this.getAssignedTaskArrayList()) {
                i++;
                System.out.printf("\t\t%15s %15s %15s %20s %25s %25s\n", i, task.getTaskName(), task.getPriority(), task.getDeadline(), task.getStatus(), task.getDescription());
            }

            DesignModel.printLine();
            this.updateAssignedIssueDetails();

        }
    }

    // This function is used to update the status of the assigned tasks of members
    private void updateAssignedIssueDetails() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        int choice;

        while (true) {
            if (this.getAssignedTaskArrayList().size() == 1) {
                choice = 1;
                break;
            } else {
                System.out.print("\n\t\tEnter the s.no of the Task which you want to update : ");
                choice = Validation.numberCheck(scanner);
                if (choice > 0 && choice <= this.getAssignedTaskArrayList().size()) {
                    break;
                } else {
                    System.out.println("\t\tWrong input");
                }
            }

        }

        Task selectedTask = this.getAssignedTaskArrayList().get(choice - 1);

        System.out.println("\t\tView the Associated Issues");
        System.out.println();

        if (selectedTask.getAssociatedIssues().size() == 0) {
            System.out.print("\t\tNo Issues are assigned to you yet!\n");
            DesignModel.printLine();
        } else {

            int i = 0;
            System.out.printf("\n\t\t%15s %15s %15s %20s %25s %25s\n", "S.no", "IssueName", "Priority", "Deadline", "Status", "Description");
            for (Issues task : selectedTask.getAssociatedIssues()) {
                i++;
                System.out.printf("\t\t%15s %15s %15s %20s %25s %25s\n", i, task.getTaskName(), task.getPriority(), task.getDeadline(), task.getStatus(), task.getDescription());
            }

            DesignModel.printLine();

            System.out.println("\n\t\tEnter 1 to update Issue Details,\n\t\tEnter 2 to view Activity Stream,\n\t\tEnter 3 to Comments,\n\t\tEnter -1 to go back");
            int ver;
            while (true) {
                System.out.print("\t\tEnter your choice : ");
                ver = Validation.numberCheck(scanner);
                if (ver == -2 || ver == 1 || ver == 2 || ver == 3) {
                    break;
                } else {
                    System.out.println("\t\tWrong input");
                }
            }

            if (ver == 1) {

                while (true) {
                    if (selectedTask.getAssociatedIssues().size() == 1) {
                        choice = 1;
                        break;
                    } else {
                        System.out.print("\n\t\tEnter the s.no of the Issue which you want to update : ");
                        choice = Validation.numberCheck(scanner);
                        if (choice > 0 && choice <= selectedTask.getAssociatedIssues().size()) {
                            break;
                        } else {
                            System.out.println("\t\tWrong input");
                        }
                    }

                }

                Task selectedIssue = selectedTask.getAssociatedIssues().get(choice - 1);

                boolean update = true;
                while(update) {
                    System.out.println("\n\t\t\tEnter the s.no of credential you want to change!");
                    System.out.println("\t\t\t Enter 1 to Issue Status ");
                    System.out.println("\t\t\t Enter 2 to Issue Remainder");
                    System.out.println("\t\t\t Enter -1 to Go back\n");

                    int updateChoice = -1;
                    while (updateChoice == -1) {
                        System.out.print("\t\t\t Enter your Choice : ");
                        updateChoice = Validation.numberCheck(scanner);
                    }

                    switch (updateChoice) {
                        case -2 -> {
                            update = false;

                            System.out.println();
                            DesignModel.printLine();
                        }
                        case 1 -> {
                            if (getType().equalsIgnoreCase("Tester")) {
                                System.out.println("\n\t\tCurrent Status : " + selectedIssue.getStatus());
                                System.out.print("\t\tEnter the new New Priority S.no : ");
                                System.out.print("\n\t\t\t S.no : 1. Issue Reported");
                                System.out.print("\n\t\t\t S.no : 2. Completed");

                                System.out.print("\n\t\t\tChoose task Status! Enter");
                                int priorityChoice = -1;
                                while (true) {
                                    while (priorityChoice == -1) {
                                        System.out.print("\t\t S.no: ");
                                        priorityChoice = Validation.numberCheck(scanner);
                                    }

                                    if (priorityChoice < 1 || priorityChoice > 2) {
                                        System.out.println("\n\t\t S.no not found!");
                                    } else {
                                        break;
                                    }
                                }
                                if (priorityChoice == 1) {
                                    selectedIssue.getActivityStream().add("The Issue Status is changed from " + selectedTask.getStatus() + " to Issue Reported by " + this.getName());
                                    for (Members members : selectedTask.getAssignedMembers()) {
                                        if (!members.getName().equalsIgnoreCase(this.getName())) {
                                            members.getWorkflow().add("Project: " + projectArrayList.get(0).getProjectName() + " :-> " + selectedIssue.getTaskName() + ":-> The Issue Status is changed from " + selectedIssue.getStatus() + " to Issue Reported by " + this.getName() + " on " + formatter.format(date));
                                            members.getNotification().add("The Issue Status is changed from " + selectedTask.getStatus() + " to Issue Reported by " + this.getName());
                                        }
                                    }
                                    selectedTask.getTaskOwner().getWorkflow().add("Project: " + projectArrayList.get(0).getProjectName() + " :-> " + selectedIssue.getTaskName() + ":-> The Issue Status is changed from " + selectedIssue.getStatus() + " to Issue Reported by " + this.getName() + " on " + formatter.format(date));
                                    selectedIssue.setStatus("Issue Reported");

                                } else {
                                    selectedTask.getActivityStream().add("The issue Status is changed from " + selectedIssue.getStatus() + " to Completed by " + this.getName());
                                    for (Members members : selectedTask.getAssignedMembers()) {
                                        if (!members.getName().equalsIgnoreCase(this.getName())) {
                                            members.getWorkflow().add("Project: " + projectArrayList.get(0).getProjectName() + " :-> " + selectedIssue.getTaskName() + ":-> The Issue Status is changed from " + selectedIssue.getStatus() + " to Completed by " + this.getName() + " on " + formatter.format(date));
                                            members.getNotification().add("The Task Status is changed from " + selectedTask.getStatus() + " to Completed by " + this.getName());
                                        }
                                    }
                                    selectedTask.getTaskOwner().getWorkflow().add("Project: " + projectArrayList.get(0).getProjectName() + " :-> " + selectedIssue.getTaskName() + ":-> The Issue Status is changed from " + selectedIssue.getStatus() + " to Completed by " + this.getName() + " on " + formatter.format(date));
                                    selectedIssue.setStatus("Completed");
                                }
                            } else if (getType().equalsIgnoreCase("Member")) {
                                System.out.println("\n\t\tCurrent Status : " + selectedIssue.getStatus());
                                System.out.print("\t\tEnter the new New Priority S.no : ");

                                i = 0;
                                for (String m : DataModel.getIssueStatus()) {
                                    if (!m.equalsIgnoreCase("Completed") || !m.equalsIgnoreCase("Issue Reported")) {
                                        i++;
                                        System.out.print("\n\t\t\t S.no : " + i + ". " + m);
                                    }
                                }
                                System.out.println();
                                DesignModel.printLine();

                                System.out.print("\t\tChoose Issue Status! Enter");
                                int priorityChoice = -1;
                                while (true) {
                                    while (priorityChoice == -1) {
                                        System.out.print("\t\t S.no: ");
                                        priorityChoice = Validation.numberCheck(scanner);
                                    }

                                    if (priorityChoice < 1 || priorityChoice > DataModel.getIssueStatus().size() - 2) {
                                        System.out.println("\n\t\t S.no not found!");
                                    } else {
                                        break;
                                    }
                                }
                                for (Members members : selectedTask.getAssignedMembers()) {
                                    if (!members.getName().equalsIgnoreCase(this.getName())) {
                                        members.getWorkflow().add("Project: " + projectArrayList.get(0).getProjectName() + " :-> " + selectedIssue.getTaskName() + ":-> The Issue Status is changed from " + selectedIssue.getStatus() + " to +" + DataModel.getIssueStatus().get(priorityChoice - 1) + " by " + this.getName() + " on " + formatter.format(date));
                                        members.getNotification().add("The Issue Status is changed from " + selectedIssue.getStatus() + " to +" + DataModel.getIssueStatus().get(priorityChoice - 1) + " by " + this.getName());
                                    }
                                }
                                selectedIssue.getActivityStream().add("The Issue Status is changed from " + selectedIssue.getStatus() + " to +" + DataModel.getIssueStatus().get(priorityChoice - 1) + " by " + this.getName());
                                selectedIssue.setStatus(DataModel.getIssueStatus().get(priorityChoice - 1));

                                if (selectedIssue.getStatus().equalsIgnoreCase("Submitted for test")) {
                                    for (Members members : this.getProjectArrayList().get(0).getTeamMemberArrayList()) {
                                        if (members.getType().equalsIgnoreCase("Tester")) {
                                            members.getAssignedTaskArrayList().add(selectedIssue);
                                            members.getNotification().add("\t\t\tTask -> " + selectedIssue.getTaskName() + " Added for testing!");
                                        }
                                    }
                                }
                            }
                        }
                        case 2 -> {
                            System.out.println("\n\t\tCurrent Remainder date : " + selectedIssue.getRemainder());
                            String deadline;
                            do {
                                System.out.print("\t\t\tIssue Remainder (Date format : dd-MM-yyyy) : ");
                                deadline = scanner.next();

                            } while (!Validation.dateValidation(deadline));
                            selectedTask.getActivityStream().add("The Issue Remainder is changed from " + selectedIssue.getRemainder() + " to " + deadline + " by " + this.getName());
                            for (Members members : selectedTask.getAssignedMembers()) {
                                if (!members.getName().equalsIgnoreCase(this.getName())) {
                                    members.getNotification().add("The Issue Remainder is changed from " + selectedIssue.getRemainder() + " to " + deadline + " by " + this.getName());
                                }
                            }
                            selectedIssue.setRemainder(deadline);
                        }

                        default -> System.out.println("\n\tWrong value. Give correct input number!\n");
                    }
                }
            }

            /*else if(ver == 2){
                this.viewAssignedActivityStream();
            } else if (ver == 3) {
                this.viewAssignedComments();
            }*/

        }
    }



    // this function is used to show the list of created projects
    public void showListOfProjects(){
        System.out.println("\n\t\t\tCurrently working Projects : \n");

        int i=0;
        for(Project task : getProjectArrayList()){
            i++;

            System.out.printf("\t\t\t\t%15s %15s %15s %15s %25s\n", "S.no", "ProjectName", "Deadline", "ProjectStatus", "Description");
            System.out.printf("\t\t\t\t%15s %15s %15s %15s %25s\n", i, task.getProjectName(), task.getDeadline(), task.getStatus(), task.getProjectDescription());

        }
    }

    // This function is used to show the notifications for the users
    public void showNotifications() {
        System.out.println("\n\t\t\tShow Notifications : \n");
        for(String notify : this.getNotification()){
            System.out.println("\t\t\t"+ notify);
        }
    }

    // This function is used for the manager notifications
    public void showManagerNotification(){
        System.out.println("\n\t\t\tShow Notifications : \n");
        for(Project project : this.getProjectArrayList()){
            for(Task task : project.getTaskArrayList()){
                if(task.getStatus().equalsIgnoreCase("Completed") || task.getStatus().equalsIgnoreCase("Issue Reported")){
                    this.getNotification().add("\t\t"+task.getTaskName()+"\t\tStatus : "+task.getStatus());
                }
            }


        }
    }

    //This function is used for the project work flow
    public void showWorkflow(){
        System.out.println("\n\t\t\tShow WorkFlow : \n");
        for(String notify : this.getWorkflow()){
            System.out.println("\t\t\t"+ notify);
            System.out.println("\t\t\t\t\t\u2193");
        }
    }

    //  this function is the home view of the dashboard
    public void viewDashboard(){
        System.out.println("\n\t\tDashboard!\n");
        if(this.getType().equalsIgnoreCase("Manager")){
            this.showManagerNotification();
            this.showNotifications();
            this.showListOfProjects();
        }
        else{
            this.showNotifications();
            System.out.println("\n\t\t\tCurrently working Tasks : \n");
            int i=0;
            for(Task task : getAssignedTaskArrayList()){
                i++;
                if(task.getStatus().equalsIgnoreCase("Implementation") || task.getStatus().equalsIgnoreCase("Optimization") || task.getStatus().equalsIgnoreCase("Designing") || task.getStatus().equalsIgnoreCase("Requirement Analysis")){
                    System.out.printf("\t\t\t\t%15s %15s %15s %20s %25s %25s\n", "S.no", "TaskName", "Priority", "Deadline", "Status", "Description");
                    System.out.printf("\t\t\t\t%15s %15s %15s %20s %25s %25s\n", i, task.getTaskName(), task.getPriority(), task.getDeadline(), task.getStatus(), task.getDescription());
                }
            }

            i = 0;
            System.out.println("\n\t\t\t Not Started Tasks : \n");
            for(Task task : getAssignedTaskArrayList()){
                i++;
                if(task.getStatus().equalsIgnoreCase("Not yet Started")){
                    System.out.printf("\t\t\t\t%15s %15s %15s %20s %25s %25s\n", "S.no", "TaskName", "Priority", "Deadline", "Status", "Description");
                    System.out.printf("\t\t\t\t%15s %15s %15s %20s %25s %25s\n", i, task.getTaskName(), task.getPriority(), task.getDeadline(), task.getStatus(), task.getDescription());
                }
            }
        }


        this.showWorkflow();
    }

    public int dashboardDecider(){
        int dash = 0;
        System.out.println("\n\t\tEnter 0 to HomePage. \n\t\tEnter 1 to ListView. \n\t\tEnter 2 to Kanban Board. \n\t\tEnter -1 to go back");
        while (true) {
            System.out.print("\t\tEnter your choice : ");
            dash = Validation.numberCheck(scanner);
            if (dash == -2 || dash == 1 || dash == 2 || dash == 0) {
                break;
            } else {
                System.out.println("\t\tWrong input");
            }
        }
        if(dash == 1){
            dash = this.decideListview();
        } else if(dash == 2){
            dash = this.decideKanbanView();
        }
        return dash;
    }

    //This function is List view by status for dashboard
    public void listviewByStatus(){
        System.out.println("\n\t\tListView :");
        for(String status : DataModel.getTaskStatus()){
            //System.out.println("\n\t\t\t"+status);
            for(Project project : getProjectArrayList()){
                for(Task task : project.getTaskArrayList()) {
                    if (task.getStatus().equalsIgnoreCase(status)) {
                        System.out.print("\t\t\t\t"+project.getProjectName() + " : -> ");
                        System.out.printf("%15s %15s %20s %25s %25s\n", task.getTaskName(), task.getPriority(), task.getDeadline(), task.getStatus(), task.getDescription());
                    }
                }
            }
        }
    }

    // This function is for listview by priority for dashboard
    public void listviewByPriority(){
        System.out.println("\n\t\tListView :");
        for(String status : DataModel.getPriority()){
            //System.out.println("\n\t\t\t"+status);
            for(Project project : getProjectArrayList()){
                for(Task task : project.getTaskArrayList()) {
                    if (task.getPriority().equalsIgnoreCase(status)) {
                        System.out.print("\t\t\t\t"+project.getProjectName() + " : -> ");
                        System.out.printf("%15s %15s %20s %25s %25s\n", task.getTaskName(), task.getPriority(), task.getDeadline(), task.getStatus(), task.getDescription());
                    }
                }
            }
        }
    }

    // This function is for kanban board by status
    public void kanbanViewByStatus(){
        System.out.println("\n\t\tKanban board :");
        for(String status : DataModel.getTaskStatus()){
            System.out.println("\n\t\t\t"+status+"\n");
            for(Project project : getProjectArrayList()){
                for(Task task : project.getTaskArrayList()) {
                    if (task.getStatus().equalsIgnoreCase(status)) {
                        System.out.print("\t\t\t\t"+project.getProjectName() + " : -> ");
                        System.out.printf("%15s %15s %20s %25s %25s\n", task.getTaskName(), task.getPriority(), task.getDeadline(), task.getStatus(), task.getDescription());
                    }
                }
            }
            System.out.println();
        }
    }

    //  This function is for kanban board by priority
    public void kanbanViewByPriority(){
        System.out.println("\n\t\tKanban board:");
        for(String status : DataModel.getPriority()){
            System.out.println("\n\t\t\t"+status+"\n");
            for(Project project : getProjectArrayList()){
                for(Task task : project.getTaskArrayList()) {
                    if (task.getPriority().equalsIgnoreCase(status)) {
                        System.out.print("\t\t\t\t"+project.getProjectName() + " : -> ");
                        System.out.printf("\t\t\t\t%15s %15s %20s %25s %25s\n", task.getTaskName(), task.getPriority(), task.getDeadline(), task.getStatus(), task.getDescription());
                    }
                }
            }
            System.out.println();
        }
    }

    //this function is to decide to display the listview 1 out of 2
    public int decideListview(){
        int decide = 0;
        listviewByStatus();
        System.out.println("\n\t\tEnter 1. to Show by status. \n\t\tEnter 2. to Show by Priority. \n\t\tEnter -1. to go back");
        while (true) {
            System.out.print("\t\tEnter your choice : ");
            decide = Validation.numberCheck(scanner);
            if (decide == -2 || decide == 1 || decide == 2) {
                break;
            } else {
                System.out.println("\t\tWrong input");
            }
        }
        if(decide == 1 || decide == -2){
            return 1;
        }
        else{
            return 3;
        }
    }

    // this function is to decide the kanban board ro display one out of two
    public int decideKanbanView(){
        int decide = 0;
        kanbanViewByStatus();
        System.out.println("\n\t\tEnter 1. to Show by status. \n\t\tEnter 2. to Show by Priority. \n\t\tEnter -1. to go back");
        while (true) {
            System.out.print("\t\tEnter your choice : ");
            decide = Validation.numberCheck(scanner);
            if (decide == -2 || decide == 1 || decide == 2) {
                break;
            } else {
                System.out.println("\t\tWrong input");
            }
        }
        if(decide == 1 || decide == -2){
            return 2;
        }
        else{
            return 4;
        }
    }

    // This function is to export the tasks of the project
    public void exportTasks() {
        System.out.println();
        DesignModel.printLine();

        System.out.println("\t\tView the Project tasks");
        System.out.println();

        if (projectArrayList.size() == 0) {
            System.out.print("\t\tNo Projects found!\n");
            DesignModel.printLine();
        } else {
            int choice;
            if (projectArrayList.size() == 1) {
                choice = 1;
            } else {
                System.out.printf("\n\t\t%15s %15s %15s %25s %35s\n", "S.no", "ProjectName", "Deadline", "Status", "Description");
                int i = 0;
                for (Project project : projectArrayList) {

                    i++;
                    System.out.printf("\t\t%15s %15s %15s %25s %35s\n", i, project.getProjectName(), project.getDeadline(), project.getStatus(), project.getProjectDescription());
                }
                DesignModel.printLine();


                while (true) {
                    System.out.print("\n\t\tEnter the s.no of the Project which you want to View Tasks : ");
                    choice = Validation.numberCheck(scanner);
                    if (choice > 0 && choice <= projectArrayList.size()) {
                        break;
                    } else {
                        System.out.println("\t\tWrong input");
                    }
                }
            }

            Project selectedProject = projectArrayList.get(choice - 1);

            File file = new File("E:/Java/projects/taskManagementSystem/src/files/export.csv");

            try {

                // create FileWriter object with file as parameter
                FileWriter outputfile = new FileWriter(file);

                // create CSVWriter object filewriter object as parameter
                CSVWriter writer = new CSVWriter(outputfile);

                // create a List which contains String array
                List<String[]> data = new ArrayList<>();
                for(Task t : selectedProject.getTaskArrayList()){
                    data.add(new String[] {t.taskName, t.taskOwner.getName(), t.description, t.deadline, t.priority});
                }
                writer.writeAll(data);

                // closing writer connection
                writer.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // This function is the root which contains every functions and activities that a user can do
    public void workOfMember(){
        if(this.getType().equalsIgnoreCase("Tester")){
            ArrayList<Task> temp = new ArrayList<>();
            for(Task task : this.getAssignedTaskArrayList()){
                if(task.getStatus().equalsIgnoreCase("Submitted for test") && !temp.contains(task)){
                    temp.add(task);
                }
            }
            this.getAssignedTaskArrayList().clear();
            this.getAssignedTaskArrayList().addAll(temp);
        }

        System.out.println("\n\t\tWelcome : " + this.getName().toUpperCase());
        if(this.type.equalsIgnoreCase("Manager") && this.projectArrayList.size()==0){
            this.sampleProject();
        }
        int dash = 0;

        while(true) {
            if(dash == 0){
                this.viewDashboard();
            } else if(dash == 1){
                this.listviewByStatus();
            } else if(dash == 2){
                this.kanbanViewByStatus();
            } else if (dash == 3) {
                this.listviewByPriority();
            } else {
                this.kanbanViewByPriority();
            }

            int flag = 0;
            HashMap<Integer, String> listOfMethods = new HashMap<>();
            System.out.println("\n\t\tWhat would you like to do :");

            System.out.println("\n\t\t\t Enter "+ flag + " to Change Password");
            listOfMethods.put(flag, "Change Password");
            flag++;

            System.out.println("\t\t\t Enter "+ flag + " to Dashboard View -> ListView, KanbanBoard");
            listOfMethods.put(flag, "Dashboard View");
            flag++;

            if(DataModel.getTypeOfUser().get(this.type).contains("Add Users to Organisation")){
                System.out.println("\t\t\t Enter "+ flag + " to Add a User to your Organisation");
                listOfMethods.put(flag, "Add User to org");
                flag++;
            }

            if(this.getType().equalsIgnoreCase("Manager")){
                System.out.println("\t\t\t Enter " + flag + " to View/Update User Type");
                listOfMethods.put(flag, "Update User");
                flag++;
            }

            if(DataModel.getTypeOfUser().get(this.type).contains("Create Projects")) {
                System.out.println("\t\t\t Enter "+ flag + " to Create a new Project");
                listOfMethods.put(flag, "Create Project");
                flag++;
            }

            if(DataModel.getTypeOfUser().get(this.type).contains("Update Projects")) {
                System.out.println("\t\t\t Enter "+ flag + " to View/Update Details of Projects");
                listOfMethods.put(flag, "View Projects");
                flag++;
            }

            if(DataModel.getTypeOfUser().get(this.type).contains("Create Tasks")){
                System.out.println("\t\t\t Enter "+ flag + " to Add Tasks To Project");
                listOfMethods.put(flag,"Create Tasks");
                flag++;
            }

            if(DataModel.getTypeOfUser().get(this.type).contains("Update Tasks")||DataModel.getTypeOfUser().get(this.type).contains("Update Tasks Status")){
                System.out.println("\t\t\t Enter "+ flag + " to View/Update Details of Task");
                listOfMethods.put(flag, "View Tasks");
                flag++;
            }

            if(DataModel.getTypeOfUser().get(this.type).contains("Delete Tasks")){
                System.out.println("\t\t\t Enter "+ flag + " to Delete Task");
                listOfMethods.put(flag, "Delete Tasks");
                flag++;
            }

            if(DataModel.getTypeOfUser().get(this.type).contains("Update Issues") || DataModel.getTypeOfUser().get(this.type).contains("Update Issues Status")){
                System.out.println("\t\t\t Enter "+flag+" to View/Update Details of Issues");
                listOfMethods.put(flag, "View Issues");
                flag++;
            }

            if(DataModel.getTypeOfUser().get(this.type).contains("Create Milestones")){
                System.out.println("\t\t\t Enter " + flag + " to Add Milestone To Project");
                listOfMethods.put(flag, "Create Milestones");
                flag++;
            }

            if(DataModel.getTypeOfUser().get(this.type).contains("Update Milestones")){
                System.out.println("\t\t\t Enter " + flag + " to View/Update Details of Milestone");
                listOfMethods.put(flag, "Update Milestones");
                flag++;
            }

            if(DataModel.getTypeOfUser().get(this.type).contains("Create Tasks")){
                System.out.println("\t\t\t Enter " + flag + " to Create Sub Task");
                listOfMethods.put(flag, "Create SubTask");
                flag++;
            }

            if(DataModel.getTypeOfUser().get(this.type).contains("Update Tasks") || DataModel.getTypeOfUser().get(this.type).contains("Update Tasks Status")){
                System.out.println("\t\t\t Enter "+flag+" to View/Update Details of SubTask");
                listOfMethods.put(flag, "View SubTask");
                flag++;
            }

            if(DataModel.getTypeOfUser().get(this.type).contains("Delete Tasks")) {
                System.out.println("\t\t\t Enter "+flag+" to Delete subTask");
                listOfMethods.put(flag, "Delete SubTask");
                flag++;
            }

            System.out.println("\t\t\t Enter "+flag+" for DiscussionBox");
            listOfMethods.put(flag, "DiscussionBox");
            flag++;

            System.out.println("\t\t\t Enter "+flag+" to Add Files");
            listOfMethods.put(flag, "Add files");
            flag++;

            if(this.getType().equalsIgnoreCase("Manager")){
                System.out.println("\t\t\t Enter "+flag+" to Permission Settings");
                listOfMethods.put(flag, "Permission Settings");
                flag++;
            }

            if(DataModel.getTypeOfUser().get(this.type).contains("Export Tasks")){
                System.out.println("\t\t\t Enter "+flag+" to export Tasks as CSV file");
                listOfMethods.put(flag, "Export Task");
            }

            System.out.println("\t\t\t Enter -1 to Logout\n");
            listOfMethods.put(-2, "Logout");

            int adminChoice = -1;
            while (adminChoice == -1) {
                System.out.print("\t\t\t Enter your Choice : ");
                adminChoice = Validation.numberCheck(scanner);
            }

            if(adminChoice >= listOfMethods.size() || adminChoice < -2){
                System.out.println("\n\t\t\tSorry! Wrong Input");
            }
            else{

                switch (listOfMethods.get(adminChoice)) {
                    case "Logout" -> this.exitVerification();
                    case "Change Password" -> this.changePassword();
                    case "Dashboard View" -> dash = this.dashboardDecider();
                    case "Add User to org" -> {
                        if(DataModel.getTypeOfUser().get(this.type).contains("Add Users to Organisation")){
                            this.addMembersToTheCompany();
                        }
                        else{
                            System.out.println("\n\t\t\tSorry! Wrong Input");
                            System.out.println();
                            DesignModel.printLine();
                        }
                    }
                    case "Update User" -> {
                        if(this.getType().equalsIgnoreCase("Manager")){
                            this.editUserType();
                        }
                        else{
                            System.out.println("\n\t\t\tSorry! Wrong input");
                            System.out.println();
                            DesignModel.printLine();
                        }
                    }
                    case "Create Project" -> {
                        if(DataModel.getTypeOfUser().get(this.type).contains("Create Projects")){
                            this.createProjects();
                        }
                        else{
                            System.out.println("\n\t\t\tSorry! You don't have the access to Create Projects");
                            System.out.println();
                            DesignModel.printLine();
                        }
                    }
                    case "View Projects" -> {
                        if(DataModel.getTypeOfUser().get(this.type).contains("Update Projects")){
                            this.viewProjects();
                        }
                        else{
                            System.out.println("\n\t\t\tSorry! Wrong Input");
                            System.out.println();
                            DesignModel.printLine();
                        }
                    }
                    case "Create Tasks" -> {
                        if(DataModel.getTypeOfUser().get(this.type).contains("Create Tasks")){
                            this.createTasks();
                        }
                        else{
                            System.out.println("\n\t\t\tSorry! Wrong Input");
                            System.out.println();
                            DesignModel.printLine();
                        }
                    }
                    case "View Tasks" -> {
                        if(DataModel.getTypeOfUser().get(this.type).contains("Update Tasks")){
                            this.viewTask();
                        }
                        else if(DataModel.getTypeOfUser().get(this.type).contains("Update Tasks Status")){
                            this.viewAssignedTask();
                        }
                        else{
                            System.out.println("\n\t\t\tSorry! Wrong input");
                            System.out.println();
                            DesignModel.printLine();
                        }
                    }
                    case "Delete Tasks" -> {
                        if(DataModel.getTypeOfUser().get(this.type).contains("Delete Tasks")){
                            this.deleteTask();
                        }
                        else{
                            System.out.println("\n\t\t\tSorry! Wrong input");
                            System.out.println();
                            DesignModel.printLine();
                        }
                    }
                    case "View Issues" -> {
                        if(DataModel.getTypeOfUser().get(this.type).contains("Update Issues")){
                            this.viewAssignedIssue();
                        }
                        else if(DataModel.getTypeOfUser().get(this.type).contains("Update Tasks Status")){
                            this.viewAssignedIssue();
                        }
                        else{
                            System.out.println("\n\t\t\tSorry! Wrong input");
                            System.out.println();
                            DesignModel.printLine();
                        }
                    }
                    case "Create Milestones" -> {
                        if(DataModel.getTypeOfUser().get(this.type).contains("Create Milestones")){
                            this.createMilestone();
                        }
                        else{
                            System.out.println("\n\t\t\tSorry! Wrong input");
                            System.out.println();
                            DesignModel.printLine();
                        }
                    }
                    case "Update Milestones" -> {
                        if(DataModel.getTypeOfUser().get(this.type).contains("Update Milestones")){
                            this.viewMilestone();
                        }
                        else{
                            System.out.println("\n\t\t\tSorry! Wrong Input");
                            System.out.println();
                            DesignModel.printLine();
                        }
                    }
                    case "Create SubTask" -> {
                        if(DataModel.getTypeOfUser().get(this.type).contains("Create Tasks")){
                            this.createSubTasks();
                        }
                        else{
                            System.out.println("\n\t\t\tSorry! Wrong input");
                            System.out.println();
                            DesignModel.printLine();
                        }
                    }
                    case "View SubTask" -> {
                        if(DataModel.getTypeOfUser().get(this.type).contains("Update Tasks") || DataModel.getTypeOfUser().get(this.type).contains("Update Tasks Status")){
                            this.viewSubTask();
                        }
                        else{
                            System.out.println("\n\t\t\tSorry! Wrong input");
                            System.out.println();
                            DesignModel.printLine();
                        }
                    }
                    case "Delete SubTask" -> {
                        if(DataModel.getTypeOfUser().get(this.type).contains("Delete Tasks")){
                            this.deleteSubTask();
                        }
                        else{
                            System.out.println("\n\t\t\tSorry! Wrong input");
                            System.out.println();
                            DesignModel.printLine();
                        }
                    }
                    case "DiscussionBox" -> this.writeDiscussionBox();
                    case "Add files" -> this.inputFiles();
                    case "Permission Settings" -> {
                        if(this.getType().equalsIgnoreCase("Manager")){
                            this.permissionSettings();
                        }
                        else{
                            System.out.println("\n\t\t\tWrong input");
                            System.out.println();
                            DesignModel.printLine();
                        }
                    }
                    case "Export Task" -> {
                        if(DataModel.getTypeOfUser().get(this.type).contains("Export Tasks")){
                            this.exportTasks();
                        }
                        else{
                            System.out.println("\n\t\t\tSorry! Wrong input");
                            System.out.println();
                            DesignModel.printLine();
                        }
                    }
                    default -> System.out.println("\n\tWrong value. Give correct input number!\n");

                }
            }
        }
    }

    /*void sample(){
        for(Task task : this.getAssignedTaskArrayList()){
            csv.write("Task", task.getTaskName(), .........);
            for(Task subtask : task.getSubTask()){
                csv.write("Subtask", subtask.getTaskName(), ........);
            }
            for(Issues issue : task.getAssociatedIssues()){
                csv.write("Issue", issue.getAssociatedIssues(),.......);
            }
        }
    }*/
}
