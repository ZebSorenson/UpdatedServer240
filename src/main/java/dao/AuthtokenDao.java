package dao;

import model.Authtoken;

/**
 * A unique token for each user everytime they log in. Used for authentication purposes to be stored in the database
 */
public class AuthtokenDao {

    /**
     * Everytime an Authtoken is needed. The constructor will use the Java UUID class to generate a new one
     * @return a new unique Authtoken
     */
    public Authtoken getNewAuthToken(){
        return null;
    }


}