package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import models.Activity;
import models.ActivityList;
import models.UserActivities;
import views.View;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class Controller
{   
    private View viewReference;
    private ActivityList activitiesList;
    private UserActivities userSelectedActivites;

    public Controller(View viewReference)
    {
        activitiesList = new ActivityList();
        userSelectedActivites = new UserActivities();
        this.viewReference = viewReference;
    }

    
    /** 
     * This function saves the activities added by the user along with their points to a file called save_data.
     * @return boolean Reutrns true if file succcessfully saves else returns false
     */
    public boolean saveEventHandler()
    {   
        try{
            FileOutputStream file = new FileOutputStream("save_data");
            ObjectOutputStream out = new ObjectOutputStream(file);
            
            out.writeObject(activitiesList);
            out.close();
            file.close();
            return true;
        }
        
        catch (IOException err) {
            System.out.println(err);
            return false;
          }
    }

    
    /** 
     * This function loads the activities present in the save_data file into <b>activitiesList</b>
     * @param activitiesListView The ListView elemwnts where the user's activities are stored.
     * @return boolean Returns true if the load is sucessful, else returns false.
     */
    public boolean loadEventHandler(ListView<Activity> activitiesListView)
    {
        try {
            FileInputStream file = new FileInputStream("save_data");
            ObjectInputStream in = new ObjectInputStream(file);
            ActivityList serialActivitiesList = (ActivityList) in.readObject();
            viewReference.clearListView(activitiesListView);
            activitiesList.clearActivitiesList();
            for(int i = 0; i < serialActivitiesList.getSize(); i++)
            {
                Activity myActivity = serialActivitiesList.getActivity(i);
                viewReference.addActivityToList(activitiesListView, myActivity);
            }
            in.close();
            file.close();
            return true;
        }
        catch (IOException err) {
            System.out.println(err);
           return false;
          }
        
        catch (ClassNotFoundException err) {
            System.out.println(err);
            return false;
        }

    }
    
    
    /** 
     * This function adds an activity from the user into the <b>activityList</b>
     * @param activityObject The activity that is to be added to <b>activitiesList</b>
     */
    public void addEventHandler(Activity activityObject)
    {
       activitiesList.addActivity(activityObject);
    }

    
    /** 
     * This function removes the activity selected by the user from <b>activitiesList</b>
     * @param selectedActivity The Activity object that is to be removed
     */
    public void removeEventHandler(Activity selectedActivity)
    {
        activitiesList.removeActivity(selectedActivity);
    }

    
    /** 
     * This function calculates the toatal amount of points from all the activities in <b>activitiesList</b>
     * @return int THis is the total amount of points from all the activities in <b>activitiesList</b>
     */
    public int summaryEventHandler()
    {
        int totalPoints = 0;
        for(int i = 0; i < activitiesList.getSize(); i++)
        {
            Activity activityObject = activitiesList.getActivity(i);
            totalPoints += activityObject.getPoints();
        }
        return totalPoints;
    }

    
    /** 
     * Sorts the activities by date using the <b>DateComparator</b> comparator class
     * @param resultsListView The ListView element in the results tab that displays the activities
     */
    public void sortByDate(ListView<Activity> resultsListView)
    {
        activitiesList.sortByDate();
        refreshEventHandler(resultsListView);
    }

    
    /** 
     * Sorts the activities by name using the <b>DateComparator</b> comparator class
     * @param resultsListView he ListView element in the results tab that displays the activities
     */
    public void sortByActivityName(ListView<Activity> resultsListView)
    {
        activitiesList.sortByActivityName();
        refreshEventHandler(resultsListView);
    }

    
    /** 
     * Automatically refreshes the ListView element when the results tab is clicked on
     * @param resultsListView The Listview element that is to be refreshed
     */
    public void refreshEventHandler(ListView<Activity> resultsListView)
    {
        viewReference.clearListView(resultsListView);
        for(int i = 0; i < activitiesList.getSize(); i++)
        {
            Activity myActivity = activitiesList.getActivity(i);
            resultsListView.getItems().add(myActivity);
        }
    }
    
    
    /** 
     * Parses the activity String entered by the user and adds it to the pre-defined list of activities and their points
     * @param activityString The activity String that is to be added to <b>userSelectedActivities</b>
     */
    public void addUserActivity(String activityString)
    {
        for(int i = 0; i < userSelectedActivites.size(); i++)
        {
            String s = userSelectedActivites.get(i);
            String activityNameToAdd = activityString.split(" : ")[0];
            String activityInList = s.split(" : ")[0];

            if(activityNameToAdd.equals(activityInList))
            {
                System.out.println("Activity already exists!");
                return;
            }
        }
        userSelectedActivites.add(activityString);
    }
     
    /** 
     *This function writes the pre-defined activities selected by the user into a file
     */
    public void writeChoicesToFile()
    {
        try{
            FileOutputStream file = new FileOutputStream("activities");
            ObjectOutputStream out = new ObjectOutputStream(file);
            
            out.writeObject(userSelectedActivites);
            out.close();
            file.close();
        }
        
        catch (IOException err) {
            System.out.println(err);
          }   
    }

    
    /** 
     * Reads the list of pre-defined activites chosen by the user and adds it to <b>userSelectedActivities</b>
     * @param options The string of activities that appears in the dropdown menu
     */
    public void readChoicesFromFile(ObservableList<String> options)
    {
        try {
            FileInputStream file = new FileInputStream("activities");
            ObjectInputStream in = new ObjectInputStream(file);
            UserActivities serialChoiceList = (UserActivities) in.readObject();

            viewReference.clearObservableList(options);

            for(int i = 0; i < serialChoiceList.size(); i++)
            {
                String myActivityString = serialChoiceList.get(i);
                viewReference.addToObservableList(options, myActivityString);
            }

            userSelectedActivites.clear();
            for(int i = 0; i < serialChoiceList.size(); i++)
            {
                userSelectedActivites.add(serialChoiceList.get(i));
            }
            in.close();
            file.close();
        }
        catch (IOException err) {
            System.out.println(err);
          }
        
        catch (ClassNotFoundException err) {
            System.out.println(err);
        }
    }

    
    /** 
     * Removes an activity from the list of pre-defined activities stored in <b>userSelectedActivities</b>
     * @param myActivity The activiy string that is to be removed
     */
    public void removeUserActivity(String myActivity)
    {
        System.out.println(userSelectedActivites.size());
        for(int i = 0; i < userSelectedActivites.size(); i++)
        {
            String s = userSelectedActivites.get(i);
            if(myActivity.equals(s))
            {
                userSelectedActivites.remove(myActivity);
                writeChoicesToFile();
                return;
            }
        }
    }
    
    /** 
     * Exits the application
     * @param e The event passed in the lambda function
     * @param primaryStage The stage on which the application is displayed
     */
    public void exitEventHandler(ActionEvent e, Stage primaryStage)
    {
        e.consume();
        primaryStage.close();
    }
}