public class BinaryGap {

    static int[] decimalToBinary(int n) {
        int[] binary = new int[32];
        int id = 0;
        while (n > 0) {
            binary[id++] = n % 2;
            n = n / 2;
        }
        return binary;
    }

    static int binaryGap(int n) {
        int[] binaryArray = decimalToBinary(n);

        int maxGap = 0;
        int currentGap = 0;
        boolean counting = false;

        for (int i = 0; i < binaryArray.length; i++) {
            if (binaryArray[i] == 1) {
                if (counting) {
                    if (currentGap > maxGap) {
                        maxGap = currentGap;
                    }
                }
                counting = true;
                currentGap = 0;
            } else {
                if (counting) {
                    currentGap++;
                }
            }
        }
        return maxGap;
    }

    public static void main(String[] args) {
        System.out.println(binaryGap(1041));
    }
}