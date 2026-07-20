# COMP 1020 Concept Notes

---

## 1. Java is a Strongly Typed Language

- Every value has a **type**, and you must declare the type when creating a variable.
- Unlike Python, you cannot create a variable without a type.

````java
sampleNumber = 20;      // ❌ Compiler error! No type declared
int sampleNumber = 20;  // ✅ Must declare the datatype first
````

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

````java
// A String is basically an array of chars
"Hello"  →  ['H', 'e', 'l', 'l', 'o']

int x = 5;              // primitive: the value 5 stored directly on the stack
String s = "Hello";     // object: stack holds only the address, "Hello" lives in the heap
````

---

## 3. Printing

````java
System.out.println("Hello");  // prints then adds a newline (\n) automatically
System.out.print("Hello");    // no newline
System.out.printf(...);       // formatted output
````

````java
System.out.print("Hello");
System.out.print("World");
// output: HelloWorld

System.out.println("Hello");
System.out.println("World");
// output:
// Hello
// World
````

### Concatenation — very common in Java

````java
// In Python you might do:
// name = "Emily"
// print(f"My name is {name}")

// In Java, use + to concatenate:
String name = "Emily";
System.out.println("My name is " + name);
````

---

## 4. Integer Division ⚠️ Be Careful!

- Integers do **not** round. They simply **drop the decimal part**.

````java
int numX = 5;
int numY = 10;
int answer = numX / numY;           // 0  (NOT 0.5!)

double answerDouble = numX / numY;  // still 0.0
// int / int performs integer division first

// Fix 1: declare as doubles
double doubleX = 5;
double doubleY = 10;
answerDouble = doubleX / doubleY;   // 0.5 ✅

// Fix 2: casting
answerDouble = (double) numX / numY; // cast numX to double before dividing → 0.5 ✅
````

---

## 5. Scope

- Scope is determined by braces `{ }`.
- A variable declared inside a block cannot be used outside it.

````java
if (isValid) {
    int testInteger = 4;
}
System.out.println(testInteger); // ❌ Out of scope! Java doesn't know testInteger here

// You CAN re-declare it outside, but it makes code unclear — not a good idea
int testInteger = 5;
````

---

## 6. Constants

- Use the `final` keyword + `UPPERCASE_SNAKE_CASE` naming
- Once set, the value **cannot be reassigned**
- Use for values that are specific and not meant to change (e.g., `PST_RATE`)

````java
final int TEST_CONSTANT = 100;
TEST_CONSTANT = 2; // ❌ ILLEGAL! Throws an error
````

> ⚠️ Note: For objects, `final` locks the **address (reference)**, not the contents.
> e.g., a `final ArrayList` can still have items added/removed.
> (Don't do this though — it's considered bad practice and reduces code clarity!)

---

## 7. Scanner (User Input)

- Requires `import java.util.Scanner;` at the top of the file
- Works with console input, and can also parse data from files or Strings
- Must be **opened** to begin with and **closed** when you're done

````java
import java.util.Scanner;

Scanner scan = new Scanner(System.in);  // open a Scanner reading console input

// These methods PAUSE for user input and return once the user enters it.
// If the input doesn't match the expected type → run-time exception/error!
String word = scan.next();      // next 'word' token (until whitespace/enter)
String line = scan.nextLine();  // the full next line (until enter)
int i = scan.nextInt();
double d = scan.nextDouble();
boolean b = scan.nextBoolean();

scan.close();  // always close when done!
````

---

## 8. Type Conversions

### Primitive → String (easy)

````java
String s1 = Integer.toString(myInt);
String s2 = Double.toString(myDouble);
String s3 = Boolean.toString(myBoolean);
````

### String → Primitive (parsing — more error prone!)

- Used when trivial casting isn't possible (e.g., numeric data stored in a String that you want as an int)

````java
int i = Integer.parseInt(myString);
// ⚠️ throws an exception/error if myString is not actually an integer!

double d = Double.parseDouble(myString);
boolean b = Boolean.parseBoolean(myString);
````

---

## 9. Boolean Operators & Truth Table

````java
&&  // AND
||  // OR
!   // NOT
````

| b1    | b2    | b1 && b2 | b1 \|\| b2 | !b1   |
|-------|-------|----------|-----------|-------|
| true  | true  | true     | true      | false |
| true  | false | false    | true      | false |
| false | true  | false    | true      | true  |
| false | false | false    | false     | true  |

---

## 10. Relational Operators (NOT for Strings!)

