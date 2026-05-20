# Behavioral Design Patterns Manual
## Java Design Patterns - Software Engineer Track

---

## 1. Chain of Responsibility Pattern

### Overview
Passes a request along a chain of handlers. Each handler decides to process the request or pass it to the next handler.

### When to Use
- Event handling systems
- Logging frameworks
- Authentication/authorization pipelines
- Input validation chains

### Implementation
```java
// Handler Interface
public abstract class Handler {
    protected Handler nextHandler;
    
    public void setNext(Handler handler) {
        this.nextHandler = handler;
    }
    
    public void handle(Request request) {
        if (process(request) && nextHandler != null) {
            nextHandler.handle(request);
        }
    }
    
    protected abstract boolean process(Request request);
}

// Concrete Handlers
public class AuthenticationHandler extends Handler {
    @Override
    protected boolean process(Request request) {
        System.out.println("Authenticating: " + request.getUser());
        return request.isAuthenticated();
    }
}

public class AuthorizationHandler extends Handler {
    @Override
    protected boolean process(Request request) {
        System.out.println("Authorizing: " + request.getAction());
        return request.hasPermission();
    }
}

public class ValidationHandler extends Handler {
    @Override
    protected boolean process(Request request) {
        System.out.println("Validating request data");
        return request.isValid();
    }
}

// Request Class
public class Request {
    private String user;
    private String action;
    private boolean authenticated = false;
    private boolean hasPermission = false;
    private boolean valid = true;
    
    public String getUser() { return user; }
    public String getAction() { return action; }
    public boolean isAuthenticated() { return authenticated; }
    public boolean hasPermission() { return hasPermission; }
    public boolean isValid() { return valid; }
    
    // Setters
    public void setAuthenticated(boolean b) { this.authenticated = b; }
    public void setHasPermission(boolean b) { this.hasPermission = b; }
    public void setValid(boolean b) { this.valid = b; }
}

// Usage
public class ChainDemo {
    public static void main(String[] args) {
        Handler auth = new AuthenticationHandler();
        Handler authz = new AuthorizationHandler();
        Handler valid = new ValidationHandler();
        
        auth.setNext(authz);
        authz.setNext(valid);
        
        Request request = new Request();
        request.setAuthenticated(true);
        request.setHasPermission(true);
        
        auth.handle(request);
    }
}
```

### Real-World Example: Log Levels
```java
public abstract class LogHandler {
    protected LogHandler next;
    
    public void setNext(LogHandler handler) {
        this.next = handler;
    }
    
    public void log(int level, String message) {
        if (shouldHandle(level)) {
            write(message);
        }
        if (next != null) {
            next.log(level, message);
        }
    }
    
    protected abstract boolean shouldHandle(int level);
    protected abstract void write(String message);
}

public class DebugHandler extends LogHandler {
    protected boolean shouldHandle(int level) { return level == 1; }
    protected void write(String message) { System.out.println("[DEBUG] " + message); }
}

public class ErrorHandler extends LogHandler {
    protected boolean shouldHandle(int level) { return level == 3; }
    protected void write(String message) { System.out.println("[ERROR] " + message); }
}
```

---

## 2. Command Pattern

### Overview
Encapsulates a request as an object, allowing parameterization, queuing, and undo operations.

### When to Use
- Undo/redo functionality
- Transaction management
- Task scheduling
- Request queuing

