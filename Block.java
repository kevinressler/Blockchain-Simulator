import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;

public class Block {
    
    public int index;
    public User sender;
    public User receiver;
    public String hash;
    public String previousHash;
    public long timestamp;
    public String data;
    public int nonce;

    public Block(int index, User sender, User receiver, String data, String previousHash) throws NoSuchAlgorithmException {
        this.index = index;
        this.sender = sender;
        this.receiver = receiver;
        this.hash = computeHash();
        this.previousHash = previousHash;
        this.timestamp = Instant.now().getEpochSecond();
        this.nonce = 0;
        this.data = data;

    }

    public String computeHash() throws NoSuchAlgorithmException {
        String input = index + Long.toString(timestamp) + data + previousHash + nonce;
        
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));

        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    } 

    public int mineBlock(int difficulty, Miner[] miners) throws NoSuchAlgorithmException {
        String targetPrefix = "";
        nonce = 0;
        for(int i = 0; i < difficulty; i++) {
            targetPrefix = targetPrefix + "0";
        }

        boolean isFound = false;
        int counter = 0;
        
        while (!isFound) {
            if (counter == miners.length) {
                counter = 0;
            }
            this.hash = computeHash();
            String hashString = "";
            
            for(int i = 0; i < difficulty; i++) {
                hashString = hashString + hash.charAt(i);
            }
            if (targetPrefix.equals(hashString)) {
                isFound = true;
                System.out.println("Winning Hash: " + this.hash);
                System.out.println("Winning Nonse: " + nonce);
            }
            else {
                System.out.println(this.hash);
                nonce++;
                counter++;
            }
        }
        return counter;
    }
    
    
    // public String toString() {
    //     StringBuilder sb = new StringBuilder();
    //     sb.append("Block #").append(index);
    //     sb.append("\nData: ").append(data);
    //     sb.append("\nTimestamp: ").append(timestamp);
    //     return sb.toString();
    // }
}


