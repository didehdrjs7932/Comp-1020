import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

/*
 * Assignment 2
 * Student: Donggeon Yang
 */



// #1 
class Community{

private String name;
private String region;

//Community stores: the community name (e.g., "Winnipeg"), the region (e.g., "Northern Manitoba", "Southern Manitoba")
public Community(String name, String region){
    this.name = name;
    this.region = region;
}

//boolean equalsCommunity(Community other): return true if this community and other are considered duplicates if their names match or false otherwise. Ignore case differences in the comparisons.
public boolean equalsCommunity(Community other){

    if(other == null){
        return false;
    }
    else{
        return this.name.equalsIgnoreCase(other.name);
    }
}

@Override
public String toString(){
       
      return name + " | " + region;
}


}

// #2
class Resource{

private String resourceName;
private String resourceType;
private Community  community;
private String information;
 
//Resource stores: the resource name ,the resource type (e.g., "Health", "Education", "Language", "Food", "Cultural"),the Community a contact string (phone number, email, or website)
public Resource(String resourceName, String resourceType, Community community, String information){
    this.resourceName = resourceName;
    this.resourceType = resourceType;
    this.community = community;
    this.information = information;
}

@Override
    public String toString() {
    
        return resourceName + " | " + resourceType + " | " + community.toString() + " | " + information;
    }


//boolean matchesType(String type): return true if this resource type matches type, ignoring case differences.
public boolean matchesType(String type){

    if( type == null ){
        return false;
    }
    else{
        return this.resourceType.equalsIgnoreCase(type);
    }
}

//boolean matchesCommunity(Community other): return true if this resource belongs to the community other based on the community name, ignoring case differences.
public boolean matchesCommunity(Community other){
    
    if( other == null ){
        return false;
    }
    else{
        return this.community.equalsCommunity(other);
    }
}

//boolean matchesKeyword(String keyword): return true if keyword appears in the resource name or contact field, ignoring case differences.
public boolean matchesKeyword(String keyword){

   if (keyword == null){
       return false;
   }

   String lowerKeyword = keyword.toLowerCase();

   boolean nameMatches = (resourceName != null && resourceName.toLowerCase().contains(lowerKeyword));
   boolean infoMatches = (information != null && information.toLowerCase().contains(lowerKeyword));
    
   return nameMatches || infoMatches;
    
}

//boolean equalsResource(Resource other): return true if this resource and other are considered duplicates if they have the same name and are in the same community, or false otherwise. Ignore case differences in the comparisons.
public boolean equalsResource(Resource other){

    if( other == null ){
        return false;
    }
   
    boolean nameMatch = this.resourceName.equalsIgnoreCase(other.resourceName);
    boolean communityMatch = this.community.equalsCommunity(other.community);
    
    return nameMatch && communityMatch;
}
}

//#3
class ResourceDirectory{
    
//ResourceDirectory stores: an ArrayList<Resource> containing all resources
//Encapsulation requirement: The ArrayList<Resource> must be a private field inside ResourceDirectory.
    private ArrayList<Resource> resources;

    public ResourceDirectory(){
        this.resources = new ArrayList<Resource>();
    }

//boolean add(Resource resource): add the resource to the directory and return true if successful. If the resource is a duplicate of an existing resource (using equalsResource), do not add it and return false.
    public boolean add(Resource resource){
     
     if(resource == null){
         return false;
     }

     for(int i = 0; i < resources.size(); i++){
         if(resources.get(i).equalsResource(resource)){
             return false;
         }
     }
     
     resources.add(resource);
     return true;

    }

//int size(): return the number of resources currently in the directory.
    public int size(){
        return resources.size();
    }

//ArrayList<Resource> getAll(): return a new ArrayList containing all resources in the directory (do not return the internal list itself. This means, make a deep copy of the list.).
    public ArrayList<Resource> getAll(){

        ArrayList<Resource> copyList = new ArrayList<Resource>();

        for(int i = 0; i < resources.size(); i++){
            copyList.add(resources.get(i));
        }

        return copyList;
    }

//ArrayList<Resource> findByCommunity(String communityName): return a new ArrayList containing all resources in the given community (ignore case).
    public ArrayList<Resource> findByCommunity(String communityName){
        
        Community target = new Community(communityName,"");

        ArrayList<Resource> newResources = new ArrayList<Resource>();

        for(int i = 0; i < resources.size(); i++){

            if(resources.get(i).matchesCommunity(target)){
                newResources.add(resources.get(i));
            }
        }

        return newResources;
    }

//ArrayList<Resource> findByType(String type): return a new ArrayList containing all resources of the given type (ignore case).
    public ArrayList<Resource> findByType(String type){

    ArrayList<Resource> newResources = new ArrayList<Resource>();

    for(int i = 0; i < resources.size(); i++){

        if(resources.get(i).matchesType(type)){
            newResources.add(resources.get(i));
        }
    }
    return newResources;
    }

//ArrayList<Resource> findByKeyword(String keyword): return a new ArrayList containing all resources where the keyword appears in the resource name or contact field (ignore case).
   public ArrayList<Resource> findByKeyword(String keyword){

    ArrayList<Resource> newResources = new ArrayList<Resource>();

    for(int i = 0; i < resources.size(); i++){

        if(resources.get(i).matchesKeyword(keyword)){
            newResources.add(resources.get(i));
        }
    }
    return newResources;
   }

