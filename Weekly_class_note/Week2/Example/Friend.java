public class Friend 
{
    // Here, a friend is technically just a string for a name.
    // The "Friend" class serves as a "wrapper" class (object)
    // that allows us to better signify what this String represents
    // in our code
    private String friendName;

    // If needed, we can add more properties to Friend later on

    public Friend (String name)
    {
        friendName = name;
    }

    public String toString()
    {
        return "Friend name: " + friendName;
    }

    public String getName()
    {
        return friendName;
    }

    public void setName(String name)
    {
        this.friendName = name;
    }
}
