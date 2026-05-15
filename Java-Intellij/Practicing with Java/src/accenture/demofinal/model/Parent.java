package accenture.demofinal.model;

public class Parent {
    public static int parentNum = 5;
    public Parent(){
        System.out.println("I am a parent class constructor");

    }
    public Parent(String message){
        System.out.println("This is a message: " + message);
        // this targets the real instance of the variable, allowing it to be changed throughout
        // See the DemoFinalKeyword
        this.parentNum = 100;
    }
    public void message(){
        System.out.println("I am a Parent class message");

    }
}
