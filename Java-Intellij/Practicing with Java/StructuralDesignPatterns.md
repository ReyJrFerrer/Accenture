# Structural Design Patterns Manual
## Java Design Patterns - Software Engineer Track

---

## 1. Adapter Pattern

### Overview
Converts the interface of a class into another interface that clients expect, enabling incompatible interfaces to work together.

### When to Use
- Integrating legacy code with new systems
- Working with third-party libraries
- Creating reusable components
- Converting data formats

### Implementation

#### Class Adapter (Extends)
```java
// Target Interface (what client expects)
public interface MediaPlayer {
    void play(String filename);
}

// Adaptee (existing incompatible class)
public class AdvancedMediaPlayer {
    public void playMp4(String filename) {
        System.out.println("Playing MP4: " + filename);
    }
    
    public void playVlc(String filename) {
        System.out.println("Playing VLC: " + filename);
    }
}

// Adapter (extends Adaptee, implements Target)
public class MediaAdapter extends AdvancedMediaPlayer implements MediaPlayer {
    @Override
    public void play(String filename) {
        if (filename.endsWith(".mp4")) {
            playMp4(filename);
        } else if (filename.endsWith(".vlc")) {
            playVlc(filename);
        }
    }
}

// Client Code
public class AudioPlayer implements MediaPlayer {
    private MediaAdapter adapter;
    
    @Override
    public void play(String filename) {
        if (filename.endsWith(".mp3")) {
            System.out.println("Playing MP3: " + filename);
        } else if (filename.endsWith(".mp4") || filename.endsWith(".vlc")) {
            adapter = new MediaAdapter();
            adapter.play(filename);
        } else {
            System.out.println("Unsupported format: " + filename);
        }
    }
}
```

#### Object Adapter (Composition)
```java
// Adapter (contains Adaptee, implements Target)
public class MediaAdapterObject implements MediaPlayer {
    private AdvancedMediaPlayer advancedPlayer;
    
    public MediaAdapterObject() {
        this.advancedPlayer = new AdvancedMediaPlayer();
    }
    
    @Override
    public void play(String filename) {
        if (filename.endsWith(".mp4")) {
            advancedPlayer.playMp4(filename);
        } else if (filename.endsWith(".vlc")) {
            advancedPlayer.playVlc(filename);
        }
    }
}
```

### Real-World Example: Payment Processing
```java
// Target Interface
public interface PaymentProcessor {
    void processPayment(double amount);
    void refund(double amount);
}

// External Legacy Payment System (Adaptee)
public class LegacyPaymentGateway {
    public void makePayment(String paymentData) {
        System.out.println("Legacy payment: " + paymentData);
    }
    
    public void reversePayment(String transactionId) {
        System.out.println("Legacy refund: " + transactionId);
    }
    
    public String generateTransactionId() {
        return "TXN-" + System.currentTimeMillis();
    }
}

// Adapter
public class PaymentAdapter implements PaymentProcessor {
    private LegacyPaymentGateway legacyGateway;
    
    public PaymentAdapter() {
        this.legacyGateway = new LegacyPaymentGateway();
    }
    
    @Override
    public void processPayment(double amount) {
        String txnId = legacyGateway.generateTransactionId();
        legacyGateway.makePayment("Amount: " + amount + ", TxnID: " + txnId);
    }
    
    @Override
    public void refund(double amount) {
        String txnId = legacyGateway.generateTransactionId();
        legacyGateway.reversePayment(txnId + " (Refund: " + amount + ")");
    }
}

// Usage
PaymentProcessor processor = new PaymentAdapter();
processor.processPayment(99.99);
processor.refund(49.99);
```

### Two-Way Adapter
```java
// Allows bidirectional communication
public class TwoWayAdapter implements FirstInterface, SecondInterface {
    private FirstObject firstObject;
    private SecondObject secondObject;
    
    public TwoWayAdapter(FirstObject first) {
        this.firstObject = first;
    }
    
    public TwoWayAdapter(SecondObject second) {
        this.secondObject = second;
    }
    
    @Override
    public void methodA() {
        if (secondObject != null) {
            secondObject.methodB();
        }
    }
    
    @Override
    public void methodB() {
        if (firstObject != null) {
            firstObject.methodA();
        }
    }
}
```

---

## 2. Bridge Pattern

