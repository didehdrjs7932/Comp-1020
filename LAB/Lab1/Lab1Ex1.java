public class Lab1Ex1 {
  public static final int[] WIDTHS = { 37, 2, 5, -22, 102, 8, 178361201, 12 };
  public static final int[] HEIGHTS = { 13, 3, 145, 8, -8, 0, 76 };
  
  public static void main(String[] args) {
    int width, height, pos = 0;
    double areaRect, areaEllipse, diagonal, ratio;
    
    while (pos < HEIGHTS.length) {

      width = WIDTHS[pos];
      height = HEIGHTS[pos];
      pos++;
      System.out.printf("***\nUsing width = %d, height = %d.\n", width, height);
      
      if (width <= 0 || height <= 0) {
        System.out.printf("ERROR: width and height must both be positive.\n");
      } else {
        areaRect = (double) width * height;
        areaEllipse = (double) width * height * Math.PI;
        diagonal = Math.sqrt((double) width * width + (double) height * height);
        ratio = (double) width / height;

        System.out.printf("\nArea of a %d x %d rectangle = %.1f\n", width, height, areaRect);
        System.out.printf("Area of a %d x %d ellipse = %f\n", width, height, areaEllipse);
        System.out.printf("Diagonal of a %d x %d right triangle = %.1f\n", width, height, diagonal);
        System.out.printf("Ratio of %d over %d = %.1f\n", width, height, ratio);
      }
    }
    
    System.out.println("\n*** End of program. ***");
  }
}