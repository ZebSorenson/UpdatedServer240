package dao;

import model.Authtoken;
import model.Event;
import model.Person;

import java.sql.*;
import java.util.ArrayList;

/**
 * Person Data Access class. This will be used to manipulate data of person objects
 */

public class PersonDao {

    /**
     * connection object for connecting to the database
     */

    private final Connection conn;

    public PersonDao(Connection conn) {
        this.conn = conn;
    }

    /**
     * inserts a new person into the database
     *
     * @param person the person object that we want to insert into the database
     */


    public void insert(Person person) throws DataAccessException {

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

            // System.out.println(person.toString());


            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while inserting a PERSON (Always double check this)  into the database");
        }


    }

    /**
     * Will return a specific person from the database
     *
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

    public Boolean findTrue(String personID) throws DataAccessException {

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
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding an event in the database");
        }
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


    public void deleteUserData(String userName) throws DataAccessException {

        if (userName == null) {
            throw new DataAccessException("The username was null, DataAccessException thrown");
        }
        String sql = "DELETE FROM Person WHERE associatedUsername = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userName);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while clearing the person table");
        }
    }


    public Person[] getAllPeopleWithUsername(String username) throws DataAccessException {

        ResultSet rs = null;

        ArrayList<Person> personArrayList = new ArrayList<>();


        String sql = "SELECT * FROM Person WHERE associatedUsername = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);

            rs = stmt.executeQuery();

            while (rs.next()) {
                Person personToAdd = new Person(rs.getString("personID"), rs.getString("associatedUsername"), rs.getString("firstName"),
                        rs.getString("lastName"), rs.getString("gender"), rs.getString("fatherID"), rs.getString("motherID"), rs.getString("spouseID"));
                personArrayList.add(personToAdd);
            }

            return personArrayList.toArray(new Person[personArrayList.size()]);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while searching for an AuthToken");
        }
    }


    //end of class
}