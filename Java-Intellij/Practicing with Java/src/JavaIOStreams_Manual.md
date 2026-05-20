# Java I/O accenture.iostreams Reviewer
## Java Bootcamp Training - Software Engineer Track

---

## 1. I/O Overview

### Stream Concept
- **Stream**: A sequence of data flowing from source to destination
- **Input Stream**: Reads data from a source (file, network, etc.)
- **Output Stream**: Writes data to a destination

### Java I/O Classification
```
java.io Package
├── Byte accenture.iostreams (for binary data)
│   ├── InputStream (abstract)
│   └── OutputStream (abstract)
├── Character accenture.iostreams (for text data)
│   ├── Reader (abstract)
│   └── Writer (abstract)
├── Buffered accenture.iostreams (for performance)
├── Object accenture.iostreams (for serialization)
└── File I/O
```

### Common Imports
```java
import java.io.*;
import java.nio.file.*; // Java 7+ NIO
```

---

## 2. File I/O with java.io.File

### File Class
```java
import java.io.File;

File file = new File("data.txt");

// Check properties
file.exists();              // true if file exists
file.isFile();              // true if it's a file
file.isDirectory();         // true if it's a directory
file.length();              // file size in bytes
file.lastModified();        // timestamp

// Create/Delete
file.createNewFile();       // creates empty file (may throw IOException)
file.mkdir();               // creates directory
file.delete();              // deletes file/directory

// Paths
file.getName();             // "data.txt"
file.getPath();             // "/path/to/data.txt"
file.getAbsolutePath();     // "/full/path/to/data.txt"
```

### File Separators
```java
// Cross-platform file paths
String sep = File.separator;          // "/" on Unix, "\" on Windows
String path = "dir" + sep + "file.txt";

// Java 7+ path builder
Path path = Paths.get("dir", "subdir", "file.txt");
```

---

## 3. Byte accenture.iostreams

### FileInputStream & FileOutputStream
Reading and writing raw bytes (binary data).

```java
import java.io.FileInputStream;
import java.io.FileOutputStream;

// Writing bytes to a file
// try with resources
byte[] data = {72, 101, 108, 108, 111}; // "Hello" in ASCII
try (FileOutputStream fos = new FileOutputStream("output.bin")) {
    fos.write(data);                   // write entire array
    fos.write(65);                     // write single byte (A)
} catch (IOException e) { e.printStackTrace(); }

// Reading bytes from a file
try (FileInputStream fis = new FileInputStream("output.bin")) {
    int byteRead;
    while ((byteRead = fis.read()) != -1) {  // -1 = end of file
        System.out.print((char) byteRead);   // prints "HelloA"
    }
} catch (IOException e) { e.printStackTrace(); }
```

### BufferedOutputStream & BufferedInputStream
Wraps byte streams to reduce disk access.

```java
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;

// Writing with buffering (more efficient for multiple writes)
try (BufferedOutputStream bos = new BufferedOutputStream(
        new FileOutputStream("output.bin"))) {
    bos.write("Hello".getBytes());
    bos.write(" World".getBytes());
    bos.flush();                       // force write to disk
} catch (IOException e) { e.printStackTrace(); }

// Reading with buffering
try (BufferedInputStream bis = new BufferedInputStream(
        new FileInputStream("output.bin"))) {
    byte[] buffer = new byte[1024];
    int bytesRead;
    while ((bytesRead = bis.read(buffer)) != -1) {
        System.out.write(buffer, 0, bytesRead);
    }
} catch (IOException e) { e.printStackTrace(); }
```

---

## 4. Character accenture.iostreams

### FileReader & FileWriter
Reading and writing text using Unicode characters.

```java
import java.io.FileReader;
import java.io.FileWriter;

// Writing text to a file
try (FileWriter fw = new FileWriter("output.txt")) {
    fw.write("Hello World");
    fw.write('\n');
    fw.write("Line 2");
    fw.flush();
} catch (IOException e) { e.printStackTrace(); }

// Reading text from a file
try (FileReader fr = new FileReader("output.txt")) {
    int ch;
    StringBuilder sb = new StringBuilder();
    while ((ch = fr.read()) != -1) {
        sb.append((char) ch);
    }
    System.out.println(sb.toString());
} catch (IOException e) { e.printStackTrace(); }
```

### BufferedReader & BufferedWriter
Buffered version for better performance.

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;

