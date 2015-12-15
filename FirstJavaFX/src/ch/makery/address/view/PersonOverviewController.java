package ch.makery.address.view;

import ch.makery.address.mockExteriorClasses.Project;
import ch.makery.address.mockExteriorClasses.Team;
import ch.makery.address.model.Payment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ch.makery.address.MainApp;
import ch.makery.address.model.Person;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.event.ActionEvent;
import java.lang.reflect.Array;
import java.util.*;


public class PersonOverviewController {
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;
    @FXML
    private TableColumn<Person, String> positionColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label positionLabel;
    @FXML
    private Label salaryLabel;
    @FXML
    private Label employedLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label phoneNumberLabel;
    @FXML
    private Label peselLabel;

    @FXML
    private ImageView imageView;

    @FXML
    private TextField filtringData;
    @FXML
    private Button findButton;

    @FXML
    private CheckBox fullWordsCheckBox;
    @FXML
    private CheckBox allWordsCheckBox;

    @FXML
    private TableView<Payment> salaryHistoryTable;
    @FXML
    private TableColumn<Payment, String> salaryColumn;
    @FXML
    private TableColumn<Payment, String> endDateColumn;

    @FXML
    private TableView<Team> teamsHistoryTable;
    @FXML
    private TableColumn<Team, String> teamNameColumn;
    @FXML
    private TableColumn<Team, String> teamLeaderColumn;

    @FXML
    private TableView<Project> projectsHistoryTable;
    @FXML
    private TableColumn<Project, String> projectTopicColumn;
    @FXML
    private TableColumn<Project, String> projectLeaderColumn;


    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public PersonOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        firstNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().lastNameProperty());
        positionColumn.setCellValueFactory(
                cellData -> cellData.getValue().positionProperty());

        // Clear person details.
        showPersonDetails(null);

        fullWordsCheckBox.setSelected(false);
        allWordsCheckBox.setSelected(false);

        // Listen for selection changes and show the person details when changed.
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));

    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        personTable.setItems(filterPeople());
    }

    private void showPersonDetails(Person person) {
        if (person != null) {
            // Fill the labels with info from the person object.
            firstNameLabel.setText(person.getFirstName());
            lastNameLabel.setText(person.getLastName());
            positionLabel.setText(person.getPosition());
            salaryLabel.setText(Integer.toString(person.getSalary()));
            employedLabel.setText(person.getDate());
            emailLabel.setText(person.getEmail());
            phoneNumberLabel.setText(person.getPhone());
            peselLabel.setText(person.getPesel());

            imageView.setImage(new Image(person.getUrl()));

            salaryColumn.setCellValueFactory(cellData -> cellData.getValue().salaryProperty());
            endDateColumn.setCellValueFactory(cellData -> cellData.getValue().dateToProperty());
            salaryHistoryTable.setItems(person.getSalaryHistory());

            person.loadTeams();
            teamNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
            teamLeaderColumn.setCellValueFactory(cellData -> cellData.getValue().leaderProperty());
            teamsHistoryTable.setItems(person.getTeamsJoined());

            person.loadProjects();
            projectTopicColumn.setCellValueFactory(cellData -> cellData.getValue().topicProperty());
            projectLeaderColumn.setCellValueFactory(cellData -> cellData.getValue().leaderProperty());
            projectsHistoryTable.setItems(person.getProjectsParticipated());


        } else {
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            positionLabel.setText("");
            salaryLabel.setText("");
            employedLabel.setText("");
            emailLabel.setText("");
            phoneNumberLabel.setText("");
            peselLabel.setText("");

            imageView.setImage(new Image("https://pbs.twimg.com/profile_images/425274582581264384/X3QXBN8C.jpeg"));
        }
    }

    private ObservableList<Person> filterPeople(){
        ObservableList<Person> myResult = FXCollections.observableArrayList();
        ObservableList<Person> originalList = mainApp.getPersonData();
        HashMap<Person, Integer> personMap = new HashMap<>();

        if(filtringData.getText().length() > 0) {

            for (Person person : originalList){
                int fits = howManyFits(person);

                if(!allWordsCheckBox.isSelected()){
                    if (fits > 0)
                        personMap.put(person, new Integer(fits));
                }
                else {
                    if(fits == filtringData.getText().split(" ").length)
                        myResult.add(person);
                }
            }
            if(!allWordsCheckBox.isSelected()) {
                LinkedHashMap<Person, Integer> sortedMap = sortHashMap(personMap);
                for(Person person : sortedMap.keySet())
                    myResult.add(person);
            }
        }
        else {
            myResult = originalList;
        }

        return myResult;
    }

    private LinkedHashMap<Person, Integer> sortHashMap(HashMap<Person, Integer> oldHashMap){
        Collection<Integer> values = oldHashMap.values();
        LinkedHashMap<Person, Integer> newHashMap = new LinkedHashMap<Person, Integer>();
        int max;
        Person temporaryPerson = null;


        while(!oldHashMap.isEmpty()){
            max = 0;
            for(Integer i : values){
                if(i.intValue() > max) {
                    max = i.intValue();
                }
            }

            for(Person person : oldHashMap.keySet())
                if(oldHashMap.get(person).equals(new Integer(max)))
                    temporaryPerson = person;

            newHashMap.put(temporaryPerson, new Integer(max));
            oldHashMap.remove(temporaryPerson);
        }
        return newHashMap;
    }

    private int howManyFits(Person person){
        int result = 0;
        String[] keys = filtringData.getText().split(" ");

        if(fullWordsCheckBox.isSelected()){
            for(String key : keys) {
                if(person.getFirstName().equals(key)){
                    result++;
                }
                if(person.getLastName().equals(key)){
                    result++;
                }
            }
        }
        else{
            for(String key : keys) {
                if(person.getFirstName().contains(key)){
                    result++;
                }
                if(person.getLastName().contains(key)) {
                    result++;
                }
            }
        }
        return result;
    }



    @FXML
    private void handleFilterPeople(){
        personTable.setItems(filterPeople());
    }

    @FXML
    private void handleDeletePerson(){
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if(selectedPerson != null){
            boolean okClicked = mainApp.showPersonDelete(selectedPerson);
            if(okClicked){
                mainApp.deletePerson(selectedPerson);
            }
        }
        personTable.setItems(filterPeople());

    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewPerson() {
        Person tempPerson = new Person();
        boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
        if (okClicked) {
            mainApp.getPersonData().add(tempPerson);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditPerson() {
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                showPersonDetails(selectedPerson);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }
}