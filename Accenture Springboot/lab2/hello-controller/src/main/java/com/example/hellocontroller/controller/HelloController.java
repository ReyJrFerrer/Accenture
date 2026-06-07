package com.example.hellocontroller.controller;

import com.example.hellocontroller.model.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello World!";
    }
    @GetMapping("/hello/{name}")
    public String sayHelloTo(@PathVariable String name){
        return "Hello, " + name + "!";
    }
    @GetMapping("/greet")
    public String greet(@RequestParam(defaultValue = "World") String name){
        return "Greetings, " + name + "!";
    }

    @GetMapping("/api/greeting")
    public Greeting greeting(){
        return new Greeting("Hello, World!", "Spring Boot", 2026);
    }


}
