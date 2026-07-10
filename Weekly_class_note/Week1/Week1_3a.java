// 07.03.2026.

// this java class is code that can build/create a Book object
// (or not!)
// you can only have ONE PUBLIC class per file AND it must
// match the file name
// you could also have other classes in this file, but they
// cannot be marked as 'public'
// every file/program we have built so far, has been a class!

public class Week1_3a{

  // INSTANCE VARIABLES
  // - we can only have instance variables associated with an object instance
  // - no object, no instance variables
  // (primitive values)
  private int numberOfPages;    // set to 0 by default
  private int numberOfChapters; // set to 0 by default
  private boolean awardWinner;  // set to false by default
  // (object references)
  public String title;              // null by default
  private String author;            // null by default
  private String[] tableOfContents; // null by default

  // best practice is to have instance variables *private* so that no code outside
  // this class can view or edit the variables without the Book class knowing about it.

  // default constructor (overriding.. like the original default constructor b/c it is parameterless)
  // NOTE: as soon as we add any constructor to our class code,
  //       we lose ability to use any original default constructor from the higher level *Object* class
 public Week1_3a () { // Week1_3a  mybook = new Week1_3a ();
    numberOfPages = 100;
    numberOfChapters = 10;
    title = "default title";
    author = "default author";
    tableOfContents = new String[numberOfChapters]; // array object of size 10, filled with 'nulls' or not initialized
  }

  // second constructor
  // Book mybook = new Book(453, 14, "My best book", "Me");
  public Week1_3a(int pages, int chapters, String title, String author) {
    this.numberOfPages = pages;
    this.numberOfChapters = chapters;
    this.title = title;   // this.title means *this* object in memory. It's a reference! just like `myBook`
    this.author = author; // `this` keyword can only be used with an instance of an object, within its class code
    this.tableOfContents = new String[numberOfChapters]; // array filled with 'nulls'
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
}

// class/objects: things we will learn....
// variables
// public / private access modifiers
// constructor
// methods
// scope
// static vs non-static (instance)

// MORE COMING UP
// static variables and methods
  // (also constants)
// comparing objects and equality
// more memory diagrams
// more putting it all together -> a bunch of different objects working together!
