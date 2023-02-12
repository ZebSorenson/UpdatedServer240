package dao;

import model.User;

import java.sql.*;



/**
 * User DAO for accessing user data in the database
 */

public class UserDao {

    private final Connection conn;

    public UserDao(Connection conn) {this.conn = conn;
    }

    //pass in and recieve model objects back in return

    public int getIntegerValue(){
        return 5;
    }



    /**
     * will insert a new user into the database
     * @param user new user to be inserted into the database
     */

    public void insert(User user) throws DataAccessException{

        String sql = "INSERT INTO User (username, password, email, firstName, lastName, gender, personID) VALUES(?,?,?,?,?,?,?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            //Using the statements built-in set(type) functions we can pick the question mark we want
            //to fill in and give it a proper value. The first argument corresponds to the first
            //question mark found in our sql String
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getFirstName());
            stmt.setString(5, user.getLastName());
            stmt.setString(6, user.getGender());
            stmt.setString(7, user.getPersonID());


            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while inserting an event into the database");
        }


    }

    /**
     * Will return a specific user from the database
     * @param paramater_personID a unique ID associated with the user in order to find it in the database
     * @return the user associated with the specified ID from the parameter
     */
    public User find(String paramater_personID ){

        return null;

    }

    /**
     * will delete 1 specific user from the database
     * @param parameter_userID the unique ID for the specified user from the database
     */
    public void clear(String parameter_userID){

    }

    /**
     * will clear all of the users from the database
     */

    public void clearAll(){

    }



    boolean login(String username, String password){
        return false;
    }
}