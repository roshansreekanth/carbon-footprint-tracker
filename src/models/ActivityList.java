package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import controller.ActivityNameComparator;
import controller.DateComparator;

public class ActivityList implements Serializable
{
    /**
     * Tthis class stores a list of all Activities in a serializable format
     */
    private static final long serialVersionUID = 1L;
    private ArrayList<Activity> activities;

    
    public ActivityList()
    {
        activities = new ArrayList<Activity>();
    }

    
    /** 
     * @return int This returns the size of the list
     */
    public int getSize()
    {
        return activities.size();
    }

    
    /** 
     * @param activityObject This is the activity to be added to the list of activities
     */
    public void addActivity(Activity activityObject)
    {
        activities.add(activityObject);
    }

    
    /** 
     * @param activityObject This is the activity that is removed from the list of activities
     */
    public void removeActivity(Activity activityObject)
    {
        for (int i = 0; i < activities.size(); i++)
        {
            Activity existingActivity = activities.get(i);
            if(existingActivity.getId() == activityObject.getId())
            {
                activities.remove(i);
            }
        }
    }

    public void clearActivitiesList()
    {
        activities.clear();
    }

    public void sortByDate()
    {
        Collections.sort(activities, new DateComparator());
    }

    public void sortByName()
    {
        Collections.sort(activities, new ActivityNameComparator());
    }

    public void sortByActivityName()
    {
        Collections.sort(activities, new ActivityNameComparator());
    }

    
    /** 
     * @param index The index at which the activity object is located in the list
     * @return Activity The activity object located in the specified index
     */
    public Activity getActivity(int index)
    {
        return activities.get(index);
    }

}