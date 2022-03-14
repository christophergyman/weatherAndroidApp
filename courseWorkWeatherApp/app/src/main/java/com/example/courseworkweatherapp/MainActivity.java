package com.example.courseworkweatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button button;

    //On create function, function that is called when the activity is loaded in.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Attaching the button elements with their R ID's
        button = (Button) findViewById(R.id.startButton);

        //Creating an onclick listener for the button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            //The on the button click call the function openMainActivity
            public void onClick(View view) {
                openMainActivity();
            }
        });


    }


    //Called when button is clicked, creates intent to open main activity screen
    private void openMainActivity() {
        Intent intent = new Intent(this, MainAppScreen.class);
        startActivity(intent);
    }
}