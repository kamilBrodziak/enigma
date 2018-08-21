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
}