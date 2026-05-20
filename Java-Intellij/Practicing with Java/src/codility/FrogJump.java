package codility;

/**
 *
 * a frog located at position x wants to get to position y. The frog jumps at a fixed distance of d
 *
 * count the minimum number of jumps that the frog must perform to reach its target
 *
 * assumptions:
 * x is always <= y
 */
public class FrogJump {

    public static void main(String[] args) {

        System.out.println(solution(10,85, 30));

    }

    public static int solution(int X, int Y, int D){

//        int count = 0 ;
//        while (X < Y){
//            X += D;
//            count++;
//
//        }
//        return count;

        int distance = Y - X;
        return (distance + D - 1) / D;
    }
}
