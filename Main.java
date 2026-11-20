
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
        BudgetManager budgetManager = new BudgetManager();

        String choice;

        do {
            System.out.println();
            System.out.println("===== SmartSpend Main Menu =====");
            System.out.println("1. Add expense");
            System.out.println("2. Display all expenses");
            System.out.println("3. Search by category");
            System.out.println("4. Search by month");
            System.out.println("5. Set Monthly Budget");
            System.out.println("6. Set Category Budget");
            System.out.println("7. Display Budgets");
            System.out.println("8. Calculate Remaining Budget");
            System.out.println("9. Check Budget Limits");
            System.out.println("10. View Monthly Expense Total");
            System.out.println("11. View Monthly Expense Summary");
            System.out.println("12. Delete expense");
            System.out.println("13. Exit");
            System.out.println("Enter your choice:");

            choice = scanner.nextLine().trim();

            switch (choice) {

                case "1":
                    expenseManager.addExpense(scanner);
                    break;

                case "2":
                    expenseManager.displayAllExpenses();
                    break;

                case "3":
                    System.out.println("Enter category to search:");
                    expenseManager.searchByCategory(scanner.nextLine());
                    break;

                case "4":
                    System.out.println("Enter month to search (YYYY-MM):");
                    expenseManager.searchByMonth(scanner.nextLine());
                    break;

                case "5":
                    budgetManager.setMonthlyBudget(scanner);
                    break;

                case "6":
                    budgetManager.setCategoryBudget(scanner);
                    break;

                case "7":
                    budgetManager.displayBudgets();
                    break;

                case "8":
                    System.out.println("Enter month (YYYY-MM):");
                    String remainingMonth = scanner.nextLine().trim();

                    if (!isValidMonth(remainingMonth)) {
                        System.out.println("Invalid month. Use YYYY-MM.");
                        break;
                    }

                    budgetManager.calculateRemainingBudget(
                        remainingMonth, expenseManager
                    );
                    break;

                case "9":
                    System.out.println("Enter month (YYYY-MM):");
                    String limitMonth = scanner.nextLine().trim();

                    if (!isValidMonth(limitMonth)) {
                        System.out.println("Invalid month. Use YYYY-MM.");
                        break;
                    }

                    budgetManager.checkBudgetLimit(
                        limitMonth, expenseManager
                    );
                    break;

                case "10":
                    System.out.println("Enter month (YYYY-MM):");
                    String totalMonth = scanner.nextLine().trim();

                    if (!isValidMonth(totalMonth)) {
                        System.out.println("Invalid month. Use YYYY-MM.");
                        break;
                    }

                    double total = expenseManager.calculateMonthlyExpenses(totalMonth);
                    System.out.println("Total Expenses: Rs. " + total);
                    break;

                case "11":
                    System.out.println("Enter month (YYYY-MM):");
                    String summaryMonth = scanner.nextLine().trim();

                    if (!isValidMonth(summaryMonth)) {
                        System.out.println("Invalid month. Use YYYY-MM.");
                        break;
                    }

                    expenseManager.displayMonthlyExpenseSummary(summaryMonth);
                    break;

                case "12":
                    expenseManager.deleteExpense(scanner);
                    break;

                case "13":
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 13.");
            }

        } while (!choice.equals("13"));
    }

    // Validate month in YYYY-MM format
    private static boolean isValidMonth(String month) {
        return month.matches("[0-9]{4}-(0[1-9]|1[0-2])")
                && !month.startsWith("0000");
    }
}
