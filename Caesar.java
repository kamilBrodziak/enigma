import java.util.HashMap;
import java.util.Map;

public class Caesar {
    private static final int ASCII_A_VAL = 65;
    private static final int ASCII_Z_VAL = 90;
    private static final int ALPH_LENGTH = 26;

    public static void encrypt(int key, String message) {
        message = message.toUpperCase();
        Map<Integer, String> alphabet = new HashMap<Integer, String>();
        for(int i = ASCII_A_VAL, j = 0; i <= ASCII_Z_VAL; i++, j++)
            alphabet.put(j, ((char)i) + "");
        String message2 = "";
        for(int i = 0; i < message.length(); i++) {
            message2 += alphabet.get((((int)message.charAt(i) - ASCII_A_VAL) + key)%ALPH_LENGTH);
            System.out.print(message2.charAt(i));
        }
        System.out.println();
    }

    public static void decrypt(int key, String message) {
        encrypt(ALPH_LENGTH-key, message);
    }

    public static void main(String[] args) {
    }
}