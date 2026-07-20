# COMP 1020 — Week 2 Notes

## Table of Contents
1. [Stack vs Heap / Object References](#1-stack-vs-heap--object-references)
2. [Static Methods & Variables](#2-static-methods--variables)
3. [Constants (`final`)](#3-constants-final)
4. [Comparing Objects: `equals`, `clone`, `compareTo`](#4-comparing-objects-equals-clone-compareto)
5. [File I/O Basics](#5-file-io-basics)
6. [Exception Handling](#6-exception-handling)
7. [Reading From Files](#7-reading-from-files)
8. [Writing To Files](#8-writing-to-files)
9. [String Processing](#9-string-processing)
10. [Runtime (Unchecked) Exceptions](#10-runtime-unchecked-exceptions)
11. [The Exception Hierarchy & Exception Objects](#11-the-exception-hierarchy--exception-objects)
12. [Throwing & Catching Exceptions](#12-throwing--catching-exceptions)
13. [Collections & Arrays](#13-collections--arrays)
14. [ArrayList](#14-arraylist)
15. [Primitive Wrapper Classes](#15-primitive-wrapper-classes)
16. [Arrays vs ArrayList Cheat Sheet](#16-arrays-vs-arraylist-cheat-sheet)
17. [CRUD on Objects/Collections](#17-crud-on-objectscollections)

---

## 1. Stack vs Heap / Object References

- **Declaration vs Instantiation**: declaring a variable just makes a name; instantiation (`new`) actually creates the object in memory.
- Variable names store either a **primitive value** or an **address reference** pointing to an object. This is true whether the variable itself lives on the stack or inside another object on the heap.
- Object references act like **nicknames** — two variable names can point to the **same object**.
- A **shallow copy** (e.g. copying a reference via getter/setter) means both variables point to the same object, so changing one affects the other.
- **Java String Pool**: Strings with the same content point to the same String object in memory, unless you explicitly force a new object to be created.

\`\`\`java
public class ReferenceExample {
    public static void main(String[] args) {
        Student s1 = new Student("Sam"); // instantiation
        Student s2 = s1; // s2 is just another "nickname" for the same object

        s2.setName("Alex");
        System.out.println(s1.getName()); // prints "Alex" -- same object!

        // String pool example
        String a = "hello";
        String b = "hello";
        System.out.println(a == b); // true, both point to the same pooled String

        String c = new String("hello"); // forces a new object
        System.out.println(a == c); // false, different objects in memory
    }
}
\`\`\`

---

## 2. Static Methods & Variables

- `static` = belongs to the **class level**, not to any individual object/instance.
- A class can be made up entirely of static methods/variables (e.g. `Math`). You can even make the constructor `private` so no instances can ever be created.
- **Static methods**: only one copy exists; called using the class name (e.g. `Math.min(a, b)`), not an instance.
- **Static variables**: only one copy shared across all instances. Useful for ID counters, or counting total objects created.

\`\`\`java
class Student {
    // instance variable — unique per object
    private int studentId;
    private String name;

    // static variables — shared across ALL Student objects
    private static int idCounter = 1000;
    private static int studentCount = 0;

    public Student(String name) {
        this.name = name;
        this.studentId = idCounter++;   // assign unique ID
        studentCount++;                 // increment shared counter
    }

    // static method — called on the CLASS, not an instance
    public static int totalEnrolment() {
        return studentCount;
    }
}

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student("Sam");
        Student s2 = new Student("Alex");

        System.out.println(Student.totalEnrolment()); // 2
        System.out.println(Math.min(4, 9));            // built-in static method example
    }
}
\`\`\`

---

## 3. Constants (`final`)

- Use `final` for values that should **never change**.
- Often combined with `static` when the constant belongs at the class level (same for every instance).
- Naming convention: `UPPERCASE_WITH_UNDERSCORES`.

\`\`\`java
class Circle {
    public static final double PI = 3.141592653589793; // constant

    public static double area(double radius) {
        return PI * radius * radius;
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println(Circle.PI);
        System.out.println(Circle.area(3));
    }
}
\`\`\`

---

## 4. Comparing Objects: `equals`, `clone`, `compareTo`

All classes inherit from `Object` by default, which provides `toString()`, `equals()`, and `clone()`. `compareTo()` comes from the separate `Comparable` interface.

### Cheat Sheet

| Method | Source | Default Behavior | Common Override Action |
|---|---|---|---|
| `toString()` | `Object` class | `ClassName@HexHashCode` | Print legible field data |
| `equals()` | `Object` class | Evaluates memory address (`==`) | Evaluates matching field values |
| `compareTo()` | `Comparable` interface | Does not exist by default | Dictates sorting order (`<`, `=`, `>`) |
| `clone()` | `Object` class | Shallow copy (throws exception if not `Cloneable`) | Duplicate values / deep copy elements |

### `equals()` — checking value equality (not just reference equality)

\`\`\`java
class Student {
    private String name;
    private int studentId;

    public Student(String name, int studentId) {
        this.name = name;
        this.studentId = studentId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Student)) return false;
        Student other = (Student) obj;
        return this.studentId == other.studentId && this.name.equals(other.name);
    }
}
\`\`\`

### `clone()` — deep copy via copy constructor (cleaner alternative to `.clone()`)

\`\`\`java
class Student {
    private String name;
    private int studentId;

    // normal constructor
    public Student(String name, int studentId) {
        this.name = name;
        this.studentId = studentId;
    }

    // copy constructor -- takes an object of the same type and deep copies it
    public Student(Student other) {
        this.name = other.name;
        this.studentId = other.studentId;
    }
}

public class Main {
    public static void main(String[] args) {
        Student original = new Student("Sam", 1001);
        Student copy = new Student(original); // deep copy, separate object in memory
    }
}
\`\`\`

### `compareTo()` — defining sort order

- Returns **0** if objects are equal in sort order
- Returns **negative** if `this` comes before the parameter
- Returns **positive** if `this` comes after the parameter
- Strings compare by Unicode value — capital letters come before lowercase (`'A'=65`, `'a'=97`), so `"Apple".compareTo("apple")` is negative.

\`\`\`java
class Student implements Comparable<Student> {
    private String name;
    private int studentId;

    private static int idCounter = 1000;

    public Student(String name) {
        this.name = name;
        this.studentId = idCounter++;
    }

    @Override
    public int compareTo(Student other) {
        // sort alphabetically by name
        return this.name.compareTo(other.name);
    }
}

public class Main {
    public static void main(String[] args) {
        Student a = new Student("Zoe");
        Student b = new Student("Amy");

        System.out.println(a.compareTo(b)); // positive: "Zoe" comes after "Amy"

        // useful for sorting a list of Students
        // Collections.sort(studentList);
    }
}
\`\`\`

---

## 5. File I/O Basics

- Errors/exceptions are common with file operations — files can be missing, renamed, moved, etc.
- You **must** `open` and `close` connections when reading/writing files.
- Once `close()` is called, the connection can no longer be used.
- The `File` class is used for **metadata / management** (create, delete, check existence) — **not** for reading/writing content.

\`\`\`java
import java.io.File;

public class FileMetadataExample {
    public static void main(String[] args) {
        File file = new File("myFile.txt");

        System.out.println(file.exists());       // does the file exist?
        System.out.println(file.getName());       // file name
        System.out.println(file.length());        // size in bytes
        System.out.println(file.canRead());        // readable?

        // creating/deleting
        // file.createNewFile();
        // file.delete();
    }
}
\`\`\`

---

## 6. Exception Handling

- **Checked exceptions**: the compiler forces you to handle them (e.g. `IOException`, `FileNotFoundException`). You must either `catch` them or add `throws` to the method signature.
- **Try-with-resources**: automatically closes the connection for you, even if an exception occurs.

\`\`\`java
import java.io.*;

public class TryWithResourcesExample {
    public static void main(String[] args) {
        // try-with-resources: automatically closes 'reader' when block ends
        try (BufferedReader reader = new BufferedReader(new FileReader("myFile.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
\`\`\`

\`\`\`java
// try-catch-finally
public class TryCatchFinallyExample {
    public static void main(String[] args) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("myFile.txt"));
            System.out.println(reader.readLine());
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } finally {
            // ALWAYS runs, whether an exception happened or not
            try {
                if (reader != null) reader.close();
            } catch (IOException e) {
                System.out.println("Error closing file.");
            }
        }
    }
}
\`\`\`

---

## 7. Reading From Files

Three readers, ordered from lowest-level to highest-level:

| Class | Purpose | Key Method |
|---|---|---|
| `FileReader` | Reads raw characters directly. No buffering. | `read()` returns `int` char code |
| `BufferedReader` | Wraps another reader to buffer input. Efficient line-by-line reading. | `readLine()` returns `String` or `null` |
| `Scanner` | Parses text into tokens/primitive types using delimiters. | `next()`, `nextInt()`, `nextLine()` |

\`\`\`java
import java.io.FileReader;
import java.io.IOException;

public class FileReaderExample {
    public static void main(String[] args) throws IOException {
        try (FileReader fr = new FileReader("myFile.txt")) {
            int c;
            while ((c = fr.read()) != -1) { // read() returns int char code, -1 = end of file
                System.out.print((char) c);
            }
        }
    }
}
\`\`\`

\`\`\`java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedReaderExample {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("myFile.txt"))) {
            String line;
            while ((line = br.readLine()) != null) { // null means end of file
                System.out.println(line);
            }
        }
    }
}
\`\`\`

\`\`\`java
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScannerFileExample {
    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner sc = new Scanner(new File("myFile.txt"))) {
            while (sc.hasNextInt()) {          // reads based on tokens
                int num = sc.nextInt();
                System.out.println(num);
            }
            // sc.hasNextLine() / sc.nextLine() also available
        }
    }
}
\`\`\`

---

## 8. Writing To Files

Three writers, ordered from lowest-level to highest-level:

| Class | Purpose | Key Method |
|---|---|---|
| `FileWriter` | Writes raw characters directly. No buffering. | `write(String)` |
| `BufferedWriter` | Wraps another writer to buffer output. Reduces disk access overhead. | `write()`, `newLine()` |
| `PrintWriter` | Formatted printing. Has `print()`, `println()`, `printf()`. | `print()`, `println()`, `printf()` |

\`\`\`java
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterExample {
    public static void main(String[] args) throws IOException {
        try (FileWriter fw = new FileWriter("out.txt")) {
            fw.write("Hello World");
        }
    }
}
\`\`\`

\`\`\`java
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedWriterExample {
    public static void main(String[] args) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("out.txt"))) {
            bw.write("Line 1");
            bw.newLine(); // writes a line separator
            bw.write("Line 2");
        }
    }
}
\`\`\`

\`\`\`java
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PrintWriterExample {
    public static void main(String[] args) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter("out.txt"))) {
            pw.println("Total:");
            pw.printf("Age: %d%n", 25); // formatted printing, like System.out.printf
        }
    }
}
\`\`\`

**The 3 general steps for any File I/O code:**
\`\`\`java
// 1. Create the reader/writer and connect to the file (open the connection)
// 2. Loop to read/write data (this is where most custom logic lives)
// 3. Close the connection (or let try-with-resources do it automatically)
\`\`\`

---

## 9. String Processing

> **Note:** Strings are **immutable** — every string method **returns a brand-new String**; it never modifies the original in memory.

\`\`\`java
public class StringMethodsExample {
    public static void main(String[] args) {
        String s = "This is my string of text.";

        System.out.println(s.toUpperCase());  // "THIS IS MY STRING OF TEXT."
        System.out.println(s);                // unchanged: "This is my string of text."

        System.out.println(s.toLowerCase());  // "this is my string of text."

        System.out.println(s.replace('i', 'u'));      // "Thus us my strung of text."
        System.out.println(s.replace("is", "ese"));    // "These ese my string of text."

        // regex = "regular expression" -- a pattern to match characters
        System.out.println(s.replaceAll("[^a-zA-Z0-9]", "")); // removes non-alphanumeric chars
        System.out.println(s.replaceAll("\\\\s+", ""));         // removes all whitespace

        System.out.println(s.trim());          // removes leading/trailing whitespace
        System.out.println(s.indexOf("is"));    // 2 (first occurrence), -1 if not found
        System.out.println(s.charAt(2));        // 'i'

        System.out.println(s.substring(2, 6));  // "is is" (start inclusive, end exclusive)
        System.out.println(s.substring(8));     // "my string of text." (to end of string)

        // split() breaks a String into an array using a regex delimiter
        String[] tokens = s.split("\\\\s+");
        // tokens = {"This", "is", "my", "string", "of", "text."}
        for (String token : tokens) {
            System.out.println(token);
        }
    }
}
\`\`\`

### Practice: Email & Phone Validation

\`\`\`java
public class ValidationExample {
    public static boolean isValidEmail(String email) {
        return email.matches("^[\\\\w.+-]+@[\\\\w-]+\\\\.[a-zA-Z]{2,}$");
    }

    public static boolean isValidPhone(String phone) {
        return phone.matches("^\\\\d{3}-\\\\d{3}-\\\\d{4}$");
    }

    public static void main(String[] args) {
        System.out.println(isValidEmail("myname@host.somewhere")); // true
        System.out.println(isValidPhone("432-123-4569"));          // true
    }
}
\`\`\`

---

## 10. Runtime (Unchecked) Exceptions

- **Unchecked / runtime exceptions**: compiler does **not** force you to handle them, since they usually indicate a **bug**.
- Generally you fix the bug rather than wrapping everything in try/catch — **except** when your algorithm intentionally risks the exception (e.g. testing whether a token is a valid int).

\`\`\`java
public class RuntimeExceptionExamples {
    public static void main(String[] args) {
        // myInt = 10 / 0;                          // ArithmeticException
        // myArray[-3] = 2;                          // ArrayIndexOutOfBoundsException
        // "hello".substring(2, 15);                 // StringIndexOutOfBoundsException
        // Integer.parseInt("not a number");         // NumberFormatException
        // Book b; b.getTitle();                     // NullPointerException (b is null)

        // Example: intentionally risking NumberFormatException while parsing tokens
        String[] tokens = {"10", "hello", "25"};
        for (String token : tokens) {
            try {
                int num = Integer.parseInt(token);
                System.out.println("Valid number: " + num);
            } catch (NumberFormatException e) {
                System.out.println("Not a number: " + token);
            }
        }
    }
}
\`\`\`

---

## 11. The Exception Hierarchy & Exception Objects

- Every exception is a **class**, and all exception classes inherit from a top-level `Exception` class.
- Two major branches: `RuntimeException` (e.g. `NumberFormatException`) and `IOException` (e.g. `FileNotFoundException`).
- `Error` is a separate, more severe class (e.g. `OutOfMemoryError`, `StackOverflowError`) — not typically handled in code.

**Common methods every exception has:**
\`\`\`java
String getMessage()      // get a message describing what happened
void printStackTrace()   // prints the call stack at the point the exception was created
String toString()        // returns exception type + message, useful for printing
\`\`\`

\`\`\`java
public class ExceptionObjectExample {
    public static void main(String[] args) {
        Exception whoops = new Exception("Uh oh! That wasn't supposed to happen");
        System.out.println(whoops);      // calls toString() -> "java.lang.Exception: Uh oh!..."
        whoops.printStackTrace();        // prints stack trace to console
        System.out.println(whoops.getMessage()); // "Uh oh! That wasn't supposed to happen"
    }
}
\`\`\`

---

## 12. Throwing & Catching Exceptions

- `throw` sends the exception up the call stack until something catches it (or the program terminates).
- **Catch the most specific exception first.** Chain catch blocks from most specific → most general, since only ONE catch block runs.
- `finally` always runs, whether an exception was thrown or not.

\`\`\`java
public class ThrowCatchExample {

    public static void main(String[] args) {
        // Custom exception example
        try {
            checkValue(-5);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught: " + e.getMessage());
        }

        // Correct catch ordering: SPECIFIC first, GENERAL last
        try {
            int[] arr = new int[3];
            arr[5] = 10; // throws ArrayIndexOutOfBoundsException
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Index problem: " + e.getMessage());
        } catch (Exception e) {
            // general catch-all goes LAST
            System.out.println("General error: " + e.getMessage());
        } finally {
            System.out.println("This always runs.");
        }
    }

    static void checkValue(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Value cannot be negative");
        }
    }
}
\`\`\`

\`\`\`java
// Solution 1: CATCH IT (handle locally, no 'throws' needed on signature)
public static void methodX() {
    try {
        // read from file
    } catch (IOException ioe) {
        System.out.println("EXCEPTION! ACK!");
    }
}

// Solution 2: THROW IT (caller must now catch or throw IOException too)
public static void methodY() throws IOException {
    // read from file -- if it fails, exception passes to whoever called methodY
}
\`\`\`

> **Best practices:**
> - Don't use exceptions as if/else logic.
> - Don't catch `RuntimeException` unless you have a very good reason — fix the bug instead.
> - Check if a reference is `null` before calling a method on it, instead of catching `NullPointerException`.
> - Don't wrap your entire codebase in one giant try/catch block.

---

## 13. Collections & Arrays

- A **Collection** = a group of individual objects ("elements") stored together. Java's `Collection` interface has subtypes: `List`, `Set`, `Queue`.
- **Array**: fixed size, contiguous memory, random access via index.
- **Partially-filled array**: an oversized array where only the first *N* elements count as "real" data — you must track a separate `size` variable.

\`\`\`java
public class ArrayExample {
    public static void main(String[] args) {
        int[] fixedArray = new int[5]; // fixed size of 5, cannot grow

        // partially-filled array pattern
        int[] data = new int[10];
        int size = 0; // tracks how many elements are actually "used"

        data[size++] = 4;
        data[size++] = 8;
        // only data[0] and data[1] are considered "real" data; rest are unused
        System.out.println("Size: " + size);
    }
}
\`\`\`

---

## 14. ArrayList

- Built-in, **dynamic/resizable** array class (`java.util.ArrayList`), implements the `List` collection.

**Key features:**
- Dynamic sizing (grows/shrinks automatically)
- Ordered (elements stay in the order added)
- Only holds **Objects** (no primitives directly — see wrapper classes)
- Allows duplicates (and `null`), unlike `Set`

\`\`\`java
import java.util.ArrayList;

public class ArrayListCreationExample {
    public static void main(String[] args) {
        ArrayList<String> strList = new ArrayList<String>(); // holds Strings

        ArrayList<Book> bookList = new ArrayList<>(); // diamond operator, type inferred

        ArrayList objectList = new ArrayList(); // raw type -- holds any Object (avoid this!)
    }
}
\`\`\`

---

## 15. Primitive Wrapper Classes

- ArrayLists can only hold **Objects**, so primitives need a **wrapper class**.
- Wrapper objects are **immutable**, like Strings.
- Conversion between primitive ↔ wrapper is handled automatically (autoboxing/unboxing).

| Primitive | Wrapper Class |
|---|---|
| `int` | `Integer` |
| `double` | `Double` |
| `boolean` | `Boolean` |
| `char` | `Character` |
| `float` | `Float` |
| `long` | `Long` |

\`\`\`java
public class WrapperClassExample {
    public static void main(String[] args) {
        int i = 5;
        Integer iObject = new Integer(5); // explicit constructor (old style)
        Integer jObject = 5;              // autoboxing -- wraps automatically

        i = iObject;              // auto-unboxing -- unwraps automatically
        int j = iObject.intValue(); // manual unwrap (rarely needed)

        System.out.println(i);              // 5
        System.out.println(iObject.intValue()); // 5
        System.out.println(jObject);        // 5, no need for .intValue()
    }
}
\`\`\`

\`\`\`java
import java.util.ArrayList;

public class ArrayListStorageExample {
    public static void main(String[] args) {
        int[] iA = {5, 7, 9}; // array: length = 3, fixed

        ArrayList<Integer> iL = new ArrayList<Integer>();
        iL.add(5);
        iL.add(7);
        iL.add(9); // ArrayList: size = 3 (internal capacity may be larger)

        // updating values
        iA[0] = 4;      // array: direct overwrite
        iL.set(0, 4);   // ArrayList: creates a new Integer object, old one is "orphaned"
    }
}
\`\`\`

> **Note:** There's a trade-off between `convenience/overhead` (ArrayList) vs `speed/manual coding` (array). Choose the right structure for the situation.

---

## 16. Arrays vs ArrayList Cheat Sheet

| Action | Array | ArrayList |
|---|---|---|
| Declare | `String[] a = new String[10];` | `ArrayList<String> a = new ArrayList<String>();` |
| Size | `a.length` (cannot change) | `a.size()` (changes after each modification) |
| Read | `a[0]` | `a.get(0)` |
| Update | `a[0] = "test";` | `a.set(0, "test");` |
| Add | *(no direct method — must resize manually)* | `a.add("new");` |
| Remove | *(no direct method — must shift manually)* | `a.remove(0);` |
| Contents | Contains any type | Contains objects only |

\`\`\`java
import java.util.ArrayList;

public class ArraysVsArrayListExample {
    public static void main(String[] args) {
        // --- Array ---
        String[] arr = new String[3];
        arr[0] = "apple";
        System.out.println(arr.length); // 3, fixed forever

        // --- ArrayList ---
        ArrayList<String> list = new ArrayList<>();
        list.add("apple");
        list.add("banana");
        System.out.println(list.size()); // 2, grows dynamically

        list.set(0, "avocado"); // update
        list.remove(1);          // delete by index
        System.out.println(list.get(0)); // read: "avocado"
    }
}
\`\`\`

---

## 17. CRUD on Objects/Collections

**CRUD** = **C**reate, **R**ead, **U**pdate, **D**elete — the core operations for working with objects and collections. Arrays are objects too!

\`\`\`java
public class CrudArrayExample {
    public static void main(String[] args) {
        // CREATE
        int[] example = new int[10];

        // READ
        System.out.println(example[0]);

        // UPDATE
        example[0] = 100;

        // DELETE (element) -- shift everything over or overwrite it
        for (int i = 0; i < example.length - 1; i++) {
            example[i] = example[i + 1];
        }

        // DELETE (whole array) -- set to null, or reassign with 'new'
        example = null;
    }
}
\`\`\`

\`\`\`java
import java.util.ArrayList;

public class CrudArrayListExample {
    public static void main(String[] args) {
        // CREATE
        ArrayList<String> words = new ArrayList<>();
        words.add("banana");
        words.add("apple");
        words.add("banana"); // duplicates allowed

        // READ
        System.out.println(words.get(0));

        // UPDATE
        words.set(0, "cherry");

        // DELETE (element)
        words.remove("apple"); // remove by value
        words.remove(0);        // remove by index

        // DELETE (whole list)
        words.clear();
    }
}
\`\`\`

### Practice Example: Build an Alphabetical List of Words

\`\`\`java
import java.util.ArrayList;
import java.util.Collections;

public class AlphabeticalListExample {
    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>();
        words.add("banana");
        words.add("apple");
        words.add("cherry");

        Collections.sort(words); // uses String's natural compareTo() order

        for (String word : words) {
            System.out.println(word);
        }
        // Output: apple, banana, cherry
    }
}
\`\`\`

### Practice Example: Remove Duplicates From a List of Words

\`\`\`java
import java.util.ArrayList;

public class RemoveDuplicatesExample {
    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>();
        words.add("apple");
        words.add("banana");
        words.add("apple");
        words.add("cherry");
        words.add("banana");

        ArrayList<String> uniqueWords = new ArrayList<>();
        for (String word : words) {
            if (!uniqueWords.contains(word)) { // CRUD: Read (check) before Create
                uniqueWords.add(word);
            }
        }

        System.out.println(uniqueWords); // [apple, banana, cherry]
    }
}
\`\`\`