package pluralsight.streams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;

public class EnterTheCollector {
    public static void main(String[] args) {
        Product door = new Product(1, "Wooden Door", 35);
        Product floorPanel = new Product(2, "Floor Panel", 25);
        Product window = new Product(3, "Glass Window", 10);

        List<Product> products = new ArrayList<>();
        products.add(door);
        products.add(floorPanel);
        products.add(window);
        products.add(floorPanel);
        products.add(window);
        products.add(floorPanel);
        
//        List<Product> lightProducts = products
//                .stream()
//                .filter(product -> product.getWeight() < 30)
//                .sorted(Comparator.comparingInt(Product::getWeight))
//                .toList();
//
//        System.out.println(lightProducts);

//                .forEach(System.out::println);

//        Map<String, List<Product>> lightProducts = products
//                .stream()
//                .filter(product -> product.getWeight() < 30)
//                .sorted(Comparator.comparingInt(Product::getWeight))
//                .collect(Collectors.groupingBy(Product::getName));

        Map<String,Long> lightProducts = products
                .stream()
                .filter(product -> product.getWeight() < 30)
                .sorted(Comparator.comparingInt(Product::getWeight))
                .collect(Collectors.groupingBy(Product::getName, counting()));



        System.out.println(lightProducts);
    }
}
