# Creational Design Patterns Manual
## Java Design Patterns - Software Engineer Track

---

## 1. Singleton Pattern

### Overview
Ensures a class has only one instance with global access point.

### When to Use
- Database connections
- Logger configuration
- Thread pools
- Configuration managers

### Implementation Variants

#### Eager Initialization
```java
public class EagerSingleton {
    private static final EagerSingleton instance = new EagerSingleton();
    
    private EagerSingleton() {}
    
    public static EagerSingleton getInstance() {
        return instance;
    }
}
```

#### Lazy Initialization (Thread-Unsafe)
```java
public class LazySingleton {
    private static LazySingleton instance;
    
    private LazySingleton() {}
    
    public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
```

#### Double-Checked Locking (Recommended)
```java
public class DoubleCheckedSingleton {
    private static volatile DoubleCheckedSingleton instance;
    
    private DoubleCheckedSingleton() {}
    
    public static DoubleCheckedSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckedSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckedSingleton();
                }
            }
        }
        return instance;
    }
}
```

#### Bill Pugh (Initialization-on-Demand Holder)
```java
public class BillPughSingleton {
    private BillPughSingleton() {}
    
    private static class SingletonHolder {
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }
    
    public static BillPughSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
```

### Real-World Example
```java
public class DatabaseConnection {
    private static volatile DatabaseConnection instance;
    private Connection connection;
    
    private DatabaseConnection() {
        try {
            connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/mydb", "root", "password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }
    
    public Connection getConnection() {
        return connection;
    }
}
```

---

## 2. Builder Pattern

### Overview
Separates complex object construction from representation, allowing different representations.

### When to Use
- Objects with many optional parameters
- Immutable objects
- Complex object creation logic

### Implementation
```java
// Product Class
public class Computer {
    private String cpu;
    private String ram;
    private String storage;
    private String gpu;
    
    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
        this.gpu = builder.gpu;
    }
    
    public String getCpu() { return cpu; }
    public String getRam() { return ram; }
    public String getStorage() { return storage; }
    public String getGpu() { return gpu; }
    
    @Override
    public String toString() {
        return "Computer{CPU=" + cpu + ", RAM=" + ram + 
               ", Storage=" + storage + ", GPU=" + gpu + "}";
    }
    
    // Builder Class
    public static class Builder {
        private String cpu;
        private String ram;
        private String storage;
        private String gpu;
        
        public Builder cpu(String cpu) {
            this.cpu = cpu;
            return this;
        }
        
        public Builder ram(String ram) {
            this.ram = ram;
            return this;
        }
        
        public Builder storage(String storage) {
            this.storage = storage;
            return this;
        }
        
        public Builder gpu(String gpu) {
            this.gpu = gpu;
            return this;
        }
        
        public Computer build() {
            return new Computer(this);
        }
    }
}

// Usage
Computer computer = new Computer.Builder()
    .cpu("Intel i7")
    .ram("16GB")
    .storage("512GB SSD")
    .gpu("NVIDIA RTX 3080")
    .build();
```

### Real-World Example: User Object
```java
public class User {
    private final String username;
    private final String email;
    private final String firstName;
    private final String lastName;
    private final int age;
    private final String phone;
    
    private User(UserBuilder builder) {
        this.username = builder.username;
        this.email = builder.email;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.phone = builder.phone;
    }
    
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public int getAge() { return age; }
    public String getPhone() { return phone; }
    
    public static class UserBuilder {
        private String username;
        private String email;
        private String firstName;
        private String lastName;
        private int age;
        private String phone;
        
        public UserBuilder(String username, String email) {
            this.username = username;
            this.email = email;
        }
        
        public UserBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }
        
        public UserBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }
        
        public UserBuilder age(int age) {
            this.age = age;
            return this;
        }
        
        public UserBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }
        
        public User build() {
            return new User(this);
        }
    }
}

// Usage
User user = new User.UserBuilder("johndoe", "john@example.com")
    .firstName("John")
    .lastName("Doe")
    .age(30)
    .phone("+1234567890")
    .build();
```

---

## 3. Prototype Pattern

### Overview
Creates new objects by cloning an existing prototype, avoiding costly object creation.

### When to Use
- Object creation is expensive (database, network)
- Creating objects similar to existing ones
- Avoiding subclassing for object creation