### Implementation
```java
// Command Interface
public interface Command {
    void execute();
    void undo();
}

// Receiver
public class TextEditor {
    private String content = "";
    
    public void addText(String text) {
        content += text;
    }
    
    public void delete(int chars) {
        if (chars <= content.length()) {
            content = content.substring(0, content.length() - chars);
        }
    }
    
    public String getContent() { return content; }
}

// Concrete Commands
public class AddTextCommand implements Command {
    private TextEditor editor;
    private String text;
    
    public AddTextCommand(TextEditor editor, String text) {
        this.editor = editor;
        this.text = text;
    }
    
    @Override
    public void execute() {
        editor.addText(text);
    }
    
    @Override
    public void undo() {
        editor.delete(text.length());
    }
}

public class DeleteTextCommand implements Command {
    private TextEditor editor;
    private int charsToDelete;
    private String deletedText;
    
    public DeleteTextCommand(TextEditor editor, int chars) {
        this.editor = editor;
        this.charsToDelete = chars;
    }
    
    @Override
    public void execute() {
        String current = editor.getContent();
        deletedText = current.substring(current.length() - charsToDelete);
        editor.delete(charsToDelete);
    }
    
    @Override
    public void undo() {
        editor.addText(deletedText);
    }
}

// Invoker
public class CommandManager {
    private Stack<Command> history = new Stack<>();
    
    public void executeCommand(Command cmd) {
        cmd.execute();
        history.push(cmd);
    }
    
    public void undo() {
        if (!history.isEmpty()) {
            history.pop().undo();
        }
    }
}

// Usage
TextEditor editor = new TextEditor();
CommandManager manager = new CommandManager();

manager.executeCommand(new AddTextCommand(editor, "Hello "));
manager.executeCommand(new AddTextCommand(editor, "World"));
System.out.println(editor.getContent()); // "Hello World"

manager.undo();
System.out.println(editor.getContent()); // "Hello "
```

### Real-World Example: Remote Control
```java
public interface Command {
    void execute();
}

public class Light {
    public void on() { System.out.println("Light is ON"); }
    public void off() { System.out.println("Light is OFF"); }
}

public class LightOnCommand implements Command {
    private Light light;
    public LightOnCommand(Light light) { this.light = light; }
    public void execute() { light.on(); }
}

public class LightOffCommand implements Command {
    private Light light;
    public LightOffCommand(Light light) { this.light = light; }
    public void execute() { light.off(); }
}

public class RemoteControl {
    private Command command;
    public void setCommand(Command cmd) { this.command = cmd; }
    public void press() { command.execute(); }
}
```

---

## 3. Interpreter Pattern

### Overview
Defines a representation for a grammar and an interpreter to handle sentences in the language.

### When to Use
- Query languages (SQL, XPath)
- Mathematical expression evaluators
- Domain-specific languages
- Configuration file parsing

### Implementation
```java
// Expression Interface
public interface Expression {
    int interpret(Context context);
}

// Terminal Expressions
public class NumberExpression implements Expression {
    private int number;
    
    public NumberExpression(int number) {
        this.number = number;
    }
    
    @Override
    public int interpret(Context context) {
        return number;
    }
}

public class VariableExpression implements Expression {
    private String variable;
    
    public VariableExpression(String variable) {
        this.variable = variable;
    }
    
    @Override
    public int interpret(Context context) {
        return context.getVariable(variable);
    }
}

// Context
public class Context {
    private Map<String, Integer> variables = new HashMap<>();
    
    public void setVariable(String name, int value) {
        variables.put(name, value);
    }
    
    public int getVariable(String name) {
        return variables.getOrDefault(name, 0);
    }
}

// Non-Terminal Expressions
public class AddExpression implements Expression {
    private Expression left;
    private Expression right;
    
    public AddExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
    
    @Override
    public int interpret(Context context) {
        return left.interpret(context) + right.interpret(context);
    }
}

public class SubtractExpression implements Expression {
    private Expression left;
    private Expression right;
    
    public SubtractExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
    
    @Override
    public int interpret(Context context) {
        return left.interpret(context) - right.interpret(context);
    }
}

public class MultiplyExpression implements Expression {
    private Expression left;
    private Expression right;
    
    public MultiplyExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
    
    @Override
    public int interpret(Context context) {
        return left.interpret(context) * right.interpret(context);
    }
}

// Usage
Context context = new Context();
context.setVariable("x", 5);
context.setVariable("y", 3);

// Expression: x + y * 2
Expression expr = new AddExpression(
    new VariableExpression("x"),
    new MultiplyExpression(new VariableExpression("y"), new NumberExpression(2))
);

System.out.println(expr.interpret(context)); // 5 + (3 * 2) = 11
```

---

## 4. Iterator Pattern

### Overview
Provides a way to access elements of a collection sequentially without exposing its underlying representation.

### When to Use
- Traversing collections without exposing structure
- Supporting multiple traversal methods
- Decoupling collection from traversal logic

