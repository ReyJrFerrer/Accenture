package accenture.javacollectionframework;

import java.util.ArrayList;
import java.util.Iterator;

public class DemoArrayListAndIterator {
    public static void main(String[] args) {
        ArrayList schoolList = new ArrayList<>(5);
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


        Iterator iterator = schoolList.iterator();
        printListElements(iterator);
    }
    public static void printListElements(ArrayList school){
        for (Object str : school){System.out.println(str);}

    }
    public static void printListElements(Iterator iterator){
        System.out.println("\n List of school using iterator");
        int counter = 1;

        while (iterator.hasNext()){
            Object obj = iterator.next();
            if(obj instanceof String){
                System.out.println(counter + " " + obj);
            }
            counter++;


        }
    }

}
