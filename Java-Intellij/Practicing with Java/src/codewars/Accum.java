package codewars;

/**
 * This time no story, no theory. The examples below show you how to write function accum:
 *
 * Examples:
 * accum("abcd") -> "A-Bb-Ccc-Dddd"
 * accum("RqaEzty") -> "R-Qq-Aaa-Eeee-Zzzzz-Tttttt-Yyyyyyy"
 * accum("cwAt") -> "C-Ww-Aaa-Tttt"
 * The parameter of accum is a string which includes only letters from a..z and A..Z.
 */
public class Accum {

    public static void main(String[] args) {
        System.out.println(accum("ab"));

    }
    public static String accum(String s){
        StringBuilder sb = new StringBuilder();
        char[] charArray = s.toLowerCase().toCharArray();

        int charIndex = 0;
        for (int i = 0 ; i < charArray.length; i++){
            for (int j = 0 ; j <= charIndex; j++){
                if (j == 0){
                    String bucket = String.valueOf(charArray[i]);
                    sb.append(bucket.toUpperCase());
                }else {
                    sb.append(charArray[i]);
                }
            }
            if (charIndex < charArray.length - 1){
                sb.append('-');
            }
            charIndex++;
        }
        return sb.toString();
    }
}
