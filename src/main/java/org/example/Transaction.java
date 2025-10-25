package org.example;

import java.util.Date;

// история операций
// Класс для хранения информации о каждой операции
class Transaction {
    private Date date;
    private String type;
    private double amount;
    private String description;

    //
    public Transaction(String type, double amount, String description) {
        this.date = new Date();
        this.type = type;
        this.amount = amount;
        this.description = description;
    }

    // геттеры
    public Date getDate() { return date; }
    public String getType() { return type; }
    public double getAmount() { return amount; }
    public String getDescription() { return description; }


    @Override
    public String toString() {
        return String.format("%tF %tT | %-10s | %8.2f | %s",
                date, date, type, amount, description);
    }
}