package RequestResult;

import model.Event;
import model.Person;
import model.User;

/**
 * Load request containing info for the load service object to act on
 */
public class LoadRequest {

    //array of users
    //array of persons
    //array of events

    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public Person[] getPersons() {
        return persons;
    }

    public void setPersons(Person[] persons) {
        this.persons = persons;
    }

    public Event[] getEvents() {
        return events;
    }

    public void setEvents(Event[] events) {
        this.events = events;
    }

    /**
     * Array to hold list of users for load request
     */

    User[] users;

    /**
     * Array to hold list of persons for load request
     */

    Person[] persons;

    /**
     * Array to hold list of events for load request
     */

    Event[] events;

}