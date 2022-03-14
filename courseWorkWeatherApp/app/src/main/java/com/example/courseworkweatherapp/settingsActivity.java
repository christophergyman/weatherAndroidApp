package com.example.courseworkweatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class settingsActivity extends AppCompatActivity {
    //Assining variables to be used later
    private TextView defaultCountryTextView;
    private EditText editText;
    private Button saveButton;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    private String text;

    //On create function, function that is called when the activity is loaded in.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //Assigning button variables to their elements
        defaultCountryTextView = (TextView) findViewById(R.id.currentDefaultCountryTextView);
        editText = (EditText) findViewById(R.id.myLocationEditText);
        saveButton = (Button) findViewById(R.id.saveSettingsButton);
        updateViews();

        //Set onClick listener for the button
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get default country Textview from editText
                defaultCountryTextView.setText(editText.getText().toString());

                //Save the defaultCountryTextView to a shared preferences
                setDefaults("defaultCountry", defaultCountryTextView.getText().toString(),
                        settingsActivity.this);

            }
        });
    }

    //Set the default country that is set, read from shared pref
    public static void setDefaults(String key, String value, Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    //Acquire the default country set
    public static String getDefaults(String key, Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }

    //Update the current default country to a textview on screen
    public void updateViews(){
       String str = getDefaults("defaultCountry", this);
       defaultCountryTextView.setText(str);
    }

}