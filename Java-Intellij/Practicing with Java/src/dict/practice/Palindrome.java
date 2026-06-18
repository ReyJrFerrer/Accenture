package dict.practice;

/**
 * A Codewars problem
 * 8 kyu
 * Is it a palindrome?
 * Collect|
 * Description:
 *
 * Write a function that checks if a given string (case insensitive) is a palindrome.
 *
 * A palindrome is a word, number,
 * phrase, or other sequence of symbols that reads the same backwards as forwards,
 * such as madam or racecar.
 */

public class Palindrome {

    public static void main(String[] args) {

    }

    public static boolean isPalindrome(String str) {
        return str.equalsIgnoreCase(new StringBuilder(str).reverse().toString());
    }
}
