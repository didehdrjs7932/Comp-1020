// Class Note ( 07.02.2026.)
// we learned about variables, public / private acess modifiers , constructor, methods, scope, statics vs non-static(instance)

public class Week1_2a{

    // instance variables
    int numberOfChapers = -1;
    int numberOfPages = -1;
    boolean awardWinner;
   
    String title;
    String author;
    String[] tableOfContents; // set to null by default


    // default contstructor   
    public Week1_2a(){ // Week1_2a mybook = new Week1_2a();
        
        numberOfPages = 100;
        numberOfChapers = 10;
        title = "default title";
        author = "defualt author";
        tableOfContents = new String[numberOfChapers]; // array filled with 'nulls'
    }


    // second constructor
    // Week1_2a mybook = new Week1_2a(453, 14, "my best book", "me")
    
    public Week1_2a(int pages, int chapters, String title, String author){ 
        // this.title means this object in memory
        this.numberOfPages = pages; 
        this.numberOfChapers = chapters;
        this.title = title;
        this.author = author;
        this.tableOfContents = new String[numberOfChapers]; // array filled with 'nulls'

    }
}

