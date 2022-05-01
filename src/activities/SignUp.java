package activities;

import models.DataModel;
import models.DesignModel;
import objects.Members;

import java.util.Scanner;

public class SignUp {

    public static Scanner scanner = new Scanner(System.in);
    /*
    -> Registration method
    -> Only manager role can register and other roles cannot register directly
     */
    public static void signupMethod() {
        Members manager = new Members();


        String email, password, name, organisation;
        System.out.println();
        DesignModel.printLine();

        System.out.println("\tManager Registration");
        System.out.println();


        System.out.print("\t\tEnter your Name : ");
        name = scanner.next();

        email = Validation.emailValidation();

        System.out.print("\t\tEnter Password : ");
        password = scanner.next();

        System.out.println("\t\tYou have successfully created your Manager Account\n");

        manager.setName(name);
        manager.setEmail(email);
        manager.setPassword(password);
        manager.setType("Manager");

        DataModel.getMembersArrayList().add(manager);

        manager.workOfMember();
    }
}
