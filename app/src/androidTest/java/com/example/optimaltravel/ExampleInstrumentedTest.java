package com.example.optimaltravel;

import android.content.Context;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.optimaltravel.json.JsonParser;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        try {
            JsonParser.getJson("https://maps.googleapis.com/maps/api/directions/json?origin=afula&destination=afula&waypoints=optimize:true|natanya+teena|tel-aviv|hadera&key=AIzaSyBUPxQMO2iI0DS_WTeetlcND9mpWaUCyyY");
        }
        catch (IOException e )
        {
            Log.i("sasdas",e.getMessage());
        }

        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.optimaltravel", appContext.getPackageName());
    }
}