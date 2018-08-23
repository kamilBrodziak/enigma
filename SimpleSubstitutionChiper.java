import java.util.ArrayList;
public class SimpleSubstitutionChiper{
    private String alphabet = "abcdefghijklmnopqrstuvwxyz";
    private String key;
    private String message;
    private String decision;    
    ArrayList<Character> keyList = new ArrayList<Character>();
    ArrayList<Character> messageList = new ArrayList<Character>();
    ArrayList<Character> alphabetList = new ArrayList<Character>();

    public static ArrayList<Character> makeList(String word){
        ArrayList<Character> newList = new ArrayList<Character>();
        for(int i=0;i<word.length();i++){
            newList.add(word.charAt(i));
        }
        return newList;

    }

    SimpleSubstitutionChiper(String key, String message, String decision){
        // System.out.println("hi");
        this.key=generateAlph(key);
        this.message=message;
        this.decision=decision;
        keyList=makeList(this.key);
        messageList=makeList(message);
        alphabetList=makeList(alphabet);

    }

    public String Encipher(){
        String enciperWord="";
        // System.out.println(message);
        // System.out.println(keyList);
        // System.out.println(messageList);
        // System.out.println(alphabetList);
        if(decision.equals("-e")){

        
            for(char iterator: messageList){
                for(int searcher=0; searcher<alphabetList.size();searcher++){
                    if(iterator==alphabetList.get(searcher)){
                        // System.out.println(searcher+1);
                        enciperWord+=keyList.get(searcher);
                        
                    }
                }
            
            }
        }
        if(decision.equals("-d")){
            for(char iterator: messageList){
                for(int searcher=0; searcher<alphabetList.size();searcher++){
                    if(iterator==keyList.get(searcher)){
                        // System.out.println(searcher+1);
                        enciperWord+=alphabetList.get(searcher);
                        
                    }
                }

            }

        }
        
    System.out.println(message);
    System.out.println(enciperWord);
    return enciperWord;

    }

    private String addCharsToString(String key, String str) {
        for(int i = 0; i < key.length(); ++i)
            if (str.indexOf(key.charAt(i)) == -1)
                str += key.charAt(i);
        return str;
    }

    private String generateAlph(String key) {
        String alp = "";
        String alph = "abcdefghijklmnopqrstuvwxyz";
        alp = addCharsToString(key, alp);
        alp = addCharsToString(alph, alp);
        return alp;
    }


    public static void main(String[] args) {
       SimpleSubstitutionChiper a = new SimpleSubstitutionChiper("dupa","kbrrdeb","-d");
       System.out.println(a.key);
       a.Encipher();
    }
}

