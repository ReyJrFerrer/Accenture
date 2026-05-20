package accenture.iostreams;

public class Student {
    private String name;
    public Student(String name, int age){
        this.name = name;
        this.age = age;
    }
    public Student (){

    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getName() {
        return name;
    }
    private int age;
    public void setName(String s) {
    }
    @Override
    public String toString(){
        return name + " " + age;
    }
}
