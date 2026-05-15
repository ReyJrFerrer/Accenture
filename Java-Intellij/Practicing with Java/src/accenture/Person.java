package com.accenture.constructor.model;

import java.sql.SQLOutput;

public class Person {

    public String name;
    public int age;

    //default constructor
    public Person(){
        System.out.println("Default constructor called");
    }

    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    public void displayInfo(){
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }






}
