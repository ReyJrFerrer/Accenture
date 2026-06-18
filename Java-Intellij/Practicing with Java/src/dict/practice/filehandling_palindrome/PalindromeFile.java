package dict.practice.filehandling_palindrome;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class PalindromeFile {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/dict/practice/filehandling_palindrome/palindrome.txt"));
        // I need to brush up my foundational knowledge on file handlers
        String str = br.readLine();
        System.out.println(isPalindrome(str));
    }

    public static boolean isPalindrome(String str){
        return str.equalsIgnoreCase(new StringBuilder(str).reverse().toString());
    }
}
