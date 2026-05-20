# Java Streams Reviewer
## Java Bootcamp Training - Software Engineer Track

---

## 1. Introduction to Streams

### What is a Stream?
A **Stream** in Java is a sequence of elements that can be processed in a pipeline of operations. Introduced in Java 8, streams provide a functional approach to processing collections.

### Stream vs Collection
| Aspect | Collection | Stream |
|--------|------------|--------|
| Storage | Stores elements | Does not store elements |
| Modification | Can modify | Does not modify source |
| Iteration | External iteration | Internal iteration |
| Traversal | Can be traversed multiple times | Can be traversed only once |
| Lazy/Eager | Eager (all data ready) | Lazy (until terminal op) |
| Size | Finite | Can be infinite |

### Creating a Stream
```java
import java.util.stream.*;
import java.util.*;

// From Collection
List<String> list = Arrays.asList("a", "b", "c");
Stream<String> stream1 = list.stream();

// From Array
String[] arr = {"x", "y", "z"};
Stream<String> stream2 = Arrays.stream(arr);

// From values
Stream<String> stream3 = Stream.of("a", "b", "c");

// Empty stream
Stream<String> empty = Stream.empty();

// Infinite stream (with limit)
Stream<Integer> naturals = Stream.iterate(1, n -> n + 1);
Stream<Double> randoms = Stream.generate(Math::random);

// Range
IntStream range1 = IntStream.range(1, 10);       // 1-9
IntStream range2 = IntStream.rangeClosed(1, 10); // 1-10
```

---

## 2. Intermediate Operations

Intermediate operations return a new stream and are **lazy** — they don't execute until a terminal operation is called.

### Filtering
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

Stream<Integer> evens = numbers.stream()
    .filter(n -> n % 2 == 0);  // 2, 4, 6, 8, 10

Stream<String> names = namesList.stream()
    .filter(name -> name.length() > 3)
    .filter(name -> name.startsWith("A"));

// Combine filters with logical AND (both conditions)
numbers.stream()
    .filter(n -> n > 5 && n < 8);  // 6, 7
```

### Mapping
```java
// transform elements
List<String> words = Arrays.asList("hello", "world");

Stream<String> upper = words.stream()
    .map(String::toUpperCase);  // HELLO, WORLD

Stream<Integer> lengths = words.stream()
    .map(String::length);       // 5, 5

// FlatMap - flatten nested structures
List<List<Integer>> nested = Arrays.asList(
    Arrays.asList(1, 2),
    Arrays.asList(3, 4)
);

Stream<Integer> flat = nested.stream()
    .flatMap(list -> list.stream());  // 1, 2, 3, 4
```

### Sorting
```java
List<Integer> nums = Arrays.asList(5, 2, 8, 1, 9);

Stream<Integer> sorted = nums.stream()
    .sorted();  // natural order: 1, 2, 5, 8, 9

Stream<Integer> reverse = nums.stream()
    .sorted(Comparator.reverseOrder());  // 9, 8, 5, 2, 1

List<String> names = Arrays.asList("Charlie", "Alice", "Bob");
names.stream()
    .sorted()  // Alice, Bob, Charlie
    .sorted(Comparator.comparing(String::length))  // by length
    .sorted(Comparator.comparing(String::length).reversed());  // longest first
```

### Distinct & Limit
```java
List<Integer> withDupes = Arrays.asList(1, 2, 2, 3, 3, 3, 4);

Stream<Integer> distinct = withDupes.stream()
    .distinct();  // 1, 2, 3, 4

Stream<Integer> limited = withDupes.stream()
    .limit(3);  // 1, 2, 2

// Combine for practical use
list.stream()
    .distinct()
    .limit(10)
    .collect(Collectors.toList());
```

### Peek
```java
// Debug: see elements as they pass through
Stream.of("one", "two", "three")
    .filter(s -> s.length() > 3)
    .peek(s -> System.out.println("Filtered: " + s))
    .map(String::toUpperCase)
    .forEach(System.out::println);
```

### Skip
```java
Stream.of(1, 2, 3, 4, 5)
    .skip(2)  // 3, 4, 5

// Pagination pattern
int page = 2;
int pageSize = 10;
list.stream()
    .skip((long) (page - 1) * pageSize)
    .limit(pageSize);
```

---

## 3. Terminal Operations

Terminal operations trigger the stream pipeline and produce a result.

### Collectors

#### Basic Collectors
```java
List<String> list = Stream.of("a", "b", "c")
    .collect(Collectors.toList());

