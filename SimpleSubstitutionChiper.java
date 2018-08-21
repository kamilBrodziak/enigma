import java.util.ArrayList;
public class SimpleSubstitutionChiper{
    private String alphabet = "abcdefghijklmnoprstuwxyz";
    private String key;
    private String message;
    private String decision;    
    ArrayList<Character> keyList = new ArrayList<Character>();
    ArrayList<Character> messageList = new ArrayList<Character>();
    ArrayList<Character> alphabetList = new ArrayList<Character>();

    public ArrayList<Character> makeList(String word){
        ArrayList<Character> newList = new ArrayList<Character>();
        for(int i=0;i<word.length();i++){
            newList.add(word.charAt(i));
        }
        return newList;

    }

    SimpleSubstitutionChiper(String key, String message, String decision){
        // System.out.println("hi");
        this.key=key;
        this.message=message;
        this.decision=decision;
        keyList=makeList(key);
        messageList=makeList(message);
        alphabetList=makeList(alphabet);

    }

    public String Encipher(){
        String enciperWord="";
        // System.out.println(message);
        // System.out.println(keyList);
        // System.out.println(messageList);
        // System.out.println(alphabetList);
        if(decision=="c"){

        
            for(char iterator: messageList){
                for(int searcher=0; searcher<alphabetList.size();searcher++){
                    if(iterator==alphabetList.get(searcher)){
                        // System.out.println(searcher+1);
                        enciperWord+=keyList.get(searcher);
                        
                    }
                }
            
            }
        }
        if(decision=="d"){
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
    


    public static void main(String[] args) {
       SimpleSubstitutionChiper a = new SimpleSubstitutionChiper("tibhopnwxrjkduslmyzcefga","doyytno","d");
       a.Encipher();
    }
}

