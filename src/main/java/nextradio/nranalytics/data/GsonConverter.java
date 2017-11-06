package nextradio.nranalytics.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by gkondati on 10/31/2017.
 */

public class GsonConverter {
    private static final String TAG = "GsonConverter";

    private static GsonConverter instance;

    public static GsonConverter getInstance() {
        if (instance == null) {
            instance = new GsonConverter();
        }
        return instance;
    }

    public <T> String convertObetToString(String savedValues, T actionPayload) {
        List<T> actionList = convertStringToArrayList(savedValues);
        actionList.add(actionPayload);
        return getGson().toJson(actionList, getType());
    }

    private <T> List<T> convertStringToArrayList(String savedValues) {
        if (!savedValues.isEmpty()) {
            return getGson().fromJson(savedValues, getType());
        } else {
            return new ArrayList<>();
        }
    }

    public <T> String createMapToString(String savedValues, HashMap<String, String> jsonMap) {
        List<T> actionList = convertStringToArrayList(savedValues);
        try {
            JSONObject utcJsonObject = new JSONObject(jsonMap);
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(utcJsonObject);
            return jsonArray.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        //String str_json = getGson().tojson(jsonArray);
    }

    private Gson getGson() {
        return new Gson();
    }

    private <T> Type getType() {
        return new TypeToken<List<T>>() {
        }.getType();
    }
}
