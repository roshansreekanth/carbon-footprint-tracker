package views;

import java.time.LocalDate;
import java.util.ArrayList;

import controller.Controller;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Activity;
import print_welcome_message.StartMessage;

public class View extends Application
{
    private Controller controllerReference;
 
    public View()
    {
        controllerReference = new Controller(this);
    }
    
    
    /** 
     * Renders a gridPane object that follows the specifications given
     * @param position The position on the screen the gridPane starts at
     * @param HGap The horizontal distance between two elements
     * @param VGap The vertical distance between two elements
     * @param insetValuesObject Sets the borders of a grid in a 4 sided rectangular area
     * @return GridPane This returns the constructed gridPane object
     */
    public GridPane renderGridPane(Pos position, int HGap, int VGap, Insets insetValuesObject)
    {
        GridPane grid = new GridPane();
        grid.setAlignment(position);
        grid.setHgap(HGap);
        grid.setVgap(VGap);
        grid.setPadding(insetValuesObject);
        
        return grid;
    }

    
    /** 
     * Renders a HBox based on the specifications given
     * @param spacing The horizontal distance between two elements
     * @return HBox This returns the constructed HBox object
     */
    public HBox renderHBox(double spacing)
    {
        HBox HBoxObject = new HBox();
        HBoxObject.setSpacing(spacing);
        return HBoxObject;
    }

    
    /** 
     * Clears the dropdown menu via the controller
     * @param options The Observable String list of activities
     */
    public void clearObservableList(ObservableList<String> options)
    {
        options.clear();
    }

    
    /** 
     * Adds an element to the dropdown menu
     * @param options The Observable String list of activities
     * @param myActivityString The activity string to be added
     */
    public void addToObservableList(ObservableList<String> options, String myActivityString)
    {
        options.add(myActivityString);
    }

    
    /** 
     * Renders an alert box based on the specifications given
     * @param titleMessage The main heading of the alert box
     * @param HeaderText The header sub-text
     * @param contentMessage The main message
     */
    public void renderAlertbox(String titleMessage, String HeaderText, String contentMessage)
    {
        Alert alertBox = new Alert(AlertType.INFORMATION);
        alertBox.setTitle(titleMessage);
        alertBox.setHeaderText(HeaderText);
        alertBox.setContentText(contentMessage);
        alertBox.showAndWait();
    } 

    
    /** 
     * Clears the ListView object of all elements
     * @param listViewObject The ListView objecct to be cleared
     */
    public void clearListView(ListView<Activity> listViewObject)
    {
        listViewObject.getItems().clear();
    }
  
    
    /** 
     * Adds an activity to the ListView
     * @param activitiesListView The ListView Object to which the Activity Object is added
     * @param myActivity The Activity object that is added to the ListView
     */
    public void addActivityToList(ListView<Activity> activitiesListView, Activity myActivity)
    {
        activitiesListView.getItems().add(myActivity);
        controllerReference.addEventHandler(myActivity);
    }

    
    /** 
     * Removes an activity to the ListView
     * @param activitiesListView The ListView Object from which the Activity Object is removed
     * @param activityObject The Activity object that is removed from the ListView
     */
    public void removeFromList(ListView<Activity> activitiesListView, Activity activityObject)
    {
        activitiesListView.getItems().remove(activityObject);
        controllerReference.removeEventHandler(activityObject);
    }
    
    
    /** 
     * The main method
     * @param args Command line arguments
     */
    public static void main(String[] args)
    {
       StartMessage.printMessage();
       launch(args);
    }

    
    /** 
     * The start() method begins the javafx program
     * @param primaryStage The stage on which the program is rendered
     */
    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("My Carbon Impact Application");
        
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
        
        Tab introTab = new Tab("Intro");
        Tab mainTab = new Tab("Manage");
        Tab resultsTab = new Tab("Results");
        Tab activityTab = new Tab("Activities");

        Label welcomeMessage = new Label("Welcome to the app!");

        introTab.setContent(welcomeMessage);
        tabPane.getTabs().add(introTab);

