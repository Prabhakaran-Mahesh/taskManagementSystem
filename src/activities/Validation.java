package activities;


import models.DesignModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Scanner;

public class Validation {

    static Scanner scanner = new Scanner(System.in);

    //date format which should be given as input
    final static String DATE_FORMAT = "dd-MM-yyyy";
    // regex to validate email
    final static String REGEX = "^[a-z0-9]+@[a-z]+\\.[a-z]{2,3}$";

    /*
    -> number validation
    -> sometimes user may give input other than int where int is required
    -> this function prevents from input mismatch error
     */
    public static int numberCheck(Scanner scanner){
        int choice;

        if (scanner.hasNextInt()) {
            choice = scanner.nextInt();
            // Advances the scanner to prevent input errors
            scanner.nextLine();
            // Sets the condition too false to break the loop
            if(choice == -1){
                return -2;
            }
            return choice;
        } else {
            System.out.println("\n\t\tInvalid input.\n");
            // Advances the scanner to prevent input errors
            scanner.nextLine();
            DesignModel.printLine();
            System.out.println("\n");
            return -1;
        }

    }

    /*
    -> email validation
    -> email verification is used to check whether correct format of email is passed as inputc
     */
    public static String emailValidation() {
        String email;

        while(true){
            System.out.print("\t\tEnter Email ID : ");
            email = scanner.next();

            if(email.toLowerCase().matches(REGEX)){
                return email;
            }
            else{
                System.out.println("\t\tmail id not correct!\n");
            }
        }
    }

    // this function converts the Date to a LocalDate
    public static LocalDate dateToLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    /*
    -> the dateValidation function validates the input date.
    -> it checks for 2 conditions
        i. if the given date exits
        ii. it is a future date, i.e. the date should be correct and should not be a passed date.
     */
    public static Boolean dateValidation(String date){
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            LocalDate localDate1 = LocalDate.now();
            df.parse(date);
            boolean result = localDate1.isBefore(dateToLocalDate(df.parse(date)));
            if(result){
                return true;
            }
            else{
                System.out.println("\t\tThe mentioned Date is already passed!Try again");
                return false;
            }
        } catch (ParseException e) {
            System.out.println("\t\tWrong date or Wrong format!\n");
            return false;
        }
    }

    public static Boolean deadlineDateValidation(String projectDeadline, String date){
        if(dateValidation(date)){
            try {
                DateFormat df = new SimpleDateFormat(DATE_FORMAT);
                df.setLenient(false);
                LocalDate localDate1 = LocalDate.now();
                df.parse(projectDeadline);
            } catch (ParseException e) {
                //System.out.println("\t\tWrong date or Wrong format!\n");
                return true;
            }

            try {
                DateFormat df = new SimpleDateFormat(DATE_FORMAT);
                df.setLenient(false);
                df.parse(date);
                df.parse(projectDeadline);
                boolean result = dateToLocalDate(df.parse(date)).isBefore(dateToLocalDate(df.parse(projectDeadline)));
                if(result){
                    return true;
                }
                else{
                    System.out.println("\t\tThe mentioned Date should be before the Project deadline");
                    return false;
                }
            } catch (ParseException e) {
                System.out.println("\t\tWrong date or Wrong format!\n");
                return false;
            }
        }
        else{
            return false;
        }
    }

    /*
    -> if a message is about to send by any user this message validation function is invoked
    -> this validation returns true is if the message contains content
    -> else if the message is empty, false is returned
     */
    public static boolean messageValidation(String msg){
        if(msg.length() == 0){
            System.out.println("\n\t\tInput String cannot be Empty!");
            return false;
        }
        else{
            return true;
        }
    }
}


