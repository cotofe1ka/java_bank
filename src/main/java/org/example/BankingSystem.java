
import java.util.*;

public class BankingSystem {
    private static List<BankAccount> accounts = new ArrayList<>();
    private static BankAccount currentAccount = null;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== sistem of bbank ===");

        createSampleAccounts();

        while (true) {
            showMainMenu();
            int choice = getIntInput("options: ");
            processMenuChoice(choice);
        }
    }

    private static void showMainMenu() {
        System.out.println("\n--- main menu ---");
        System.out.println("1. open new credit");
        System.out.println("2. Choose an account to work with");
        System.out.println("3. Deposit money");
        System.out.println("4. Withdraw money");
        System.out.println("5. Show the balance");
        System.out.println("6. List of transactions");
        System.out.println("7. searching tranzaction");
        System.out.println("8. search by attributs");
        System.out.println("9. look for all credits");
        System.out.println("0. excape");

        if(currentAccount!=null){
            System.out.println("\ncredit: "+currentAccount.getAccountNumber()+" ("+currentAccount.getOwnerName()+")");
        }
    }

    private static void processMenuChoice(int choice) {
        switch(choice){
            case 1: openAccount(); break;
            case 2: selectAccount(); break;
            case 3: depositMoney(); break;
            case 4: withdrawMoney(); break;
            case 5: showBalance(); break;
            case 6: showTransactions(); break;
            case 7: searchTransactions(); break;
            case 8: searchAccounts(); break;
            case 9: showAllAccounts(); break;
            case 0:
                System.out.println("bye!");
                System.exit(0);
                break;
            default: System.out.println("lost!");
        }
    }

    private static void createSampleAccounts() {
        accounts.add(new BankAccount("40817810099910004321","Ivanov ivan ivanovi4",50000.0,
                "044525225","773401001","30101810400000000225",
                "sber russian","7707083893","Moscow, st. kolotushkina, h. 19"));

        accounts.add(new BankAccount("40702810500000012345","ООО 'Konfeta'",150000.0,
                "044525201","997750001","30101810700000000101",
                "Al'fa","7728168971","Moskow, st. Pushkina, д. 35"));

        System.out.println("create credits: " + accounts.size());
    }

    private static void openAccount() {
        System.out.println("\n--- open new credit ---");

        System.out.print("enter nomber schet: ");
        String accountNumber = scanner.nextLine();

        System.out.print("enter FIO: ");
        String ownerName = scanner.nextLine();

        double initialDeposit = getDoubleInput("create firstly deposit: ");

        System.out.print("enter BIK: ");
        String bik = scanner.nextLine();

        System.out.print("Enter KPP: ");
        String kpp = scanner.nextLine();

        System.out.print("enter corr credit: ");
        String corrAccount = scanner.nextLine();

        System.out.print("enter name of bank: ");
        String bankName = scanner.nextLine();

        System.out.print("enter INN: ");
        String inn = scanner.nextLine();

        System.out.print("enter adress of bank: ");
        String bankAddress = scanner.nextLine();

        BankAccount newAccount=new BankAccount(accountNumber,ownerName,initialDeposit,
                bik,kpp,corrAccount,bankName,inn,bankAddress);

        accounts.add(newAccount);
        currentAccount=newAccount;
        System.out.println("credit activate");
        newAccount.showFullAccountInfo();
    }

    private static void selectAccount() {
        if(accounts.isEmpty()){
            System.out.println("No look for credits");
            return;
        }

        showAllAccounts();
        int choice=getIntInput("Select an account to work with (number): ");

        if(choice>0&&choice<=accounts.size()){
            currentAccount=accounts.get(choice-1);
            System.out.println("credit "+currentAccount.getAccountNumber()+" selected!");
        }else{
            System.out.println("mistake select!");
        }
    }

    private static void depositMoney() {
        if(!checkAccountSelected())return;

        double amount=getDoubleInput("enter summ for deposit: ");
        System.out.print("enter comment: ");
        String description=scanner.nextLine();

        currentAccount.deposit(amount,description);
    }

    private static void withdrawMoney() {
        if(!checkAccountSelected())return;

        double amount=getDoubleInput("enter for take: ");
        System.out.print("enter comment: ");
        String description=scanner.nextLine();

        currentAccount.withdraw(amount,description);
    }

    private static void showBalance() {
        if(!checkAccountSelected())return;
        currentAccount.showBalance();
    }

    private static void showTransactions() {
        if(!checkAccountSelected())return;
        currentAccount.showTransactions();
    }

    private static void searchTransactions() {
        if (!checkAccountSelected()) return;

        System.out.println("\n=== searching tranzactions ===");
        System.out.print("Тип (DEPOSIT/WITHDRAWAL/пусто): ");
        String type = scanner.nextLine();

        System.out.print("Keyword in the description (empty - skip): ");
        String keyword = scanner.nextLine();

        Double minAmount = null;
        Double maxAmount = null;

        System.out.print("Minimum amount (empty - skip): ");
        String minInput = scanner.nextLine();
        if (!minInput.isEmpty()) {
            minAmount = Double.parseDouble(minInput);
        }

        System.out.print("Maximum amount (empty - skip): ");
        String maxInput = scanner.nextLine();
        if (!maxInput.isEmpty()) {
            maxAmount = Double.parseDouble(maxInput);
        }

        currentAccount.searchTransactions(type, keyword, minAmount, maxAmount);
    }

    private static void searchAccounts() {
        System.out.println("\n=== searching credits atributs ===");
        System.out.print("enter nomber credit, BIK, KPP, INN, the owner's name or part of the string: ");
        String searchTerm = scanner.nextLine();

        List<BankAccount> foundAccounts = new ArrayList<>();

        for(BankAccount account:accounts){
            if(account.matchesSearch(searchTerm)){
                foundAccounts.add(account);
            }
        }

        if(foundAccounts.isEmpty()){
            System.out.println("credits no look for.");
        }else{
            System.out.println("\n=== i'm search credits: ===");
            for(int i=0;i<foundAccounts.size();i++){
                BankAccount account=foundAccounts.get(i);
                System.out.println((i+1)+". credit: "+account.getAccountNumber()+" | owner: "+account.getOwnerName()+" | ballance: "+account.getBalance());
            }

            if(foundAccounts.size()==1){
                System.out.print("Найден 1 счет. Сделать его текущим? (y/n): ");//тут можно работать со счетами
                String response=scanner.nextLine();
                if(response.equalsIgnoreCase("y")){
                    currentAccount=foundAccounts.get(0);
                    System.out.println("credit take!");
                }
            }else if(foundAccounts.size()>1){
                System.out.print("Select an account to work with (number) or 0 to cancel: ");
                int accountChoice=getIntInput("");
                if(accountChoice>0&&accountChoice<=foundAccounts.size()){
                    currentAccount=foundAccounts.get(accountChoice-1);
                    System.out.println("credit take!");
                }
            }
        }
    }

    private static void showAllAccounts() {
        if(accounts.isEmpty()){
            System.out.println("no open credits.");
            return;
        }

        System.out.println("\n=== all credits ===");
        for(int i=0;i<accounts.size();i++){
            BankAccount account=accounts.get(i);
            String currentIndicator=(account==currentAccount)?" [now]":"";
            System.out.println((i+1)+". credit: "+account.getAccountNumber()+" | owner: "+account.getOwnerName()+" | ballance: "+account.getBalance()+currentIndicator);
        }
    }

    private static boolean checkAccountSelected() {
        if(currentAccount==null){
            System.out.println("Error: No account selected! Use option 2 or 8.");
            return false;
        }
        return true;
    }

    private static int getIntInput(String prompt) {
        while(true){
            try{
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            }catch(NumberFormatException e){
                System.out.println("Error: Enter an integer!");
            }
        }
    }

    private static double getDoubleInput(String prompt) {
        while(true){
            try{
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine());
            }catch(NumberFormatException e){
                System.out.println("mistake");
            }
        }
    }
}