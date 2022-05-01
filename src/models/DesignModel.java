package models;

public class DesignModel {
    public static void printLine(){
        for(int i=0; i<150; i++){
            System.out.print("*");
        }
        System.out.println();
    }

    // This functions prints out the title design of the application.
    // This function is called only once ie; called at the top of the main function and not inside the loop
    public static void printTitle(){
        System.out.println();

        printLine();
        System.out.printf("*%148s*\n", " ");
        System.out.printf("*%85s%63s*\n", "Task Management System", " ");
        System.out.printf("*%148s*\n", " ");
        printLine();
    }
}
