import java.io.*;
import java.util.Scanner;

public class MyDFAModel {
    
    static String alphabetString;
    static String statesString;
    static char s0;
    static String acceptStatesString;
    static char[] alphabetArr;
    static char[] statesArr;
    static char[] acceptStatesArr;
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Scanner input = new Scanner(System.in);
        
        //getting input from text file
        if (args.length != 0) {
            prepareInputFromTextFile(args[0]);
        } else if (args.length == 0) {
            System.out.println("Please enter the name of the file containing your DFA specifications: ");
            prepareInputFromTextFile(input.nextLine());
        }


        System.out.println("Please enter the first string to be tested: ");
        String string1 = input.nextLine();
        System.out.println("Please enter the second string to be tested: ");
        String string2 = input.nextLine();

        char[] string1Arr = string1.toCharArray();
        char[] string2Arr = string2.toCharArray();

        for(int i = 0; i < string1Arr.length; i++){
            transition(s0, string1Arr[i]);
        }
        if(s0 == acceptStatesArr[0] || s0 == acceptStatesArr[1]){ //this block checks if we end up in an accept state/fail state
            System.out.println("String 1 is in the language");
        }else{
            System.out.println("String 1 is not in the language");
        }
        for(int i = 0; i < string2Arr.length; i++){
            transition(s0, string2Arr[i]);
        }
        if(s0 == acceptStatesArr[0] || s0 == acceptStatesArr[1]){
            System.out.println("String 2 is in the language");
        }else{
            System.out.println("String 2 is not in the language");
        }

        

    }

    //method that takes input from text files and puts it into arrays/variables without commas/brackets
    static void prepareInputFromTextFile(String filename) throws FileNotFoundException, IOException {
        File file = new File(filename);
        Scanner sc = new Scanner(file);
        Scanner input = new Scanner(System.in);

        alphabetString = sc.nextLine();
        statesString = sc.nextLine();
        s0 = sc.nextLine().charAt(0);
        acceptStatesString = sc.nextLine();
        

        alphabetString = alphabetString.replaceAll("[\\{\\}\\,]","");
        statesString = statesString.replaceAll("[\\{\\}\\,]","");
        acceptStatesString = acceptStatesString.replaceAll("[\\{\\}\\,]","");

        alphabetArr = alphabetString.toCharArray();
        statesArr = statesString.toCharArray();
        acceptStatesArr = acceptStatesString.toCharArray();
        
        

        
    }

    public static void transition(char state, char character){ //method that transitions between states based on input
        if(state == 'A'){
            if(character == alphabetArr[0] || character == alphabetArr[1]){
                s0 = 'A';
            }else if(character == alphabetArr[2]){
                s0 = 'C';
            }
        }else if(state == 'B'){
            if(character == alphabetArr[0] || character == alphabetArr[1]){
                s0 = 'D';
            }else if(character == alphabetArr[2]){
                s0 = 'C';
            }
        }else if(state == 'C'){
            if(character == alphabetArr[0] || character == alphabetArr[1] || character == alphabetArr[2]){
                s0 = 'D';
            }
        }else if(state == 'D'){
            if(character == alphabetArr[0]){
                s0 = 'D';
            }else if (character == alphabetArr[1] || character == alphabetArr[2]){
                s0 = 'A';
            }
        }
    }
    
}