// Writing with buffering
try (BufferedWriter bw = new BufferedWriter(
        new FileWriter("output.txt"))) {
    bw.write("Line 1");
    bw.newLine();                      // cross-platform line break
    bw.write("Line 2");
    bw.flush();
} catch (IOException e) { e.printStackTrace(); }

// Reading with buffering - most common pattern
try (BufferedReader br = new BufferedReader(
        new FileReader("output.txt"))) {
    String line;
    while ((line = br.readLine()) != null) {  // returns null at EOF
        System.out.println(line);
    }
} catch (IOException e) { e.printStackTrace(); }
```

---

## 5. Scanner Class

### Reading User Input & Files
```java
import java.util.Scanner;

// Reading from console
Scanner scanner = new Scanner(System.in);
System.out.print("Enter your name: ");
String name = scanner.nextLine();           // reads entire line
int age = scanner.nextInt();               // reads next int
double salary = scanner.nextDouble();       // reads next double

// Reading from a file
try (Scanner fileScanner = new Scanner(new File("data.txt"))) {
    while (fileScanner.hasNext()) {
        if (fileScanner.hasNextInt()) {
            int num = fileScanner.nextInt();
            System.out.println(num);
        } else {
            String word = fileScanner.next();
            System.out.println(word);
        }
    }
} catch (IOException e) { e.printStackTrace(); }

// Reading with delimiter
scanner.useDelimiter(",");                  // comma-separated
while (scanner.hasNext()) {
    System.out.println(scanner.next());
}
```

---

## 6. Try-with-Resources

### Auto-Closing Resources
Ensures streams are closed even if exceptions occur.

```java
// BEFORE Java 7 (manual close)
FileReader fr = null;
try {
    fr = new FileReader("file.txt");
    // use fr
} catch (IOException e) {
    e.printStackTrace();
} finally {
    if (fr != null) {
        try { fr.close(); } catch (IOException e) { }
    }
}

// AFTER Java 7 (try-with-resources)
try (FileReader fr = new FileReader("file.txt");
     BufferedReader br = new BufferedReader(fr)) {
    String line = br.readLine();
} catch (IOException e) {
    e.printStackTrace();
} // resources auto-closed
```

---

## 7. Object Serialization

### Serializable Objects
```java
import java.io.Serializable;

// Objects must implement Serializable to be saved/loaded
class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    String name;
    int age;
    transient String password;  // won't be serialized
}

// Writing objects to file
try (ObjectOutputStream oos = new ObjectOutputStream(
        new FileOutputStream("person.dat"))) {
    Person person = new Person();
    person.name = "Alice";
    person.age = 25;
    oos.writeObject(person);
} catch (IOException e) { e.printStackTrace(); }

// Reading objects from file
try (ObjectInputStream ois = new ObjectInputStream(
        new FileInputStream("person.dat"))) {
    Person person = (Person) ois.readObject();
    System.out.println(person.name);    // "Alice"
    System.out.println(person.age);     // 25
} catch (IOException | ClassNotFoundException e) { e.printStackTrace(); }
```

---

## 8. PrintWriter & PrintStream

### Easy Output Formatting
```java
import java.io.PrintWriter;
import java.io.PrintStream;

// PrintWriter (character-based)
try (PrintWriter pw = new PrintWriter(new FileWriter("output.txt"))) {
    pw.println("Line 1");
    pw.print("No newline, ");
    pw.println("but this has one");
    pw.printf("Name: %s, Age: %d%n", "Bob", 30);  // formatted output
    pw.format("Pi: %.2f%n", 3.14159);             // printf alternative
} catch (IOException e) { e.printStackTrace(); }

// PrintStream (byte-based) - System.out uses this
PrintStream ps = new PrintStream("output.txt");
ps.println("Hello");
ps.print(123);
ps.printf("%.2f", 3.14159);
ps.close();

// System.out replacement
PrintStream out = new PrintStream("log.txt");
System.setOut(out);
System.out.println("This goes to file");
```

---

## 9. StringReader & StringWriter

### In-Memory String I/O
```java
import java.io.StringReader;
import java.io.StringWriter;

// StringWriter - build strings using I/O patterns
try (StringWriter sw = new StringWriter()) {
    sw.write("Start");
    sw.write(" Middle");
    sw.write(" End");
    String result = sw.toString();  // "Start Middle End"
    System.out.println(result);
} catch (IOException e) { e.printStackTrace(); }

// StringReader - parse strings like accenture.iostreams.files
try (StringReader sr = new StringReader("Line1\nLine2\nLine3")) {
    int ch;
    while ((ch = sr.read()) != -1) {
        System.out.print((char) ch);
    }
} catch (IOException e) { e.printStackTrace(); }