### Implementation
```java
// Prototype Interface
public interface Prototype<T> {
    T clone();
}

// Concrete Prototype
public class Person implements Prototype<Person> {
    private String name;
    private int age;
    private Address address;
    
    public Person(String name, int age, Address address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }
    
    @Override
    public Person clone() {
        return new Person(this.name, this.age, new Address(
            this.address.getStreet(), 
            this.address.getCity()
        ));
    }
    
    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }
}

public class Address {
    private String street;
    private String city;
    
    public Address(String street, String city) {
        this.street = street;
        this.city = city;
    }
    
    public String getStreet() { return street; }
    public String getCity() { return city; }
}
```

### Prototype Manager
```java
public class PrototypeManager {
    private Map<String, Prototype> prototypes = new HashMap<>();
    
    public void register(String key, Prototype prototype) {
        prototypes.put(key, prototype);
    }
    
    public Prototype get(String key) {
        Prototype prototype = prototypes.get(key);
        if (prototype != null) {
            return prototype.clone();
        }
        return null;
    }
}
```

### Shape Example
```java
public class Shape implements Prototype<Shape> {
    protected String color;
    protected int x;
    protected int y;
    
    public Shape() {}
    
    public Shape(Shape target) {
        if (target != null) {
            this.color = target.color;
            this.x = target.x;
            this.y = target.y;
        }
    }
    
    @Override
    public Shape clone() {
        return new Shape(this);
    }
}

public class Rectangle extends Shape {
    private int width;
    private int height;
    
    public Rectangle() {}
    
    public Rectangle(Rectangle target) {
        super(target);
        if (target != null) {
            this.width = target.width;
            this.height = target.height;
        }
    }
    
    @Override
    public Rectangle clone() {
        return new Rectangle(this);
    }
}

// Usage
Rectangle original = new Rectangle();
original.setColor("Red");
original.setX(10);
original.setY(20);
original.setWidth(100);
original.setHeight(50);

Rectangle cloned = original.clone();
cloned.setColor("Blue");
```

---

## 4. Factory Pattern

### Overview
Defines an interface for creating objects but lets subclasses decide which class to instantiate.

### When to Use
- Object creation requires complex logic
- Creating different subtypes of a common interface
- Decoupling client code from concrete implementations

### Simple Factory
```java
// Product Interface
public interface Payment {
    void pay(double amount);
}

// Concrete Products
public class CreditCardPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " via Credit Card");
    }
}

public class PayPalPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " via PayPal");
    }
}

public class CryptoPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " via Cryptocurrency");
    }
}

// Factory
public class PaymentFactory {
    public static Payment createPayment(String type) {
        switch (type.toLowerCase()) {
            case "credit":
                return new CreditCardPayment();
            case "paypal":
                return new PayPalPayment();
            case "crypto":
                return new CryptoPayment();
            default:
                throw new IllegalArgumentException("Unknown payment type: " + type);
        }
    }
}

// Usage
Payment payment = PaymentFactory.createPayment("credit");
payment.pay(100.0);
```

### Factory Method
```java
// Product
public interface Notification {
    void send(String message);
}

// Concrete Products
public class EmailNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("Sending Email: " + message);
    }
}

public class SMSNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("Sending SMS: " + message);
    }
}

public class PushNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("Sending Push: " + message);
    }
}

// Creator (Abstract Factory Method)
public abstract class NotificationFactory {
    public abstract Notification createNotification();
    
    public void notifyUser(String message) {
        Notification notification = createNotification();
        notification.send(message);
    }
}

// Concrete Creators
public class EmailNotificationFactory extends NotificationFactory {
    @Override
    public Notification createNotification() {
        return new EmailNotification();
    }
}

public class SMSNotificationFactory extends NotificationFactory {
    @Override
    public Notification createNotification() {
        return new SMSNotification();
    }
}

// Usage
NotificationFactory emailFactory = new EmailNotificationFactory();
emailFactory.notifyUser("Hello via Email!");
```

---

## 5. Abstract Factory Pattern

### Overview
Provides an interface for creating families of related objects without specifying concrete classes.

### When to Use
- Creating multiple related objects that must be used together
- Supporting multiple platforms/themes
- Decoupling client from implementation details

