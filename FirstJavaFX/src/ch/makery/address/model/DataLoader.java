package ch.makery.address.model;

import ch.makery.address.mockExteriorClasses.Project;
import ch.makery.address.mockExteriorClasses.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by afetrist on 15.12.15.
 */
public class DataLoader {
    private long id;
    public DataLoader(long id){
        this.id = id;
    }

    public ObservableList<Project> getProjects(){
        ObservableList<Project> result = FXCollections.observableArrayList();

        //this part is only for mocks
        result.add(new Project());
        result.add(new Project());

        return result;
    }

    public ObservableList<Team> getTeams(){
        ObservableList<Team> result = FXCollections.observableArrayList();

        //this part is only for mocks
        result.add(new Team());
        result.add(new Team());

        return result;
    }

}