### Implementation
```java
// Iterator Interface
public interface Iterator<T> {
    boolean hasNext();
    T next();
    void reset();
}

// Collection Interface
public interface Collection<T> {
    Iterator<T> createIterator();
}

// Concrete Iterator
public class ArrayIterator<T> implements Iterator<T> {
    private T[] array;
    private int position = 0;
    
    public ArrayIterator(T[] array) {
        this.array = array;
    }
    
    @Override
    public boolean hasNext() {
        return position < array.length;
    }
    
    @Override
    public T next() {
        return array[position++];
    }
    
    @Override
    public void reset() {
        position = 0;
    }
}

// Concrete Collection
public class BookCollection implements Collection<String> {
    private String[] books = new String[10];
    private int count = 0;
    
    public void addBook(String book) {
        if (count < 10) {
            books[count++] = book;
        }
    }
    
    @Override
    public Iterator<String> createIterator() {
        return new ArrayIterator<>(books);
    }
}

// Reverse Iterator
public class ReverseIterator<T> implements Iterator<T> {
    private T[] array;
    private int position;
    
    public ReverseIterator(T[] array) {
        this.array = array;
        this.position = array.length - 1;
    }
    
    @Override
    public boolean hasNext() {
        return position >= 0;
    }
    
    @Override
    public T next() {
        return array[position--];
    }
    
    @Override
    public void reset() {
        position = array.length - 1;
    }
}

// Usage
BookCollection collection = new BookCollection();
collection.addBook("Design Patterns");
collection.addBook("Clean Code");
collection.addBook("Effective Java");

Iterator<String> iterator = collection.createIterator();
while (iterator.hasNext()) {
    System.out.println(iterator.next());
}
```

### Java Built-in Iterator
```java
List<String> list = new ArrayList<>();
list.add("Apple");
list.add("Banana");
list.add("Cherry");

Iterator<String> it = list.iterator();
while (it.hasNext()) {
    String item = it.next();
    if (item.startsWith("B")) {
        it.remove();  // Safe removal during iteration
    }
}

// Java 8+ forEachRemaining
it.forEachRemaining(System.out::println);
```

---

## 5. Mediator Pattern

### Overview
Defines an object that encapsulates how a set of objects interact, promoting loose coupling.

### When to Use
- UI components communication
- Chat rooms/messaging systems
- Air traffic control
- Event handling systems

### Implementation
```java
// Mediator Interface
public interface ChatMediator {
    void sendMessage(String message, User sender);
    void addUser(User user);
}

// Colleague
public class User {
    private String name;
    private ChatMediator mediator;
    
    public User(String name, ChatMediator mediator) {
        this.name = name;
        this.mediator = mediator;
    }
    
    public void send(String message) {
        System.out.println(name + " sends: " + message);
        mediator.sendMessage(message, this);
    }
    
    public void receive(String message, String from) {
        System.out.println(name + " receives from " + from + ": " + message);
    }
}

// Concrete Mediator
public class ChatRoom implements ChatMediator {
    private List<User> users = new ArrayList<>();
    
    @Override
    public void addUser(User user) {
        users.add(user);
    }
    
    @Override
    public void sendMessage(String message, User sender) {
        for (User user : users) {
            if (user != sender) {
                user.receive(message, sender.getName());
            }
        }
    }
    
    public String getUserNames() {
        return users.stream()
            .map(User::getName)
            .collect(Collectors.joining(", "));
    }
}

// Usage
ChatMediator chatRoom = new ChatRoom();

User alice = new User("Alice", chatRoom);
User bob = new User("Bob", chatRoom);
User charlie = new User("Charlie", chatRoom);

chatRoom.addUser(alice);
chatRoom.addUser(bob);
chatRoom.addUser(charlie);

alice.send("Hello everyone!");
bob.send("Hi Alice!");
// Charlie receives both messages
```

### Real-World Example: UI Dialog
```java
public interface DialogMediator {
    void notify(Component component, String event);
}

public abstract class Component {
    protected DialogMediator dialog;
    
    public void setDialog(DialogMediator dialog) {
        this.dialog = dialog;
    }
    
    public abstract void changed(String event);
}

public class Button extends Component {
    private String label;
    
    public Button(String label) {
        this.label = label;
    }
    
    public void click() {
        dialog.notify(this, "click");
    }
    
    @Override
    public void changed(String event) {
        System.out.println("Button " + label + " changed: " + event);
    }
}

public class TextField extends Component {
    private String text;
    
    public String getText() { return text; }
    public void setText(String text) {
        this.text = text;
        dialog.notify(this, "textChanged");
    }
    
    @Override
    public void changed(String event) {
        System.out.println("TextField changed: " + event);
    }
}

public class OKDialog implements DialogMediator {
    private Button okButton;
    private TextField inputField;
    
    public OKDialog() {
        okButton = new Button("OK");
        inputField = new TextField();
        
        okButton.setDialog(this);
        inputField.setDialog(this);
    }
    
    @Override
    public void notify(Component component, String event) {
        if (component == inputField && "textChanged".equals(event)) {
            System.out.println("Text changed, enabling button...");
            okButton.changed("enabled");
        }
    }
}
```