        GridPane grid = renderGridPane(Pos.TOP_LEFT, 10, 10, new Insets(25, 25, 25, 25));
        
        Label week = new Label("Week");
        Spinner<Integer> weekTextField = new Spinner<Integer>(1, Integer.MAX_VALUE, 1);
        weekTextField.setEditable(true);
        HBox weekInput = renderHBox(20);
        weekInput.getChildren().addAll(week, weekTextField);
        grid.add(weekInput, 0, 0);

        Label date = new Label("Date");
        DatePicker dateTextField = new DatePicker();
        HBox dateInput = renderHBox(20);
        dateInput.getChildren().addAll(date, dateTextField);
        grid.add(dateInput, 0, 1);

        ObservableList<String> options = FXCollections.observableArrayList();

        Label activity = new Label("Activity");
        ComboBox<String> activitiesDropDown = new ComboBox<String>(options);
        HBox activityInput = renderHBox(20);
        activityInput.getChildren().addAll(activity, activitiesDropDown);
        grid.add(activityInput, 0, 2);

        Button add = new Button("Add");
        Button remove = new Button("Remove");
        Button summary = new Button("Summary");
        Button memoryLeak = new Button("Memory Leak");

        HBox buttonsRow = renderHBox(20);
        buttonsRow.getChildren().addAll(add, remove, summary, memoryLeak);
        grid.add(buttonsRow, 0, 4);

        ListView<Activity> activitiesListView = new ListView<Activity>();
        grid.add(activitiesListView, 0, 5);

        Button load = new Button("Load");
        Button save = new Button("Save");
        HBox footer = renderHBox(20);
        footer.getChildren().addAll(load, save);
        grid.add(footer, 0, 6);

        Button exit = new Button("Exit");
        grid.add(exit, 1, 6);
        mainTab.setContent(grid);

        tabPane.getTabs().add(mainTab);

        GridPane resultsGrid = renderGridPane(Pos.TOP_LEFT, 10, 10, new Insets(25, 25, 25, 25));
        ListView<Activity> resultsListView = new ListView<Activity>();
        resultsGrid.add(resultsListView, 0, 0);
        
        Button dateOrder = new Button("Order by date");
        Button activityOrder = new Button("Order by activity");
        VBox sortButtons = new VBox(dateOrder, activityOrder);
        sortButtons.setSpacing(10);
        resultsGrid.add(sortButtons, 1, 0);

        resultsTab.setContent(resultsGrid);
        tabPane.getTabs().add(resultsTab);

        GridPane addTabGrid = renderGridPane(Pos.CENTER, 10, 10, new Insets(25, 25, 25, 25));
        
        Label activityNameLabel = new Label("Name");
        Label pointsLabel = new Label("Points");
        Label removeLabel = new Label("Remove");

        TextField addChoiceTextField = new TextField();
        Spinner<Integer> pointsChoiceTextField = new Spinner<Integer>(-10, 10, 1);
        pointsChoiceTextField.setEditable(true);
        ObservableList<String> removeOptions = FXCollections.observableArrayList();
        ComboBox<String> removeDropdown = new ComboBox<String>();
        
        Button addChoiceButton = new Button("Add");
        Button removeChoiceButton = new Button("Remove");

        HBox addChoiceBox = renderHBox(10);
        addChoiceBox.getChildren().addAll(activityNameLabel, addChoiceTextField, pointsLabel, pointsChoiceTextField, addChoiceButton);
        
       
        
        HBox removeChoiceBox = renderHBox(10);
        removeChoiceBox.getChildren().addAll(removeLabel, removeDropdown, removeChoiceButton);
        
        addTabGrid.add(addChoiceBox, 0, 0);
        addTabGrid.add(removeChoiceBox, 0, 4);

        activityTab.setContent(addTabGrid);
        tabPane.getTabs().add(activityTab);

        Scene scene = new Scene(tabPane, 500, 500);
        scene.getStylesheets().add("stylesheet.css");
        primaryStage.setScene(scene);
        primaryStage.show();

