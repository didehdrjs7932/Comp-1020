/* This class has some static methods to demonstrate reading from and writing to files */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class FileIoExamples {

  public static final String FILENAME = "myFile.txt";
  public static final String NEW_FILENAME = "newFile.txt";

  public static void main(String[] args) {
    // file management and information
    fileExample();

    // reading from files
    bufferedReaderExample();
    bufferedReaderExample2();
    scannerExample();
    scannerExample2();
    fileReaderExample();

    // writing to files
    bufferedWriterExample();
    fileWriterExample();
    printWriterExample();
  }

  // File class: connect to, get information about, or create/delete files and directories
  // Not reading/writing yet
  public static void fileExample() {
    File fileObj = new File(FILENAME);
    if(fileObj.exists()) { // we found the file with this name, now we can do stuff with it
      System.out.printf("The filename is %s and the directory path is %s.", FILENAME, fileObj.getAbsolutePath());
    }
  }

  // BufferedReader Example
  // a good choice for large files, makes reading faster with buffering
  public static void bufferedReaderExample() {
    try {
        BufferedReader buff = new BufferedReader(new FileReader(FILENAME));
        String line;
        while((line = buff.readLine()) != null) { // readLine returns null when it reaches the end of the file
          // print out the whole line, could do parsing here
          System.out.println(line);
        }
        buff.close();
    } catch (FileNotFoundException e) { // new FileReader(...) could through this
        System.out.printf("No file found with name: %s\n", FILENAME);
        e.printStackTrace();
    } catch (IOException e) { // .close could throw this... FNFE is also an IOException
        e.printStackTrace();
    } finally {
        System.out.println("Finally... End of processing in the BufferedReader method");
    }
    // remember there could still be code or a return statement after the try/catch/finally block
  }

  // somewhat simplified BufferedReader and try-with-resources
  public static void bufferedReaderExample2() {
    // this notation is called "try with resources"
    // putting our stream reader connection statement within the try will automatically .close()
    // the stream when the try concludes (with both a normal exit and if an exception happens)
    try(BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
      String line = br.readLine();
      while(line != null) {
        System.out.println(line);
        line = br.readLine(); // note: our only useful options with br are .readLine(),
        // to get the whole line, or .read(), which returns one character (as an int value)
      }
    } catch (FileNotFoundException e) { // if this is caught, we know there was an error connecting to the file
        e.printStackTrace();
    } catch (IOException e) { // if this is caught, we know there is an error in reading with br.readLine()
        e.printStackTrace();
    }
  }

  // Scanner Example
  public static void scannerExample() {
    try(Scanner sc = new Scanner(new File(FILENAME))) {
      while(sc.hasNextLine()) { // test to make sure a next line exists, returns false at end of file
        String line = sc.nextLine(); // scanner gives us lots of options for reading out file data
        System.out.println(line);
      }
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
  }

  // Scanner Example with more parsing
  public static void scannerExample2() {
    try(Scanner sc = new Scanner(new File(FILENAME))) {
      while(sc.hasNext()) { // test to make sure any next `token` exists
        if(sc.hasNextInt()) {
          System.out.printf("\nWe found an int! It is: %d\n", sc.nextInt());
        }
        else {
          System.out.println(sc.next().toUpperCase());
        }
      }
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
  }

  // reading char by char, not as useful as the others (low level)
  // BufferedReader is a wrapper class of FileReader to make it more useful
  // Scanner can wrap around File or FileReader for input streams
  public static void fileReaderExample() {
    try(FileReader reader = new FileReader(FILENAME)) {
      int character;
      while((character = reader.read()) != -1) {// returns negative 1 at end of file
        System.out.print((char)character); // cast the int to a char
      }
    } catch (IOException e) {
        e.printStackTrace();
    }
  }

  // BufferedWriter, good for large files of text
  public static void bufferedWriterExample() {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(NEW_FILENAME))) { // use FileWriter(filename, true) to append to an existing file instead of overwriting
      bw.write("Welcome to my new file with using BufferedWriter!\n");
      bw.newLine();
      bw.write('@');
      bw.write("Use .newLine() method to add a new line break."); // there is no writeLine method!
      bw.newLine();
      bw.write("If the file that matches the filename does not exist, bw will create the file and write to it.");
      bw.write("\nIf the file does exist, bw will connect to it and either completely overwrite it, or appened to the end.");
      bw.write("\n this depends on how we set it up.");
      bw.write("\nMake sure to close your BufferedWriter when you are done using it! Otherwise, the text may not *actually* get saved to the file.");
      // if we didn't use try with resources, we would want to make sure we close the bw so it actually writes/saves to the file!
      // bw.close();
      System.out.println("Successfully wrote to the file with BufferedWriter.");
    } catch (IOException e) {
        e.printStackTrace();
    }
  }

  // lower level, typically we would use the BufferedWriter wrapper class instead
  public static void fileWriterExample() {
    try{
      FileWriter writer = new FileWriter(NEW_FILENAME, true); // lets append to the file we created already
      writer.write("\n\n\nNow we are writing to the same file but from the FileWriter example!");
      writer.write("\nFileWriter can write a String or a char to the file. You should call .close when you are done.");
      writer.close();
      System.out.println("Successfully wrote to the file with FileWriter.");
    } catch(IOException e) {
      e.printStackTrace();
    }
  }

  // for printing to a stream or file, looks familiar to System.out.print
  // Note: methods of this class don't throw exceptions, only potentially the constructor!
  public static void printWriterExample() {
    try(PrintWriter writer = new PrintWriter("anotherNewFile.txt")) { // could even do this: new PrintWriter(System.out);
      writer.println("Now we are writing to a file using PrintWriter!");
      writer.print("These methods look really familiar...\n");
      writer.printf("It even has a way to format data into text! %.2f %6d", 0.6789, 5000);
      System.out.println("Successfully wrote to the file with PrintWriter.");
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
  }
}