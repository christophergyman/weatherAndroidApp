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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Showing shard pref on startup
//        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
//        String str = (sharedPreferences.getString("defaultCountry", "Default_Value"));
//        Toast.makeText(this, "SavedPref: " + str, Toast.LENGTH_SHORT).show();

        button = (Button) findViewById(R.id.startButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainActivity();
            }
        });


    }

    private void openMainActivity() {
        Intent intent = new Intent(this, MainAppScreen.class);
        startActivity(intent);
    }
}