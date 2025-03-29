import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;

public class BlockChain {
    ArrayList<Block> chain = new ArrayList<Block>();
    HashMap<String, User> userDatabase = new HashMap<>();
    Miner[] miners;
    public int difficulty;
    public int numOfMiners;

    public BlockChain(int difficulty, int numOfMiners) throws NoSuchAlgorithmException {
        this.difficulty = difficulty;
        miners = new Miner[numOfMiners];
        createMiners(numOfMiners);
        
        User Genesis = new User("Genesis", 0);
        userDatabase.put("Geneis", Genesis);
        
        Block genesisBlock = new Block(0, Genesis, Genesis, "Genesis Block", "0");
        // genesisBlock.mineBlock(difficulty, miners);
        chain.add(genesisBlock);

    }

    public void addBlock(String sender, String receiver, int transactionAmount) throws NoSuchAlgorithmException {
        if (!userDatabase.containsKey(sender)) {
            User senderUser = new User(sender);
            userDatabase.put(sender, senderUser);
        }
        if (!userDatabase.containsKey(receiver)) {
            User receiverUser = new User(receiver);
            userDatabase.put(receiver, receiverUser);
        }
        User sendingUser = userDatabase.get(sender);
        User receivingUser = userDatabase.get(receiver);
        
        if(sendingUser.balance < transactionAmount) {
            System.out.println("Insufficient funds of Sender");
            return;
        }
        else {
            sendingUser.balance -= transactionAmount;
            receivingUser.balance += transactionAmount;
        }

        String transactionData = sender + " sends " + transactionAmount + " RessCoin to " + receiver;

        String previousHash = chain.get(chain.size() - 1).hash;

        Block newBlock = new Block(chain.size(), sendingUser, receivingUser, transactionData, previousHash);
        int winningMiner = newBlock.mineBlock(difficulty, miners);
        
        System.out.println(miners[winningMiner].name + " won the race. They earned 5 RessCoin");
        miners[winningMiner].balance += 5;
        miners[winningMiner].raceWins += 1;

        chain.add(newBlock);
        System.out.println("Money Sent.");
        return;
    }

    public void createMiners(int numOfMiners) {
        String[] names = {"aa", "ab", "ac", "ad", "ae", "af", "ag", "ah", "ai", "aj", "ak", "al", "am", "an", "ao", 
        "ap", "aq", "ar", "as", "at", "au", "av", "aw", "ax", "ay", "az"};
        for(int i = 0; i < numOfMiners; i++) {
            Miner newMiner = new Miner(names[i]);
            miners[i] = newMiner;
        }
    }

    public boolean checkUser(String name) {
        return userDatabase.containsKey(name);
    }

    public void addFunds(String name, int funds) {
        if (!userDatabase.containsKey(name)) {
            User newUser = new User(name, funds);
            userDatabase.put(name, newUser);
        }
        else {
            userDatabase.get(name).balance += funds;
        }
    }

    
}
