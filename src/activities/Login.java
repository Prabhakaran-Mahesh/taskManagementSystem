package activities;

import models.DataModel;
import models.DesignModel;
import objects.Members;

import java.util.Scanner;

public class Login {
    static Scanner scanner = new Scanner(System.in);

    /*
    -> login verification method is used to verify the login credentials of the user.
    -> this method is used to check the credentials, ie. name, password
    -> the method takes in email and password and search all the arraylist accordingly to find the user type.
     */
    private static boolean loginVerification(String email, String password){
        for (Members teamMember : DataModel.getMembersArrayList()) {
            if (teamMember.getPassword().equals(password) && teamMember.getEmail().equals(email)) {
                teamMember.workOfMember();
                return true;
            }
            else if(teamMember.getEmail().equals(email)){
                System.out.println("\n\t\tIncorrect Password");
                return false;
            }
        }

        System.out.println("\n\t\tCheck your Credentials");
        return false;

    }

    /*
    -> this is the main login method.
    -> gets input and checks input and process are done
     */
    public static void loginMethod() {
        boolean verification = false; // for calling loginVerification function
        int limit = 3; // if the user hits wrong credentials for 3 times. the application ends

        String email="", password;
        //String type;

        // loop stops when correct credentials are given or when attempts exceed 3 times.
        while(!verification){

            limit--;
            if(limit == -1){
                System.out.println("\n\t\tYou have attempted 3 times with wrong credentials!\n");
                DesignModel.printLine();
                return;
            }
            else if(limit<2){
                System.out.println("\t\tYou have " + (limit+1) + " more attempts left\n");
            }

            System.out.println();
            DesignModel.printLine();
            System.out.println();

            System.out.print("\t\tEnter Email ID : ");
            email = scanner.next();

            System.out.print("\t\tEnter Password : ");
            //password = String.valueOf(console.readPassword());
            password = scanner.next();

            verification = loginVerification(email, password);

        }
    }
}
