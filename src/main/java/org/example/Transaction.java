package org.example;

import java.util.Date;

// история операций
// Класс для хранения информации о каждой операции
public class BankAccount {
    private String accountNumber;
    private String ownerName;
    private double balance;
    private boolean active;
    private List<Transaction> transactions;

    // Банковские реквизиты
    private String bik;
    private String kpp;
    private String corrAccount;
    private String bankName;
    private String inn;
    private String bankAddress;

    public BankAccount(String accountNumber, String ownerName, double initialDeposit,
                       String bik, String kpp, String corrAccount,
                       String bankName, String inn, String bankAddress) {
        this.accountNumber=accountNumber;
        this.ownerName=ownerName;
        this.balance=initialDeposit;
        this.active=true;
        this.transactions=new ArrayList<>();

        this.bik=bik;
        this.kpp=kpp;
        this.corrAccount=corrAccount;
        this.bankName=bankName;
        this.inn=inn;
        this.bankAddress=bankAddress;

        if(initialDeposit>0){
            transactions.add(new Transaction("DEPOSIT",initialDeposit,"firstly deposit"));
        }
    }

    // Пополнение счета
    public boolean deposit(double amount, String description) {
        if (amount <= 0) {
            System.out.println("mistake: summ uncorrect");
            return false;
        }
        if (!active) {
            System.out.println("mistake: credit is not active");
            return false;
        }

        balance += amount;
        transactions.add(new Transaction("DEPOSIT", amount, description));
        System.out.printf("good: %.2f. new ballance: %.2f\n", amount, balance);
        return true;
    }

    // Снятие денег
    public boolean withdraw(double amount,String description){
        if(amount<=0){
            System.out.println("mistake: summ uncorrect");
            return false;
        }
        if(!active){
            System.out.println("mistake credit is no active");
            return false;
        }
        if(amount>balance){
            System.out.println("mistake: no monye!");
            return false;
        }

        balance-=amount;
        transactions.add(new Transaction("WITHDRAWAL",amount,description));
        System.out.printf("summ take: %.2f. new ballance: %.2f\n",amount,balance);
        return true;
    }

    public void showBalance() {
        System.out.printf("Баланс счета %s: %.2f\n", accountNumber, balance);
    }

    public void showTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("no tranzaction.");
            return;
        }

        System.out.println("\n=== history ===");
        for (int i = 0; i < transactions.size(); i++) {
            System.out.println((i + 1) + ". " + transactions.get(i));
        }
        System.out.println("==========================\n");
    }

    // Поиск по атрибутам счета
    public boolean matchesSearch(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return false;
        }

        String term = searchTerm.toLowerCase().trim();

        // Ищем по всем полям
        return (accountNumber.toLowerCase().contains(term) ||
                ownerName.toLowerCase().contains(term) ||
                (bik != null && bik.toLowerCase().contains(term)) ||
                (kpp != null && kpp.toLowerCase().contains(term)) ||
                (corrAccount != null && corrAccount.toLowerCase().contains(term)) ||
                (bankName != null && bankName.toLowerCase().contains(term)) ||
                (inn != null && inn.toLowerCase().contains(term)) ||
                (bankAddress != null && bankAddress.toLowerCase().contains(term)) ||
                String.valueOf(balance).contains(term));
    }

    public void showFullAccountInfo() {
        System.out.println("\n=== full info ===");
        System.out.println("nomber credit: " + accountNumber);
        System.out.println("who: " + ownerName);
        System.out.printf("ballance: %.2f\n", balance);
        System.out.println("state: " + (active ? "active" : "afc"));
        System.out.println("BUK: " + (bik != null ? bik : "empty"));
        System.out.println("KPP: " + (kpp != null ? kpp : "empty"));
        System.out.println("corr credit: " + (corrAccount != null ? corrAccount : "empty"));
        System.out.println("bankname: " + (bankName != null ? bankName : "empty"));
        System.out.println("INN: " + (inn != null ? inn : "empty"));
        System.out.println("addres: " + (bankAddress != null ? bankAddress : "empty"));
        System.out.println("================================\n");
    }