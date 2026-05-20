package codewars;

public class DoubleThis {
    public static void main(String[] args) {
        System.out.println(doubleInteger(1));
    }
    public static int doubleInteger(int i) {
        // Double the integer and return it!
        return (int) Math.pow(i, 2);
    }
}
