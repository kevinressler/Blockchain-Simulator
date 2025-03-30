import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class BlockChain {
    ArrayList<Block> chain = new ArrayList<>();
    HashMap<String, User> userDatabase = new HashMap<>();
    Miner[] miners;
    public int difficulty;
    public int numOfMiners;

    public BlockChain(int difficulty, int numOfMiners) throws NoSuchAlgorithmException {
        this.difficulty = difficulty;
        miners = new Miner[numOfMiners];
        createMiners(numOfMiners);
        
        User Genesis = new User("Genesis", 0);
        userDatabase.put("Genesis", Genesis);
        
        Block genesisBlock = new Block(0, Genesis, Genesis, "Genesis Block", "0");
        // genesisBlock.mineBlock(difficulty, miners);
        chain.add(genesisBlock);

    }

    public void addBlock(String sender, String receiver, int transactionAmount) throws NoSuchAlgorithmException {
        if (!userDatabase.containsKey(sender)) {
            System.out.println("\nTransaction Failed. Sending user not found. Create a wallet first.");
            return;
        }
        if (!userDatabase.containsKey(receiver)) {
            System.out.println("\nTransaction Failed. Receiving user not found. Create a wallet first.");
            return;
        }
        User sendingUser = userDatabase.get(sender);
        User receivingUser = userDatabase.get(receiver);
        
        if(sendingUser.balance < transactionAmount) {
            System.out.println("Transaction Failed. Insufficient funds of Sender");
            System.out.println("Sender: " + sendingUser.name + " has " + sendingUser.balance + " coins.");
            System.out.println("Transaction amount: " + transactionAmount + " coins.");
            return;
        }
        else {
            sendingUser.balance -= transactionAmount;
            receivingUser.balance += transactionAmount;
        }

        String transactionData = sender + " sends " + transactionAmount + " coins to " + receiver;
        String previousHash = chain.get(chain.size() - 1).hash;

        Block newBlock = new Block(chain.size(), sendingUser, receivingUser, transactionData, previousHash);
        int winningMiner = newBlock.mineBlock(difficulty, miners);
        
        System.out.println("\n" + miners[winningMiner].name + " found the hash. They earned 5 coins");
        miners[winningMiner].balance += 5;
        miners[winningMiner].raceWins += 1;

        chain.add(newBlock);
        System.out.println("\nMoney Sent.");
    }

    public void createMiners(int numOfMiners) {
        List<String> minerNames = new ArrayList<>(List.of("Ethan", "Lily", "Aiden", "Zoe", "Caleb", "Avery", "Logan", "Isla", "Connor", "Brooklyn"));

        Collections.shuffle(minerNames);

        for(int i = 0; i < numOfMiners; i++) {
            Miner newMiner = new Miner(minerNames.remove(0));
            miners[i] = newMiner;
        }
    }

    public boolean checkUser(String name) {
        return userDatabase.containsKey(name);
    }

    public void addFunds(String name, int funds) {
        if (!userDatabase.containsKey(name)) {
            System.out.println("User not found. Create a wallet first.");
        }
        else {
            userDatabase.get(name).balance += funds;
        }
    }

    public void showBlockChain() {
        for (Block b: chain) {
            System.out.println(b);
        }
    }

    public void createUsers(int numOfUsers) {
        List<String> userNames = new ArrayList<>(List.of("James", "Michael", "William", "David", "Joseph", "Emily", "Sarah", "Jessica", "Ashley", "Hannah", "Samantha", "Olivia", "Rachel", "Lauren", "Megan", 
        "Daniel", "Matthew", "Christopher", "Joshua", "Andrew", "Chloe", "Grace", "Natalie", "Ryan", "John", "Nathan"));

        Collections.shuffle(userNames);

        Random rand = new Random();


        for(int i = 0; i < numOfUsers; i++) {
            int startingBalance = rand.nextInt(10001) + 10000;
            User newUser = new User(userNames.remove(0), startingBalance);
            userDatabase.put(newUser.name, newUser);
        }
    }

    public String getRandomUser() {
        
        List<String> keys = new ArrayList<>(userDatabase.keySet());
        Random rand = new Random();
        String randomKey = keys.get(rand.nextInt(keys.size()));
        while (randomKey.equals("Genesis")) {
            randomKey = keys.get(rand.nextInt(keys.size()));
        }
        return randomKey;
    }
}