### Overview
Decouples an abstraction from its implementation so that both can vary independently.

### When to Use
- Cross-platform applications
- Plugin systems
- UI frameworks with multiple themes
- Device drivers

### Implementation
```java
// Implementor Interface
public interface Renderer {
    void renderCircle(float x, float y, float radius);
    void renderSquare(float x, float y, float side);
}

// Concrete Implementors
public class OpenGLRenderer implements Renderer {
    @Override
    public void renderCircle(float x, float y, float radius) {
        System.out.println("OpenGL: Drawing circle at (" + x + "," + y + ") radius " + radius);
    }
    
    @Override
    public void renderSquare(float x, float y, float side) {
        System.out.println("OpenGL: Drawing square at (" + x + "," + y + ") side " + side);
    }
}

public class DirectXRenderer implements Renderer {
    @Override
    public void renderCircle(float x, float y, float radius) {
        System.out.println("DirectX: Drawing circle at (" + x + "," + y + ") radius " + radius);
    }
    
    @Override
    public void renderSquare(float x, float y, float side) {
        System.out.println("DirectX: Drawing square at (" + x + "," + y + ") side " + side);
    }
}

// Abstraction
public abstract class Shape {
    protected Renderer renderer;
    
    public Shape(Renderer renderer) {
        this.renderer = renderer;
    }
    
    public abstract void draw();
    public abstract void resize(float factor);
}

// Refined Abstractions
public class Circle extends Shape {
    private float x, y, radius;
    
    public Circle(Renderer renderer, float x, float y, float radius) {
        super(renderer);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }
    
    @Override
    public void draw() {
        renderer.renderCircle(x, y, radius);
    }
    
    @Override
    public void resize(float factor) {
        radius *= factor;
    }
}

public class Square extends Shape {
    private float x, y, side;
    
    public Square(Renderer renderer, float x, float y, float side) {
        super(renderer);
        this.x = x;
        this.y = y;
        this.side = side;
    }
    
    @Override
    public void draw() {
        renderer.renderSquare(x, y, side);
    }
    
    @Override
    public void resize(float factor) {
        side *= factor;
    }
}

// Usage
Renderer opengl = new OpenGLRenderer();
Renderer directx = new DirectXRenderer();

Shape circle1 = new Circle(opengl, 10, 10, 5);
Shape circle2 = new Circle(directx, 20, 20, 7);
Shape square1 = new Square(opengl, 5, 5, 10);

circle1.draw();      // OpenGL circle
circle2.draw();      // DirectX circle
square1.draw();      // OpenGL square
```

### Real-World Example: Message System
```java
// Implementor
public interface MessageSender {
    void send(String message);
}

// Concrete Implementors
public class EmailSender implements MessageSender {
    @Override
    public void send(String message) {
        System.out.println("Sending EMAIL: " + message);
    }
}

public class SMSSender implements MessageSender {
    @Override
    public void send(String message) {
        System.out.println("Sending SMS: " + message);
    }
}

public class WhatsAppSender implements MessageSender {
    @Override
    public void send(String message) {
        System.out.println("Sending WhatsApp: " + message);
    }
}

// Abstraction
public abstract class Message {
    protected MessageSender sender;
    protected String content;
    
    public Message(MessageSender sender, String content) {
        this.sender = sender;
        this.content = content;
    }
    
    public abstract void send();
}

// Refined Abstractions
class UrgentMessage extends Message {
    public UrgentMessage(MessageSender sender, String content) {
        super(sender, content);
    }
    
    @Override
    public void send() {
        sender.send("[URGENT] " + content);
    }
}

class NormalMessage extends Message {
    public NormalMessage(MessageSender sender, String content) {
        super(sender, content);
    }
    
    @Override
    public void send() {
        sender.send(content);
    }
}

class SilentMessage extends Message {
    public SilentMessage(MessageSender sender, String content) {
        super(sender, content);
    }
    
    @Override
    public void send() {
        sender.send("[SILENT] " + content);
    }
}

// Usage
MessageSender email = new EmailSender();
MessageSender sms = new SMSSender();

Message urgentEmail = new UrgentMessage(email, "Server down!");
Message normalSms = new NormalMessage(sms, "Meeting at 3pm");

urgentEmail.send();
normalSms.send();
```

---

## 3. Composite Pattern

