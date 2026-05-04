/**
 *
 * Implement a function that adds two numbers together and returns their sum in binary. The conversion can be done before, or after the addition.
 *
 * The binary number returned should be a string.
 *
 * Examples:(Input1, Input2 --> Output (explanation)))
 *
 * 1, 1 --> "10" (1 + 1 = 2 in decimal or 10 in binary)
 * 5, 9 --> "1110" (5 + 9 = 14 in decimal or 1110 in binary)
 */

public class BinaryAddition {
    public static void main(String[] args) {
        System.out.println(decimalSumToBinary(5 + 9));
    }

    static String decimalSumToBinary(int sum){
        StringBuilder sb = new StringBuilder();

        while (sum > 0){
            sb.append(sum % 2);
            sum = sum / 2;
        }

        return sb.reverse().toString();
    }
}
