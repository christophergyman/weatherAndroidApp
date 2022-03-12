package com.example.courseworkweatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

    RecyclerView recyclerView;
    ArrayList<String> countryNamesArraylist;
    private String[] countryNamesArray, CountryTempArray;
    TextView names1, names2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_view);

        String[] countryNamesArray = {
                "London",
                "Hong Kong",
                "Berlin",
                "China",
                "Canada",
                "Japan",
                "Korea",
        };


        String[] countryTempArray= {
                "",
                "",
                "",
                "",
                "",
                "",
                "",
        };


        recyclerView = findViewById(R.id.recyclerView);
        MyAdapter myAdapter = new MyAdapter(this, countryNamesArray, countryTempArray);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}