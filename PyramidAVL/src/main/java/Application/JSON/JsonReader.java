/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.JSON;

import Application.Web.Exceptions.ApiRequestException;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;

/**
 *
 * @author jefemayoneso
 */
public class JsonReader {

    /**
     * Return a JSON with syntax {"key":"value"...} as a single HashMap
     *
     * @param json
     * @return
     */
    public HashMap<String, String> getJsonAsHash(String json) {
        HashMap<String, String> hash = new HashMap<>();

        try {
            // a JSON contains [ {... list ...} ], we receive just list, so need to be casted
            json = String.format("[%1$s]", json);
            JSONArray jsonArray = new JSONArray(json); // get array list
            int length = jsonArray.length();

            // move into array
            for (int i = 0; i < length; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Iterator<String> keys = jsonObject.keys();

                while (keys.hasNext()) {
                    //this will give 1st level keys - "surname,name,course,_id,grades"
                    String key = keys.next();
                    if (jsonObject.get(key) instanceof JSONObject) {
                        //build either a recursive function or required logic to iterate over inner json objects similar to this
                    } else if (jsonObject.get(key) instanceof JSONArray) {
                        //build either a recursive function or required logic to iterate over inner json arrays similar to this
                    } else {
                        hash.put(key, jsonObject.get(key).toString());
                    }
                }
            }
        } catch (JSONException ex) {
            System.out.println("Error reading JSON: " + ex.getMessage());
            throw new ApiRequestException("Error leyendo el JSON, revisa las llaves", HttpStatus.NOT_ACCEPTABLE);
        }

        return hash;
    }

}
