package org.example;

import java.util.*;

// класс с банковский счетом
class BankAccount {
    private String accountNumber;    // Номер счета
    private String ownerName;        // Владелец счета
    private double balance;          // Текущий баланс
    //пополнение
    //снятие
//куда-то вывести
    //история
    private List<Transaction> transactions;

    // Конструктор для создания нового счета
    public BankAccount(String accountNumber, String ownerName, double initialDeposit) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = initialDeposit;
        this.isActive = true;
        this.transactions = new ArrayList<>();

        // первая транзакция- вместе с открытием счета
        if (initialDeposit > 0) {
            transactions.add(new Transaction("DEPOSIT", initialDeposit, "Initial deposit"));
        }
    }