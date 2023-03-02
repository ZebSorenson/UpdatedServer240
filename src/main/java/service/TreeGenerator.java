package service;

import JSonMagic.json.*;
import dao.DataAccessException;
import dao.EventDao;
import dao.PersonDao;
import model.Event;
import model.Person;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.Random;
import java.util.UUID;

//in the register service, create the database connection and then pass it into this class





public class TreeGenerator {

    Connection myConnection = null;
    String userName = null;

    int numPeople = 0;

    int numEvents = 0;



    public TreeGenerator(Connection conn, String userName) {

        this.myConnection = conn;
        this.userName = userName;

    }

    Person generatePersonTree(String gender, int generations, int year) throws DataAccessException, FileNotFoundException {

         PersonDao personDataAccess = new PersonDao(myConnection);
        EventDao eventDataAccess = new EventDao(myConnection);

        Person mother = null;

        Person father = null;

        if(generations+1>1){

            mother = generatePersonTree("f", generations-1, year-25); // adjust reasonable year
            father = generatePersonTree("m", generations-1, year-25);

             mother.setGender("f");
             father.setGender("m");


            mother.setSpouseID(father.getPersonID());

            father.setSpouseID(mother.getPersonID());





            setMaleRandomName(father);

            setRandomFemaleFirstName(mother);

            mother.setLastName(father.getLastName());//father and mother will have last name

            //set the usernames

            father.setAssociatedUsername(userName);
            mother.setAssociatedUsername(userName);

            //create the marriage event

//            Event fatherMarriage = generateMarriageEvent(userName, father.getPersonID(), year -50);
//
//            Event motherMarriage = fatherMarriage; //we do this so that all the needed info matches up.
//
//            motherMarriage.setPersonID(mother.getPersonID()); //we can first set the mother's marriage event to the father and then change ID and eventID
//
//            motherMarriage.setEventID(UUID.randomUUID().toString()); // give the mother marriage event a unique ID
//
//
//
//
//            //create the rest of the person information, names with the Json info before inserting them info the database
//
            personDataAccess.insert(mother); // put all of service in a try

            personDataAccess.insert(father);
//
//            eventDataAccess.insert(motherMarriage); // insert the mother marriage event into the database
//            eventDataAccess.insert(fatherMarriage); //insert the father marriage event into the database



            numPeople+=2;

            //add the marriage events here. This is where you will use the jSon data you were able to retrieve

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


        } //this is the end of the if statement



        //need to create person object for mother and then father

       // Person person = new Person(UUID.randomUUID().toString(),"random", "motherFirst","motherLast", "f", "123","123","123");
        //exit if stament and create the person

        Person person = new Person(UUID.randomUUID().toString(), userName); //need to have a personID
        //need to create person events for user before returning the personObject. Connect the events to this person
//        person.setGender("f");
//        person.setFirstName("Zeb");
//        person.setLastName("Sorenson");


        personDataAccess.find(userName);

        //personDataAccess.insert(person);

        //this will be the child of the people that were created.

        //Make uuid for person ID...Get their names,

        //events, check the gender of the person. this will determine what list you will pull from
        //save the person in the person in the database

        //if the father is null, not going further back set the parent ID's to null. We've reached the end of the recusrsion
        //This will be the base case for as far back as we go

        //create their birth events, eventID and the event itself, insert into the database and incriment the events added
        //same thing with the death of the person. This will be an event in itself
        //birth event, just pass in the year and then subtract a certain amount -20?

        //create the person and return it.

        //need to know how many total people and events are added



       //how do I handle creating the attributes of the person? we could get all of the info from the Register request object
        //but I don't know how we would handle this with the fill API
        //Update, fill API comes with the username that is already in the database



     return person;
    }

    private Event generateMarriageEvent(String associatedUsername, String personID, int yearParam) throws FileNotFoundException {

        LocationsGenerator locs = new LocationsGenerator();

        int max = locs.getLocationList().size(); //this is how far we can go in our random generation

        int randomLocationIndex = new Random().nextInt(max);

        float latitude = locs.getLocationList().get(randomLocationIndex).getLatitude();
        float longitude = locs.getLocationList().get(randomLocationIndex).getLongitude();
        String country = locs.getLocationList().get(randomLocationIndex).getCountry();
        String city = locs.getLocationList().get(randomLocationIndex).getCity();
        String eventType = "marriage";
        int year = yearParam;

        Event marriageEvent = new Event(UUID.randomUUID().toString(),associatedUsername,personID,latitude,longitude,country,city,eventType, year);

        return marriageEvent;






    }


    private void setMaleRandomName(Person father) throws FileNotFoundException { //setting first and last name to random index in JSon name files

        //first name
        maleNameGenerator maleNames = new maleNameGenerator();

        int max = maleNames.getMaleNameArray().size();

        Random randomFirstName = new Random();

        int randomNameIndex = randomFirstName.nextInt(max+1); //arrays start at 0 in Java, right?

        father.setFirstName(maleNames.getMaleNameArray().get(randomNameIndex));
        //last name

        sirNameGenerator lastNames = new sirNameGenerator();

        int maxLastName = lastNames.getSireNameList().size();

        Random randomLastName = new Random();

        int randomLastNameIndex = randomLastName.nextInt( maxLastName+1);

        father.setLastName(lastNames.getSireNameList().get(randomNameIndex));

    }

    private void setRandomFemaleFirstName(Person mother) throws FileNotFoundException {

        femaleNameGenerator femaleNames = new femaleNameGenerator();

        int max = femaleNames.getFemaleNameList().size();

        Random randomFirstName = new Random();

        int randomFirstNameIndex = randomFirstName.nextInt(max+1);

        mother.setFirstName(femaleNames.getFemaleNameList().get(randomFirstNameIndex));




    }


//need to have base case of if the father/mother is null, we have reached the end.
}