### Overview
Composes objects into tree structures to represent part-whole hierarchies, allowing clients to treat individual objects and compositions uniformly.

### When to Use
- File systems
- UI components
- Organization hierarchies
- Menu systems

### Implementation
```java
// Component Interface
public interface FileSystemComponent {
    String getName();
    long getSize();
    void display(String indent);
}

// Leaf
public class File implements FileSystemComponent {
    private String name;
    private long size;
    
    public File(String name, long size) {
        this.name = name;
        this.size = size;
    }
    
    @Override
    public String getName() { return name; }
    
    @Override
    public long getSize() { return size; }
    
    @Override
    public void display(String indent) {
        System.out.println(indent + "- " + name + " (" + size + " KB)");
    }
}

// Composite
public class Folder implements FileSystemComponent {
    private String name;
    private List<FileSystemComponent> components = new ArrayList<>();
    
    public Folder(String name) {
        this.name = name;
    }
    
    public void addComponent(FileSystemComponent component) {
        components.add(component);
    }
    
    public void removeComponent(FileSystemComponent component) {
        components.remove(component);
    }
    
    @Override
    public String getName() { return name; }
    
    @Override
    public long getSize() {
        return components.stream()
            .mapToLong(FileSystemComponent::getSize)
            .sum();
    }
    
    @Override
    public void display(String indent) {
        System.out.println(indent + "+ " + name + " (" + getSize() + " KB)");
        for (FileSystemComponent component : components) {
            component.display(indent + "  ");
        }
    }
}

// Usage
FileSystemComponent file1 = new File("resume.pdf", 120);
FileSystemComponent file2 = new File("photo.jpg", 2500);
FileSystemComponent file3 = new File("notes.txt", 10);

Folder documents = new Folder("Documents");
documents.addComponent(file1);
documents.addComponent(file3);

Folder pictures = new Folder("Pictures");
pictures.addComponent(file2);

Folder root = new Folder("Root");
root.addComponent(documents);
root.addComponent(pictures);

root.display("");
System.out.println("Total size: " + root.getSize() + " KB");
```

### Real-World Example: Organization Chart
```java
public interface Employee {
    void showDetails();
    double getSalary();
    int getTeamSize();
}

public class Developer implements Employee {
    private String name;
    private double salary;
    
    public Developer(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }
    
    @Override
    public void showDetails() {
        System.out.println("Developer: " + name + " - $" + salary);
    }
    
    @Override
    public double getSalary() { return salary; }
    @Override
    public int getTeamSize() { return 1; }
}

public class Manager implements Employee {
    private String name;
    private double salary;
    private List<Employee> team = new ArrayList<>();
    
    public Manager(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }
    
    public void addEmployee(Employee emp) {
        team.add(emp);
    }
    
    @Override
    public void showDetails() {
        System.out.println("Manager: " + name + " - $" + salary);
        for (Employee emp : team) {
            emp.showDetails();
        }
    }
    
    @Override
    public double getSalary() {
        return salary + team.stream()
            .mapToDouble(Employee::getSalary)
            .sum();
    }
    
    @Override
    public int getTeamSize() {
        return 1 + team.stream()
            .mapToInt(Employee::getTeamSize)
            .sum();
    }
}

// Usage
Employee dev1 = new Developer("Alice", 80000);
Employee dev2 = new Developer("Bob", 75000);
Employee dev3 = new Developer("Charlie", 82000);

Manager techLead = new Manager("Diana", 120000);
techLead.addEmployee(dev1);
techLead.addEmployee(dev2);

Manager ceo = new Manager("Eve", 200000);
ceo.addEmployee(techLead);
ceo.addEmployee(dev3);

ceo.showDetails();
System.out.println("Total salary: $" + ceo.getSalary());
System.out.println("Total team size: " + ceo.getTeamSize());
```

---

## 4. Decorator Pattern

### Overview
Attaches additional responsibilities to an object dynamically, providing a flexible alternative to subclassing.

### When to Use
- Adding features to objects at runtime
- Stream wrappers (BufferedInputStream)
- UI component styling
- Logging and monitoring

