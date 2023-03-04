package service;

import JSonMagic.json.*;
import dao.*;
import model.Event;
import model.Person;
import model.User;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;
import java.util.UUID;

//in the register service, create the database connection and then pass it into this class





public class TreeGenerator {

    Database db = new Database();

    Connection myConnection = null;
    String userName = null;

    int numPeople = 0;

    int numEvents = 0;



    public TreeGenerator(Connection conn, String userName) {

        this.myConnection = conn;
        this.userName = userName;

    }

    Person generatePersonTree(String gender, int generations, int year) throws DataAccessException, FileNotFoundException, SQLException {

        //should this be in a try catch block because it deals with the database?

        PersonDao personDataAccess = new PersonDao(myConnection);

        EventDao eventDataAccess = new EventDao(myConnection);

        UserDao userDataAccess = new UserDao(myConnection);

        User rootUser = userDataAccess.findUser(userName); //used to check if we already have this user in the database

        Person basePerson = null; //the root child of the tree


        Boolean duplicate = personDataAccess.findTrue(rootUser.getPersonID());

        if(!duplicate){
            basePerson = makePerson(rootUser); //if the user is not already in the database, we create the base person with the username
            System.out.println("new user");
        }else{
            basePerson=personDataAccess.find(rootUser.getPersonID());
            System.out.println("Duplicate");
        }

        //call create parents, will generate random data for the parents

        createParents(basePerson, generations, personDataAccess); //we base our base person in to make sure we can keep
        //the parents connected to them

        //open database connection

        personDataAccess.insert(basePerson); //insert the new base person into the database

        myConnection.commit();
        myConnection.close();

        //possible add a new database and connection. Open and close before doing anything
        //don't try and open database multiple times
        return basePerson;
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

    private Person makePerson(User user){

        Person newPerson = new Person(user.getPersonID(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getGender(),null, null, null);

        return newPerson;
    }

    private Person[] createParents(Person currPerson, int currentGeneration, PersonDao personDataAccess) throws FileNotFoundException, DataAccessException {


        Person mother = null;

        Person father = null;



        if(currentGeneration>0){

            mother = new Person(UUID.randomUUID().toString(),currPerson.getAssociatedUsername(),null, null, "f", null, null, null);
            father = new Person (UUID.randomUUID().toString(), currPerson.getAssociatedUsername(), null, null, "m", null, null, null);

            setMaleRandomName(father);

            setRandomFemaleFirstName(mother);

            mother.setLastName(father.getLastName());

            mother.setSpouseID(father.getPersonID());

            father.setSpouseID(mother.getPersonID());


            //not setting mother id's try printing
            currPerson.setFatherID(father.getPersonID());
            currPerson.setMotherID(mother.getPersonID());

            //create the events for the people...This is where you will call your make event functions for mom and dad...
            createParents(mother, currentGeneration-1, personDataAccess);

            createParents(father, currentGeneration-1, personDataAccess);

            personDataAccess.insert(mother); // put all of service in a try

            personDataAccess.insert(father);


            numPeople+=2;

        }


        return new Person[]{mother, father};
    }


//need to have base case of if the father/mother is null, we have reached the end.
}

//function that makes a birth and a death
//other that makes marriage, birth and death
//make a function called make events...make a marriage event, make birth, make death...

//have the second create event function take in an event and then set the new person to the marriage event.

//then call make events every time in cr


//possible move this stuff into the fill service and the register in


//should make everything 25 years apart to keep things easy. This will help to avoid weird event data

