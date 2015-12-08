package ch.makery.address.view;

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
        }
        else {
            myResult = originalList;
        }

        if(!allWordsCheckBox.isSelected()) {
            //Tutaj kiedys bedzie sortowanie personMap po values
            for(Person person : personMap.keySet())
                myResult.add(person);
        }
        return myResult;
    }

    private int howManyFits(Person person){
        int result = 0;
        String[] keys = filtringData.getText().split(" ");

        if(fullWordsCheckBox.isSelected()){
            for(String key : keys) {
                if(person.getFirstName().equals(key)){
                    result++;
                    continue;
                }
                if(person.getLastName().equals(key)){
                    result++;
                    continue;
                }
            }
        }
        else{
            for(String key : keys) {
                if(person.getFirstName().contains(key)){
                    result++;
                    continue;
                }
                if(person.getLastName().contains(key)) {
                    result++;
                    continue;
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
}