### Implementation
```java
// Component Interface
public interface Coffee {
    String getDescription();
    double getCost();
}

// Concrete Component
public class SimpleCoffee implements Coffee {
    @Override
    public String getDescription() {
        return "Simple Coffee";
    }
    
    @Override
    public double getCost() {
        return 2.00;
    }
}

// Base Decorator
public abstract class CoffeeDecorator implements Coffee {
    protected Coffee coffee;
    
    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }
    
    @Override
    public String getDescription() {
        return coffee.getDescription();
    }
    
    @Override
    public double getCost() {
        return coffee.getCost();
    }
}

// Concrete Decorators
public class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }
    
    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Milk";
    }
    
    @Override
    public double getCost() {
        return coffee.getCost() + 0.50;
    }
}

public class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }
    
    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Sugar";
    }
    
    @Override
    public double getCost() {
        return coffee.getCost() + 0.20;
    }
}

public class WhippedCreamDecorator extends CoffeeDecorator {
    public WhippedCreamDecorator(Coffee coffee) {
        super(coffee);
    }
    
    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Whipped Cream";
    }
    
    @Override
    public double getCost() {
        return coffee.getCost() + 1.00;
    }
}

// Usage
Coffee coffee = new SimpleCoffee();
System.out.println(coffee.getDescription() + " - $" + coffee.getCost());

coffee = new MilkDecorator(coffee);
System.out.println(coffee.getDescription() + " - $" + coffee.getCost());

coffee = new SugarDecorator(coffee);
System.out.println(coffee.getDescription() + " - $" + coffee.getCost());

coffee = new WhippedCreamDecorator(coffee);
System.out.println(coffee.getDescription() + " - $" + coffee.getCost());
```

### Real-World Example: Data Encryption
```java
// Component
public interface DataSource {
    void writeData(String data);
    String readData();
}

// Concrete Component
public class FileDataSource implements DataSource {
    private String filename;
    private String data;
    
    public FileDataSource(String filename) {
        this.filename = filename;
    }
    
    @Override
    public void writeData(String data) {
        this.data = data;
        System.out.println("Writing to file: " + filename);
    }
    
    @Override
    public String readData() {
        System.out.println("Reading from file: " + filename);
        return data;
    }
}

// Decorator Base
public class DataSourceDecorator implements DataSource {
    protected DataSource wrapped;
    
    public DataSourceDecorator(DataSource source) {
        this.wrapped = source;
    }
    
    @Override
    public void writeData(String data) {
        wrapped.writeData(data);
    }
    
    @Override
    public String readData() {
        return wrapped.readData();
    }
}

// Concrete Decorators
public class EncryptionDecorator extends DataSourceDecorator {
    public EncryptionDecorator(DataSource source) {
        super(source);
    }
    
    @Override
    public void writeData(String data) {
        super.writeData(encrypt(data));
    }
    
    @Override
    public String readData() {
        return decrypt(super.readData());
    }
    
    private String encrypt(String data) {
        return "ENCRYPTED[" + data + "]";
    }
    
    private String decrypt(String data) {
        return data.replace("ENCRYPTED[", "").replace("]", "");
    }
}

public class CompressionDecorator extends DataSourceDecorator {
    public CompressionDecorator(DataSource source) {
        super(source);
    }
    
    @Override
    public void writeData(String data) {
        super.writeData(compress(data));
    }
    
    @Override
    public String readData() {
        return decompress(super.readData());
    }
    
    private String compress(String data) {
        return "COMPRESSED(" + data + ")";
    }
    
    private String decompress(String data) {
        return data.replace("COMPRESSED(", "").replace(")", "");
    }
}

// Usage
DataSource source = new FileDataSource("data.txt");
source = new EncryptionDecorator(source);
source = new CompressionDecorator(source);

source.writeData("Sensitive data");
System.out.println(source.readData());
```

---

## 5. Facade Pattern

### Overview
Provides a simplified interface to a complex subsystem, making it easier to use.

### When to Use
- Simplifying complex libraries
- Hiding implementation details
- Providing a unified interface
- Reducing dependencies