        resultsTab.setOnSelectionChanged(e -> 
        {
            if(resultsTab.isSelected())
            {
                controllerReference.refreshEventHandler(resultsListView);
            }
        });

        mainTab.setOnSelectionChanged(e ->
        {
            if(mainTab.isSelected())
            {
                controllerReference.readChoicesFromFile(options);
                activitiesDropDown.setItems(options);
                activitiesDropDown.getSelectionModel().selectFirst(); 
            }
        });

        activityTab.setOnSelectionChanged(e -> {
            if(activityTab.isSelected())
            {
                removeOptions.clear();
                controllerReference.readChoicesFromFile(removeOptions);
                removeDropdown.setItems(removeOptions);
                removeDropdown.getSelectionModel().selectFirst();
            }

        });

        add.setOnAction(e -> {
            String selectedActivityString = (String) activitiesDropDown.getValue();
            if(selectedActivityString != null && selectedActivityString.length() > 0)
            {
                String splitActivityDetails[] = selectedActivityString.split(" : ");

                String activityAttr = splitActivityDetails[0];
                int pointsAttr = Integer.parseInt(splitActivityDetails[1]); 
                int weekAttr = weekTextField.getValue();
                LocalDate dateAttr = dateTextField.getValue();
                if(activityAttr.length() != 0 && dateAttr != null)
                {
                    Activity activityObject = new Activity(weekAttr, dateAttr, activityAttr, pointsAttr);
                    addActivityToList(activitiesListView, activityObject);
                }  
            }

        });

        remove.setOnAction(e -> {
            Activity selectedActivity = activitiesListView.getSelectionModel().getSelectedItem();
            removeFromList(activitiesListView, selectedActivity);
        });

        summary.setOnAction(e ->
        {
            int totalPoints = controllerReference.summaryEventHandler();
            renderAlertbox("Summary", "", "Your total points are: " + totalPoints);
        });

        save.setOnAction(e-> 
        {
            boolean status = controllerReference.saveEventHandler();
            
            if(status)
            {
                renderAlertbox("Save", "", "Save Succesful");
            }
            else
            {
                renderAlertbox("Save", "", "Save Unsuccessful");
            }
        });

        load.setOnAction(e-> 
        {
            boolean status = controllerReference.loadEventHandler(activitiesListView);
            
            if(status)
            {
                clearListView(activitiesListView);
                controllerReference.loadEventHandler(activitiesListView);
            }
            else
            {
                renderAlertbox("Load", "", "Load Unsuccessful");
            }
        });

        
        addChoiceButton.setOnAction(e -> {
            String chosenActivity = (String) addChoiceTextField.getText();
            if(chosenActivity.length() > 0)
            {
                chosenActivity += " : " + pointsChoiceTextField.getValue();
                controllerReference.addUserActivity(chosenActivity);
                controllerReference.writeChoicesToFile();
                removeOptions.clear();
                controllerReference.readChoicesFromFile(removeOptions);
                removeDropdown.setItems(removeOptions);
                removeDropdown.getSelectionModel().selectFirst();
            }
    
        });

        removeChoiceButton.setOnAction(e ->
        {
            String chosenActivity = (String) removeDropdown.getValue();
            controllerReference.removeUserActivity(chosenActivity);
            controllerReference.readChoicesFromFile(options);
            controllerReference.readChoicesFromFile(removeOptions);
            removeDropdown.setItems(removeOptions);
            removeDropdown.getSelectionModel().selectFirst();
        });

        dateOrder.setOnAction(e -> {
            controllerReference.sortByDate(resultsListView);  
        });

        activityOrder.setOnAction(e -> {
            controllerReference.sortByActivityName(resultsListView);
        });

        memoryLeak.setOnAction(e -> {
            ArrayList<Activity> objectStore = new ArrayList<Activity>();
            while(true)
            {
                Activity testActivity = new Activity(1, LocalDate.parse("2020-03-15"), "Test Activity", 1);
                objectStore.add(testActivity);
            }
        });

        exit.setOnAction(e -> {
            controllerReference.exitEventHandler(e, primaryStage);
        });
    }
}