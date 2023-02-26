package service;

import model.Person;

import java.util.UUID;



public class GenerateTree {

    Person generatePerson(String gender, int generations ){

        Person mother = null;

        Person father = null;

        if(generations>1){
            mother = generatePerson("f", generations-1);
            father = generatePerson("m", generations-1);

            String spouseID = UUID.randomUUID().toString();

            mother.setSpouseID(spouseID);

            father.setSpouseID(spouseID);

            //add the marriage events that need to be in sync
        }

       // Person person = new Person(UUID.randomUUID().toString(), )
     return null;
    }
}