---

## 6. Memento Pattern

### Overview
Captures and externalizes an object's internal state so it can be restored later without violating encapsulation.

### When to Use
- Undo functionality
- Checkpoints/snapshots
- Transaction rollback
- State persistence

### Implementation
```java
// Memento (State Container)
public class GameMemento {
    private int level;
    private int health;
    private int score;
    
    public GameMemento(int level, int health, int score) {
        this.level = level;
        this.health = health;
        this.score = score;
    }
    
    public int getLevel() { return level; }
    public int getHealth() { return health; }
    public int getScore() { return score; }
}

// Originator (State to be saved)
public class Game {
    private int level;
    private int health;
    private int score;
    
    public Game() {
        this.level = 1;
        this.health = 100;
        this.score = 0;
    }
    
    public void play() {
        level++;
        score += 100;
        health -= 10;
        System.out.println("Playing... Level: " + level + ", Score: " + score);
    }
    
    public GameMemento save() {
        return new GameMemento(level, health, score);
    }
    
    public void restore(GameMemento memento) {
        this.level = memento.getLevel();
        this.health = memento.getHealth();
        this.score = memento.getScore();
        System.out.println("Restored to Level: " + level + ", Score: " + score);
    }
    
    public void showState() {
        System.out.println("Level: " + level + ", Health: " + health + ", Score: " + score);
    }
}

// Caretaker (Manages saved states)
public class GameHistory {
    private Stack<GameMemento> history = new Stack<>();
    private Game game;
    
    public GameHistory(Game game) {
        this.game = game;
    }
    
    public void backup() {
        history.push(game.save());
    }
    
    public void undo() {
        if (history.isEmpty()) {
            System.out.println("Nothing to undo");
            return;
        }
        GameMemento memento = history.pop();
        game.restore(memento);
    }
}

// Usage
Game game = new Game();
GameHistory history = new GameHistory(game);

game.showState();        // Level: 1, Health: 100, Score: 0
history.backup();

game.play();             // Level: 2, Score: 100
game.showState();

history.undo();          // Restored to Level: 1, Score: 0
game.showState();
```

### Real-World Example: Text Editor
```java
public class EditorMemento {
    private String content;
    private int cursorPosition;
    
    public EditorMemento(String content, int cursorPosition) {
        this.content = content;
        this.cursorPosition = cursorPosition;
    }
    
    public String getContent() { return content; }
    public int getCursorPosition() { return cursorPosition; }
}

public class Editor {
    private String content = "";
    private int cursorPosition = 0;
    
    public void type(String text) {
        content = content.substring(0, cursorPosition) + text + 
                  content.substring(cursorPosition);
        cursorPosition += text.length();
    }
    
    public EditorMemento save() {
        return new EditorMemento(content, cursorPosition);
    }
    
    public void restore(EditorMemento memento) {
        this.content = memento.getContent();
        this.cursorPosition = memento.getCursorPosition();
    }
    
    public String getContent() { return content; }
}
```

---

## 7. Observer Pattern

### Overview
Defines a one-to-many dependency where when one object changes state, all dependents are notified.

### When to Use
- Event handling systems
- GUI frameworks
- Model-View-Controller architecture
- Distributed event handling

