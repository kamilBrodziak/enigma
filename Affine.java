
public class Affine {
    private static final int ALPH_ASCII_START = 65;
    private static final int ALPH_LEN = 26;

    public static void encrypt(String message, int a, int b) {
        String encrypted = "";
        for(int i = 0; i < message.length(); ++i) 
            encrypted += (char)((((int)message.charAt(i) - ALPH_ASCII_START) * a + b) % ALPH_LEN + ALPH_ASCII_START);
        
        System.out.println(encrypted);
    }

    public static void decrypt(String message, int a, int b) {
        String decrypted = "";
        int inversedA = inverseA(a);
        for(int i = 0; i < message.length(); ++i) {
            if (((int)message.charAt(i) - ALPH_ASCII_START - b) >= 0)
                decrypted += (char)((inversedA * ((int)message.charAt(i) - ALPH_ASCII_START - b)) % ALPH_LEN + ALPH_ASCII_START);
            else
                decrypted += (char)(26 - inversedA * (Math.abs((int)message.charAt(i) - ALPH_ASCII_START - b)) % ALPH_LEN + ALPH_ASCII_START);
        }
        
        System.out.println(decrypted);
    }

    private static int inverseA(int a) {
        for(int i = ALPH_LEN; i >= 0; --i)
            if ((a * i) % ALPH_LEN == 1)
                return i;
        return 0;
    }

    public static void main(String[] args) {
        String message = "defend the east wall of the castle";
        message = message.replace(" ", "");
        message = message.toUpperCase();
        encrypt(message, 5, 7);
        decrypt("WBGBUWYQBBHTYNHKKZGYQBRHTYKB", 5, 7);
    }
}