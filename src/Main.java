import activities.WelcomePage;
import models.DataModel;
import models.DesignModel;

public class Main {

    public static void main(String[] args){

        DataModel.setMembersArrayList();
        DataModel.setIssueSeverity();
        DataModel.setIssueClassification();
        DataModel.setTaskStatus();
        DataModel.setPriority();
        DataModel.setIssueStatus();
        DataModel.setModelProjectStatus();
        DataModel.setRecurringTaskType();
        DataModel.setConfigurations();
        DataModel.setTypeOfUser();

        DesignModel.printTitle();
        WelcomePage.loginDisplay();

    }
}
