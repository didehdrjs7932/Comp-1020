public class Week1_2b {
    public static void main(String[] args){

        Week1_2a b1 = new Week1_2a();
        // written as Book( 5, 2, "title", "author")
        Week1_2a b2 = new Week1_2a(5, 2, "My great book", "My great author");

        System.out.println(b1); // prints like "book at memory address"
        System.out.println(b2);

        b1.title = "Some other title"; // bad practice but you can do this for now

    }
}
