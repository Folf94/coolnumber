import java.util.HashMap;
import java.util.Map;

public class Parse {
    private double income = 0.0;
    private double expenses = 0.0;
    private Map<String, Double> movementList = new HashMap<>();

    public void Calculate(String input) {
        String[] columns = input.split(",", 8);
        double incomeColumn = Double.parseDouble(columns[6]);
        double expenseColumn = Double.parseDouble(columns[7].replaceAll("\"", "").replace(',', '.'));
        if (incomeColumn == 0) {
            calculateExpenses(columns[5], expenseColumn);
        }
        else {
            this.income += incomeColumn;
        }
    }

    private void calculateExpenses(String expenseName, double expense) {
        this.expenses += expense;
        String[] name = expenseName.trim().split(" {3,}");
        String[] column = name[1].split("/");
        String[] suppliesColumn = column[column.length - 1].split("\\\\");
        String values = suppliesColumn[suppliesColumn.length - 1];
        if (!movementList.containsKey(values))
            movementList.put(values, expense);
        else {
            double sum = movementList.get(values);
            sum += expense;
            movementList.put(values.trim(), sum);
        }
    }

    public void printSum() {

        System.out.printf("Сумма расходов: %.2f руб", expenses);
        System.out.println();
        System.out.println("--------------------------------------------------");
        System.out.printf("Сумма доходов: %.2f руб", income);
        System.out.println();
        System.out.println("--------------------------------------------------");
        for (String values : movementList.keySet()) {
            System.out.printf("%-10s  %.2f %-10s\n", values, movementList.get(values), "руб");
        }

    }
}
