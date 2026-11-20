import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Locale;
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

    public void deleteExpense(Scanner scanner) {
        if (expenseCount == 0) {
            System.out.println("No expenses to delete.");
            return;
        }

        System.out.println("===== Expenses =====");
        for (int i = 0; i < expenseCount; i++) {
            System.out.println("Expense " + (i + 1) + ":");
            printExpense(expenses[i]);
        }

        System.out.println("Enter the expense number or exact description to delete:");
        String selection = scanner.nextLine().trim();
        int selectedIndex;

        try {
            selectedIndex = Integer.parseInt(selection) - 1;
            if (selectedIndex < 0 || selectedIndex >= expenseCount) {
                System.out.println("Invalid expense number.");
                return;
            }
        } catch (NumberFormatException exception) {
            int[] matchingIndexes = new int[expenseCount];
            int matchCount = 0;

            for (int i = 0; i < expenseCount; i++) {
                if (expenses[i].getDescription().equalsIgnoreCase(selection)) {
                    matchingIndexes[matchCount] = i;
                    matchCount++;
                }
            }

            if (matchCount == 0) {
                System.out.println("No expense found with that description.");
                return;
            }

            if (matchCount == 1) {
                selectedIndex = matchingIndexes[0];
            } else {
                System.out.println("Multiple expenses have that description:");
                for (int i = 0; i < matchCount; i++) {
                    int index = matchingIndexes[i];
                    System.out.println("Expense " + (index + 1) + ":");
                    printExpense(expenses[index]);
                }

                System.out.println("Enter the expense number to delete:");
                String matchingSelection = scanner.nextLine().trim();
                try {
                    selectedIndex = Integer.parseInt(matchingSelection) - 1;
                } catch (NumberFormatException invalidSelection) {
                    System.out.println("Invalid expense number.");
                    return;
                }

                boolean isMatchingExpense = false;
                for (int i = 0; i < matchCount; i++) {
                    if (matchingIndexes[i] == selectedIndex) {
                        isMatchingExpense = true;
                        break;
                    }
                }
                if (!isMatchingExpense) {
                    System.out.println("Invalid expense number.");
                    return;
                }
            }
        }

        System.out.println("Expense selected for deletion:");
        printExpense(expenses[selectedIndex]);
        System.out.println("Are you sure you want to delete this expense? (y/n):");
        String confirmation = scanner.nextLine().trim();
        if (!confirmation.equalsIgnoreCase("y") && !confirmation.equalsIgnoreCase("yes")) {
            System.out.println("Expense deletion cancelled.");
            return;
        }

        for (int i = selectedIndex; i < expenseCount - 1; i++) {
            expenses[i] = expenses[i + 1];
        }
        expenses[expenseCount - 1] = null;
        expenseCount--;
        System.out.println("Expense deleted successfully.");
    }

    public void editExpense(Scanner scanner) {
        if (expenseCount == 0) {
            System.out.println("No expenses to edit.");
            return;
        }

        System.out.println("===== Expenses =====");
        for (int i = 0; i < expenseCount; i++) {
            System.out.println("Expense " + (i + 1) + ":");
            printExpense(expenses[i]);
        }

        System.out.println("Enter the expense number or exact description to edit:");
        String selection = scanner.nextLine().trim();
        int selectedIndex;

        try {
            selectedIndex = Integer.parseInt(selection) - 1;
            if (selectedIndex < 0 || selectedIndex >= expenseCount) {
                System.out.println("Invalid expense number.");
                return;
            }
        } catch (NumberFormatException exception) {
            int[] matchingIndexes = new int[expenseCount];
            int matchCount = 0;

            for (int i = 0; i < expenseCount; i++) {
                if (expenses[i].getDescription().equalsIgnoreCase(selection)) {
                    matchingIndexes[matchCount] = i;
                    matchCount++;
                }
            }

            if (matchCount == 0) {
                System.out.println("No expense found with that description.");
                return;
            }

            if (matchCount == 1) {
                selectedIndex = matchingIndexes[0];
            } else {
                System.out.println("Multiple expenses have that description:");
                for (int i = 0; i < matchCount; i++) {
                    int index = matchingIndexes[i];
                    System.out.println("Expense " + (index + 1) + ":");
                    printExpense(expenses[index]);
                }

                System.out.println("Enter the expense number to edit:");
                String matchingSelection = scanner.nextLine().trim();
                try {
                    selectedIndex = Integer.parseInt(matchingSelection) - 1;
                } catch (NumberFormatException invalidSelection) {
                    System.out.println("Invalid expense number.");
                    return;
                }

                boolean isMatchingExpense = false;
                for (int i = 0; i < matchCount; i++) {
                    if (matchingIndexes[i] == selectedIndex) {
                        isMatchingExpense = true;
                        break;
                    }
                }
                if (!isMatchingExpense) {
                    System.out.println("Invalid expense number.");
                    return;
                }
            }
        }

        Expense expense = expenses[selectedIndex];
        System.out.println("Current expense details:");
        printExpense(expense);
        System.out.println("Enter new amount, or press Enter to keep " + expense.getAmount() + ":");
        String amountInput = scanner.nextLine().trim();
        if (amountInput.equalsIgnoreCase("cancel")) {
            System.out.println("Expense editing cancelled.");
            return;
        }

        double amount = expense.getAmount();
        if (!amountInput.isEmpty()) {
            try {
                amount = Double.parseDouble(amountInput);
            } catch (NumberFormatException exception) {
                System.out.println("Enter a numeric amount, such as 125.50. Expense unchanged.");
                return;
            }
            if (!Double.isFinite(amount) || amount <= 0) {
                System.out.println("Amount must be a positive, finite number. Expense unchanged.");
                return;
            }
        }

        System.out.println("Enter new category, or press Enter to keep " + expense.getCategory() + ":");
        String categoryInput = scanner.nextLine().trim();
        if (categoryInput.equalsIgnoreCase("cancel")) {
            System.out.println("Expense editing cancelled.");
            return;
        }
        String category = expense.getCategory();
        if (!categoryInput.isEmpty()) {
            category = findCategory(categoryInput);
            if (category == null) {
                System.out.println("Choose Food, Travel, Shopping, Education, or Others. Expense unchanged.");
                return;
            }
        }

        System.out.println("Enter new date (YYYY-MM-DD), or press Enter to keep " + expense.getDate() + ":");
        String dateInput = scanner.nextLine().trim();
        if (dateInput.equalsIgnoreCase("cancel")) {
            System.out.println("Expense editing cancelled.");
            return;
        }
        String date = expense.getDate();
        if (!dateInput.isEmpty()) {
            if (!isValidDate(dateInput)) {
                System.out.println("Enter a valid date in YYYY-MM-DD format. Expense unchanged.");
                return;
            }
            date = dateInput;
        }

        expense.setAmount(amount);
        expense.setCategory(category);
        expense.setDate(date);
        System.out.println("Expense updated successfully:");
        printExpense(expense);
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

    public void searchByAmount(Scanner scanner) {
        System.out.println("Enter expense amount:");
        double amount;
        try {
            amount = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException exception) {
            System.out.println("Enter a numeric amount, such as 125.50.");
            return;
        }

        if (!Double.isFinite(amount) || amount <= 0) {
            System.out.println("Amount must be a positive, finite number.");
            return;
        }

        boolean found = false;
        for (int i = 0; i < expenseCount; i++) {
            if (Double.compare(expenses[i].getAmount(), amount) == 0) {
                printExpense(expenses[i]);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No expenses found with that amount.");
        }
    }

    public void searchByDescription(Scanner scanner) {
        System.out.println("Enter a word or phrase to search in expense descriptions:");
        String keyword = scanner.nextLine().trim();
        if (keyword.isEmpty()) {
            System.out.println("Search keyword cannot be empty.");
            return;
        }

        String searchText = keyword.toLowerCase(Locale.ROOT);
        boolean found = false;
        for (int i = 0; i < expenseCount; i++) {
            if (expenses[i].getDescription().toLowerCase(Locale.ROOT).contains(searchText)) {
                printExpense(expenses[i]);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No expenses found with that description keyword.");
        }
    }

    public void displayExpensesSortedByAmount(Scanner scanner) {
        if (expenseCount == 0) {
            System.out.println("No expenses to sort.");
            return;
        }

        System.out.println("Choose sort order:");
        System.out.println("1. Ascending (lowest to highest)");
        System.out.println("2. Descending (highest to lowest)");
        String order = scanner.nextLine().trim();
        if (!order.equals("1") && !order.equals("2")) {
            System.out.println("Invalid choice. Enter 1 for ascending or 2 for descending.");
            return;
        }

        Expense[] sortedExpenses = new Expense[expenseCount];
        System.arraycopy(expenses, 0, sortedExpenses, 0, expenseCount);

        for (int i = 0; i < sortedExpenses.length - 1; i++) {
            for (int j = 0; j < sortedExpenses.length - i - 1; j++) {
                boolean outOfOrder;
                if (order.equals("1")) {
                    outOfOrder = sortedExpenses[j].getAmount() > sortedExpenses[j + 1].getAmount();
                } else {
                    outOfOrder = sortedExpenses[j].getAmount() < sortedExpenses[j + 1].getAmount();
                }

                if (outOfOrder) {
                    Expense temp = sortedExpenses[j];
                    sortedExpenses[j] = sortedExpenses[j + 1];
                    sortedExpenses[j + 1] = temp;
                }
            }
        }

        System.out.println("===== Expenses Sorted by Amount =====");
        for (Expense expense : sortedExpenses) {
            printExpense(expense);
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

public void displayMonthlyExpenseSummary(String month) {
    double total = 0;
    int count = 0;
    Expense highestExpense = null;
    Expense lowestExpense = null;

    for (int i = 0; i < expenseCount; i++) {
        Expense expense = expenses[i];

        if (expense.getDate().startsWith(month + "-")) {
            total = total + expense.getAmount();
            count++;

            if (highestExpense == null || expense.getAmount() > highestExpense.getAmount()) {
                highestExpense = expense;
            }

            if (lowestExpense == null || expense.getAmount() < lowestExpense.getAmount()) {
                lowestExpense = expense;
            }
        }
    }

    System.out.println("===== Monthly Expense Summary =====");
    System.out.println("Month: " + month);
    System.out.println("Total Expenses: Rs. " + total);
    System.out.println("Number of Expenses: " + count);

    if (count == 0) {
        System.out.println("No expenses found in that month.");
        return;
    }

    System.out.println("Highest Expense: Rs. " + highestExpense.getAmount());
    System.out.println("Highest Expense Category: " + highestExpense.getCategory());
    System.out.println("Lowest Expense: Rs. " + lowestExpense.getAmount());
    System.out.println("Lowest Expense Category: " + lowestExpense.getCategory());
}

public double calculateCategoryExpenses(String month, String category) {
    double total = 0;

    for (int i = 0; i < expenseCount; i++) {
        Expense expense = expenses[i];

        if (expense.getDate().startsWith(month + "-")
                && expense.getCategory().equalsIgnoreCase(category)) {

            total = total + expense.getAmount();
        }
    }

    return total;
}
}