// Common use: format complex strings
try (StringWriter sw = new StringWriter()) {
    PrintWriter pw = new PrintWriter(sw);
    pw.printf("Name: %-10s | Age: %3d | Salary: %10.2f%n",
              "Alice", 25, 75000.50);
    pw.printf("Name: %-10s | Age: %3d | Salary: %10.2f%n",
              "Bob", 30, 82000.00);
    String formatted = sw.toString();
    System.out.println(formatted);
}
```

---

## 10. DataInputStream & DataOutputStream

### Primitive Data Types
```java
import java.io.DataOutputStream;
import java.io.DataInputStream;

// Writing primitive types
try (DataOutputStream dos = new DataOutputStream(
        new FileOutputStream("data.bin"))) {
    dos.writeInt(42);
    dos.writeDouble(3.14159);
    dos.writeBoolean(true);
    dos.writeUTF("Hello");              // UTF-8 encoded string
    dos.writeChars("Hi");               // characters (no length prefix)
} catch (IOException e) { e.printStackTrace(); }

// Reading primitive types (must match write order)
try (DataInputStream dis = new DataInputStream(
        new FileInputStream("data.bin"))) {
    int num = dis.readInt();           // 42
    double pi = dis.readDouble();      // 3.14159
    boolean flag = dis.readBoolean();  // true
    String str = dis.readUTF();        // "Hello"
} catch (IOException e) { e.printStackTrace(); }
```

---

## 11. NIO (New I/O) - Java 7+

### Files Utility Class
```java
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// Simple file operations
Path path = Paths.get("data.txt");

// Read entire file as lines
List<String> lines = Files.readAllLines(path);
for (String line : lines) {
    System.out.println(line);
}

// Read entire file as bytes
byte[] bytes = Files.readAllBytes(path);
String content = new String(bytes);

// Write lines to file
List<String> content = Arrays.asList("Line 1", "Line 2");
Files.write(path, content);

// Write bytes to file
Files.write(path, "Hello".getBytes());

// Append to file
Files.write(path, "More content".getBytes(),
            StandardOpenOption.APPEND);

// Copy/Move accenture.iostreams.files
Files.copy(Paths.get("source.txt"), Paths.get("dest.txt"));
Files.move(Paths.get("old.txt"), Paths.get("new.txt"));
```

### BufferedReader/Writer with NIO Paths
```java
import java.nio.file.Files;
import java.nio.file.Paths;

// BufferedReader from Path
try (BufferedReader reader = Files.newBufferedReader(path)) {
    String line;
    while ((line = reader.readLine()) != null) {
        System.out.println(line);
    }
} catch (IOException e) { e.printStackTrace(); }

// BufferedWriter from Path
try (BufferedWriter writer = Files.newBufferedWriter(path)) {
    writer.write("Hello");
    writer.newLine();
    writer.write("World");
} catch (IOException e) { e.printStackTrace(); }
```

### FileVisitor (Recursive Directory Operations)
```java
import java.nio.file.*;
import java.nio.file.attribute.*;

Path start = Paths.get(".");

Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        System.out.println(file);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
        System.out.println("Entering: " + dir);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
        System.out.println("Leaving: " + dir);
        return FileVisitResult.CONTINUE;
    }
});
```

---

## 12. Console Class

### System Console
```java
import java.io.Console;

// Get system console
Console console = System.console();

if (console != null) {
    // Read password (masked input)
    char[] password = console.readPassword("Enter password: ");

    // Read line
    String input = console.readLine("Enter name: ");

    // Formatted output
    console.format("Hello, %s%n", input);
    console.printf("Password length: %d%n", password.length);
}
```

---

## 13. ByteArrayInputStream & ByteArrayOutputStream

### In-Memory Byte I/O
```java
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

// ByteArrayInputStream - read from byte array
byte[] data = {65, 66, 67};  // ASCII for "ABC"
try (ByteArrayInputStream bais = new ByteArrayInputStream(data)) {
    int b;
    while ((b = bais.read()) != -1) {
        System.out.print((char) b);  // "ABC"
    }
}

// ByteArrayOutputStream - write to byte array
try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
    baos.write("Hello".getBytes());
    baos.write(32);  // space character
    baos.write("World".getBytes());
    byte[] result = baos.toByteArray();  // [72, 101, ...]
    String str = baos.toString();        // "Hello World"
}
```

---

## 14. PipedInputStream & PipedOutputStream

### Inter-Thread Communication
```java
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