### Implementation
```java
// Subject Interface
public interface Subject {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyObservers();
}

// Observer Interface
public interface Observer {
    void update(String message);
}

// Concrete Subject
public class NewsAgency implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private String latestNews;
    
    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }
    
    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }
    
    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(latestNews);
        }
    }
    
    public void publishNews(String news) {
        this.latestNews = news;
        notifyObservers();
    }
}

// Concrete Observers
public class NewsChannel implements Observer {
    private String name;
    
    public NewsChannel(String name) {
        this.name = name;
    }
    
    @Override
    public void update(String news) {
        System.out.println(name + " received: " + news);
    }
}

public class Subscriber implements Observer {
    private String email;
    
    public Subscriber(String email) {
        this.email = email;
    }
    
    @Override
    public void update(String news) {
        System.out.println("Email to " + email + ": " + news);
    }
}

// Usage
NewsAgency agency = new NewsAgency();

NewsChannel cnn = new NewsChannel("CNN");
NewsChannel bbc = new NewsChannel("BBC");
Subscriber john = new Subscriber("john@example.com");

agency.attach(cnn);
agency.attach(bbc);
agency.attach(john);

agency.publishNews("Breaking: Java 21 Released!");
```

### Java Built-in Observer
```java
import java.util.Observable;
import java.util.Observer;

public class NewsSubject extends Observable {
    private String news;
    
    public void setNews(String news) {
        this.news = news;
        setChanged();  // Important!
        notifyObservers(news);
    }
    
    public String getNews() { return news; }
}

public class NewsReader implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Received: " + arg);
    }
}
```

---

## 8. State Pattern

### Overview
Allows an object to alter its behavior when its internal state changes, appearing as if the object changed its class.

### When to Use
- Finite state machines
- Order processing systems
- Game character states
- Vending machines

### Implementation
```java
// State Interface
public interface State {
    void insertCoin(VendingMachine machine);
    void selectProduct(VendingMachine machine);
    void dispense(VendingMachine machine);
}

// Context
public class VendingMachine {
    private State state;
    private int coinCount = 0;
    
    public VendingMachine() {
        this.state = new NoCoinState();
    }
    
    public void setState(State state) {
        this.state = state;
    }
    
    public void addCoin() {
        coinCount++;
    }
    
    public int getCoinCount() { return coinCount; }
    
    public void insertCoin() {
        state.insertCoin(this);
    }
    
    public void selectProduct() {
        state.selectProduct(this);
    }
    
    public void dispense() {
        state.dispense(this);
    }
}

// Concrete States
public class NoCoinState implements State {
    @Override
    public void insertCoin(VendingMachine machine) {
        machine.addCoin();
        machine.setState(new HasCoinState());
        System.out.println("Coin inserted");
    }
    
    @Override
    public void selectProduct(VendingMachine machine) {
        System.out.println("Please insert coin first");
    }
    
    @Override
    public void dispense(VendingMachine machine) {
        System.out.println("No coin, no dispense");
    }
}

public class HasCoinState implements State {
    @Override
    public void insertCoin(VendingMachine machine) {
        machine.addCoin();
        System.out.println("Coin added. Total: " + machine.getCoinCount());
    }
    
    @Override
    public void selectProduct(VendingMachine machine) {
        if (machine.getCoinCount() >= 2) {
            machine.setState(new SoldState());
            machine.dispense();
        } else {
            System.out.println("Need more coins");
        }
    }
    
    @Override
    public void dispense(VendingMachine machine) {
        System.out.println("Select product first");
    }
}

public class SoldState implements State {
    @Override
    public void insertCoin(VendingMachine machine) {
        System.out.println("Wait for dispensing");
    }
    
    @Override
    public void selectProduct(VendingMachine machine) {
        System.out.println("Already dispensing");
    }
    
    @Override
    public void dispense(VendingMachine machine) {
        System.out.println("Product dispensed!");
        machine.setState(new NoCoinState());
    }
}

// Usage
VendingMachine machine = new VendingMachine();
machine.selectProduct();    // "Please insert coin first"
machine.insertCoin();       // "Coin inserted"
machine.selectProduct();    // "Product dispensed!"
```

### Real-World Example: Order Processing
```java
public interface OrderState {
    void proceed(Order order);
    void cancel(Order order);
    String getStatus();
}

public class Order {
    private OrderState state;
    
    public Order() {
        this.state = new NewOrderState();
    }
    
    public void setState(OrderState state) { this.state = state; }
    public void proceed() { state.proceed(this); }
    public void cancel() { state.cancel(this); }
    public String getStatus() { return state.getStatus(); }
}

public class NewOrderState implements OrderState {
    public void proceed(Order order) {
        order.setState(new ProcessingState());
    }
    public void cancel(Order order) {
        order.setState(new CancelledState());
    }
    public String getStatus() { return "NEW"; }
}
```

