package ch.makery.address.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Date;

/**
 * Created by afetrist on 07.12.15.
 */
public class Payment {
    private final StringProperty dateTo;
    private final StringProperty salary;

    public Payment(int salary){
        Date today = new Date();
        this.dateTo = new SimpleStringProperty(today.getYear() + "-" + today.getMonth() + "-" + today.getDay());
        this.salary = new SimpleStringProperty(new Integer(salary).toString());
    }

    public Payment(int salary, String date){
        this.salary = new SimpleStringProperty(new Integer(salary).toString());
        this.dateTo = new SimpleStringProperty(date);
    }

    public String getDateTo() {
        return dateTo.get();
    }

    public StringProperty dateToProperty() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo.set(dateTo);
    }

    public String getSalary() {
        return salary.get();
    }

    public StringProperty salaryProperty() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary.set(salary);
    }
}
