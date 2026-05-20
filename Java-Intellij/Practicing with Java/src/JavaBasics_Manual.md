# Java Basics Reviewer
## Java Bootcamp Training - Software Engineer Track

---

## 1. Variables

### Declaration & Initialization
```java
int age = 25;              // declare and initialize
String name = "Alice";     // reference type
boolean isActive = true;   // boolean
double salary = 75000.50;  // double precision
```

### Naming Conventions
- **Variables**: `camelCase` (`maxCount`, `userAge`)
- **Constants**: `UPPER_SNAKE_CASE` (`MAX_SIZE`, `DEFAULT_NAME`)
- **Classes**: `PascalCase` (`Person`, `BankAccount`)
- **Packages**: lowercase (`com.company.project`)

### Scope
- **Local**: Inside methods/blocks
- **Instance**: In class, non-static
- **Static**: Marked with `static` keyword
- **Block**: Inside `{}` (e.g., inside an `if` block)

### Key Patterns
```java
public class User {
    static int count = 0;           // static (class-level)
    private String name;            // instance

    public void demo() {
        int local = 10;            // local
        if (true) {
            int blockVar = 20;      // block scope
        }
    }
}
```

---

## 2. Data Types

### Primitive Types (8 total)
| Type | Size | Range |
|------|------|-------|
| `byte` | 1B | -128 to 127 |
| `short` | 2B | -32,768 to 32,767 |
| `int` | 4B | ~±2 billion |
| `long` | 8B | ±9 quintillion |
| `float` | 4B | ~±3.4e38 |
| `double` | 8B | ~±1.8e308 |
| `char` | 2B | Unicode characters |
| `boolean` | 1B | `true`/`false` |

### Reference Types
- **Classes**: `String`, `Object`, custom classes
- **Arrays**: `int[]`, `String[]`, `double[][]`
- **Interfaces**: `List`, `Map`, `Set`

### Type Conversion
```java
// Widening (automatic)
int i = 100;
long l = i;          // int → long (automatic)

// Narrowing (manual, data loss possible)
double d = 65.9;
int n = (int) d;     // double → int (cast required)
```

---

## 3. Math Operators

### Arithmetic Operators
```java
+   // addition
-   // subtraction
*   // multiplication
/   // division (integer division truncates decimal)
// Example:
int a = 10 / 3;     // a = 3 (not 3.33...)
double b = 10.0 / 3.0;  // b = 3.333...
%   // modulo (remainder)
// Example:
int c = 10 % 3;     // c = 1
```

### Increment/Decrement
```java
int x = 5;
x++;                // x = 6 (post-increment)
++x;                // x = 7 (pre-increment)
x--;                // x = 6
--x;                // x = 5
```

### Relational & Logical
```java
// Relational
==   // equal
!=   // not equal
>    // greater than
<    // less than
>=   // greater or equal
<=   // less or equal

// Logical
&&   // AND (short-circuits)
||   // OR (short-circuits)
!    // NOT

// Short-circuit example:
if (obj != null && obj.isValid()) { ... }
```

### Assignment Operators
```java
+=   // a += 5  →  a = a + 5
-=   // a -= 5  →  a = a - 5
*=   // a *= 5  →  a = a * 5
/=   // a /= 5  →  a = a / 5
%=   // a %= 5  →  a = a % 5
```

---

## 4. Arrays and Loops

### Array Declaration
```java
// One-dimensional
int[] nums = new int[5];              // array of 5 ints
int[] vals = {1, 2, 3, 4, 5};        // initialize with values

// Two-dimensional
int[][] matrix = new int[3][4];       // 3 rows, 4 columns
int[][] grid = {
    {1, 2, 3},
    {4, 5, 6},
    {7, 8, 9}
};

// Accessing
int x = nums[0];                     // first element (0-indexed)
matrix[1][2];                        // row 1, column 2
nums.length;                         // 5
```

### Loop Types
```java
// Traditional for loop
for (int i = 0; i < 5; i++) {
    System.out.println(i);
}

// Enhanced for-each (read-only)
for (int num : nums) {
    System.out.println(num);
}

// While loop
int i = 0;
while (i < 5) {
    System.out.println(i);
    i++;
}

// Do-while (executes at least once)
do {
    System.out.println(i);
    i++;
} while (i < 5);

// Break & Continue
for (int i = 0; i < 10; i++) {
    if (i == 3) continue;           // skip this iteration
    if (i == 7) break;               // exit loop
    System.out.println(i);
}
```

