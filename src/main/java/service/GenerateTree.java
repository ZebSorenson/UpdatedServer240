package service;

import dao.DataAccessException;
import dao.Database;
import dao.PersonDao;
import model.Person;

import java.sql.Connection;
import java.util.UUID;

//in the register service, create the database connection and then pass it into this class





public class GenerateTree {

    Connection myConnection = null;
    String userName = null;

    int numPeople = 0;

    int numEvents = 0;

    public GenerateTree(Connection conn, String userName) {

        this.myConnection = conn;
        this.userName = userName;

    }

    Person generatePerson(String gender, int generations, int year ) throws DataAccessException {

       PersonDao personDataAccess = new PersonDao(myConnection);

        Person mother = null;

        Person father = null;

        if(generations>1){
            mother = generatePerson("f", generations-1, 10); // adjust reasonable year
            father = generatePerson("m", generations-1, 10);

          //  String spouseID = UUID.randomUUID().toString();

            mother.setSpouseID(father.getPersonID());

            father.setSpouseID(mother.getPersonID());

            personDataAccess.insert(mother); // put all of service in a try

            personDataAccess.insert(father);

            numPeople+=2;

            //one UUID for the eventID. Might need to insert it twice want everything to match up
            //last thing here is to make the marriage event. Get UUID for marrriage event for each of them
            //get a random location, pull from the locations list
            //create events for mother and father
            //mother marriage event, UUID of event...Associated username, personID for mother, lat and lon...country, city and marriage, year as well
            //Year is the person was born. Year +20
            //Father is identical.. all info should be the same
            //all done in if statement
            //after you've made the events, call the event dao and add them to the databas

            //add the marriage events that need to be in sync
        }

        //exit if stament and create the person

        //this will be the child of the people that were created.

        //Make uuid for person ID...Get their names,

        //events, check the gender of the person. this will determine what list you will pull from
        //save the person in the person in the database

        //if the father is null, not going further back set the parent ID's to
        //This will be the base case for as far back as we go

        //create their birth events, eventID and the event itself, insert into the database and incriment the events added
        //same thing with the death of the person. This will be an event in itself
        //birth event, just pass in the year and then

        //create the person and return it.

        //need to know how many total people and events are added



       // Person person = new Person(UUID.randomUUID().toString(), )
     return null;
    }


//need to have base case of if the father/mother is null, we have reached the end.
}
