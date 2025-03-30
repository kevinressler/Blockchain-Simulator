import java.security.NoSuchAlgorithmException;

public class Demo {
    
    public static void main(String[] args) throws NoSuchAlgorithmException, InterruptedException {

        System.out.println("Blockchain is starting...\n");
        Thread.sleep(2000);


        // Initializes the blockchian with the difficulty and number of miners
        BlockChain bc = new BlockChain(2,5);

        // creates up to 26 users each with a unique name and 10000-20000 coins
        bc.createUsers(10);

        // simulates 100 transactions with a 1 second pause in between each transaction
        for(int i = 0; i < 100; i++) {
            bc.addBlock(bc.getRandomUser(), bc.getRandomUser(), i);
            System.out.println(bc.chain.get(bc.chain.size() - 1).toString());

            Thread.sleep(1000);
        }
        
        // displays the blockchain after the transactions have been completed
        bc.showBlockChain();
        
        
        // displays the miners and their balances after the transactions have been completed
        for (Miner m: bc.miners) {
            System.out.println(m.name + " has successfully mined " + m.raceWins + " blocks and has a balance of: " + m.balance);
        }
        System.out.println("\n\n");
        
        // displays the users and their balances after the transactions have been completed
        for (User u: bc.userDatabase.values()) {
            System.out.println(u.name + " has a balance of: " + u.balance);
        }
    }
}
