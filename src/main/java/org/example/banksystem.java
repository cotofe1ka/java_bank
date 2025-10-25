package org.example;

import java.util.Scanner;


// Главный класс программы со всеми плюшками
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
        this.transactions = new ArrayList<>();//

        // Добавляем первую транзакцию - открытие счета
        if (initialDeposit > 0) {
            transactions.add(new Transaction("DEPOSIT", initialDeposit, "Initial deposit"));
        }
    }
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