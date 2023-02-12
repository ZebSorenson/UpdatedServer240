package dao;

import model.Person;

/**
 * Person Data Access class. This will be used to manipulate data of person objects
 */

public class PersonDao {

    /**
     * inserts a new person into the database
     * @param person the person object that we want to insert into the database
     */

    public void insert(Person person){

    }

    /**
     * Will return a specific person from the database
     * @param personID_String a unique ID associated with the person used for identification
     * @return the Person object associated with the ID parameter
     */

    public Person find(String personID_String){
        return null;
    }

    /**
     * Will delete a person from the database bassed on their unique ID
     * @param personID_String a unique ID used for identifying a specific person in the database
     */

    public void deletePerson(String personID_String){

    }

    /**
     * will delete all person objects from the database
     */
    public void clear(){

    }


    //end of class
}