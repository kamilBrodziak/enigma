import java.util.Arrays;

public class Railfence {

    public static void encrypt(int key, String message) {
        String[] rails = new String[key];
        String encrypted = "";
        rails = createRails(rails, message, key);
        for(String s: rails)
            encrypted += s;
        System.out.println(encrypted);
    }

    private static String[] createRails(String[] rails, String message, int railCount) {
        Arrays.fill(rails, "");
        boolean increment = true;
        for(int i = 0, rail = 0; i < message.length(); ++i) {
            if (message.charAt(i) == ' ')
                continue;
            rails[rail] += "" + message.charAt(i);
            rail = increment ? rail + 1: rail - 1;
            if (rail == 0 || rail == railCount - 1)
                increment = !increment;
        }
        return rails;
    }

    public static void decrypt(int key, String message) {
        String[] rails = new String[key];
        Arrays.fill(rails, "");

        String[] invalidRails = new String[key]; // needed to know lengths of rails
        invalidRails = createRails(invalidRails, message, key);

        // creating valid rails (dividing message into rails thanks to information about rails length from invalidrails)
        int rail0length = invalidRails[0].length();
        int rail1length = invalidRails[1].length();
        rails[0] = message.substring(0, rail0length);
        rails[1] = message.substring(rail0length ,rail0length + rail1length);
        rails[2] = message.substring(rail0length + rail1length, message.length());
        
        System.out.println(decryptWordFromRails(rails, message.length(), key));
    }

    private static String decryptWordFromRails(String[] rails, int messageLength, int railsCount) {
        String decrypted = "";
        boolean increment = true;
        for(int i = 0, rail = 0; i < messageLength; ++i) {
            decrypted += "" + rails[rail].charAt(0);
            if (rails[rail].length() > 1)
                rails[rail] = rails[rail].substring(1, rails[rail].length());
            rail = increment ? rail + 1: rail - 1;
            if (rail == 0 || rail == railsCount - 1)
                increment = !increment;
        }
        return decrypted;
    }

    public static void main(String[] args) {
        encrypt(3, "defend the east wall of the castle");
        decrypt(3, "dnetlhseedheswloteateftaafcl");
    }
}