// 07.07.2026.

public class Week2_2 implements Cloneable { //ObjectEquality
    // TODO demonstrate .equals(), .compareTo(), .clone(), .copyConstructor

    public int objInt;
    public String objStr;
    public int[] primArr;
    public Object[] objArr;

    public Week2_2(int val, String text, int arrSize) {
      objInt = val;
      objStr = text;
      primArr = new int[arrSize];
      objArr = new Object[arrSize];
      fillArray(primArr);
      fillArray(objArr);
    }

    // TODO write a copy constructor
    public Week2_2(Week2_2 oe) {
      this.objInt = oe.objInt;
      this.objStr = oe.objStr;
      this.primArr = new int[oe.primArr.length];
      this.objArr = new Object[oe.objArr.length];
      System.arraycopy(this.primArr, 0, oe.primArr, 0, this.primArr.length);
      System.arraycopy(this.objArr, 0, oe.objArr, 0, this.objArr.length);
    }

    private void fillArray(int[] nums) {
      for(int i=0; i<nums.length; i++){
        nums[i] = i+1;
      }
    }

    private void fillArray(Object[] objs) {
      for(int i=0; i<objs.length; i++) {
        objs[i] = new Object();
      }
    }

    @Override
    public boolean equals(Object oe) {
      boolean equals = false;
      Week2_2 oeq = (Week2_2)oe;

      if(this.objInt == oeq.objInt) {
        if(this.objStr.equals(oeq.objStr)) {
          if(this.primArr.equals(oeq.primArr)) {
            if(this.objArr.equals(oeq.objArr)) { // or put these in one statement...
              equals = true;
            }
          }
        }
      }

      return equals;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
      Week2_2 oe = (Week2_2) super.clone();

      // why do we need these two lines?
      // oe.primArr = new int[this.primArr.length];
      // oe.objArry = new Object[this.objArry.length];

      System.arraycopy(this.primArr, 0, oe.primArr, 0, this.primArr.length);
      System.arraycopy(this.objArr, 0, oe.objArr, 0, this.objArr.length);

      return oe;
    }

    @Override
    public String toString() {
      String result = String.format("ObjectEquality:object@%s[objInt:%d, objStr:%s, primArr:%s, objArr:%s]", this.hashCode(), objInt, objStr, arrayString(primArr), arrayString(objArr));
      return result;
    }

    public String arrayString(int[] nums){
      String result = "array@"+ nums.hashCode() +"[" + nums[0];
      for(int i=1; i<nums.length; i++) {
        result += "," + nums[i];
      }
      return result + "]";
    }

    public String arrayString(Object[] obs){
      String result = "array@"+ obs.hashCode() +"[" + obs[0].hashCode();
      for(int i=1; i<obs.length; i++) {
        result += "," + obs[i].hashCode();
      }
      return result + "]";
    }

    // TODO write a .compareTo method!
    // public int compareTo(Object o) {

    // }

    public static void main(String[] arg) {
      String greeting = "hello";

      Week2_2 oe1 = new Week2_2(43, greeting, 10);
      System.out.println("\nPrinting oe1: " + oe1);

      Week2_2 oe2 = null;
      try {
        oe2 = (Week2_2)oe1.clone();
      }
      catch(CloneNotSupportedException ce) {
      }
      System.out.println("\nPrinting oe2 (clone of oe1): " + oe2);
      System.out.println("Are oe1 and oe2 .equals() ?: " + oe1.equals(oe2));
      System.out.println("Are oe1 and oe2 == ?: " + (oe1 == oe2));

      // shallow copy
      ObjectEquality oe3 = oe1;
      System.out.println("\nPrinting oe3 (shallow copy of oe1): " + oe3);
      System.out.println("Are oe1 and oe3 .equals() ?: " + oe1.equals(oe3));
      System.out.println("Are oe1 and oe3 == ?: " + (oe1 == oe3));

      // same properties
      ObjectEquality oe4 = new Week2_2(43, greeting, 10);
      System.out.println("\nPrinting oe4 (new object with same attributes as oe1): " + oe4);
      System.out.println("Are oe1 and oe4 .equals() ?: " + oe1.equals(oe4));
      System.out.println("Are oe1 and oe4 == ?: " + (oe1 == oe4));
  }