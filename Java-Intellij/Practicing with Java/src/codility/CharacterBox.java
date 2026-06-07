package codility;

import java.util.Arrays;
public class CharacterBox {
    public static void main(String[] args) {
//        solution(new int[]{1,2,30,100}, 3);
        solution(new int[]{4, 35, 80, 123, 12345, 44, 8, 5}, 10);

    }
    public static void solution(int[]A, int K){
        int max = Arrays.stream(A).max().getAsInt();
        int characterWidth = String.valueOf(max).length();

        System.out.println(buildBorder(Math.min(K, A.length ), characterWidth));
        for (int i = 0 ; i < A.length; i+=K){

            int colsInRow = Math.min(K, A.length - i);

            for (int j = 0 ; j < colsInRow; j++){
                System.out.print("|" + formatCell(A[i + j], characterWidth));
            }
            System.out.println("|");
            System.out.println(buildBorder(colsInRow, characterWidth));

        }

    }
    public static String buildBorder(int numCols, int cellWidth){
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < numCols; i++){
            sb.append("+").append("-".repeat(cellWidth));
        }
        sb.append("+");
        return sb.toString();
    }
    public static String formatCell(int num, int cellWidth){
        return String.format("%" + cellWidth + "d", num);
    }
}
