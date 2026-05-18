package codewars;


/**
 *
 * You are going to be given a non-empty string. Your job is to return the middle character(s) of the string.
 *
 * If the string's length is odd, return the middle character.
 * If the string's length is even, return the middle 2 characters.
 * Examples:
 * "test" --> "es"
 * "testing" --> "t"
 * "middle" --> "dd"
 * "A" --> "A"
 *
 *
 * */

public class GetMiddleCharacter {
    public static void main(String[] args) {
        System.out.println(getMiddle("A"));
    }
    public static String getMiddle(String word){

        int length = word.length();

        if (length % 2 == 1){
            return word.substring(length / 2, length / 2 + 1);

        } else {
            return word.substring(length / 2 - 1, length / 2 + 1);
        }

    }
}
