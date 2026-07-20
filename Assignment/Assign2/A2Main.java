import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
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

  String lowerKeyword = keyword.toLowerCase();

   if (keyword == null){
       return false;
   }

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
    
    private Resource[] resources;
    private int count;

//ResourceDirectory stores: an ArrayList<Resource> containing all resources
    public ResourceDirectory(){
        this(100);
    }

    public ResourceDirectory(int capacity){
        this.resources = new Resource[capacity];
        this.count = 0;
    }

//boolean add(Resource resource): add the resource to the directory and return true if successful. If the resource is a duplicate of an existing resource (using equalsResource), do not add it and return false.
    public boolean add(Resource resource){
     
     if(resource == null || count >= resources.length){
         return false;
     }

     for(int i = 0; i < count; i++){
         if(resources[i].equalsResource(resource)){
             return false;
         }
     }
     
     resources[count] = resource;
     count++;
     return true;

    }

//int size(): return the number of resources currently in the directory.
    public int size(){
        return count;
    }

//ArrayList<Resource> getAll(): return a new ArrayList containing all resources in the directory (do not return the internal list itself. This means, make a deep copy of the list.).
    public ArrayList<Resource> getAll(){

        ArrayList<Resource> copyList = new ArrayList<Resource>();

        for(int i = 0; i < count; i++){
            copyList.add(resources[i]);
        }

        return copyList;
    }

//ArrayList<Resource> findByCommunity(String communityName): return a new ArrayList containing all resources in the given community (ignore case).
    public ArrayList<Resource> findByCommunity(String communityName){
        
        Community target = new Community(communityName,"");

        ArrayList<Resource> newResources = new ArrayList<Resource>();

        for(int i = 0; i < count; i++){

            if(resources[i].matchesCommunity(target)){
                newResources.add(resources[i]);
            }
        }

        return newResources;
    }

//ArrayList<Resource> findByType(String type): return a new ArrayList containing all resources of the given type (ignore case).
    public ArrayList<Resource> findByType(String type){

    ArrayList<Resource> newResources = new ArrayList<Resource>();

    for(int i = 0; i < count; i++){

        if(resources[i].matchesType(type)){
            newResources.add(resources[i]);
        }
    }
    return newResources;
    }

//ArrayList<Resource> findByKeyword(String keyword): return a new ArrayList containing all resources where the keyword appears in the resource name or contact field (ignore case).
   public ArrayList<Resource> findByKeyword(String keyword){

    ArrayList<Resource> newResources = new ArrayList<Resource>();

    for(int i = 0; i < count; i++){

        if(resources[i].matchesKeyword(keyword)){
            newResources.add(resources[i]);
        }
    }
    return newResources;
   }
}

//#4
class A2Main{

    private static final int capacity = 70;

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
    
    File file = new File(filename);
    ResourceDirectory dir = new ResourceDirectory(capacity);
    boolean hasContent = false;

    try{
        Scanner scnr = new Scanner(file);

        while(scnr.hasNextLine()){
            String line = scnr.nextLine().trim();

            if(line.isEmpty()){
                continue;
            }

            hasContent = true;
            String[] part = line.split("\\|");
             
             if (part.length != 5) { 
                System.out.println("Error"); 
                  continue;
                }

            for (int i = 0; i < part.length; i++) {
                part[i] = part[i].trim();
            }

             Community comm = createCommunity(part[2], part[3]);
             Resource res = createResource(part[0], part[1], comm, part[4]);
             dir.add(res);
        }
        if(!hasContent){
            System.out.println("Error");
        }
    }
    catch(FileNotFoundException ex){
        System.out.println("Error");
    }
     return dir;
   }
   

   //void saveDirectory(ResourceDirectory directory, String filename): write all resources in the directory back to a file using the original format.
   public static void saveDirectory(ResourceDirectory directory, String filename) {

       try{
           PrintWriter writer = new PrintWriter(filename);
           
           ArrayList<Resource> allResources = directory.getAll();
           
           for (Resource res : allResources) {

               writer.println(res.toString());
           }
           
           writer.close();
       }
       catch (IOException e) {
        System.out.println("Error saving file");
       }
    }
}