// Producer thread
PipedOutputStream out = new PipedOutputStream();
PipedInputStream in = new PipedInputStream(out);  // connected

// Writing from producer
new Thread(() -> {
    try {
        out.write("Message".getBytes());
        out.close();
    } catch (IOException e) { e.printStackTrace(); }
}).start();

// Reading from consumer
new Thread(() -> {
    try {
        int b;
        while ((b = in.read()) != -1) {
            System.out.print((char) b);
        }
    } catch (IOException e) { e.printStackTrace(); }
}).start();
```

---

## 15. SequenceInputStream

### Combining Multiple Input accenture.iostreams
```java
import java.io.SequenceInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

// Concatenate multiple accenture.iostreams.files
Vector<InputStream> streams = new Vector<>();
streams.add(new FileInputStream("file1.txt"));
streams.add(new FileInputStream("file2.txt"));
streams.add(new FileInputStream("file3.txt"));

try (SequenceInputStream seq = new SequenceInputStream(streams.elements());
     FileOutputStream out = new FileOutputStream("combined.txt")) {
    int b;
    while ((b = seq.read()) != -1) {
        out.write(b);
    }
} catch (IOException e) { e.printStackTrace(); }
```

---

## 16. Line Number Input Stream

### Tracking Line Numbers
```java
import java.io.LineNumberInputStream;
import java.io.FileInputStream;

try (LineNumberInputStream lnis = new LineNumberInputStream(
        new FileInputStream("script.txt"))) {
    String line;
    BufferedReader br = new BufferedReader(lnis);
    while ((line = br.readLine()) != null) {
        System.out.println("Line " + lnis.getLineNumber() + ": " + line);
    }
} catch (IOException e) { e.printStackTrace(); }
```

---

## 17. PushbackInputStream

### Looking Ahead in accenture.iostreams
```java
import java.io.PushbackInputStream;
import java.io.FileInputStream;

// Read one character, push it back if needed
try (PushbackInputStream pis = new PushbackInputStream(
        new FileInputStream("data.txt"), 1)) {  // 1 byte pushback buffer
    int b;
    while ((b = pis.read()) != -1) {
        if (b == '#') {
            int next = pis.read();
            if (next != '#') {
                pis.unread(next);  // put it back
            }
        }
        System.out.print((char) b);
    }
} catch (IOException e) { e.printStackTrace(); }
```

---

## 18. RandomAccessFile

### Non-Sequential File Access
```java
import java.io.RandomAccessFile;

// Read/Write at any position in file
try (RandomAccessFile raf = new RandomAccessFile("data.txt", "rw")) {
    raf.writeBytes("Hello World");

    raf.seek(6);                   // move to position 6
    System.out.println(raf.readLine());  // "World"

    raf.seek(0);                   // back to start
    System.out.println(raf.readLine());  // "Hello"

    raf.seek(0);
    raf.writeBytes("Hi");          // overwrites "He"

    // Current position
    long pos = raf.getFilePointer();  // current position in file
    long len = raf.length();         // file length
} catch (IOException e) { e.printStackTrace(); }

// Mode: "r" (read), "rw" (read/write), "rws" (sync every write), "rwd" (sync data only)
```

---

## 19. Charset (Character Encoding)

### Handling Different Encodings
```java
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

// Available charsets
Charset utf8 = Charset.forName("UTF-8");
Charset iso = Charset.forName("ISO-8859-1");
Charset ascii = Charset.forName("US-ASCII");

// Encode string to bytes
String text = "Hello";
byte[] bytes = text.getBytes(utf8);

// Decode bytes to string
String decoded = new String(bytes, utf8);

// Read file with specific encoding
try (BufferedReader reader = Files.newBufferedReader(
        Paths.get("file.txt"), StandardCharsets.UTF_8)) {
    // ...
}

// Write file with specific encoding
try (BufferedWriter writer = Files.newBufferedWriter(
        Paths.get("file.txt"), StandardCharsets.UTF_8)) {
    // ...
}

// Detect encoding (basic)
Path file = Paths.get("data.txt");
byte[] bytes = Files.readAllBytes(file);
Charset detected = CharsetDetector.detect(bytes);
```

---

## 20. Common I/O Patterns

### Reading File Contents
```java
// Java 7+: Simple case
String content = new String(Files.readAllBytes(Paths.get("file.txt")));

// Lines to List
List<String> lines = Files.readAllLines(Paths.get("file.txt"));

