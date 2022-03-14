package com.example.courseworkweatherapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainAppScreen extends AppCompatActivity {
    Context context;
    TextView textView, countryNameTextView, locationTempTextView,
            localConditionTextView, localTimeTextView;
    private Button button;
    private RequestQueue queue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app_screen);

//        Assign screen elements objects to their ID's

        countryNameTextView = (TextView) findViewById(R.id.countryNameText);
        locationTempTextView= (TextView) findViewById(R.id.locationTemp);
        localConditionTextView= (TextView) findViewById(R.id.localCondition);
        localTimeTextView= (TextView) findViewById(R.id.localTimeText);

        button = (Button) findViewById(R.id.myLocationButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //start onclick

//        Make URL with country as choice
                StringBuilder url = new StringBuilder();

                String startURL = "http://api.weatherapi.com/v1/current.json?key=";
                url.append(startURL);

                String apiKey = "7d9707fc343e49a682d165402220903";
                url.append(apiKey);

                String locationTag = "&q=";
                url.append(locationTag);

//        Read user settings default country

                //Showing shard pref on startup
                final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                String locationRequest= (sharedPreferences.getString("defaultCountry", "Default_Value"));
//                Toast.makeText(this, "SavedPref: " + locationRequest, Toast.LENGTH_SHORT).show();
                url.append(locationRequest);

                String endTag = "&aqi=no";
                url.append(endTag);

                //What the final string should look like using my API key and london as hardcoded
                //http://api.weatherapi.com/v1/current.json?key=7d9707fc343e49a682d165402220903&q=London&aqi=no
                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(MainAppScreen.this);

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url.toString(),
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
//                                textView.setText("Response is: " + response.substring(0,500));


                                //Json object reader to read the api call reply
                                try {
                                    JSONObject reader = new JSONObject(response);
                                    JSONObject locationJson = reader.getJSONObject("location");
                                    String countryName = locationJson.getString("country");
                                    String localTime = locationJson.getString("localtime");


                                    JSONObject currentJson = reader.getJSONObject("current");
                                    String locationTemp= currentJson.getString("temp_c");
                                    String humidity = currentJson.getString("humidity");

                                    countryNameTextView.setText(countryName);
                                    locationTempTextView.setText(locationTemp);
                                    localConditionTextView.setText(humidity);
                                    localTimeTextView.setText(localTime);


//        , countryNameTextView, locationTempTextView,
//                localConditionTextView, localTimeTextView;

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(MainAppScreen.this, "Json object" +
                                                    " reader error"
                                            , Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainAppScreen.this, "API CALL ERROR"
                                , Toast.LENGTH_SHORT).show();
                        countryNameTextView.setText(error.toString());
//                        textView.setText(error.toString());
                    }
                });

                // Add the request to the RequestQueue.
                queue.add(stringRequest);

            }
        });

    }

//    Show the menu options at the top of screen
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.item1:


                //HERE READ DEFAULT LOCATION SET
                //Showing shard pref on startup
                final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                String locationRequest= (sharedPreferences.getString("defaultCountry", "Default_Value"));


//                Toast.makeText(this, "Current Location Set: " + locationRequest, Toast.LENGTH_SHORT).show();
                openMultipleViewActivity();
                return true;

            case R.id.item2:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                //Intent to start settings activity
                openSettingsActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openSettingsActivity() {
        Intent intentSettings = new Intent(this, settingsActivity.class);
        startActivity(intentSettings);
    }

    private void openMultipleViewActivity(){
        //open multiple view activity and pass throuh extras
        // while doing it to meet explicit/implicit intents

        //Grab default country location from shared preferences:
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String locationRequest= (sharedPreferences.getString("defaultCountry", "Default_Value"));

        Intent intentMultipleviewActivity = new Intent(this, multipleViewActivity.class);
        intentMultipleviewActivity.putExtra("locationRequest", locationRequest);

        startActivity(intentMultipleviewActivity);

    }


}