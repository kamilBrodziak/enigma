import java.util.HashMap;
import java.util.Map;

public class Vigenere {

    private static char[][] generateAlphabet() {
        char[][] alphabet = new char[26][26];
        int tmp;
        for(int i = 0; i < alphabet.length; ++i)
            for(int j = i; j < alphabet.length + i; ++j) {
                tmp = j + 65;
                if(tmp > 90)
                    tmp -= 26;
                alphabet[i][j - i] = (char)(tmp);
            }
        return alphabet;
    }

    private static String generateKey(String key, String message) {
        String actualKey = "";
        for (int i = 0; i < message.length(); ++i)
            actualKey += key.charAt(i%key.length());
        return actualKey;
    }

    public static void encrypt(String key, String message) {
        char[][] alphabet = generateAlphabet();
        String actualKey = generateKey(key, message);
        String encryptedMessage = "";
        
        for(int i = 0; i < actualKey.length(); ++i)
            encryptedMessage += alphabet[(int)actualKey.charAt(i) - 65][(int)message.charAt(i) - 65];
        System.out.println(actualKey);
        System.out.println(message);
        System.out.println();
        System.out.println(encryptedMessage);
    }

    public static void decrypt(String key, String message) {
        char[][] alphabet = generateAlphabet();
        String actualKey = generateKey(key, message);
        String decryptedMessage = "";

        for(int i = 0; i < actualKey.length(); ++i)
            for(int j = 0; j < 26; ++j)
                if (message.charAt(i) == alphabet[(int)actualKey.charAt(i) - 65][j]) {
                    decryptedMessage += (char)(65 + j);
                    break;
                }
            System.out.println(actualKey);
            System.out.println(message);
            System.out.println();
            System.out.println(decryptedMessage);
    }

    public static void main(String[] args) {
        encrypt("FORTIFICATION", "DEFENDTHEEASTWALLOFTHECASTLE");
        decrypt("FORTIFICATION", "ISWXVIBJEXIGGBOCEWKBJEVIGGQS");
    }
}