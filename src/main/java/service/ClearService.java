package service;

import RequestResult.clearRequest;
import RequestResult.clearResult;


/**
 * Service object that will perform the action of deleting all data from the database, including user, authtoken, person, and event data
 */
public class ClearService {

    /**
     * clears ALL data from database
     * @param clearReq the request object containing the needed info to perform the requested service. This is coming from the http handler
     * @return clearResult object containing info depending on whether or not the service was successful
     */

    public clearResult clear(clearRequest clearReq){

    clearResult resultToreturn = new clearResult();

    return resultToreturn;
    }


}

//want to pass request objects