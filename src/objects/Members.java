package objects;

import activities.Validation;
import activities.WelcomePage;
import jdk.jfr.DataAmount;
import models.DataModel;
import models.DesignModel;

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
            //Todo: report sending
            //sendReport();
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
    public void changePassword(){
        String oldPassword, newPassword;
        System.out.println();
        DesignModel.printLine();
        System.out.println("\t\tPassword Change");
        System.out.print("\t\t\tEnter your Current Password : ");
        oldPassword = scanner.next();
        if(this.getPassword().equals(oldPassword)){
            System.out.print("\t\t\tEnter your New Password : ");
            //newPassword = String.valueOf(console.readPassword());;
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
    public void addMembersToTheCompany(){
        String name, password, email, type;

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
            System.out.print("Enter a Valid Project name : ");
        }

        System.out.println("\t\t\tEnter Type : ");
        int i=0;
        for(String types : DataModel.getTypeOfUser().keySet().stream().toList()){
            i++;
            System.out.println("\t\t"+i+". "+types);
        }
        i++;
        System.out.println("\t\t" + i + ". Add Custom Type");
        System.out.print("\n\t\tEnter s.no the User Type : ");

        int typeChoice = -1;
        while(true){
            while(typeChoice == -1){
                System.out.print("\t\t S.no: ");
                typeChoice = Validation.numberCheck(scanner);
            }

            if(typeChoice == -2){
                break;
            }
            else if(typeChoice<1 || typeChoice > DataModel.getTypeOfUser().keySet().stream().toList().size() + 1){
                System.out.println("\n\t\tS.no not found!");
                typeChoice = -1;
            }
            else{
                break;
            }
        }

        Members member = null;

        if(typeChoice == i){
            System.out.print("\n\t\tEnter Type name : ");
            while ((type = scanner.nextLine()).isEmpty()) {
                System.out.print("Enter a Valid Project name : ");
            }

            i=0;
            for(String config : DataModel.getConfigurations()){
                i++;
                System.out.println("\t\t"+i+". "+config);
            }
            System.out.println("\n\t\tSelect the Permissions that you allow for the users of type " + type + "\n");
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

            DataModel.getTypeOfUser().put(type, configurations);
            member = new Members(name, email, password, type);
        }
        else if(typeChoice == -2){
            member = new Members(name, email, password, "Member");

        }
        else{
            member = new Members(name, email, password, DataModel.getTypeOfUser().keySet().stream().toList().get(typeChoice-1));
        }


        DataModel.getMembersArrayList().add(member);
        System.out.println("\t\tUser added to the Organisation");
        DesignModel.printLine();
    }
    public void createProjects(){

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

            Project project = new Project(projectName, description, deadline, memberArrayList);
            System.out.println("\n\t\tProject created!");
            for(Members m : memberArrayList){
                m.getProjectArrayList().add(project);
            }
            projectArrayList.add(project);
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
                    Project project = new Project(projectName, description, deadline, memberArrayList);

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


        Project project = new Project(projectName, description, deadline, memberArrayList);

        System.out.println("\n\t\tProject created!");
        for(Members m : memberArrayList){
            m.getProjectArrayList().add(project);
        }
        projectArrayList.add(project);
        DesignModel.printLine();
    }
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

        System.out.println();
        DesignModel.printLine();


    }

    public void editProjectDetails(){
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
                    }
                    else{
                        System.out.print("\t\tEnter the custom Status : ");
                        String chat;
                        //scanner.nextLine();
                        chat = scanner.nextLine();
                        System.out.print("");

                        DataModel.getModelProjectStatus().add(chat);
                        selectedProject.setStatus(chat);
                    }

                }

                case 5 -> addMembersToProject(selectedProject);

                default -> System.out.println("\n\tWrong value. Give correct input number!\n");
            }
        }

    }
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

    public void workOfMember(){
        System.out.println("\n\t\tWelcome : " + this.getName().toUpperCase());

        while(true) {
            //this.viewDashboard();
            for(Project project : projectArrayList){
                System.out.println(project.getProjectName());
            }
            System.out.println("\n\t\tWhat would you like to do :");

            System.out.println("\n\t\t\t Enter 0 to Change Password");
            System.out.println("\t\t\t Enter 1 to Add a User to your Organisation");
            System.out.println("\t\t\t Enter 2 to Create a new Project");
            System.out.println("\t\t\t Enter 3 to View/Update Details of Projects");
            //System.out.println("\t\t\t Enter 4 to Add Tasks");
            //System.out.println("\t\t\t Enter 5 to View/Update Details of Task");
            //System.out.println("\t\t\t Enter 6 to Create a Milestone");
           // System.out.println("\t\t\t Enter 7 to View/Update a Milestone");
            //System.out.println("\t\t\t Enter 8 to Create Own Tasks");
            //System.out.println("\t\t\t Enter 9 to View/Update Own Tasks");
            //System.out.println("\t\t\t Enter 10 for DiscussionBox");
           // System.out.println("\t\t\t Enter 11 to Add Files");
            //System.out.println("\t\t\t Enter 1 to View/Update AssignedTask Status");
            //System.out.println("\t\t\t Enter 2 to View/Update AssignedIssue Status");
            //System.out.println("\t\t\t Enter 3 to Create Own Tasks");
            //System.out.println("\t\t\t Enter 4 to View/Update Own Tasks");
            //System.out.println("\t\t\t Enter 5 for DiscussionBox");
            //System.out.println("\t\t\t Enter 6 to Add files to Project");
            System.out.println("\t\t\t Enter -1 to Logout\n");

            int adminChoice = -1;
            while (adminChoice == -1) {
                System.out.print("\t\t\t Enter your Choice : ");
                adminChoice = Validation.numberCheck(scanner);
            }


            switch (adminChoice) {
                case -2 -> this.exitVerification();
                case 0 -> this.changePassword();
                case 1 -> {
                    if(DataModel.getTypeOfUser().get(this.type).contains("Add Users to Organisation")){
                        this.addMembersToTheCompany();
                    }
                    else{
                        System.out.println("\n\t\t\tSorry! You don't have the access to add a User to the Organisation");
                        System.out.println();
                        DesignModel.printLine();
                    }
                }
                case 2 -> {
                    if(DataModel.getTypeOfUser().get(this.type).contains("Create Projects")){
                        this.createProjects();
                    }
                    else{
                        System.out.println("\n\t\t\tSorry! You don't have the access to Create Projects");
                        System.out.println();
                        DesignModel.printLine();
                    }
                }
                case 3 -> {
                    if(DataModel.getTypeOfUser().get(this.type).contains("Update Projects")){
                        this.viewProjects();
                    }
                    else{
                        System.out.println("\n\t\t\tSorry! You don't have the access to Update Projects");
                        System.out.println();
                        DesignModel.printLine();
                    }
                }
                default -> System.out.println("\n\tWrong value. Give correct input number!\n");

            }
        }
    }
}
