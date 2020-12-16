package com.example.optimaltravel.UI;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.optimaltravel.R;
import com.example.optimaltravel.json.PlaceConverter;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import org.json.JSONException;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class CreatePath extends AppCompatActivity {
    private static int AUTOCOMPLETE_REQUEST_CODE = 1;
    public static List<String> keysList = new LinkedList<String>();
    public static HashMap<String, LatLng> keyMap = new HashMap<String, LatLng>();
    ListView listView = null;
    @SuppressLint("ResourceType")
    ArrayAdapter<String> adapter;
    Button bCalculateRoutes;
    Button bAddStop;
    HashMap<String, String> map;
    List<String> ls = new LinkedList<String>();

    @SuppressLint("ResourceType")
    @Override
    public void onCreate(Bundle saved) {
        super.onCreate(saved);
        setContentView(R.layout.activity_create_path);
        this.setFinishOnTouchOutside(false);
        listView = (ListView) findViewById(R.id.lvAddress);
        adapter = new ArrayAdapter<String>(this, R.id.lvAddress, ls);
        bAddStop = findViewById(R.id.bAddStop);
        bCalculateRoutes = findViewById(R.id.bCalculateRoute);
        listView.setAdapter(adapter);
        map = new HashMap<String, String>();


        // Set the fields to specify which types of place data to
        // return after the user has made a selection.


        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, ls);

        ListView listView = (ListView) findViewById(R.id.lvAddress);
        listView.setAdapter(adapter);
        PlaceConverter.mld.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isEnable) {
                bAddStop.setEnabled(true);
                bCalculateRoutes.setEnabled(true);
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void googleAutoComplete(View view) {
        String api = "AIzaSyBUPxQMO2iI0DS_WTeetlcND9mpWaUCyyY";
        Places.initialize(this, api);
        List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG);
        // Start the autocomplete intent.
        Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
                .build(this);
        startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                map.put(place.getId(), place.getAddress());
                keyMap.put(place.getId(), place.getLatLng());
                keysList.add(place.getId());
                ls.add(place.getAddress());
                adapter.notifyDataSetChanged();
                Log.i("shit", "Place: " + place.getAddress() + ", " + place.getId());
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // TODO: Handle the error.
                Status status = Autocomplete.getStatusFromIntent(data);
                Log.i("shit", status.getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    public void runParse(View view) throws IOException, JSONException {
        if (ls.size() < 4) // Not need to optimize
        {
            return;
        }
        bCalculateRoutes.setEnabled(false);
        bAddStop.setEnabled(false);
        new Thread() {
            public void run() {
                try {
                    PlaceConverter.placesListFromJson(map, ls, keysList);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void btShowmapClick(View view) {
        startActivity(new Intent(this, MapsActivity.class));
    }
}