---

## 9. Strategy Pattern

### Overview
Defines a family of algorithms, encapsulates each one, and makes them interchangeable.

### When to Use
- Multiple payment methods
- Different sorting algorithms
- Compression strategies
- Validation rules

### Implementation
```java
// Strategy Interface
public interface PaymentStrategy {
    void pay(double amount);
}

// Concrete Strategies
public class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    
    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " via Credit Card " + cardNumber);
    }
}

public class PayPalPayment implements PaymentStrategy {
    private String email;
    
    public PayPalPayment(String email) {
        this.email = email;
    }
    
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " via PayPal account " + email);
    }
}

public class CryptoPayment implements PaymentStrategy {
    private String walletAddress;
    
    public CryptoPayment(String walletAddress) {
        this.walletAddress = walletAddress;
    }
    
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " via Crypto to " + walletAddress);
    }
}

// Context
public class ShoppingCart {
    private List<Item> items = new ArrayList<>();
    private PaymentStrategy paymentStrategy;
    
    public void addItem(Item item) {
        items.add(item);
    }
    
    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.paymentStrategy = strategy;
    }
    
    public double calculateTotal() {
        return items.stream()
            .mapToDouble(Item::getPrice)
            .sum();
    }
    
    public void checkout() {
        double total = calculateTotal();
        paymentStrategy.pay(total);
    }
}

public class Item {
    private String name;
    private double price;
    
    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }
    
    public String getName() { return name; }
    public double getPrice() { return price; }
}

// Usage
ShoppingCart cart = new ShoppingCart();
cart.addItem(new Item("Laptop", 999.99));
cart.addItem(new Item("Mouse", 29.99));

cart.setPaymentStrategy(new CreditCardPayment("1234-5678-9012-3456"));
cart.checkout();

cart.setPaymentStrategy(new PayPalPayment("user@email.com"));
cart.checkout();
```

### Real-World Example: Sorting
```java
public interface SortStrategy {
    void sort(int[] array);
}

public class BubbleSort implements SortStrategy {
    @Override
    public void sort(int[] array) {
        System.out.println("Sorting with Bubble Sort");
        // Bubble sort implementation
    }
}

public class QuickSort implements SortStrategy {
    @Override
    public void sort(int[] array) {
        System.out.println("Sorting with Quick Sort");
        // Quick sort implementation
    }
}

public class Sorter {
    private SortStrategy strategy;
    
    public void setStrategy(SortStrategy strategy) {
        this.strategy = strategy;
    }
    
    public void sort(int[] array) {
        strategy.sort(array);
    }
}
```

---

## 10. Template Method Pattern

### Overview
Defines the skeleton of an algorithm in a method, deferring some steps to subclasses.

### When to Use
- Framework hooks
- Data processing pipelines
- Building construction steps
- Game AI templates

### Implementation
```java
// Abstract Class with Template Method
public abstract class DataMiner {
    // Template method
    public final void mine(String path) {
        String file = openFile(path);
        String rawData = extractData(file);
        String[] data = parseData(rawData);
        String[] analyzed = analyzeData(data);
        sendReport(analyzed);
        closeFile(file);
    }
    
    // Abstract methods (to be implemented by subclasses)
    protected abstract String openFile(String path);
    protected abstract String extractData(String file);
    protected abstract String[] parseData(String rawData);
    protected abstract String[] analyzeData(String[] data);
    protected abstract void closeFile(String file);
    
    // Hook (optional override)
    protected void sendReport(String[] data) {
        System.out.println("Report sent: " + data.length + " items");
    }
}

// Concrete Implementations
public class PDFDataMiner extends DataMiner {
    @Override
    protected String openFile(String path) {
        System.out.println("Opening PDF: " + path);
        return "pdf-content";
    }
    
    @Override
    protected String extractData(String file) {
        System.out.println("Extracting PDF data");
        return "raw-pdf-data";
    }
    
    @Override
    protected String[] parseData(String rawData) {
        System.out.println("Parsing PDF data");
        return new String[]{"pdf-line-1", "pdf-line-2"};
    }
    
    @Override
    protected String[] analyzeData(String[] data) {
        System.out.println("Analyzing PDF data");
        return data;
    }
    
    @Override
    protected void closeFile(String file) {
        System.out.println("Closing PDF");
    }
}

public class CSVMiner extends DataMiner {
    @Override
    protected String openFile(String path) {
        System.out.println("Opening CSV: " + path);
        return "csv-content";
    }
    
    @Override
    protected String extractData(String file) {
        System.out.println("Extracting CSV data");
        return "raw-csv-data";
    }
    
    @Override
    protected String[] parseData(String rawData) {
        System.out.println("Parsing CSV data");
        return new String[]{"csv-row-1", "csv-row-2", "csv-row-3"};
    }
    
    @Override
    protected String[] analyzeData(String[] data) {
        System.out.println("Analyzing CSV data");
        return data;
    }
    
    @Override
    protected void closeFile(String file) {
        System.out.println("Closing CSV");
    }
}

// Usage
DataMiner pdfMiner = new PDFDataMiner();
pdfMiner.mine("document.pdf");

System.out.println("---");

DataMiner csvMiner = new CSVMiner();
csvMiner.mine("data.csv");
```

