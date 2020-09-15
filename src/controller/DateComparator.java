package controller;

import java.time.LocalDate;
import java.util.Comparator;

import models.Activity;

public class DateComparator implements Comparator<Activity>
{
    
    /** 
     *  This method uses Java's built in compareTo method for LocalDate to compare Activity objects based on the date
     * @param a1 The first Activity object
     * @param a2 The second Activity Object that is compared to the first Activity Object
     * @return int int Returns 0 if Activity dates are the same, else returns 1 or -1 based on the ordering determined by Java's compareTo method for LocalDate
     */
    public int compare(Activity a1, Activity a2)
    {
        LocalDate a1Date = a1.getDate();
        LocalDate a2Date = a2.getDate();
        return a1Date.compareTo(a2Date);
    }
}