---

## 5. Methods

### Method Structure
```java
access_modifier return_type methodName(param1, param2) {
    // method body
    return value;
}

// Examples:
public static int add(int a, int b) {
    return a + b;
}

public void printMessage(String msg) {
    System.out.println(msg);
}

private static double calculate(double radius) {
    return Math.PI * radius * radius;
}
```

### Overloading
**Same name, different parameters** (compile-time polymorphism)
```java
int add(int a, int b) { return a + b; }
double add(double a, double b) { return a + b; }
int add(int a, int b, int c) { return a + b + c; }
```

### Recursion
**Method calls itself** — must have base case
```java
public static int factorial(int n) {
    if (n <= 1) return 1;           // base case
    return n * factorial(n - 1);   // recursive call
}
// factorial(5) = 5 * 4 * 3 * 2 * 1 = 120
```

### Varargs
```java
public static int sum(int... numbers) {
    int total = 0;
    for (int n : numbers) total += n;
    return total;
}

sum(1, 2, 3);    // returns 6
sum(1, 2, 3, 4, 5);  // returns 15
```

---

## 6. Strings

### String Properties
- **Immutable** — cannot be changed after creation
- Stored in **String Pool** (memory optimization)

### Common Methods
```java
String s = "Hello World";

s.length();                    // 11
s.charAt(0);                   // 'H'
s.substring(0, 5);             // "Hello"
s.indexOf("World");            // 6
s.indexOf("o");                // 4 (first occurrence)
s.lastIndexOf("o");            // 7 (last occurrence)
s.contains("World");           // true
s.startsWith("Hello");         // true
s.endsWith("World");           // true
s.equals("hello world");       // false (case-sensitive)
s.equalsIgnoreCase("hello world"); // true
s.toLowerCase();               // "hello world"
s.toUpperCase();               // "HELLO WORLD"
s.trim();                      // removes leading/trailing spaces
s.replace("World", "Java");    // "Hello Java"
s.split(" ");                  // ["Hello", "World"]
s.isEmpty();                   // false
s.isBlank();                   // false (Java 11+)

// String → primitive
int num = Integer.parseInt("123");
double dub = Double.parseDouble("3.14");
boolean bool = Boolean.parseBoolean("true");

// Primitive → String
String str = String.valueOf(123);
String str2 = Integer.toString(123);
```

### StringBuilder (Mutable)
```java
StringBuilder sb = new StringBuilder();
sb.append("Hello");
sb.append(" ");
sb.append("World");
sb.toString();                  // "Hello World"

sb.insert(5, "Java ");         // "Hello Java World"
sb.delete(5, 10);              // "Hello World"
sb.reverse();                   // "dlroW olleH"
```

---

## 7. Maps

### Key Characteristics
- **Key-Value** pairs
- Keys are **unique**
- Values can be **null**
- `Map` is an **interface**

### HashMap (Unordered)
```java
import java.util.HashMap;
import java.util.Map;

Map<String, Integer> ages = new HashMap<>();

ages.put("Alice", 25);
ages.put("Bob", 30);
ages.put("Charlie", 35);

ages.get("Alice");              // 25
ages.getOrDefault("Dave", 0);   // 0 (key not found)
ages.containsKey("Bob");       // true
ages.containsValue(30);        // true
ages.remove("Bob");            // removes entry
ages.size();                   // 2
ages.isEmpty();                // false

// Iterate over entries
for (Map.Entry<String, Integer> entry : ages.entrySet()) {
    System.out.println(entry.getKey() + ": " + entry.getValue());
}

// Iterate over keys
for (String key : ages.keySet()) {
    System.out.println(key);
}

// Iterate over values
for (Integer val : ages.values()) {
    System.out.println(val);
}

// Java 8+ forEach
ages.forEach((k, v) -> System.out.println(k + ": " + v));
```

### TreeMap (Sorted by Key)
```java
import java.util.TreeMap;

TreeMap<String, Integer> sorted = new TreeMap<>();
sorted.put("Banana", 2);
sorted.put("Apple", 1);
sorted.put("Cherry", 3);

// Keys are sorted: Apple, Banana, Cherry
sorted.firstKey();              // "Apple"
sorted.lastKey();               // "Cherry"
sorted.headMap("Cherry");       // submap for keys < "Cherry"
```