### Implementation
```java
// Complex Subsystem Classes
class CPU {
    public void freeze() { System.out.println("CPU: Freezing"); }
    public void jump(long position) { System.out.println("CPU: Jumping to " + position); }
    public void execute() { System.out.println("CPU: Executing"); }
}

class Memory {
    public void load(long position, byte[] data) {
        System.out.println("Memory: Loading at " + position);
    }
}

class HardDrive {
    public byte[] read(long lba, int size) {
        System.out.println("HardDrive: Reading " + size + " bytes");
        return new byte[size];
    }
}

// Facade
class ComputerFacade {
    private CPU cpu;
    private Memory memory;
    private HardDrive hardDrive;
    
    public ComputerFacade() {
        this.cpu = new CPU();
        this.memory = new Memory();
        this.hardDrive = new HardDrive();
    }
    
    public void start() {
        System.out.println("=== Starting Computer ===");
        cpu.freeze();
        memory.load(0, hardDrive.read(0, 1024));
        cpu.jump(0);
        cpu.execute();
        System.out.println("=== Computer Started ===");
    }
    
    public void shutdown() {
        System.out.println("=== Shutting Down ===");
        cpu.freeze();
        System.out.println("=== Computer Stopped ===");
    }
}

// Client Code
public class FacadeDemo {
    public static void main(String[] args) {
        ComputerFacade computer = new ComputerFacade();
        computer.start();
        computer.shutdown();
    }
}
```

### Real-World Example: Home Theater
```java
// Subsystem Components
class Amplifier {
    public void on() { System.out.println("Amp: ON"); }
    public void off() { System.out.println("Amp: OFF"); }
    public void setVolume(int level) { System.out.println("Amp: Volume " + level); }
}

class Projector {
    public void on() { System.out.println("Projector: ON"); }
    public void off() { System.out.println("Projector: OFF"); }
    public void setInput(String source) { System.out.println("Projector: Input " + source); }
}

class StreamingPlayer {
    public void on() { System.out.println("Player: ON"); }
    public void off() { System.out.println("Player: OFF"); }
    public void play(String movie) { System.out.println("Player: Playing " + movie); }
}

class Screen {
    public void up() { System.out.println("Screen: UP"); }
    public void down() { System.out.println("Screen: DOWN"); }
}

class Lights {
    public void dim(int level) { System.out.println("Lights: Dimmed to " + level + "%"); }
}

// Facade
class HomeTheaterFacade {
    private Amplifier amp;
    private Projector projector;
    private StreamingPlayer player;
    private Screen screen;
    private Lights lights;
    
    public HomeTheaterFacade(Amplifier amp, Projector projector, 
                             StreamingPlayer player, Screen screen, Lights lights) {
        this.amp = amp;
        this.projector = projector;
        this.player = player;
        this.screen = screen;
        this.lights = lights;
    }
    
    public void watchMovie(String movie) {
        System.out.println("\n--- Starting Movie: " + movie + " ---");
        lights.dim(10);
        screen.down();
        projector.on();
        projector.setInput("HDMI");
        amp.on();
        amp.setVolume(20);
        player.on();
        player.play(movie);
        System.out.println("--- Enjoy your movie! ---\n");
    }
    
    public void endMovie() {
        System.out.println("\n--- Ending Movie ---");
        player.off();
        amp.off();
        projector.off();
        screen.up();
        lights.dim(100);
        System.out.println("--- Done ---\n");
    }
}

// Usage
HomeTheaterFacade theater = new HomeTheaterFacade(
    new Amplifier(), new Projector(), new StreamingPlayer(), 
    new Screen(), new Lights()
);

theater.watchMovie("Inception");
theater.endMovie();
```

---

## 6. Flyweight Pattern

### Overview
Uses sharing to support large numbers of fine-grained objects efficiently by sharing common state.

### When to Use
- Large number of similar objects
- Memory constraints
- Text editors (character objects)
- Game development (trees, bullets)

