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
    public static void placesListFromJson(HashMap<String, String> map, List<Double>currentLocation, List<String> namesList, List<String> keysList) throws JSONException, IOException {
        JSONObject jsonObject = JsonParser.getJson(currentLocation, keysList);
        JSONArray placesIDList = (JSONArray) jsonObject.get("geocoded_waypoints");
        int len = placesIDList.length();
        for (int i = 0; i < len - 1; ++i) {
            JSONObject record = placesIDList.getJSONObject(i);
            String place = record.getString("place_id");
            if (i == 0) {
                keysList.add(i, place);
            } else {
                namesList.set(i-1, map.get(place));
                keysList.set(i, place);
            }
        }
        mld.postValue(true);

        // return list;
    }
}
