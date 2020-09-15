package test;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import controller.Controller;
import javafx.scene.control.ListView;
import models.Activity;
import views.View;

public class MyProgramTest
{
    View view;
    Controller controller;
    ListView<Activity> listView;
    ListView<Activity> emptyListView;
    Activity testActivity;

    @Before
    public void setUp()
    {
        com.sun.javafx.application.PlatformImpl.startup(()->{}); // Initializes the JavaFX Toolkit. JavaFX won't run otherwise.
        view = new View();
        controller = new Controller(view);
        listView = new ListView<Activity>();
        emptyListView = new ListView<Activity>();
        testActivity = new Activity(5, LocalDate.parse("2020-03-15"), "Walking to work", 10);
    }

    @Test
    public void testAddActivity()
    {
        assertEquals(listView.getItems(), emptyListView.getItems());
        view.addActivityToList(listView, testActivity);
        assertNotEquals(listView, emptyListView);
    }

    @Test
    public void testRemoveActivity()
    {
        view.addActivityToList(listView, testActivity);
        assertNotEquals(listView.getItems(), emptyListView.getItems());
        view.removeFromList(listView, testActivity);
        assertEquals(listView.getItems(), emptyListView.getItems());
    }

    @Test
    public void testPointCalculation()
    {
        assertEquals(controller.summaryEventHandler(), 0); //No activities added
        controller.addEventHandler(testActivity);
        assertEquals(controller.summaryEventHandler(), testActivity.getPoints());
    }

}