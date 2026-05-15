package accenture.demofinal.controller;

import accenture.demofinal.model.Child;
import accenture.demofinal.model.Parent;

public class DemoFinalKeyword {
    public static void main(String[] args) {
        final String message;

        Child myChildObject = new Child();
        Parent myParentObject = new Parent();
        System.out.println(myChildObject);

        System.out.println(new Child("Hi, I am a message from the anonymous class"));

        System.out.println(Parent.parentNum);



        message = "I hate coding";
        System.out.println(message);

    }
}
