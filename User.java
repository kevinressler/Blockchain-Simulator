import java.util.Random;

public class User {
    public String name;
    public String address;
    public int balance;

    public User(String name) {
        this.name = name;
        this.balance = 0;
        this.address = createAddress();
    }

    public User(String name, int balance) {
        this.name = name;
        this.balance = balance;
        this.address = createAddress();
    }

    public String createAddress() {
        String alphaNumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int addressLength = 10;
        StringBuilder address = new StringBuilder();
        for (int i = 0; i < addressLength; i++) {
            Random random = new Random();
            String character = Character.toString(alphaNumeric.charAt(random.nextInt(62)));
            address.append(character);
        }
        return address.toString();
    }
}
