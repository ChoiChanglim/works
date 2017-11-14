import java.io.IOException;
import java.util.HashMap;

import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonToMap {

    public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
        JSONObject json = new JSONObject();
        json.put("name", "blair");
        json.put("sex", "female");

        System.err.println(json);

        HashMap<String, Object> map = new HashMap<String, Object>();
        HashMap<String,Object> result = new ObjectMapper().readValue(json.toString(), HashMap.class);

        System.err.println(result);


    }

}
