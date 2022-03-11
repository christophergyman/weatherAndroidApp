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
    private TextView defaultCountryTextView;
    private EditText editText;
    private Button saveButton;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";

    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //Assining button variables to their elements
        defaultCountryTextView = (TextView) findViewById(R.id.currentDefaultCountryTextView);
        editText = (EditText) findViewById(R.id.myLocationEditText);
        saveButton = (Button) findViewById(R.id.saveSettingsButton);


        updateViews();

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

    public static void setDefaults(String key, String value, Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getDefaults(String key, Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }

    public void updateViews(){
       String str = getDefaults("defaultCountry", this);
       defaultCountryTextView.setText(str);
       Toast.makeText(this, "Default Location: " + str, Toast.LENGTH_SHORT).show();
    }



//    private void saveData() {
//        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString(TEXT, defaultCountryTextView.getText().toString());
//
//        editor.apply();
//        Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();
//
//    }
//    public void loadData(){
//        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
//        text = sharedPreferences.getString(TEXT, "London");
//
//    }
//    public void updateViews(){
//        defaultCountryTextView.setText(text);
//    }


}