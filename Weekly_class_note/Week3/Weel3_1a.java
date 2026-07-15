package Weekly_class_note.Week3;

import java.util.Arrays;


  // 07.14.2026.
public class Weel3_1a {
    public static void main(String [] args) {
      
      // create test data and test out sorting methods (consider edge cases and invalid data)
      // write print statements to check your collections
      // better yet, try sorting both arrays and also ArryLists
      // better yet, read the collection data from a file for testing and write the data to a file once sorted

      // Example test 1
      int[] test1 = { 55, 6, 3, 99, -9, 0, 35, 14 };
      System.out.println(Arrays.toString(test1));
      insertionSort1(test1);
      System.out.println(Arrays.toString(test1));

      // Example test 2
      int[] test2 = { 55, 6, 3, 99, -9, 0, 35, 14 };
      System.out.println(Arrays.toString(test2));
      insertionSort2(test2);
      System.out.println(Arrays.toString(test2));
    }

    // Insertion sort: take the next value and "insert" it into the correct sorted position
    // 1. using two nested for loops (purposely using more comments and varibles for learning in this one)
    public static void insertionSort1(int[] dataset) {
      // What are the steps for insertion sort?
      // 1. Look at the elements at each index in the collection 1-by-1
      // 2. Sort that value into the correct "sorted" side of the collection
      // (i.e., shift left towards start of collection until in correct order)
              // --- i.e., (walk the element backwards into the correct sort order)
              // --- if value is lower than element on sorted side,
                            // *shift* higher value to the right, and check next element
                            // when value is equal to or greater than the sorted check, place value at that index, or at the start if its the min

      // Reminder: do not assume perfect data! Check for null reference or too short an array first....
      if(dataset != null && dataset.length > 1) { // if there is only one element... we can consider the sort finished!

        int n = dataset.length; // showing for educational purposes with Big O notation

        // the "outer loop": aka each "pass of the algorithm".
        // Walks forwards through each element in the unsorted portion to sort into correct order
        for(int sortIndex = 1; sortIndex < n; sortIndex++) {

          int sortValue = dataset[sortIndex]; // the value we are trying to insert into our sorted side
          boolean sorted = false; // whether we found and placed the value yet

          // the "inner loop": aka the sorting/comparision steps of the algorithm.
          // Walks backwards down the sorted half towards the first index, checking for the correct position to insert the new value
          // Uses comparison to evaluate if the sorting value should be placed before or after
          for(int compareIndex = sortIndex-1; compareIndex >= 0 && !sorted; compareIndex--) {

            int compareValue = dataset[compareIndex]; // next value to compare with from the sorted side

            // if our sort value is smaller than the compare value, shift the compare value down and continue checking
            if(sortValue < compareValue) {
              dataset[compareIndex+1] = compareValue; // shift element over
            }
            else { // else >= ... "insert" the sort value into the correct location now!
              dataset[compareIndex+1] = sortValue;
              sorted = true;
            }
          }

          if (!sorted) { // if it was never placed, it should go to at the start
            dataset[0] = sortValue;
          }
        }
      }
    }

    // Insertion sort 2: using a inner while loop and position counter
    // example from course material slides
    public static void insertionSort2(int[] dataset) {

      for(int i = 1; i < dataset.length; i++) {
        // select element to be inserted
        int currentValue = dataset[i];
        int position = i;

        // shift elemetns of sorted segment to the right
        // to create a space to insert the currentValue into the correct location
        while (position > 0 && dataset[position-1] > currentValue) {
          dataset[position] = dataset[position-1];
          position--;
        }

        // insert element into new correct position
        dataset[position] = currentValue;
      }
    }

    public static void selectionSort(int[] myNumbers) {
      
    }

    public static void bubbleSort(int[] myNumbers) {

    }
}
