import java.util.ArrayList;

public class Lab4Ex12 {

  public static void main(String [] args) {
    String[] strings = createStringArray();
    ArrayList<String> words = stringsToWords(strings);

    printWordList(words);
    
    System.out.println("\nTotal number of words: " + words.size());
		
    System.out.println("\nEnd of processing.");
  }

  public static String[] createStringArray() {
    return new String[] {
      "O my Luve's like a red, red rose, That's newly sprung in June.",
      "O my Luve's like the melodie, That's sweetly play'd in tune.",
      "As fair art thou, my bonie lass, So deep in luve",
      "am I; And I will luve thee still, my dear, Till a' the seas gang dry."
    };
  }
	
  public static ArrayList stringsToWords(String[] s) {
    ArrayList<String> result = new ArrayList<String>();
    String w;
    String[] tokens;

    for (int i = 0; i < s.length; i++) {
      // The line below splits on space and punctuation
      tokens = s[i].split("[\\s,;.]+");
      for (int j = 0; j < tokens.length; j++) {
        String word = tokens[j].toLowerCase();
        result.add(word);
      }
    }
		
    return result;
  }

  public static void printWordList(ArrayList<String> words) {
    for (int i = 0; i < words.size(); i++)
      System.out.println(words.get(i));
  }
}