Set<Integer> set = Stream.of(1, 2, 2, 3)
    .collect(Collectors.toSet());

// To ArrayList/HashSet
ArrayList<String> arrList = stream.collect(Collectors.toCollection(ArrayList::new));
HashSet<Integer> hashSet = intStream.collect(Collectors.toCollection(HashSet::new));
```

#### Joining
```java
List<String> words = Arrays.asList("Hello", "World");

String joined = words.stream()
    .collect(Collectors.joining());  // "HelloWorld"

String withSpace = words.stream()
    .collect(Collectors.joining(" "));  // "Hello World"

String withDelimiter = words.stream()
    .collect(Collectors.joining(", "));  // "Hello, World"

// With prefix/suffix
String formatted = words.stream()
    .collect(Collectors.joining(", ", "[", "]"));  // "[Hello, World]"
```

#### Summarizing Numbers
```java
List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);

IntSummaryStatistics stats = nums.stream()
    .collect(Collectors.summarizingInt(Integer::intValue));

stats.getSum();      // 15
stats.getAverage();  // 3.0
stats.getMin();      // 1
stats.getMax();      // 5
stats.getCount();    // 5
```

#### Partitioning & Grouping
```java
// Partition by predicate (always 2 groups)
List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);
Map<Boolean, List<Integer>> partitioned = nums.stream()
    .collect(Collectors.partitioningBy(n -> n > 3));
// {false=[1, 2, 3], true=[4, 5]}

// Group by property
List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "Anna");
Map<Character, List<String>> grouped = names.stream()
    .collect(Collectors.groupingBy(name -> name.charAt(0)));
// {A=[Alice, Anna], B=[Bob], C=[Charlie]}

// Grouping with counting
Map<Character, Long> countByFirstLetter = names.stream()
    .collect(Collectors.groupingBy(
        name -> name.charAt(0),
        Collectors.counting()
    ));

// Grouping with mapping
Map<Character, List<Integer>> lengthsByFirstLetter = names.stream()
    .collect(Collectors.groupingBy(
        name -> name.charAt(0),
        Collectors.mapping(String::length, Collectors.toList())
    ));

// Grouping with custom collector
Map<String, List<Person>> byDepartment = people.stream()
    .collect(Collectors.groupingBy(Person::getDepartment));
```

### Aggregation Operations
```java
List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);

int sum = nums.stream()
    .mapToInt(Integer::intValue)
    .sum();  // 15

OptionalInt max = nums.stream()
    .mapToInt(Integer::intValue)
    .max();  // OptionalInt[5]

OptionalInt min = nums.stream()
    .mapToInt(Integer::intValue)
    .min();  // OptionalInt[1]

OptionalDouble avg = nums.stream()
    .mapToInt(Integer::intValue)
    .average();  // OptionalDouble[3.0]

Optional<Integer> first = nums.stream()
    .findFirst();  // Optional[1]

Optional<Integer> any = nums.stream()
    .findAny();  // Optional (for parallel)
```

### Reduction
```java
// reduce(identity, accumulator)
List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);

Integer product = nums.stream()
    .reduce(1, (a, b) -> a * b);  // 120

String concatenated = Stream.of("a", "b", "c")
    .reduce("", (s1, s2) -> s1 + s2);  // "abc"

// reduce(accumulator) - no identity
Optional<Integer> sum = nums.stream()
    .reduce((a, b) -> a + b);  // Optional[15]

// reduce with three parameters
int result = nums.stream()
    .reduce(0, Integer::sum, Integer::sum);  // for parallel
```

### Match Operations
```java
List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);

boolean anyEven = nums.stream()
    .anyMatch(n -> n % 2 == 0);  // true

boolean allPositive = nums.stream()
    .allMatch(n -> n > 0);  // true

boolean noneNegative = nums.stream()
    .noneMatch(n -> n < 0);  // true
```

### forEach
```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

// forEach (terminal)
names.stream()
    .forEach(System.out::println);

// forEachOrdered (preserves order in parallel)
names.parallelStream()
    .forEachOrdered(System.out::println);

// forEach in Map
map.forEach((k, v) -> System.out.println(k + ": " + v));
```

### toArray
```java
String[] arr = Stream.of("a", "b", "c")
    .toArray(String[]::new);

Object[] objArr = Stream.of(1, 2, 3)
    .toArray();
