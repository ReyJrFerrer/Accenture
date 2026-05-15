package codewars;

/**
 *
 * We need a function that can transform a number (integer) into a string.
 *
 * What ways of achieving this do you know?
 *
 * Examples (input --> output):
 * 123  --> "123"
 * 999  --> "999"
 * -100 --> "-100"
 */

public class ConvertNumToString {
    public static void main(String[] args) {

        System.out.println(numConversion(123));

    }

    static String numConversion(int n){
//        StringBuilder sb = new StringBuilder();
//        sb.append(n);
//
//        return sb.toString();
        return Integer.toString(n);
    }
}
