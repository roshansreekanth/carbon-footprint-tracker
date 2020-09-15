package controller;

import java.util.Comparator;
import models.Activity;

public class ActivityNameComparator implements Comparator<Activity>
{
    
    /** 
     * This method uses Java's built in compareTo method for Strings to compare Activity objects based on the name of the activity
     * @param a1 The first Activity object
     * @param a2 The second Activity Object that is compared to the first Activity Object
     * @return int Returns 0 if Activity names are the same, else returns 1 or -1 based on the alphabetical ordering determined by Java's compareTo method for Strings
     */
    public int compare(Activity a1, Activity a2)
    {
        String a1Name = a1.getActivity();
        String a2Name = a2.getActivity();
        return a1Name.compareTo(a2Name);
    }
}
