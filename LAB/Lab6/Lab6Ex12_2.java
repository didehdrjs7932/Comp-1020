public class Lab6Ex12_2 {
  public static void main(String args[]) {
    int[] list;

    list = createList();
    printList(list);
    System.out.println("\nThe maximum value is: " + findMax(list));

    System.out.println("\nEnd of processing.");
  }

  public static int[] createList() {
    int[] list = { 20,10,50,60,70,40,30 };

    return list;
  }

  public static int findMax(int[] values) {
    return findMaxRecursive(values, 0);
  }

  public static int findMaxRecursive(int[] values, int position) {
    int result = 0;

    if (position == values.length - 1) {
      return values[position];

    } else {
     
      result = findMaxRecursive(values, position + 1);
      if( values[position] > result ){
          result = values[position];
      }
    }

    return result;
  }

  public static void printList(int[] array) {
    printArrayRecursively(array, 0);
  }

  public static void printArrayRecursively(int[] array, int pos) {
    if (pos == array.length-1) {
      System.out.println(array[pos]);
    } else {
      System.out.print(array[pos] + " ");
      printArrayRecursively(array, pos+1);
    }
  }

  public static void printListBackwards(int[] array){
     printArrayRecursivelyInv(array, array.length -1);
  }

  public static void printArrayRecursivelyInv(int[] array, int pos) {

    if (pos == 0 ) {
      System.out.println(array[pos]);
    } else {
      System.out.print(array[pos] + " ");
      printArrayRecursivelyInv(array, pos-1);
    }
  }

}
