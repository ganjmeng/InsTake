package func;

import org.json.JSONObject;

/**
 * Created by mac on 02/03/16.
 */
public class json {

    public static String jsonObject(String json , String key){

        try{

            JSONObject j = new JSONObject(func.convertCharacters.xmlchars(json));

            return j.getString(key);

        }catch(Exception e){

            return "";

        }
    }

}
