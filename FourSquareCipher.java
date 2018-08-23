import java.util.*;
public class FourSquareCipher{
    private String message;
    final String alphabetTXT="abcdefghiklmnopqrstuvwxyz";
    private String key;
    private String key2;
    private String decision;
    public char[][] alphabet= new char[5][5];
    public char[][] ciphertext1=new char[5][5];
    public char[][] ciphertext2=new char[5][5];

    public String getMessage(){
        return message;
    }

    FourSquareCipher(String message, String key, String key2, String decision){
        if(message.length()%2==0)
            this.message=message;
        else
            this.message=message+'x';
        this.key=key;
        this.key2=key2;
        this.decision=decision;
        alphabet=makeArrayFull(alphabetTXT, alphabet.length, alphabet[0].length);
        ciphertext1=makekey(key, ciphertext1.length, ciphertext1[0].length);
        ciphertext2=makekey(key2,ciphertext2.length,ciphertext2[0].length);
        System.out.println(encrypt(getMessage()));
     }

    public String encrypt(String Word){
        String encrypted="";
        char currentChar;
        String newString="";
        int firstletterX=0,firstletterY=0,secondletterX=0,secondletterY=0;
        ArrayList<Character> newWord = new ArrayList<Character>();
        newWord=SimpleSubstitutionChiper.makeList(Word);
        for(char letter: newWord){
            if(letter=='j'){
                letter='i';
            }
        newString+=letter;
        }
        Word=newString;

        if(decision.equals("b")){
            for(int k=0; k<Word.length()-1;k+=2){
                for(int i=0; i<alphabet.length;i++)
                {
                    for(int j=0; j<alphabet[0].length;j++){
                        if(alphabet[i][j]==Word.charAt(k))
                        {
                            firstletterX=i;
                            firstletterY=j;
                        }
                        if(alphabet[i][j]==Word.charAt(k+1))
                        {
                            secondletterX=i;
                            secondletterY=j;
                        }

                    }
                    
                }
            
            

            encrypted+=ciphertext1[firstletterX][secondletterY];
            encrypted+=ciphertext2[secondletterX][firstletterY];
            }
        }

        if(decision.equals("d")){
            for(int k=0; k<Word.length()-1;k+=2){
                for(int i=0; i<ciphertext1.length;i++)
                {
                    for(int j=0; j<ciphertext1[0].length;j++){
                        if(ciphertext1[i][j]==Word.charAt(k))
                        {
                            firstletterX=i;
                            firstletterY=j;
                        }
                        if(ciphertext2[i][j]==Word.charAt(k+1))
                        {
                            secondletterX=i;
                            secondletterY=j;
                        }

                    }
                    
                }
                encrypted+=alphabet[firstletterX][secondletterY];
                encrypted+=alphabet[secondletterX][firstletterY];
        }
    }







        // System.out.println(firstletterX);
        return encrypted;
    }

    public char[][] makekey(String key,int x, int y){
        char[][]  keyArray=new char[x][y];
        int count=0;
        String newWord=key+alphabetTXT;
        //String newWord2="";
        LinkedHashSet<Character> newWord2 = new LinkedHashSet<Character>();
        for(int i=0; i<newWord.length();i++){
            if(newWord.charAt(i)=='j'){
                newWord2.add('i');
            }
            else{
            newWord2.add(newWord.charAt(i));        
            }
        }
        ArrayList<Character> setToList= new ArrayList<Character>(newWord2);

        for(int i=0; i<x; i++){
            for(int j=0; j<y; j++){
                
                if (newWord.length()>count){
                keyArray[i][j]=setToList.get(count);
                    count++;
                }
            }
        }

  
        return keyArray;
      }

    public char[][] makeArrayFull(String content,int x, int y){
        int count=0;
        char[][] returnedarray=new char[x][y];
        for(int i=0; i<x; i++){
            for(int j=0; j<y; j++){
                
                if (content.length()>count){
                returnedarray[i][j]=content.charAt(count);
                    count++;
                }
            }
        }
        return returnedarray;
    }








    public static void main(String[] args) {
        FourSquareCipher a = new FourSquareCipher("pWCKcfdlUp", "KLUCZJEDEN","KLUCZDWA", "d");
        //FourSquareCipher a = new FourSquareCipher("wiadomosc", "KLUCZJEDEN","KLUCZDWA", "b");
        //d to ODSZYFROWYWANIE, b to SZYFROWANIE.
        
    }
}