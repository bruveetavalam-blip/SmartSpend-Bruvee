import java.util.Scanner;

public class BudgetManager {

    private Budget[] budgets = new Budget[100];
    private int budgetCount = 0;

    public void setMonthlyBudget(Scanner scanner) {
        if (budgetCount == budgets.length) {
            System.out.println("Budget list is full.");
            return;
        }

        System.out.println("Enter month (YYYY-MM):");
        String month = scanner.nextLine().trim();

        if (!month.matches("[0-9]{4}-(0[1-9]|1[0-2])") || month.startsWith("0000")) {
            System.out.println("Invalid month. Use YYYY-MM.");
            return;
        }

        System.out.println("Enter monthly budget amount:");
        double amount;

        try {
            amount = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException exception) {
            System.out.println("Please enter a numeric amount.");
            return;
        }

        if (!Double.isFinite(amount) || amount <= 0) {
            System.out.println("Budget amount must be positive.");
            return;
        }

        Budget budget = new Budget(month, "Overall", amount);
        budgets[budgetCount] = budget;
        budgetCount++;

        System.out.println("Monthly budget saved successfully!");
    }

    public void setCategoryBudget(Scanner scanner) {
        if (budgetCount == budgets.length) {
            System.out.println("Budget list is full.");
            return;
        }

        System.out.println("Enter month (YYYY-MM):");
        String month = scanner.nextLine().trim();

        if (!month.matches("[0-9]{4}-(0[1-9]|1[0-2])") || month.startsWith("0000")) {
            System.out.println("Invalid month. Use YYYY-MM.");
            return;
        }

        System.out.println("Enter category (Food, Travel, Shopping, Education, Others):");
        String input = scanner.nextLine().trim();

        String[] categories = {"Food", "Travel", "Shopping", "Education", "Others"};
        String category = null;

        for (String item : categories) {
            if (item.equalsIgnoreCase(input)) {
                category = item;
                break;
            }
        }

        if (category == null) {
            System.out.println("Invalid category.");
            return;
        }

        System.out.println("Enter category budget amount:");
        double amount;

        try {
            amount = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException exception) {
            System.out.println("Please enter a numeric amount.");
            return;
        }

        if (!Double.isFinite(amount) || amount <= 0) {
            System.out.println("Budget amount must be positive.");
            return;
        }

        Budget budget = new Budget(month, category, amount);
        budgets[budgetCount] = budget;
        budgetCount++;

        System.out.println("Category budget saved successfully!");
    }
    public void displayBudgets() {
    if (budgetCount == 0) {
        System.out.println("No budgets to display.");
        return;
    }

    System.out.println("===== Saved Budgets =====");

    for (int i = 0; i < budgetCount; i++) {
        Budget budget = budgets[i];

        System.out.println("Month: " + budget.getMonth());
        System.out.println("Category: " + budget.getCategory());
        System.out.println("Amount: Rs. " + budget.getAmount());
        System.out.println("--------------------");
    }
}
}