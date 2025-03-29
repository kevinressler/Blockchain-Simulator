import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        new Driver();
    }

    public Driver() throws NoSuchAlgorithmException {
        Scanner scanner = new Scanner(System.in);
        
        
        // Setting up Blockchian
        System.out.println("\nWelcome to RessCoin.\nHow many miners: ");
        int numOfMiners = Integer.parseInt(scanner.nextLine());
        
        System.out.println("\nWhat difficulty (higher is more secure, but slower):");
        int difficulty = Integer.parseInt(scanner.nextLine());

        BlockChain theChain = new BlockChain(difficulty, numOfMiners);

        System.out.println("Blockchain settings confirmed");

        
        
        while (true) {
            System.out.println("\n1. Send Money\n2. Create Wallet\n3. View User Wallets\n4. Reconfigure Blockchain\n5. Exit\n6. Add Funds\n7. View Blockchain");
            int choice = Integer.parseInt(scanner.nextLine());
            
            switch (choice) {
                case 1:
                    System.out.println("\nName of Sender: ");
                    String sender = scanner.nextLine();
                    System.out.println("Name of Receiver: ");
                    String receiver = scanner.nextLine();
                    System.out.println("Transaction amount: ");
                    int transactionAmount = Integer.parseInt(scanner.nextLine());

                    System.out.println("Transaction Pending...");

                    theChain.addBlock(sender, receiver, transactionAmount);

                    //System.out.println("Money Sent.");
                    break;
                case 2:
                    System.out.println("\nWhat is your name: ");
                    String walletName = scanner.nextLine();
                    if (theChain.checkUser(walletName)) {
                        System.out.println("Wallet already exists");
                    }
                    else {
                        theChain.userDatabase.put(walletName, new User(walletName));
                        System.out.println("\nWallet created for: " + walletName);
                    }
                    break;
                case 3:
                    System.out.println("");
                    for (Map.Entry<String, User> entry : theChain.userDatabase.entrySet()) {
                        User user = entry.getValue();
                        System.out.println("👤 User: " + user.name);
                        System.out.println("   📌 Address: " + user.address);
                        System.out.println("   💰 Balance: " + user.balance + " RessCoin");
                        System.out.println("------------------------------");
                    }
                    break;
                case 4:
                    new Driver();
                case 5:
                    System.exit(0);    
                case 6:
                    System.out.println("\nWhat is your name: ");
                    String walletName2 = scanner.nextLine();
                    
                    System.out.println("How many RessCoin: ");
                    int funds = Integer.parseInt(scanner.nextLine());
                    theChain.addFunds(walletName2, funds);
                    System.out.println(funds + " added to " + walletName2 + "'s wallet");
                    break;
                case 7:
                    for (Block b: theChain.chain) {
                        System.out.println("\nBlock index: " + b.index);
                        System.out.println("Block hash: " + b.hash);
                    }
                    break;
                default:
                    System.out.println("\nenter a number 1-7");
            }
        }
    }
}
