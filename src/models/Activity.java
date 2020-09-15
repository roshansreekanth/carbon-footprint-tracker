package models;
import java.io.Serializable;
import java.time.LocalDate;



public class Activity implements Serializable
{
    /**
     * This class contains the week an activity takes place, the date, the name of the activity, and the number of points it is worth
     */
    private static final long serialVersionUID = 1L;

    private static int idCounter = 0;
    
    private int id;
    private int week;
    private LocalDate date;
    private String activity;
    private int points;

    public Activity(int week, LocalDate date, String activity, int points)
    {
        this.week = week;
        this.date = date;
        this.activity = activity;
        this.points = points;
        this.id = idCounter++;
        System.out.println(); 
    }

    
    /** 
     * @return int This returns the unique Id of the activity
     */
    public int getId()
    {
        return id;
    }

    
    /** 
     * @return int This returns the number of points the Activity is worth
     */
    public int getPoints()
    {
        return points;
    }

    
    /** 
     * @return int This returns the week the activity took place
     */
    public int getWeek()
    {
        return week;
    }

    
    /** 
     * @return String This returns the name of the Activity
     */
    public String getActivity()
    {
        return activity;
    }

    
    /** 
     * @return int This returns the length of the Activity name
     */
    public int getLength()
    {
        return activity.length();
    }
    
    
    /** 
     * @return LocalDate This returns the dadte the activity takes place
     */
    public LocalDate getDate()
    {
        return date;
    }
    
    
    /** 
     * @return String Overrides the toString method for the Activity class
     */
    public String toString()
    {
        return week + "\t" + date + "\t" + activity + "\t" + points;
    }

    
}