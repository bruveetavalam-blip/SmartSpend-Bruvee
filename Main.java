import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("==========================");
        System.out.println("Welcome to SmartSpend");
        System.out.println("Expense Tracker and Budget Planner");

        Scanner scanner = new Scanner(System.in);
        ExpenseManager expenseManager = new ExpenseManager();
        String choice;

        do {
            System.out.println("1. Add expense");
            System.out.println("2. Exit");
            System.out.println("3. Display all expenses");
            System.out.println("4. Search by category");
            System.out.println("Enter your choice:");
            choice = scanner.nextLine();

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
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, 3, or 4.");
            }
        } while (!choice.equals("2"));

        scanner.close();
    }
}
