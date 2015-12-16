package ch.makery.address.model;

import javafx.beans.property.*;

/**
 * Created by xibuk on 16.12.15.
 */
public class PersonForTeams implements iPerson {

    private final LongProperty id;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty position;
    private final IntegerProperty salary;
    private final StringProperty pesel;

    public PersonForTeams(String firstName, String lastName, String position, int salary, String pesel, Long id){
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.position = new SimpleStringProperty(position);
        this.salary = new SimpleIntegerProperty(salary);
        this.pesel = new SimpleStringProperty(pesel);
        this.id = new SimpleLongProperty(id);
    }

    @Override
    public String getFirstName() {
        return firstName.get();
    }

    @Override
    public String getLastName() {
        return lastName.get();
    }

    @Override
    public int getSalary() {
        return salary.get();
    }

    @Override
    public String getPesel() {
        return pesel.get();
    }

    @Override
    public String getPosition() {
        return position.get();
    }

    @Override
    public long getId() {
        return id.get();
    }
}