```

---

## 4. Primitive Streams

Java provides specialized streams for primitives to avoid autoboxing overhead.

### IntStream, LongStream, DoubleStream
```java
// Create primitive streams
IntStream intStream = IntStream.of(1, 2, 3, 4, 5);
LongStream longStream = LongStream.range(1, 10);      // 1-9
DoubleStream doubleStream = DoubleStream.generate(() -> Math.random());

// From array
int[] arr = {1, 2, 3, 4, 5};
IntStream fromArray = Arrays.stream(arr);

// From String (chars)
IntStream chars = "hello".chars();  // IntStream of ASCII values

// Boxed streams
Stream<Integer> boxed = intStream.boxed();  // IntStream -> Stream<Integer>
```

### Numeric Operations
```java
IntStream.range(1, 6)
    .sum();  // 15

IntStream.rangeClosed(1, 5)
    .average()  // OptionalDouble[3.0]
    .orElse(0.0);

IntStream.of(5, 3, 1, 4, 2)
    .sorted()
    .findFirst();  // OptionalInt[1]
```

### Mapping Between Primitives
```java
IntStream.range(1, 6)
    .mapToLong(i -> (long) i)     // LongStream
    .mapToDouble(i -> i * 1.0)    // DoubleStream
    .mapToObj(i -> "Num: " + i);  // Stream<String>
```

---

## 5. Parallel Streams

Parallel streams split data into multiple chunks and process them concurrently using the ForkJoin pool.

### Creating Parallel Streams
```java
List<String> list = Arrays.asList("a", "b", "c");

// From collection
Stream<String> parallel1 = list.parallelStream();

// From stream
Stream<String> parallel2 = list.stream().parallel();

// Sequential from parallel
Stream<String> sequential = parallel1.sequential();
```

### Performance Comparison
```java
// Sequential
list.stream()
    .filter(...)
    .map(...)
    .collect(...);

// Parallel (may improve performance on large datasets)
list.parallelStream()
    .filter(...)
    .map(...)
    .collect(...);
```

### Order in Parallel Streams
```java
// forEach may be unordered
parallelStream.forEach(System.out::println);

// forEachOrdered preserves insertion order
parallelStream.forEachOrdered(System.out::println);

// unordered() - removes ordering guarantee (can be faster)
parallelStream
    .unordered()
    .distinct();
```

### When to Use Parallel Streams
- Large datasets (>10,000 elements)
- CPU-intensive operations
- No side effects or shared state
- Order doesn't matter (or handled specially)

### When NOT to Use
- Small datasets (overhead > benefit)
- Operations with dependencies
- Stateful operations
- I/O-bound operations
- When order must be preserved

---

## 6. Common Patterns & Examples

### Filter-Map-Collect Pattern
```java
// Get names of adults
List<Person> people = getPeople();
List<String> adultNames = people.stream()
    .filter(p -> p.getAge() >= 18)
    .map(Person::getName)
    .collect(Collectors.toList());

// Get prices of products in "Electronics"
List<Double> prices = products.stream()
    .filter(p -> p.getCategory().equals("Electronics"))
    .filter(p -> p.getPrice() < 1000)
    .map(Product::getPrice)
    .collect(Collectors.toList());
```

### Group and Aggregate
```java
// Count employees by department
Map<String, Long> countByDept = employees.stream()
    .collect(Collectors.groupingBy(
        Employee::getDepartment,
        Collectors.counting()
    ));

// Sum salary by department
Map<String, Double> salaryByDept = employees.stream()
    .collect(Collectors.groupingBy(
        Employee::getDepartment,
        Collectors.summingDouble(Employee::getSalary)
    ));

// Average age by city
Map<String, Double> avgAgeByCity = people.stream()
    .collect(Collectors.groupingBy(
        Person::getCity,
        Collectors.averagingInt(Person::getAge)
    ));

// Get highest paid by department
Map<String, Optional<Employee>> topEarners = employees.stream()
    .collect(Collectors.groupingBy(
        Employee::getDepartment,
        Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))
    ));
```

### Finding Min/Max
```java
// Find longest string
Optional<String> longest = strings.stream()
    .max(Comparator.comparingInt(String::length));

// Find youngest person
Optional<Person> youngest = people.stream()
    .min(Comparator.comparingInt(Person::getAge));

// With orElse defaults
String longestName = strings.stream()
    .max(Comparator.comparingInt(String::length))
    .orElse("");

Person youngestPerson = people.stream()
    .min(Comparator.comparingInt(Person::getAge))
    .orElse(new Person("Unknown", 0));
