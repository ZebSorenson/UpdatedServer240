package service;

import RequestResult.clearRequest;
import RequestResult.clearResult;
import dao.Database;


/**
 * Service object that will perform the action of deleting all data from the database, including user, authtoken, person, and event data
 */
public class clear {

    /**
     * clears ALL data from database
     * @param clear_request the request object containing the needed info to perform the requested service
     * @return based on whether the service was successful or not, we will return a result object holding that info
     */

    public clearResult clear(clearRequest clear_request){

    clearResult resultToreturn = new clearResult();

    return resultToreturn;
    }


}

//want to pass request objects