---

## 8. Lists

### Key Characteristics
- **Ordered** collection (index-based)
- **Allows duplicates**
- **Dynamic size** (vs fixed-size arrays)
- `List` is an **interface**

### ArrayList (Most common)
```java
import java.util.ArrayList;
import java.util.List;

List<String> names = new ArrayList<>();

names.add("Alice");
names.add("Bob");
names.add("Alice");              // duplicate allowed

names.get(0);                    // "Alice"
names.set(1, "Charlie");         // replace at index
names.remove(0);                  // remove by index
names.remove("Bob");              // remove by object
names.size();                    // 2
names.isEmpty();                  // false
names.contains("Alice");          // true
names.indexOf("Alice");          // 0
names.lastIndexOf("Alice");       // 1

// Sublist
List<String> sub = names.subList(0, 2);
```

### Iteration Patterns
```java
// Traditional loop
for (int i = 0; i < names.size(); i++) {
    System.out.println(names.get(i));
}

// Enhanced for-each
for (String name : names) {
    System.out.println(name);
}

// Iterator (for removal during iteration)
Iterator<String> it = names.iterator();
while (it.hasNext()) {
    String name = it.next();
    if (name.equals("Bob")) {
        it.remove();
    }
}

// Java 8+ forEach
names.forEach(name -> System.out.println(name));
names.forEach(System.out::println);
```

### LinkedList (Insert/Delete at head/tail — O(1))
```java
import java.util.LinkedList;

LinkedList<String> linked = new LinkedList<>();
linked.addFirst("Start");
linked.addLast("End");
linked.add(1, "Middle");

linked.getFirst();               // "Start"
linked.getLast();                // "End"
linked.removeFirst();
linked.removeLast();
```

---

## 9. Sets

### Key Characteristics
- **No duplicates** allowed
- **Unordered** (except TreeSet)
- **At most one null** element
- `Set` is an **interface**

### HashSet (Unordered, fastest)
```java
import java.util.HashSet;
import java.util.Set;

Set<String> fruits = new HashSet<>();

fruits.add("Apple");
fruits.add("Banana");
fruits.add("Apple");              // ignored (duplicate)

fruits.size();                    // 2
fruits.contains("Apple");        // true
fruits.remove("Banana");
fruits.isEmpty();                // false

// Iteration (no guaranteed order)
for (String fruit : fruits) {
    System.out.println(fruit);
}

fruits.forEach(System.out::println);
```

### TreeSet (Sorted, natural order)
```java
import java.util.TreeSet;

TreeSet<Integer> numbers = new TreeSet<>();
numbers.add(5);
numbers.add(2);
numbers.add(8);
numbers.add(2);                   // ignored

// Sorted: 2, 5, 8
numbers.first();                  // 2
numbers.last();                   // 8
numbers.lower(5);                // 2 (element < 5)
numbers.higher(5);               // 8 (element > 5)
numbers.headSet(5);              // {2}
numbers.tailSet(5);              // {5, 8}
```

### HashSet vs TreeSet vs LinkedHashSet
| Implementation | Order | Speed | Null |
|----------------|-------|-------|------|
| `HashSet` | None | O(1) | One |
| `TreeSet` | Sorted | O(log n) | No |
| `LinkedHashSet` | Insertion | O(1) | One |

---

## Quick Reference Cheat Sheet

### Common Imports
```java
import java.util.*;                          // Lists, Maps, Sets
import java.util.stream.*;                   // accenture.iostreams (Java 8+)
import java.util.Arrays;
import java.util.Collections;
```

### Choosing Collections
```
Need key-value pairs?        → Map (HashMap)
Need ordered + unique?       → TreeSet
Need ordered + duplicates?   → List (ArrayList)
Need fast + unique?          → HashSet
Need insertion order?        → LinkedHashSet / LinkedHashMap
```

### Common Pitfalls
- **Strings are immutable** — use `StringBuilder` for concatenation in loops
- **Use `.equals()` not `==`** to compare String values
- **ArrayList vs LinkedList** — ArrayList for random access, LinkedList for frequent add/remove at ends
- **HashMap allows one null key** — TreeMap does not
- **Avoid raw types** — use `List<String>` not just `List`
- **Integer comparison** — use `.equals()` or `.compareTo()` not `==`

---

*End of Java Basics Reviewer*
