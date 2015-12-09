package ch.makery.address.mockExteriorClasses;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Random;

/**
 * Created by afetrist on 08.12.15.
 */
public class Project {

    private final StringProperty topic;
    private final StringProperty leader;

    public Project(){
        this.topic = new SimpleStringProperty(getRandomName());
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

    public String getTopic() {
        return topic.get();
    }

    public StringProperty topicProperty() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic.set(topic);
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
