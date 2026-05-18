package accenture.switchingalgorithm;

import java.util.*;
public class LinkedListToVector {
    public static void main(String[] args) {
        List<String> schoolList = new ArrayList<>(5); // upcasting
        schoolList = new LinkedList<>();
        schoolList = new Vector<>();
        schoolList.add("University of Batangas");
        schoolList.add("STI College");
        schoolList.add("iAcademy");
        schoolList.add("Mindanao State University");
        schoolList.add("Emilio Aguinaldo Academy College");
        schoolList.add("Mindanao State University");


        System.out.println("Number of elements " + schoolList.size());


        System.out.println(schoolList);
        printListElements(schoolList);
        System.out.println("New list with distinct elements");


        Iterator<String> iterator = schoolList.iterator();
        printListElements(iterator);
    }
    public static void printListElements(List<String> school){
        for (String str : school){System.out.println(str);}

    }
    public static  void printListElements(Iterator<String> iterator){
        System.out.println("\n List of school using iterator");
        int counter = 1;

        while (iterator.hasNext()){
            System.out.println(counter + " " + iterator.next());
            counter++;
        }
    }
}