### Real-World Example: Game Character
```java
public abstract class CharacterBuilder {
    public final void buildCharacter() {
        createBase();
        addAbilities();
        equipWeapons();
        applyArmor();
    }
    
    protected abstract void createBase();
    protected abstract void addAbilities();
    protected abstract void equipWeapons();
    protected abstract void applyArmor();
}

public class WarriorBuilder extends CharacterBuilder {
    protected void createBase() { System.out.println("Create Warrior base"); }
    protected void addAbilities() { System.out.println("Add Strength, Endurance"); }
    protected void equipWeapons() { System.out.println("Equip Sword, Shield"); }
    protected void applyArmor() { System.out.println("Apply Heavy Armor"); }
}

public class MageBuilder extends CharacterBuilder {
    protected void createBase() { System.out.println("Create Mage base"); }
    protected void addAbilities() { System.out.println("Add Intelligence, Mana"); }
    protected void equipWeapons() { System.out.println("Equip Staff, Wand"); }
    protected void applyArmor() { System.out.println("Apply Robe"); }
}
```

---

## 11. Visitor Pattern

### Overview
Represents an operation to be performed on elements of an object structure, allowing new operations without changing element classes.

### When to Use
- Document processing
- Tax calculation systems
- Compiler symbol table
- File system operations

### Implementation
```java
// Element Interface
public interface Shape {
    void accept(Visitor visitor);
}

// Concrete Elements
public class Circle implements Shape {
    private double radius;
    
    public Circle(double radius) {
        this.radius = radius;
    }
    
    public double getRadius() { return radius; }
    
    @Override
    public void accept(Visitor visitor) {
        visitor.visitCircle(this);
    }
}

public class Rectangle implements Shape {
    private double width;
    private double height;
    
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }
    
    public double getWidth() { return width; }
    public double getHeight() { return height; }
    
    @Override
    public void accept(Visitor visitor) {
        visitor.visitRectangle(this);
    }
}

public class Triangle implements Shape {
    private double base;
    private double height;
    
    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }
    
    public double getBase() { return base; }
    public double getHeight() { return height; }
    
    @Override
    public void accept(Visitor visitor) {
        visitor.visitTriangle(this);
    }
}

// Visitor Interface
public interface Visitor {
    void visitCircle(Circle circle);
    void visitRectangle(Rectangle rectangle);
    void visitTriangle(Triangle triangle);
}

// Concrete Visitors
public class AreaCalculator implements Visitor {
    private double totalArea = 0;
    
    public double getTotalArea() { return totalArea; }
    
    @Override
    public void visitCircle(Circle circle) {
        double area = Math.PI * circle.getRadius() * circle.getRadius();
        totalArea += area;
        System.out.println("Circle area: " + area);
    }
    
    @Override
    public void visitRectangle(Rectangle rectangle) {
        double area = rectangle.getWidth() * rectangle.getHeight();
        totalArea += area;
        System.out.println("Rectangle area: " + area);
    }
    
    @Override
    public void visitTriangle(Triangle triangle) {
        double area = 0.5 * triangle.getBase() * triangle.getHeight();
        totalArea += area;
        System.out.println("Triangle area: " + area);
    }
}

public class ShapeDrawer implements Visitor {
    @Override
    public void visitCircle(Circle circle) {
        System.out.println("Drawing Circle with radius " + circle.getRadius());
    }
    
    @Override
    public void visitRectangle(Rectangle rectangle) {
        System.out.println("Drawing Rectangle " + 
            rectangle.getWidth() + "x" + rectangle.getHeight());
    }
    
    @Override
    public void visitTriangle(Triangle triangle) {
        System.out.println("Drawing Triangle with base " + triangle.getBase());
    }
}

// Object Structure
public class Drawing {
    private List<Shape> shapes = new ArrayList<>();
    
    public void addShape(Shape shape) {
        shapes.add(shape);
    }
    
    public void accept(Visitor visitor) {
        for (Shape shape : shapes) {
            shape.accept(visitor);
        }
    }
}

// Usage
Drawing drawing = new Drawing();
drawing.addShape(new Circle(5));
drawing.addShape(new Rectangle(4, 6));
drawing.addShape(new Triangle(3, 4));

AreaCalculator areaCalc = new AreaCalculator();
drawing.accept(areaCalc);
System.out.println("Total area: " + areaCalc.getTotalArea());

ShapeDrawer drawer = new ShapeDrawer();
drawing.accept(drawer);
```

