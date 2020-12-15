package com.example.optimaltravel.UI;

import androidx.appcompat.app.AppCompatActivity;

import com.example.optimaltravel.R;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;


import java.util.Arrays;
import java.util.List;

public class CreatePath extends AppCompatActivity {
    ListView listView = null;
    private static int AUTOCOMPLETE_REQUEST_CODE = 1;
    @Override
    public void onCreate(Bundle saved) {
        super.onCreate(saved);
        setContentView(R.layout.activity_create_path);
        this.setFinishOnTouchOutside(false);
        listView = findViewById(R.id.lvAddress);



        // Set the fields to specify which types of place data to
        // return after the user has made a selection.

    }

    public void Shit(View view) {
        String api ="AIzaSyBUPxQMO2iI0DS_WTeetlcND9mpWaUCyyY";
        Places.initialize(this,api);
        List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME);

        // Start the autocomplete intent.
        Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
                .build(this);
        startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);
    }
}