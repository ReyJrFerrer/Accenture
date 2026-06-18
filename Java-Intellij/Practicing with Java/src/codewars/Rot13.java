package codewars;


/**
 *
 * ROT13 is a simple letter substitution cipher that replaces a letter with the letter 13 letters after it in the alphabet. ROT13 is an example of the Caesar cipher.
 *
 * Create a function that takes a string and returns the string ciphered with Rot13.
 * If there are numbers or special characters included in the string, they should be returned as they are.
 * Only letters from the latin/english alphabet should be shifted, like in the original Rot13 "implementation".
 */
public class Rot13 {

    public static void main(String[] args) {
        System.out.println(rot13("grfg"));
        System.out.println(rot13("Grfg"));


    }

    public static String rot13(String str) {
        // algorithm

        // declare string builder
        StringBuilder sb = new StringBuilder();
        // for loop
        for (Character c : str.toCharArray()){
            // if statement to check if it's a number or a special number
            if (Character.isLetter(c)){
                // if statement that checks if the letter is uppercase
                if (Character.isUpperCase(c)){
                    // concatenate letter in upper case in upper case
                    char shifted = (char) ('A' + (c - 'A' + 13) % 26);
                    sb.append(shifted);
                }
                else {
                    // concatenate letter in lowercase
                    char shifted = (char) ('a' + (c - 'a' + 13) % 26);
                    sb.append(shifted);
                }
            }
            else {
                // concatenate as they are
                sb.append(c);
            }
        }







        return sb.toString();
    }
}
