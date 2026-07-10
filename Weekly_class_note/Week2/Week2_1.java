// 07.06.2026.

public class Week2_1 implements Cloneable { // `implements Cloneable` needed if we want to write a .clone()

  // INSTANCE VARIABLES
  // best practice is to have instance variables *private*
  // - we can only have instance variables associated with an object
  // - no object, means no instance variables
  // (these are primitive values)
  private int numberOfPages;    // set to 0 by default
  private int numberOfChapters; // set to 0 by default
  private boolean awardWinner;  // set to false by default
  // (these are object references)
  public String title;              // null by default
  private String author;            // null by default
  private String[] tableOfContents; // null by default

  // STATIC VARIABLES

  // CONSTANTS


  // default constructor with no parameters
  // NOTE: as soon as we add ANY constructor to our class code,
  // we lose ability to use any *original* default constructor
 public Week2_1(){ // Week2_1 mybook = new Week2_1();
    numberOfPages = 100;
    numberOfChapters = 10;
    title = "default title";
    author = "default author";
    tableOfContents = new String[numberOfChapters]; // array object of size 10, filled with 'nulls' or not initialized

    // @TODO `constructor chaining` and `copy constructor` example
  }

  // second constructor with 2 parameters
  // TODO write example of `constructor chaining`

  // third constructor with 4 parameters
  // Week2_1 mybook = new Week2_1(453, 14, "My best book", "Me");
  public Week2_1(int pages, int chapters, String title, String author) {
    this.numberOfPages = pages;
    this.numberOfChapters = chapters;
    this.title = title;   // this.title means *this* object in memory. It's a reference! just like `myBook`
    this.author = author; // `this` keyword can only be used with an instance of an object, within its class code
    this.tableOfContents = new String[numberOfChapters]; // array filled with 'nulls'
  }

  // COPY CONSTRUCTOR
  public Week2_1(Week2_1 b) {
    // @TODO write a copy constructor that we could use instead of .clone()
  }

  // INSTANCE METHODS
  // a method that can ONLY be run with an object instance
  // instance is essentially the opposite of static (ie., no static keyword in message signature)

  // a `public` `setter` to allow outside code to update the title String appropriately and as intended by the Book class
  public void setTitle(String title) {
    if (title != null && !title.isBlank()) {
      this.title = title;
    }
  }

  // a simple `public` `getter` to allow outside code to view the title of the book
  public String getTitle() {
    return title;
  }
  // Note: because Strings are immutable, we don't care that we are returning a reference to the actual object String
  // perhaps if it was a method called getTableOfContents() we would have to decide if we want to return a new
  // deep copy of the table of contents array or a shallow copy (just the reference) to the actual array...
  // if we were to return tableOfContents, outside code is free to update values at any index, the Book class would have no idea


  // special method called `toString()`
  // every Object class has a special method called 'toString()'
  // it's best practice to `override` the original by writting your own toString method
  // and create a meaningful String representation of the Object for returning instead
  @Override // technically don't *need* to write `@Override` here but explicitly we are stating
            // this will run instead of the higher level built-in Object.toString() method Java gives us
  public String toString() {
    return "\"" + title + "\" by " + author + " with " + numberOfPages + " pages."; // create a meaningful String to represent your Book object
  }

  @Override
  public boolean equals(Object otherBook) {
    // how do WE (us coding the Book class) determine if two Books are essentially the same?...
    // TODO: write code for that here
    // hint: since we are expecting `otherBook` to be an of type Book, we could try "casting"
    // the Object reference to type Book

    return false;
  }

  // Having to add `implements Clonable` to our class and also handle potential exceptions makes this cumbersome
  @Override
  public Week2_1 clone() {
    // default behaviour, creates new object and performs shallow copy of variables
    // this is OK for objects that only contain primitive instance variables...
    // but not for Objects that have object properites

    // to call the default `superclass` `Object` .clone method, we would write:
    // return (Week2_1)super.clone();

    // TODO write a method that actually returns a new identical copy of a the current instance of the Book!
    return new Week2_1();
  }
}

// class/objects: things we will learn....
// variables (done)
// public / private access modifiers (done)
// constructor (more to come)
// methods (done)
// scope (done)
// static vs non-static (instance) (done)

// MORE COMING UP
// static variables and methods (done)
  // (also constants) (done)
// comparing objects and equality (TODO: homework)
// more memory diagrams (done, more examples will come)
// more putting it all together -> a bunch of different objects working together! (coming up)