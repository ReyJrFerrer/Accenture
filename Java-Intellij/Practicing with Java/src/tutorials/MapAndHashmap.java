package tutorials;


import java.util.HashMap;

/**
 * A map is just a set of key values pairs
 */
public class MapAndHashmap {
    public static void main(String[] args) {
        HashMap<String, Integer> empIds = new HashMap<>();

        empIds.put("John", 12345);
        empIds.put("Carl", 54321);
        empIds.put("Jerry", 867509);


        System.out.println(empIds);
        System.out.println(empIds.get("Carl"));
        System.out.println(empIds.containsKey("John"));
        System.out.println(empIds.containsValue(12345));
        empIds.replace("John", 777);
        System.out.println(empIds);
        empIds.replace("Carl", 54321);
        System.out.println(empIds);


    }
}
