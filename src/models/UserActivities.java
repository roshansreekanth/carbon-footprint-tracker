package models;

import java.io.Serializable;
import java.util.ArrayList;

public class UserActivities implements Serializable
{
    /**
     * The class that stores a list of predefined activity strings chosen by the user
     */
    private static final long serialVersionUID = 1L;
    private ArrayList<String> userSelectedactivities;

    public UserActivities()
    {
        userSelectedactivities = new ArrayList<String>(); 
    }

    
    /** 
     * @param s This is the Activity String that is added to the list of user selected activities
     */
    public void add(String s)
    {
        userSelectedactivities.add(s);
    }

    
    /** 
     * @param s This is the Activity String that is removed from the list of user selected activities
     */
    public void remove(String s)
    {
        userSelectedactivities.remove(s);
    }

    
    /** 
     * @return int This returns the size of the list of user selected activities
     */
    public int size()
    {
        return userSelectedactivities.size();
    }

    
    /** 
     * @param index The index at which the activity object is located in the list
     * @return String The activity String located in the specified index
     */
    public String get(int index)
    {
        return userSelectedactivities.get(index);
    }

    public void clear()
    {
        userSelectedactivities.clear();
    }
}