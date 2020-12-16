package com.example.optimaltravel.json;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class PlaceConverter {
    public static MutableLiveData mld = new MutableLiveData<>();

    @RequiresApi(api = Build.VERSION_CODES.R)
    public static void placesListFromJson(HashMap<String, String> map, List<String> list, List<String> keysList) throws JSONException, IOException {
        JSONObject jsonObject = JsonParser.getJson(keysList);
        JSONArray placesIDList = (JSONArray) jsonObject.get("geocoded_waypoints");

        for (int i = 0; i < placesIDList.length(); ++i) {
            JSONObject record = placesIDList.getJSONObject(i);
            String place = record.getString("place_id");
            //optimalTravel.add(place);
            list.set(i, map.get(place));
            keysList.set(i,place);
        }
        mld.postValue(true);

        // return list;
    }
}