### Real-World Example: Employee System
```java
public interface Employee {
    void accept(Visitor visitor);
}

public class Developer implements Employee {
    private String name;
    private double salary;
    
    public Developer(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }
    
    public String getName() { return name; }
    public double getSalary() { return salary; }
    
    @Override
    public void accept(Visitor visitor) {
        visitor.visitDeveloper(this);
    }
}

public class Manager implements Employee {
    private String name;
    private double salary;
    
    public Manager(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }
    
    public String getName() { return name; }
    public double getSalary() { return salary; }
    
    @Override
    public void accept(Visitor visitor) {
        visitor.visitManager(this);
    }
}

public interface Visitor {
    void visitDeveloper(Developer dev);
    void visitManager(Manager mgr);
}

public class SalaryReport implements Visitor {
    private double totalSalary = 0;
    
    @Override
    public void visitDeveloper(Developer dev) {
        totalSalary += dev.getSalary();
        System.out.println("Developer: " + dev.getName() + " - $" + dev.getSalary());
    }
    
    @Override
    public void visitManager(Manager mgr) {
        totalSalary += mgr.getSalary();
        System.out.println("Manager: " + mgr.getName() + " - $" + mgr.getSalary());
    }
    
    public double getTotal() { return totalSalary; }
}
```

---

## Quick Reference Cheat Sheet

### Pattern Summary
| Pattern | Purpose | Complexity |
|---------|---------|------------|
| **Chain of Responsibility** | Pass requests along a chain | Medium |
| **Command** | Encapsulate requests as objects | Medium |
| **Interpreter** | Language grammar evaluation | High |
| **Iterator** | Traverse collections | Low |
| **Mediator** | Centralize complex communication | Medium |
| **Memento** | Capture and restore state | Low |
| **Observer** | One-to-many notifications | Low |
| **State** | Object behavior based on state | Medium |
| **Strategy** | Interchangeable algorithms | Low |
| **Template Method** | Algorithm skeleton | Low |
| **Visitor** | New operations without changing classes | High |

### When to Use Which
```
Need to pass request through chain?       → Chain of Responsibility
Need undo/redo functionality?               → Command
Need to parse expressions?                → Interpreter
Need to traverse collections?            → Iterator
Need to decouple components?              → Mediator
Need to save/restore state?               → Memento
Need to notify multiple objects?          → Observer
Need to change behavior with state?      → State
Need to swap algorithms?                  → Strategy
Need to define algorithm skeleton?        → Template Method
Need to add operations without changes?  → Visitor
```

### Common Use Cases
- **Chain of Responsibility**: Logging, validation, authentication
- **Command**: Undo/redo, queuing, transactions
- **Interpreter**: Expression parsers, regex engines
- **Iterator**: Collection traversal, file processing
- **Mediator**: Chat rooms, UI dialogs, controllers
- **Memento**: Game save points, document versioning
- **Observer**: Event systems, GUI updates, MVC
- **State**: State machines, order processing
- **Strategy**: Payment methods, sorting, compression
- **Template Method**: Frameworks, data processing
- **Visitor**: Tax calculators, report generators

---

*End of Behavioral Design Patterns Manual*