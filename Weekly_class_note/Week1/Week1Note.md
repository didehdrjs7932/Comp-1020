# COMP 1020 — Week 1 Notes

## Table of Contents
1. [Java is a Strongly Typed Language](#1-java-is-a-strongly-typed-language)
2. [Primitive Types vs Object Types](#2-primitive-types-vs-object-types)
3. [Printing](#3-printing)
4. [Integer Division](#4-integer-division)
5. [Scope](#5-scope)
6. [Constants](#6-constants)
7. [Scanner (User Input)](#7-scanner-user-input)
8. [Type Conversions](#8-type-conversions)
9. [Boolean Operators & Truth Table](#9-boolean-operators--truth-table)
10. [Relational Operators (NOT for Strings!)](#10-relational-operators-not-for-strings)
11. [Conditionals (If/Else)](#11-conditionals-ifelse)
12. [Loops](#12-loops)
13. [Escape Characters](#13-escape-characters)
14. [String Methods](#14-string-methods)
15. [Math Methods](#15-math-methods)
16. [Arrays](#16-arrays)
17. [Array Copying: Shallow vs Deep Copy](#17-array-copying-shallow-vs-deep-copy)
18. [OOP Basics — Key Definitions](#18-oop-basics--key-definitions)
19. [Declaration vs Instantiation, and null](#19-declaration-vs-instantiation-and-null)
20. [Instance Methods](#20-instance-methods)
21. [The toString() Method](#21-the-tostring-method)
22. [Access Modifiers](#22-access-modifiers)
23. [Encapsulation](#23-encapsulation)
24. [Getters and Setters](#24-getters-and-setters)
25. [References and Copying Objects](#25-references-and-copying-objects)
26. [Garbage Collection](#26-garbage-collection)
27. [Key Takeaways (Pre-Exam Checklist)](#27-key-takeaways-pre-exam-checklist)

---

## 1. Java is a Strongly Typed Language

- Every value has a **type**, and you must declare the type when creating a variable.
- Unlike Python, you cannot create a variable without a type.

```java
sampleNumber = 20;      // ERROR! No type declared
int sampleNumber = 20;  // correct -- must declare the datatype first
```

---

## 2. Primitive Types vs Object Types

### Primitives
- Examples: `int`, `long`, `float`, `double`, `boolean`, `char`
- Start with a **lowercase** letter (good hint!)
- The **value itself** is stored directly in stack memory
- **No methods!**

### Objects
- Examples: `String`, all arrays, `Scanner`, your own classes
- Start with an **uppercase** letter (classes start with capitals — another hint!)
- The object lives in **heap** memory; the variable stores the **address (reference)** in the stack
- **Have methods** (e.g., `String` has `trim()`, `isBlank()`, `charAt()`)

```java
public class PrimitiveVsObjectExample {
    public static void main(String[] args) {
        int x = 5;              // primitive: the value 5 stored directly on the stack
        String s = "Hello";     // object: stack holds only the address, "Hello" lives in the heap

        // A String is basically an array of chars: "Hello" -> ['H','e','l','l','o']
        System.out.println(s.charAt(0)); // 'H' -- only objects have methods like this
    }
}
```

---

## 3. Printing

```java
System.out.println("Hello");  // prints then adds a newline (\n) automatically
System.out.print("Hello");    // no newline
System.out.printf("%d items%n", 5); // formatted output
```

```java
public class PrintingExample {
    public static void main(String[] args) {
        System.out.print("Hello");
        System.out.print("World");
        // output: HelloWorld

        System.out.println("Hello");
        System.out.println("World");
        // output:
        // Hello
        // World
    }
}
```

### Concatenation — very common in Java

```java
public class ConcatenationExample {
    public static void main(String[] args) {
        // In Python you might do: name = "Emily"; print(f"My name is {name}")

        // In Java, use + to concatenate:
        String name = "Emily";
        System.out.println("My name is " + name);
    }
}
```

---

## 4. Integer Division

> Integers do **not** round. They simply **drop the decimal part**.

```java
public class IntegerDivisionExample {
    public static void main(String[] args) {
        int numX = 5;
        int numY = 10;
        int answer = numX / numY;           // 0 (NOT 0.5!)

        double answerDouble = numX / numY;  // still 0.0
        // int / int performs integer division FIRST, then assigns the result

        // Fix 1: declare as doubles
        double doubleX = 5;
        double doubleY = 10;
        answerDouble = doubleX / doubleY;   // 0.5, correct

        // Fix 2: casting
        answerDouble = (double) numX / numY; // cast numX to double before dividing -> 0.5
    }
}
```

---

## 5. Scope

- Scope is determined by braces `{ }`.
- A variable declared inside a block cannot be used outside it.

```java
public class ScopeExample {
    public static void main(String[] args) {
        boolean isValid = true;

        if (isValid) {
            int testInteger = 4;
        }
        // System.out.println(testInteger); // ERROR! Out of scope -- Java doesn't know testInteger here

        // You CAN re-declare it outside, but it makes code unclear -- not a good idea
        int testInteger = 5;
        System.out.println(testInteger);
    }
}
```

---

## 6. Constants

- Use the `final` keyword + `UPPERCASE_SNAKE_CASE` naming
- Once set, the value **cannot be reassigned**
- Use for values that are specific and not meant to change (e.g., `PST_RATE`)

```java
public class ConstantsExample {
    public static void main(String[] args) {
        final int TEST_CONSTANT = 100;
        // TEST_CONSTANT = 2; // ILLEGAL! Throws a compiler error
        System.out.println(TEST_CONSTANT);
    }
}
```

> **Note:** For objects, `final` locks the **address (reference)**, not the contents.
> e.g., a `final ArrayList` can still have items added/removed.
> (Don't do this though — it's considered bad practice and reduces code clarity!)

---

## 7. Scanner (User Input)

- Requires `import java.util.Scanner;` at the top of the file
- Works with console input, and can also parse data from files or Strings
- Must be **opened** to begin with and **closed** when you're done

```java
import java.util.Scanner;

public class ScannerExample {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in); // open a Scanner reading console input

        // These methods PAUSE for user input and return once the user enters it.
        // If the input doesn't match the expected type -> run-time exception/error!
        System.out.print("Enter a word: ");
        String word = scan.next();      // next 'word' token (until whitespace/enter)

        System.out.print("Enter your age: ");
        int i = scan.nextInt();

        scan.close(); // always close when done!

        System.out.println("You entered: " + word + ", age " + i);
    }
}
```

---

## 8. Type Conversions

### Primitive → String (easy)

```java
public class PrimitiveToStringExample {
    public static void main(String[] args) {
        int myInt = 42;
        double myDouble = 3.14;
        boolean myBoolean = true;

        String s1 = Integer.toString(myInt);
        String s2 = Double.toString(myDouble);
        String s3 = Boolean.toString(myBoolean);

        System.out.println(s1 + " " + s2 + " " + s3);
    }
}
```

### String → Primitive (parsing — more error prone!)

- Used when trivial casting isn't possible (e.g., numeric data stored in a String that you want as an int)

```java
public class StringToPrimitiveExample {
    public static void main(String[] args) {
        String myString = "123";

        int i = Integer.parseInt(myString);
        // throws a NumberFormatException if myString is not actually an integer!

        double d = Double.parseDouble("3.14");
        boolean b = Boolean.parseBoolean("true");

        System.out.println(i + " " + d + " " + b);
    }
}
```

---

## 9. Boolean Operators & Truth Table

```java
&&  // AND
||  // OR
!   // NOT
```

| b1    | b2    | b1 && b2 | b1 \|\| b2 | !b1   |
|-------|-------|----------|-----------|-------|
| true  | true  | true     | true      | false |
| true  | false | false    | true      | false |
| false | true  | false    | true      | true  |
| false | false | false    | false     | true  |

```java
public class BooleanOperatorsExample {
    public static void main(String[] args) {
        boolean b1 = true;
        boolean b2 = false;

        System.out.println(b1 && b2); // false
        System.out.println(b1 || b2); // true
        System.out.println(!b1);      // false
    }
}
```

---

## 10. Relational Operators (NOT for Strings!)

```java
==   !=   <   <=   >   >=   // return boolean values
```

> **Never use `==` on a String (or any object)!**
> `==` compares **memory address references**, not the actual text.
> It may LOOK like it works sometimes, but it does not!

```java
public class RelationalOperatorsExample {
    public static void main(String[] args) {
        String myString = new String("hello");
        String other = "hello";

        System.out.println(myString == other);              // false! different addresses
        System.out.println(myString.equals(other));          // true -- same contents
        System.out.println(myString.equalsIgnoreCase("HELLO")); // true -- ignores case
        System.out.println(myString.compareTo(other));        // 0 -- alphabetically equal
    }
}
```

---

## 11. Conditionals (If/Else)

```java
public class ConditionalsExample {
    public static void main(String[] args) {
        int score = 75;

        if (score >= 90) {
            System.out.println("A");
        } else if (score >= 70) {
            System.out.println("B");
        } else {
            System.out.println("C or lower");
        }
    }
}
```

---

## 12. Loops

### For Loop — runs a set number of iterations

```java
public class ForLoopExample {
    public static void main(String[] args) {
        // loops 10 times, i increases by one each iteration
        for (int i = 0; i < 10; i++) {
            System.out.print(i + " ");  // prints: 0 1 2 3 4 5 6 7 8 9
        }
    }
}
```

### While Loop — runs while a condition is true

```java
import java.util.Scanner;

public class WhileLoopExample {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String quit = "keep going";

        // runs until the user types "quit"
        while (!quit.equalsIgnoreCase("quit")) {
            System.out.println("Type 'quit' to exit: ");
            quit = scan.next();
            System.out.println("You entered: " + quit);
        }
        scan.close();
    }
}
```

---

## 13. Escape Characters

```java
\"  // to put quotes inside a String
\n  // newline character
\\  // a backslash literal
\t  // tab character
```

```java
public class EscapeCharactersExample {
    public static void main(String[] args) {
        System.out.print("Hello\nThis is a new line."
            + "\nAs is this and \tthis will be tabbed. \"This is in quotes\"");
    }
}
```

---

## 14. String Methods

```java
public class StringMethodsExample {
    public static void main(String[] args) {
        String myString = "hello";

        System.out.println(myString.equals("Hello"));            // false (case-sensitive)
        System.out.println(myString.equalsIgnoreCase("Hello"));  // true
        System.out.println(myString.length());                   // 5
        System.out.println("".length());                         // 0 (empty String)
        System.out.println(myString.charAt(2));                  // 'l' -- h e l l o, index 2

        String myOtherString;    // not initialized = null (NOT an empty string!)
        myOtherString = "";      // NOW it's an empty String
        System.out.println(myOtherString.length()); // 0
    }
}
```

---

## 15. Math Methods

```java
public class MathMethodsExample {
    public static void main(String[] args) {
        System.out.println(Math.pow(2, 3));   // x to the power y -> 8.0 (returns double)
        System.out.println(Math.sqrt(16));    // square root of x -> 4.0 (returns double)
        System.out.println(Math.min(3, 7));   // minimum value -> 3
        System.out.println(Math.max(3, 7));   // maximum value -> 7

        // Math.random() returns a random double where 0 <= x < 1
        double randomNum = Math.random() * 100; // random number between 0 and 99
        System.out.println(randomNum);
    }
}
```

---

## 16. Arrays

### Key idea: An array is an OBJECT!

- Stored in heap memory; the variable stores only the **reference (address)**
- Contains a **contiguous** block of data of the same type → allows 'random' access at any location
- **Size is fixed on instantiation** — cannot change it (you can create a different array and reassign)
- Following the address into the heap, there's a header like "int array here, 10 elements", followed immediately by the elements

### Creating & Using

```java
public class ArrayCreationExample {
    public static void main(String[] args) {
        int[] arrayOfInts = new int[5];        // [0,0,0,0,0] -- filled with default values
        // arrayOfInts stores a reference (address) on the STACK
        // following that address into the HEAP, you find the actual array

        int[] data = new int[] {1, 2, 3, 4};   // array literal
        data = new int[] {4, 5, 6};            // creates and assigns a new array
        int[] data2 = {9, 10, 11};             // can omit "new int[]" on the declaration line

        data[0] = 15;                 // change value at index 0 to 15
        data[1] = data[0] + data[1];  // add indexes 0 and 1, save to index 1
        System.out.println(data.length);  // array length -- NO parentheses! It's not a method
    }
}
```

### Printing arrays — careful!

```java
import java.util.Arrays;

public class PrintingArraysExample {
    public static void main(String[] args) {
        int[] data = {1, 2, 3};

        System.out.println(data);  // prints the MEMORY ADDRESS, not the array!

        // To print properly, use a loop:
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();

        // OR use the Arrays class:
        System.out.println(Arrays.toString(data)); // returns a String representation
    }
}
```

### Default Values

| Array type | Default value |
|------------|---------------|
| `int` | `0` |
| `double` | `0.0` |
| `boolean` | `false` |
| `char` | `'\u0000'` (nil character) |
| `String`/objects | `null` (no objects created) |

### For-each Loop (quick iteration)

```java
public class ForEachExample {
    public static void main(String[] args) {
        int[] myArray = {10, 20, 30};

        for (int number : myArray) {
            // iterates through every value in the array
            System.out.println(number);
        }
    }
}
```

---

## 17. Array Copying: Shallow vs Deep Copy

### Shallow Copy — copies only the reference

```java
public class ShallowCopyExample {
    public static void main(String[] args) {
        int[] intArr = {1, 2, 3};

        int[] copyArr = intArr;  // this IS a shallow copy!
        // copyArr and intArr point to the SAME array in memory
        copyArr[0] = 99;
        System.out.println(intArr[0]); // 99 -- changing one affects both!
    }
}
```

### Deep Copy — a completely independent new array

```java
public class DeepCopyExample {
    public static void main(String[] args) {
        int[] myArray = new int[] {1, 2, 3};

        // Method 1: manual loop
        int[] myCopy = new int[myArray.length];  // new array of the same size
        for (int i = 0; i < myArray.length; i++) {
            myCopy[i] = myArray[i];              // copy each element
        }

        // Method 2: System.arraycopy
        int[] myCopy2 = new int[myArray.length];
        System.arraycopy(myArray, 0, myCopy2, 0, myArray.length);
        // (source, source start, destination, destination start, number of elements)

        myCopy[0] = 100;
        System.out.println(myArray[0]); // still 1 -- independent array!
    }
}
```

### The arraycopy trap with OBJECT arrays!

- `System.arraycopy` is a deep copy for **primitive** arrays
- BUT for an array of **objects** (say, `Student[]`):
  - a new array object IS created,
  - but it's filled with **copied references** to the SAME Student objects
  - → no new objects were created, so this is a **shallow copy**!
  - → changing a `Student` in one array is reflected in **both**

```java
public class ArraycopyObjectTrapExample {
    public static void main(String[] args) {
        Student[] original = { new Student("Kim"), new Student("Lee") };
        Student[] copy = new Student[original.length];
        System.arraycopy(original, 0, copy, 0, original.length);

        // copy != original          -- the arrays themselves are different
        // copy[0] == original[0]    -- but the objects inside are the SAME!

        copy[0].setName("Changed"); // modifying copy[0] also changes original[0]
        System.out.println(original[0].getName()); // "Changed"
    }
}
```

---

## 18. OOP Basics — Key Definitions

| Term | Meaning |
|------|---------|
| **OOP** | Object Oriented Programming. Most Java programs are built entirely around creating and using objects |
| **user-defined class** | A class that YOU (the user of Java) created — not a built-in class like String |
| **instantiate** | To allocate new memory for an object, using a constructor. i.e., to create an object |
| **instance variables** | Variables belonging to each instance of an object. Declared at the class level in scope |
| **static variables** | Variables belonging to the entire class. Accessible with or without an instance. Only ONE set exists in memory |
| **this** | A keyword usable inside instance methods — a reference to "this object". You cannot have `this` without an instance |

### Class = new data type = blueprint

- Defining a Java class in a file = defining a **new data type** you can create objects from
- We use the class like a **factory or blueprint** to create instance objects
- Class names capitalize every word, including the first: `MyOwnDataType`
  - Types starting uppercase = object references / lowercase = primitives (the hint again!)
- **Only one public class per file**, AND the filename must match the class name
- Your new data type has:
  - some collection of data → instance variables (or static class variables)
  - some behaviours/actions → instance methods (or static class methods)

---

## 19. Declaration vs Instantiation, and null

```java
public class Main {
    public static void main(String[] args) {
        Person morrigan;  // declaration of a Person reference only
        Person lamia;     // no object instantiated -- reference is null

        // null = special Java keyword meaning "no memory address assigned to this reference"
        // If we try to interact with a null reference like an object
        // (e.g., calling a method on it), Java will throw an error!

        morrigan = new Person("Morrigan", 25);
        lamia = new Person("Lamia", 30);
        // new -> calls the Person constructor (special method in the Person class)
        // the constructor creates the object in memory and RETURNS its reference address
        // we called the constructor twice -> two Person objects created in HEAP memory
    }
}
```

### Memory diagram

```
STACK memory                    HEAP memory
morrigan [addr] ─────────────> [ name: "Morrigan" | age: 25 ]
lamia    [addr] ─────────────> [ name: "Lamia"    | age: 30 ]
```

---

## 20. Instance Methods

- Can **only** be used with an instance (an instantiated object) — no object, no method call
- Can access the instance variables of the object they belong to
- Method signature has **NO `static` keyword** ("static" and "instance" are opposites!)

```java
public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // instance method -- only usable on an actual Person object
    public void haveBirthday() {
        age++;
    }

    // instance method -- can access instance variables directly
    public int getNumLettersInName() {
        return name.length();
    }
}
```

```java
public class InstanceMethodExample {
    public static void main(String[] args) {
        Person morrigan = new Person("Morrigan", 25);

        // call instance methods using the "." operator on the object reference
        morrigan.haveBirthday();
        System.out.println(morrigan.getNumLettersInName());
    }
}
```

> Use `private` instead of `public` for internal helper methods not meant to be used from outside.

---

## 21. The toString() Method

- The **standard way** to print an object's values as a String
- Every object has one by default, but the default is useless:
  - it's actually `getClass().getName() + '@' + Integer.toHexString(hashCode())`
  - i.e., just `ClassName@memoryAddress`
- Classes should **override** it to return a meaningful representation

```java
public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override  // tells Java we are overriding this method from the default Object class
    // Java doesn't actually require @Override for toString(), but it's good to know
    // this REPLACES the default method (similar to how writing a constructor
    // replaces the default constructor)
    public String toString() {
        return this.name + " is " + this.age + " years old.";
    }
}
```

```java
public class ToStringExample {
    public static void main(String[] args) {
        Person morrigan = new Person("Morrigan", 25);

        System.out.println(morrigan);
        // println automatically calls .toString() on the object reference!
        // Output: Morrigan is 25 years old.
    }
}
```

---

## 22. Access Modifiers

Apply to instance and static variables and methods. They affect **visibility/accessibility**.

| Keyword | Meaning |
|---------|---------|
| `public` | accessible from anywhere, outside the class |
| `private` | accessible **only within this class's code** |
| `protected` | not covered in this course |
| (none) | package-private (the default) — also not covered |

> When coding OOP, we typically **always specify an access modifier** (public or private) on our instance/static variables and methods.

---

## 23. Encapsulation

- Use `private` to **hide** the instance variables (data) of a class and restrict unwanted changes
- **Only the class itself should modify its own variables** (safer and cleaner)

```java
public class Person {
    private String name;  // no code outside this class can change name
    private int age;      // without this class controlling HOW it's done
}
```

---

## 24. Getters and Setters

- Simple methods that allow controlled access to instance variables you want code to view or update

```java
public class Person {
    private String name;
    private int age;

    public String getName() {   // getter: lets others READ the value
        return name;
    }

    public void setName(String name) {   // setter: lets others UPDATE the value
        // perhaps perform a check that this new name is valid...
        // if so...
        this.name = name;
        // this.name = the instance variable / name = the parameter
    }
}
```

### Full example: an encapsulated Person class

```java
public class Person {
    // data hidden with private (encapsulation)
    private String name;
    private int age;

    // constructor
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // getters
    public String getName() { return name; }
    public int getAge() { return age; }

    // setter (only write the ones you need!)
    public void setName(String name) {
        this.name = name;
    }

    // behaviour method
    public void haveBirthday() {
        age++;
    }

    @Override
    public String toString() {
        return this.name + " is " + this.age + " years old.";
    }
}
```

```java
public class Main {
    public static void main(String[] args) {
        Person morrigan = new Person("Morrigan", 25);
        morrigan.haveBirthday();
        System.out.println(morrigan);  // Morrigan is 26 years old.

        // morrigan.age = 100;  // ERROR! age is private
        System.out.println(morrigan.getAge());  // access via getter instead
    }
}
```

---

## 25. References and Copying Objects

### Simple assignment = copying the reference = Aliasing

```java
public class AliasingExample {
    public static void main(String[] args) {
        Person one = new Person("Fred", 29);
        Person two = one;   // only the REFERENCE is copied! Both point to the same object

        two.haveBirthday();
        System.out.println(one.getAge()); // 30 -- changing one affects both!
    }
}
```

### clone() = deep copy (you have to write it yourself)

```java
public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // custom clone method -- returns a brand-new, identical object
    public Person clone() {
        return new Person(name, age);
    }

    // getters/setters omitted for brevity
}
```

```java
public class CloneExample {
    public static void main(String[] args) {
        Person one = new Person("Anik", 29);
        Person two = one.clone();
        // one != two -- two fully independent objects
        // a change to one will NOT affect the other

        // The name (String) is still shared after cloning -- and that's fine!
        // Strings are IMMUTABLE, they can't be changed, so sharing is safe
    }
}
```

### Passing objects to methods

> Java **always passes a copy of the value** to a method, not the variable itself.
> - primitives → a copy of the **value**
> - objects → a copy of the **reference (address)**

```java
// Case 1: primitive -> original unchanged
public class PassPrimitiveExample {
    public static void main(String[] args) {
        int x = 5;
        changeValue(x);
        System.out.println(x);  // 5 (unchanged)
    }

    public static void changeValue(int x) {
        x += 10;  // x here is just a copy of the value
    }
}
```

```java
// Case 2: reassigning the reference -> original unchanged
public class ReassignReferenceExample {
    public static void main(String[] args) {
        Person p = new Person("George", 65);
        changeValue(p);
        System.out.println(p);  // still George
    }

    public static void changeValue(Person p) {
        p = new Person("Janet", 48);
        // p is a COPY of the reference -- changing where it points
        // does NOT affect the original reference in main
    }
}
```

```java
// Case 3: modifying the object's contents -> original CHANGES!
public class ModifyContentsExample {
    public static void main(String[] args) {
        Person p = new Person("Sayam", 65);
        changeValue(p);
        System.out.println(p);  // Sayam is 66! Changed!
    }

    public static void changeValue(Person p) {
        p.haveBirthday();
        // NOT reassigning p -- altering the object stored at the reference
        // this acts like an ALIAS, changing the original object in main()
    }
}
```

---

## 26. Garbage Collection

- We keep creating objects with `new` — why don't we ever run out of memory?
- → **Java collects 'garbage' for us throughout the life of our program**

### Orphan objects

> When there are **no places where the reference to an object is stored**, it is no longer usable = an **'orphan'**

```java
public class GarbageCollectionExample {
    public static void main(String[] args) {
        Person x = new Person("Nich", 11);
        x = new Person("Claws", 29);
        // x now points to Claws
        // nothing points to the "Nich" object anymore -> ORPHAN! -> garbage collected
    }
}
```

```
x [addr] ──────> Person { name -> "Claws", age: 29 }   <- alive
                 Person { name -> "Nich",  age: 11 }   <- ORPHAN (collected)
```

- Java handles this automatically — "garbage collection" frees up any unused memory
- Should probably be called *recycling*, since the space gets reused
- **Not all languages do this for you (thanks, Java!)**

---

## 27. Key Takeaways (Pre-Exam Checklist)

1. **Primitives store values; objects store references (addresses)** — lowercase vs uppercase is the hint
2. **Integer division drops the decimal** — fix with a `(double)` cast
3. **Never use `==` on Strings** — it compares addresses. Use `.equals()`!
4. **`String s;` is null** — not the same as the empty string `""`
5. **Arrays are objects** — `data.length` has no parentheses; `println(array)` prints an address
6. **`=` is a shallow copy** — deep copy needs a loop or `System.arraycopy`
7. **`arraycopy` on an OBJECT array is a shallow copy** — only references get copied!
8. **Calling a method on a null reference = error**
9. **Instance variables should be private + getters/setters** = encapsulation
10. **Override `toString()`** so `println(object)` prints something meaningful
11. **Methods receive a copy of the reference** — reassigning ≠ change; modifying contents = change
12. **An object with no references = orphan** → garbage collected