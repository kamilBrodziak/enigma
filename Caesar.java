import java.util.HashMap;
import java.util.Map;

public class Caesar {

    public static void encrypt(int key, String message) {
        message = message.toUpperCase();
        Map<Integer, String> alphabet = new HashMap<Integer, String>();
        for(int i = 65, j = 0; i < 91; i++, j++)
            alphabet.put(j, ((char)i) + "");
        String message2 = "";
        for(int i = 0; i < message.length(); i++) {
            message2 += alphabet.get((((int)message.charAt(i) - 65) + key)%26);
            System.out.print(message2.charAt(i));
        }
        System.out.println();
    }

    public static void decrypt(int key, String message) {
        encrypt(26-key, message);
    }

    public static void main(String[] args) {
        decrypt(1, "bcd");
    }
}