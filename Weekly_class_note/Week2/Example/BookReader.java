import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BookReader {

  public static final String BEST_BOOKS = "Best_Books_Ever.csv";
  public static final String LIBRARY_BOOKS = "library_books_dataset_2755.txt";

  public static void main(String[] args) {
    readFile(LIBRARY_BOOKS);
  }

  public static void readFile(String filename) {

    try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
      String line = null;
      while((line = br.readLine()) != null) {
        System.out.println(line);
      }
    } catch (FileNotFoundException fnfe) {
      System.out.println(fnfe.getMessage());
    } catch (IOException ioe) {
      System.out.println(ioe.getMessage());
    }

  }
}
