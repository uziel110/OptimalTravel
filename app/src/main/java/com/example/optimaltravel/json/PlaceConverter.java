package com.example.optimaltravel.json;

import android.os.Build;

import androidx.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PlaceConverter {

    @RequiresApi(api = Build.VERSION_CODES.R)
    public static List<String> placesListFromJson(JSONObject jsonObject) throws JSONException, IOException {
        JSONArray placesIDList = (JSONArray) jsonObject.get("geocoded_waypoints");
        List<String> optimalTravel = new LinkedList<>();
        for (int i = 0; i < placesIDList.length(); ++i) {
            JSONObject record = placesIDList.getJSONObject(i);
            String place = record.getString("place_id");
            optimalTravel.add(place);
        }
        return optimalTravel;
    }
}
