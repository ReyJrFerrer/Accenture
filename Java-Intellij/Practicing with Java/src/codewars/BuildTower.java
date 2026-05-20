package codewars;


import java.util.Arrays;
import java.util.List;

/**
 *
 * Build Tower
 * Build a pyramid-shaped tower, as an array/list of strings, given a positive integer number of floors. A tower block is represented with "*" character.
 *
 * For example, a tower with 3 floors looks like this:
 * [
 *   "  *  ",
 *   " *** ",
 *   "*****"
 * ]
 */
public class BuildTower {
    public static void main(String[] args) {
        String[] tower = towerBuilder(3);
        for (String floor : tower){
            System.out.print("[" + floor +"],");
        }

        List<String> stringList = Arrays.stream(tower).toList();
        System.out.println(stringList);
    }

    public static String[] towerBuilder(int nFloor){

        String[] tower = new String[nFloor];

        for (int i = 1 ; i <= nFloor ; i++){
            StringBuilder sb = new StringBuilder();
            for (int j = nFloor; j > i; j--){
                sb.append(" ");

            }
            for (int k = 1 ; k <= i; k++){
                sb.append("*");

            }
            for (int k = 2 ; k <= i; k++){
                sb.append("*");
            }
            for (int j = nFloor; j > i; j--){
                sb.append(" ");
            }
            tower[ i - 1] = sb.toString();

        }
        return tower;

    }
}
