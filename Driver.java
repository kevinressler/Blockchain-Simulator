import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) throws NoSuchAlgorithmException, InterruptedException {
        new Driver();
    }

    public Driver() throws NoSuchAlgorithmException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        
        
        // Setting up Blockchian
        // System.out.println("\nWelcome to the blockchain.\nHow many miners: ");
        // int numOfMiners = Integer.parseInt(scanner.nextLine());
        
        System.out.println("\nChoose a difficulty level for mining blocks. (recommended: <6)");
        System.out.println("Higher values make the blockchain more secure, but take longer to mine:");
        int difficulty = Integer.parseInt(scanner.nextLine());

        BlockChain theChain = new BlockChain(difficulty, 10);

        System.out.println("Blockchain settings confirmed");
        
        while (true) {

            System.out.println("\n1. Create Wallet\n2. Add Funds\n3. Send Money\n4. View Blockchain\n5. View User Wallets\n6. Reconfigure Blockchain Difficulty(users and transactions will be erased)\n7. Exit");
            
            int choice = Integer.parseInt(scanner.nextLine());
            
            switch (choice) {
                case 1:
                    
                    System.out.println("\nWhat is your name: ");
                    String walletName = scanner.nextLine();

                    if (theChain.checkUser(walletName)) {
                        System.out.println("Wallet already exists");
                    }
                    else {
                        theChain.userDatabase.put(walletName, new User(walletName));
                        System.out.println("\nWallet created for: " + walletName);
                    }

                    Thread.sleep(2000);
                    break;

                case 2:

                    System.out.println("\nWallet name: ");
                    String walletName2 = scanner.nextLine();
                    
                    System.out.println("How many coins: ");
                    int funds = Integer.parseInt(scanner.nextLine());
                    theChain.addFunds(walletName2, funds);
                    System.out.println(funds + " added to " + walletName2 + "'s wallet");

                    Thread.sleep(2000);
                    break;

                case 3:

                    System.out.println("\nName of Sender: ");
                    String sender = scanner.nextLine();

                    System.out.println("Name of Receiver: ");
                    String receiver = scanner.nextLine();

                    System.out.println("Transaction amount: ");
                    int transactionAmount = Integer.parseInt(scanner.nextLine());

                    System.out.println("\nMining in progress...");
                    Thread.sleep(2000);

                    System.out.println("\nTransaction Pending...");

                    theChain.addBlock(sender, receiver, transactionAmount);

                    Thread.sleep(2000);

                    break;

                case 4:

                    theChain.showBlockChain();
                    Thread.sleep(2000);
                    break;

                case 5:
                    
                    System.out.println("");
                    for (Map.Entry<String, User> entry : theChain.userDatabase.entrySet()) {
                        User user = entry.getValue();
                        System.out.println("------------------------------");
                        System.out.println("User: " + user.name);
                        System.out.println("    Address: " + user.address);
                        System.out.println("    Balance: " + user.balance + " coins");
                        System.out.println("------------------------------");
                    }

                    Thread.sleep(2000);

                    break;

                case 6:

                    Thread.sleep(2000);
                        
                    new Driver();
                    return;

                case 7:

                    System.exit(0);

                default:

                    System.out.println("\nenter a number 1-7");
                    break;
            }
        }
    }
}
