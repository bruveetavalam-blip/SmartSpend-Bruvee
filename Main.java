import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("==========================");
        System.out.println("Welcome to SmartSpend");
        System.out.println("Expense Tracker and Budget Planner");

        Scanner scanner = new Scanner(System.in);
        try {
            runMenu(scanner);
        } catch (NoSuchElementException exception) {
            System.out.println("Input ended. Goodbye!");
        } finally {
            scanner.close();
        }
    }

    private static void runMenu(Scanner scanner) {
        ExpenseManager expenseManager = new ExpenseManager();
        String choice;

        do {
            System.out.println("1. Add expense");
            System.out.println("2. Exit");
            System.out.println("3. Display all expenses");
            System.out.println("4. Search by category");
            System.out.println("5. Search by month");
            System.out.println("Enter your choice:");
            choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    expenseManager.addExpense(scanner);
                    break;
                case "2":
                    System.out.println("Goodbye!");
                    break;
                case "3":
                    expenseManager.displayAllExpenses();
                    break;
                case "4":
                    System.out.println("Enter category to search:");
                    expenseManager.searchByCategory(scanner.nextLine());
                    break;
                case "5":
                    System.out.println("Enter month to search (YYYY-MM):");
                    expenseManager.searchByMonth(scanner.nextLine());
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, 3, 4, or 5.");
            }
        } while (!choice.equals("2"));

    }
}
