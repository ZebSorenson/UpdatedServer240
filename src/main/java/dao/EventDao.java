package dao;

import model.Event;

import java.sql.*;

/**
 * An EventDao. This will allow us to manipulate data of an Even model object
 */

public class EventDao {

    /**
     * the connection of the the EventDao to the database
     */
    private final Connection conn;

    /**
     * Constructor which will connect the EventDao to the database
     * @param conn the current connection
     */

    public EventDao(Connection conn) {
        this.conn = conn;
    }

    /**
     * Inserts a new event into the database
     * @param event a new event object to be inserted into the database with new values
     * @throws DataAccessException if we encounter an error with accessing the data, we will throw this custom error class
     */

    public void insert(Event event) throws DataAccessException {
        //We can structure our string to be similar to a sql command, but if we insert question
        //marks we can change them later with help from the statement
        String sql = "INSERT INTO Events (EventID, AssociatedUsername, PersonID, Latitude, Longitude, " +
                "Country, City, EventType, Year) VALUES(?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            //Using the statements built-in set(type) functions we can pick the question mark we want
            //to fill in and give it a proper value. The first argument corresponds to the first
            //question mark found in our sql String
            stmt.setString(1, event.getEventID());
            stmt.setString(2, event.getAssociatedUsername());
            stmt.setString(3, event.getPersonID());
            stmt.setFloat(4, event.getLatitude());
            stmt.setFloat(5, event.getLongitude());
            stmt.setString(6, event.getCountry());
            stmt.setString(7, event.getCity());
            stmt.setString(8, event.getEventType());
            stmt.setInt(9, event.getYear());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while inserting an event into the database");
        }
    }

    /**
     * Will return the specificied event from the database
     * @param eventID a unique ID string connected to the event in order to find in the database
     * @return The specified event connected to the eventID given in the parameter
     * @throws DataAccessException will be thrown if we encounter an error when attempting to acess the data
     */
    public Event find(String eventID) throws DataAccessException {
        Event event;
        ResultSet rs;
        String sql = "SELECT * FROM Events WHERE EventID = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, eventID);
            rs = stmt.executeQuery();
            if (rs.next()) {
                event = new Event(rs.getString("EventID"), rs.getString("AssociatedUsername"),
                        rs.getString("PersonID"), rs.getFloat("Latitude"), rs.getFloat("Longitude"),
                        rs.getString("Country"), rs.getString("City"), rs.getString("EventType"),
                        rs.getInt("Year"));
                return event;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding an event in the database");
        }

    }

    /**
     * will completely clear the event table
     * @throws DataAccessException will be thrown if we encounter an error while accessing this data
     */
    public void clear() throws DataAccessException {
        String sql = "DELETE FROM Events";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while clearing the event table");
        }
    }

    //code I have added

    /**
     * we should be able to make changes to an event details. This function will serve this purpose
     * @param event the specific event to which we want to make a change to.
     */
    public void update(Event event){
        //makes updates to even that is passed in
    }

    public void deleteUserData(String userName) throws DataAccessException {
        String sql = "DELETE FROM Events WHERE associatedUsername = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userName);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while clearing the person table");
        }
    }







    //end of class
}