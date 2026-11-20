import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ExpenseManager {
    private static final String[] CATEGORIES = {"Food", "Travel", "Shopping", "Education", "Others"};
    private Expense[] expenses = new Expense[100];
    private int expenseCount = 0;

    public void addExpense(Scanner scanner) {
        if (expenseCount == expenses.length) {
            System.out.println("Expense list is full.");
            return;
        }

        System.out.println("Enter amount:");
        double amount;
        try {
            amount = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException exception) {
            System.out.println("Enter a numeric amount, such as 125.50.");
            return;
        }
        if (!Double.isFinite(amount) || amount <= 0) {
            System.out.println("Amount must be a positive, finite number.");
            return;
        }

        System.out.println("Enter category (Food, Travel, Shopping, Education, or Others):");
        String category = findCategory(scanner.nextLine());
        if (category == null) {
            System.out.println("Choose Food, Travel, Shopping, Education, or Others.");
            return;
        }

        System.out.println("Enter date (YYYY-MM-DD):");
        String date = scanner.nextLine().trim();
        if (!isValidDate(date)) {
            System.out.println("Enter a valid date in YYYY-MM-DD format.");
            return;
        }

        System.out.println("Enter description:");
        String description = scanner.nextLine().trim();
        if (description.isEmpty()) {
            System.out.println("Description cannot be blank.");
            return;
        }

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

    public void searchByCategory(String category) {
        String matchingCategory = findCategory(category);
        if (matchingCategory == null) {
            System.out.println("Choose Food, Travel, Shopping, Education, or Others.");
            return;
        }
        boolean found = false;

        for (int i = 0; i < expenseCount; i++) {
            Expense expense = expenses[i];
            if (expense.getCategory().equals(matchingCategory)) {
                printExpense(expense);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No expenses found in that category.");
        }
    }

    public void searchByMonth(String month) {
        month = month.trim();
        if (!isValidDate(month + "-01")) {
            System.out.println("Enter a valid month in YYYY-MM format.");
            return;
        }
        boolean found = false;
        String datePrefix = month + "-";

        for (int i = 0; i < expenseCount; i++) {
            Expense expense = expenses[i];
            if (expense.getDate().startsWith(datePrefix)) {
                printExpense(expense);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No expenses found in that month.");
        }
    }

    private String findCategory(String input) {
        String trimmedInput = input.trim();
        for (String category : CATEGORIES) {
            if (category.equalsIgnoreCase(trimmedInput)) {
                return category;
            }
        }
        return null;
    }

    private boolean isValidDate(String date) {
        if (!date.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}") || date.startsWith("0000")) {
            return false;
        }
        try {
            LocalDate.parse(date);
            return true;
        } catch (DateTimeParseException exception) {
            return false;
        }
    }

    private void printExpense(Expense expense) {
        System.out.println("Amount: " + expense.getAmount());
        System.out.println("Category: " + expense.getCategory());
        System.out.println("Date: " + expense.getDate());
        System.out.println("Description: " + expense.getDescription());
        System.out.println();
    }
    public double calculateMonthlyExpenses(String month) {
    double total = 0;

    for (int i = 0; i < expenseCount; i++) {
        Expense expense = expenses[i];

        if (expense.getDate().startsWith(month + "-")) {
            total = total + expense.getAmount();
        }
    }

    return total;
}
}
