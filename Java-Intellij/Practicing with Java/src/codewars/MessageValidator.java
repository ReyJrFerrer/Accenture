package codewars;

import java.util.regex.Pattern;

/**
 * In this kata, you have an input string and you should check whether it is a valid message. To decide that, you need to split the string by the numbers, and then compare the numbers with the number of characters in the following substring.
 *
 * For example "3hey5hello2hi" should be split into 3, hey, 5, hello, 2, hi and the function should return true, because "hey" is 3 characters, "hello" is 5, and "hi" is 2; as the numbers and the character counts match, the result is true.
 *
 * Notes:
 *
 * Messages are composed of only letters and digits
 * Numbers may have multiple digits: e.g. "4code13hellocodewars" is a valid message
 * Every number must match the number of character in the following substring, otherwise the message is invalid: e.g. "hello5" and "2hi2" are invalid
 * If the message is an empty string, you should return true
 */
public class MessageValidator {
    public static void main(String[] args) {
        System.out.println(messageValidator("3four"));

    }

    static boolean messageValidator(String s){
       if (s.isEmpty()) return true;

       int i = 0 ;
       while (i < s.length()){
           if (!Character.isDigit(s.charAt(i))){ return false;}

           int numStart = i;
           while (i < s.length() && Character.isDigit(s.charAt(i))){
               i++;
           }
           int count = Integer.parseInt(s.substring(numStart,i));

           if (i + count > s.length()) return false;

           for (int j = 0 ; j < count; j++){
               if (Character.isDigit(s.charAt(i + j))) return false;
           }
           i += count;
       }
       return true;








    }
    /*Best Practice*/
    private static Pattern MSG_PART = Pattern.compile("(\\d+)(\\D*)");
    public static boolean isAValidMessage(String message) {
        return MSG_PART.matcher(message)
                .replaceAll(g -> Integer.parseInt(g.group(1)) == g.group(2).length() ? "" : "1")
                .isEmpty();
    }

}