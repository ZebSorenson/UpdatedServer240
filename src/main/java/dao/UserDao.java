package dao;

import model.User;

/**
 * User DAO for accessing user data in the database
 */

public class UserDao {

    //pass in and recieve model objects back in return

    public int getIntegerValue(){
        return 5;
    }

    /**
     * will insert a new user into the database
     * @param user new user to be inserted into the database
     */

    public void insert(User user){

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