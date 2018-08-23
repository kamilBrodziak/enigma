public class Polybius {

    private static final int startAsciiAlph = 65;

    public static void encrypt(String key, String message) {
        String alphKey = generateAlph(key);
        String encrypted = "";
        message = message.replace('j', 'i');
        for(int i = 0; i < message.length(); ++i)
            if (message.charAt(i) == ' ')
                encrypted += " ";
            else
                encrypted += "" + (char)(alphKey.indexOf(message.charAt(i)) / 5 + startAsciiAlph) + 
                                  (char)(alphKey.indexOf(message.charAt(i)) % 5 + startAsciiAlph);
        System.out.println(encrypted);
    }

    public static void decrypt(String key, String message) {
        String alphKey = generateAlph(key);
        String decrypted = "";
        message = message.replace('j', 'i');
        for(int i = 0; i < message.length(); ++i)
            if (message.charAt(i) == ' ')
                decrypted += " ";
            else {
                decrypted += "" + alphKey.charAt(((int)message.charAt(i) - startAsciiAlph) * 5 + 
                                                (((int)message.charAt(i + 1) - startAsciiAlph) % 5));
                ++i;
            }
        System.out.println(decrypted);
    }

    private static String generateAlph(String key) {
        String newAlph = "";
        String normalAlph = "abcdefghiklmnopqrstuvwxyz";
        key = key.replace('j', 'i');
        newAlph = addCharsToString(key, newAlph);
        newAlph = addCharsToString(normalAlph, newAlph);
        return newAlph;
    }

    private static String addCharsToString(String key, String str) {
        for(int i = 0; i < key.length(); ++i)
            if (str.indexOf(key.charAt(i)) == -1)
                str += key.charAt(i);
        return str;
    }

    public static void main(String[] args) {
        encrypt("phqgmeaylnofdxkrcvszwbuti", "defend the east wall of the castle");
        decrypt("phqgmeaylnofdxkrcvszwbuti", "CCBACBBABECC EDABBA BABBDDED EABBBDBD CACB EDABBA DBBBDDEDBDBA");
    }
}