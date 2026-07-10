/* This activity was dreamed up and designed by your TA, KC :) */
// 07.09.2026. 

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Week2_3
{
    public static final int FILE_NAME_IDX = 0;
    // For the sake of simplicity, we will assume that there are no duplicate
    // friends. I.e. all names are unique.
    public static void main(String[] args)
    {
        FriendsList friendsList = bfReadFriendsFromFile(args[FILE_NAME_IDX]);

        // TODO: print friends here!
    }


    public static FriendsList scReadFriendsFromFile(String fileName)
    {
        FriendsList friendsList = new FriendsList();

        // Since we are using File I/O, this will throw
        // an exception if such file does not exist.
        // This is considered a caught exception, meaning
        // Java will refuse to compile the program unless it is
        // handled in a try-catch
        // TODO: fill-in the try() with a reader declaration
        try ()
        {
            // TODO: your code here to process reading the .txt file
            // and storing them in a FriendsList
        }
        catch (FileNotFoundException fnf)
        {
            fnf.printStackTrace();
        }

        return friendsList;
    }

    public static FriendsList bfReadFriendsFromFile(String fileName)
    {
        FriendsList friendsList = new FriendsList();

        // Since we are using File I/O, this will throw
        // an exception if such file does not exist.
        // This is considered a caught exception, meaning
        // Java will refuse to compile the program unless it is
        // handled in a try-catch
        // TODO: fill-in the try() with a reader declaration
        try()
        {
            // TODO: your code here to process reading the .txt file
            // and storing them in a FriendsList
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }

        return friendsList;
    }
}
