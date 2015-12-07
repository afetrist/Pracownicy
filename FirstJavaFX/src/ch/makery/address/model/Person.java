package ch.makery.address.model;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.beans.property.*;


public class Person {

    private final LongProperty id;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty position;
    private final IntegerProperty salary;
    private final StringProperty phone;
    private final StringProperty date;
    private final StringProperty email;
    private final StringProperty pesel;
    private final StringProperty url;
    private final ObjectProperty<ArrayList<Payment>> salaryHistory;
    private ObjectProperty<ArrayList<Integer>> projectsParticipated;
    private ObjectProperty<ArrayList<Integer>> teamsJoined;

    private static long nextId = 0;
    //private final ObjectProperty<LocalDate> birthday;

    /**
     * Default constructor.
     */
    public Person() {
        this(null, null);
    }

    /**
     * Constructor with some initial data.
     *
     * @param firstName
     * @param lastName
     */
    public Person(String firstName, String lastName) {
        this.id = new SimpleLongProperty(Person.nextId);
        Person.nextId++;

        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);

        // Some initial dummy data, just for convenient testing.
        this.position = new SimpleStringProperty("Witcher");
        this.salary = new SimpleIntegerProperty(2000);
        this.date = new SimpleStringProperty("15.07.1410");
        this.email = new SimpleStringProperty("white@withers.com");
        this.phone = new SimpleStringProperty("+48 123 456 789");
        this.pesel = new SimpleStringProperty("94010100001");
        this.url = new SimpleStringProperty("");
        this.salaryHistory = new SimpleObjectProperty<ArrayList<Payment>>();
        this.projectsParticipated = new SimpleObjectProperty<ArrayList<Integer>>();
        this.teamsJoined = new SimpleObjectProperty<ArrayList<Integer>>();
    }

    public Person(String firstName, String lastName, String position, int salary, String date, String email, String phone, String pesel, String url){
        this.id = new SimpleLongProperty(Person.nextId);
        Person.nextId++;
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.position = new SimpleStringProperty(position);
        this.salary = new SimpleIntegerProperty(salary);
        this.date = new SimpleStringProperty(date);
        this.email = new SimpleStringProperty(email);
        this.phone = new SimpleStringProperty(phone);
        this.pesel = new SimpleStringProperty(pesel);
        this.url = new SimpleStringProperty(url);
        this.salaryHistory = new SimpleObjectProperty<ArrayList<Payment>>();
        this.projectsParticipated = new SimpleObjectProperty<ArrayList<Integer>>();
        this.teamsJoined = new SimpleObjectProperty<ArrayList<Integer>>();
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getPosition() {
        return position.get();
    }

    public StringProperty positionProperty() {
        return position;
    }

    public void setPosition(String position) {
        this.position.set(position);
    }

    public int getSalary() {
        return salary.get();
    }

    public IntegerProperty salaryProperty() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary.set(salary);
    }

    public String getPhone() {
        return phone.get();
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public String getPesel() {
        return pesel.get();
    }

    public StringProperty peselProperty() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel.set(pesel);
    }

    public String getUrl() {
        return url.get();
    }

    public StringProperty urlProperty() {
        return url;
    }

    public void setUrl(String url) {
        this.url.set(url);
    }
}