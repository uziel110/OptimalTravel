package com.example.optimaltravel.json;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class PlaceConverter {
       public static MutableLiveData  mld = new MutableLiveData<>();
    @RequiresApi(api = Build.VERSION_CODES.R)
    public static void placesListFromJson(HashMap<String, String> map, List<String> list) throws JSONException, IOException {
        List<String> keyList= new ArrayList<>(map.keySet());

        JSONObject jsonObject = JsonParser.getJson(keyList);
        JSONArray placesIDList = (JSONArray) jsonObject.get("geocoded_waypoints");

        for (int i = 0; i < placesIDList.length(); ++i) {
            JSONObject record = placesIDList.getJSONObject(i);
            String place = record.getString("place_id");
            //optimalTravel.add(place);
            list.set(i, map.get(place));
        }
        mld.postValue(true);

      // return list;
    }
}