### Implementation
```java
// Flyweight (shared state)
public class TreeType {
    private String name;
    private String color;
    private String texture;
    
    public TreeType(String name, String color, String texture) {
        this.name = name;
        this.color = color;
        this.texture = texture;
    }
    
    public void draw(int x, int y) {
        System.out.println("Drawing " + name + " tree at (" + x + "," + y + 
            ") with color " + color);
    }
}

// Flyweight Factory
class TreeFactory {
    private static Map<String, TreeType> treeTypes = new HashMap<>();
    
    public static TreeType getTreeType(String name, String color, String texture) {
        String key = name + "_" + color + "_" + texture;
        
        if (!treeTypes.containsKey(key)) {
            treeTypes.put(key, new TreeType(name, color, texture));
            System.out.println("Created new TreeType: " + name);
        }
        
        return treeTypes.get(key);
    }
    
    public static int getTypeCount() {
        return treeTypes.size();
    }
}

// Context (unique state)
public class Tree {
    private int x, y;
    private TreeType type;
    
    public Tree(int x, int y, TreeType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }
    
    public void draw() {
        type.draw(x, y);
    }
}

// Forest (Client)
class Forest {
    private List<Tree> trees = new ArrayList<>();
    
    public void plantTree(int x, int y, String name, String color, String texture) {
        TreeType type = TreeFactory.getTreeType(name, color, texture);
        Tree tree = new Tree(x, y, type);
        trees.add(tree);
    }
    
    public void draw() {
        for (Tree tree : trees) {
            tree.draw();
        }
    }
}

// Usage
Forest forest = new Forest();
forest.plantTree(10, 20, "Oak", "Green", "OakTexture");
forest.plantTree(30, 40, "Oak", "Green", "OakTexture");
forest.plantTree(50, 60, "Pine", "DarkGreen", "PineTexture");
forest.plantTree(70, 80, "Oak", "Green", "OakTexture");

System.out.println("Unique tree types: " + TreeFactory.getTypeCount());
forest.draw();
```

### Real-World Example: Character Text Editor
```java
// Flyweight
class CharacterStyle {
    private String font;
    private int size;
    private String color;
    
    public CharacterStyle(String font, int size, String color) {
        this.font = font;
        this.size = size;
        this.color = color;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        CharacterStyle other = (CharacterStyle) obj;
        return font.equals(other.font) && size == other.size && color.equals(other.color);
    }
    
    @Override
    public int hashCode() {
        return font.hashCode() + size + color.hashCode();
    }
}

class CharacterStyleFactory {
    private static Map<CharacterStyle, CharacterStyle> styles = new HashMap<>();
    
    public static CharacterStyle getStyle(String font, int size, String color) {
        CharacterStyle style = new CharacterStyle(font, size, color);
        if (!styles.containsKey(style)) {
            styles.put(style, style);
        }
        return styles.get(style);
    }
    
    public static int getStyleCount() { return styles.size(); }
}

// Context
class Character {
    private char symbol;
    private int x, y;
    private CharacterStyle style;
    
    public Character(char symbol, int x, int y, CharacterStyle style) {
        this.symbol = symbol;
        this.x = x;
        this.y = y;
        this.style = style;
    }
    
    public void display() {
        System.out.println("Char '" + symbol + "' at (" + x + "," + y + 
            ") - Font: " + style.getClass().getSimpleName());
    }
}

// Usage
String text = "Hello";
int x = 0;
for (char c : text.toCharArray()) {
    CharacterStyle style = CharacterStyleFactory.getStyle("Arial", 12, "Black");
    new Character(c, x++, 0, style).display();
}

System.out.println("Unique styles: " + CharacterStyleFactory.getStyleCount());
```

---

## 7. Proxy Pattern

### Overview
Provides a surrogate or placeholder for another object to control access to it.

### When to Use
- Lazy initialization (virtual proxy)
- Access control (protection proxy)
- Remote object access (remote proxy)
- Logging and caching (smart reference)

### Implementation

#### Virtual Proxy (Lazy Loading)
```java
// Subject Interface
public interface Image {
    void display();
}

// Real Subject
public class RealImage implements Image {
    private String filename;
    
    public RealImage(String filename) {
        this.filename = filename;
        loadFromDisk();
    }
    
    private void loadFromDisk() {
        System.out.println("Loading image: " + filename);
    }
    
    @Override
    public void display() {
        System.out.println("Displaying image: " + filename);
    }
}

// Virtual Proxy
public class ImageProxy implements Image {
    private RealImage realImage;
    private String filename;
    
    public ImageProxy(String filename) {
        this.filename = filename;
    }
    
    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(filename);
        }
        realImage.display();
    }
}

// Usage
System.out.println("Creating proxy...");
Image image = new ImageProxy("large_photo.jpg");

System.out.println("\nFirst display:");
image.display();

System.out.println("\nSecond display:");
image.display();
```

