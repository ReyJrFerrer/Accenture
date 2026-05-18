# Java OOP Reviewer
## Java Bootcamp Training - Software Engineer Track

---

## 1. Classes & Objects

### Concepts
- **Class**: A blueprint or template for creating objects. It defines state (fields) and behavior (methods).
- **Object**: An instance of a class. It occupies memory and has an actual state.

### Code Example
```java
public class Car {
    // State (Fields)
    String color;
    int speed;

    // Behavior (Methods)
    void accelerate() {
        speed += 10;
        System.out.println("Accelerating. Speed: " + speed);
    }
}

// Instantiation
Car myCar = new Car(); // 'new' allocates memory for the object
myCar.color = "Red";
myCar.accelerate();
```

---

## 2. Encapsulation

### Concepts
- Bundling data (variables) and methods that operate on the data into a single unit (class).
- Restricting direct access to some of an object's components (data hiding) using access modifiers.

### Access Modifiers
| Modifier | Class | Package | Subclass | World |
|----------|-------|---------|----------|-------|
| `public` | Y | Y | Y | Y |
| `protected`| Y | Y | Y | N |
| *default*| Y | Y | N | N |
| `private`| Y | N | N | N |

### Code Example
```java
public class BankAccount {
    // Data hidden from outside
    private double balance;

    // Getter (Read access)
    public double getBalance() {
        return balance;
    }

    // Setter (Write access with validation)
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }
}
```

---

## 3. Constructors

### Concepts
- Special method used to initialize objects.
- Has the **same name as the class** and **no return type** (not even `void`).
- If no constructor is defined, Java provides a default no-argument constructor.

### Code Example
```java
public class Person {
    private String name;
    private int age;

    // Default Constructor
    public Person() {
        this.name = "Unknown";
        this.age = 0;
    }

    // Parameterized Constructor
    public Person(String name, int age) {
        this.name = name; // 'this' resolves ambiguity between instance var and parameter
        this.age = age;
    }
    
    // Copy Constructor (Manual in Java)
    public Person(Person other) {
        this.name = other.name;
        this.age = other.age;
    }
}
```

---

## 4. Inheritance

### Concepts
- Mechanism where one class acquires properties (fields and methods) of another.
- Promotes code reusability.
- Java supports **Single Inheritance** (a class can only extend one class) but a class can implement multiple interfaces.

### Keywords
- `extends`: Used to inherit from a class.
- `super`: Refers to the immediate parent class object/constructor.

### Code Example
```java
// Parent (Superclass)
public class Animal {
    protected String name;
    
    public Animal(String name) {
        this.name = name;
    }
    
    public void eat() {
        System.out.println(name + " is eating.");
    }
}

// Child (Subclass)
public class Dog extends Animal {
    private String breed;
    
    public Dog(String name, String breed) {
        super(name); // Calls parent constructor (MUST be first line)
        this.breed = breed;
    }
    
    public void bark() {
        System.out.println(name + " is barking.");
    }
}
```

---

## 5. Polymorphism

### Concepts
- Ability of an object to take on many forms. "One interface, multiple methods."

### 1. Compile-time Polymorphism (Method Overloading)
Same method name, different parameters in the **same class**.
```java
public class MathUtil {
    public int add(int a, int b) { return a + b; }
    public double add(double a, double b) { return a + b; }
}
```

### 2. Runtime Polymorphism (Method Overriding)
Subclass provides a specific implementation of a method already provided by its parent.
```java
public class Animal {
    public void makeSound() { System.out.println("Generic sound"); }
}

public class Cat extends Animal {
    @Override // Annotation ensures we actually override a parent method
    public void makeSound() { System.out.println("Meow"); }
}

// Dynamic Method Dispatch
Animal myPet = new Cat(); // Upcasting
myPet.makeSound();        // Outputs "Meow" (decided at runtime based on object type)
```

---

## 6. Abstraction

### Concepts
- Hiding implementation details and showing only functionality to the user.

### Abstract Classes
- Declared with `abstract` keyword. Cannot be instantiated.
- Can have both abstract (no body) and concrete methods.
```java
public abstract class Shape {
    protected String color;
    
    public Shape(String color) { this.color = color; }
    
    // Abstract method (must be implemented by subclass)
    public abstract double getArea();
    
    // Concrete method
    public void printColor() { System.out.println(color); }
}
```

### Interfaces
- A contract that classes must follow.
- By default, methods are `public abstract`. Variables are `public static final`.
- From Java 8+, can have `default` and `static` methods.
```java
public interface Drivable {
    int MAX_SPEED = 120; // public static final
    
    void startEngine();  // public abstract
    
    // Default method (Java 8+)
    default void stopEngine() {
        System.out.println("Engine stopped.");
    }
}

public class Car implements Drivable {
    @Override
    public void startEngine() { System.out.println("Vroom!"); }
}
```

---

## 7. Advanced OOP Concepts

### `static` Keyword
Belongs to the class rather than instances.
```java
public class Counter {
    public static int count = 0; // Shared across all instances
    
    public Counter() { count++; }
    
    public static void printCount() {
        System.out.println(count); // Cannot access 'this' or instance vars
    }
}
```

### `final` Keyword
- **Variable**: Cannot be reassigned (constant).
- **Method**: Cannot be overridden.
- **Class**: Cannot be inherited.

### Composition (HAS-A) vs Inheritance (IS-A)
Composition is generally preferred over inheritance to avoid tight coupling.
```java
// Composition example
public class Engine { /* ... */ }

public class Car {
    private Engine engine; // Car HAS-A Engine
    
    public Car() {
        this.engine = new Engine();
    }
}
```

