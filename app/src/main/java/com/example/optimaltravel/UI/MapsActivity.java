package com.example.optimaltravel.UI;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.Button;

import com.example.optimaltravel.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.model.Place;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button btGetDirections;
    List<MarkerOptions> points = new LinkedList<MarkerOptions>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        MapFragment mapFragment1 = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment1.getMapAsync(this);

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;// Add a marker in Sydney and move the camera
        mMap.clear();
        points.clear();
        for (String it : CreatePath.keysList) {
            points.add(new MarkerOptions().position(CreatePath.keyMap.get(it)));
        }
        for (int i = 0; i < points.size(); i++) {
            mMap.addMarker(points.get(i));
        }
        MarkerOptions firstLocation = points.get(0);
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(firstLocation.getPosition()));
        //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(firstLocation.getPosition(), 13.0f));
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (MarkerOptions marker : points) {
            builder.include(marker.getPosition());
        }
       /* if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }*/
        //mMap.setMyLocationEnabled(true);
        LatLngBounds bounds = builder.build();
        int padding = 140;
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
        mMap.setMaxZoomPreference(8.0f);
        googleMap.animateCamera(cu);

    }
}