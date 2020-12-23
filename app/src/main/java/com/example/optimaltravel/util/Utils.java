package com.example.optimaltravel.util;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

public class Utils {
    public static Intent OpenInGoogleMaps(StringBuilder currentPlaceId, List<String> keysList) {
        // https://www.google.com/maps/dir/?api=1&origin=Afula&origin_place_id=ChIJ-zbFi8NTHBURSwqqD4dNEuM&destination=tel+aviv&destination_place_id=ChIJH3w7GaZMHRURkD-WwKJy-8E&waypoints=b|a|a&waypoint_place_ids=ChIJ1XXAkwRAHRURIj88VL6V2Sw|adasdasassad|dasdasdsad
        if (keysList.size() == 0)
            return null;
        String origin = "https://www.google.com/maps/dir/?api=1&origin=GPS&origin_place_id=" + currentPlaceId.toString() + "&destination=GPS&destination_place_id=" + currentPlaceId.toString();
        String wayP = "&waypoints=";
        String wayPid = "&waypoint_place_ids=";

        for (int i = 0; i < keysList.size(); ++i) {
            wayP += "wp";
            wayPid += "" + keysList.get(i);
            if (i < keysList.size() - 1) { //if is not the last stop
                wayP += "|";
                wayPid += "|";
            }
        }
        return new Intent(Intent.ACTION_VIEW, Uri.parse(origin + wayP + wayPid + "&travelmode=driving"));
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    @SuppressLint("RestrictedApi")
    public static List<Double> getCurrentLocation(Activity activity) {
        FusedLocationProviderClient fusedLocationProviderClient;
        final double[] lat = new double[1];
        final double[] longt = new double[1];
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getApplicationContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                fusedLocationProviderClient.getLastLocation()
                        .addOnSuccessListener(new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                if (location != null) {
                                    lat[0] = location.getLatitude();
                                    longt[0] = location.getLongitude();
                                    //Toast.makeText(activity.getBaseContext(), lat + " , " + longt, Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            } else {
                activity.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 5);
            }
        }

        return List.of(lat[0], longt[0]);
    }
}