---

## 8. Nested & Inner Classes

### Types
1. **Static Nested Class**: Acts like a top-level class.
2. **Inner Class (Non-static)**: Associated with an instance of the enclosing class.
3. **Local Class**: Defined within a block/method.
4. **Anonymous Class**: Class without a name, declared and instantiated in one expression.

### Code Example
```java
public class Outer {
    private String secret = "hidden";
    
    // Inner Class
    public class Inner {
        public void reveal() {
            System.out.println(secret); // Has access to outer's private members
        }
    }
}

// Anonymous class usage
Runnable r = new Runnable() {
    @Override
    public void run() { System.out.println("Running..."); }
};
```

---

## 9. Enums

### Concepts
- A special "class" that represents a group of constants.
- Type-safe compared to using final integer constants.

### Code Example
```java
public enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

// Enum with fields and methods
public enum Status {
    PENDING(1), ACTIVE(2), INACTIVE(3);
    
    private final int code;
    
    Status(int code) { this.code = code; }
    
    public int getCode() { return code; }
}
```

---

## 10. SOLID Principles

### 1. Single Responsibility Principle (SRP)
A class should have one, and only one, reason to change.
```java
// Bad: Handles logic and persistence
class User { void saveToDB() { /* ... */ } }

// Good: Separated responsibilities
class User { /* User data */ }
class UserRepository { void saveToDB(User user) { /* ... */ } }
```

### 2. Open/Closed Principle (OCP)
Software entities should be open for extension, but closed for modification.
```java
interface Shape { double area(); }

class Rectangle implements Shape {
    double width, height;
    public double area() { return width * height; }
}
class Circle implements Shape {
    double radius;
    public double area() { return Math.PI * radius * radius; }
}

class AreaCalculator {
    // Works for any new Shape added without modifying this code
    double calculateTotal(List<Shape> shapes) {
        return shapes.stream().mapToDouble(Shape::area).sum();
    }
}
```

### 3. Liskov Substitution Principle (LSP)
Objects of a superclass should be replaceable with objects of its subclasses without breaking the application.
```java
// Bad: Ostrich extends Bird but cannot fly (violates LSP if Bird assumes flying)
class Bird { void fly() {} }
class Ostrich extends Bird { void fly() { throw new RuntimeException("Can't fly"); } }

// Good
interface Flyable { void fly(); }
class Sparrow implements Flyable { public void fly() {} }
class Ostrich { void run() {} } // Doesn't implement Flyable
```

### 4. Interface Segregation Principle (ISP)
Clients should not be forced to depend upon interfaces that they do not use.
```java
// Bad: Fat interface
interface Worker { void work(); void eat(); }
class Robot implements Worker { 
    public void work() {} 
    public void eat() { /* Unnecessary for robots */ } 
}

// Good
interface Workable { void work(); }
interface Eatable { void eat(); }
class Robot implements Workable { public void work() {} }
```

### 5. Dependency Inversion Principle (DIP)
Depend upon abstractions, not concretions.
```java
// Bad: High-level depends on low-level detail
class Keyboard { /* ... */ }
class Computer { 
    private Keyboard kb = new Keyboard(); // Tightly coupled
}

// Good
interface InputDevice { /* ... */ }
class Keyboard implements InputDevice { /* ... */ }
class Computer {
    private InputDevice device;
    // Dependency injected
    public Computer(InputDevice device) { this.device = device; }
}
```

---

## 11. Common OOP Design Patterns

### 1. Singleton Pattern
Ensures a class has only one instance and provides a global point of access.
```java
public class DatabaseConnection {
    private static DatabaseConnection instance;
    
    private DatabaseConnection() {} // Private constructor
    
    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
}
```

### 2. Factory Pattern
Creates objects without exposing the instantiation logic to the client.
```java
interface Notification { void notifyUser(); }
class EmailNotification implements Notification { public void notifyUser() {} }
class SMSNotification implements Notification { public void notifyUser() {} }

class NotificationFactory {
    public Notification createNotification(String type) {
        if ("EMAIL".equals(type)) return new EmailNotification();
        if ("SMS".equals(type)) return new SMSNotification();
        return null;
    }
}
```

### 3. Strategy Pattern
Enables selecting an algorithm at runtime.
```java
interface PaymentStrategy { void pay(int amount); }

class CreditCardStrategy implements PaymentStrategy { 
    public void pay(int amount) { System.out.println("Paid with CC"); } 
}
class PayPalStrategy implements PaymentStrategy { 
    public void pay(int amount) { System.out.println("Paid with PayPal"); } 
}

class ShoppingCart {
    public void checkout(int amount, PaymentStrategy strategy) {
        strategy.pay(amount);
    }
}
```

---

## Quick Reference Cheat Sheet

### Overloading vs Overriding
| Feature | Overloading | Overriding |
|---------|-------------|------------|
| Location | Same class | Child class |
| Signature | Must change (params) | Must be exactly the same |
| Return Type | Can change | Must be same (or covariant) |
| Binding | Compile-time (Static) | Run-time (Dynamic) |

### Abstract Class vs Interface
| Feature | Abstract Class | Interface |
|---------|----------------|-----------|
| State (Fields) | Can have instance variables | Only `public static final` |
| Multiple Inheritance| No (`extends` only one) | Yes (`implements` multiple) |
| Constructors | Yes | No |
| When to use | "Is-A" (strong relationship) | "Can-Do" (behavior contract) |

---

*End of Java OOP Reviewer*