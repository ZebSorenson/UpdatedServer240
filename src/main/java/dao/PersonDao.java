package dao;

import model.Event;
import model.Person;

import java.sql.*;

/**
 * Person Data Access class. This will be used to manipulate data of person objects
 */

public class PersonDao {

   private final Connection conn;

    public PersonDao(Connection conn) {
        this.conn = conn;
    }

    /**
     * inserts a new person into the database
     * @param person the person object that we want to insert into the database
     */


    public void insert(Person person) throws DataAccessException{

     String sql = "INSERT INTO Person (personID, associatedUsername, firstName, lastName, gender, fatherID, motherID,spouseID) VALUES(?,?,?,?,?,?,?,?)";

     try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      //Using the statements built-in set(type) functions we can pick the question mark we want
      //to fill in and give it a proper value. The first argument corresponds to the first
      //question mark found in our sql String
      stmt.setString(1, person.getPersonID());
      stmt.setString(2, person.getAssociatedUsername());
      stmt.setString(3, person.getFirstName());
      stmt.setString(4, person.getLastName());
      stmt.setString(5, person.getGender());
      stmt.setString(6, person.getFatherID());
      stmt.setString(7, person.getMotherID());
      stmt.setString(8, person.getSpouseID());


      stmt.executeUpdate();
     } catch (SQLException e) {
      e.printStackTrace();
      throw new DataAccessException("Error encountered while inserting a PERSON (Always double check this)  into the database");
     }


    }

    /**
     * Will return a specific person from the database
     * @param personID a unique ID associated with the person used for identification
     * @return the Person object associated with the ID parameter
     */

    public Person find(String personID) throws DataAccessException {

     Person person;
     ResultSet rs;
     String sql = "SELECT * FROM Person WHERE personID = ?;";
     try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setString(1, personID);
      rs = stmt.executeQuery();
      if (rs.next()) {
       person = new Person(rs.getString("personID"), rs.getString("AssociatedUsername"),
               rs.getString("firstName"), rs.getString("lastName"), rs.getString("gender"),
               rs.getString("fatherID"), rs.getString("motherID"), rs.getString("spouseID"));
       return person;
      } else {
       return null;
      }
     } catch (SQLException e) {
      e.printStackTrace();
      throw new DataAccessException("Error encountered while finding an event in the database");
     }
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
    public void clear() throws DataAccessException {
     String sql = "DELETE FROM Person";
     try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.executeUpdate();
     } catch (SQLException e) {
      e.printStackTrace();
      throw new DataAccessException("Error encountered while clearing the person table");
     }
    }


    //end of class
}