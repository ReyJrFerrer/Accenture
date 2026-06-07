package com.example.hellocontroller.model;


public class Greeting {
    private String message;
    private String from;
    private int year;

    public Greeting(String message, String from, int year){
        this.message = message;
        this.from = from;
        this.year = year;
    }

    public String getMessage(){
        return message;
    }
    public String getFrom(){
        return from;
    }
    public int getYear(){
        return year;
    }
}