#### Protection Proxy (Access Control)
```java
public interface BankAccount {
    void deposit(double amount);
    void withdraw(double amount);
    double getBalance();
}

public class RealBankAccount implements BankAccount {
    private double balance;
    private String ownerName;
    
    public RealBankAccount(String ownerName) {
        this.ownerName = ownerName;
        this.balance = 0;
    }
    
    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: $" + amount);
    }
    
    @Override
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
        } else {
            System.out.println("Insufficient funds");
        }
    }
    
    @Override
    public double getBalance() {
        return balance;
    }
}

public class BankAccountProxy implements BankAccount {
    private RealBankAccount realAccount;
    private String userRole;
    
    public BankAccountProxy(String ownerName, String userRole) {
        this.realAccount = new RealBankAccount(ownerName);
        this.userRole = userRole;
    }
    
    @Override
    public void deposit(double amount) {
        realAccount.deposit(amount);
    }
    
    @Override
    public void withdraw(double amount) {
        if ("admin".equals(userRole) || "user".equals(userRole)) {
            realAccount.withdraw(amount);
        } else {
            System.out.println("Access denied: Cannot withdraw");
        }
    }
    
    @Override
    public double getBalance() {
        if ("admin".equals(userRole)) {
            return realAccount.getBalance();
        }
        System.out.println("Access denied: Cannot view balance");
        return 0;
    }
}

// Usage
BankAccount userAccount = new BankAccountProxy("John", "user");
userAccount.deposit(1000);
userAccount.withdraw(500);
System.out.println("Balance: " + userAccount.getBalance());

System.out.println("---");
BankAccount adminAccount = new BankAccountProxy("Admin", "admin");
System.out.println("Admin balance: " + adminAccount.getBalance());
```

#### Remote Proxy
```java
// RMI Example (Conceptual)
public interface RemoteService extends Remote {
    String getData() throws RemoteException;
}

public class RemoteServiceProxy implements RemoteService {
    private String host;
    private int port;
    
    public RemoteServiceProxy(String host, int port) {
        this.host = host;
        this.port = port;
    }
    
    @Override
    public String getData() throws RemoteException {
        // Connect to remote server
        System.out.println("Connecting to " + host + ":" + port);
        return "Data from remote server";
    }
}
```

#### Logging Proxy
```java
public interface Service {
    void execute();
    String getData();
}

public class RealService implements Service {
    @Override
    public void execute() {
        System.out.println("Executing service...");
    }
    
    @Override
    public String getData() {
        return "Service Data";
    }
}

public class LoggingProxy implements Service {
    private RealService realService;
    
    public LoggingProxy() {
        this.realService = new RealService();
    }
    
    @Override
    public void execute() {
        System.out.println("[LOG] Before execute()");
        long start = System.currentTimeMillis();
        realService.execute();
        long end = System.currentTimeMillis();
        System.out.println("[LOG] After execute() - took " + (end - start) + "ms");
    }
    
    @Override
    public String getData() {
        System.out.println("[LOG] Before getData()");
        String result = realService.getData();
        System.out.println("[LOG] After getData() = " + result);
        return result;
    }
}

// Usage
Service service = new LoggingProxy();
service.execute();
service.getData();
```

---

## Quick Reference Cheat Sheet

### Pattern Summary
| Pattern | Purpose | Complexity |
|---------|---------|------------|
| **Adapter** | Convert interfaces | Low |
| **Bridge** | Decouple abstraction from implementation | Medium |
| **Composite** | Tree structures | Medium |
| **Decorator** | Add responsibilities dynamically | Low |
| **Facade** | Simplified interface | Low |
| **Flyweight** | Share objects efficiently | Medium |
| **Proxy** | Control access to object | Low |

### When to Use Which
```
Need to make incompatible interfaces work?     → Adapter
Need to separate abstraction from implementation? → Bridge
Need to represent part-whole hierarchy?          → Composite
Need to add features at runtime?                → Decorator
Need to simplify complex subsystem?             → Facade
Need to share many similar objects?             → Flyweight
Need to control access to an object?            → Proxy
```

### Common Use Cases
- **Adapter**: Legacy code integration, third-party libraries
- **Bridge**: Cross-platform UIs, device drivers
- **Composite**: File systems, UI components, organization charts
- **Decorator**: Stream wrappers, UI styling, logging
- **Facade**: Library simplification, API wrappers
- **Proxy**: Caching, lazy loading, access control, remote services

---

*End of Structural Design Patterns Manual*