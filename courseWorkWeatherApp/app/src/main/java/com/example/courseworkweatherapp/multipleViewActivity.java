package com.example.courseworkweatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
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

import java.util.ArrayList;

public class multipleViewActivity extends AppCompatActivity {

    //Assining variables that will be used later
    RecyclerView recyclerView;
    ArrayList<String> countryNamesArraylist;
    private String[] countryNamesArray, CountryTempArray;
    TextView names1, names2;

    //On create function, function that is called when the activity is loaded in.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_view);

        //List of some random countries the user will see
        String[] countryNamesArray = {
                "London",
                "Hong Kong",
                "Berlin",
                "China",
                "Canada",
                "Japan",
                "Korea",
                "Ireland",
                "New Zealand",
                "Jamaica",
                "Australia",
                "India",
        };


        //Initialise empty string array with certain elements
        String[] countryTempArray= {
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
        };


        //create recycler view entity and attach it to the view with its R id
        recyclerView = findViewById(R.id.recyclerView);
        MyAdapter myAdapter = new MyAdapter(this, countryNamesArray, countryTempArray);
        //Set the recycler views adapter
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        //Retrieve Extras intent and use toast to see current location
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            String locationRequest= extras.getString("locationRequest");
            Toast.makeText(this, "Current Location Set: " + locationRequest, Toast.LENGTH_SHORT).show();
        }

    }

}