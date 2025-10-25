import java.util.*;

public class BankAccount {
    private String accountNumber;
    private String ownerName;
    private double balance;
    private boolean active;
    private List<Transaction> transactions;

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

    public boolean deposit(double amount, String description) {
        if (amount <= 0) {
            System.out.println("uncorrect summ!");
            return false;
        }
        if (!active) {
            System.out.println("mistake: credit no active!");
            return false;
        }

        balance += amount;
        transactions.add(new Transaction("DEPOSIT", amount, description));
        System.out.printf("successfuly: %.2f. new ballance: %.2f\n", amount, balance);
        return true;
    }

    public boolean withdraw(double amount,String description){
        if(amount<=0){
            System.out.println("mistake: uncorrect summ!");
            return false;
        }
        if(!active){
            System.out.println("mistake: credit afk!");
            return false;
        }
        if(amount>balance){
            System.out.println("mistake: no ballance!");
            return false;
        }

        balance-=amount;
        transactions.add(new Transaction("WITHDRAWAL",amount,description));
        System.out.printf("succesfule, give: %.2f. new ballance: %.2f\n",amount,balance);
        return true;
    }

    public void showBalance() {
        System.out.printf("ballance: %s: %.2f\n", accountNumber, balance);
    }

    public void showTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("no tranzaction.");
            return;
        }

        System.out.println("\n=== History tranzaction ===");
        for (int i = 0; i < transactions.size(); i++) {
            System.out.println((i + 1) + ". " + transactions.get(i));
        }
        System.out.println("==========================\n");
    }

    public boolean matchesSearch(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return false;
        }

        String term = searchTerm.toLowerCase().trim();

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
        System.out.println("\n=== full inforamtion ===");
        System.out.println("nomber of credit: " + accountNumber);
        System.out.println("owner: " + ownerName);
        System.out.printf("ballance: %.2f\n", balance);
        System.out.println("status: " + (active ? "activly" : "no active"));
        System.out.println("BIK: " + (bik != null ? bik : "Not specified"));
        System.out.println("KPP: " + (kpp != null ? kpp : "Not specified"));
        System.out.println("corr credit: " + (corrAccount != null ? corrAccount : "Not specified"));
        System.out.println("Bank: " + (bankName != null ? bankName : "Not specified"));
        System.out.println("INN: " + (inn != null ? inn : "Not specified"));
        System.out.println("adress of bank: " + (bankAddress != null ? bankAddress : "Not specified"));
        System.out.println("================================\n");
    }

    public void searchTransactions(String searchType, String keyword, Double minAmount, Double maxAmount) {
        List<Transaction> results = new ArrayList<>();

        for (Transaction transaction : transactions) {
            boolean matches = true;

            if (searchType != null && !searchType.isEmpty()) {
                if (!transaction.getType().equalsIgnoreCase(searchType)) {
                    matches = false;
                }
            }

            if (keyword != null && !keyword.isEmpty()) {
                if (!transaction.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                    matches = false;
                }
            }

            if (minAmount != null) {
                if (transaction.getAmount() < minAmount) {
                    matches = false;
                }
            }

            if (maxAmount != null) {
                if (transaction.getAmount() > maxAmount) {
                    matches = false;
                }
            }

            if (matches) {
                results.add(transaction);
            }
        }

        if (results.isEmpty()) {
            System.out.println("tranzaction no search.");
        } else {
            System.out.println("\n=== results of search ===");
            for (int i = 0; i < results.size(); i++) {
                System.out.println((i + 1) + ". " + results.get(i));
            }
            System.out.println("=========================\n");
        }
    }

    public String getAccountNumber() { return accountNumber; }
    public String getOwnerName() { return ownerName; }
    public double getBalance() { return balance; }
    public boolean isActive() { return active; }
    public String getBik() { return bik; }
    public String getKpp() { return kpp; }
    public String getCorrAccount() { return corrAccount; }
    public String getBankName() { return bankName; }
    public String getInn() { return inn; }
    public String getBankAddress() { return bankAddress; }
}