````java
==   !=   <   <=   >   >=   // return boolean values
````

> ⚠️ **NEVER use `==` on a String (or any object)!**
> `==` compares **memory address references**, not the actual text.
> It may LOOK like it works sometimes, but it does not!

````java
// For String comparisons, always use:
myString.equals(other);            // same contents (case-sensitive)
myString.equalsIgnoreCase(other);  // same contents (ignores case)
myString.compareTo(other);         // for alphabetical ordering
````

---

## 11. Conditionals (If/Else)

````java
if (/* boolean expression */) {
    // do something if this is true
} else if (/* some other boolean expression */) {
    // do something if the first was false but this is true
} else {
    // do something if neither was true
}
````

---

## 12. Loops

### For Loop — runs a set number of iterations

````java
// loops 10 times, i increases by one each iteration
for (int i = 0; i < 10; i++) {
    System.out.print(i + " ");  // prints: 0 1 2 3 4 5 6 7 8 9
}
````

### While Loop — runs while a condition is true

````java
Scanner scan = new Scanner(System.in);
String quit = "keep going";

// runs until the user types "quit"
while (!quit.equalsIgnoreCase("quit")) {
    System.out.println("Type 'quit' to exit: ");
    quit = scan.next();
    System.out.println("You entered: " + quit);
}
scan.close();
````

---

## 13. Escape Characters

````java
\"  // to put quotes inside a String
\n  // newline character
\\  // a backslash literal
\t  // tab character
````

````java
System.out.print("Hello\nThis is a new line."
    + "\nAs is this and \tthis will be tabbed. \"This is in quotes\"");
````

---

## 14. String Methods (some)

````java
String myString = "hello";

myString.equals("Hello");            // false (case-sensitive)
myString.equalsIgnoreCase("Hello");  // true
myString.length();                   // 5
"".length();                         // 0 (empty String)
myString.charAt(2);                  // 'l'  →  h e l l o, index 2

String myOtherString;    // ⚠️ not initialized = null (NOT an empty string!)
myOtherString = "";      // NOW it's an empty String
````

---

## 15. Math Methods (some)

````java
Math.pow(x, y);    // x to the power y (returns double)
Math.sqrt(x);      // square root of x (returns double)
Math.min(x, y);    // minimum value (versions for other types exist)
Math.max(x, y);    // maximum value
Math.random();     // random double where 0 <= x < 1
                   // multiply by 100 for a random number between 0 and 99
````

---

## 16. Arrays

### Key idea: An array is an OBJECT!

- Stored in heap memory; the variable stores only the **reference (address)**
- Contains a **contiguous** block of data of the same type → allows 'random' access at any location
- **Size is fixed on instantiation** — cannot change it (you can create a different array and reassign)
- Following the address into the heap, there's a header like "int array here, 10 elements", followed immediately by the elements

### Creating & Using

````java
int[] arrayOfInts = new int[5];        // [0,0,0,0,0] — filled with default values
// arrayOfInts stores a reference (address) on the STACK
// following that address into the HEAP, you find the actual array

int[] data = new int[] {1, 2, 3, 4};   // array literal
data = new int[] {4, 5, 6};            // creates and assigns a new array
int[] data2 = {9, 10, 11};             // can omit "new int[]" on the declaration line

data[0] = 15;                 // change value at index 0 to 15
data[1] = data[0] + data[1];  // add indexes 0 and 1, save to index 1
data.length;                  // array length — ⚠️ NO parentheses! It's not a method
````

### Printing arrays — careful!

````java
System.out.println(data);  // ⚠️ prints the MEMORY ADDRESS, not the array!

// To print properly, use a loop:
for (int i = 0; i < data.length; i++) {
    System.out.println(data[i] + " ");
}

// OR use the Arrays class:
Arrays.toString(data);  // returns a String representation of the array
````

### Default Values

| Array type | Default value |
|------------|---------------|
| `int` | `0` |
| `double` | `0.0` |
| `boolean` | `false` |
| `char` | `'\u0000'` (nil character) |
| `String`/objects | `null` (no objects created) |

### For-each Loop (quick iteration)

````java
for (int number : myArray) {
    // iterates through every value in the array
    System.out.println(number);
}
````

---

## 17. Array Copying: Shallow vs Deep Copy ⭐ Important!

### Shallow Copy — copies only the reference

````java
int[] intArr = {1, 2, 3};

int[] copyArr = intArr;  // ⚠️ this IS a shallow copy!
// copyArr and intArr point to the SAME array in memory
// changing a value shows up in BOTH variables!
````

