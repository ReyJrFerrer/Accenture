package codewars;

/**
 * A CodeForce Problem
 *
 Replace With Alphabet Position

 Welcome.

 In this kata you are required to, given a string, replace every letter with its position in the alphabet.

 If anything in the text isn't a letter, ignore it and don't return it.

 "a" = 1, "b" = 2, etc.
 Example:
 Input = "The sunset sets at twelve o' clock."
 Output = "20 8 5 19 21 14 19 5 20 19 5 20 19 1 20 20 23 5 12 22 5 15 3 12 15 3 11"


 */

public class AlphabetPosition {
    public static void main(String[] args) {

        System.out.println(alphabetPosition("The sunset sets at twelve o' clock."));


    }
    static String alphabetPosition(String text){
        String position;
        char[] alphabetPosition = {'a', 'b', 'c','d', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
                                    'o', 'p', 'q', 'r', 's', 't', 'u' , 'v', 'w' , 'x' ,'y' , 'z' };
        char[] formattedString = text.toLowerCase().toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0 ; i < formattedString.length; i++){
            for (int j = 0 ; j < alphabetPosition.length; j++){
                if (formattedString[i] == alphabetPosition[j]){
                    stringBuilder.append(j + 1);
                    stringBuilder.append(" ");
                }
            }
        }
        position = stringBuilder.toString();







        return position;
    }
}