### Implementation
```java
// Abstract Products
public interface Button {
    void render();
}

public interface TextField {
    void render();
}

public interface Checkbox {
    void render();
}

// Windows Family
public class WindowsButton implements Button {
    @Override
    public void render() {
        System.out.println("Rendering Windows Button");
    }
}

public class WindowsTextField implements TextField {
    @Override
    public void render() {
        System.out.println("Rendering Windows TextField");
    }
}

public class WindowsCheckbox implements Checkbox {
    @Override
    public void render() {
        System.out.println("Rendering Windows Checkbox");
    }
}

// Mac Family
public class MacButton implements Button {
    @Override
    public void render() {
        System.out.println("Rendering Mac Button");
    }
}

public class MacTextField implements TextField {
    @Override
    public void render() {
        System.out.println("Rendering Mac TextField");
    }
}

public class MacCheckbox implements Checkbox {
    @Override
    public void render() {
        System.out.println("Rendering Mac Checkbox");
    }
}

// Abstract Factory Interface
public interface UIFactory {
    Button createButton();
    TextField createTextField();
    Checkbox createCheckbox();
}

// Concrete Factories
public class WindowsUIFactory implements UIFactory {
    @Override
    public Button createButton() { return new WindowsButton(); }
    @Override
    public TextField createTextField() { return new WindowsTextField(); }
    @Override
    public Checkbox createCheckbox() { return new WindowsCheckbox(); }
}

public class MacUIFactory implements UIFactory {
    @Override
    public Button createButton() { return new MacButton(); }
    @Override
    public TextField createTextField() { return new MacTextField(); }
    @Override
    public Checkbox createCheckbox() { return new MacCheckbox(); }
}

// Client Code
public class Application {
    private Button button;
    private TextField textField;
    private Checkbox checkbox;
    
    public Application(UIFactory factory) {
        this.button = factory.createButton();
        this.textField = factory.createTextField();
        this.checkbox = factory.createCheckbox();
    }
    
    public void render() {
        button.render();
        textField.render();
        checkbox.render();
    }
}

// Usage
UIFactory windowsFactory = new WindowsUIFactory();
Application windowsApp = new Application(windowsFactory);
windowsApp.render();

UIFactory macFactory = new MacUIFactory();
Application macApp = new Application(macFactory);
macApp.render();
```

### Real-World Example: Database Abstraction
```java
// Connection and Command as Abstract Products
public interface Connection {
    void connect();
    void disconnect();
}

public interface Command {
    void execute(String sql);
}

// MySQL Family
public class MySqlConnection implements Connection {
    @Override
    public void connect() { System.out.println("Connecting to MySQL"); }
    @Override
    public void disconnect() { System.out.println("Disconnecting from MySQL"); }
}

public class MySqlCommand implements Command {
    @Override
    public void execute(String sql) { System.out.println("Executing MySQL: " + sql); }
}

// PostgreSQL Family
public class PostgreSqlConnection implements Connection {
    @Override
    public void connect() { System.out.println("Connecting to PostgreSQL"); }
    @Override
    public void disconnect() { System.out.println("Disconnecting from PostgreSQL"); }
}

public class PostgreSqlCommand implements Command {
    @Override
    public void execute(String sql) { System.out.println("Executing PostgreSQL: " + sql); }
}

// Abstract Factory
public interface DatabaseFactory {
    Connection createConnection();
    Command createCommand();
}

// Concrete Factories
public class MySqlFactory implements DatabaseFactory {
    @Override
    public Connection createConnection() { return new MySqlConnection(); }
    @Override
    public Command createCommand() { return new MySqlCommand(); }
}

public class PostgreSqlFactory implements DatabaseFactory {
    @Override
    public Connection createConnection() { return new PostgreSqlConnection(); }
    @Override
    public Command createCommand() { return new PostgreSqlCommand(); }
}
```

---

## Quick Reference Cheat Sheet

### Pattern Summary
| Pattern | Purpose | Complexity |
|---------|---------|-------------|
| **Singleton** | One instance | Low |
| **Builder** | Complex object construction | Medium |
| **Prototype** | Clone existing objects | Medium |
| **Factory** | Create objects via interface | Medium |
| **Abstract Factory** | Create related objects | High |

### When to Use Which
```
Need exactly one instance?                → Singleton
Object has many optional fields?         → Builder
Object creation is expensive?            → Prototype
Class doesn't know which subclass?       → Factory
Need families of related objects?        → Abstract Factory
```

### Common Use Cases
- **Singleton**: DB connection, logger, config, thread pool
- **Builder**: Immutable objects, complex DTOs, objects with 4+ fields
- **Prototype**: Caching, undo functionality, expensive object creation
- **Factory**: Payment processors, notification systems, document generators
- **Abstract Factory**: UI themes, database drivers, cross-platform components

---

*End of Creational Design Patterns Manual*