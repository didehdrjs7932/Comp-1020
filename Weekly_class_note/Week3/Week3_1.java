package Weekly_class_note.Week3;

// 07.14.2026.
public class Week3_1 {
     public static void main(String [] args) {
    
      // create test data and test out searching methods (consider edge cases and invalid data)
      // write print statements to check your collections
      // better yet, try searching both arrays and also ArryLists
    }


    // Linear Search: scan collection from start to end, looking for target
    // this one returns the index of the found element, sometimes you may want to return a boolean found or not etc.,
    public static int linearSearch(int target, int[] collection) {
      int foundIndex = -1;

      for(int i=0; i<collection.length && foundIndex == -1; i++) {
        if(collection[i] == target)
          foundIndex = i;
      }

      return foundIndex; // returns -1 if not found
    }


    // TODO write a binary search to find the index of the target value in a given collection of data
    public static int binarySearch(int target, int[] collection) {
      // can only perform binary search on sorted collection of data
      // narrow data into half each time...
      // searching for target until you've found it or you've checked the last possible narrowed down element
      int foundIndex = -1;

      // TODO consider how you would do this using "start, middle, end" indices to check for where the target is



      return foundIndex;
    }
    
}
