package com.accenture.constructor.controller;

import com.accenture.constructor.model.Person;

public class PersonController {
    public static void main(String[] args) {

        //Creating an instance of the Person class using the default constructor
        Person person1 = new Person();
        person1.displayInfo();

        person1.name = "Alice";
        person1.age = 30;

        person1.displayInfo();



        //Creating an instance of the Person class using the parameterized constructor
        Person person2 = new Person("Bob", 25);
        person2.displayInfo();
    }
}
 