   @Override
   public String toString(){

       String result = "Resource Directory (" + resources.size() + " resources):";

       for(int i = 0; i < resources.size(); i++){
           result = result + "\n" + resources.get(i).toString();
       }

       return result;
   }
}

//#4
class A2Main2{

    public static final String INPUT_FILE = "resources.txt";
    public static final String OUTPUT_FILE = "output.txt";

//The main method should remain short and primarily coordinate program startup and shutdown.
    public static void main(String[] args){

        ResourceDirectory directory = loadDirectory(INPUT_FILE);

        if(directory.size() == 0){
            System.out.println("Error: no valid resources were found in " + INPUT_FILE + ". Program will terminate.");
        }
        else{
            runMenu(directory, OUTPUT_FILE);
        }
    }

//Community createCommunity(String name, String region)
    public static Community createCommunity(String name, String region){
    
    Community newCommunity = new Community(name, region);

    return newCommunity;
    }

//Resource createResource(String name, String type, Community community, String contact)
    public static Resource createResource(String name, String type, Community community, String contact){

        Resource newResources = new Resource(name, type, community, contact);

        return newResources;
    }

//ResourceDirectory loadDirectory(String filename): read the file and return a ResourceDirectory containing the valid resources. Skip blank lines. If a line has an invalid format, print an error message and skip it.  If the file does not exist or was completely blank, your code should print an error message and the program should terminate.
   public static ResourceDirectory loadDirectory(String filename){
    
    ResourceDirectory dir = new ResourceDirectory();
    boolean hasContent = false;

    try{
        BufferedReader reader = new BufferedReader(new FileReader(filename));

        String line = reader.readLine();

        while(line != null){

            line = line.trim();

            if(!line.isEmpty()){

                hasContent = true;
                String[] part = line.split("\\|");
             
                if (part.length != 5) { 
                    System.out.println("Error: invalid line format, skipping line: " + line); 
                }
                else{
                    for (int i = 0; i < part.length; i++) {
                        part[i] = part[i].trim();
                    }

                    Community comm = createCommunity(part[2], part[3]);
                    Resource res = createResource(part[0], part[1], comm, part[4]);
                    dir.add(res);
                }
            }

            line = reader.readLine();
        }

        reader.close();

        if(!hasContent){
            System.out.println("Error: the file " + filename + " was completely blank.");
        }
    }
    catch(IOException ex){
        System.out.println("Error: could not open the file " + filename + ".");
    }
     return dir;
   }
   

