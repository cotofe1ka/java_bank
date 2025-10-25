package org.example;

import java.util.Scanner;


// Главный класс программы со всеми плюшками
import java.util.*;

// Главный класс банковского счета
class BankAccount {
    private String accountNumber;    // Номер счета
    private String ownerName;        // Владелец счета
    private double balance;          // Текущий баланс
    private boolean isActive;        // Активен ли счет
    private List<Transaction> transactions; // Список всех транзакций

    // Конструктор для создания нового счета
    public BankAccount(String accountNumber, String ownerName, double initialDeposit) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = initialDeposit;
        this.isActive = true;
        this.transactions = new ArrayList<>();

        // Добавляем первую транзакцию - открытие счета
        if (initialDeposit > 0) {
            transactions.add(new Transaction("DEPOSIT", initialDeposit, "Initial deposit"));
        }
    }

    // Метод для пополнения счета
    public boolean deposit(double amount, String description) {
        if (amount <= 0) {
            System.out.println("mistake: summ should be positive");//Ошибка: Сумма должна быть положительной!
            return false;
        }
        if (!isActive) {
            System.out.println("mistake: credit is not active");//Ошибка: Счет не активен!
            return false;
        }

        balance += amount;
        transactions.add(new Transaction("DEPOSIT", amount, description));
        System.out.printf("successfuly top up: %.2f. new ballance: %.2f\n", amount, balance);//Успешно пополнено: %.2f. Новый баланс: %.2f
        return true;
    }

    // Метод для снятия денег
    public boolean withdraw(double amount, String description) {
        if (amount <= 0) {
            System.out.println("mistake: summ should be positive!");//Ошибка: Сумма должна быть положительной!
            return false;
        }
        if (!isActive) {
            System.out.println("mistake: credit is not active");//Ошибка: Счет не активен!
            return false;
        }
        if (amount > balance) {
            System.out.println("mistake: no money");//Ошибка: Недостаточно средств!
            return false;
        }

        balance -= amount;
        transactions.add(new Transaction("WITHDRAWAL", amount, description));
        System.out.printf("sucsessfuly take: %.2f. new ballance %.2f\n", amount, balance);//Успешно снято: %.2f. Новый баланс: %.2f
        return true;
    }

    // Метод для показа баланса
    public void showBalance() {
        System.out.printf("balance of credit %s: %.2f\n", accountNumber, balance);//Баланс счета
    }

    // Метод для вывода всех транзакций
    public void showTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("no alone tranzactione");//Транзакций нет.
            return;
        }

        System.out.println("...history of tranzaction...\n");//=== ИСТОРИЯ ТРАНЗАКЦИЙ ===
        for (int i = 0; i < transactions.size(); i++) {
            System.out.println((i + 1) + ". " + transactions.get(i));
        }
        System.out.println("_____________________\n");
    }

    // Метод для поиска транзакций по атрибутам
    public void searchTransactions(String searchType, String keyword, Double minAmount, Double maxAmount) {
        List<Transaction> results = new ArrayList<>();

        for (Transaction transaction : transactions) {
            boolean matches = true;

            // Поиск по типу транзакции
            if (searchType != null && !searchType.isEmpty()) {
                if (!transaction.getType().equalsIgnoreCase(searchType)) {
                    matches = false;
                }
            }

            // Поиск по ключевому слову в описании
            if (keyword != null && !keyword.isEmpty()) {
                if (!transaction.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                    matches = false;
                }
            }if (minAmount != null) {
                if (transaction.getAmount() < minAmount) {
                    matches = false;
                }
            }

            // Поиск по максимальной сумме
            if (maxAmount != null) {
                if (transaction.getAmount() > maxAmount) {
                    matches = false;
                }
            }

            if (matches) {
                results.add(transaction);
            }
        }

        // Вывод результатов поиска
        if (results.isEmpty()) {
            System.out.println("mo look for tranzaction");//Транзакции не найдены.
        } else {
            System.out.println("\nresults of searching");//=== РЕЗУЛЬТАТЫ ПОИСКА ==="
            for (int i = 0; i < results.size(); i++) {
                System.out.println((i + 1) + ". " + results.get(i));
            }
            System.out.println("____________________\n");
        }
    }

    // Геттеры для получения информации о счете
    public String getAccountNumber() { return accountNumber; }
    public String getOwnerName() { return ownerName; }
    public double getBalance() { return balance; }
    public boolean isActive() { return isActive; }
}