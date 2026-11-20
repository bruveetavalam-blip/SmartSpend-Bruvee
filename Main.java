import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("==========================");
        System.out.println("Welcome to SmartSpend");
        System.out.println("Expense Tracker and Budget Planner");

        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Add expense");
        System.out.println("2. Exit");
        System.out.println("Enter your choice:");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                System.out.println("You selected Add expense.");
                break;
            case "2":
                System.out.println("Goodbye!");
                break;
            default:
                System.out.println("Invalid choice. Please enter 1 or 2.");
        }

        scanner.close();
    }
}
