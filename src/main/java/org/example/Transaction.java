import java.util.Date;

public class Transaction {
    private Date operationDate;
    private String type;
    private double amount;
    private String description;

    public Transaction(String type, double amount, String description) {
        this.operationDate = new Date();
        this.type = type;
        this.amount = amount;
        this.description = description;
    }

    public Date getDate() {
        return operationDate;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        String dateStr = String.format("%td.%tm.%tY", operationDate, operationDate, operationDate);
        String timeStr = String.format("%tH:%tM", operationDate, operationDate);

        return String.format("%s %s | %-10s | %8.2f | %s",
                dateStr, timeStr, type, amount, description);
    }
}