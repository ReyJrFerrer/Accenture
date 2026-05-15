package codewars;

/**
 *
 * An isogram is a word that has no repeating letters, consecutive or non-consecutive.
 * Implement a function that determines whether a string that contains only letters is an isogram.
 * Assume the empty string is an isogram. Ignore letter case.
 *
 * Example: (Input --> Output)
 *
 * "Dermatoglyphics" --> true
 * "aba" --> false
 * "moOse" --> false (ignore letter case)
 */

public class Isogram {
    public static void main(String[] args) {
        System.out.println(isIsogram("aba"));

    }

    static boolean isIsogram(String s){
        boolean isogram = true;
        // intuition - matrix array
        char[] stringToArray = s.toLowerCase().toCharArray();

        for (int i = 0 ; i < stringToArray.length; i++){
            for (int j = i + 1 ; j < stringToArray.length; j++){
                if (stringToArray[i] == stringToArray[j]){
                    // found isogram
                    isogram = false;
                }
            }

        }
        return isogram;



    }
}
