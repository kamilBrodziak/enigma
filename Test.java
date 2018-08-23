import java.util.Scanner;

public class Test
{
    public static void main(String[] args) {
        if(args.length > 1) {
            System.out.println("Please deliver message to encrypt/decrypt.");
            Scanner getMessage = new Scanner(System.in);
            String message = getMessage.nextLine();
            if (!isAlpha(message)) {
                System.out.println("Invalid message. You should give message containing only letters");
                System.exit(0);
            }
            getMessage.close();
            switch (args[1].toUpperCase())
            {
                case "CAESAR":
                    if (args.length < 3 || !isNumeric(args[2])) {
                        System.out.println("You must give a number key!");
                        break;
                    }
                    caesar(args[0], args[2] ,message);
                    break;
                case "SSC":
                    if (args.length < 3 || !isAlpha(args[2])) {
                        System.out.println("You must give a key that contains only letters!");
                        break;
                    }
                    sscc(args[0], args[2].toLowerCase(), message);
                    break;
                case "VIGENERE":
                    if (args.length < 3 || !isAlpha(args[2])) {
                        System.out.println("You must give a key that contains only letters!");
                        break;
                    }
                    vig(args[0], args[2].toLowerCase(), message);
                    break;
                case "POLYBIUS":
                    if (args.length < 3 || !isAlpha(args[2])) {
                        System.out.println("You must give a key that contains only letters!");
                        break;
                    }
                    poly(args[0], args[2].toLowerCase(), message);
                        break;
                case "RAILFENCE":
                    if (args.length < 3 || !isNumeric(args[2])) {
                        System.out.println("You must give a number key!");
                        break;
                    }
                    rail(args[0], args[2] ,message);
                    break;
                case "BIFID":
                    if (args.length < 4 || !isNumeric(args[3]) || !isAlpha(args[2])) {
                        System.out.println("You must give a string key and then after space number key!");
                        break;
                    }
                    bifid(args[0], args[2], args[3] ,message);
                    break;
                case "AFFINE":
                if (args.length < 4 || !isNumeric(args[3]) || !isNumeric(args[2])) {
                    System.out.println("You must give a number key(that must be relatively prime to 26) and then after space another number key!");
                    break;
                }
                affine(args[0], args[2], args[3] ,message);
                break;
                default:
                    System.out.println("There is no such option!");
            }
        } else
            System.out.println("Bad program invoke.");
    }
    private static boolean isNumeric(String s) {  
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    } 

    private static boolean isAlpha(String name) {
        return name.matches("^[ A-Za-z]+$");
    }

    private static void caesar(String option, String key, String message) {
        if (option.equals("-e")) {
            System.out.println("Encrypted message: ");
            Caesar.encrypt(Integer.parseInt(key), message);
        }
        else if(option.equals("-d")) {
            System.out.println("Decrypted message: ");
            Caesar.decrypt(Integer.parseInt(key), message);
        }
        else
            System.out.println("No such option, available: -d, -e!");
    }

    private static void sscc(String option, String key, String message) {
        SimpleSubstitutionChiper ssc = new SimpleSubstitutionChiper(key, message, option);
        ssc.Encipher();
    }

    private static void vig(String option, String key, String message) {
        if (option.equals("-d"))
            Vigenere.decrypt(key, message);
        else if (option.equals("-e"))
            Vigenere.encrypt(key, message);
        else
            System.out.println("No such option, available: -d, -e!");
    }

    private static void poly(String option, String key, String message) {
        if (option.equals("-d"))
            Polybius.decrypt(key.toLowerCase(), message.toUpperCase());
        else if (option.equals("-e"))
            Polybius.encrypt(key.toLowerCase(), message.toLowerCase());
        else
            System.out.println("No such option, available: -d, -e!");
    }

    private static void rail(String option, String key, String message) {
        if (option.equals("-e")) {
            System.out.println("Encrypted message: ");
            Railfence.encrypt(Integer.parseInt(key), message);
        }
        else if(option.equals("-d")) {
            System.out.println("Decrypted message: ");
            Railfence.decrypt(Integer.parseInt(key), message);
        }
        else
            System.out.println("No such option, available: -d, -e!");
    }
    
    private static void bifid(String option, String keyString, String keyCols, String message) {
        message = message.replace(" ", "");
        message = message.replace("j", "i").toLowerCase();

        if (option.equals("-e")) {
            System.out.println("Encrypted message: ");
            Bifid.encrypt(message, keyString, Integer.parseInt(keyCols));
        }
        else if(option.equals("-d")) {
            System.out.println("Decrypted message: ");
            Bifid.decrypt(message, keyString, Integer.parseInt(keyCols));
        }
        else
            System.out.println("No such option, available: -d, -e!");
    }

    private static void affine(String option, String keyA, String keyB, String message) {
        for (int i = 0; i < keyA.length(); ++i)
            if (keyA.charAt(i) == '2' || keyA.charAt(i) == '6') {
                System.out.println("First key and 26 must by relatively prime!");
                return;
            }

        if (option.equals("-e")) {
            System.out.println("Encrypted message: ");
            Affine.encrypt(message, Integer.parseInt(keyA), Integer.parseInt(keyB));
        }
        else if(option.equals("-d")) {
            System.out.println("Decrypted message: ");
            Affine.decrypt(message, Integer.parseInt(keyA), Integer.parseInt(keyB));
        }
        else
            System.out.println("No such option, available: -d, -e!");
    }
}