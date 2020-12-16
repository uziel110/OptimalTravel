package com.example.optimaltravel.json;

import android.location.Location;
import android.os.Build;

import androidx.annotation.RequiresApi;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

public class JsonParser {

    @RequiresApi(api = Build.VERSION_CODES.R)
    public static JSONObject getJson(List<String> wayPoints) throws IOException {
        //wayPoints=List.of("afula", "tel-aviv","afula");
        JSONObject json = null;
        String begin = "https://maps.googleapis.com/maps/api/directions/json?origin=place_id:";
        int size = wayPoints.size();
        String origin = wayPoints.get(0)+"&";
        String points = "destination=place_id:"+wayPoints.get(size-1)+"&waypoints=optimize:true";
        for (int i = 1; i < size-1; ++i)
            points += "|place_id:" + wayPoints.get(i);
        String end = "&key=AIzaSyBUPxQMO2iI0DS_WTeetlcND9mpWaUCyyY";
        String link = begin + origin + points + end;
        InputStream is = new URL(link).openStream();
       // InputStream is = new URL("https://maps.googleapis.com/maps/api/directions/json?origin=afula&destination=Tel-Aviv&key=AIzaSyBUPxQMO2iI0DS_WTeetlcND9mpWaUCyyY").openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            //Object[] ff = rd.lines().toArray();
            //  String s= rd.lines().collect(Collectors.joining());
            String jsonText = "";
            String line = rd.readLine();



            while (line!=null && !line.equals("   ],"))
            {
                jsonText+= line + "\n";
                line = rd.readLine();
            }
            json = new JSONObject(jsonText+="]}");



        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            is.close();
        }
    return json;
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    private Location getCurrentLocation() {
        return null;
    }
}
//   LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//
//
//   if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
//           && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
//           && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {
//
//    return;
//   }
//
//   try {
//
//    gps_loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//    network_loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//
//   } catch (Exception e) {
//    e.printStackTrace();
//   }
//
//   if (gps_loc != null) {
//    final_loc = gps_loc;
//    latitude = final_loc.getLatitude();
//    longitude = final_loc.getLongitude();
//   }
//   else if (network_loc != null) {
//    final_loc = network_loc;
//    latitude = final_loc.getLatitude();
//    longitude = final_loc.getLongitude();
//   }
//   else {
//    latitude = 0.0;
//    longitude = 0.0;
//   }
//
//
//   ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_NETWORK_STATE}, 1);
//
//   try {
//
//    Geocoder geocoder = new Geocoder(this, Locale.getDefault());
//    List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
//
//   } finally {
//
//   }
//





