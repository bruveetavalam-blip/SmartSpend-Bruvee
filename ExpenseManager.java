import java.util.Scanner;

public class ExpenseManager {
    public void addExpense(Scanner scanner) {
        System.out.println("Enter amount:");
        double amount = Double.parseDouble(scanner.nextLine());

        System.out.println("Enter category (Food, Travel, Shopping, Education, or Others):");
        String category = scanner.nextLine();

        System.out.println("Enter date (YYYY-MM-DD):");
        String date = scanner.nextLine();

        System.out.println("Enter description:");
        String description = scanner.nextLine();

        Expense expense = new Expense(amount, category, date, description);
        System.out.println("Expense entered: " + expense.getDescription());
    }
}
