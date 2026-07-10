// 07.06.2026.

public class Week2_1main{
  public static void main(String[] args) {

    // EXAMPLE *declaring* AND *instantiating* a Book *reference* and a Book *object*. Using two different constructors.

    // labels b1, b2, b3 are all references to an object of type Book. They actually store a memory address or 'null' if no address is set
    // labels b1, b2, b3 and the memory address (reference or pointer) would exist in the "STACK"
    // any actual Book objects created, using the new keyword, will exist in "HEAP" memory
    Week2_1 b1 = new Week2_1(); // instantiating a new Book object with the default constructor
    Week2_1 b2 = new Week2_1(5, 2, "My great book", "Me"); // written as Book(5, 2, "title", "author");

    // EXAMPLE shallow and deep copies of ojects

   Week2_1 b3 = b2; // *shallow copy*, these references point to the same Book in memory

    // we could write a *deep copy* like this... if these instance variables were all public.. b4 would point to a brand new object in memory
    // NOTE: Strings are optimized in Java, technically b1.title would be the reference to the same String for the new title,
    //       b/c Java will keep the minimum required Strings in memory... two strings with the same text, might actually refer to the same memory
    //       Strings are also immutable, so we don't care so much and are OK with this being called a deep copy for this example

    //Week2_1 b4 = new Week2_1(b1.numberOfPages, b1.numberOfChapters, b1.title, b1.author);

    // EXAMPLE printing objects before and after overriding the default .toString() method
    // EXAMPLE showing changes reflected in both shallow copy references
    // EXAMPLE public and private access modifiers. What happens when we make `title` private in the Book class?

    System.out.println(b1); // prints like "Book @ memory address" by default for objects in Java
    System.out.println(b2); // this calls the method .toString() on the object automatically

    b3.title = "my new book title"; // bad practice but you can do this for now
    System.out.println(b2.title);   // prints "my new book title"

    b1.title = "Some other title";  // if we make the `title` instance variable private, we can no longer access it with `b1.`
    System.out.println(b1);
    System.out.println(b2);

    // EQUALITY TESTING
    // b1 and b2 refer to unique objects
    // b2 and b3 refer to the same objects

    // let's look at the default equality and clone methods before writing our own:
    System.out.println("Are b1 and b2 .equal() ?: " + b1.equals(b2));
    System.out.println("Are b1 and b2 == ?: " + (b1 == b2));

    System.out.println("Are b2 and b3 .equal() ?: " + b2.equals(b3));
    System.out.println("Are b2 and b3 == ?: " + (b2 == b3));

    // CLONING
    Week2_1 b4 = new Week2_1();
    b4 = b1.clone();

    // Need to use a `try-catch` block if our .clone() method `throws an exception`
    // with using the Object superclass default behaviour, like so:

    // try {
    //     b4 = b1.clone();
    // } catch (CloneNotSupportedException e) {
    //     e.printStackTrace();
    // }

    System.out.println("Book b4: " + b4);
    System.out.println("Are b4 and b1 .equal() ?: " + b4.equals(b1));
    System.out.println("Are b4 and b1 == ?: " + (b4 == b1));

    // copyConstructor
    // TODO what is this?
  }
}