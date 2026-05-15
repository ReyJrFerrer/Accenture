package codewars;

import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListsExample {
    public static void main(String[] args) {
//        ArrayList<Integer> list = new ArrayList<>();
//        ArrayList<String> stringList = new ArrayList<>();
//
//        list.add(3);
//        list.add(2);
//        list.add(1);
//
//        stringList.add("Hi");
//        stringList.add("Hello");
//
////        list.remove(0);
//
//        list.set(0,5);
//        Collections.sort(list);
//        Collections.sort(stringList);
//        System.out.println(list.get(2));
//        System.out.println(list);
//        System.out.println(list);
//        System.out.println(stringList);
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> foods = new ArrayList<>();

        System.out.print("Enter the number food you would like: ");
        int numOfFood = scanner.nextInt();

        scanner.nextLine();

        for (int i = 1 ; i <= numOfFood; i++){
            System.out.print("Enter food #" + i + ": ");
            String food = scanner.nextLine();
            foods.add(food);
        }

        System.out.println(foods);
        scanner.close();
    }
}
