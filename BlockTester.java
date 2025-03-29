import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BlockTester {
    
    public static void main(String[] args) throws NoSuchAlgorithmException, InterruptedException {
        // for(int i = 0; i<10; i++) {
        //     Thread.sleep(1000);
        //     System.out.println(i);
        // }

        long startTime = System.currentTimeMillis();


        BlockChain ressCoin = new BlockChain(4, 10);

        
        //List<String> users = Arrays.asList("Kevin", "Jason", "Alice", "Bob", "Charlie", "David");

        //Random rand = new Random();

       // System.out.println("\n--- Performing 1000 Transactions ---");

        User KR = new User("Kevin", 5000000);
        ressCoin.userDatabase.put("Kevin", KR);
        
        for(int i = 0; i < 100; i++) {
            ressCoin.addBlock("Kevin", "Steven", 40);
            ressCoin.addBlock("Steven", "April", 8);
            ressCoin.addBlock("Steven", "Jason", 5);
            ressCoin.addBlock("April", "Jason", 3);
        }
        
        

        //System.out.println(ressCoin.chain);
        
        
        
        
        
        
        
        for (Miner m: ressCoin.miners) {
            System.out.println(m.name + " has won races: " + m.raceWins);
        }
        
        for (Map.Entry<String, User> entry : ressCoin.userDatabase.entrySet()) {
            User user = entry.getValue();
            System.out.println("👤 User: " + user.name);
            System.out.println("   📌 Address: " + user.address);
            System.out.println("   💰 Balance: " + user.balance + " RessCoin");
            System.out.println("------------------------------");
        }

        // for (int i = 0; i < 1000; i++) {
        //     String sender = users.get(rand.nextInt(users.size())); 
        //     String receiver = users.get(rand.nextInt(users.size())); // Pick a random receiver
        //     while (receiver.equals(sender)) { // Ensure sender != receiver
        //         receiver = users.get(rand.nextInt(users.size()));
        //     }
        //     int amount = rand.nextInt(5) + 1; // Random transaction amount (1 - 50)
        //     Block newBlock = ressCoin.addBlock(sender, receiver, amount);
        //     //printBlock(newBlock);
        //     System.out.println("\n--- Block #" + newBlock.index + " ---");
        //     System.out.println("Sender: " + newBlock.sender.name + " (" + newBlock.sender.address + ")");
        //     System.out.println("Receiver: " + newBlock.receiver.name + " (" + newBlock.receiver.address + ")");
        //     System.out.println("Transaction: " + newBlock.data);
        //     System.out.println("Hash: " + newBlock.hash);

        //     // Thread.sleep(500);

        //     // Ensure sender has sufficient balance
        //     // if (ressCoin.balances.getOrDefault(sender, 0) >= amount) {
        //     //     ressCoin.addBlock(sender, receiver, amount);
        //     // }
        // }

        long endTime = System.currentTimeMillis();

        // Step 4: Display blockchain visually
        // System.out.println("\n--- Blockchain State ---");
        // printBlockchain(ressCoin);

        // // Step 5: Validate blockchain
        // System.out.println("\n--- Verifying Blockchain Integrity ---");
        // boolean isValid = verifyBlockchain(ressCoin);
        // System.out.println("Blockchain valid? " + (isValid ? "✅ Yes" : "❌ No"));

        System.out.println("Time taken: " + (endTime - startTime));



    }

    // Helper function to print the blockchain visually
    public static void printBlock(Block block) {
        System.out.println("\n--- Block #" + block.index + " ---");
        System.out.println("Sender: " + block.sender.name + " (" + block.sender.address + ")");
        System.out.println("Receiver: " + block.receiver.name + " (" + block.receiver.address + ")");
        System.out.println("Transaction: " + block.data);
        System.out.println("Previous Hash: " + block.previousHash);
        System.out.println("Current Hash: " + block.hash);
        System.out.println("-----------------------------");
    }

    // Helper function to verify blockchain integrity
    public static boolean verifyBlockchain(BlockChain blockchain) throws NoSuchAlgorithmException {
        for (int i = 1; i < blockchain.chain.size(); i++) {
            Block currentBlock = blockchain.chain.get(i);
            Block previousBlock = blockchain.chain.get(i - 1);

            // Check hash consistency
            if (!currentBlock.hash.equals(currentBlock.computeHash())) {
                System.out.println("❌ Block " + currentBlock.index + " has been tampered with!");
                return false;
            }

            // Check previous hash linkage
            if (!currentBlock.previousHash.equals(previousBlock.hash)) {
                System.out.println("❌ Block " + currentBlock.index + " does not correctly link to the previous block!");
                return false;
            }
        }
        return true;
    }
}
