package codility;

import java.util.HashMap;

/**
 * Given N numbers, output the string based on the alphabets that matches the length of the N
 * All letters in the output should have the same number of count of the letters.
 *
 * Example: N = 30
 * aabbcc... until o, reaching 30
 */
public class Letters {
    public static void main(String[] args) {
        System.out.println(solution(30));

    }
    public static String solution(int N){
        char[] alphabetPosition = {'a', 'b', 'c','d', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
                'o', 'p', 'q', 'r', 's', 't', 'u' , 'v', 'w' , 'x' ,'y' , 'z' };
        StringBuilder sb = new StringBuilder();

        int numLetters = 1;
        for (int i = Math.min(N, 26); i >= 1; i--){
            if (N % i == 0 ){
                numLetters = i;
                break;
            }
        }
        int countPerLetter = N / numLetters;
        for (int i = 0 ; i < numLetters; i++){
            for (int j = 0 ; j < countPerLetter; j++){
                sb.append(alphabetPosition[i]);
            }
        }

        return sb.toString();


    }
}
