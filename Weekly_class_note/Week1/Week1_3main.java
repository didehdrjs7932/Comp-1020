// 07.03.2026.

public class Week1_3main {
  public static void main(String[] args) {

    // EXAMPLE *declaring* AND *instantiating* a Book *reference* and a Book *object*. Using two different constructors.

    // labels b1, b2, b3 are all references to an object of type Book. They actually store a memory address or 'null' if no address is set
    // labels b1, b2, b3 and the memory address (reference or pointer) would exist in the "STACK"
    // any actual Book objects created, using the new keyword, will exist in "HEAP" memory
    Week1_3a b1 = new Week1_3a(); // instantiating a new Book object with the default constructor
    Week1_3a b2 = new Week1_3a(5, 2, "My great book", "Me"); // written as Book(5, 2, "title", "author");

    // EXAMPLE shallow and deep copies of ojects

    Week1_3a b3 = b2; // *shallow copy*, these references point to the same Book in memory

    // we could write a *deep copy* like this... if these instance variables were all public.. b4 would point to a brand new object in memory
    // NOTE: Strings are optimized in Java, technically b1.title would be the reference to the same String for the new title,
    //       b/c Java will keep the minimum required Strings in memory... two strings with the same text, might actually refer to the same memory
    //       Strings are also immutable, so we don't care so much and are OK with this being called a deep copy for this example

    //Week1_3a b4 = new Week1_3a(b1.numberOfPages, b1.numberOfChapters, b1.title, b1.author);

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
    System.out.println(b3);
  }
}