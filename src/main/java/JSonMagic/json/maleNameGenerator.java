package JSonMagic.json;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;

public class maleNameGenerator {

    ArrayList<String> maleNameArray = new ArrayList<>();


    public ArrayList<String> getMaleNameArray() throws FileNotFoundException {
        try {

            Reader reader = new FileReader("json/mnames.json");

            Gson gson = new Gson();

            maleNameData maleData = (maleNameData) gson.fromJson(reader, maleNameData.class); //is this filling the array with location objects?


            for (String mname : maleData.getData()) {

                maleNameArray.add(mname);

            }


            return maleNameArray;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (JsonSyntaxException e) {
            throw new RuntimeException(e);
        } catch (JsonIOException e) {
            throw new RuntimeException(e);
        }
    }


}
