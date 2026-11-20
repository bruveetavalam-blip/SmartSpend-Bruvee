import java.util.Scanner;

public class ExpenseManager {
    private Expense[] expenses = new Expense[100];
    private int expenseCount = 0;

    public void addExpense(Scanner scanner) {
        if (expenseCount == expenses.length) {
            System.out.println("Expense list is full.");
            return;
        }

        System.out.println("Enter amount:");
        double amount = Double.parseDouble(scanner.nextLine());

        System.out.println("Enter category (Food, Travel, Shopping, Education, or Others):");
        String category = scanner.nextLine();

        System.out.println("Enter date (YYYY-MM-DD):");
        String date = scanner.nextLine();

        System.out.println("Enter description:");
        String description = scanner.nextLine();

        Expense expense = new Expense(amount, category, date, description);
        expenses[expenseCount] = expense;
        expenseCount++;
        System.out.println("Expense stored: " + expense.getDescription());
    }

    public void displayAllExpenses() {
        if (expenseCount == 0) {
            System.out.println("No expenses to display.");
            return;
        }

        for (int i = 0; i < expenseCount; i++) {
            printExpense(expenses[i]);
        }
    }

    private void printExpense(Expense expense) {
        System.out.println("Amount: " + expense.getAmount());
        System.out.println("Category: " + expense.getCategory());
        System.out.println("Date: " + expense.getDate());
        System.out.println("Description: " + expense.getDescription());
        System.out.println();
    }
}
