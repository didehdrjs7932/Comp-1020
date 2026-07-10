import java.util.ArrayList;
import java.util.List;

// Wrapper class for ArrayList
public class FriendsList 
{
    private List<Friend> friendsList;

    public FriendsList()
    {
        friendsList = new ArrayList<>();
    }

    public void printFriends()
    {
        // if friends list is empty
        // it should print indicating so; otherwise,
        // print the list of friends
        if(friendsList.isEmpty()){
            System.out.println( " Friends list is empty ");
        }
        else{
            for(int i = 0; i<friendsList.size(); i++){
                System.out.println(friendsList.get[i]);
            }
        }
    }

    public void printFriendsNoLoop()
    {
        System.out.println("List of friends printed without loop: ");
        // TODO: code to print list of friends without using a loop
        System.out.println(friendsList);

    }

    // Function to remove a friend from the FriendsList
    // provided a String name. If the name does not exist
    // in the list, nothing will happen
    public void removeFriend(String name)
    {
    
        // SETUP index
        // default index is -1, to signify
        // that the friend we are trying to remove
        // doesn't exist
        int indexToRemove = -1;

        // find friend to remove

        for(int i =0; i<friendsList.size(); i++){
           
            if(friendsList[i].get(i).getName().equalIgnoreCase(name)){
                indexToRemove = i;
            }
        }

        // remove friend - don't remove while looping in list!
         if(indexToRemove >= 0){
            friendsList.remove(indexToRemove);
         }
    } 

    // Function to add a Friend object to the FriendsList
    // provided a String name
    public void addFriend(String name)
    {
        
    }

    // Function to retrieve a Friend object from a list
    // Returns NULL if friend does not exist
    public Friend getFriend(String name)
    {
        Friend foundFriend = null;
        
        for(int i = 0; i < friendsList.size(); i++){
            
            if(friendsList.get(i).getName().equalsIgnoreCase(name)){
             foundFriend = friendsList.get(i);
            }
        }
    }

    // Function to indicate if friendslist is empty
    // TRUE if empty, FALSE if non-empty
    public boolean isEmpty()
    {
       
    }
}