   //void saveDirectory(ResourceDirectory directory, String filename): write all resources in the directory back to a file using the original format.
   public static void saveDirectory(ResourceDirectory directory, String filename) {

       try{
           PrintWriter writer = new PrintWriter(new FileWriter(filename));
           
           ArrayList<Resource> allResources = directory.getAll();
           
           for (int i = 0; i < allResources.size(); i++) {

               writer.println(allResources.get(i).toString());
           }
           
           writer.close();
       }
       catch (IOException e) {
        System.out.println("Error saving file " + filename + ".");
       }
    }

//public static void runMenu(ResourceDirectory directory, String outputFilename)
//All menu logic (printing the menu, reading user input, calling search/add/save methods, looping until the user chooses to save and quit, and saving to the output file) must be implemented inside runMenu, not directly inside main.
    public static void runMenu(ResourceDirectory directory, String outputFilename){

        Scanner keyboard = new Scanner(System.in);
        boolean done = false;

        while(!done){

            printMenu();
            String choice = keyboard.nextLine().trim();

            if(choice.equals("1")){
                listAllResources(directory);
            }
            else if(choice.equals("2")){
                searchByCommunity(directory, keyboard);
            }
            else if(choice.equals("3")){
                searchByType(directory, keyboard);
            }
            else if(choice.equals("4")){
                searchByKeyword(directory, keyboard);
            }
            else if(choice.equals("5")){
                addNewResource(directory, keyboard);
            }
            else if(choice.equals("6")){
                saveDirectory(directory, outputFilename);
                System.out.println("Directory saved to " + outputFilename + ". Goodbye!");
                done = true;
            }
            else{
                System.out.println("Invalid option. Please enter a number from 1 to 6.");
            }
        }

        keyboard.close();
    }

//prints the menu options
    public static void printMenu(){

        System.out.println();
        System.out.println("1 - List all resources");
        System.out.println("2 - Search resources by community");
        System.out.println("3 - Search resources by type");
        System.out.println("4 - Search resources by keyword");
        System.out.println("5 - Add a new resource");
        System.out.println("6 - Save and quit");
        System.out.print("What would you like to do (1-6)? ");
    }

//Option 1: Print all resources currently stored in the directory. Output must be clear and readable; each resource must fit on one line.
    public static void listAllResources(ResourceDirectory directory){

        ArrayList<Resource> all = directory.getAll();

        if(all.size() == 0){
            System.out.println("The directory is empty.");
        }
        else{
            for(int i = 0; i < all.size(); i++){
                System.out.println(all.get(i).toString());
            }
        }
    }

//prints a list of search results, or a message if the list is empty
    public static void printResults(ArrayList<Resource> results){

        if(results.size() == 0){
            System.out.println("No matching resources found.");
        }
        else{
            for(int i = 0; i < results.size(); i++){
                System.out.println(results.get(i).toString());
            }
        }
    }

//Option 2: Ask for a community name (e.g., Winnipeg). If the input is blank, return to the menu. Print all matching resources, or print a message if none are found.
    public static void searchByCommunity(ResourceDirectory directory, Scanner keyboard){

        System.out.print("Enter a community name: ");
        String communityName = keyboard.nextLine().trim();

        if(communityName.isEmpty()){
            return;
        }

        printResults(directory.findByCommunity(communityName));
    }

//Option 3: Ask for a resource type (e.g., Health, Education, Language, Food, Cultural). If the input is blank, return to the menu. Print all matching resources, or print a message if none are found.
    public static void searchByType(ResourceDirectory directory, Scanner keyboard){

        System.out.print("Enter a resource type: ");
        String type = keyboard.nextLine().trim();

        if(type.isEmpty()){
            return;
        }

        printResults(directory.findByType(type));
    }

//Option 4: Ask for a keyword. If the input is blank, return to the menu. Search must match the keyword (ignore case) in either the resource name or the contact field.
    public static void searchByKeyword(ResourceDirectory directory, Scanner keyboard){

        System.out.print("Enter a keyword: ");
        String keyword = keyboard.nextLine().trim();

        if(keyword.isEmpty()){
            return;
        }

        printResults(directory.findByKeyword(keyword));
    }

//Option 5: Prompt the user for each required field. Blank inputs are invalid. If any field is blank, print an error message and return to the menu.
    public static void addNewResource(ResourceDirectory directory, Scanner keyboard){

        System.out.println("Please enter each field one at a time.");

        System.out.print("Resource name: ");
        String name = keyboard.nextLine().trim();

        System.out.print("Resource type: ");
        String type = keyboard.nextLine().trim();

        System.out.print("Community name: ");
        String communityName = keyboard.nextLine().trim();

        System.out.print("Region: ");
        String region = keyboard.nextLine().trim();

        System.out.print("Contact info: ");
        String contact = keyboard.nextLine().trim();

        if(name.isEmpty() || type.isEmpty() || communityName.isEmpty() || region.isEmpty() || contact.isEmpty()){
            System.out.println("Error: all fields are required. Returning to the menu.");
            return;
        }

        Community community = createCommunity(communityName, region);
        Resource resource = createResource(name, type, community, contact);

        if(directory.add(resource)){
            System.out.println("Resource added successfully.");
        }
        else{
            System.out.println("That resource already exists in the directory. It was not added.");
        }
    }
}