import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/* TODO HOMEWORK: Add Object creation and ArrayList for storage

This program currently connects to a file and reads contents line by line with BufferedReader
It sends each line to a method called CreateBook
Currently, CreateBook demonstrates two different methods to separate 'token' data out of our String and prints the information

1. Modify createBook so it would actually create a Book type object.
-- It may be best to start with a class altogether. Call it LibraryBook and write a LibraryBook.java file to use with this program.
-- Each line of the file (except line 1) contains book information as follows: "ISBN,Title,Author,PublicationYear,Genre,Status"
-- Your LibraryBook class should have the following properties: String title, String author, int yearPublished, String genre, boolean available
-- Write any necessary constructors, getters/setters, and methods that you need

2. Once your code can create LibraryBook objects, store each book in a collection of data called an ArrayList (see posted notes from today)
-- ArrayList<LibraryBook> library = new ArrayList<>(); // to declare and instantiate your library
-- Your main method should have access to the library. Either make the library a static class variable OR
-- a local variable within .main and pass the reference around to any method that needs access.
-- while reading/creating book objects, if you notice the book has a 'Lost' Status, don't add it to your library

3. Once you have read in the file, created your books, filled your library arraylist, write a method to print out your ArrayList and library books
-- You'll want to have added a useful toString() method to your LibraryBook class.

*/

public class BookReader { // TODO either rename the class or rename the file so they match :)

  public static final String BEST_BOOKS = "Best_Books_Ever.csv";
  public static final String LIBRARY_BOOKS = "library_books_dataset_2755.txt";

  public static void main(String[] args) {
    readFile(LIBRARY_BOOKS);
  }

  public static void readFile(String filename) {
    try (BufferedReader br = new BufferedReader(new FileReader(filename))){

      String line;

      while((line = br.readLine()) != null) {
        // TODO we know the first line of our file represents column headers and not a Book object...
        // modify the code so that you don't attempt to create a book object with the header line
        createBook(line);
      }

    } catch (FileNotFoundException fnfe) {

      System.out.println(fnfe.getMessage() + fnfe);

    } catch (IOException ioe) {

      System.out.println(ioe.getMessage() + ioe);

    }
  }

  private static void createBook(String bookString) {
    Scanner sc = new Scanner(bookString); // using a Scanner to parse data out of the bookString
    // System.out.println(sc.delimiter()); // just to see what the default delimiter of scanner is

    sc.useDelimiter(","); // set scanner to split on any sequence of characters that exactly matches "," (i.e., split on commas)
    // now calling .next() with sc will give the next value between commas (IF A NEXT VALUE EXISTS...)
    // i.e., the next `token`

    // What happens if we call .next() and there is no next value? (like when we are at the end of the string or file...)
    // .next() or any other scanner next methods, will `throw` an `exception`!
    // We MUST `check` first with .hasNext() to make sure it is safe to call .next()!
    // Note: make sure to study the difference between our `reader` types for how to know when we reached the end!

    while(sc.hasNext()) {
    // if(sc.hasNext()) { // originally we just printed one token... we want to use a while loop to read them all!
      String token = sc.next();   // get the current token and advance sc to point to next token
      System.out.println(token); // print the token
    }

    sc.close(); // always close your scanners when you are done with them! (and any other IO streams)

    // METHOD 2: use the String instance method .split(...)
    // more information about .split:
    // https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#split-java.lang.String-
    // https://www.w3schools.com/java/ref_string_split.asp
    // https://stackoverflow.com/questions/28035810/when-does-string-split-return-an-empty-array
    String [] bookProperties = bookString.split(","); // splits String into an array of tokens with given delimiter
    System.out.println(bookProperties.length); // our array length is the number of tokens returned by the split method
  }
}
