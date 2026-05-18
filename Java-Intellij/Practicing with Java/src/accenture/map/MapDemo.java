package accenture.map;

import java.util.HashMap;
import java.util.Map;

public class MapDemo {
    public static void main(String[] args) {
        Map<Integer, String> inventors = new HashMap<>();

        inventors.put(1, "James Gosling");
        inventors.put(2, "Charles Babbage");
        inventors.put(3, "Herman Hollerith");
        inventors.put(4, "Ada Byron");
        inventors.put(5, "Dennis Ritchie");



        System.out.println("Total number of entries " + inventors.size());
        System.out.println("Entries " + inventors);

        for (int key : inventors.keySet()){
            System.out.print("\n" + key);
            System.out.print(". " + inventors.get(key));
        }

        inventors.clear();

        System.out.println("\nTotal number of entries " + inventors.size());

    }
}
