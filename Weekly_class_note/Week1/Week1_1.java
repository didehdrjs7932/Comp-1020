// Class note ( 06.30.2026. )
// Review for loop , array copy

public class Week1_1{
    
    public static void main (String[] args){
        // write code to call a method here

    }
    
    public static char[] concat(char[] a, char[] b){
      
      char[] c = new char[a.length + b.length];

      int i = 0;

      for( ; i < a.length; i++){
        c[i] = a[i];
      }
    
     
      // now are array is partially filled
      for(char cha : b ){ // a for each notation
        c[i++] = cha;
      }
      return c;
    }

    public static char[] concat2(char[] a, char[] b){
        char[] c = new char[a.length + b.length];

        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        
        return c;
    }
}