package ch.makery.address.mockExteriorClasses;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Random;

/**
 * Created by afetrist on 08.12.15.
 */
public class Team {
    private final StringProperty name;
    private final StringProperty leader;

    public Team(){
        this.name = new SimpleStringProperty(getRandomName());
        this.leader = new SimpleStringProperty(getRandomLeader());
    }

    private String getRandomLeader(){
        String[] pool = {"John Rambo", "Tyrion Lannister", "Muminek's Father", "Alien"};
        return pool[new Random().nextInt(pool.length)];
    }

    private String getRandomName(){
        String[] pool = {"Realise the Kraken", "This is Sparta", "WWW", "Great Battle of History"};
        return pool[new Random().nextInt(pool.length)];
    }


    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getLeader() {
        return leader.get();
    }

    public StringProperty leaderProperty() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader.set(leader);
    }
}