### Deep Copy — a completely independent new array

````java
int[] myArray = new int[] {1, 2, 3};

// Method 1: manual loop
int[] myCopy = new int[myArray.length];  // new array of the same size
for (int i = 0; i < myArray.length; i++) {
    myCopy[i] = myArray[i];              // copy each element
}

// Method 2: System.arraycopy
System.arraycopy(myArray, 0, myCopy, 0, myArray.length);
// (source, source start, destination, destination start, number of elements)
````

### ⚠️ The arraycopy trap with OBJECT arrays!

- `System.arraycopy` is a deep copy for **primitive** arrays
- BUT for an array of **objects** (say, `Student[]`):
  - a new array object IS created,
  - but it's filled with **copied references** to the SAME Student objects
  - → no new objects were created, so this is a **shallow copy**!
  - → changing a `Student` in one array is reflected in **both**

````java
Student[] original = { new Student("Kim"), new Student("Lee") };
Student[] copy = new Student[original.length];
System.arraycopy(original, 0, copy, 0, original.length);

// copy != original          → the arrays themselves are different
// copy[0] == original[0]    → but the objects inside are the SAME!
// modifying copy[0] also changes original[0]
````

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

````java
public class Main {
    public static void main(String[] args) {
        Person morrigan;  // declaration of a Person reference only
        Person lamia;     // no object instantiated — reference is null

        // null = special Java keyword meaning "no memory address assigned to this reference"
        // If we try to interact with a null reference like an object
        // (e.g., calling a method on it), Java will throw an error!

        morrigan = new Person();
        lamia = new Person();
        // new → calls the Person constructor (special method in the Person class)
        // the constructor creates the object in memory and RETURNS its reference address
        // we called the constructor twice → two Person objects created in HEAP memory
    }
}
````

### Memory diagram

````
STACK memory                    HEAP memory
morrigan [addr] ─────────────> [ name: null | age: 0 ]
lamia    [addr] ─────────────> [ name: null | age: 0 ]
````

---

## 20. Instance Methods

- Can **only** be used with an instance (an instantiated object) — no object, no method call
- Can access the instance variables of the object they belong to
- Method signature has **NO `static` keyword** ("static" and "instance" are opposites!)

````java
public void haveBirthday() {
    age++;
}

public int getNumLettersInName() {
    return name.length();
}
````

````java
// in main: call instance methods using the "." operator on the object reference
morrigan.haveBirthday();
````

- Use `private` instead of `public` for internal helper methods not meant to be used from outside

---

## 21. The toString() Method

- The **standard way** to print an object's values as a String
- Every object has one by default, but the default is useless:
  - it's actually `getClass().getName() + '@' + Integer.toHexString(hashCode())`
  - i.e., just `ClassName@memoryAddress`
- Classes should **override** it to return a meaningful representation

````java
@Override  // tells Java we are overriding this method from the default Object class
// Java doesn't actually require @Override for toString(), but it's good to know
// this REPLACES the default method (similar to how writing a constructor
// replaces the default constructor)
public String toString() {
    return this.name + " is " + this.age + " years old.";
}
````

````java
// somewhere in Main with a Person object:
System.out.println(morrigan);
// println automatically calls .toString() on the object reference!
// Now we print something meaningful!
````

---

## 22. Access Modifiers

Apply to instance and static variables and methods. They affect **visibility/accessibility**.

| Keyword | Meaning |
|---------|---------|
| `public` | accessible from anywhere, outside the class |
| `private` | accessible **only within this class's code** |
| `protected` | we don't care about this in this course |
| (none) | package-private (the default) — also don't care |

> When coding OOP, we typically **always specify an access modifier** (public or private) on our instance/static variables and methods.

---

## 23. Encapsulation ⭐

- Use `private` to **hide** the instance variables (data) of a class and restrict unwanted changes
- **Only the class itself should modify its own variables** (safer and cleaner)

````java
private String name;  // no code outside this class can change name
private int age;      // without this class controlling HOW it's done
````

---

## 24. Getters and Setters

- Simple methods that allow controlled access to instance variables you want code to view or update

````java
public String getName() {   // getter: lets others READ the value
    return name;
}

public void setName(String name) {   // setter: lets others UPDATE the value
    // perhaps perform a check that this new name is valid...
    // if so...
    this.name = name;
    // this.name = the instance variable / name = the parameter
}
````

### Full example: an encapsulated Person class

````java
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
````