```

### FlatMap for Nested Data
```java
// Get all book titles from libraries
List<Library> libraries = getLibraries();
List<String> allTitles = libraries.stream()
    .flatMap(lib -> lib.getBooks().stream())
    .map(Book::getTitle)
    .collect(Collectors.toList());

// Get unique tags from all posts
Set<String> allTags = posts.stream()
    .flatMap(post -> post.getTags().stream())
    .collect(Collectors.toSet());
```

### Partitioning Practical Use
```java
// Separate passed/failed students
Map<Boolean, List<Student>> passed = students.stream()
    .collect(Collectors.partitioningBy(s -> s.getGrade() >= 60.0));

List<Student> passedList = passed.get(true);
List<Student> failedList = passed.get(false);

// Separate adults/children
Map<Boolean, List<Person>> byAge = people.stream()
    .collect(Collectors.partitioningBy(p -> p.getAge() >= 18));
```

### Counting & Matching
```java
long evens = numbers.stream()
    .filter(n -> n % 2 == 0)
    .count();

boolean hasVip = customers.stream()
    .anyMatch(c -> c.getTier().equals("VIP"));

boolean allActive = users.stream()
    .allMatch(u -> u.isActive());

boolean noneBanned = users.stream()
    .noneMatch(u -> u.getStatus().equals("BANNED"));
```

### Deduplication and Sorting
```java
// Unique elements with custom criteria
List<String> uniqueByLength = strings.stream()
    .distinct()  // exact duplicates
    .collect(Collectors.toCollection(
        () -> new TreeSet<>(Comparator.comparingInt(String::length))
    ));

// Top N elements
List<Integer> top5 = numbers.stream()
    .sorted(Comparator.reverseOrder())
    .limit(5)
    .collect(Collectors.toList());

// Elements between indices
List<String> subList = strings.stream()
    .skip(start)
    .limit(length)
    .collect(Collectors.toList());
```

### Converting Between Types
```java
// List to Set
Set<String> set = list.stream()
    .collect(Collectors.toSet());

// Set to List
List<String> list = set.stream()
    .collect(Collectors.toList());

// Array to List
List<String> list = Arrays.stream(array)
    .collect(Collectors.toList());

// List to Map
Map<String, Integer> map = list.stream()
    .collect(Collectors.toMap(
        Function.identity(),
        String::length
    ));

// List to array
String[] array = list.stream()
    .toArray(String[]::new);
```

---

## 7. Lambda Expressions in Streams

### Syntax
```java
// Full syntax
(x, y) -> { return x + y; }

// Expression body (no return needed)
x -> x * 2

// Multiple statements
list.stream()
    .filter(x -> {
        boolean result = x > 0;
        System.out.println("Checking: " + x);
        return result;
    });
```

### Method References
```java
// Constructor reference
Stream<Person> people = names.stream()
    .map(Person::new);

// Static method
Stream<Integer> squares = numbers.stream()
    .map(Math::abs);

// Instance method on object
Stream<String> upper = names.stream()
    .map(String::toUpperCase);

// Instance method on parameter
Stream<Boolean> tests = strings.stream()
    .map(String::isEmpty);
```

### Common Functional Interfaces
```java
Predicate<T>      // T -> boolean  (filter)
Function<T, R>    // T -> R        (map)
Consumer<T>       // T -> void     (forEach)
Supplier<T>       // () -> T       (generate)
UnaryOperator<T>  // T -> T        (map with same type)
BinaryOperator<T> // (T, T) -> T   (reduce)
```

---

## 8. Optional with Streams

### Terminal Operations Return Optional
```java
Optional<T> first = stream.findFirst();
Optional<T> any   = stream.findAny();
Optional<T> min   = stream.min(comparator);
Optional<T> max   = stream.max(comparator);
Optional<T> reduce = stream.reduce((a, b) -> ...);
```

### Handling Optional
```java
// Get value or default
String result = optionalString.orElse("default");
String result = optionalString.orElseGet(() -> computeDefault());

// Throw if empty
String result = optionalString.orElseThrow();

// Transform if present
Optional<Integer> length = optionalString.map(String::length);

// FlatMap for nested optionals
Optional<String> nested = opt1.flatMap(v1 -> opt2);

// Conditional processing
optional.ifPresent(value -> System.out.println(value));
optional.ifPresentOrElse(
    value -> System.out.println(value),
    () -> System.out.println("No value")
);
```

### Optional in Collectors
```java
// Get single result from filter
Optional<String> firstLong = strings.stream()
    .filter(s -> s.length() > 10)
    .findFirst();

String longStr = firstLong.orElse("too short");

