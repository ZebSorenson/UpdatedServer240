package JSonMagic.json;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;

public class femaleNameGenerator {

    ArrayList<String> femaleNameListArray = new ArrayList<>();


    public ArrayList<String> getFemaleNameList() throws FileNotFoundException {
        try{
            Reader reader = new FileReader("json/fnames.json");

            Gson gson = new Gson();

            femaleNameData fnameList = (femaleNameData) gson.fromJson(reader, femaleNameData.class); //is this filling the array with location objects?

//            System.out.println(fnameList);
//
//            return fnameList;

            for(String fname: fnameList.getData()){

                femaleNameListArray.add(fname);

            }

           // System.out.println(femaleNameListArray);

            return femaleNameListArray;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (JsonSyntaxException e) {
            throw new RuntimeException(e);
        } catch (JsonIOException e) {
            throw new RuntimeException(e);
        }
    }


}