````java
// Usage:
public class Main {
    public static void main(String[] args) {
        Person morrigan = new Person("Morrigan", 25);
        morrigan.haveBirthday();
        System.out.println(morrigan);  // Morrigan is 26 years old.

        // morrigan.age = 100;  // ❌ Error! age is private
        System.out.println(morrigan.getAge());  // ✅ access via getter
    }
}
````

---

## 25. References and Copying Objects (Week 3 core)

### Simple assignment = copying the reference = Aliasing

````java
Person one, two;
one = new Person("Fred", 29);
two = one;   // only the REFERENCE is copied! Both point to the same object
// changing one affects both
````

### clone() = deep copy (you have to write it yourself)

````java
// inside the Person class:
public Person clone() {
    return new Person(name, age);  // returns a brand-new, identical object
}
````

````java
Person one = new Person("Anik", 29);
Person two = one.clone();
// one != two — two fully independent objects
// a change to one will NOT affect the other

// 💡 The name (String) is still shared after cloning — and that's fine!
// → Strings are IMMUTABLE, they can't be changed, so sharing is safe
````

### Passing objects to methods

> Java **always passes a copy of the value** to a method, not the variable itself.
> - primitives → a copy of the **value**
> - objects → a copy of the **reference (address)**

````java
// Case 1: primitive → original unchanged
public static void main(String[] args) {
    int x = 5;
    changeValue(x);
    System.out.println(x);  // 5 (unchanged)
}
public static void changeValue(int x) {
    x += 10;  // x here is just a copy of the value
}
````

````java
// Case 2: reassigning the reference → original unchanged
public static void main(String[] args) {
    Person p = new Person("George", 65);
    changeValue(p);
    System.out.println(p);  // still George
}
public static void changeValue(Person p) {
    p = new Person("Janet", 48);
    // p is a COPY of the reference — changing where it points
    // does NOT affect the original reference in main
}
````

````java
// Case 3: modifying the object's contents → original CHANGES! ⚠️
public static void main(String[] args) {
    Person p = new Person("Sayam", 65);
    changeValue(p);
    System.out.println(p);  // Sayam is 66! Changed!
}
public static void changeValue(Person p) {
    p.haveBirthday();
    // NOT reassigning p — altering the object stored at the reference
    // this acts like an ALIAS, changing the original object in main()
}
````

---

## 26. Garbage Collection

- We keep creating objects with `new` — why don't we ever run out of memory?
- → **Java collects 'garbage' for us throughout the life of our program**

### Orphan objects

> When there are **no places where the reference to an object is stored**, it is no longer usable = an **'orphan'**

````java
Person x = new Person("Nich", 11);
x = new Person("Claws", 29);
// x now points to Claws
// nothing points to the "Nich" object anymore → ORPHAN! → garbage collected
````

````
x [addr] ──────> Person { name → "Claws", age: 29 }   ← alive
                 Person { name → "Nich",  age: 11 }   ← ORPHAN 🗑️ collected
````

- Java handles this automatically — "garbage collection" frees up any unused memory
- Should probably be called *recycling*, since the space gets reused
- **Not all languages do this for you (thanks, Java!)**

---

## ⚡ Key Takeaways (Pre-Exam Checklist)

1. **Primitives store values; objects store references (addresses)** — lowercase vs uppercase is the hint
2. **Integer division drops the decimal** — fix with a `(double)` cast
3. **Never use `==` on Strings** — it compares addresses. Use `.equals()`!
4. **`String s;` is null** — not the same as the empty string `""`
5. **Arrays are objects** — `data.length` has no parentheses; `println(array)` prints an address
6. **`=` is a shallow copy** — deep copy needs a loop or `System.arraycopy`
7. **arraycopy on an OBJECT array is a shallow copy** — only references get copied!
8. **Calling a method on a null reference = error**
9. **Instance variables should be private + getters/setters** = encapsulation
10. **Override toString()** so `println(object)` prints something meaningful
11. **Methods receive a copy of the reference** — reassigning ≠ change; modifying contents = change
12. **An object with no references = orphan** → garbage collected

---

## 📌 Course Announcements (from TA notes)

- Labs: late or partial participation doesn't count. **Be present + complete at least one CodeGrade exercise** for full marks
- Assignments submitted through CodeGrade — **multiple submissions allowed** (each creates a snapshot you can go back to)
- Programming standards (changed from Winter 26): **methods should have only ONE point of return** (unless recursion — a topic for later)
- UMLearn notification app: "Pulse"
- No textbook, but an interactive workbook is available for practice
````
````