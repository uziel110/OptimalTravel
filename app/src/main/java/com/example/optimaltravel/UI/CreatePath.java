package com.example.optimaltravel.UI;

import androidx.appcompat.app.AppCompatActivity;

import com.example.optimaltravel.R;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import android.os.Bundle;
import android.widget.ListView;

public class CreatePath extends AppCompatActivity {
    ListView listView = null;
    @Override
    public void onCreate(Bundle saved) {
        super.onCreate(saved);
        setContentView(R.layout.activity_create_path);
        this.setFinishOnTouchOutside(false);
        listView = findViewById(R.id.lvAddress);


        Object autocompleteFragment =
                supportFragmentManager.findFragmentById(R.id.autocomplete_fragment)
        as AutocompleteSupportFragment;
    }
}