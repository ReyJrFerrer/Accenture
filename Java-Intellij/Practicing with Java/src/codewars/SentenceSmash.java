package codewars;


/**
 *
 * Sentence Smash
 *
 * Write a function that takes an array of words and smashes them together into a sentence and returns the sentence.
 * You can ignore any need to sanitize words or add punctuation, but you should add spaces between each word.
 * Be careful, there shouldn't be a space at the beginning or the end of the sentence!
 * Example
 *
 * ['hello', 'world', 'this', 'is', 'great']  =>  'hello world this is great'
 *
 * Assumptions
 *
 *     You can assume that you are only given words.
 *     You cannot assume the size of the array.
 *     You can assume that you do get an array.
 *
 * What We're Testing
 *
 * We're testing basic loops and string manipulation. This is for beginners who are just learning loops and string manipulation.
 */
public class SentenceSmash {

    public static void main(String[] args) {
        String[] words = {"hello", "world", "this", "is", "great"};
        System.out.println(sentenceSmash(words));
    }

    public static String sentenceSmash(String[] words){
        StringBuilder sb = new StringBuilder();

        for (String word : words){
            sb.append(word).append(" ");
        }

        return sb.toString().trim();
    }
}