// collectingAndThen
String result = stream
    .collect(Collectors.collectingAndThen(
        Collectors.toList(),
        list -> list.isEmpty() ? "empty" : list.size() + " items"
    ));
```

---

## 9. Common Pitfalls

### Stream Already Consumed
```java
Stream<String> stream = list.stream();
// ...
stream.forEach(System.out::println);
stream.forEach(System.out::println);  // IllegalStateException!
```

### Forgetting to Import
```java
import java.util.stream.*;      // Streams
import java.util.stream.Collectors;  // Collectors
```

### Type Mismatch with Primitives
```java
List<Integer> list = Arrays.asList(1, 2, 3);
// Wrong: list.stream().sum() - won't compile
// Right:
int sum = list.stream()
    .mapToInt(Integer::intValue)
    .sum();
```

### Accidental Sequential in Parallel
```java
// This is still parallel
list.parallelStream()
    .unordered()
    .filter(...)
    .toList();

// This forces sequential
list.parallelStream()
    .sequential()  // DON'T do this
    .filter(...);
```

### Stateful vs Stateless
```java
// BAD: Stateful in stateless context
Set<Integer> seen = new HashSet<>();
list.parallelStream()
    .filter(x -> seen.add(x))  // Unpredictable in parallel!
    .collect(Collectors.toList());

// GOOD: Stateless
list.parallelStream()
    .filter(x -> x > 0)  // Stateless - safe for parallel
    .collect(Collectors.toList());
```

### Modifying Source During Stream
```java
List<String> list = new ArrayList<>();
list.add("a");
Stream<String> s = list.stream();
list.add("b");  // Modifying source while streaming - unpredictable!

// DON'T DO THIS
```

### null Values in Streams
```java
// null causes NPE in most operations
list.stream()
    .filter(Objects::nonNull)  // Filter nulls first
    .map(String::toUpperCase)
    .collect(Collectors.toList());

// Optional.ofNullable for null-safe processing
Stream.ofNullable(value)
    .filter(...)
    .collect(Collectors.toList());
```

### Confusing filter().map() vs map().filter()
```java
// Filter then map (preferred)
items.stream()
    .filter(item -> item.isValid())   // Process fewer items
    .map(Item::getName)
    .collect(toList());

// Map then filter (processes more)
items.stream()
    .map(item -> item.getName())       // Map all items first
    .filter(name -> !name.isEmpty())
    .collect(toList());
```

### Overusing Streams
```java
// NOT everything needs streams
// Simple loops can be clearer
for (String s : list) {
    System.out.println(s);
}

// vs
list.stream().forEach(System.out::println);

// Use streams when you need:
// - Filtering, mapping, reducing
// - Pipeline operations
// - Functional style benefits
```

---

## Quick Reference Cheat Sheet

### Stream Lifecycle
```
Create Stream → Intermediate Operations → Terminal Operation
   (source)         (lazy, chainable)          (executes pipeline)
```

### Intermediate Operations
```
.filter(Predicate)    // Keep matching elements
.map(Function)        // Transform each element
.flatMap(Function)    // Flatten and transform
.sorted()             // Sort elements
.distinct()           // Remove duplicates
.limit(n)             // Keep first n elements
.skip(n)              // Skip first n elements
.peek(Consumer)       // Debug/log elements
```

### Terminal Operations
```
.collect(...)         // Build collection/result
.forEach(...)         // Execute for each element
.count()              // Count elements
.min() / .max()       // Find min/max
.sum() / .average()   // Numeric aggregation
.reduce(...)          // Combine to single value
.anyMatch()           // Any match predicate?
.allMatch()           // All match predicate?
.noneMatch()          // None match predicate?
.findFirst()          // Get first element
.findAny()            // Get any element (parallel-safe)
.toArray()            // Convert to array
```

### Collectors
```
toList()              // List<T>
toSet()               // Set<T>
toMap(k, v)           // Map<K, V>
joining()             // String joining
counting()            // Long count
summingInt()          // Sum integers
averagingInt()        // Average
maxBy() / minBy()     // Optional<T>
groupingBy()          // Group by classifier
partitioningBy()      // Partition by predicate
collectingAndThen()   // Transform result
```

### Primitive Streams
```
IntStream / LongStream / DoubleStream
.range(start, end)        // exclusive end
.rangeClosed(start, end)  // inclusive end
.of(...)                  // of values
.mapToObj()               // to object stream
.boxed()                  // to boxed stream
```

---

*End of Java Streams Reviewer*