// BufferedReader (larger accenture.iostreams.files)
try (BufferedReader br = Files.newBufferedReader(Paths.get("file.txt"))) {
    String line;
    while ((line = br.readLine()) != null) {
        // process line
    }
}

// Scanner (when parsing tokens)
try (Scanner sc = new Scanner(Paths.get("file.txt"))) {
    while (sc.hasNext()) {
        String token = sc.next();
    }
}
```

### Writing File Contents
```java
// Java 7+: Simple case
Files.write(Paths.get("file.txt"), "content".getBytes());

// Multiple lines
List<String> lines = Arrays.asList("Line 1", "Line 2");
Files.write(Paths.get("file.txt"), lines);

// BufferedWriter (larger accenture.iostreams.files)
try (BufferedWriter bw = Files.newBufferedWriter(Paths.get("file.txt"))) {
    bw.write("content");
    bw.newLine();
}

// PrintWriter (formatted output)
try (PrintWriter pw = new PrintWriter("file.txt")) {
    pw.printf("%s %d %.2f%n", "Hello", 42, 3.14);
}
```

### Copying Files
```java
// Java 7+ (simple)
Files.copy(Paths.get("source.txt"), Paths.get("dest.txt"));

// With options
Files.copy(Paths.get("source.txt"), Paths.get("dest.txt"),
           StandardCopyOption.REPLACE_EXISTING,
           StandardCopyOption.COPY_ATTRIBUTES);

// Manual byte copy (for large accenture.iostreams.files)
try (InputStream in = new FileInputStream("source.txt");
     OutputStream out = new FileOutputStream("dest.txt")) {
    byte[] buffer = new byte[8192];
    int bytesRead;
    while ((bytesRead = in.read(buffer)) != -1) {
        out.write(buffer, 0, bytesRead);
    }
}
```

---

## 21. Exception Handling

### Common I/O Exceptions
```java
// IOException - general I/O errors
// FileNotFoundException - file doesn't exist (extends IOException)
// EOFException - unexpected end of file (extends IOException)
// UnsupportedEncodingException - encoding not supported

try {
    FileReader fr = new FileReader("nonexistent.txt");
} catch (FileNotFoundException e) {
    System.err.println("File not found: " + e.getMessage());
} catch (IOException e) {
    System.err.println("I/O error: " + e.getMessage());
}

// Handling multiple exceptions (Java 7+)
try (FileReader fr = new FileReader("file.txt")) {
    // ...
} catch (FileNotFoundException e) {
    // file doesn't exist
} catch (IOException e) {
    // other I/O errors
}
```

---

## Quick Reference Cheat Sheet

### Choosing the Right Stream
```
Reading text lines?          → BufferedReader.readLine()
Reading tokens/primitives?  → Scanner
Reading binary data?        → FileInputStream / BufferedInputStream
Reading objects?           → ObjectInputStream
Reading primitive values?   → DataInputStream

Writing text lines?         → BufferedWriter / PrintWriter
Writing binary data?        → FileOutputStream / BufferedOutputStream
Writing objects?            → ObjectOutputStream
Writing primitive values?   → DataOutputStream
```

### Stream Chaining Pattern
```java
// Character stream chain (text)
BufferedReader br = new BufferedReader(new FileReader("file.txt"));
BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));

// Byte stream chain (binary)
BufferedInputStream bis = new BufferedInputStream(new FileInputStream("file.bin"));
BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("output.bin"));

// Object stream chain
ObjectOutputStream oos = new ObjectOutputStream(
    new BufferedOutputStream(
        new FileOutputStream("data.obj")));
```

### Key Methods Summary
| Class | Key Methods |
|-------|--------------|
| `InputStream` | `read()`, `read(byte[])`, `read(byte[], off, len)`, `skip()`, `available()`, `close()` |
| `OutputStream` | `write(int)`, `write(byte[])`, `flush()`, `close()` |
| `Reader` | `read()`, `read(char[])`, `readLine()`, `skip()`, `close()` |
| `Writer` | `write(int)`, `write(String)`, `write(char[])`, `append()`, `newLine()`, `flush()`, `close()` |
| `Scanner` | `next()`, `nextInt()`, `nextDouble()`, `nextLine()`, `hasNext()`, `hasNextInt()` |
| `Files` | `readAllBytes()`, `readAllLines()`, `write()`, `copy()`, `move()`, `delete()`, `exists()` |

### AutoCloseable Resources
All streams implement `Closeable` (extends `AutoCloseable`). Always use try-with-resources to ensure proper cleanup.

---

*End of Java I/O accenture.iostreams Reviewer*