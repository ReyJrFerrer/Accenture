package pluralsight.streams;

import java.util.Comparator;

public class Product implements Comparator<Product> {

    private int id;
    private String name;
    private int weight;

    public Product(){

    }
    public Product(int id, String name, int weight){
        this.id = id;
        this.name = name;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public int comparingInt(Product o1, Product o2) {
        return 0;
    }

    @Override
    public int compare(Product o1, Product o2) {
        return 0;
    }

    @Override
    public String toString(){

        return "id= " + id + " name= " + name + " weight= "+ weight ;
    }
}
